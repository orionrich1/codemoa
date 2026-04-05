# CodeMoa 디자인/기능 개선 PRD

## 1) 목표
- 사용자 관점에서 "탐색이 쉽고", "핵심 기능 진입이 빠르며", "화면/동작이 일관된" 서비스로 개선한다.
- 운영 관점에서 화면 템플릿, 스타일, 라우팅, 예외 처리 기준을 정리해 유지보수 비용을 낮춘다.
- 이번 문서는 **구현 전 기획/설계 기준**이며, 우선순위 높은 작업부터 순차 진행한다.

## 2) 현재 구조 요약 (확인 기준)
- 서버: Spring Boot + Thymeleaf + MyBatis
- 화면 공통: `src/main/resources/templates/layouts/default_layout.html`, `src/main/resources/templates/fragments/*`
- 스타일 공통: `src/main/resources/static/css/common.css`, `src/main/resources/static/css/main.css`
- 메인 진입: `src/main/java/com/codemoa/project/domain/main/controller/MainController.java`, `src/main/resources/templates/views/main/index.html`
- 도메인 화면: `src/main/resources/templates/views/**`
- 도메인 컨트롤러: `src/main/java/com/codemoa/project/domain/**/controller/*Controller.java`

## 3) 핵심 문제 정의
- 네비게이션/랜딩/검색의 UX는 존재하지만, 도메인별 진입 정책과 화면 품질이 일정하지 않다.
- URL/엔드포인트 네이밍 규칙이 일부 불일치(대소문자 혼용, 복수 경로 공존)하여 사용자/개발자 혼란 가능성이 있다.
- 예외 처리와 에러 UX가 API 중심으로 구성되어 웹 화면 사용자 경험(에러 페이지, 안내 문구)이 약하다.
- 공통 디자인 토큰은 잘 정리되어 있으나, 개별 페이지 인라인 스타일/페이지별 편차를 줄일 필요가 있다.
- 운영/배포 관점에서 빌드 산출물(`bin/...`)과 소스(`src/...`) 동시 수정 위험이 있어 작업 기준 정리가 필요하다.

## 4) 우선순위 로드맵

## P0 (긴급, 이번 사이클 최우선)
### A. 정보구조(IA)와 글로벌 네비게이션 정비
- 목적: 사용자가 3클릭 이내로 원하는 기능에 도달하도록 메뉴 구조 단순화
- 수정 대상
  - `src/main/resources/templates/fragments/header.html`
  - `src/main/resources/templates/fragments/footer.html`
  - `src/main/resources/templates/layouts/default_layout.html`
  - `src/main/resources/static/css/common.css`
- 산출물
  - 확정 메뉴 트리(1-depth/2-depth)
  - 공통 헤더/푸터 컴포넌트 개선안
  - 모바일 우선 네비게이션 동작 정의(접기/펼치기/검색 진입)

### B. 메인/검색 경험 개선 (전환율 개선 핵심)
- 목적: 메인에서 핵심 기능 유입률 상승, 검색 결과 가독성/정확도 개선
- 수정 대상
  - `src/main/resources/templates/views/main/index.html`
  - `src/main/resources/templates/views/main/search.html`
  - `src/main/java/com/codemoa/project/domain/main/controller/MainController.java`
  - `src/main/resources/static/css/main.css`
  - `src/main/resources/static/css/common.css`
- 산출물
  - 메인 섹션 우선순위 재정의(히어로, 커뮤니티 최신글, 추천 강의)
  - 검색 결과 카드/필터/빈결과 UX 개선안
  - 메인 노출 데이터 기준(정렬/개수/빈 데이터 처리)

### C. URL/라우팅 일관성 정책 수립
- 목적: 경로 체계를 통일해 사용자 인지와 개발 생산성 동시 개선
- 우선 점검 파일
  - `src/main/java/com/codemoa/project/domain/recruit/controller/TeamRecruitController.java`
  - `src/main/java/com/codemoa/project/domain/community/controller/BoardViewController.java`
  - `src/main/java/com/codemoa/project/domain/information/controller/InformationController.java`
  - `src/main/java/com/codemoa/project/domain/problem/controller/ProblemController.java`
- 산출물
  - URL 규칙 문서(소문자-하이픈, 리소스 기반, 목록/상세/작성/수정 표준)
  - 리다이렉트/하위호환 정책 초안(기존 링크 깨짐 방지)

## P1 (중요, P0 완료 직후)
### D. 도메인별 화면 품질 표준화
- 목적: 페이지마다 다른 레이아웃 밀도/폼/버튼/테이블 사용성을 통일
- 수정 대상(대표)
  - 커뮤니티:  
    - `src/main/resources/templates/views/community/boardList.html`
    - `src/main/resources/templates/views/community/boardDetail.html`
    - `src/main/resources/templates/views/community/boardWrite.html`
    - `src/main/resources/templates/views/community/boardUpdate.html`
  - 문제풀이:
    - `src/main/resources/templates/views/problem/problemList.html`
    - `src/main/resources/templates/views/problem/problemDetail.html`
    - `src/main/resources/templates/views/problem/problemWriteForm.html`
    - `src/main/resources/templates/views/problem/problemUpdateForm.html`
  - 정보/채용/팀:
    - `src/main/resources/templates/views/information/*.html`
    - `src/main/resources/templates/views/employment/employmentList.html`
    - `src/main/resources/templates/views/recruit/teamRecruitList.html`
    - `src/main/resources/templates/views/recruit/teamRecruitDetail.html`
  - 공통/페이지별 스타일:
    - `src/main/resources/static/css/common.css`
    - `src/main/resources/static/css/problem.css`
    - `src/main/resources/static/css/information/*.css`
    - `src/main/resources/static/css/mypage/*.css`
- 산출물
  - 공통 컴포넌트 사용 가이드(카드, 버튼, 폼, 테이블, 뱃지)
  - 페이지 템플릿 점검 체크리스트

### E. 기능 신뢰성/보안 UX 강화
- 목적: 권한/예외/실패 케이스에서 사용자 혼란 최소화
- 수정 대상
  - `src/main/java/com/codemoa/project/configurations/SecurityConfig.java`
  - `src/main/java/com/codemoa/project/domain/exception/GlobalExceptionHandler.java`
  - (신규 필요 가능) `src/main/resources/templates/error/*.html`
- 산출물
  - 인증 필요 페이지 접근 시 UX 규칙(로그인 유도/리턴 URL)
  - 공통 오류 응답 + 사용자용 오류 페이지 정책

## P2 (개선/확장, 안정화 단계)
### F. 마이페이지/관리자/채팅 고도화
- 수정 대상(대표)
  - `src/main/resources/templates/views/user/mypage/*.html`
  - `src/main/resources/templates/views/user/admin/*.html`
  - `src/main/resources/templates/views/chat/*.html`
  - `src/main/resources/static/js/mypage/*.js`
  - `src/main/resources/static/js/admin/users.js`
  - `src/main/java/com/codemoa/project/domain/user/controller/MyPageController.java`
  - `src/main/java/com/codemoa/project/domain/user/controller/AdminController.java`
  - `src/main/java/com/codemoa/project/domain/chat/controller/*.java`
- 산출물
  - 대시보드형 정보 배치 개선안
  - 관리자 운영 플로우 개선안(검색/필터/상세 액션)

### G. 운영 기준 정리 (품질/배포)
- 목적: 소스/산출물 혼선 방지, 회귀 리스크 축소
- 수정/관리 대상
  - `README.md`
  - `.gitignore`
  - (검토) `bin/main/templates/views/main/index.html` 관리 정책
- 산출물
  - "소스 우선 수정 경로" 규칙 문서화
  - 배포 전 체크리스트(화면, 권한, 링크, 폼 검증)

## 5) 실행 순서 (권장)
- 1주차: P0-A, P0-B (디자인/탐색/메인 전환)
- 2주차: P0-C, P1-D 일부 (라우팅 정책 + 핵심 도메인 화면 표준화)
- 3주차: P1-E, P2-F 일부 (오류/보안 UX + 운영 기능)
- 4주차: P2-G 및 전체 회귀 점검

## 6) 완료 기준 (Definition of Done)
- 주요 사용자 여정(메인→탐색→상세→작성)이 끊김 없이 동작
- 모바일(최소 375px)과 데스크톱(1280px+)에서 핵심 화면 레이아웃 깨짐 없음
- URL/네이밍 규칙 문서와 실제 라우팅이 일치
- 인증/권한/예외 케이스에서 사용자 안내가 명확함
- 기획 문서 기준으로 QA 체크리스트 통과

## 7) 즉시 착수 추천 Top 5 파일
- `src/main/resources/templates/fragments/header.html`
- `src/main/resources/templates/views/main/index.html`
- `src/main/resources/templates/views/main/search.html`
- `src/main/resources/static/css/common.css`
- `src/main/java/com/codemoa/project/domain/recruit/controller/TeamRecruitController.java`

