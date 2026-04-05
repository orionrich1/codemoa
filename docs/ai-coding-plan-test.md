# 코드 품질 검토 보고서 — AI 코딩 문제 풀이 — 2026-04-05 (v5, 최종)

---

## 미해결 위반 사항

없음 — 모든 위반 사항 해결 완료.

---

## 기획서 수락 조건 전체 달성 현황

| 기능 | 수락 조건 | 상태 |
|---|---|---|
| 기능 1 (풀이 이력 저장) | 제출 이력 DB 저장 | ✅ |
| 기능 1 | 비로그인 차단 | ✅ |
| 기능 2 (목록 개선) | 키워드 검색 (클라이언트) | ✅ |
| 기능 2 | Kotlin 필터 추가 | ✅ |
| 기능 2 | 풀이 상태 배지 (초기 로드 + AJAX 필터 모두) | ✅ |
| 기능 2 | 목록 상단 진행 요약 | ✅ |
| 기능 3 (코드 입력 환경) | Tab 키 들여쓰기 4칸 | ✅ |
| 기능 3 | 언어별 힌트 텍스트 변경 | ✅ |
| 기능 3 | 줄 번호 표시 | ✅ (CSS+JS, 외부 라이브러리 없음) |
| 기능 3 | 뷰포트 고정 레이아웃 | ✅ |
| 기능 3 | 모바일 접기/펼치기 | ✅ |
| 기능 4 (인라인 AI 질문) | 버튼 + 모달 + 5회 제한 | ✅ |
| 기능 5 (결과 페이지) | 점수·등급·탭·모범 답안·코드 블록 | ✅ |
| 기능 5 | "내 이력 보기" / "학습 통계" 버튼 | ✅ |
| 기능 6 (내 풀이 이력) | `/problems/myHistory` 페이지 | ✅ |
| 기능 7 (학습 통계) | `/problems/stats` 페이지 | ✅ |
| 기능 8 (포인트 지급) | 점수 구간별 자동 지급 | ✅ |
| 기능 8 | 하루 1회 중복 방지 | ✅ |
| 기능 8 | 결과 페이지 포인트 메시지 | ✅ |
| 기능 9 (문제 작성 폼) | 언어·난이도 드롭다운 | ✅ |
| 기능 9 | 작성 완료 후 바로 보기 링크 | ✅ |
| 기능 9 | 실시간 미리보기 패널 | ✅ |

---

## 잔여 미달성 항목

| 기능 | 수락 조건 | 단계 | 비고 |
|---|---|---|---|
| 기능 2 | 페이지네이션 / 무한 스크롤 (문제 20개↑) | 4단계 | 현재 문제 수 부족으로 우선순위 낮음 |

---

## 사전 조건 (DB 테이블)

`problem_submissions` 테이블이 DB에 존재해야 합니다. 없으면 로그인 사용자의 문제 목록 페이지에서 500 오류 발생.

```sql
-- CreateSql.sql 파일 마지막 구문을 MySQL에서 실행
CREATE TABLE IF NOT EXISTS problem_submissions (
    submission_id   INT AUTO_INCREMENT PRIMARY KEY,
    user_id         VARCHAR(10) NOT NULL,
    problem_id      INT NOT NULL,
    submitted_answer TEXT NOT NULL,
    ai_score        INT NOT NULL DEFAULT 0,
    ai_feedback     TEXT,
    submitted_at    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    point_awarded   INT NOT NULL DEFAULT 0,
    FOREIGN KEY (user_id)    REFERENCES user(user_id)    ON DELETE CASCADE,
    FOREIGN KEY (problem_id) REFERENCES problems(problem_id) ON DELETE CASCADE
);
```

---

## 하네스 개선 제안

1. **MyBatis POJO에 @Setter 금지 규칙 적용 범위 명시**: 개발자 하네스의 "Entity에 @Setter 금지" 규칙이 JPA @Entity를 전제로 서술되어 있으나, 이 프로젝트는 MyBatis 기반 POJO 클래스도 다수 사용합니다. 동일 규칙 적용 여부를 명시하도록 보완을 권장합니다.
2. **기술 부채 항목 구분**: 기획서 구현 우선순위 1단계 내에 외부 라이브러리 도입이 필요한 항목(CodeMirror 등)이 포함될 경우 "기술 부채(Tech Debt)" 섹션으로 분리하여 단계별 완료 기준을 명확히 하도록 권장합니다.
