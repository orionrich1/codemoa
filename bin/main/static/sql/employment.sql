DROP TABLE IF EXISTS employment;

CREATE TABLE employment (
    recruit_no BIGINT PRIMARY KEY AUTO_INCREMENT,
    wantedAuthNo VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    company VARCHAR(255) NOT NULL,
    region VARCHAR(100) NOT NULL,
    sub_region VARCHAR(100) NOT NULL,
    regDt VARCHAR(20) NOT NULL,
    close_dt VARCHAR(20) NOT NULL,
    url VARCHAR(768) NOT NULL UNIQUE
);

select * from employment;
