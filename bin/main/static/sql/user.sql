-- 외래 키 제약 조건으로 인해 DROP 순서가 중요합니다.
-- 참조하는 테이블(자식)부터 역순으로 삭제합니다.
DROP TABLE IF EXISTS local_user;
DROP TABLE IF EXISTS sns_user;
DROP TABLE IF EXISTS ban_history;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS user_grade;



-- 1. user_grade 테이블 생성 (기준 정보)
-- 다른 테이블들이 참조해야 하므로 가장 먼저 만듭니다.
CREATE TABLE user_grade (
    grade_id VARCHAR(30) NOT NULL COMMENT '등급 ID',
    grade_name VARCHAR(30) NOT NULL COMMENT '등급 이름',
    min_points INT NOT NULL DEFAULT 0 COMMENT '등급 최소 포인트',
    PRIMARY KEY (grade_id)
) COMMENT '회원 등급 정보';


-- 2. user 테이블 생성 (핵심 회원 정보)
-- user_grade 테이블을 참조하는 외래 키(FK)를 포함합니다.
CREATE TABLE user (
    user_id VARCHAR(10) NOT NULL COMMENT '사용자 ID',
    name VARCHAR(10) NULL COMMENT '이름',
    nickname VARCHAR(10) NULL UNIQUE COMMENT '닉네임',
    mobile VARCHAR(15) NULL COMMENT '전화번호',
    total_points INT NOT NULL DEFAULT 0 COMMENT '총 포인트',
    membership_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '가입일',
    unban_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '차단해제일',
    email VARCHAR(100) NULL COMMENT '이메일',
    grade_id VARCHAR(30) NULL COMMENT '회원 등급 ID (FK)',
    PRIMARY KEY (user_id),
    FOREIGN KEY (grade_id) REFERENCES user_grade(grade_id) -- 외래 키 관계 설정
) COMMENT '회원 공통 정보';


-- 3. local_user 테이블 생성 (자체 로그인 정보)
-- user 테이블을 참조합니다.
CREATE TABLE local_user (
    user_id VARCHAR(10) NOT NULL COMMENT '사용자 ID (PK, FK)',
    pass VARCHAR(100) NOT NULL COMMENT '비밀번호 (암호화된 값)',
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE -- 회원이 삭제되면 같이 삭제
) COMMENT '자체 로그인 회원 정보';


-- 4. sns_user 테이블 생성 (소셜 로그인 정보)
-- user 테이블을 참조합니다.
CREATE TABLE sns_user (
    user_id VARCHAR(10) NOT NULL COMMENT '사용자 ID (PK, FK)',
    sns_type VARCHAR(20) NOT NULL COMMENT 'SNS 종류 (예: google, kakao)',
    sns_id VARCHAR(100) NOT NULL COMMENT '해당 SNS의 고유 ID',
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE -- 회원이 삭제되면 같이 삭제
) COMMENT '소셜 로그인 회원 정보';


-- 5. ban_history 테이블 생성 (차단 기록)
-- user 테이블을 참조합니다.
CREATE TABLE ban_history (
	history_no INT NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(10) NOT NULL COMMENT '사용자 ID (FK)',
    ban_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '차단된 날짜',
    ban_day INT NOT NULL COMMENT '차단한 일일 수',
    ban_reason VARCHAR(100) NULL COMMENT '차단 사유',
    PRIMARY KEY (history_no),
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE -- 회원이 삭제되면 같이 삭제
) COMMENT '회원 차단 기록';



INSERT INTO user_grade (grade_id, grade_name, min_points)
SELECT 'GENERAL', '일반회원', 0
WHERE NOT EXISTS (SELECT 1 FROM user_grade WHERE grade_id = 'GENERAL');



-- 2단계: 'oaeoae' 사용자의 기본 정보 생성
INSERT INTO user (user_id, name, nickname, email, total_points, grade_id, membership_date)
VALUES ('oaeoae', '테스트계정', '오애오애', 'oaeoae@test.com', 0, 'GENERAL', NOW());


-- 3단계: 'oaeoae' 사용자의 비밀번호 '1234' 설정
-- 주의: 실제 프로젝트에서는 반드시 비밀번호를 암호화해서 저장해야 합니다.
INSERT INTO local_user (user_id, pass)
VALUES ('oaeoae', '1234'); 

select * from user;


INSERT INTO ban_history (user_id, ban_day, ban_reason) VALUES ('oaeoae', 7,'도배');

SELECT * FROM user u
JOIN user_grade USING (grade_id)
LEFT JOIN ban_history b ON u.user_id = b.user_id
WHERE u.user_id='oaeoae';

INSERT INTO user_grade (grade_id, grade_name, min_points) VALUES ('BRONZE', '브론즈', 0);
INSERT INTO user_grade (grade_id, grade_name, min_points) VALUES ('SILVER', '실버', 1000);
INSERT INTO user_grade (grade_id, grade_name, min_points) VALUES ('GOLD', '골드', 5000);