## DATABASE 생성 및 선택
## DROP DATABASE IF EXISTS springboot;
## CREATE DATABASE IF NOT EXISTS springboot;
use springboot;

## recommendList 테이블
drop table if exists contest;
CREATE TABLE IF NOT EXISTS contest (
    recommend_no INTEGER AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(20) NOT NULL,
    title VARCHAR(100) NOT NULL,
    host_organization VARCHAR(300),  
    reg_date TIMESTAMP NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    lecture_source VARCHAR(300),
    content TEXT,  -- TEXT로 변경
    pass VARCHAR(60),
    file1 VARCHAR(100),
    recommend_num INTEGER DEFAULT 0
);