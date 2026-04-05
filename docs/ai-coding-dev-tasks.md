# 개발 작업 지시서 — AI 코딩 문제 풀이 (`/problems`) — 2026-04-06

> 참고: `docs/auth-pages-test.md` 형식을 따른다. 입력 문서는 `docs/ai-coding-plan.md`, `docs/ai-coding-plan-test.md` 이다.  
> 본 문서는 **개발자가 착수·완료 체크**할 수 있도록 파일 경로·수정 방향을 구체화한다.

---

## 1. 기획서 대비 잔여·보완 작업 (필수)

| 구분 | 파일·위치 | 내용 | 심각도 | 수정 방향 |
|------|-----------|------|--------|-----------|
| 기능 2 (기획) | `problemList.html`, `problem.js`, `ProblemController` / `ProblemService` | 문제 **20개 초과 시 페이지네이션 또는 무한 스크롤** 미구현 (`ai-coding-plan-test.md` 잔여) | 🟡 Major | 서버: `page`, `size` 쿼리 파라미터 + 총 건수 반환. 클라이언트: 기존 AJAX 필터와 호환되게 페이지 전환 UI 추가. 또는 클라이언트에서 전체 목록 로드 후 가상 스크롤(문제 수가 매우 많아질 때는 서버 페이징 권장) |
| 예외 시나리오 | `problemResult.html`, `problem.js`, `ProblemRestController` / `ProblemService` | 기획서: AI 실패 시 **이력 저장 보류**·30초 초과 안내 — 구현과 문서가 어긋나면 사용자 혼란 | 🟡 Major | AI 호출 실패/타임아웃 시 `problem_submissions` INSERT 생략 여부를 기획과 동일하게 맞추고, 화면에 "재시도" 버튼 또는 동일 답안 재제출 플로우 명시 |
| 문자열 3-way 일치 | `ProblemService.searchProblemList`, `problemList.html` 필터 value, DB `category` | 개발자 하네스: DB·폼·서비스 분기 문자열 **대소문자까지 동일** | 🟠 Minor | `JAVA` vs `Java`, `Javascript` vs `JavaScript` 등 전 구간 grep 후 단일 상수 또는 enum으로 정리 |
| 서비스 테스트 | `src/test/java/.../problem/` | 개발자 하네스: 신규 기능에 **Service 단위 테스트** | 🟠 Minor | 제출 저장, 하루 1회 포인트, 통계 집계, AI 실패 분기에 대한 최소 단위 테스트 추가 |

---

## 2. 추가 개선 방향 (플러스 — 권장)

아래는 기획서에 없으나 **사용자 편의·운영·품질**을 위해 권장하는 작업이다.

| 구분 | 파일·위치 | 내용 | 심각도 | 수정 방향 |
|------|-----------|------|--------|-----------|
| 제출 UX | `problemDetail.html`, `problem.js` | 제출 버튼 **중복 클릭**으로 이중 제출·이중 API 호출 가능 | 🟡 Major | 제출 직후 버튼 `disabled` + 스피너(디자이너 하네스: 중복 클릭 방지). 응답 후에만 해제 또는 결과 페이지로 이탈 |
| AI 비용·남용 | `ProblemRestController`, `AiSupportService` | 인라인 질문·채점 API **IP/사용자당 rate limit** 없으면 비용 폭주 | 🟡 Major | Spring 기반 제한 또는 리버스 프록시 레벨에서 `/api/problems/**` 호출 빈도 제한 검토. 관리자는 완화 |
| 에러 메시지 | `GlobalExceptionHandler`, 프론트 fetch | Gemini/API 키 만료·쿼터 초과 시 사용자에게 **기술 용어 그대로** 노출 방지 | 🟠 Minor | 사용자용 문구: "일시적으로 AI 응답을 받을 수 없습니다" + 로그에는 상세 스택 |
| `aiAnswer.html` | 독립 AI 질문 페이지 | 문제 컨텍스트 없이 질문 → 답변 품질 저하 | 🟢 Low | 상단에 "문제 풀기 화면의 AI 질문은 해당 문제 맥락이 자동 포함됩니다" 안내 + `/problems/` 링크 CTA |
| 내 이력·통계 | `myHistory.html`, `stats.html` | 이력 많을 때 **전체 로드** 시 성능 저하 | 🟢 Low | 서버 페이징 또는 "더 보기" 로딩. 통계는 집계 쿼리 1~2회로 유지 |
| 접근성 | `problemDetail.html`, `problemResult.html` | 모달·탭에 `aria-*`, 아이콘 버튼에 `aria-label` | 🟢 Low | 디자이너 하네스 접근성 규칙에 맞춤 |
| 문서 동기화 | `docs/ai-coding-plan-test.md` | 페이지네이션 완료 후 **잔여 미달성** 행 제거 | 🟢 Low | 테스터가 완료 시 갱신 (또는 개발 완료 시 PR에서 함께 수정 합의) |

---

## 3. 기능별 상세 체크리스트 (개발자 자가 점검)

### 3.1 문제 목록 (`/problems/`)

| 항목 | 기대 동작 | 완료 |
|------|-----------|------|
| 언어·난이도 필터 (Kotlin 포함) | AJAX로 목록 갱신, 배지 유지 | ☐ |
| 키워드 검색 | 클라이언트 필터 또는 서버 검색과 일관 | ☐ |
| 로그인 시 진행 요약 | "전체 n개 중 m개…" 문구·수치 정확 | ☐ |
| 풀이 상태 배지 | 최고 점수 표기, 필터 후에도 동기화 | ☐ |
| **페이지네이션 / 무한 스크롤** | 문제 20개 초과 시에도 탐색 가능 | ☐ |
| 필터 결과 0건 | "필터 초기화" 안내 (기획 예외) | ☐ |

### 3.2 문제 풀기 (`/problems/problemDetail`)

| 항목 | 기대 동작 | 완료 |
|------|-----------|------|
| Tab → 4칸 들여쓰기 | 포커스 이탈 없음 | ☐ |
| 줄 번호 | textarea와 스크롤 동기화 | ☐ |
| 언어 드롭다운 | placeholder 문구 연동 | ☐ |
| 뷰포트 레이아웃 | 패널 내부 스크롤 | ☐ |
| 모바일 문제 패널 | 접기/펼치기 | ☐ |
| 인라인 AI 질문 | 모달/패널, 일 5회, 문제 맥락 포함 | ☐ |
| 비로그인 제출 | 차단 + 로그인 유도 | ☐ |
| 제출 중복 클릭 방지 | 버튼 비활성 + 로딩 표시 | ☐ |

### 3.3 채점 결과 (`/problems/problemResult`)

| 항목 | 기대 동작 | 완료 |
|------|-----------|------|
| 점수·등급 강조 | 100점 만점·등급 문구 | ☐ |
| 탭/아코디언 | 문제·제출·피드백 구분 | ☐ |
| 모범 답안 | 기본 숨김·토글 | ☐ |
| 피드백 내 코드 블록 | 가독 가능한 렌더링 | ☐ |
| 포인트 메시지 | 구간별 지급·당일 중복 안내 | ☐ |
| 하단 CTA | 다시 풀기·목록·이력·통계 | ☐ |
| AI 실패 | 기획서와 동일한 저장 정책 + 안내 문구 | ☐ |

### 3.4 내 이력·통계 (`/problems/myHistory`, `/problems/stats`)

| 항목 | 기대 동작 | 완료 |
|------|-----------|------|
| 비로그인 | 로그인 리다이렉트 | ☐ |
| 이력 정렬·필터 | 최신순·점수 필터 | ☐ |
| 빈 상태 | CTA 포함 | ☐ |
| 통계 카드·차트 | 기획 지표와 일치 | ☐ |
| (권장) 대량 이력 | 페이징 또는 더보기 | ☐ |

### 3.5 DB·배포

| 항목 | 기대 동작 | 완료 |
|------|-----------|------|
| `problem_submissions` | 운영 DB에 테이블 존재 (`CreateSql.sql` 등) | ☐ |
| 마이그레이션 문서 | 신규 환경 세팅 시 실행 순서 명시 | ☐ |

---

## 4. 우선순위 작업 목록 (실행 순서)

### 4.1 즉시 (기획 미달·사용자 혼란 방지)

1. **페이지네이션 또는 무한 스크롤** 구현 및 목록 UX와 통합 (`기능 2` 잔여).
2. **AI 실패·타임아웃** 시 이력/포인트 처리 방침을 기획서와 코드로 일치.

### 4.2 다음 단계 (품질·운영)

3. **제출 버튼 중복 클릭 방지** + 로딩 표시.
4. **카테고리·난이도 문자열** 3-way 일치 점검 및 정리.
5. **Problem 도메인 Service 단위 테스트** 보강.

### 4.3 권장 (플러스)

6. AI/API 호출 **rate limit** 또는 인프라 차단 정책.
7. `aiAnswer.html` **문맥 안내** + 문제 풀기 유도 CTA.
8. 이력·통계 **페이징**(데이터 증가 대비).

---

## 5. 관련 파일 빠른 참조

| 역할 | 경로 |
|------|------|
| 목록·상세·결과·이력·통계 템플릿 | `src/main/resources/templates/views/problem/*.html` |
| 스크립트 | `src/main/resources/static/js/problem.js` |
| 스타일 | `src/main/resources/static/css/problem.css` |
| 뷰 컨트롤러 | `.../problem/controller/ProblemController.java` |
| REST | `.../problem/controller/ProblemRestController.java` |
| 서비스 | `ProblemService.java`, `AiSupportService.java`, 제출·포인트 관련 서비스 |
| 매퍼·XML | `.../problem/mapper/*`, `src/main/resources/mappers/problem/*` |
| 기획 원문 | `docs/ai-coding-plan.md` |
| QA 완료 현황 | `docs/ai-coding-plan-test.md` |

---

작업 완료 후 서버 기동으로 `/problems` 전 구간을 수동 확인하고, 변경 범위에 맞게 `docs/ai-coding-plan-test.md`를 테스터와 동기화한다.
