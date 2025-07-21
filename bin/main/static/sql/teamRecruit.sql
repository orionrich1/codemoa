DROP TABLE IF EXISTS recruitbbs;

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
	view_count iNT DEFAULT 0,
	reg_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
    
INSERT INTO recruitbbs
(user_id, contest_title, recruit_type, recruit_period, activity_period, total_members, remaining_members, progress_type, contact, tech_stack, status, apply_guide, title, content, attachment_url, view_count, reg_date, update_date)
VALUES
('user01', 'https://www.k-startup.go.kr/web/contents/bizpbanc-ongoing.do', '팀원구인', '2025-07-01 ~ 2025-07-31', '2025-08-01 ~ 2025-12-31',
 5, 2, '온라인', 'email:user01@test.com', '#Java #Spring #MySQL', '모집중', '이메일로 간단한 자기소개서 제출', 'AI 공모전 팀원 모집', 'AI 공모전 함께할 팀원을 모집합니다.', NULL, 12, NOW(), NOW()),

('user02', 'https://www.k-startup.go.kr/web/contents/bizpbanc-ongoing.do', '팀원구인', '2025-07-05 ~ 2025-07-20', '2025-08-01 ~ 2025-10-30',
 4, 1, '오프라인', 'kakao:user02', '#Python #Django', '모집중', '카카오톡으로 포트폴리오 전송', '블록체인 해커톤 팀 모집', '블록체인 기반 프로젝트 팀원 구합니다.', NULL, 9, NOW(), NOW()),

('user03', 'https://www.k-startup.go.kr/web/contents/bizpbanc-ongoing.do', '참가희망', '2025-07-02 ~ 2025-07-15', '2025-07-20 ~ 2025-09-15',
 3, 0, '혼합', 'phone:010-1234-5678', '#React #Node.js', '모집완료', '전화로 간단한 인터뷰', '스타트업 아이디어톤 참가희망', '열정적인 팀을 찾고 있습니다.', NULL, 15, NOW(), NOW()),

('user04', 'https://www.k-startup.go.kr/web/contents/bizpbanc-ongoing.do', '팀원구인', '2025-07-10 ~ 2025-07-30', '2025-08-05 ~ 2025-12-15',
 6, 3, '온라인', 'email:user04@test.com', '#Vue #SpringBoot', '모집중', '이메일로 지원서 제출', 'IT 해커톤 팀원 모집', '웹 프론트엔드 가능한 분 우대.', NULL, 22, NOW(), NOW()),

('user05', 'https://www.k-startup.go.kr/web/contents/bizpbanc-ongoing.do', '참가희망', '2025-07-03 ~ 2025-07-25', '2025-08-01 ~ 2025-11-20',
 4, 0, '오프라인', 'email:user05@test.com', '#Flutter #Firebase', '모집완료', '포트폴리오 첨부', '모바일 앱 공모전 참가희망', '앱 개발 프로젝트 참가 희망.', NULL, 18, NOW(), NOW()),

('user06', 'https://www.k-startup.go.kr/web/contents/bizpbanc-ongoing.do', '팀원구인', '2025-07-01 ~ 2025-07-28', '2025-08-05 ~ 2025-10-31',
 5, 2, '혼합', 'kakao:user06', '#AWS #Docker', '모집중', '카카오톡 연락', '클라우드 해커톤 팀원 모집', 'DevOps 경험자 우대.', NULL, 7, NOW(), NOW()),

('user07', 'https://www.k-startup.go.kr/web/contents/bizpbanc-ongoing.do', '팀원구인', '2025-07-08 ~ 2025-07-27', '2025-08-01 ~ 2025-09-30',
 4, 2, '온라인', 'phone:010-5678-1234', '#JavaScript #Next.js', '모집중', '전화 인터뷰', '웹앱 개발 공모전 팀원 모집', '프론트엔드 개발자 우대.', NULL, 11, NOW(), NOW()),

('user08', 'https://www.k-startup.go.kr/web/contents/bizpbanc-ongoing.do', '참가희망', '2025-07-02 ~ 2025-07-18', '2025-08-01 ~ 2025-09-15',
 3, 0, '오프라인', 'email:user08@test.com', '#Java #JPA', '모집완료', '메일 지원', 'AI 해커톤 참가 희망', 'AI 관련 프로젝트 팀을 찾습니다.', NULL, 14, NOW(), NOW()),

('user09', 'https://www.k-startup.go.kr/web/contents/bizpbanc-ongoing.do', '팀원구인', '2025-07-04 ~ 2025-07-21', '2025-08-01 ~ 2025-11-15',
 6, 4, '혼합', 'kakao:user09', '#C++ #Unreal', '모집중', '카카오톡 포트폴리오', '게임 개발 공모전 팀원 모집', '3D 게임 제작 경험자 우대.', NULL, 8, NOW(), NOW()),

('user10', 'https://www.k-startup.go.kr/web/contents/bizpbanc-ongoing.do', '참가희망', '2025-07-05 ~ 2025-07-22', '2025-08-01 ~ 2025-09-30',
 2, 0, '온라인', 'email:user10@test.com', '#Kotlin #Android', '모집완료', '이메일 포트폴리오 제출', '앱 해커톤 참가 희망', '앱 개발 경력자와 함께 하고 싶습니다.', NULL, 19, NOW(), NOW()),

('user11', 'https://www.k-startup.go.kr/web/contents/bizpbanc-ongoing.do', '팀원구인', '2025-07-06 ~ 2025-07-25', '2025-08-01 ~ 2025-10-20',
 5, 2, '오프라인', 'email:user11@test.com', '#Node.js #MongoDB', '모집중', '메일 지원', '풀스택 프로젝트 팀원 모집', '풀스택 개발자 찾습니다.', NULL, 16, NOW(), NOW()),

('user12', 'https://www.k-startup.go.kr/web/contents/bizpbanc-ongoing.do', '팀원구인', '2025-07-02 ~ 2025-07-30', '2025-08-01 ~ 2025-12-15',
 6, 3, '온라인', 'kakao:user12', '#Go #Kubernetes', '모집중', '카톡 지원', '클라우드 공모전 팀원 모집', '쿠버네티스 경험자 우대.', NULL, 5, NOW(), NOW()),

('user13', 'https://www.k-startup.go.kr/web/contents/bizpbanc-ongoing.do', '참가희망', '2025-07-08 ~ 2025-07-19', '2025-08-01 ~ 2025-09-30',
 4, 0, '혼합', 'phone:010-4321-5678', '#Python #ML', '모집완료', '전화 인터뷰', '머신러닝 공모전 참가 희망', 'ML 경험 팀을 찾고 있습니다.', NULL, 20, NOW(), NOW()),

('user14', 'https://www.k-startup.go.kr/web/contents/bizpbanc-ongoing.do', '팀원구인', '2025-07-07 ~ 2025-07-26', '2025-08-01 ~ 2025-11-10',
 3, 1, '오프라인', 'email:user14@test.com', '#ReactNative #Node.js', '모집중', '메일 지원', '모바일 앱 해커톤 팀원 모집', 'React Native 경험자 환영.', NULL, 13, NOW(), NOW()),

('user15', 'https://www.k-startup.go.kr/web/contents/bizpbanc-ongoing.do', '팀원구인', '2025-07-04 ~ 2025-07-31', '2025-08-01 ~ 2025-12-31',
 5, 2, '혼합', 'kakao:user15', '#SpringBoot #MyBatis', '모집중', '카카오톡 지원', '웹 서비스 공모전 팀원 모집', 'Spring Boot 가능자 우대.', NULL, 10, NOW(), NOW()),

('user16', 'https://www.k-startup.go.kr/web/contents/bizpbanc-ongoing.do', '참가희망', '2025-07-03 ~ 2025-07-22', '2025-08-01 ~ 2025-10-31',
 4, 0, '온라인', 'email:user16@test.com', '#PHP #Laravel', '모집완료', '메일 인터뷰', '백엔드 개발 공모전 참가 희망', '백엔드 개발 가능 팀 찾습니다.', NULL, 6, NOW(), NOW()),

('user17', 'https://www.k-startup.go.kr/web/contents/bizpbanc-ongoing.do', '팀원구인', '2025-07-09 ~ 2025-07-27', '2025-08-01 ~ 2025-09-30',
 3, 1, '오프라인', 'kakao:user17', '#Rust #WebAssembly', '모집중', '카톡 포트폴리오', '신기술 해커톤 팀원 모집', 'Rust 경험자 환영.', NULL, 14, NOW(), NOW()),

('user18', 'https://www.k-startup.go.kr/web/contents/bizpbanc-ongoing.do', '팀원구인', '2025-07-05 ~ 2025-07-21', '2025-08-01 ~ 2025-11-01',
 6, 2, '온라인', 'email:user18@test.com', '#TensorFlow #Keras', '모집중', '메일 지원', 'AI 개발 공모전 팀원 모집', '딥러닝 경험자 찾습니다.', NULL, 17, NOW(), NOW()),

('user19', 'https://www.k-startup.go.kr/web/contents/bizpbanc-ongoing.do', '참가희망', '2025-07-06 ~ 2025-07-20', '2025-08-01 ~ 2025-10-25',
 2, 0, '혼합', 'phone:010-8765-4321', '#Swift #iOS', '모집완료', '전화 지원', 'iOS 앱 공모전 참가 희망', 'iOS 앱 개발 팀을 찾습니다.', NULL, 21, NOW(), NOW()),

('user20', 'https://www.k-startup.go.kr/web/contents/bizpbanc-ongoing.do', '팀원구인', '2025-07-08 ~ 2025-07-29', '2025-08-01 ~ 2025-12-15',
 5, 2, '오프라인', 'email:user20@test.com', '#C# #Unity', '모집중', '메일 포트폴리오', '게임 해커톤 팀원 모집', 'Unity 개발자 환영.', NULL, 12, NOW(), NOW());   
    
select * from recruitbbs;
