# DATABASE 생성 및 선택
CREATE DATABASE IF NOT EXISTS springboot;
use springboot;

-- 기존의 springbbs 테이블을 삭제하고 추천, 땡큐를 저장할 컬럼을 추가해 다시 생성한다.
-- 게시글 번호, 제목, 작성자, 내용, 날짜, 조회수, 비밀번호, 파일정보, 추천, 땡큐
-- no, title, writer, content, reg_date, read_count, pass, file1, recommend, thank
DROP TABLE IF EXISTS springbbs;
CREATE TABLE IF NOT EXISTS springbbs(
  no INTEGER AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(50) NOT NULL,
  writer VARCHAR(10) NOT NULL,  
  content VARCHAR(1000) NOT NULL,
  reg_date TIMESTAMP NOT NULL,
  read_count INTEGER(5) NOT NULL,
  pass VARCHAR(10) NOT NULL,
  file1 VARCHAR(100),
  recommend INTEGER DEFAULT 0,
  thank INTEGER DEFAULT 0
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- 아래를 10번 입력한다.
-- 199번과 200번 게시글을 댓글 테이블에서 참조하므로 꼭 10번을 입력해야 한다.
INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('Thymeleaf 문법 질문', '김소현', 'Thymeleaf 템플릿에서 조건문과 반복문 사용법이 궁금합니다.', '2023-07-20 15:30:00', 36, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('OAuth2 소셜 로그인 구현', '유연석', 'Google OAuth2를 이용한 소셜 로그인 구현 방법이 궁금합니다.', '2023-07-18 10:40:00', 60, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('Spring Security 로그인 설정', '문채원', 'Spring Security로 로그인 기능을 구현하는 방법을 알고 싶습니다.', '2023-07-15 17:50:00', 52, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('REST API 설계 관련 질문', '서강준', 'RESTful API 설계 시 자원 네이밍과 URI 설계 방법이 궁금합니다.', '2023-07-10 13:15:00', 47, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('MySQL 연결 오류', '한지민', 'Spring Boot에서 MySQL 연결 시 오류가 발생합니다.', '2023-07-05 08:20:00', 33, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('Docker 이미지 생성 문의', '정하늘', 'Spring Boot 애플리케이션의 Docker 이미지 생성이 잘 되지 않습니다.', '2023-07-01 16:30:00', 28, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('Mockito 사용법 질문', '최윤서', 'Mockito로 의존 객체를 mocking하는 방법을 알고 싶습니다.', '2023-06-27 09:45:00', 38, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('JUnit 테스트 작성법', '박지후', 'JUnit으로 단위 테스트를 어떻게 작성해야 할지 모르겠습니다.', '2023-06-22 14:00:00', 42, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('Jenkins 자동 배포 설정', '이서연', 'Jenkins를 통해 CI/CD 자동 배포를 설정하는 방법을 알고 싶습니다.', '2023-06-18 11:12:00', 34, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('GitHub 사용법 문의', '김민준', 'GitHub의 기본적인 사용법에 대해 알고 싶습니다.', '2023-06-15 10:23:00', 25, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('Gradle 빌드 오류', '장유빈', 'Gradle 빌드 중 발생하는 오류를 해결하고 싶습니다.', '2023-06-10 15:45:00', 40, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('Maven 의존성 문제', '윤다현', 'Maven 프로젝트에서 의존성 충돌 문제가 발생합니다.', '2023-06-05 09:30:00', 29, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('Redis 캐시 적용법', '강민준', 'Spring Boot에서 Redis를 이용해 캐시를 적용하는 방법을 알고 싶습니다.', '2023-06-01 13:50:00', 36, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('Kafka 메시지 처리', '오지후', 'Kafka를 사용한 메시지 큐 처리 방법에 대해 문의드립니다.', '2023-05-28 10:20:00', 44, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('Nginx 리버스 프록시 설정', '김지안', 'Nginx에서 리버스 프록시 설정 방법이 궁금합니다.', '2023-05-23 16:05:00', 31, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('Tomcat 배포 이슈', '이하늘', 'Tomcat 서버에 WAR 파일 배포 시 발생하는 문제를 해결하고 싶습니다.', '2023-05-20 11:45:00', 26, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('Swagger API 문서 생성', '박주원', 'Swagger를 이용해 API 문서를 자동 생성하는 방법이 궁금합니다.', '2023-05-15 14:25:00', 37, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('RabbitMQ 큐 설정', '최도현', 'RabbitMQ에서 큐와 익스체인지 설정 방법에 대해 알고 싶습니다.', '2023-05-10 09:55:00', 41, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('ELK 스택 설치', '정지호', 'ELK 스택을 설치하고 연동하는 과정을 알고 싶습니다.', '2023-05-05 13:40:00', 28, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('Prometheus 모니터링', '한예빈', 'Prometheus를 이용한 애플리케이션 모니터링 설정 방법이 궁금합니다.', '2023-05-01 10:10:00', 35, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('Grafana 대시보드 구성', '장하은', 'Grafana에서 대시보드를 구성하는 방법을 알고 싶습니다.', '2023-04-28 15:00:00', 33, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('IntelliJ 플러그인 추천', '윤태윤', 'Spring 개발에 유용한 IntelliJ 플러그인을 추천해주세요.', '2023-04-22 11:20:00', 30, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('VSCode 디버깅 설정', '강유찬', 'VSCode에서 Java 애플리케이션 디버깅 설정 방법이 궁금합니다.', '2023-04-18 09:45:00', 29, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('Postman API 테스트', '오채은', 'Postman을 사용해 REST API를 테스트하는 방법을 알고 싶습니다.', '2023-04-15 14:35:00', 27, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('cURL 명령어 사용법', '신태현', '터미널에서 cURL 명령어를 사용하는 방법을 자세히 알려주세요.', '2023-04-10 12:25:00', 31, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('CI/CD 파이프라인 구축', '유민지', 'CI/CD 파이프라인을 구축하는 기본적인 절차에 대해 문의드립니다.', '2023-04-05 09:10:00', 26, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('WebSocket 구현 방법', '배민서', 'Spring Boot에서 WebSocket을 구현하는 방법이 궁금합니다.', '2023-04-01 16:50:00', 34, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('OpenAPI 스펙 작성법', '임승현', 'OpenAPI 스펙을 작성하는 방법과 도구에 대해 알고 싶습니다.', '2023-03-28 13:15:00', 33, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('Actuator 활용법', '조지우', 'Spring Boot Actuator를 활용해 모니터링하는 방법이 궁금합니다.', '2023-03-25 10:40:00', 29, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('AOP 적용 사례', '서연우', 'AOP를 적용한 실무 사례와 설정 방법에 대해 문의드립니다.', '2023-03-22 08:30:00', 27, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('Validation 처리법', '노하진', 'Spring Boot에서 입력값 Validation을 처리하는 방법을 알고 싶습니다.', '2023-03-18 16:00:00', 24, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('Multipart 파일 업로드', '황도영', 'Multipart를 이용한 파일 업로드 구현법이 궁금합니다.', '2023-03-15 12:45:00', 23, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('예외 처리 전략', '문예진', 'Spring Boot에서 효과적인 예외 처리 전략에 대해 알고 싶습니다.', '2023-03-12 14:20:00', 21, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('스케줄러 설정법', '백지환', 'Spring에서 스케줄러를 설정하는 방법이 궁금합니다.', '2023-03-10 10:05:00', 25, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('H2 DB 사용법', '김유하', '테스트용 인메모리 DB인 H2 사용법을 알려주세요.', '2023-03-05 09:00:00', 30, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('TestContainer 활용', '이채린', 'TestContainer를 활용한 통합 테스트 방법이 궁금합니다.', '2023-03-01 15:30:00', 28, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('Devtools 자동 재시작', '박현우', 'Spring Boot Devtools를 사용한 자동 재시작 설정법을 알고 싶습니다.', '2023-02-28 11:15:00', 26, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('Flyway 마이그레이션', '최주하', 'Flyway로 데이터베이스 마이그레이션을 수행하는 방법이 궁금합니다.', '2023-02-25 14:40:00', 29, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('Liquibase 설정', '정유나', 'Liquibase를 설정하고 관리하는 방법을 알고 싶습니다.', '2023-02-22 09:20:00', 27, '1234', null);

INSERT INTO springbbs (title, writer, content, reg_date, read_count, pass, file1)
VALUES ('YAML 설정 작성법', '한승우', 'Spring Boot에서 YAML 설정 파일을 작성하는 방법이 궁금합니다.', '2023-02-18 16:55:00', 31, '1234', null);

COMMIT;

SELECT * FROM springbbs ORDER BY no DESC;