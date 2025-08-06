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
('user01', 'https://career.programmers.co.kr/competitions/4079', 'TEAM_RECRUIT', '2025-06-01 ~ 2025-06-30', '2025-07-01 ~ 2025-10-01', 4, 2, '온라인', 'email:alice01@example.com', '#Java #SpringBoot', 'RECRUITING', '포트폴리오 제출', '프로그래머스 백엔드 팀원 모집', '함께 성장할 백엔드 개발자 구합니다. 관심있으신 분은 연락 주세요.', NULL, 23, '2025-06-02 09:15:00', '2025-06-03 12:00:00'),

('admin', 'https://www.kaggle.com/c/titanic', 'TEAM_JOIN', '2025-05-20 ~ 2025-06-20', '2025-06-21 ~ 2025-09-30', 3, 1, '혼합', 'kakao:bob02', '#Python #DataScience', 'RECRUITING', 'GitHub 링크 제출', '데이터 사이언스 경진대회 참가 희망', '데이터 분석 경험 많으신 분과 함께하고 싶어요!', NULL, 15, '2025-05-25 14:00:00', '2025-05-26 15:30:00'),

('user02', 'https://sigmod-contest-2025.github.io/', 'TEAM_JOIN', '2025-04-15 ~ 2025-05-15', '2025-05-20 ~ 2025-08-31', 5, 4, '온라인', 'email:carol03@university.edu', '#C++ #SQL', 'RECRUITING', '이력서 제출', 'SIGMOD DB 경진대회 참가 희망', 'DB 설계 및 최적화 경험자 구합니다.', NULL, 12, '2025-04-20 10:30:00', '2025-04-21 11:00:00'),

('user03', 'https://www.mofa.go.kr/www/brd/m_4075/view.do?seq=369259', 'TEAM_RECRUIT', '2025-07-01 ~ 2025-07-31', '2025-08-01 ~ 2025-09-30', 4, 3, '온라인', 'email:daniel04@test.com', '#Policy #IdeaGeneration', 'RECRUITING', '자유 제안서 제출', '청년 정책 아이디어 공모전 팀원 모집', '정책 개선 아이디어를 함께 고민할 분을 찾습니다.', NULL, 18, '2025-07-02 09:00:00', '2025-07-03 10:00:00'),

('user05', 'https://www.unesco.org/en/creativity/arts-contests', 'TEAM_JOIN', '2025-05-10 ~ 2025-06-15', '2025-06-20 ~ 2025-08-31', 3, 1, '혼합', 'kakao:ellen05', '#Art #UNESCO', 'RECRUITING', '포스터 제출', '유엔 문화 공모전 참가 희망', '참신한 아이디어와 아트워크 경험자 환영합니다.', NULL, 9, '2025-05-11 13:00:00', '2025-05-12 14:20:00'),

('user07', 'https://all-con.co.kr/view/contest/526173', 'TEAM_RECRUIT', '2025-06-02 ~ 2025-08-10', '2025-08-15 ~ 2025-10-10', 4, 2, '온라인', 'email:frank06@example.com', '#Python #GameDevelopment', 'RECRUITING', '소스 제출', '청소년 게임 개발 대회 팀원 모집', '파이썬으로 게임 개발 가능한 분 찾습니다.', NULL, 20, '2025-06-05 16:30:00', '2025-06-06 17:00:00'),

('user08', 'https://onoffmix.com/event/325414', 'TEAM_RECRUIT', '2025-06-20 ~ 2025-07-20', '2025-08-01 ~ 2025-11-01', 3, 1, '오프라인', 'email:grace07@test.com', '#HTML #CSS #JavaScript', 'RECRUITING', '메일 지원', '대학생 홈페이지 제작 공모전 팀원 모집', '웹 디자인 및 프론트엔드 개발 경험자 구합니다.', NULL, 17, '2025-06-21 10:00:00', '2025-06-22 11:15:00'),

('user04', 'https://kamco.or.kr/portal/bbs/view.do?mId=0701010000&ptIdx=380&bIdx=22906', 'TEAM_RECRUIT', '2025-06-16 ~ 2025-07-15', '2025-07-20 ~ 2025-10-20', 4, 2, '혼합', 'kakao:henry08', '#AI #PublicData', 'RECRUITING', '이메일 지원', 'AI 공공 데이터 공모전 팀원 모집', 'AI 프로젝트 경험자, 데이터 분석 가능자 구합니다.', NULL, 22, '2025-06-17 09:45:00', '2025-06-18 10:30:00'),

('user09', 'https://sotong.go.kr/front/epilogue/epilogueNewViewPage.do?bbs_id=7da8f5dd1...', 'TEAM_JOIN', '2025-04-24 ~ 2025-05-23', '2025-05-24 ~ 2025-07-31', 2, 0, '온라인', 'email:irene09@test.com', '#Art #MediaArt', 'COMPLETED', '포트폴리오 제출', '미디어아트 공모전 참가 희망', '미디어아트 작품 제작 경험자 구합니다.', NULL, 13, '2025-04-25 14:00:00', '2025-05-01 15:00:00'),

('user06', 'http://kitpa.org/contest/4', 'TEAM_RECRUIT', '2025-02-17 ~ 2025-03-13', '2025-03-15 ~ 2025-05-31', 5, 3, '오프라인', 'kakao:jack10', '#C #Algorithms', 'RECRUITING', '카카오톡 지원', '청소년 IT 경시대회 팀원 모집', '알고리즘 개발 경험자 모집합니다.', NULL, 19, '2025-02-18 11:00:00', '2025-02-19 12:30:00'),

('user10', 'http://www.bfc.or.kr/...&board_no=10562', 'TEAM_RECRUIT', '2025-06-09 ~ 2025-06-30', '2025-07-01 ~ 2025-09-30', 4, 2, '혼합', 'email:kate11@test.com', '#Storytelling #IP', 'RECRUITING', '이메일 지원', '스토리 IP 공모전 팀원 모집', '스토리텔링 경험자, 기획자 우대합니다.', NULL, 11, '2025-06-10 13:00:00', '2025-06-11 13:30:00'),

('user11', 'https://linkareer.com/activity/247420', 'TEAM_JOIN', '2025-06-02 ~ 2025-08-10', '2025-08-15 ~ 2025-10-30', 3, 0, '온라인', 'email:leo12@test.com', '#Python #GameDev', 'COMPLETED', '포트폴리오 제출', '게임 코딩대회 참가 희망', '게임 개발 경험자 구합니다.', NULL, 14, '2025-06-03 15:00:00', '2025-06-04 15:30:00'),

('amdin', 'https://www.programmers.co.kr/pages/프로그래머스_코드챌린지', 'TEAM_RECRUIT', '2025-01-20 ~ 2025-02-05', '2025-02-10 ~ 2025-05-01', 4, 2, '온라인', 'kakao:mia13', '#C++ #Go', 'RECRUITING', 'GitHub 링크 제출', '코드챌린지 풀스택 팀원 모집', '프론트 및 백엔드 개발 가능자 모집합니다.', NULL, 16, '2025-01-21 10:00:00', '2025-01-22 10:30:00'),

('user02', 'https://www.wevity.com/index_university.php?c=find&gbn=viewok&gp=14&ix=96975', 'TEAM_JOIN', '2025-04-14 ~ 2025-06-13', '2025-06-15 ~ 2025-09-15', 3, 1, '혼합', 'email:nina14@test.com', '#Policy #Idea', 'RECRUITING', '자유 제안서', '정책 아이디어 공모전 참가 희망', '정책 제안 경험자 우대합니다.', NULL, 13, '2025-04-15 09:00:00', '2025-04-16 09:30:00'),

('user06', 'https://www.wevity.com/', 'TEAM_RECRUIT', '2025-05-01 ~ 2025-07-01', '2025-07-05 ~ 2025-11-01', 5, 3, '온라인', 'kakao:oliver15', '#DataViz #Design', 'RECRUITING', '이메일 지원', '영상 공모전 팀원 모집', '영상 제작 가능한 분 환영합니다.', NULL, 18, '2025-05-02 14:30:00', '2025-05-03 15:00:00'),

('user07', 'https://www.wevity.com/index.php?c=find&gbn=viewok&gp=6&ix=98486', 'TEAM_JOIN', '2025-06-05 ~ 2025-07-05', '2025-07-10 ~ 2025-08-31', 2, 0, '오프라인', 'email:paul16@test.com', '#Cartoon #Character', 'COMPLETED', '아트워크 제출', '캐릭터 콘텐츠 공모전 참가 희망', '캐릭터 디자인 경험자 구합니다.', NULL, 21, '2025-06-06 10:00:00', '2025-06-07 10:30:00'),

('user01', 'https://www.wevity.com/index.php?c=find&gbn=viewok&gp=8&ix=98792', 'TEAM_RECRUIT', '2025-06-16 ~ 2025-07-15', '2025-07-20 ~ 2025-10-20', 4, 2, '혼합', 'kakao:quinn17', '#AI #PublicData', 'RECRUITING', 'GitHub 링크 제출', 'AI 공모전 팀원 모집', 'AI 프로젝트 경험자 구합니다.', NULL, 22, '2025-06-17 11:00:00', '2025-06-18 12:00:00'),

('user04', 'https://www.contestkorea.com/sub/view.php?Txt_bcode=030510001&int_gbn=1&str_no=202412280001', 'TEAM_JOIN', '2025-02-03 ~ 2025-02-28', '2025-03-01 ~ 2025-04-30', 3, 0, '온라인', 'email:rachel18@test.com', '#Roblox #GameDev', 'COMPLETED', '포트폴리오 제출', '잼S 코딩 페스티벌 참가 희망', '로블록스 게임 개발 경험자 모집.', NULL, 15, '2025-02-05 13:00:00', '2025-02-06 13:30:00'),

('user03', 'https://www.wevity.com/index.php?c=find&gbn=viewok&gp=9&ix=96445', 'TEAM_RECRUIT', '2025-06-09 ~ 2025-07-30', '2025-08-01 ~ 2025-10-01', 4, 2, '온라인', 'email:sam19@test.com', '#StoryIP #Writing', 'RECRUITING', '이메일 지원', '스토리 IP 공모전 팀원 모집', '스토리텔링 및 IP 기획 경험자 모집합니다.', NULL, 20, '2025-06-10 09:00:00', '2025-06-11 09:30:00'),

('user05', 'https://linkareer.com/activity/220644', 'TEAM_JOIN', '2025-01-20 ~ 2025-02-05', '2025-02-10 ~ 2025-05-01', 3, 0, '혼합', 'email:tom20@test.com', '#CodingChallenge #Dev', 'COMPLETED', 'GitHub 링크 제출', '프로그래머스 코드챌린지 참가 희망', '코딩 챌린지 경험자 구합니다.', NULL, 18, '2025-01-22 14:00:00', '2025-01-23 15:00:00');

UPDATE recruitbbs 
SET recruit_type = 'TEAM_RECRUIT' 
WHERE recruit_type = '팀원구인';

UPDATE recruitbbs 
SET recruit_type = 'TEAM_JOIN' 
WHERE recruit_type IN ('참가 희망', '팀원으로 참가희망', '참가희망');

 
 UPDATE recruitbbs
 SET status = 'RECRUITING'
 WHERE status = '모집중';
 
 UPDATE recruitbbs
 SET status = 'COMPLETED'
 WHERE status = '모집완료';
 
select * from recruitbbs;
