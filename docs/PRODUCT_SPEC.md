# CodeMoa 제품 명세 (단일 원본)

**역할**: 기능·화면·수락 조건·백로그를 **이 파일 한 곳**에서 관리한다.  
신규 `*-spec.md`를 만들지 않는다. 개발·디자인·검토 시 `@docs/PRODUCT_SPEC.md` 를 붙인다.

**고정 참고**: `HARNESS_GUIDE.md`, `DESIGN.md`  
**테스터 임시 기록**: `QA_SCRATCH.md` (명세와 섞지 않음)

---

## 백로그 (미착수·선택 과제)

> 동일 항목을 도메인 본문에 `[ ]`로 **중복 두지 않는다**. 착수 시 해당 도메인 섹션에 수락 조건을 적고, 여기서는 제거한다.

| 출처 | 항목 | 비고 |
|------|------|------|
| 커뮤니티 | 긴 본문·댓글 **앵커 링크**(목차·댓글 영역 이동) | 선택 UX |
| 커뮤니티 | 작성·수정 **임시 저장**(로컬 스토리지) 또는 나가기 전 확인 | 선택 |
| IT 정보 | 강좌·도서·공모전 목록 **3 HTML → fragments / 단일 템플릿 + `th:switch`** | 유지보수 |
| IT 정보 | 목록 **그리드·리스트 전환 토글** 또는 소형 화면 **세로 스택** 사용자 선택 | UX 선택 |
| IT 정보 | 도서 목록·AJAX에 **writer/content 등 검색 타입** 선택 UI (매퍼는 지원) | UI 정합 |
| IT 정보 | 허브 **통합 검색**(키워드 한 번에 세 영역) | 후순위 |
| IT 정보 | (선택) 공모전 **일괄 마감·상태 자동 전환 배치** | 운영 |
| IT 정보 | (선택) **스크랩/찜** | 확장 |
| 채용·팀 | **채용·팀 허브** (`/jobs`·`/career` 등) 카드 2개 | IA |
| 채용·팀 | 헤더 드롭다운 **허브 1링크 vs 2링크** 결정 | IA |
| 채용·팀 | 채용 목록 **지역·경력** 필터 (데이터·API 가능 시) | 데이터 의존 |
| 채용·팀 | 채용 **사이트 내 상세** `GET /employmentList/{id}` + 원문 보기 | 선택 |
| 채용·팀 | **메뉴·버튼 URL 매핑 표** 명세 1회 정리 | 문서 |
| 채용·팀 | 팀 모집 **필수 필드 검증** 서버·클라 메시지 완전 일치 | 품질 |
| 채용·팀 | 팀 모집 첨부 **용량·확장자 서버 검증**과 폼 안내 일치 | 보안·품질 |
| 통합 검색 | 유형별 결과 **탭 배지**(전체 n · 게시글 n …) | UX 선택 |
| 통합 검색 | **채용 공고**·**Q&A** 검색 대상 포함 여부 결정 후 `UNION` 또는 섹션 | 범위 |
| 통합 검색 | 결과 제목·요약 **검색어 하이라이트** (`<mark>`, HTML 이스케이프 유지) | UX |
| 메인 | 로그인 **개인화 카드** — 「마지막으로 시도한 문제 이어 풀기」(데이터·쿼리 연동) | 데이터 연동 |
| 마이페이지 | 프로젝트 다이어리 목록 **상태·기간** 필터 | 데이터·UI |
| 마이페이지 | **알림·쪽지** 도입 시 상단 배지 | 확장 |

_(고객센터 **알림**만 「고객센터」절 수락 조건의 `[ ]`로 본다. 위 표와 중복 없음.)_

---

## 전역 현황 및 원칙

### 목표

- 사용자가 탐색·핵심 기능 진입이 쉽고, 화면·동작이 일관되게 유지된다.
- 신규·수정 작업은 **DESIGN.md**와 **`PRODUCT_SPEC.md` 해당 도메인 섹션**을 우선 기준으로 한다.

### 기술·구조 (현재 기준)

- 서버: Spring Boot 3.x, Thymeleaf, MyBatis, Spring Security.
- 레이아웃: `templates/layouts/default_layout.html`, `templates/fragments/*`.
- 스타일: `static/css/site-ds.css`(토큰), `common.css`, 도메인별 CSS.
- 메인 포털: `domain/main/controller/MainController.java`, `templates/views/main/index.html`, `static/css/main.css`.
- 도메인 화면·컨트롤러: `templates/views/**`, `domain/**/controller/*`.

### 운영 원칙

- **URL·권한**: 신규 화면은 경로 규칙과 `SecurityConfig`·`@PreAuthorize`를 함께 갱신한다.
- **예외 UX**: Thymeleaf 뷰는 `MvcViewExceptionHandler`와 `templates/error/*.html`로 404/403/500 등 사용자 안내를 맞춘다.
- **Definition of Done**: 메인→탐색→상세→작성 여정이 끊기지 않음; 최소 375px·1280px에서 핵심 화면 레이아웃 유지; 인증·권한·오류 시 안내가 명확함.

---

## 메인 페이지 (`/`)

### 개요

비로그인/로그인 분기, 히어로, 통계, 오늘의 AI 문제, DS 칩 트레이, 커뮤니티 피드, 사이드(Q&A·랭킹·채팅·퀵링크), IT 정보 허브, 채용·팀, **게시판별 최신 탭**, 프로젝트 다이어리, FAQ 등 포털 구조는 **현재 템플릿·컨트롤러 기준으로 반영된 상태**로 본다.

### 반영된 핵심 (검토·QA 시 참고)

- 비로그인 히어로: 서브 카피, CTA(AI 문제·커뮤니티), **서비스 가치 3줄**, 가입 링크, 히어로 내 통합 검색 폼.
- 로그인: 닉네임·등급·포인트; **개인화 카드**(이번 주 포인트 0일 때 첫 문제 CTA, 미답변 Q&A 안내 등).
- 통계 바 및 **이번 주 활동자** 지표(데이터 있을 때만).
- 오늘의 AI 문제·칩 트레이 순서, 데모 안내 배너(`mainShowDemoNotice`).
- 게시판별 최신: **탭 + 리스트 + 더보기 + 모바일 가로 스크롤 탭**.
- Q&A 하이라이트: 미답변 뱃지, 경과 시간, 질문하기 CTA.
- 시각: `DESIGN.md`·`main.css` 기준 카드·섹션 구분·웜스톤 구역 등.

### 남은 과제

- 「마지막으로 시도한 문제 이어 풀기」 등 → **본 문서 「백로그」** 표 참조.

**구현 참조**: `MainController.java`, `MainService.java`, `MainMapper.xml`, `index.html`, `main.css`.

---

## AI 코딩 문제 (`/problems/**`)

### 개요

문제 목록(필터·Kotlin·클라이언트 제목 검색·진행 요약), 상세(코드 입력 UX, 모바일 문제 패널 접기, **인라인 AI 질문 모달·횟수 배지**), 제출·결과, **제출 이력 저장**, 내 풀이 이력·학습 통계, 포인트 지급 정책 연동 등 **핵심 스코프는 코드 기준으로 반영된 상태**로 본다.

### 남은·선택

- 목록 **서버 페이지네이션**·무한 스크롤 등 대량 데이터 대응은 필요 시 별도 인크리먼트로 본 문서에 수락 조건을 추가한다.
- 관리자 문제 작성 폼(미리보기·드롭다운 등) 및 결과 화면의 코드 블록 렌더링은 구현 상태를 열어보고 차이가 있으면 한 줄 수락 조건으로만 보강한다.

**구현 참조**: `ProblemController.java`, `ProblemService.java`, `ProblemRestController.java`, `SubmissionMapper.xml`, `templates/views/problem/*.html`, `problem.css`.

---

## 도메인별 참조 파일 (요약)

| 영역 | 경로 |
|------|------|
| 메인 | `domain/main/`, `templates/views/main/` |
| 검색 | `MainController` `GET /search`, `MainService`, `MainMapper.xml`, `search.html` |
| 문제 | `domain/problem/`, `templates/views/problem/` |
| 고객센터 | `domain/support/` (아래 「고객센터」절) |
| 공통 오류 | `MvcViewExceptionHandler.java`, `templates/error/` |

---

## 커뮤니티

게시판 UX·네비게이션·보안 정책. 후순위 과제는 **본 문서 「백로그」**만 본다.

### 개요

헤더 **커뮤니티** 드롭다운과 `/community/{category}` 기반 **목록·상세·작성·수정** 흐름의 수락 조건이다.

| 구분 | 비고 |
|------|------|
| 부록 「리치 HTML 정책」 | Summernote·저장 시 Sanitize·출력 |
| `CommunityHtmlSanitizer` | 저장 시 본문 정제 |

### 사용자 스토리

- **게시판 이용자**로서, 목록에서 글을 빠르게 찾고 모바일에서도 읽기 편하게 보고 싶다.
- **글 작성자**로서, 수정·삭제가 안 깨지고, 삭제 전에 한 번 더 확인할 수 있으면 좋겠다.
- **댓글 참여자**로서, 로그인 여부가 분명하고, 댓글 등록 후 페이지가 덜 거슬리면 좋겠다.
- **질문 포인트 사용자**로서, 채택·댓글 흐름이 한 화면에서 이해되면 좋겠다.

### 화면 목록

| URL 패턴 | 화면명 |
|----------|--------|
| `/community/free`, `/community/Java`, … | 게시판 목록 |
| `/community/{category}/{boardNo}` | 게시글 상세 |
| `/community/{category}/write` | 새 글 작성 |
| `/community/{category}/{boardNo}/edit` | 글 수정 |

### 수락 조건

#### 1. 네비게이션·정보 구조

- [x] 헤더 커뮤니티 메뉴와 게시판 카테고리 정의 일치 (C#·기타 포함)
- [x] 목록·상세·작성·수정 **브레드크럼**·현재 게시판 맥락
- [x] 수정·삭제·목록 링크 **`/community/...` 규칙**

#### 2. 목록 (`boardList`)

- [x] 검색·정렬 툴바 상단
- [x] 정렬: 글번호·최신·댓글 수·추천(`recommend`, 조회수 대체)
- [x] 행 전체 클릭·키보드 진입
- [x] 0건 시 CTA
- [x] `table-responsive` 등 반응형

#### 3. 상세 (`boardDetail`)

- [x] 수정 URL `/community/{segment}/{boardNo}/edit`
- [x] 삭제 **모달** 후 API
- [x] 비로그인: 댓글 폼 대신 로그인 안내
- [x] 피드백 토스트·모달 (alert 최소)
- [x] 댓글·채택 **중복 클릭 방지**

#### 4. 작성·수정

- [x] 카테고리·URL·DB 값 정합 (`free`/`자유`, `기타`/`etc` 등)
- [x] Summernote 정책 → 부록
- [x] 제출 중 버튼 비활성화

#### 5. 보안·권한

- [x] REST 권한·403 등
- [x] 본문 저장 시 Sanitize + 부록 정책

#### 6. 접근성

- [x] `aria-label`·모달 키보드 (Bootstrap 기본)

### 시나리오

**정상**: Kotlin 게시판 → 목록 → 검색 → 상세 → 댓글; 본인 글 수정 → `/community/Kotlin/{id}/edit` → 저장.

**예외**: 타인 글 `/edit` 직접 접근 → 상세 리다이렉트; 비로그인 댓글 시도 → 안내·API 401/403; 삭제 모달 취소 → 요청 없음.

### 데이터·도메인 유의

- `category` URL↔DB 매핑은 서비스·`CommunityCategoryPaths`와 동일 패턴으로 확장
- `MAIN_CATEGORIES`·헤더·폼 **문자열 3-way 일치** 유지

### 관련 파일

| 역할 | 경로 |
|------|------|
| 뷰 | `BoardViewController.java` |
| REST | `CommunityBoardController.java`, `CommentController.java` |
| 템플릿 | `boardList.html`, `boardDetail.html`, `boardWrite.html`, `boardUpdate.html` |
| 헤더 | `fragments/header.html` |
| 스타일 | `static/css/community.css` |
| 서비스 | `CommunityBoardService.java` |
| Sanitize | `common/util/CommunityHtmlSanitizer.java` |

### 부록: 리치 HTML 정책 (Summernote · 게시글 본문)

게시글 본문은 Summernote HTML 저장·표시이므로 저장 시 정제와 출력 리스크를 줄인다.

**저장 (서버)**: 생성·수정 시 `CommunityHtmlSanitizer`로 정제 후 DB 저장. Jsoup `Safelist.relaxed()` + `figure`, `figcaption`, `hr`, `img` 일부 속성. `script`·이벤트 핸들러 속성 제거.

**클라이언트**: 링크·이미지·동영상 툴바 사용 가능. 서버 업로드 API 연동 시 MIME·용량·경로·URL 화이트리스트는 별도 기획.

**출력**: 상세 본문 `th:utext` — 저장 단계 정제 전제. 정책 변경 시 Sanitizer와 본 부록을 함께 수정.

---

## IT 정보

강좌·도서·공모전 허브·목록·상세·권한·경로. 후순위는 **「백로그」** 표만 본다.

### 사용자 스토리

- **학습자**로서, 강좌·도서·공모전을 **한 곳에서 진입**하고 유형별로 빠르게 넘어가고 싶다.
- **일정 관리 사용자**로서, 공모전은 **마감 임박**을 한눈에 보고 싶다.
- **콘텐츠 기여자**로서, 등록·수정은 **권한 있는 사람만** 노출되고 링크·썸네일이 깨지지 않았으면 좋겠다.
- **모바일 사용자**로서, 슬라이더뿐 아니라 **스캔 가능한 목록**을 원한다.

### 화면 목록

| URL | 화면명 | 비고 |
|-----|--------|------|
| `/information` | IT 정보 허브 | 강좌·도서·공모전 카드, 마감 임박·미리보기 |
| `/information/lecture` | 강좌 목록 | |
| `/information/lectureDetail` | 강좌 상세 | |
| `/information/book` | 도서 목록 | |
| `/information/bookDetail` | 도서 상세 | |
| `/information/contest` | 공모전 목록 | 필터·D-day |
| `/information/contestDetail` | 공모전 상세 | |
| 각 `*Write` / `*Update` | 등록·수정 | |

### 수락 조건

#### 기능 1: 허브

- [x] `GET /information` 카드·바로가기 — `InformationController`, `informationHub.html`
- [x] 공모전 마감 14일 이내 건수·미리보기 — `InformationService`
- [x] 헤더 **IT 정보 허브** + 유형별 링크 — `header.html`

#### 기능 2: 목록 템플릿·프론트

- [x] 정렬 **단일 `<select>` + `th:selected`**

#### 기능 3: URL·이미지·외부 링크

- [x] 내부 링크 `th:href="@{/information/...}"`
- [x] 썸네일 `@{/files/information/...}`
- [x] 외부 링크 `target="_blank"` + `rel="noopener noreferrer"`

#### 기능 4: 권한

- [x] 등록 버튼 `hasRole('ADMIN')` — 목록 템플릿

#### 기능 5: 공모전 목록

- [x] D-day·마감 뱃지
- [x] 필터: 전체 / 진행 중 / 마감 임박(14일) / 마감됨
- [x] 마감 건 시각 구분

#### 기능 6: 상세

- [x] 같은 유형 추천 블록
- [x] 공모전 접수·진행 기간 메타
- [x] 브레드크럼 `IT 정보 > 유형 > …`

#### 기능 7: 검색·필터

- [x] `keyword` 빈 값·문자열 `"null"` 서버 정규화 — `InformationService.normalizeQueryToken`

#### 기능 8: 운영·품질

- [x] 등록·수정 폼 썸네일 비율·용량 안내

**출력·XSS**: 상세는 `th:text` 등 이스케이프 위주. 리치 HTML 도입 시 커뮤니티와 동일하게 Sanitize 정책 추가.

### 데이터·역할 (참고)

| 기능 | 비로그인 | 일반 | 관리자 |
|------|----------|------|--------|
| 목록·상세 | ✅ | ✅ | ✅ |
| 등록·수정·삭제 | 버튼 미노출 | 정책에 따름 | ✅ |

### 관련 파일

| 역할 | 경로 |
|------|------|
| 허브 | `InformationController.java`, `informationHub.html`, `information-hub.css` |
| 유형별 | `LectureController`, `BookController`, `ContestController`, `InformationAjaxController` |
| 서비스 | `InformationService.java` |
| 표시 | `InformationDisplayUtils.java` |
| 매퍼 | `mappers/information/InformationMapper.xml` |
| 목록 | `informationList.html`, `informationList2.html`, `informationList3.html` |
| 상세·폼 | `information*Detail.html`, `*WriteForm.html`, `*UpdateForm.html` |
| 헤더 | `fragments/header.html` |

---

## 채용·팀 모집

헤더 **채용·팀** — **채용 공고**·**팀원 모집**. 후순위는 **「백로그」** 표만 본다.

### 개요

- **채용**: 수집 데이터 목록·필터·검색·외부 원문 이동.
- **팀 모집**: UGC 목록·상세·등록·수정·삭제·첨부·공모전 URL·OG 미리보기.

### 화면 목록

#### 채용

| URL | 화면명 | 비고 |
|-----|--------|------|
| `GET /employmentList` | 목록 | `type`, `keyword`, `page` |
| `GET /employment/crawl` | 수동 갱신 | ADMIN, GET 폼 |
| `POST /employmentfetch` | 레거시 수집 | ADMIN |

#### 팀 모집

| URL | 화면명 | 비고 |
|-----|--------|------|
| `GET /teamRecruitList` | 목록 | `pageNum`, `type`, `keyword` |
| `GET /teamRecruitDetail` | 상세 | `recruitId` |
| `GET /addTeamRecruit` | 작성 폼 | 로그인 |
| `POST /recruit/write` | 등록 | 첨부 가능 |
| `GET /recruit/updateForm` | 수정 폼 | 작성자 |
| `POST /teamRecruitUpdate` | 수정 저장 | |
| `DELETE /recruit/{recruitId}` | 삭제 | 작성자, CSRF |
| `GET /TeamRecruitList` | 호환 | → `/teamRecruitList` 리다이렉트 |

### 수락 조건

#### 채용 목록

- [x] **정보 갱신** 버튼·`/employment/crawl`·`/employmentfetch` — **ADMIN**
- [x] 카드 클릭 → 새 탭 + `noopener noreferrer`
- [x] 최근 수집 기준일 안내 (`latestDatasetRegDt`)
- [x] 0건 시 필터 초기화 링크
- [x] 페이지네이션과 필터 결과 일치

#### 팀 모집 URL·네비

- [x] 주요 링크 `th:href="@{/...}"`
- [x] `/TeamRecruitList` → `/teamRecruitList`
- [x] 수정 후 상세 리다이렉트 `teamRecruitDetail?recruitId=…`

#### 팀 목록

- [x] 유형·상태·키워드 필터
- [x] 비로그인 **로그인 후 작성** CTA
- [x] 빈 목록 시 **첫 모집글** CTA (로그인)

#### 팀 상세

- [x] 섹션 카드 위계
- [x] 공모전 **http(s) URL**만 링크 + `noopener` + OG 프리뷰
- [x] 작성자만 수정·삭제; 삭제 **Bootstrap 모달** + `DELETE` + CSRF
- [x] 연락처 스팸·피싱 안내

#### 팀 작성·수정

- [x] 공모전 필드 라벨 **URL** 명시
- [x] `TEAM_JOIN` 인원·상태 처리

#### 보안

- [x] 채용 크롤 ADMIN
- [x] 팀 수정·삭제 작성자 검증 + 삭제 CSRF

### 역할·권한

| 기능 | 비로그인 | 일반 | 관리자 |
|------|----------|------|--------|
| 채용 목록·검색 | ✅ | ✅ | ✅ |
| 채용 갱신 | ❌ | ❌ | ✅ |
| 팀 조회 | ✅ | ✅ | ✅ |
| 팀 작성·수정·삭제 | 본인만 | ✅ | ✅ |

### 관련 파일

| 역할 | 경로 |
|------|------|
| 채용 | `EmploymentController.java`, `EmploymentService.java`, `EmploymentApiService.java`, `employmentList.html`, `employmentList.js` |
| 팀 | `TeamRecruitController.java`, `TeamRecruitService*.java`, `OgPreviewService.java`, `teamRecruit*.html` |
| 헤더 | `fragments/header.html` |

---

## 고객센터 (FAQ · Q&A)

헤더 **고객센터** 영역. 관리자 UI는 **`hasRole('ADMIN')`** (`sec:authorize`·`@PreAuthorize`) 로 통일.

### 사용자 스토리

- **일반 사용자**로서, 로그인 전에도 FAQ로 해결할 수 있는지 먼저 보고, 안 되면 Q&A로 질문하고 싶다.
- **로그인 사용자**로서, 질문 작성 버튼이 **내 상태에 맞게** 보이고, 제출 후 목록에서 답변 여부를 빠르게 알고 싶다.
- **관리자**로서, 미답변 질문만 모아 처리하고, FAQ는 폼으로 간단히 추가하고 싶다.
- **모바일 사용자**로서, Q&A 목록 테이블이 **`table-responsive`** 안에서 읽기 편했으면 좋겠다.

### 화면 목록

| URL | 화면명 | 비고 |
|-----|--------|------|
| `GET /support`, `GET /support/` | 고객센터 허브 | FAQ·Q&A 카드, 안내 문구 |
| `GET /support/faq` | FAQ 목록 | 아코디언, (관리자) 등록 폼 |
| `POST /support/faq` | FAQ 등록 | `ROLE_ADMIN`, `@Valid` `FaqCreateRequest` |
| `GET /support/qna` | Q&A 목록 | `page`, `type`, `keyword`, **`answer`** (`all`·`waiting`·`answered`) |
| `GET /support/qna/{qnaId}` | Q&A 상세 | 답변 목록, (관리자·미완료 시만) 답변 폼 |
| `GET /support/qna/write` | Q&A 작성 폼 | 인증 필요 |
| `POST /support/qna/write` | Q&A 등록 | 인증 필요, `@Valid` `QnaCreateRequest` |
| `GET /support/qna/{qnaId}/edit` | Q&A 수정 폼 | 작성자·**답변 전**만 |
| `POST /support/qna/{qnaId}/edit` | Q&A 수정 저장 | 동일 |
| `POST /support/qna/{qnaId}/delete` | Q&A 삭제 | 작성자·**답변 전**만, 모달·CSRF |
| `POST /support/qna/reply` | 답변 등록 | `ROLE_ADMIN`, `@Valid` `QnaReplyRequest` |

### 수락 조건

#### 기능 1: 고객센터 허브

- [x] `GET /support` 에서 FAQ·Q&A로 이동하는 카드
- [x] 안내 문구: FAQ 우선 → Q&A
- [x] 헤더 드롭다운에 **고객센터 홈** 추가

#### 기능 2: 권한·UI 일관성

- [x] 관리자 전용 UI는 **`sec:authorize="hasRole('ADMIN')"`** , 컨트롤러 **`@PreAuthorize("hasRole('ADMIN')")`**
- [x] 등급명(`getGrade()…`) 분기 **제거**

#### 기능 3: Q&A 목록

- [x] **질문하기** 는 로그인 시만; 비로그인 **로그인 CTA**
- [x] 필터 **전체 / 답변대기 / 답변완료** — **`answer`**: `all`·`waiting`·`answered` (`SupportMapper.xml` 과 동일)
- [x] 페이지네이션에 `type`·`keyword`·`answer` 유지
- [x] **`table-responsive`** 래퍼

#### 기능 4: Q&A 상세

- [x] **답변 완료** 시 관리자 답변 폼 **미노출**; 서버에서도 이미 완료 시 답변 등록 거절
- [x] 작성자 **수정·삭제** — **답변 등록 전**(`is_answered = false`)만; 본인·서버 검증·삭제는 확인 모달·`POST`+CSRF
- [x] 브레드크럼·목록으로

#### 기능 5: Q&A 작성

- [x] `QnaCreateRequest` **Bean Validation** + `@Valid`
- [x] 제출 시 버튼 **비활성화**

#### 기능 6: FAQ

- [x] FAQ **카테고리** 분류 — `FaqCategory` 라벨(일반·계정·문제·코딩·결제·환불·기타)과 DB `faq.category`·등록 폼 `<select>` **3-way 일치**; 목록은 카테고리별 섹션·아코디언
- [x] 등록 폼 **글자 수 안내**·`maxlength` (질문 500 / 답변 4000)
- [x] 답변 **줄바꿈** — `ds-faq-answer` (`white-space: pre-wrap`)

#### 기능 7: 운영·알림 (선택)

- [ ] 신규 Q&A·답변 **알림** — 미구현

### 데이터·검증 유의

- Q&A 검색 `type`: `title` / `writer` / `content` — 폼·서비스·MyBatis **동일 문자열** 유지
- 답변 상태 필터: **`answer`** — `all` | `waiting` | `answered` (XML `qnaAnswerFilter` 와 일치)
- FAQ 카테고리: `FaqCategory`·`faq.category`·폼 value **동일 문자열** (한글 라벨 그대로 저장)
- **기존 DB**에 `category` 컬럼이 없으면 `static/sql/migration_faq_add_category.sql` 실행

### 역할·권한

| 기능 | 비로그인 | 일반 회원 | 관리자 |
|------|----------|-----------|--------|
| FAQ 조회 | ✅ | ✅ | ✅ |
| FAQ 등록 | ❌ | ❌ | ✅ |
| Q&A 목록·상세 조회 | ✅ | ✅ | ✅ |
| Q&A 질문 작성 | ❌ | ✅ | ✅ |
| Q&A 답변 | ❌ | ❌ | ✅ |

### 관련 파일

| 역할 | 경로 |
|------|------|
| 컨트롤러 | `domain/support/controller/SupportController.java` |
| 서비스 | `domain/support/service/SupportService.java` |
| DTO | `FaqCreateRequest`, `QnaCreateRequest`, `QnaReplyRequest` |
| 매퍼 XML | `mappers/support/SupportMapper.xml` |
| 템플릿 | `support-hub.html`, `faq-list.html`, `qna-list.html`, `qna-detail.html`, `qna-form.html`, `qna-edit.html` |
| 헤더 | `templates/fragments/header.html` |

---

## 통합 검색·마이페이지

메인 **통합 검색**(`/search`)과 **마이페이지**(`/my-pages/`).

### 개요

- **통합 검색**: 키워드로 커뮤니티·IT정보·팀 모집 등을 한 번에 조회하고, 정렬·유형 필터를 적용한다.
- **마이페이지**: 계정 요약, 정보 수정·탈퇴, SNS 연동 해제, **프로젝트 다이어리** 목록·검색·CRUD 진입.

### 1. 통합 검색 (`/search`)

#### 현재 구조에서 드러난 이슈 (참고)

| 영역 | 내용 |
|------|------|
| 유형 필터·URL | 커뮤니티 경로는 **`/community/{category}/{boardNo}`** 와 정합 필요 |
| 데이터 증가 | 전체 결과 한 번에 조회 시 부담 — 상한·페이지네이션 정책 |
| 범위 | 채용·Q&A 미포함 시 사용자 기대와 차이 가능 |

#### 수락 조건

**필수에 가까운 수정**

- [x] 커뮤니티 게시글 검색 결과 URL을 **실제 상세 경로**와 동일하게 생성
- [x] `MainService`의 `community` 필터 조건도 **`/community/`** 기준
- [x] 도서 `UNION` 컬럼 alias 정합 (`createAt` vs `createdAt`)

**탐색·성능**

- [x] 서버 측 상한(예: `LIMIT 200`) + 정렬 후 안전 상한
- (선택) 탭 배지·채용/Q&A 검색 포함·`<mark>` 하이라이트 → **위 「백로그」표 「통합 검색」행**

**UX**

- [x] 빈 검색어: `required` + 서버 거절 또는 안내
- [x] 검색 결과 없을 때 **CTA**(커뮤니티, IT정보)

### 2. 마이페이지 (`/my-pages/`)

#### 수락 조건

**URL·보안·일관성**

- [x] 링크·폼 액션 **`th:href` / `th:action` + `@{/my-pages/...}`** 통일
- [x] 프로젝트 작성 버튼 `th:href="@{/my-pages/projectForm/0}"` 등

**UX·디자인**

- [x] 임시 CSS 주석 제거 및 **`site-ds.css`** 기준 정리
- [x] 계정 정보: `table-responsive` 또는 카드형 요약
- [x] 탈퇴·SNS 해제·프로젝트 삭제: **모달 확인**

**허브화**

- [x] **활동 요약** 영역 (AI 문제·Q&A·프로젝트 등)
- [x] **빠른 링크**: `/problems/myHistory`, `/problems/stats`, `/support/qna`, `/ranking` 등
- (선택) **알림·쪽지** 배지 → **「백로그」표 「마이페이지」행**

**프로젝트**

- (선택) 프로젝트 검색 **상태·기간** 필터 → **「백로그」표 「마이페이지」행**
- [x] 빈 목록·검색 0건 시 **작성 CTA**
- [x] `project.js` 삭제·수정 모달·접근성 점검

**서버·검증**

- [x] 정보 수정 `@Valid` 및 오류 시 폼 재표시
- [x] AJAX **CSRF** 헤더 일관성

### 사용자 스토리 (요약)

- **검색 사용자**로서, 통합 검색에서 게시글을 눌렀을 때 **실제 글 상세**로 가고 싶다.
- **검색 사용자**로서, 결과가 많을 때 **빠르게 로드**되기를 원한다.
- **마이페이지 사용자**로서, 한 화면에서 **계정·학습·활동**으로 이어지는 링크를 보고 싶다.
- **모바일 사용자**로서, 마이페이지 표가 **가로로 깨지지 않게** 보고 싶다.

### 관련 파일

| 구분 | 경로 |
|------|------|
| 검색 뷰 | `templates/views/main/search.html`, `static/css/search.css` |
| 검색 서비스 | `domain/main/service/MainService.java`, `MainController.java` |
| 검색 SQL | `resources/mappers/main/MainMapper.xml` |
| 마이페이지 뷰 | `templates/views/user/mypage/myPageMain.html`, `updateForm.html` |
| 마이페이지 컨트롤러 | `domain/user/controller/MyPageController.java`, `MyPageRestController.java` |
| 마이페이지 스크립트 | `static/js/mypage/project.js`, `mypage.js` |

---

## 부록: 장기 보관 문서 (명세 아님)

- `site-renewal-full-test-report.md` — 리뉴얼 후 점검·E2E 체크리스트 기록. **수락 조건의 원본은 본 `PRODUCT_SPEC.md`** 이다.
