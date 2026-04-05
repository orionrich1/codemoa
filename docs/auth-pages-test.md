# 코드 품질 검토 보고서 — 인증 페이지 (로그인·회원가입·아이디/비밀번호 찾기) — 2026-04-05

---

## 미해결 위반 사항

| 파일 | 위반 내용 | 심각도 | 수정 방향 |
|---|---|---|---|
| `joinForm.html` 18행 | `<form action="/join">` → `th:action` 미사용으로 Thymeleaf CSRF 토큰 미주입 → POST `/join` 403 Forbidden → **회원가입 불가** | 🔴 Critical | `action="/join"` → `th:action="@{/join}"` 으로 변경 |
| `joinForm.html` 82~101행 | `/checkId` AJAX POST에 CSRF 토큰 없음 + SecurityConfig ignore 목록 미등록 → 403 Forbidden → 중복 체크 실패 → 회원가입 버튼 클릭 시 아무 반응 없음 | 🔴 Critical | SecurityConfig CSRF ignore 목록에 `/checkId` 추가 또는 AJAX에 `X-XSRF-TOKEN` 헤더 추가 |
| `findResult.html` 33행 | `<form action="/updatePass" method="POST">` → `th:action` 미사용 → CSRF 토큰 미주입 → **비밀번호 재설정 불가** | 🔴 Critical | `action="/updatePass"` → `th:action="@{/updatePass}"` 로 변경 |
| `loginForm.html` 38~42행 | "로그인 정보 저장" 체크박스: `name` 속성 없음 + SecurityConfig에 `rememberMe()` 미설정 → 체크 여부와 무관하게 완전 미동작 | 🟡 Major | SecurityConfig에 `.rememberMe()` 설정 추가 및 체크박스에 `name="remember-me"` 속성 추가 |
| `UserController.java` 55행 | `join(UserSignUpRequest request)` 파라미터에 `@Valid` 없음 → DTO의 `@NotBlank`, `@Size(min=4,max=20)`, `@Email` 제약 조건이 실제로 적용되지 않음 | 🟡 Major | 파라미터 앞에 `@Valid` 추가 + `GlobalExceptionHandler`에 `MethodArgumentNotValidException` 처리 추가 |
| `joinForm.html` 22행 | HTML 아이디 안내 "영문, 숫자 조합 **6~12자**" vs `UserSignUpRequest` DTO `@Size(min=4, max=20)` **(4~20자)** → 클라이언트 안내와 서버 유효성 기준 불일치 | 🟡 Major | HTML 안내 문구를 DTO 기준 "4~20자" 에 맞추거나, DTO의 `@Size`를 "6~12자" 로 통일 |
| `UserSignUpRequest.java` | `passConfirm` 필드 없음 → 서버에서 비밀번호 일치 검사 불가. 클라이언트 JS만 검증 → JS 비활성화 또는 API 직접 호출 시 우회 가능 | 🟠 Minor | DTO에 `passConfirm` 필드 추가 후 서비스 레이어에서 `pass.equals(passConfirm)` 검증 |
| `findResult.html` 39행, 47행 | `<i class="fa-solid fa-lock">`, `<i class="fa-solid fa-check">` → Font Awesome 사용. 프로젝트에 Font Awesome CDN 미포함 → 자물쇠·체크 아이콘 미표시 | 🟠 Minor | Bootstrap Icons로 교체: `bi bi-lock`, `bi bi-check-lg` |
| `findResult.html` 46~50행 | 비밀번호 재설정 폼에 `passConfirm` 입력 필드 있으나 일치 검증 JS 없음. `UserPassUpdateRequest`에도 `passConfirm` 없어 서버 검증 전혀 없음 → 실수로 다른 비밀번호 설정 가능 | 🟠 Minor | `findResult.html` 제출 전 JS로 일치 확인 추가. `UserPassUpdateRequest`에 `passConfirm` 추가 후 서비스 검증 |
| `findId.html` 30~31행 | `<a href="findId">`, `<a href="findPass">` 상대 경로 → 현재 URL `/findId` 기준 상대 경로로 동작하므로 의도대로 작동하나, Thymeleaf 컨텍스트 패스 변경 시 깨짐 | 🟢 Low | `th:href="@{/findId}"`, `th:href="@{/findPass}"` 로 변경 |
| `findPass.html` 35~36행 | 동일: `<a href="findId">`, `<a href="findPass">` 상대 경로 | 🟢 Low | `th:href="@{/findId}"`, `th:href="@{/findPass}"` 로 변경 |
| `findId.html`, `findPass.html` | 전화번호 입력 필드에 숫자 입력 제한 JS 없음 (joinForm은 있음) → 하이픈 포함 시 DB 조회 불일치 가능 | 🟢 Low | `joinForm.html`의 `$('#mobile').on('input', ...)` 패턴과 동일하게 적용 |

---

## 기능별 상세 분석

### 1. 로그인 (`/loginForm`)

| 항목 | 상태 | 비고 |
|---|---|---|
| 아이디/비밀번호 폼 로그인 | ✅ 동작 | Spring Security `formLogin()` 처리, `th:action="@{/login}"` CSRF 정상 |
| 로그인 실패 에러 메시지 | ✅ 동작 | `${param.error}` → Spring Security 자동 파라미터 |
| Google / Kakao OAuth2 로그인 | ✅ 동작 | `btn-kakao` CSS `site-ds.css`에 정의됨 (`body.site-ds` 적용) |
| SNS 연동 후 안내 + 해제 버튼 | ✅ 동작 | 세션 `provider` 유무에 따라 분기 |
| **로그인 정보 저장 (Remember Me)** | ❌ 미구현 | 체크박스에 `name` 없음, SecurityConfig `rememberMe()` 미설정 |

### 2. 회원가입 (`/joinForm`)

| 항목 | 상태 | 비고 |
|---|---|---|
| 폼 렌더링 | ✅ 동작 | |
| 전화번호 숫자 제한 JS | ✅ 동작 | `input` 이벤트로 숫자만 허용 |
| **폼 제출 (`POST /join`)** | ❌ 고장 | `action="/join"` → CSRF 토큰 없음 → 403 |
| **아이디 중복 체크 (`POST /checkId`)** | ❌ 고장 | CSRF 토큰 없음 → 403, ignore 목록 미등록 |
| 비밀번호 확인 클라이언트 검증 | ✅ 동작 | JS `pass != passConfirm` 체크 |
| **비밀번호 확인 서버 검증** | ❌ 미구현 | DTO에 `passConfirm` 없음 |
| **서버 유효성 검사 (@Valid)** | ❌ 미구현 | 컨트롤러 파라미터에 `@Valid` 없음 |
| **아이디 입력 안내 일관성** | ❌ 불일치 | HTML "6~12자" / DTO `@Size(min=4,max=20)` |
| SNS 연동 회원가입 흐름 | ✅ 구현 | 세션 `provider`/`providerId` 연동 로직 있음 |

### 3. 아이디 찾기 (`/findId`)

| 항목 | 상태 | 비고 |
|---|---|---|
| 폼 제출 (`POST /findResult`) | ✅ 동작 | `th:action="@{/findResult}"` → CSRF 정상 |
| 이름 + 전화번호로 아이디 조회 | ✅ 동작 | `UserMapper.findId(name, phone)` → DB `mobile` 칼럼 조회 |
| 결과 화면 아이디 표시 | ✅ 동작 | `findResult.html`에서 `isFindId=true` 분기 |
| **페이지 내 링크 (Thymeleaf 미적용)** | ❌ 고장 | `href="findId"` 상대 경로 → 컨텍스트 변경 시 깨짐 |
| **전화번호 포맷 입력 제한 없음** | ❌ 미구현 | 하이픈 입력 시 조회 실패 가능 |

### 4. 비밀번호 찾기 (`/findPass`)

| 항목 | 상태 | 비고 |
|---|---|---|
| 폼 제출 (`POST /findResult`) | ✅ 동작 | `th:action="@{/findResult}"` → CSRF 정상 |
| 아이디 + 이름 + 전화번호로 사용자 조회 | ✅ 동작 | `UserMapper.findPass(id, name, phone)` |
| **페이지 내 링크 (Thymeleaf 미적용)** | ❌ 고장 | `href="findId"` 상대 경로 |
| **전화번호 포맷 입력 제한 없음** | ❌ 미구현 | 하이픈 입력 시 조회 실패 가능 |

### 5. 찾기 결과 + 비밀번호 재설정 (`/findResult` → `POST /updatePass`)

| 항목 | 상태 | 비고 |
|---|---|---|
| 아이디 찾기 결과 표시 | ✅ 동작 | 복수 계정 지원 (`th:each`) |
| 결과 없음 안내 | ✅ 동작 | `th:unless="${user}"` 분기 |
| **비밀번호 재설정 폼 제출** | ❌ 고장 | `action="/updatePass"` → CSRF 없음 → 403 |
| **Font Awesome 아이콘** | ❌ 고장 | `fa-solid fa-lock/fa-check` → CDN 없음 → 아이콘 미표시 |
| **새 비밀번호 확인 JS 검증** | ❌ 미구현 | `passConfirm` 일치 확인 JS 없음 |
| **새 비밀번호 확인 서버 검증** | ❌ 미구현 | `UserPassUpdateRequest`에 `passConfirm` 없음 |

---

## 우선순위 수정 목록

### 즉시 수정 필요 (기능 동작 불가)

1. `joinForm.html` — `action="/join"` → `th:action="@{/join}"`
2. `SecurityConfig.java` — CSRF ignore 목록에 `/checkId` 추가
3. `findResult.html` — `action="/updatePass"` → `th:action="@{/updatePass}"`

### 다음 단계 수정 (기능 불완전)

4. `SecurityConfig.java` — `.rememberMe()` 설정 + `loginForm.html` 체크박스 `name="remember-me"` 추가
5. `UserController.java` — `join()` 파라미터에 `@Valid` 추가
6. `joinForm.html` — 아이디 안내 문구 DTO 기준에 맞게 수정 ("4~20자")
7. `UserSignUpRequest.java` — `passConfirm` 필드 + 서비스 검증 추가
8. `findResult.html` — Font Awesome → Bootstrap Icons 교체 + passConfirm 검증 JS 추가

### 낮은 우선순위

9. `findId.html`, `findPass.html` — 내부 링크 `th:href="@{...}"` 적용
10. `findId.html`, `findPass.html` — 전화번호 숫자 입력 제한 JS 추가
