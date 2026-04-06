# 채용·팀 모집 기능 기획서 (employment-team-spec)

> 헤더 **채용·팀** — **채용 공고**·**팀원 모집**.  
> **후순위**(허브·사이트 내 채용 상세·지역 필터·검증 정합 등)는 `plan.md` **Part D**만 본다.

---

## 개요

- **채용**: 수집 데이터 목록·필터·검색·외부 원문 이동.
- **팀 모집**: UGC 목록·상세·등록·수정·삭제·첨부·공모전 URL·OG 미리보기.

---

## 화면 목록 (현재 기준)

### 채용

| URL | 화면명 | 비고 |
|-----|--------|------|
| `GET /employmentList` | 목록 | `type`, `keyword`, `page` |
| `GET /employment/crawl` | 수동 갱신 | ADMIN, GET 폼 |
| `POST /employmentfetch` | 레거시 수집 | ADMIN |

### 팀 모집

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

---

## 수락 조건 (현재 스코프 — 전체 충족)

### 채용 목록

- [x] **정보 갱신** 버튼·`/employment/crawl`·`/employmentfetch` — **ADMIN** (`employmentList.html`, `SecurityConfig`)
- [x] 카드 클릭 → 새 탭 + `noopener noreferrer` (`employmentList.js`)
- [x] 최근 수집 기준일 안내 (`latestDatasetRegDt`)
- [x] 0건 시 필터 초기화 링크
- [x] 페이지네이션과 필터 결과 일치
- *(지역·경력 필터, 사이트 내 상세 → Part D)*

### 팀 모집 URL·네비

- [x] 주요 링크 `th:href="@{/...}"`
- [x] `/TeamRecruitList` → `/teamRecruitList`
- [x] 수정 후 상세 리다이렉트 `teamRecruitDetail?recruitId=…`
- *(메뉴·URL 매핑 표 문서화 → Part D)*

### 팀 목록

- [x] 유형·상태·키워드 필터
- [x] 비로그인 **로그인 후 작성** CTA
- [x] 빈 목록 시 **첫 모집글** CTA (로그인)

### 팀 상세

- [x] 섹션 카드 위계
- [x] 공모전 **http(s) URL**만 링크 + `noopener` + OG 프리뷰
- [x] 작성자만 수정·삭제; 삭제 **Bootstrap 모달** + `DELETE` + CSRF
- [x] 연락처 스팸·피싱 안내

### 팀 작성·수정

- [x] 공모전 필드 라벨 **URL** 명시
- [x] `TEAM_JOIN` 인원·상태 처리
- *(서버·클라 검증 문구 완전 일치, 첨부 서버 검증 정합 → Part D)*

### 보안

- [x] 채용 크롤 ADMIN
- [x] 팀 수정·삭제 작성자 검증 + 삭제 CSRF

---

## 시나리오

### 정상

1. 채용: 필터 → 카드 → 외부 지원  
2. 팀: 작성 → 공모전 URL → 상세 미리보기 → 저장  
3. 본인 글 수정 → 저장 → 상세

### 예외

1. 비로그인·일반 사용자 갱신 시도 → 403  
2. 타인 수정 URL → 목록 리다이렉트·메시지  
3. 첨부 용량 초과 → 실패 메시지 (서버 규칙 정합은 Part D)

---

## 데이터·도메인 유의

- 채용 `type`·`region`·`url` — DB·표시·필터 **문자열 일치**
- 팀 `recruitType`·`status` 등 — 화면 표시명과 매핑 유지

---

## 역할·권한

| 기능 | 비로그인 | 일반 | 관리자 |
|------|----------|------|--------|
| 채용 목록·검색 | ✅ | ✅ | ✅ |
| 채용 갱신 | ❌ | ❌ | ✅ |
| 팀 조회 | ✅ | ✅ | ✅ |
| 팀 작성·수정·삭제 | 본인만 | ✅ | ✅ |

---

## 관련 파일

| 역할 | 경로 |
|------|------|
| 채용 | `EmploymentController.java`, `EmploymentService.java`, `EmploymentApiService.java`, `employmentList.html`, `employmentList.js` |
| 팀 | `TeamRecruitController.java`, `TeamRecruitService*.java`, `OgPreviewService.java`, `teamRecruit*.html` |
| 헤더 | `fragments/header.html` |
