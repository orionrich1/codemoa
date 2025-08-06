-- 댓글 테이블
DROP TABLE IF EXISTS reply;
CREATE TABLE IF NOT EXISTS reply(
	no INTEGER AUTO_INCREMENT PRIMARY KEY,
	bbs_no INTEGER NOT NULL,
	reply_content VARCHAR(500) NOT NULL,
	reply_writer VARCHAR(20) NOT NULL,
	reg_date TIMESTAMP NOT NULL,	
	CONSTRAINT reply_fk FOREIGN KEY(bbs_no) REFERENCES springbbs(no)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- 댓글 데이터 추가하기 - 1번만 실행한다. --
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (40, 'Thymeleaf에서는 ${객체.속성} 구문을 사용해 데이터를 출력할 수 있습니다.', 'midas', '2025-07-10');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (39, 'Spring에서는 Pageable과 PageRequest를 이용해 페이징 처리를 간단히 구현할 수 있습니다.', 'midas', '2025-07-10');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (38, 'IDENTITY 전략은 DB가 자동으로 키를 생성하고, SEQUENCE는 별도의 시퀀스 객체를 사용해 키를 관리합니다.', 'midas', '2025-07-11');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (37, '로그인 실패 시 Spring Security에서는 AuthenticationException을 통해 실패 원인을 확인할 수 있습니다.', 'midas', '2025-07-11');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (36, '쿠키는 HttpServletResponse의 addCookie 또는 @CookieValue로 생성 및 확인할 수 있습니다.', 'midas', '2025-07-12');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (35, 'Thymeleaf에서 #dates.format(date, "yyyy-MM-dd")를 이용해 날짜 포맷팅이 가능합니다.', 'midas', '2025-07-12');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (34, 'MyBatis에서 여러 파라미터를 전달할 땐 @Param 어노테이션을 사용하거나 Map으로 묶어 넘기면 됩니다.', 'midas', '2025-07-13');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (33, 'JSP에서 자바 변수를 자바스크립트로 넘길 때는 script 태그 내에서 <%= 변수 %> 또는 JSON 방식으로 출력합니다.', 'midas', '2025-07-13');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (32, 'JPA에서 조건 검색은 QueryDSL이나 Criteria API를 활용하면 유연하게 구현할 수 있습니다.', 'midas', '2025-07-14');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (31, 'Validation 실패 메시지는 BindingResult에 바인딩되며, fieldError.getDefaultMessage()로 출력할 수 있습니다.', 'midas', '2025-07-14');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (30, 'Spring에서는 @ControllerAdvice와 @ExceptionHandler를 활용해 전역 예외 처리를 설정할 수 있습니다.', 'midas', '2025-07-15');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (29, 'MySQL에서 시간만 비교하려면 TIME(date_column) 또는 DATE_FORMAT을 사용할 수 있습니다.', 'midas', '2025-07-15');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (28, 'Thymeleaf에서 라디오 버튼은 th:field와 th:value를 활용해 값을 바인딩할 수 있습니다.', 'midas', '2025-07-16');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (27, '폼 유효성 검사는 @Valid, @NotBlank 등의 어노테이션과 BindingResult를 함께 사용하면 됩니다.', 'midas', '2025-07-16');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (26, 'MySQL에서 AUTO_INCREMENT를 초기화하려면 ALTER TABLE 테이블명 AUTO_INCREMENT = 숫자; 를 사용하세요.', 'midas', '2025-07-17');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (25, 'JSP에서 <%@ include file="경로.jsp" %> 또는 <jsp:include>를 사용해 공통 헤더를 삽입할 수 있습니다.', 'midas', '2025-07-17');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (24, '스프링 AOP는 공통 로직(로깅, 트랜잭션 등)을 분리해 관리할 수 있는 강력한 기능입니다.', 'midas', '2025-07-18');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (23, 'LAZY는 지연 로딩, EAGER는 즉시 로딩을 의미하며, 성능 및 사용 패턴에 따라 적절히 선택해야 합니다.', 'midas', '2025-07-18');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (22, 'CORS 에러는 서버에서 @CrossOrigin 어노테이션이나 WebMvcConfigurer 설정으로 해결할 수 있습니다.', 'midas', '2025-07-19');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (21, 'Thymeleaf는 th:if, th:unless와 함께 and, or를 사용해 조건을 조합할 수 있습니다.', 'midas', '2025-07-19');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (20, 'AJAX에서 JSON 응답을 받으려면 Content-Type을 application/json으로 설정하고, JSON.parse()를 사용할 수 있습니다.', 'midas', '2025-07-20');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (19, 'JPA에서 save는 하나의 엔티티, saveAll은 리스트 형태로 여러 개의 엔티티를 저장합니다.', 'midas', '2025-07-20');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (18, 'POST 처리 후 "redirect:/경로"를 리턴하면 GET 방식으로 리디렉션할 수 있습니다.', 'midas', '2025-07-21');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (17, 'Devtools 자동 재시작이 안 될 땐 빌드 자동 설정이나 캐시 문제를 확인해보세요.', 'midas', '2025-07-22');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (16, 'Thymeleaf에서는 th:if와 th:unless로 조건문을 처리할 수 있습니다.', 'midas', '2025-07-22');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (15, '쿼리 최적화를 위해 인덱스 설정, LIMIT 사용, 조인 순서 조정 등을 고려해보세요.', 'midas', '2025-07-23');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (14, 'Spring에서 MultipartFile을 사용하면 파일 업로드를 쉽게 처리할 수 있습니다.', 'midas', '2025-07-23');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (13, 'JSP에서 JSTL을 사용하면 반복문(c:forEach), 조건문(c:if) 등을 편리하게 사용할 수 있습니다.', 'midas', '2025-07-24');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (12, '인덱스는 검색 성능을 높이지만, 쓰기 성능에 영향을 줄 수 있으므로 상황에 맞게 사용해야 합니다.', 'midas', '2025-07-24');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (11, 'Spring MVC는 DispatcherServlet을 중심으로 Controller-Service-Repository 계층이 연동되는 구조입니다.', 'midas', '2025-07-25');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (10, 'Spring Security의 remember-me 기능이나 세션 유지를 통해 로그인 상태를 유지할 수 있습니다.', 'midas', '2025-07-25');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (9, 'AJAX에서는 fetch나 XMLHttpRequest를 통해 서버에 요청하고, 비동기적으로 응답을 받을 수 있습니다.', 'midas', '2025-07-26');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (8, 'JSP에서는 session.getAttribute("속성명")으로 세션 데이터를 가져올 수 있습니다.', 'midas', '2025-07-26');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (7, 'MyBatis에서 resultMap이 작동하지 않는다면 컬럼명과 필드명이 일치하는지 확인해보세요.', 'midas', '2025-07-27');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (6, 'DTO는 계층 간 데이터 전달 시 엔티티를 직접 노출하지 않기 위해 사용되며, 유지보수성과 보안성을 높입니다.', 'midas', '2025-07-27');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (5, 'INNER JOIN은 두 테이블의 공통된 값을 기준으로, LEFT JOIN은 왼쪽 테이블의 모든 데이터를 기준으로 결과를 반환합니다.', 'midas', '2025-07-28');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (4, '스프링 시큐리티에서는 SecurityConfig에서 인증 필터와 사용자 정보를 설정해 로그인 기능을 구현할 수 있습니다.', 'midas', '2025-07-28');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (3, 'Thymeleaf에서는 th:each를 사용해 리스트 반복 출력이 가능합니다.', 'midas', '2025-07-29');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (2, 'JPA의 N+1 문제는 fetch join 또는 @EntityGraph로 해결할 수 있습니다.', 'midas', '2025-07-29');
INSERT INTO reply (bbs_no, reply_content, reply_writer, reg_date) VALUES (1, 'Spring Boot에서는 @ControllerAdvice와 @ExceptionHandler를 사용해 API 예외를 전역적으로 처리할 수 있습니다.', 'midas', '2025-07-30');




commit;
SELECT * FROM reply;