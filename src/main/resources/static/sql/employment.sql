DROP TABLE IF EXISTS employment;


CREATE TABLE employment(
recruit_no BIGINT PRIMARY KEY,
instt_nm VARCHAR(255),
recruit_nm VARCHAR(255),
jobs_cd_nm VARCHAR(255),
recruit_se_cd VARCHAR(255),
work_region VARCHAR(255),
career VARCHAR(255),
receprion_close_dt TIMESTAMP,
employment_url text,
is_scraped BOOLEAN,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb COLLATE=utf8_unicode_ci;



select * from employment;