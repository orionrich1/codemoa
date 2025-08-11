-- ====================================================================================
-- CodeMoa 프로젝트 통합 초기화 스크립트
-- 실행 시 기존 테이블과 데이터는 모두 삭제되고, 새로운 구조와 샘플 데이터로 대체됩니다.
-- ====================================================================================

use springboot;


-- 제약 조건 체크를 잠시 비활성화하여 순서에 상관없이 테이블을 삭제할 수 있게 합니다.
SET FOREIGN_KEY_CHECKS = 0;

-- 1. 기존 테이블 삭제 (존재할 경우)
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS community_board;
DROP TABLE IF EXISTS pointlog;
DROP TABLE IF EXISTS local_user;
DROP TABLE IF EXISTS sns_user;
DROP TABLE IF EXISTS user;

-- 제약 조건 체크를 다시 활성화합니다.
SET FOREIGN_KEY_CHECKS = 1;

-- ====================================================================================
-- 2. 테이블 생성 (DDL)
-- ====================================================================================

CREATE TABLE user (
    user_id VARCHAR(10) NOT NULL COMMENT '사용자 ID (PK)',
    name VARCHAR(10) COMMENT '이름',
    nickname VARCHAR(10) UNIQUE COMMENT '닉네임 (고유)',
    mobile VARCHAR(15) COMMENT '휴대폰 번호',
    email VARCHAR(100) COMMENT '이메일',
    total_points INT NOT NULL DEFAULT 0 COMMENT '총 보유 포인트',
    membership_date DATETIME NOT NULL COMMENT '가입일',
    unban_date DATETIME NOT NULL COMMENT '차단 해제일',
    grade VARCHAR(255) NOT NULL COMMENT '사용자 등급 (Enum 이름)',
    user_position VARCHAR(10) DEFAULT 'USER' COMMENT '유저 직책',
    PRIMARY KEY (user_id)
) COMMENT '사용자 기본 정보';

CREATE TABLE local_user (
    user_id VARCHAR(10) NOT NULL COMMENT '사용자 ID (PK, FK)',
    pass VARCHAR(100) NOT NULL COMMENT '비밀번호 (암호화된 값)',
    PRIMARY KEY (user_id),
    CONSTRAINT fk_local_user_user FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE
) COMMENT '일반 로그인 사용자 정보';

CREATE TABLE sns_user (
    user_id VARCHAR(10) NOT NULL COMMENT '사용자 ID (PK, FK)',
    sns_type VARCHAR(20) NOT NULL COMMENT 'SNS 종류 (예: google, kakao)',
    sns_id VARCHAR(100) NOT NULL COMMENT 'SNS 고유 ID',
    PRIMARY KEY (user_id),
    CONSTRAINT fk_sns_user_user FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE
) COMMENT '소셜 로그인 사용자 정보';

CREATE TABLE community_board (
    board_no INT NOT NULL AUTO_INCREMENT COMMENT '게시글 번호 (PK)',
    user_id VARCHAR(10) COMMENT '작성자 ID (FK)',
    title VARCHAR(255) NOT NULL COMMENT '제목',
    content LONGTEXT NOT NULL COMMENT '내용',
    recommend INT DEFAULT 0 COMMENT '추천수',
    file1 VARCHAR(255) COMMENT '첨부파일',
    category VARCHAR(255) NOT NULL COMMENT '카테고리',
    post_type VARCHAR(255) NOT NULL COMMENT '게시글 타입 (NORMAL, QUESTION)',
    staked_points INT DEFAULT 0 COMMENT '질문에 건 포인트',
    is_resolved BOOLEAN DEFAULT FALSE COMMENT '해결 여부',
    created_at DATETIME NOT NULL COMMENT '작성일',
    PRIMARY KEY (board_no),
    CONSTRAINT fk_board_user FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE SET NULL
) COMMENT '커뮤니티 게시판';

CREATE TABLE comment (
    comment_no INT NOT NULL AUTO_INCREMENT COMMENT '댓글 번호 (PK)',
    content LONGTEXT NOT NULL COMMENT '내용',
    is_adopted BOOLEAN DEFAULT FALSE COMMENT '채택 여부',
    created_at DATETIME NOT NULL COMMENT '작성일',
    user_id VARCHAR(10) COMMENT '댓글 작성자 ID (FK)',
    board_no INT COMMENT '게시글 번호 (FK)',
    PRIMARY KEY (comment_no),
    CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE SET NULL,
    CONSTRAINT fk_comment_board FOREIGN KEY (board_no) REFERENCES community_board (board_no) ON DELETE CASCADE
) COMMENT '게시판 댓글';

CREATE TABLE pointlog (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '로그 ID (PK)',
    user_id VARCHAR(10) COMMENT '사용자 ID (FK)',
    points INT COMMENT '지급 또는 사용된 포인트',
    description VARCHAR(255) COMMENT '포인트 변경 사유',
    event_type VARCHAR(255) NOT NULL COMMENT '포인트 이벤트 종류',
    created_at DATETIME NOT NULL COMMENT '로그 생성 시각',
    PRIMARY KEY (id),
    CONSTRAINT fk_pointlog_user FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE SET NULL
) COMMENT '포인트 변동 내역';

-- ====================================================================================
-- 3. 샘플 데이터 삽입 (DML)
-- ====================================================================================

SET @encrypted_pass = '$2a$10$zl.oMh5bB6yL3w.Z1o5m.u6fW5X3c2e1v.g6h7i8j9k0l'; -- '1234'를 Bcrypt 암호화

-- 3-1. 사용자 데이터 삽입
INSERT INTO user (user_id, name, nickname, mobile, email, total_points, membership_date, unban_date, grade) VALUES
('admin', '관리자', 'Admin', '010-0000-0000', 'admin@codemoa.com', 999999, NOW(), NOW(), 'GRANDMASTER'),
('user01', '김브론즈', '브론즈맨', '010-1111-0001', 'user01@test.com', 500, NOW(), NOW(), 'BRONZE'),
('user02', '박브론즈', '나는브론즈', '010-1111-0002', 'user02@test.com', 1500, NOW(), NOW(), 'BRONZE'),
('user03', '이실버', '실버스타', '010-1111-0003', 'user03@test.com', 2500, NOW(), NOW(), 'SILVER'),
('user04', '최실버', '실버서퍼', '010-1111-0004', 'user04@test.com', 4800, NOW(), NOW(), 'SILVER'),
('user05', '정골드', '골드러시', '010-1111-0005', 'user05@test.com', 6000, NOW(), NOW(), 'GOLD'),
('user06', '강골드', '황금인생', '010-1111-0006', 'user06@test.com', 9500, NOW(), NOW(), 'GOLD'),
('user07', '조플래', '플래티넘맨', '010-1111-0007', 'user07@test.com', 12000, NOW(), NOW(), 'PLATINUM'),
('user08', '윤플래', '백금전사', '010-1111-0008', 'user08@test.com', 18000, NOW(), NOW(), 'PLATINUM'),
('user09', '장다이아', '다이아손', '010-1111-0009', 'user09@test.com', 25000, NOW(), NOW(), 'DIAMOND'),
('user10', '임다이아', '빛나는다이아', '010-1111-0010', 'user10@test.com', 45000, NOW(), NOW(), 'DIAMOND'),
('user11', '구글유저', 'Gooogler', '010-1111-0011', 'user11@google.com', 1000, NOW(), NOW(), 'BRONZE');

-- 3-2. 로컬/소셜 사용자 데이터 삽입
INSERT INTO local_user (user_id, pass) VALUES
('admin', @encrypted_pass),('user01', @encrypted_pass),('user02', @encrypted_pass),('user03', @encrypted_pass),('user04', @encrypted_pass),
('user05', @encrypted_pass),('user06', @encrypted_pass),('user07', @encrypted_pass),('user08', @encrypted_pass),('user09', @encrypted_pass),('user10', @encrypted_pass);

INSERT INTO sns_user (user_id, sns_type, sns_id) VALUES
('user11', 'google', '123456789012345678901');

-- 3-3. 게시판 데이터 삽입
INSERT INTO community_board (board_no, user_id, title, content, category, post_type, staked_points, created_at) VALUES
(1, 'user05', '자바 질문 있습니다!', '스프링 부트에서 순환 참조는 어떻게 해결하나요? @Lazy 말고 다른 방법도 있나요?', 'Java', 'QUESTION', 100, NOW() - INTERVAL 5 DAY),
(2, 'user03', '파이썬으로 웹크롤링 하는 법', 'BeautifulSoup 라이브러리와 requests를 사용하면 쉽게 할 수 있습니다.', 'Python', 'NORMAL', 0, NOW() - INTERVAL 4 DAY),
(3, 'user07', '요즘 코틀린 어떤가요?', '안드로이드 개발에서는 거의 필수로 자리잡았고, 서버 사이드에서도 많이 사용되는 추세입니다.', 'Kotlin', 'NORMAL', 0, NOW() - INTERVAL 3 DAY),
(4, 'admin', '[공지] CodeMoa 서비스 점검 안내', '더 나은 서비스 제공을 위해 시스템 점검을 실시합니다. 많은 양해 바랍니다.', '자유', 'NORMAL', 0, NOW() - INTERVAL 2 DAY);

-- 3-4. 댓글 데이터 삽입
INSERT INTO comment (user_id, board_no, content, created_at) VALUES
('user08', 1, '생성자 주입 대신 Setter 주입을 사용하거나, 필드 주입을 사용하는 방법도 있습니다. 하지만 권장되진 않아요.', NOW() - INTERVAL 4 DAY),
('admin', 1, '좋은 질문과 답변입니다! 순환 참조는 설계적으로 피하는 것이 가장 좋습니다.', NOW() - INTERVAL 4 DAY),
('user01', 2, '오 좋은 정보 감사합니다!', NOW() - INTERVAL 3 DAY),
('user02', 4, '점검 시간은 언제부터 언제까지인가요?', NOW() - INTERVAL 1 DAY);

-- 3-5. 포인트 로그 데이터 삽입
INSERT INTO pointlog (user_id, points, description, event_type, created_at) VALUES
('user10', 15, '게시글 작성', 'CREATE_POST', NOW() - INTERVAL 1 DAY),
('user09', 15, '게시글 작성', 'CREATE_POST', NOW() - INTERVAL 2 DAY),
('user10', 5, '댓글 작성', 'CREATE_COMMENT', NOW() - INTERVAL 1 DAY),
('user09', 5, '댓글 작성', 'CREATE_COMMENT', NOW() - INTERVAL 2 DAY),
('user03', 10, '일일 로그인', 'DAILY_LOGIN', NOW() - INTERVAL 3 DAY),
('user05', 10, '일일 로그인', 'DAILY_LOGIN', NOW() - INTERVAL 4 DAY);

-- 3-6. 어드민 계정 수정
UPDATE user SET user_position='ADMIN' WHERE user_id='admin';

-- ====================================================================================
-- 4. 데이터 삽입 확인 (SELECT)
-- ====================================================================================
SELECT * FROM user;
SELECT * FROM local_user;
SELECT * FROM sns_user;
SELECT * FROM community_board;
SELECT * FROM comment;
SELECT * FROM pointlog;