## DATABASE 생성 및 선택
## DROP DATABASE IF EXISTS springboot;
CREATE DATABASE IF NOT EXISTS springboot;
use springboot;

## recommendList 테이블
create table if not exists recommendList(
	recommend_no INTEGER AUTO_INCREMENT PRIMARY KEY,
	user_id varchar not null,
	title varchar not null,
	category varchar,
	reg_date timestamp not null,
	content varchar,
	recommend_num integer default 0
	
) 
