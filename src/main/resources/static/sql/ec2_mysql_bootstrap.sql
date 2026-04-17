-- =============================================================================
-- EC2(Ubuntu) MySQL 최초 준비 — application-server.properties 와 맞춤
--   spring.datasource.url=jdbc:mysql://127.0.0.1:3306/codemoa_db?...
--   username=codemoa_user / password=1234
--
-- 실행 예 (서버 SSH):
--   sudo mysql < /path/to/ec2_mysql_bootstrap.sql
--   또는: sudo mysql -e "source /path/to/ec2_mysql_bootstrap.sql"
--
-- 비밀번호는 운영 환경에 맞게 변경한 뒤, 동일 값을 서버의 secret.properties
-- 또는 환경 변수·배포 스크립트에서 주입하도록 맞출 것.
-- =============================================================================

CREATE DATABASE IF NOT EXISTS codemoa_db
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- JDBC가 127.0.0.1 로 붙을 때와 소켓 localhost 일 때 모두 대비
CREATE USER IF NOT EXISTS 'codemoa_user'@'localhost' IDENTIFIED BY '1234';
CREATE USER IF NOT EXISTS 'codemoa_user'@'127.0.0.1' IDENTIFIED BY '1234';

GRANT ALL PRIVILEGES ON codemoa_db.* TO 'codemoa_user'@'localhost';
GRANT ALL PRIVILEGES ON codemoa_db.* TO 'codemoa_user'@'127.0.0.1';

FLUSH PRIVILEGES;

-- 이후: spring.profiles.active=server 로 기동 시 JPA ddl-auto=update 로 스키마 생성,
-- 또는 기존 덤프가 있으면 codemoa_db 에 import.
