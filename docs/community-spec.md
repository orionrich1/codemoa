# 커뮤니티 기능 기획서 (community-spec)

> 게시판 UX·네비게이션·보안 정책을 **한 파일**에서 관리한다.  
> **후순위·선택 과제**는 `plan.md` **Part D**에만 적어 두고, 본 문서에는 중복 `[ ]`를 두지 않는다.

---

## 개요

헤더 **커뮤니티** 드롭다운과 `/community/{category}` 기반 **목록·상세·작성·수정** 흐름의 수락 조건이다.  
초기 진단(수정 URL 불일치·삭제 confirm·비로그인 댓글·Sanitize 등)은 **현재 코드로 해소**된 상태다.

### 구현 요약

| 구분 | 비고 |
|------|------|
| 부록 「리치 HTML 정책」 | Summernote·저장 시 Sanitize·출력 |
| `CommunityHtmlSanitizer` | 저장 시 본문 정제 |

---

## 사용자 스토리

- **게시판 이용자**로서, 목록에서 글을 빠르게 찾고 모바일에서도 읽기 편하게 보고 싶다.
- **글 작성자**로서, 수정·삭제가 안 깨지고, 삭제 전에 한 번 더 확인할 수 있으면 좋겠다.
- **댓글 참여자**로서, 로그인 여부가 분명하고, 댓글 등록 후 페이지가 덜 거슬리면 좋겠다.
- **질문 포인트 사용자**로서, 채택·댓글 흐름이 한 화면에서 이해되면 좋겠다.

---

## 화면 목록 (현재 기준)

| URL 패턴 | 화면명 |
|----------|--------|
| `/community/free`, `/community/Java`, … | 게시판 목록 |
| `/community/{category}/{boardNo}` | 게시글 상세 |
| `/community/{category}/write` | 새 글 작성 |
| `/community/{category}/{boardNo}/edit` | 글 수정 |

---

## 수락 조건 (현재 스코프 — 전체 충족)

### 1. 네비게이션·정보 구조

- [x] 헤더 커뮤니티 메뉴와 게시판 카테고리 정의 일치 (C#·기타 포함)
- [x] 목록·상세·작성·수정 **브레드크럼**·현재 게시판 맥락
- [x] 수정·삭제·목록 링크 **`/community/...` 규칙**

### 2. 목록 (`boardList`)

- [x] 검색·정렬 툴바 상단
- [x] 정렬: 글번호·최신·댓글 수·추천(`recommend`, 조회수 대체)
- [x] 행 전체 클릭·키보드 진입
- [x] 0건 시 CTA
- [x] `table-responsive` 등 반응형

### 3. 상세 (`boardDetail`)

- [x] 수정 URL `/community/{segment}/{boardNo}/edit`
- [x] 삭제 **모달** 후 API
- [x] 비로그인: 댓글 폼 대신 로그인 안내
- [x] 피드백 토스트·모달 (alert 최소)
- [x] 댓글·채택 **중복 클릭 방지**

### 4. 작성·수정

- [x] 카테고리·URL·DB 값 정합 (`free`/`자유`, `기타`/`etc` 등)
- [x] Summernote 정책 → 부록
- [x] 제출 중 버튼 비활성화

### 5. 보안·권한

- [x] REST 권한·403 등
- [x] 본문 저장 시 Sanitize + 부록 정책

### 6. 접근성

- [x] `aria-label`·모달 키보드 (Bootstrap 기본)

---

## 시나리오

### 정상

1. Kotlin 게시판 → 목록 → 검색 → 상세 → 댓글 → 토스트·리로드 최소화  
2. 본인 글 수정 → `/community/Kotlin/{id}/edit` → 저장 → 상세

### 예외

1. 타인 글 `/edit` 직접 접근 → 상세 리다이렉트  
2. 비로그인 댓글 시도 → 안내·API 401/403  
3. 삭제 모달 취소 → 요청 없음

---

## 데이터·도메인 유의

- `category` URL↔DB 매핑은 서비스·`CommunityCategoryPaths`와 동일 패턴으로 확장
- `MAIN_CATEGORIES`·헤더·폼 **문자열 3-way 일치** 유지

---

## 관련 파일

| 역할 | 경로 |
|------|------|
| 뷰 | `BoardViewController.java` |
| REST | `CommunityBoardController.java`, `CommentController.java` |
| 템플릿 | `boardList.html`, `boardDetail.html`, `boardWrite.html`, `boardUpdate.html` |
| 헤더 | `fragments/header.html` |
| 스타일 | `static/css/community.css` |
| 서비스 | `CommunityBoardService.java` |
| Sanitize | `common/util/CommunityHtmlSanitizer.java` |

---

## 부록: 리치 HTML 정책 (Summernote · 게시글 본문)

### 목적

게시글 본문은 Summernote HTML 저장·표시이므로 저장 시 정제와 출력 리스크를 줄인다.

### 저장 (서버)

- 생성·수정 시 `CommunityHtmlSanitizer`로 정제 후 DB 저장.
- Jsoup `Safelist.relaxed()` + `figure`, `figcaption`, `hr`, `img` 일부 속성.
- `script`·이벤트 핸들러 속성 제거.

### 클라이언트 (에디터)

- 링크·이미지·동영상 툴바 사용 가능.
- 서버 업로드 API 연동 시 MIME·용량·경로·URL 화이트리스트는 별도 기획.

### 출력 (Thymeleaf)

- 상세 본문 `th:utext` — 저장 단계 정제 전제.
- 정책 변경 시 Sanitizer와 본 부록을 함께 수정.

### 관련 코드

- `com.codemoa.project.common.util.CommunityHtmlSanitizer`
- `CommunityBoardService` `create` / `update`
