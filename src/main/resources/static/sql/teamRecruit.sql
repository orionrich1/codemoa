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
	view_count INT DEFAULT 0,
	reg_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
    
INSERT INTO recruitbbs
(user_id, contest_title, recruit_type, recruit_period, activity_period, total_members, remaining_members, progress_type, contact, tech_stack, status, apply_guide, title, content, attachment_url, view_count, reg_date, update_date)
VALUES
('user01', 'https://career.programmers.co.kr/competitions/4079', '팀원구인',
 '2025-06-01 ~ 2025-06-30', '2025-07-01 ~ 2025-10-01',
 4, 2, '온라인', 'email:user01@test.com', '#Java #Spring',
 '모집중', '포트폴리오 제출', '프로그래머스 코드챌린지 백엔드 팀원 모집',
 '프로그래머스 코드챌린지 참여할 백엔드 개발자 구합니다.', NULL, 5, NOW(), NOW()),

('user02', 'https://www.programmers.co.kr/pages/프로그래머스_코드챌린지', '팀원으로 참가희망',
 '2025-06-15 ~ 2025-07-10', '2025-07-15 ~ 2025-10-15',
 3, 1, '혼합', 'kakao:user02', '#React #TypeScript',
 '모집중', 'GitHub 링크 제출', '프론트엔드 참가 희망 – 코드챌린지',
 'React 기반 UI 구현 가능한 분 연락 부탁드려요!', NULL, 3, NOW(), NOW()),

('user03', 'https://sigmod-contest-2025.github.io/', '팀원으로 참가희망',
 '2025-02-17 ~ 2025-03-24', '2025-03-24 ~ 2025-06-30',
 5, 5, '온라인', 'email:user03@uni.edu', '#C++ #Database',
 '모집중', '이력서 및 경험 제출', 'SIGMOD 2025 DB 경진대회 참가 희망',
 '데이터베이스 파이프라인 구축 가능한 분 찾습니다.', NULL, 8, NOW(), NOW()),

('user04', 'https://www.mofa.go.kr/www/brd/m_4075/view.do?page=1&pitem=10&seq=369259', '팀원구인',
 '2025-07-01 ~ 2025-07-31', '2025-08-01 ~ 2025-09-30',
 4, 3, '온라인', 'email:user04@test.com', '#Policy #Idea',
 '모집중', '자유 제안서 제출', '2025 청년정책 공모전 팀원 모집',
 '정책 개선 아이디어를 함께 구상할 팀원을 찾습니다.', NULL, 12, NOW(), NOW()),

('user05', 'https://www.mofa.go.kr/www/brd/m_4075/view.do?page=167&pitem=10&seq=369242', '참가희망',
 '2025-05-20 ~ 2025-07-06', '2025-07-10 ~ 2025-08-31',
 3, 1, '혼합', 'kakao:user05', '#Art #UN',
 '모집중', '포스터/영상 제출', '유엔 창설 80주년 기념 공모전 참가 희망',
 '유엔 주제 작품으로 참여하실 분을 찾습니다.', NULL, 7, NOW(), NOW()),

('user06', 'https://www.all-con.co.kr/view/contest/526173?page=1&sortname=cl_order', '팀원구인',
 '2025-06-02 ~ 2025-08-10', '2025-08-15 ~ 2025-10-10',
 4, 2, '온라인', 'email:user06@test.com', '#Python #GameDev',
 '모집중', '소스 및 실행파일 제출', '청소년 게임 코딩대회 팀원 모집',
 '파이썬 기반 게임 개발 가능한 분 찾습니다.', NULL, 10, NOW(), NOW()),

('user07', 'https://onoffmix.com/event/325414?srsltid=...', '팀원구인',
 '2025-06-20 ~ 2025-07-20', '2025-08-01 ~ 2025-11-01',
 3, 1, '오프라인', 'email:user07@test.com', '#HTML #CSS #JS',
 '모집중', '메일 지원', '대학생 홈페이지 제작 공모전 팀원 모집',
 '웹 디자인/퍼블리싱 가능한 분 연락주세요.', NULL, 9, NOW(), NOW()),

('user08', 'https://www.kamco.or.kr/portal/bbs/view.do?mId=0701010000&ptIdx=380&bIdx=22906', '팀원구인',
 '2025-06-16 ~ 2025-07-15', '2025-07-20 ~ 2025-10-20',
 4, 2, '혼합', 'kakao:user08', '#AI #PublicData',
 '모집중', '이메일 지원', 'AI·공공데이터 공모전 팀원 모집',
 'AI 아이디어 함께 개발할 팀원 찾습니다.', NULL, 11, NOW(), NOW()),

('user09', 'https://sotong.go.kr/front/epilogue/epilogueNewViewPage.do?bbs_id=7da8f5dd1...', '참가희망',
 '2025-04-24 ~ 2025-05-23', '2025-05-24 ~ 2025-07-31',
 2, 0, '온라인', 'email:user09@test.com', '#Art #MediaArt',
 '모집완료', '포트폴리오 제출', '서울림 미디어아트 공모전 참가 희망',
 '미디어아트 작품으로 참여 희망합니다.', NULL, 6, NOW(), NOW()),

('user10', 'http://kitpa.org/contest/4', '팀원구인',
 '2025-02-17 ~ 2025-03-13', '2025-03-15 ~ 2025-05-31',
 5, 3, '오프라인', 'kakao:user10', '#C #Algorithms',
 '모집중', '카카오톡 지원', '청소년 IT경시대회 팀원 모집',
 'C언어/Python 알고리즘 가능자 찾습니다.', NULL, 14, NOW(), NOW()),

('user11', 'http://www.bfc.or.kr/...&board_no=10562', '팀원구인',
 '2025-06-09 ~ 2025-06-30', '2025-07-01 ~ 2025-09-30',
 4, 2, '혼합', 'email:user11@test.com', '#Storytelling #IP',
 '모집중', '이메일 지원', 'BFC 스토리 IP 공모전 팀원 모집',
 '스토리텔링 작업 경험자 찾습니다.', NULL, 8, NOW(), NOW()),

('user12', 'https://linkareer.com/activity/247420', '참가희망',
 '2025-06-02 ~ 2025-08-10', '2025-08-15 ~ 2025-10-30',
 3, 0, '온라인', 'email:user12@test.com', '#Python #GameDev',
 '모집완료', '포트폴리오 제출', 'SW GAME 코딩대회 참가 희망',
 '게임 개발 경험자 팀 참여 희망!', NULL, 13, NOW(), NOW()),

('user13', 'https://www.programmers.co.kr/pages/프로그래머스_코드챌린지', '팀원구인',
 '2025-01-20 ~ 2025-02-05', '2025-02-10 ~ 2025-05-01',
 4, 2, '온라인', 'kakao:user13', '#C++ #Go',
 '모집중', 'GitHub 링크 제출', '코드챌린지 풀스택 팀원 모집',
 '프론트+백엔드 개발 가능자 찾습니다.', NULL, 7, NOW(), NOW()),

('user14', 'https://www.wevity.com/index_university.php?c=find&gbn=viewok&gp=14&ix=96975', '팀원으로 참가희망',
 '2025-04-14 ~ 2025-06-13', '2025-06-15 ~ 2025-09-15',
 3, 1, '혼합', 'email:user14@test.com', '#Policy #Idea',
 '모집중', '자유제안서', '국민생각함 정책 아이디어 공모전 참가 희망',
 '정책 제안을 함께할 팀 찾습니다.', NULL, 10, NOW(), NOW()),

('user15', 'https://www.wevity.com/', '팀원구인',
 '2025-05-01 ~ 2025-07-01', '2025-07-05 ~ 2025-11-01',
 5, 3, '온라인', 'kakao:user15', '#DataViz #Design',
 '모집중', '이메일 지원', '광화문글판 영상 공모전 팀원 모집',
 '영상 제작 가능자 우대합니다.', NULL, 9, NOW(), NOW()),

('user16', 'https://www.wevity.com/index.php?c=find&gbn=viewok&gp=6&ix=98486', '팀원으로 참가희망',
 '2025-06-05 ~ 2025-07-05', '2025-07-10 ~ 2025-08-31',
 2, 0, '오프라인', 'email:user16@test.com', '#Cartoon #Character',
 '모집완료', '아트워크 제출', '국제 캐릭터 콘텐츠 공모전 참가 희망',
 '캐릭터 디자인 경험자 찾습니다.', NULL, 15, NOW(), NOW()),

('user17', 'https://www.wevity.com/index.php?c=find&gbn=viewok&gp=8&ix=98792', '팀원구인',
 '2025-06-16 ~ 2025-07-15', '2025-07-20 ~ 2025-10-20',
 4, 2, '혼합', 'kakao:user17', '#AI #PublicData',
 '모집중', 'GitHub 링크 제출', 'AI·공공데이터 공모전 팀원 모집',
 'AI·공공데이터 프로젝트 함께할 분 찾습니다.', NULL, 11, NOW(), NOW()),

('user18', 'https://www.contestkorea.com/sub/view.php?Txt_bcode=030510001&int_gbn=1&str_no=202412280001', '참가희망',
 '2025-02-03 ~ 2025-02-28', '2025-03-01 ~ 2025-04-30',
 3, 0, '온라인', 'email:user18@test.com', '#Roblox #GameDev',
 '모집완료', '포트폴리오 제출', '잼S 코딩 페스티발 참가 희망',
 '로블록스 코딩 경험자 팀 참여 희망합니다.', NULL, 12, NOW(), NOW()),

('user19', 'https://www.wevity.com/index.php?c=find&gbn=viewok&gp=9&ix=96445', '팀원구인',
 '2025-06-09 ~ 2025-07-30', '2025-08-01 ~ 2025-10-01',
 4, 2, '온라인', 'email:user19@test.com', '#StoryIP #Writing',
 '모집중', '이메일 지원', 'BFC 스토리 IP 공모전 팀원 모집',
 '스토리/IP 기획 경험자 찾습니다.', NULL, 14, NOW(), NOW()),

('user20', 'https://linkareer.com/activity/220644', '팀원으로 참가희망',
 '2025-01-20 ~ 2025-02-05', '2025-02-10 ~ 2025-05-01',
 3, 0, '혼합', 'email:user20@test.com', '#CodingChallenge #Dev',
 '모집완료', 'GitHub 링크', '프로그래머스 코드챌린지 참가 희망',
 '코딩 챌린지 함께할 분 찾습니다.', NULL, 16, NOW(), NOW());
 
select * from recruitbbs;
