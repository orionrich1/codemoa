DROP TABLE IF EXISTS employment;

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

select * from employment;
