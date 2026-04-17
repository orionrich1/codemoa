# 코드 품질·전체 기능 점검 보고서 — 사이트 리뉴얼 후 — 2026-04-06 (2차 갱신)

> **기능 수락 조건의 원본**은 `docs/PRODUCT_SPEC.md` 이다. 본 문서는 점검·E2E 체크리스트 **기록용**이다.

## 문서 목적

리뉴얼 이후 **비로그인 / 일반 회원 / 관리자** 관점에서 기능·권한·오류를 점검한 결과를 정리한다.  
2차 갱신에서는 **오류(404/403)·레이아웃 일관성**을 보강하는 코드 반영 + **Thymeleaf 주요 화면 렌더 스모크(WebMvcTest)** 를 추가했다. **실제 브라우저 E2E**는 여전히 Owner가 §7으로 수행해야 한다.

---

## 1. 자동화 테스트

| 항목 | 결과 |
|------|------|
| **실행 명령** | `.\gradlew.bat test` (프로젝트 루트) |
| **결과** | **BUILD SUCCESSFUL** (실패 0건) |
| **범위** | 단위·슬라이스·ArchUnit·**신규 WebMvc 스모크** 포함 `src/test` 전체 |

**해석**: 컴파일·아키텍처·등록된 자동 테스트 기준으로는 통과. **실 DB·OAuth 실계정·JS 상호작용**은 자동 테스트만으로는 완전 대체 불가.

### 1.1 Thymeleaf GET 렌더 스모크 (`@WebMvcTest`, 서비스 Mock)

다음 테스트는 **해당 URL이 200(또는 정책상 리다이렉트)으로 뷰 이름까지 해석되는지** 검증한다. **대부분** 보안 필터는 **`addFilters = false`** 로 분리했으므로, 그 클래스들에서는 **302 로그인·역할별 동작이 스모크에 포함되지 않는다**. **`ProblemControllerWebMvcTest`** 만 `PermitAllSecurityMvcTestConfig`(전 경로 permitAll·CSRF 비활성)로 필터를 올려 `user(...)` → `SecurityContextHolder` 동기화 후 **문제 작성/수정 GET의 비로그인 302·일반 회원 403**을 검증한다. 그 외 역할별 노출은 §7에서 확인.

| 테스트 클래스 | 검증 URL·뷰 (요약) |
|---------------|-------------------|
| `MainControllerWebMvcTest` | `GET /` → `views/main/index`, `GET /search` → `views/main/search` |
| `BoardViewControllerWebMvcTest` | `GET /community/free` → `views/community/boardList` + 서비스 `IllegalArgumentException` 시 **`error/404`·404** |
| `InformationControllerWebMvcTest` | `GET /information` → `views/information/informationHub` |
| `ProblemControllerWebMvcTest` | `GET /problems` → redirect `/problems/`, `GET /problems/`·`/problems/aiAnswer`; `problemWrite`·`problemUpdate` 비로그인→`/loginForm`, 일반→`error/403` |
| `UserControllerWebMvcTest` | `GET /loginForm`, `/joinForm`, `GET /login` → `/loginForm` |
| `SupportControllerWebMvcTest` | `GET /support/`, `/support/faq`, `/support/qna` |
| `EmploymentControllerWebMvcTest` | `GET /employmentList` |
| `TeamRecruitControllerWebMvcTest` | `GET /teamRecruitList` |

---

## 2. 본 세션에서 수행하지 않은 것 (Owner 수동 필수)

| 항목 | 이유 |
|------|------|
| 브라우저 실클릭·모바일 뷰포트 | 실행 중 서버·계정·데이터가 에이전트 환경에 없음 |
| 이메일·OAuth 실계정 흐름 | 외부 연동·자격 증명 필요 |
| 실제 고용24 크롤·외부 API 한도 | 운영 부하·정책 |

**권장**: 로컬 `bootRun` 후 **비로그인 → 일반 회원 → 관리자** 순으로 §7 체크리스트를 순차 실행하고, 이슈는 이슈 트래커에 URL·스크린샷·재현 절차를 남긴다.

---

## 3. Spring Security 요약 (`SecurityConfig`)

| 구분 | 경로·메서드 | 정책 |
|------|-------------|------|
| 관리자 | `/admin/**` | `ROLE_ADMIN` |
| 인증 필요 | `/my-pages/**`, `/ranking`, `/ranking/**` | authenticated |
| AI API | `POST /problems/apiRequest`, `/problems/apiQuestion` | authenticated |
| 채용 크롤 | `/employmentfetch`, `GET /employment/crawl` | `ROLE_ADMIN` |
| 커뮤니티 글쓰기·수정 화면 | `/community/*/write`, `/community/*/*/edit` | authenticated |
| 커뮤니티 API 변경 | `POST/PUT/DELETE` `/api/boards/**`, 댓글 POST, 채택 POST | authenticated |
| 그 외 | `anyRequest` | **permitAll** |

**CSRF**: `csrf().ignoringRequestMatchers`에 `/api/**`, `/information/**`, `/problems/apiRequest` 등이 포함됨. REST·AJAX는 주로 **세션 쿠키 + 별도 토큰(프론트)** 패턴을 전제로 할 수 있음. **상태 변경 POST가 permitAll 경로에만 막혀 있지 않은지** 수동으로 한 번 더 확인 권장.

---

## 4. 오류 페이지·MVC 예외 (2차 반영)

| 항목 | 내용 |
|------|------|
| **Thymeleaf `@Controller` + `IllegalArgumentException`** | `MvcViewExceptionHandler` → 뷰 **`error/404`**, HTTP **404** (기존 `GlobalExceptionHandler`는 `@RestController` 전용이라 API와 분리). |
| **`IllegalStateException`** | 뷰 **`error/403`**, HTTP **403**. |
| **템플릿** | `templates/error/404.html`, `403.html`, `500.html`, `4xx.html` — 모두 `layouts/default_layout` 적용(헤더·푸터·DS CSS 공통). |
| **Spring Boot `/error` 디스패치** | 동일 디렉터리 템플릿으로 **상태코드별 위스프레이블 대체**에 사용. |

**수동 확인 권장**: 존재하지 않는 경로(404), 권한 없는 URL(403), 의도적 서버 예외(500)를 브라우저에서 열어 **레이아웃 깨짐·문구**를 본다.

---

## 5. 코드 추적 기반 이슈 (잔여·참고)

| 파일·위치 | 내용 | 상태 |
|-----------|------|------|
| `header.html`·`problemList.html`·`problemDetail.html` | `hasRole('ROLE_ADMIN')` → **`hasRole('ADMIN')`로 통일** (2차 수정). | **조치됨** — 관리자 로그인 후 **유저 관리·문제 작성 버튼** 노출만 §7에서 재확인. |
| `ProblemController` | 문제 작성/수정 **GET/POST**·삭제 **GET** 모두 비로그인→`/loginForm`, 비관리자→`IllegalStateException`→403. | **조치됨** — §1.1 `ProblemControllerWebMvcTest` 스모크. |
| 정보·채용 UI | `@PreAuthorize`·`hasRole('ADMIN')` 패턴 | 양호 |

---

## 6. 통과·양호로 본 코드 포인트 (샘플)

- 커뮤니티: `boardDetail` 수정·삭제·댓글·비로그인 안내 `sec:authorize` 분기 존재.
- 채용: 갱신 버튼·크롤 폼 `hasRole('ADMIN')` + `SecurityConfig` 이중 제한.
- IT 정보 목록: 글쓰기 `hasRole('ADMIN')` + 컨트롤러 `PreAuthorize`.
- 팀 모집: 삭제 `DELETE` 시 작성자 검증 + 401/403 응답 코드 분기.
- 커뮤니티 REST: 변경 메서드 authenticated.

---

## 7. 수동 E2E 체크리스트 (비로그인 / 일반 / 관리자)

각 셀에서 **오류 페이지·500·콘솔 에러·깨진 레이아웃** 없이 기대 동작인지 확인한다.

### 7.1 공통·인증

| # | 시나리오 | 비로그인 | 일반 회원 | 관리자 |
|---|----------|----------|-----------|--------|
| A1 | `/` 메인 로딩, 헤더·푸터 | ☐ | ☐ | ☐ |
| A2 | `/search` 통합 검색 | ☐ | ☐ | ☐ |
| A3 | `/loginForm` 로그인 | ☐ | — | — |
| A4 | 회원가입 `/joinForm` | ☐ | — | — |
| A5 | 로그아웃 후 세션 제거 | — | ☐ | ☐ |
| A6 | **헤더 프로필 드롭다운에「유저 관리」** | 숨김 | 숨김 | **보여야 함** (`hasRole('ADMIN')` 수정 후 재확인) |

### 7.2 AI 코딩·문제

| # | 시나리오 | 비로그인 | 일반 | 관리자 |
|---|----------|----------|------|--------|
| P1 | `/problems/` 목록 | ☐ | ☐ | ☐ |
| P2 | `/problems/problemDetail` 열기 | ☐ | ☐ | ☐ |
| P3 | 제출 → 결과 페이지 | 로그인 유도 또는 정책대로 | ☐ | ☐ |
| P4 | `/problems/aiAnswer` | ☐ | ☐ | ☐ |
| P5 | `/problems/myHistory`, `/problems/stats` | 리다이렉트 로그인 | ☐ | ☐ |
| P6 | **문제 작성·수정·삭제 UI** | 숨김 또는 미노출 | 숨김(ADMIN만 서버 허용) | **보여야 함** |
| P7 | `/problems/apiRequest` (AI 채점) 비로그인 | 거부 | ☐ | ☐ |

### 7.3 커뮤니티

| # | 시나리오 | 비로그인 | 일반 | 관리자 |
|---|----------|----------|------|--------|
| C1 | `/community/free` 목록·검색·정렬·페이지네이션 | ☐ | ☐ | ☐ |
| C2 | 상세 `/community/free/{id}` | ☐ | ☐ | ☐ |
| C3 | 글쓰기 `/community/free/write` | 로그인 유도 | ☐ | ☐ |
| C4 | **본인 글** 수정·삭제 모달 | — | ☐ | ☐ |
| C5 | **타인 글** 수정 URL 직접 입력 | — | 리다이렉트/거부 | ☐ |
| C6 | 댓글 작성·토스트 | 로그인 안내 카드 | ☐ | ☐ |
| C7 | 질문글 채택 모달 | — | 작성자만 | 작성자만 |

### 7.4 IT 정보

| # | 시나리오 | 비로그인 | 일반 | 관리자 |
|---|----------|----------|------|--------|
| I1 | `/information` 허브 | ☐ | ☐ | ☐ |
| I2 | 강좌·도서·공모전 목록·상세 | ☐ | ☐ | ☐ |
| I3 | **글쓰기 버튼** | 숨김 | 숨김 | 노출 |
| I4 | 관리자만 등록·수정·삭제 폼 동작 | — | — | ☐ |
| I5 | 외부 링크 새 탭·보안 속성 | ☐ | ☐ | ☐ |

### 7.5 채용·팀

| # | 시나리오 | 비로그인 | 일반 | 관리자 |
|---|----------|----------|------|--------|
| E1 | `/employmentList` 필터·페이지·외부 링크 | ☐ | ☐ | ☐ |
| E2 | **정보 갱신** 버튼 | 숨김 | 숨김 | 노출·동작 |
| E3 | 비관리자가 `/employment/crawl` 직접 호출 | 403/로그인 | 동일 | ☐ |
| T1 | `/teamRecruitList`·상세 | ☐ | ☐ | ☐ |
| T2 | 모집글 작성·첨부 | 로그인 유도 | ☐ | ☐ |
| T3 | 본인 글 수정·삭제 모달 | — | ☐ | ☐ |
| T4 | 타인 글 삭제 API | — | 403 | 403 |

### 7.6 랭킹·마이페이지·고객센터·기타

| # | 시나리오 | 비로그인 | 일반 | 관리자 |
|---|----------|----------|------|--------|
| R1 | `/ranking` | 로그인 유도 | ☐ | ☐ |
| M1 | `/my-pages` 각 탭·체크리스트·다이어리 API | 로그인 유도 | ☐ | ☐ |
| S1 | `/support/faq` — 관리자만 편집 UI | ☐ | ☐ | ☐ |
| S2 | `/support/qna` 목록·상세·질문 작성 | ☐ | ☐ | ☐ |
| S3 | Q&A 관리자 답변 영역 | 숨김 | 숨김 | ☐ |
| AD1 | `/admin/users` 등 관리자 메뉴 | 거부 | 거부 | ☐ |
| CH1 | 채팅 `/chat/rooms` (등) | 정책대로 | ☐ | ☐ |

---

## 8. 결론 및 권장 조치

| 구분 | 내용 |
|------|------|
| **자동화** | `gradlew test` 전부 통과. **주요 공개 GET 화면**은 §1.1 스모크로 Thymeleaf 파싱·뷰 이름까지 검증. |
| **404/403/레이아웃** | `MvcViewExceptionHandler` + `templates/error/*.html` 로 **화면 예외 UX** 보강(2차). |
| **관리자 UI** | `hasRole('ADMIN')` 통일 **적용됨** — §7에서 관리자 계정으로 한 번 더 눈으로 확인. |
| **남은 리스크** | 다수 WebMvc 스모크는 **`addFilters = false`**; ProblemController만 최소 체인. CSRF 예외·넓은 `permitAll` — §7·침투 샘플링 권장. |
| **다음 단계** | §7 수동 E2E 완료 후 통과 판정 시, 테스터 하네스 정책에 따라 **본 md 유지/삭제**는 Owner와 합의. |

---

## 9. 메타

- **문서**: 초안(테스터 관점) + **2차 갱신**(개발 반영·스모크 테스트·오류 페이지).
- **권장 담당**: §7 → **Owner·QA** / 추가 하드닝 → **개발자**
- **파일 위치**: `docs/site-renewal-full-test-report.md`
