use springboot;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS community_board;
DROP TABLE IF EXISTS pointlog;
DROP TABLE IF EXISTS local_user;
DROP TABLE IF EXISTS sns_user;
DROP TABLE IF EXISTS ban_history;
DROP TABLE IF EXISTS user;

DROP TABLE IF EXISTS lecture;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS contest;

DROP TABLE IF EXISTS recruitbbs;
DROP TABLE IF EXISTS employment;

DROP TABLE IF EXISTS project_diary;
DROP TABLE IF EXISTS project_checklist;
DROP TABLE IF EXISTS project;

DROP TABLE IF EXISTS faq;
DROP TABLE IF EXISTS qna;
DROP TABLE IF EXISTS qna_reply;

DROP TABLE IF EXISTS problems;

SET FOREIGN_KEY_CHECKS = 1;

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
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일',
    PRIMARY KEY (board_no),
    CONSTRAINT fk_board_user FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE SET NULL
) COMMENT '커뮤니티 게시판';

CREATE TABLE comment (
    comment_no INT NOT NULL AUTO_INCREMENT COMMENT '댓글 번호 (PK)',
    content LONGTEXT NOT NULL COMMENT '내용',
    is_adopted BOOLEAN DEFAULT FALSE COMMENT '채택 여부',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일',
    user_id VARCHAR(10) COMMENT '댓글 작성자 ID (FK)',
    board_no INT COMMENT '게시글 번호 (FK)',
    PRIMARY KEY (comment_no),
    CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE SET NULL,
    CONSTRAINT fk_comment_board FOREIGN KEY (board_no) REFERENCES community_board (board_no) ON DELETE CASCADE
) COMMENT '게시판 댓글';

CREATE TABLE ban_history (
	history_no INT NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(10) NOT NULL COMMENT '사용자 ID (FK)',
    ban_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '차단된 날짜',
    ban_days INT NOT NULL COMMENT '차단한 일일 수',
    ban_reason VARCHAR(100) NULL COMMENT '차단 사유',
    PRIMARY KEY (history_no),
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE -- 회원이 삭제되면 같이 삭제
) COMMENT '회원 차단 기록';

CREATE TABLE pointlog (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '로그 ID (PK)',
    user_id VARCHAR(10) COMMENT '사용자 ID (FK)',
    points INT COMMENT '지급 또는 사용된 포인트',
    description VARCHAR(255) COMMENT '포인트 변경 사유',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '로그 생성 시각',
    PRIMARY KEY (id),
    CONSTRAINT fk_pointlog_user FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE SET NULL
) COMMENT '포인트 변동 내역';


CREATE TABLE IF NOT EXISTS book (
    book_no INTEGER AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(20) NOT NULL,
    title VARCHAR(100) NOT NULL,
    publisher VARCHAR(300),  
    reg_date TIMESTAMP NOT NULL,
    pub_date TIMESTAMP,
    book_source VARCHAR(300),
    content TEXT,  -- TEXT로 변경
    file1 VARCHAR(100),
    rating DECIMAL(3,1),
    isbn VARCHAR(60),
    total_page_num INTEGER,
    view_count INTEGER DEFAULT 0
    -- FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE IF NOT EXISTS lecture (
    recommend_no INTEGER AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(20) NOT NULL,
    title VARCHAR(100) NOT NULL,
    subtitle VARCHAR(300),  
    category VARCHAR(50),
    rating DECIMAL(2,1),
    reg_date TIMESTAMP NOT NULL,
    lecture_source VARCHAR(300),
    content1 VARCHAR(1000),
    content2 VARCHAR(5000),  
    file1 VARCHAR(100)
    -- FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE IF NOT EXISTS contest (
    contest_no INTEGER AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(20) NOT NULL,
    title VARCHAR(100) NOT NULL,
    host_organization VARCHAR(300),  
    reg_date TIMESTAMP NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    contest_source VARCHAR(300),
    content TEXT,  -- TEXT로 변경
    pass VARCHAR(60),
    file1 VARCHAR(100),
    recommend_num INTEGER DEFAULT 0
    -- FOREIGN KEY (user_id) REFERENCES user(user_id) 
);

CREATE TABLE recruitbbs (
	recruit_id INT AUTO_INCREMENT PRIMARY KEY,
	user_id VARCHAR(50) NOT NULL,
	contest_title VARCHAR(255),
	recruit_type VARCHAR(50) NOT NULL,
	recruit_period VARCHAR(50),
	activity_period VARCHAR(50),
	total_members INT NOT NULL,
	remaining_members INT NOT NULL,
	progress_type VARCHAR(50),
	contact VARCHAR(100),
	tech_stack VARCHAR(255),
	status VARCHAR(50) DEFAULT '모집중',
	apply_guide TEXT,
	title VARCHAR(200) NOT NULL,
	content TEXT NOT NULL,
	attachment_url VARCHAR(255),
	view_count INT DEFAULT 0,
	reg_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE employment (
    recruit_no BIGINT PRIMARY KEY AUTO_INCREMENT,
    wanted_auth_no VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    company VARCHAR(255) NOT NULL,
    region VARCHAR(100),              -- nullable = true
    sub_region VARCHAR(100),          -- nullable = true
    reg_dt VARCHAR(20),               -- nullable = true
    close_dt VARCHAR(20),             -- nullable = true
    url VARCHAR(768) NOT NULL UNIQUE,
    type VARCHAR(255),                -- nullable = true
    start_date VARCHAR(20),           -- nullable = true
    end_date VARCHAR(20),   
    source VARCHAR(255) DEFAULT '정보 없음'
);


CREATE TABLE project (
    project_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(10) NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    status ENUM('계획', '진행중', '종료') NOT NULL DEFAULT '계획',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE project_checklist (
    checklist_id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT NOT NULL,
    content VARCHAR(255) NOT NULL,
    is_done BOOLEAN NOT NULL DEFAULT FALSE,
    priority TINYINT NOT NULL DEFAULT 3,
    FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE
);

CREATE TABLE project_diary (
    diary_id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT NOT NULL,
    work_date DATE NOT NULL,
    content TEXT,
    FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE,
    UNIQUE KEY (project_id, work_date)
);

CREATE TABLE faq (
    faq_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    question VARCHAR(255) NOT NULL,
    answer TEXT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE qna (
    qna_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    writer_id VARCHAR(255) NOT NULL, -- user 테이블의 user_id와 연결
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_answered BOOLEAN DEFAULT FALSE, -- 답변 완료 여부
    view_count INT DEFAULT 0
);

CREATE TABLE qna_reply (
    reply_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    qna_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    writer_id VARCHAR(255) NOT NULL, -- user 테이블의 user_id와 연결 (관리자)
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (qna_id) REFERENCES qna(qna_id) ON DELETE CASCADE
);

CREATE TABLE problems(
	problem_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(30) NOT NULL,
    title VARCHAR(100) NOT NULL,
    content TEXT,
    answer TEXT,
    hint TEXT,
    difficulty ENUM ('상', '중', '하') NOT NULL,
    category VARCHAR(20) NOT NULL,
    reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);