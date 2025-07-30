DROP TABLE IF EXISTS employment;


CREATE TABLE employment (
    recruit_no BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    company VARCHAR(255) NOT NULL,
    region VARCHAR(100)NOT NULL,
    sub_region VARCHAR(100) NOT NULL,
    url VARCHAR(1024)
);

select * from employment;
