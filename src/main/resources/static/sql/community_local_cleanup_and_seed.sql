-- =============================================================================
-- 로컬 codemoa_db: 커뮤니티 테스트/의미 없는 글 정리 + C·기타 언어 게시판 시드
-- 실행 예:
--   mysql --default-character-set=utf8mb4 -u root -p codemoa_db < src/main/resources/static/sql/community_local_cleanup_and_seed.sql
-- (1366 오류 시: 테이블이 latin1이면 아래 ALTER로 utf8mb4 통일)
-- =============================================================================

USE codemoa_db;

SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

-- 한글 INSERT/UPDATE 시 ERROR 1366 방지 (기존 컬럼이 latin1인 경우)
ALTER TABLE community_board
  CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 잘못 저장된 영문 'free' → 서비스 기준 '자유'로 통일 (메인 6개 언어·자유 외 필터와 맞춤)
UPDATE community_board
SET category = '자유'
WHERE LOWER(TRIM(category)) = 'free';

-- 의미 없는 제목·난해한 테스트 글 삭제 (댓글은 FK CASCADE로 함께 삭제)
DELETE FROM community_board
WHERE title IN ('ㅇㅇㅇ', 'ㅇㅇ', 'ddd', 'dddd', 'dd', 'ddddd', 'ㅁㅁㅁ')
   OR title REGEXP '^[ㅇ]{3,}$'
   OR (CHAR_LENGTH(TRIM(title)) BETWEEN 2 AND 5 AND TRIM(title) REGEXP '^[dD]+$');

-- ---------------------------------------------------------------------------
-- C / 기타 언어: DB category 값은 그대로 저장. URL은 CommunityCategoryPaths 기준
--   Java·Python·JS·C#·Kotlin·자유 가 아니면 /community/etc (기타 언어)에서 조회됨.
-- ---------------------------------------------------------------------------

INSERT INTO community_board (user_id, title, content, category, post_type, recommend, created_at) VALUES
-- C
('user01', 'malloc과 calloc 차이를 실무에서 어떻게 선택하나요?', 'calloc은 0 초기화까지 보장해서 배열 할당에 자주 씁니다. 성능이 중요한 핫패스에서는 malloc만 쓰는 팀도 봤어요. 경험 공유 부탁드립니다.', 'C', 'NORMAL', 14, NOW() - INTERVAL 1 DAY),
('user03', '포인터 산술: int* vs char* 한 칸 이동 크기', '제목 그대로입니다. 면접 질문으로도 자주 나와서 정리해 봤어요.', 'C', 'NORMAL', 9, NOW() - INTERVAL 2 DAY),
('user05', '구조체 패딩 줄이는 순서 팁', '큰 멤버부터 두면 패딩이 줄어든다는 말이 맞는지, 컴파일러별로 확인해 보신 분 계신가요?', 'C', 'NORMAL', 7, NOW() - INTERVAL 3 DAY),
('user07', 'POSIX pthread 기초 예제 정리', 'mutex와 condition variable 최소 예제를 정리해 뒀습니다. 초보 분들 참고하세요.', 'C', 'NORMAL', 11, NOW() - INTERVAL 4 DAY),
('user09', 'C99 vs C11 실무에서 어디까지 쓰나요?', '회사 표준이 C99인데 designated initializer 쓰고 싶은 유혹이… 팀 규칙 어떻게 두시나요?', 'C', 'NORMAL', 6, NOW() - INTERVAL 5 DAY),
('admin', '헤더 include 가드 #ifndef 패턴', 'pragma once vs 전통 가드, 레거시 코드베이스에서는 가드를 고집하는 경우가 많더라고요.', 'C', 'NORMAL', 4, NOW() - INTERVAL 6 DAY),

-- 기타 언어 (MAIN_CATEGORIES 밖)
('user02', 'Go 모듈 경로와 사내 프라이빗 프록시', 'go env GOPROXY 설정이 팀마다 달라서 정리해 뒀습니다. 아티팩트 저장소 연동 팁 있으면 공유해 주세요.', 'Go', 'NORMAL', 10, NOW() - INTERVAL 1 DAY),
('user04', 'Rust 소유권 에러 메시지 읽는 법', 'borrow checker 에러가 길게 나올 때 핵심만 빨리 찾는 순서를 적어 봤어요.', 'Rust', 'NORMAL', 18, NOW() - INTERVAL 2 DAY),
('user06', 'Swift Concurrency actor 격리 범위 질문', '@MainActor 붙인 타입에서 UIKit 접근할 때 주의할 점이 있을까요?', 'Swift', 'NORMAL', 5, NOW() - INTERVAL 3 DAY),
('user08', 'Ruby on Rails 7 importmap vs jsbundling', '소규모 프로젝트면 importmap만으로 충분했는데, 번들러 도입 기준을 어떻게 잡으시나요?', 'Ruby', 'NORMAL', 8, NOW() - INTERVAL 4 DAY),
('user10', 'TypeScript strict 옵션 단계적 켜기 후기', 'strictNullChecks부터 켰을 때 레거시 코드와의 싸움이었습니다.', 'TypeScript', 'NORMAL', 12, NOW() - INTERVAL 5 DAY),
('user01', 'PHP 8 attributes로 라우트 메타데이터', 'Attribute 기반으로 OpenAPI 태그를 붙이는 실험 중입니다.', 'PHP', 'NORMAL', 3, NOW() - INTERVAL 6 DAY),
('user03', 'Lua 임베딩 시 스택 크기·에러 핸들링', '게임 스크립팅용으로 Lua C API 쓰는데 pcall 패턴 정리했습니다.', 'Lua', 'NORMAL', 6, NOW() - INTERVAL 7 DAY),
('user05', 'Scala 3 given/using으로 의존성 주입 느낌 내기', '라이브러리 없이 팀 내 DSL 만들 때 유용했어요.', 'Scala', 'NORMAL', 5, NOW() - INTERVAL 8 DAY);
