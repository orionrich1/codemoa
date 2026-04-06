# IT 정보 기능 기획서 (information-spec)

> 강좌·도서·공모전 **허브·목록·상세·권한·경로**는 현재 스코프에서 반영됨.  
> **후순위**(템플릿 통합·검색 타입 UI·스크랩 등)는 `plan.md` **Part D**만 본다.

---

## 사용자 스토리

- **학습자**로서, 강좌·도서·공모전을 **한 곳에서 진입**하고 유형별로 빠르게 넘어가고 싶다.
- **일정 관리 사용자**로서, 공모전은 **마감 임박**을 한눈에 보고 싶다.
- **콘텐츠 기여자**로서, 등록·수정은 **권한 있는 사람만** 노출되고 링크·썸네일이 깨지지 않았으면 좋겠다.
- **모바일 사용자**로서, 슬라이더뿐 아니라 **스캔 가능한 목록**을 원한다. *(그리드 토글 등 추가 UX → Part D)*

---

## 화면 목록 (현재 기준)

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

---

## 수락 조건 (현재 스코프 — 전체 충족)

### 기능 1: 허브

- [x] `GET /information` 카드·바로가기 — `InformationController`, `informationHub.html`
- [x] 공모전 마감 14일 이내 건수·미리보기 — `InformationService`
- [x] 헤더 **IT 정보 허브** + 유형별 링크 — `header.html`

### 기능 2: 목록 템플릿·프론트

- [x] 정렬 **단일 `<select>` + `th:selected`**
- *(목록 3파일 통합·그리드 토글 → Part D)*

### 기능 3: URL·이미지·외부 링크

- [x] 내부 링크 `th:href="@{/information/...}"`
- [x] 썸네일 `@{/files/information/...}`
- [x] 외부 링크 `target="_blank"` + `rel="noopener noreferrer"`

### 기능 4: 권한

- [x] 등록 버튼 `hasRole('ADMIN')` — 목록 템플릿

### 기능 5: 공모전 목록

- [x] D-day·마감 뱃지
- [x] 필터: 전체 / 진행 중 / 마감 임박(14일) / 마감됨
- [x] 마감 건 시각 구분

### 기능 6: 상세

- [x] 같은 유형 추천 블록
- [x] 공모전 접수·진행 기간 메타
- [x] 브레드크럼 `IT 정보 > 유형 > …`

### 기능 7: 검색·필터

- [x] `keyword` 빈 값·문자열 `"null"` 서버 정규화 — `InformationService.normalizeQueryToken`
- *(도서 writer/content 검색 타입 UI → Part D)*

### 기능 8: 운영·품질

- [x] 등록·수정 폼 썸네일 비율·용량 안내
- *(일괄 마감·스크랩 → Part D)*

**출력·XSS**: 상세는 `th:text` 등 이스케이프 위주. 리치 HTML 도입 시 커뮤니티와 동일하게 Sanitize 정책 추가.

---

## 시나리오

### 정상

1. 허브 → 공모전 마감 임박 확인 → 상세 → 공식 사이트 새 탭  
2. 강좌 목록 정렬 → 상세 → 외부 강의 링크

### 예외

1. 썸네일 없음 → 플레이스홀더 + `alt`  
2. 외부 링크 깨짐 → (선택) 안내 문구

---

## 데이터·역할 (참고)

- 공모전 상태·필터는 서비스·매퍼와 연동 유지
- 스크랩 등 확장 시 Part D에서 착수 후 본 문서에 수락 조건 추가

| 기능 | 비로그인 | 일반 | 관리자 |
|------|----------|------|--------|
| 목록·상세 | ✅ | ✅ | ✅ |
| 등록·수정·삭제 | 버튼 미노출 | 정책에 따름 | ✅ |

---

## 관련 파일

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
