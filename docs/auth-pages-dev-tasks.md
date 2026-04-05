# 개발 작업 지시서 — 인증 페이지 (로그인·회원가입·아이디/비밀번호 찾기) — 2026-04-06

> **입력 문서**: `docs/auth-pages-test.md` (테스터 품질 보고서).  
> 본 문서는 보고서의 **미해결 항목을 개발자가 그대로 착수**할 수 있도록 정리한 작업 지시서다.  
> (이전에 잘못 작성된 `docs/ai-coding-dev-tasks.md`는 AI 코딩 도메인용이며, **본 인증 작업과 무관**하다.)

---

## 1. 미해결 위반 → 수정 지시 (파일 단위)

| 우선순위 | 파일 | 문제 | 수정 방향 |
|----------|------|------|-----------|
| P0 | `joinForm.html` (약 18행) | `action="/join"` → CSRF 미주입 → **회원가입 403** | `th:action="@{/join}"` 로 변경 |
| P0 | `joinForm.html` (AJAX 중복 체크) + `SecurityConfig.java` | `POST /checkId` CSRF 없음 → **중복 체크 403** | **택1**: SecurityConfig CSRF ignore에 `/checkId` 등록 **또는** AJAX 요청에 `X-XSRF-TOKEN` 헤더 + 메타 태그/쿠키 연동 |
| P0 | `findResult.html` (약 33행) | `action="/updatePass"` → **비밀번호 재설정 403** | `th:action="@{/updatePass}"` 로 변경 |
| P1 | `SecurityConfig.java` + `loginForm.html` (38~42행) | Remember Me 미동작 | `.rememberMe()` 설정 + 체크박스 `name="remember-me"` (Spring Security 기본 파라미터명과 일치 확인) |
| P1 | `UserController.java` (join 메서드) | `@Valid` 없음 → DTO 검증 미적용 | `join(@Valid UserSignUpRequest request, ...)` |
| P1 | `GlobalExceptionHandler.java` | `@Valid` 실패 시 사용자 안내 없을 수 있음 | `MethodArgumentNotValidException` 처리 → 필드 오류 메시지 또는 공통 에러 페이지 |
| P1 | `joinForm.html` (아이디 안내) + `UserSignUpRequest.java` | HTML "6~12자" vs DTO `4~20자` 불일치 | **한쪽으로 통일** (권장: 안내 문구를 DTO 기준으로 수정하거나, 정책 합의 후 `@Size` 수정) |
| P2 | `UserSignUpRequest.java` + User 서비스 | `passConfirm` 없음 → 서버에서 비번 일치 검증 불가 | DTO에 `passConfirm` 추가 + 서비스에서 `pass.equals(passConfirm)` 검증, 불일치 시 예외 |
| P2 | `findResult.html` | Font Awesome 아이콘 → CDN 없어 **아이콘 미표시** | `bi bi-lock`, `bi bi-check-lg` 등 **Bootstrap Icons**로 교체 |
| P2 | `findResult.html` + `UserPassUpdateRequest.java` | 새 비밀번호 확인 JS·서버 검증 없음 | HTML 제출 전 JS 일치 검증 + DTO `passConfirm` + 서비스 검증 |
| P3 | `findId.html`, `findPass.html` | 내부 링크 상대 경로 | `th:href="@{/findId}"`, `th:href="@{/findPass}"` |
| P3 | `findId.html`, `findPass.html` | 전화번호 숫자만 허용 JS 없음 | `joinForm.html`의 `mobile` `input` 이벤트 패턴과 동일 적용 |

---

## 2. 기능별 완료 체크리스트 (자가 점검)

### 2.1 로그인 (`/loginForm`)

| 항목 | 완료 |
|------|------|
| 폼 로그인 + CSRF 정상 | ☐ |
| 로그인 실패 메시지 | ☐ |
| OAuth (Google/Kakao) | ☐ |
| **Remember Me** 동작 (체크 시 세션/토큰 유지) | ☐ |

### 2.2 회원가입 (`/joinForm`)

| 항목 | 완료 |
|------|------|
| `POST /join` 403 없음 | ☐ |
| `POST /checkId` 중복 체크 403 없음 | ☐ |
| `@Valid` + 서버 검증 반영 | ☐ |
| `passConfirm` 서버 검증 | ☐ |
| 아이디 길이 안내 ↔ DTO 일치 | ☐ |

### 2.3 아이디/비밀번호 찾기 (`/findId`, `/findPass`, `/findResult`)

| 항목 | 완료 |
|------|------|
| `POST /updatePass` CSRF 정상 | ☐ |
| 비번 재설정 passConfirm JS + 서버 | ☐ |
| 아이콘 Bootstrap Icons 표시 | ☐ |
| 탭 간 링크 `th:href` | ☐ |
| findId/findPass 전화번호 숫자 제한 | ☐ |

---

## 3. 추가 개선 (플러스 — 권장)

| 항목 | 방향 |
|------|------|
| 로그인 후 **원래 가려던 URL** 복귀 | Spring Security `saved request` / `continue` 파라미터 정책이 이미 있으면 문서화, 없으면 검토 |
| 찾기 API **남용 방지** | 동일 IP/세션 연속 실패 시 쿨다운 또는 캡차는 기획 합의 후 |
| 에러 메시지 | 검증 실패 시 필드별 메시지가 Thymeleaf에 노출되는지 확인 (`flash` 또는 `BindingResult`) |

---

## 4. 실행 순서 (권장)

1. **P0 세 줄**: `joinForm` th:action, `/checkId` CSRF 대응, `findResult` updatePass th:action  
2. **P1**: Remember Me, `@Valid`, GlobalExceptionHandler, 아이디 길이 문구 통일  
3. **P2**: passConfirm(가입·재설정), Font Awesome 제거, findResult JS  
4. **P3**: findId/findPass 링크·전화번호 JS  

완료 후 서버 기동으로 **회원가입 전 과정·비밀번호 재설정·로그인 유지**를 수동 검증하고, 테스터가 `docs/auth-pages-test.md`를 **미해결만 남기도록** 갱신한다.

---

## 5. 관련 파일 참조

| 역할 | 경로 (예시) |
|------|-------------|
| 로그인 | `templates/views/user/loginForm.html` |
| 회원가입 | `templates/views/user/joinForm.html` |
| 찾기 | `templates/views/user/findId.html`, `findPass.html`, `findResult.html` |
| 컨트롤러 | `domain/user/controller/UserController.java` (및 관련 컨트롤러) |
| 보안 | `configurations/SecurityConfig.java` |
| 예외 | `domain/exception/GlobalExceptionHandler.java` |
| DTO | `UserSignUpRequest.java`, `UserPassUpdateRequest.java` 등 |
