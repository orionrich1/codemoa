INSERT INTO user (user_id, name, nickname, mobile, email, total_points, membership_date, unban_date, grade) VALUES
('admin', '관리자', 'Admin', '01000000000', 'admin@codemoa.com', 999999, NOW() - INTERVAL 10 DAY, NOW(), 'ADMIN'),
('user01', '김브론즈', '브론즈맨', '01011110001', 'user01@test.com', 500, NOW() - INTERVAL 8 DAY, NOW(), 'BRONZE'),
('user02', '박브론즈', '나는브론즈', '01011110002', 'user02@test.com', 1500, NOW() - INTERVAL 7 DAY, NOW(), 'BRONZE'),
('user03', '이실버', '실버스타', '01011110003', 'user03@test.com', 2500, NOW() - INTERVAL 9 DAY, NOW(), 'SILVER'),
('user04', '최실버', '실버서퍼', '01011110004', 'user04@test.com', 4800, NOW() - INTERVAL 5 DAY, NOW(), 'SILVER'),
('user05', '정골드', '골드러시', '01011110005', 'user05@test.com', 6000, NOW() - INTERVAL 7 DAY, NOW(), 'GOLD'),
('user06', '강골드', '황금인생', '01011110006', 'user06@test.com', 9500, NOW() - INTERVAL 3 DAY, NOW(), 'GOLD'),
('user07', '조플래', '플래티넘맨', '01011110007', 'user07@test.com', 12000, NOW() - INTERVAL 6 DAY, NOW(), 'PLATINUM'),
('user08', '윤플래', '백금전사', '01011110008', 'user08@test.com', 18000, NOW() - INTERVAL 2 DAY, NOW(), 'PLATINUM'),
('user09', '장다이아', '다이아손', '01011110009', 'user09@test.com', 25000, NOW() - INTERVAL 8 DAY, NOW(), 'DIAMOND'),
('user10', '임다이아', '빛나는다이아', '01011110010', 'user10@test.com', 45000, NOW() - INTERVAL 1 DAY, NOW(), 'DIAMOND'),
('user11', '구글유저', 'Gooogler', '01011110011', 'user11@google.com', 1000, NOW(), NOW(), 'BRONZE');

INSERT INTO local_user (user_id, pass) VALUES
('admin', 1),
('user01', 1),
('user02', 1),
('user03', 1),
('user04', 1),
('user05', 1),
('user06', 1),
('user07', 1),
('user08', 1),
('user09', 1),
('user10', 1),
('user11', 1);

UPDATE user SET user_position='ADMIN' WHERE user_id='admin';

INSERT INTO recruitbbs (user_id, contest_title, recruit_type, recruit_period, activity_period, total_members, remaining_members, progress_type, contact, tech_stack, status, apply_guide, title, content, attachment_url, view_count, reg_date, update_date)
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

INSERT INTO community_board (user_id, title, content, category, post_type, created_at) VALUES
('user01', '오늘 날씨 너무 좋네요', '햇살이 따뜻해서 기분 좋은 하루입니다.', '자유', 'NORMAL', NOW() - INTERVAL 1 DAY),
('user02', '최근 본 영화 추천해 주세요', '주말에 볼만한 영화 추천 부탁드립니다.', '자유', 'NORMAL', NOW() - INTERVAL 2 DAY),
('user03', '요즘 읽는 책 소개합니다', '최근에 재미있게 본 소설 한 권 추천해요.', '자유', 'NORMAL', NOW() - INTERVAL 3 DAY),
('user04', '코딩 공부 어떻게 하나요?', '처음 배우는 사람에게 추천할 만한 방법 있나요?', '자유', 'NORMAL', NOW() - INTERVAL 4 DAY),
('user05', '주말에 뭐하세요?', '다들 주말 계획은 어떻게 되시나요?', '자유', 'NORMAL', NOW() - INTERVAL 5 DAY),
('user06', '좋아하는 음악 공유해요', '요즘 듣는 노래 중 좋은 곡 있으면 알려주세요.', '자유', 'NORMAL', NOW() - INTERVAL 6 DAY),
('user07', '취미 생활이 뭐에요?', '새로운 취미를 찾고 있는데 추천 부탁합니다.', '자유', 'NORMAL', NOW() - INTERVAL 7 DAY),
('user08', '운동 시작하려고 해요', '효과 좋은 운동법 알려주세요!', '자유', 'NORMAL', NOW() - INTERVAL 8 DAY),
('user09', '커피 좋아하시는 분?', '좋은 카페 추천해주실 분 계신가요?', '자유', 'NORMAL', NOW() - INTERVAL 9 DAY),
('user10', '여름 휴가 어디로 가세요?', '올해 휴가지 추천받습니다.', '자유', 'NORMAL', NOW() - INTERVAL 10 DAY),
('admin', '서버 점검 예정 안내', '다음 주 월요일 새벽 2시부터 4시까지 서버 점검이 있습니다.', '자유', 'NORMAL', NOW() - INTERVAL 11 DAY),
('user01', '좋은 맛집 추천해주세요', '서울 근처 가성비 좋은 맛집 있나요?', '자유', 'NORMAL', NOW() - INTERVAL 12 DAY),
('user02', '주말 등산 갈 사람?', '이번 주말에 등산 같이 가실 분 구해요.', '자유', 'NORMAL', NOW() - INTERVAL 13 DAY),
('user03', '영어 공부법 공유', '효과적인 영어 공부 방법 알려드려요.', '자유', 'NORMAL', NOW() - INTERVAL 14 DAY),
('user04', '드라마 추천해 주세요', '최근 재미있게 본 드라마 있으신가요?', '자유', 'NORMAL', NOW() - INTERVAL 15 DAY),
('user05', '새로운 취미 시작했어요', '뜨개질 배우기 시작했습니다!', '자유', 'NORMAL', NOW() - INTERVAL 16 DAY),
('user06', '건강 관리 팁 공유', '요즘 실천하는 건강 관리 방법 소개합니다.', '자유', 'NORMAL', NOW() - INTERVAL 17 DAY),
('user07', '영화관 가본지 오래됐네요', '요즘 좋은 영화 상영 중인가요?', '자유', 'NORMAL', NOW() - INTERVAL 18 DAY),
('user08', '온라인 강의 추천', '유용한 무료 온라인 강의 있으면 알려주세요.', '자유', 'NORMAL', NOW() - INTERVAL 19 DAY),
('user09', '여행 사진 공유', '최근 다녀온 여행 사진 올려봅니다.', '자유', 'NORMAL', NOW() - INTERVAL 20 DAY),
('user10', '코로나 예방 수칙', '최근 코로나 관련 정보 공유해요.', '자유', 'NORMAL', NOW() - INTERVAL 21 DAY),
('admin', '신규 기능 안내', '새로운 게시판 기능이 추가되었습니다.', '자유', 'NORMAL', NOW() - INTERVAL 22 DAY),
('user01', '반려동물 이야기', '강아지 키우시는 분 있나요?', '자유', 'NORMAL', NOW() - INTERVAL 23 DAY),
('user02', '요리 초보 질문', '간단한 요리 레시피 추천 부탁해요.', '자유', 'NORMAL', NOW() - INTERVAL 24 DAY),
('user03', '사진 촬영 팁 공유', '좋은 사진 찍는 법 알려드립니다.', '자유', 'NORMAL', NOW() - INTERVAL 25 DAY),
('user04', '취업 준비 어떻게 하나요?', '효과적인 취업 준비 방법 공유해주세요.', '자유', 'NORMAL', NOW() - INTERVAL 26 DAY),
('user05', '새해 계획 세우기', '올해 목표 어떻게 잡으셨나요?', '자유', 'NORMAL', NOW() - INTERVAL 27 DAY),
('user06', '운동화 추천 부탁', '러닝화 추천 받고 싶어요.', '자유', 'NORMAL', NOW() - INTERVAL 28 DAY),
('user07', '책 읽는 시간 확보', '바쁜 와중에 책 읽는 시간 어떻게 만드나요?', '자유', 'NORMAL', NOW() - INTERVAL 29 DAY),
('user08', '커피 머신 고장났어요', '고장 수리 경험 공유해주세요.', '자유', 'NORMAL', NOW() - INTERVAL 30 DAY),
('user09', '좋은 영화 OST', '영화 음악 추천 부탁드립니다.', '자유', 'NORMAL', NOW() - INTERVAL 31 DAY),
('user10', '드론 촬영 시작했어요', '초보 드론 촬영 팁 알려주세요.', '자유', 'NORMAL', NOW() - INTERVAL 32 DAY),
('admin', '공지사항: 사이트 이용 규칙', '커뮤니티 이용 시 지켜야 할 규칙 안내입니다.', '자유', 'NORMAL', NOW() - INTERVAL 33 DAY),
('user01', '재택근무 장단점', '재택근무 하면서 느낀 점 공유합니다.', '자유', 'NORMAL', NOW() - INTERVAL 34 DAY),
('user02', '맛집 탐방 후기', '최근 다녀온 맛집 후기 올려요.', '자유', 'NORMAL', NOW() - INTERVAL 35 DAY),
('user03', '웹툰 추천', '재밌게 본 웹툰 소개합니다.', '자유', 'NORMAL', NOW() - INTERVAL 36 DAY),
('user04', '사진 편집 프로그램', '무료 사진 편집 프로그램 추천해 주세요.', '자유', 'NORMAL', NOW() - INTERVAL 37 DAY),
('user05', '기술 블로그 운영 중', '블로그 운영 경험 공유합니다.', '자유', 'NORMAL', NOW() - INTERVAL 38 DAY),
('user06', 'IT 자격증 공부', 'IT 자격증 준비하시는 분 계신가요?', '자유', 'NORMAL', NOW() - INTERVAL 39 DAY),
('user07', '영화관람 팁', '할인 받는 방법 공유합니다.', '자유', 'NORMAL', NOW() - INTERVAL 40 DAY);

INSERT INTO community_board (user_id, title, content, category, post_type, staked_points, created_at) VALUES
('user01', 'Java 8 스트림 사용법 질문', '스트림에서 map과 flatMap의 차이가 궁금합니다.', 'Java', 'QUESTION', 30, NOW() - INTERVAL 1 DAY),
('user02', '스프링 부트 프로젝트 셋업 팁', '효율적으로 프로젝트를 시작하는 방법 공유합니다.', 'Java', 'NORMAL', 0, NOW() - INTERVAL 2 DAY),
('user03', 'Java 컬렉션 프레임워크 질문', 'HashMap과 TreeMap의 내부 구조가 어떻게 다른가요?', 'Java', 'QUESTION', 50, NOW() - INTERVAL 3 DAY),
('user04', 'Java에서 메모리 누수 해결법', 'Garbage Collector 작동 방식과 최적화 팁', 'Java', 'NORMAL', 0, NOW() - INTERVAL 4 DAY),
('user05', '스프링 AOP 사용 경험 공유', 'AOP로 로깅 처리하는 방법 설명합니다.', 'Java', 'NORMAL', 0, NOW() - INTERVAL 5 DAY),
('user06', 'Java에서 멀티스레딩 질문', 'synchronized와 ReentrantLock 차이점이 궁금합니다.', 'Java', 'QUESTION', 100, NOW() - INTERVAL 6 DAY),
('user07', 'Java 11 새로운 기능 소개', 'var 키워드와 HTTP 클라이언트', 'Java', 'NORMAL', 0, NOW() - INTERVAL 7 DAY),
('user08', '예외 처리 best practice', 'Checked Exception과 Unchecked Exception 차이', 'Java', 'NORMAL', 0, NOW() - INTERVAL 8 DAY),
('user09', 'Java 람다식 이해하기', '람다식 작성법과 활용 사례', 'Java', 'NORMAL', 0, NOW() - INTERVAL 9 DAY),
('user10', 'Java GC 튜닝 팁', 'GC 로그 분석과 성능 개선 방법', 'Java', 'NORMAL', 0, NOW() - INTERVAL 10 DAY),
('admin', 'Java와 Kotlin 비교 질문', '두 언어 중 어떤 점이 더 좋은가요?', 'Java', 'QUESTION', 10, NOW() - INTERVAL 11 DAY),
('user01', 'Java 14 switch 문법 질문', '새로운 switch 표현식 사용법 알려주세요.', 'Java', 'QUESTION', 30, NOW() - INTERVAL 12 DAY),
('user02', 'Java 웹 애플리케이션 배포 팁', 'Tomcat 배포 시 주의사항 공유', 'Java', 'NORMAL', 0, NOW() - INTERVAL 13 DAY),
('user03', 'JPA 성능 최적화', 'Lazy Loading 문제 해결 방법', 'Java', 'NORMAL', 0, NOW() - INTERVAL 14 DAY),
('user04', 'Java 프로젝트 빌드 도구 추천', 'Maven과 Gradle 중 뭐가 더 좋나요?', 'Java', 'NORMAL', 0, NOW() - INTERVAL 15 DAY),

('user05', 'Python 리스트 컴프리헨션 질문', '복잡한 리스트 컴프리헨션 어떻게 작성하나요?', 'Python', 'QUESTION', 30, NOW() - INTERVAL 16 DAY),
('user06', '파이썬으로 데이터 분석 시작하기', 'Pandas와 NumPy 기본 사용법 소개', 'Python', 'NORMAL', 0, NOW() - INTERVAL 17 DAY),
('user07', '파이썬 예외 처리 질문', 'try-except 문에서 여러 예외를 처리하는 방법?', 'Python', 'QUESTION', 50, NOW() - INTERVAL 18 DAY),
('user08', 'Flask vs Django 비교', '작은 프로젝트에 적합한 프레임워크는?', 'Python', 'NORMAL', 0, NOW() - INTERVAL 19 DAY),
('user09', '파이썬 멀티스레딩 질문', 'GIL이 무엇이고 어떻게 극복하나요?', 'Python', 'QUESTION', 100, NOW() - INTERVAL 20 DAY),
('user10', '파이썬 웹 크롤링 팁', 'BeautifulSoup을 활용한 데이터 추출 방법', 'Python', 'NORMAL', 0, NOW() - INTERVAL 21 DAY),
('admin', 'Python 타입 힌트 사용법', '코드 가독성 향상을 위한 타입 힌트 설명', 'Python', 'NORMAL', 0, NOW() - INTERVAL 22 DAY),
('user01', '파이썬 람다 함수 질문', '람다 함수와 일반 함수 차이가 뭔가요?', 'Python', 'QUESTION', 10, NOW() - INTERVAL 23 DAY),
('user02', '파이썬 가상환경 만들기', 'venv 사용법과 활용 팁', 'Python', 'NORMAL', 0, NOW() - INTERVAL 24 DAY),
('user03', '파이썬에서 데이터 시각화', 'Matplotlib과 Seaborn 사용법', 'Python', 'NORMAL', 0, NOW() - INTERVAL 25 DAY),
('user04', '파이썬 문자열 처리 질문', '문자열 포매팅 방법 중 추천은?', 'Python', 'QUESTION', 30, NOW() - INTERVAL 26 DAY),
('user05', '파이썬 스크립트 자동화', 'schedule 라이브러리 활용법', 'Python', 'NORMAL', 0, NOW() - INTERVAL 27 DAY),
('user06', '파이썬에서 JSON 다루기', 'json 모듈 사용법 소개', 'Python', 'NORMAL', 0, NOW() - INTERVAL 28 DAY),
('user07', '파이썬 비동기 처리 질문', 'asyncio 기본 사용법 알려주세요.', 'Python', 'QUESTION', 50, NOW() - INTERVAL 29 DAY),
('user08', '파이썬 패키지 배포', 'PyPI에 패키지 등록 방법', 'Python', 'NORMAL', 0, NOW() - INTERVAL 30 DAY),

('user09', '자바스크립트 클로저 질문', '클로저의 원리와 사용법 궁금합니다.', 'JavaScript', 'QUESTION', 30, NOW() - INTERVAL 31 DAY),
('user10', 'React 기본 개념 소개', '컴포넌트와 상태 관리', 'JavaScript', 'NORMAL', 0, NOW() - INTERVAL 32 DAY),
('admin', 'JavaScript 비동기 처리 질문', 'Promise와 async/await 차이점', 'JavaScript', 'QUESTION', 50, NOW() - INTERVAL 33 DAY),
('user01', 'Node.js 서버 구축 팁', 'Express를 이용한 간단 서버 만들기', 'JavaScript', 'NORMAL', 0, NOW() - INTERVAL 34 DAY),
('user02', 'JavaScript 이벤트 버블링 질문', '이벤트 캡처링과 버블링 차이점', 'JavaScript', 'QUESTION', 100, NOW() - INTERVAL 35 DAY),
('user03', 'Vue.js 사용 경험 공유', '초보자가 배우기 좋은 프레임워크', 'JavaScript', 'NORMAL', 0, NOW() - INTERVAL 36 DAY),
('user04', 'JavaScript 배열 메서드 질문', 'map, filter, reduce 사용법', 'JavaScript', 'QUESTION', 10, NOW() - INTERVAL 37 DAY),
('user05', '프론트엔드 성능 최적화 팁', '렌더링 속도 개선 방법', 'JavaScript', 'NORMAL', 0, NOW() - INTERVAL 38 DAY),
('user06', 'JavaScript 모듈 시스템', 'ES6 import/export 기본', 'JavaScript', 'NORMAL', 0, NOW() - INTERVAL 39 DAY),
('user07', '자바스크립트 this 키워드 질문', 'this가 가리키는 대상', 'JavaScript', 'QUESTION', 30, NOW() - INTERVAL 40 DAY),
('user08', 'React 훅 사용법', 'useState와 useEffect 활용', 'JavaScript', 'NORMAL', 0, NOW() - INTERVAL 41 DAY),
('user09', 'JavaScript 디버깅 팁', '크롬 개발자 도구 활용법', 'JavaScript', 'NORMAL', 0, NOW() - INTERVAL 42 DAY),
('user10', '자바스크립트 타입 변환 질문', '암묵적 형 변환 문제 해결법', 'JavaScript', 'QUESTION', 50, NOW() - INTERVAL 43 DAY),
('admin', 'ESLint 설정 방법', '코드 스타일 자동화', 'JavaScript', 'NORMAL', 0, NOW() - INTERVAL 44 DAY),
('user01', '자바스크립트 프로토타입 이해하기', '프로토타입 체인 설명', 'JavaScript', 'NORMAL', 0, NOW() - INTERVAL 45 DAY),

('user02', 'C# async/await 질문', '비동기 프로그래밍 이해', 'C#', 'QUESTION', 30, NOW() - INTERVAL 46 DAY),
('user03', 'ASP.NET Core 시작하기', '간단한 웹 앱 만들기', 'C#', 'NORMAL', 0, NOW() - INTERVAL 47 DAY),
('user04', 'LINQ 사용법 질문', '쿼리 문법과 예제', 'C#', 'QUESTION', 50, NOW() - INTERVAL 48 DAY),
('user05', '.NET Core와 .NET Framework 차이', '성능 및 지원 비교', 'C#', 'NORMAL', 0, NOW() - INTERVAL 49 DAY),
('user06', 'C#에서 이벤트 처리', '이벤트와 델리게이트 사용법', 'C#', 'NORMAL', 0, NOW() - INTERVAL 50 DAY),
('user07', 'WPF 기본 사용법', 'UI 디자인과 데이터 바인딩', 'C#', 'NORMAL', 0, NOW() - INTERVAL 51 DAY),
('user08', 'C# 메모리 관리 질문', 'Garbage Collector 동작 방식', 'C#', 'QUESTION', 100, NOW() - INTERVAL 52 DAY),
('user09', 'C# 인터페이스와 추상 클래스 차이', '사용 사례와 장단점', 'C#', 'NORMAL', 0, NOW() - INTERVAL 53 DAY),
('user10', 'Entity Framework Core 소개', 'ORM 기본 개념', 'C#', 'NORMAL', 0, NOW() - INTERVAL 54 DAY),
('admin', 'C# 9.0 신규 기능', '레코드 타입과 패턴 매칭', 'C#', 'NORMAL', 0, NOW() - INTERVAL 55 DAY),
('user01', 'C# 컬렉션 사용법 질문', 'List와 Dictionary 차이점', 'C#', 'QUESTION', 10, NOW() - INTERVAL 56 DAY),
('user02', 'C# 예외 처리 베스트 프랙티스', 'try-catch 활용법', 'C#', 'NORMAL', 0, NOW() - INTERVAL 57 DAY),
('user03', '유니티 게임 개발 시작', 'C# 스크립팅 기초', 'C#', 'NORMAL', 0, NOW() - INTERVAL 58 DAY),
('user04', 'C# 제네릭 질문', '제네릭 타입과 메서드 사용법', 'C#', 'QUESTION', 30, NOW() - INTERVAL 59 DAY),
('user05', 'ASP.NET MVC와 Razor 페이지 차이', '웹 개발 선택 가이드', 'C#', 'NORMAL', 0, NOW() - INTERVAL 60 DAY),

('user06', '코틀린 코루틴 질문', '비동기 처리 방법', 'Kotlin', 'QUESTION', 30, NOW() - INTERVAL 61 DAY),
('user07', '코틀린 기본 문법 소개', '클래스와 함수', 'Kotlin', 'NORMAL', 0, NOW() - INTERVAL 62 DAY),
('user08', '안드로이드 개발과 코틀린', 'Kotlin 활용 팁', 'Kotlin', 'NORMAL', 0, NOW() - INTERVAL 63 DAY),
('user09', '코틀린 데이터 클래스 질문', 'data class의 특징과 사용법', 'Kotlin', 'QUESTION', 50, NOW() - INTERVAL 64 DAY),
('user10', '코틀린 DSL 만들기', '도메인 특화 언어 작성법', 'Kotlin', 'NORMAL', 0, NOW() - INTERVAL 65 DAY),
('admin', 'Kotlin Null 안전성', 'null 처리와 안전 호출 연산자', 'Kotlin', 'NORMAL', 0, NOW() - INTERVAL 66 DAY),
('user01', '코틀린 확장 함수 질문', '확장 함수 작성 및 활용', 'Kotlin', 'QUESTION', 100, NOW() - INTERVAL 67 DAY),
('user02', '코틀린 람다와 고차 함수', '함수형 프로그래밍 소개', 'Kotlin', 'NORMAL', 0, NOW() - INTERVAL 68 DAY),
('user03', '코틀린에서 Sealed 클래스', 'sealed 클래스 활용법', 'Kotlin', 'NORMAL', 0, NOW() - INTERVAL 69 DAY),
('user04', '코틀린 스코프 함수', 'let, apply, also 차이점', 'Kotlin', 'QUESTION', 10, NOW() - INTERVAL 70 DAY),
('user05', '코틀린 객체 선언', 'object 키워드 사용법', 'Kotlin', 'NORMAL', 0, NOW() - INTERVAL 71 DAY),
('user06', '코틀린 인터페이스 기본 구현', 'interface에서 메서드 기본 구현', 'Kotlin', 'NORMAL', 0, NOW() - INTERVAL 72 DAY),
('user07', '코틀린 컬렉션 다루기', 'List, Set, Map 기본', 'Kotlin', 'NORMAL', 0, NOW() - INTERVAL 73 DAY),
('user08', '코틀린 시퀀스 질문', '시퀀스와 컬렉션 차이점', 'Kotlin', 'QUESTION', 30, NOW() - INTERVAL 74 DAY),
('user09', '코틀린 멀티플랫폼 프로젝트', '공유 코드 작성 팁', 'Kotlin', 'NORMAL', 0, NOW() - INTERVAL 75 DAY);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1) 
values ('admin', 
'한 입 크기로 잘라먹는 타입스크립트(TypeScript)',
'문법을 넘어 동작 원리와 개념 이해까지 배워도 배워도 헷갈리는 타입스크립트 이제 제대로 배워보세요! 여러분을 타입스크립트 마법사로 만들어드립니다.',
'웹 개발',
5.0,
now(),
'https://www.inflearn.com/course/%ED%95%9C%EC%9E%85-%ED%81%AC%EA%B8%B0-%ED%83%80%EC%9E%85%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8',
'타입스크립트

타입스크립트의 필요성과 특징

타입 시스템에 대한 깊은 이해

타입을 집합으로 이해하기

리액트와 함께 타입스크립트 사용하기',

'🧐 배워도 배워도 헷갈리는
타입스크립트, 이제 제대로 배워봐요
이제는 피할 수 없는 대세가 되어버린 타입스크립트(Typescript)!
원리를 제대로 이해하지 못한 채 문법만 대충 배웠다면
타입스크립트가 제공하는 강력한 기능들을 제대로 이용하기 어렵습니다.

여러분은 타입스크립트를 정말 잘 이해하고 계시나요?
아래 질문에 충분히 대답할 수 있는지 확인해 보세요.

타입스크립트에서 말하는 타입이란 무엇인가요?
서로 다른 타입 간의 호환성은 어떤 기준으로 결정되나요?
기본적으로 제공되는 타입들(any, unknown, never 등)의 동작 원리를 자세히 설명할 수 있나요?
이 강의는 단순한 타입스크립트의 문법만 나열해 놓은 강의가 아닙니다.
문법을 포함해 타입스크립트가 왜 그렇게 동작하는지 그리고 어떻게 설계되었는지
아주 쉽고 재미있게 알아봅니다.
강의가 끝나고 나면 이제 여러분은 타입스크립트 마법사가 되어 있을 거예요. 🧙🏻‍♀
아래 그림과 같은 강의와 함께 보실 수 있는 핸드북도 제공됩니다!



어렵고 복잡한 개념도
쉽고 재미있게 살펴볼 거예요
타입스크립트는 수학의 집합론을 기반으로 동작하는 언어이기 때문에
말과 글로만은 원리를 확실히 이해하기 어려울 수 있어요.
그래서 다양한 시각 자료와 사례들을 준비했어요.
아무리 어렵고 복잡한 개념이더라도 쉽고 재미있게 살펴볼 거예요.',
'06f851e7-4f07-48db-a26c-5a14aa3182de.png'
);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values (
'admin',
'한 입 크기로 잘라먹는 Next.js(v15)',
'한입 시리즈의 3번째 작품! 세상에서 가장 친절하고 디테일 한 Next.js(15+)강의 입니다. App Router 뿐만 아니라 Page Router까지 프로젝트를 통해 살펴봅니다.',
'프론트엔드',
5.0,
now(),
'https://www.inflearn.com/course/%ED%95%9C%EC%9E%85-%ED%81%AC%EA%B8%B0-nextjs',
'최신 Next.js(v15 이상) 개념에 대해 살펴봅니다.

Page Router부터 App Router까지 대부분의 개념을 살펴봅니다.

서버 컴포넌트, 페이지 라우팅, 레이아웃 설정, 데이터 페칭, 스트리밍, 배포등의 다양한 개념을 살펴봅니다',
'한입 크기로 잘라먹는 Next.js(15+)
15시간의 분량으로
Page Router부터 App Router까지
Next.js 13 버전부터 새롭게 등장한 App Router는 Page Router의 단점을 보완하기 위해 등장했습니다.
따라서 Page Router에 대한 이해가 없다면 App Router의 새로운 매커니즘에 대해 쉽게 이해하기 어렵습니다.

따라서 본 강의는 Page Router(5시간)를 빠르게 살펴본 다음 App Router(10시간)를 본격적으로 살펴보는 순서로 진행됩니다. 이를 통해 기존의 Page Router에 어떤 한계점이 있었고 App Router가 이런 한계점을 어떻게 극복하는지 깊히 이해하게 됩니다.

⚠ 그러나 오해하진 마세요 본 강의는 App Router를 중심으로 진행됩니다.

정적인 시각자료는 그만 ✋
직관적인 애니메이션과 함께 살펴보는 Next.js
Next.js는 복잡한 매커니즘을 갖는 기능이 많은 편입니다.
따라서 정적인 시각자료 만으로는 충분히 설명되기 어렵습니다.
이에 본 강의는 아래와 같은 직관적인 애니메이션과 함께합니다.

실제 상황과 최대한 가깝게 🚧
실습을 위한 자체 제작 백엔드 서버 제공
Next.js의 각종 캐싱 기능을 좀 더 실전에 가까운 환경에서 살펴보기 위해
자체 제작한 백엔드 서버를 제공해드립니다.',
'f05c4379-91d7-4f8a-a11b-0f05dda7bb0b.png'
);


insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values ('admin', 
'[코드캠프] 부트캠프에서 만든 완벽한 프론트엔드 코스', -- 제목
'수백명의 개발자를 배출한 ‘진짜’ 부트캠프의 완벽한 프론트엔드 커리큘럼 입니다. 이 강의 하나로 여러분은 현업 주니어 개발자 수준까지 성장 가능하며, 프론트엔드 기술스택의 활용 능력과 지식을 얻게 될거에요. 누구나 사회적, 경제적, 교육적 배경에 상관없이 커리어를 쌓을 수 있도록, [코드캠프]가 준비했습니다 :)', -- 부제목
'프론트엔드', -- 카테고리
5.0,
now(),
'https://www.inflearn.com/course/%EC%BD%94%EB%93%9C%EC%BA%A0%ED%94%84-%EC%99%84%EB%B2%BD%ED%95%9C-%ED%94%84%EB%A1%A0%ED%8A%B8%EC%97%94%EB%93%9C-%EC%BD%94%EC%8A%A4', -- 출처
'심화과정을 대비하는 친절한 [CSS&Javascript]

최기술스택을 활용한 [웹 프론트엔드]

실무역량을 위한 [하이브리드앱 프론트엔드]

미니 포트폴리오 2개, 실무 포트폴리오 2개

전체적인 개발 사이클 완벽 이해', -- 내용 1
'진짜 부트캠프에서 만든
[완벽한 프론트엔드 코스]는요
코딩의 코자도 모르는 나.. 개발자로 취업할 수 있을까? 그런 걱정은 당장 넣어두세요.
이 강의에서는 쌩초보도 현업 개발자 수준으로 성장할 수 있도록 탄탄한 기초 강의부터 시작하니까요!

온·오프라인으로 2,000명이 넘는 수강생이 선택한 고농축 프론트엔드 코스 
더 강력하게 업데이트 되어 완벽한 프론트엔드 코스로 다시 찾아 왔습니다😀', -- 내용 2
'abd5b1aa-455b-44e9-8df8-ca20242eac37.png'
);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values ('admin', 
'React, Node.js, MongoDB로 만드는 나만의 회사 웹사이트: 완벽 가이드', -- 제목
'React, Node.js, MongoDB를 활용해 회사 웹사이트를 직접 제작하는 과정을 배울 수 있는 실전 강의입니다. 간단한 React, Node.js 배경으로 풀스택 프로젝트를 같이 만들어요!', -- 부제목
'웹 개발', -- 카테고리
4.9,
now(),
'https://www.inflearn.com/course/react-nodejs-%ED%9A%8C%EC%82%AC%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8-%EB%A7%8C%EB%93%A4%EA%B8%B0-%ED%92%80%EC%8A%A4%ED%83%9D', -- 출처
'React & Tailwind CSS로 트렌디한 UI를 설계, 구현

Node.js와 Express로 백엔드 서버 구축

MongoDB와 Express Router로 데이터베이스 설계 및 관리

프론트엔드와 백엔드 간의 데이터 통신 이해

실제 회사 웹사이트 수준의 프로젝트 완성', -- 내용 1
'회사의 웹사이트는 어떻게 고객과의 첫인상을 좌우할까요?
답은 React, Node.js, MongoDB, HTML/CSS, JavaScript를 활용한 강력한 기술 스택에 있습니다. 많은 사용자들이 기업사이트의 트렌디한 UI와 효율적인 데이터 관리를 통해 Interaction을 경험하고 있습니다.

본 강의에서는 React로 프론트엔드를, Node.js와 MongoDB로 백엔드를 설계하여 실제 회사 웹사이트 수준의 프로젝트를 완성합니다. 효율적인 상태 관리, RESTful API 통합, 데이터베이스 설계 등 실무에서 요구되는 핵심 기술을 깊이 있게 다룹니다.

단순히 따라 하는 튜토리얼이 아닌, 체계적인 학습을 통해 Production Ready 수준의 웹사이트를 만들며 실전 개발자의 감각을 익혀보세요!

이 강의의 특징
📌 회사의 첫인상을 결정짓는 웹사이트 제작의 노하우를 공개합니다. 3년간의 프로젝트 경험을 통해 얻은 효율적이고 실용적인 개발 방법론을 전수합니다.

📌 프론트엔드부터 백엔드까지 스키마 설계, 상태 관리, 데이터베이스 최적화 등 실무에서 반드시 필요한 기술과 insight를 제공합니다.

📌 이론 20%, 실습 80%. 모든 코드를 직접 작성하고 테스트하며, 실제로 작동하는 완성도 높은 웹사이트를 만들어 봅니다.

📌 중급에서 상급으로 도약하려는 분들을 위한 강의. 기본적인 HTML, CSS, JavaScript 및 React & Node.js의 간단한 지식만 있다면 누구나 따라 할 수 있습니다.

수강 후에는
React, Tailwind CSS, Node.js, MongoDB를 활용해 실제 회사 웹사이트 수준의 프로젝트를 제작할 수 있게 돼요.
RESTful API 설계와 상태 관리를 체계적으로 이해하고, 클라이언트와 서버 간의 데이터를 효율적으로 처리할 수 있게 돼요.
MongoDB를 활용한 데이터베이스 설계와 최적화로 데이터를 체계적으로 관리할 수 있어요.
Production Ready 웹사이트 개발 과정을 경험하며, 실무에서도 활용 가능한 포트폴리오를 만들게 돼요.
개발 과정에서 테스트와 디버깅을 통해 코드의 품질을 높이고 안정적인 웹사이트를 완성할 수 있어요.', -- 내용 2
'f9d3ef97-46b5-43fd-abab-1338ddb994b7.avif'
);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values ('admin', 
'[개정3판] Node.js 교과서 - 기본부터 프로젝트 실습까지', -- 제목
'노드가 무엇인지부터, 자바스크립트 최신 문법, 노드의 API, npm, 모듈 시스템, 데이터베이스, 테스팅 등을 배우고 5가지 실전 예제로 프로젝트를 만들어 나갑니다. 클라우드에 서비스를 배포해보기도 하고 노드 프로젝트를 타입스크립트로 전환해도 봅니다.', -- 부제목
'백엔드', -- 카테고리
4.8,
now(),
'https://www.inflearn.com/course/%EB%85%B8%EB%93%9C-js-%EA%B5%90%EA%B3%BC%EC%84%9C', -- 출처
'Node.js

NPM

Express.js

서버

MySQL

MongoDB

sequelize

mongoose

테스팅

socket.io (WebSocket)

Server Sent Event

AWS 배포(S3, Lambda)

TypeScript 전환', -- 내용 1
'인프런 수강생 4,200명이 선택한 강의 리뉴얼
Node.js 교과서 개정 3판(길벗)의 내용을 다루는 동영상 강의입니다. 노드가 무엇인지부터 자바스크립트(JavaScript) 최신 문법, 노드 API, npm, 모듈 시스템, 데이터베이스, 테스팅 등을 배우고 5가지 실전 예제로 프로젝트를 만들어 나간 다음 AWS에 배포하는 것으로 마무리 짓습니다.

840쪽에 달하는 책 내용을 1500분 분량의 영상으로 배웁니다. 길벗 더북에서 노드교과서 책을 8장까지 무료로 보실 수 있으니 함께 보시는 것을 추천드립니다.

기본기를 꽉 잡은 Node.js 학습

왜 Node.js를 사용할까요? 서버로서 노드는 어떤 특징이 있을까요? 타입스크립트를 노드에 적용하면 얻는 이점은 무엇일까요? Node.js 개발자라면 반드시 알아야 하는 핵심 개념과 기능, 구조를 상세하게 학습해 봅니다.
 
실력을 높이는 실전형 예제

실시간 GIF 채팅방, 경매 시스템, CLI 프로그램, 트위터 같은 노드버드 SNS 웹앱 등 다양한 예제를 따라 만들어볼 수 있습니다. 실습을 통해 자연스럽게 모듈 사용, 호출, 테스트, 배포 등 실제 프로젝트에 필요한 핵심 기술을 학습할 수 있습니다.

생생하게 배우는 라이브 코딩

이미 만들어 둔 소스코드를 복붙하지 않습니다. 라이브 코딩 중심의 수업을 통해 개발하는 과정에서 발생할 수 있는 에러를 해결하는 방법과 노하우까지 고스란히 확인하실 수 있습니다.', -- 내용 2
'42107ccb-750c-4c28-aafa-781c3afbcd29.avif'
);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values ('admin', 
'Do It! 장고+부트스트랩: 파이썬 웹개발의 정석', -- 제목
'만들면서 배우는 파이썬 웹개발 A to Z! 웹 기초부터 블로그 개발, 배포까지 한 번에 배워요.', -- 부제목
'풀스택', -- 카테고리
4.8,
now(),
'https://www.inflearn.com/course/%EB%91%90%EC%9E%87-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%9B%B9%EA%B0%9C%EB%B0%9C', -- 출처
'파이썬 웹 프레임워크인 Django를 배울 수 있어요.

웹 개발 방법 및 구조를 배울 수 있어요.

실제 블로그 웹사이트를 만들 수 있어요. (doitdjango.com)

CSS 프레임워크인 bootstrap의 활용방법을 배울 수 있어요.

회원가입(구글 로그인, 이메일 가입) 등을 구현할 수 있어요.

도메인을 구입하고 AWS을 통해 배포하는 방법을 배워요.', -- 내용 1
'강의 주제 😊
이 강의는 웹 개발을 하고 싶은 파이썬 사용자를 위한 강좌입니다. 실제 파이썬 진영의 가장 대표적인 웹 프레임워크 중 하나인 django를 이용해 여러분만의 블로그 사이트를 만들어 볼 수 있으며, 이 강의를 끝까지 따라하고 나면 여러분 모두 doitdjango.com 과 같은 웹사이트를 가질 수 있습니다.

이 강의에서는 HTML, CSS, 자바스크립트부터 부트스트랩, 파이썬 웹프레임워크인 장고(django), 도커(Docker), 아마존 웹서비스(AWS)까지 익힐 수 있습니다. 필요한 기능을 하나씩 구현하며 맞닥뜨리는 어려움을 직접 풀며 웹 개발에 대한 전반적인 이해와 함께 문제를 해결하는 능력까지 쌓아 보세요. 지금 바로 시작합시다!

화면 디자인부터 아마존 서버 배포까지, 한 권으로 만나는 웹 개발!
 수강 전 확인해주세요! 

이 강의는 2019년 인프런에 공개한 파이썬 사용자를 위한 웹개발 입문 A to Z Django + Bootstrap 의 리뉴얼 & 확장판입니다.
이 강의는 이지스퍼블리싱에서 출판된 <Do it! 장고+부트스트랩 파이썬 웹 개발의 정석> 을 영상으로 배우고 싶은 분들을 위한 강의입니다. 2021년 기준으로 최근 보편적으로 사용되는 기술을 추가로 담았고, 2019년에 오픈했던 강의의 수강생 분들의 피드백을 최대한 많이 반영했습니다.

저와 함께 웹 개발, 이제 제대로 달려볼까요? 👉', -- 내용 2
'7a83fb39-70a7-49ed-b27a-2b12b401fa9d.avif'
);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values ('admin', 
'Spring WebFlux + LLM 실전 구현', -- 제목
'Spring WebFlux로 여러 기능을 만듭니다. 이 강의를 통해 WebFlux의 필수 개념과 실전 구현을 익힐 수 있습니다.', -- 부제목
'백엔드', -- 카테고리
5.0,
now(),
'https://www.inflearn.com/course/spring-webflux-llm%EC%8B%A4%EC%A0%84%EA%B5%AC%ED%98%84', -- 출처
'Spring WebFlux 실전 구현

LLM을 활용한 기능 구현', -- 내용 1
'Spring WebFlux + LLM 실전 구현
근 몇 년간 Spring WebFlux와 LLM을 직접 사용해보고 사내 스터디도 진행하며 너무 즐거운 경험들을 했습니다.
이 강의를 통해 그 간의 경험과 지식을 여러분과 함께 나누고, 같이 다양한 기능을 만들어보는 시간을 갖고 싶습니다.

여러 WebFlux 서비스를 설계하고 유지보수 해온 경험과
직접 Spring WebFlux와 Netty의 코드를 모두 뜯어본 경험을 통해
정말 필요한 개념만 알기 쉽게 전달드립니다.

강의를 마칠 때쯤엔 

여러분이 상상만 해보셨던 기능들 

WebFlux와 LLM으로 전부 구현할 수 있을 만한 역량을 갖추실 수 있도록 도와드리겠습니다.', -- 내용 2
'246b5b6a-24a4-4737-b57d-fb553957f641.png'
);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values ('admin', 
'비전공자를 위한 진짜 입문 올인원 개발 부트캠프', -- 제목
'왕초보도 할 수 있는 웹, 서버, 모바일 앱, 머신러닝까지 익히는 끝판왕 풀스택 강의!! 개발을 어디서부터 시작할지 막막했다면 이 강의를 강력 추천할게요. Javascript 하나로 진행합니다!', -- 부제목
'풀스택', -- 카테고리
5.0,
now(),
'https://www.inflearn.com/course/%EC%98%AC%EC%9D%B8%EC%9B%90-%EA%B0%9C%EB%B0%9C%ED%81%B4%EB%9E%98%EC%8A%A4', -- 출처
'HTML, CSS, JS 기본

웹 개발 (React)

모바일 앱 개발 (React Native)

서버 개발 (Node.js)

머신러닝 개발 (Tensorflow js)

Git

클라우드

필수 개발 지식', -- 내용 1
'쉽고 다양하게 활용되는 Javascript 로 공부해요!
Javascript(자바스크립트)는 웹을 시작으로 서버, 모바일, 머신러닝, 블록체인 등 다양한 분야에서 사용되는 프로그래밍 언어예요.
상대적으로 쉬운 난이도로 입문자가 배우기에 적합하고 모든 분야에서 활용될 수 있어요!!

궁금해요 🙋‍♂️🙋‍♀️

Q. 이 강의에서 무엇을 배울 수 있나요?
프론트엔드
HTML, CSS, FlexBox
Javascript, ES6
React 라이브러리
Antd, React-Router 라이브러리
Postman
반응형 웹 등
백엔드
Node.js
Express 프레임워크
Sqlite 데이터베이스 + Sequelize ORM
이미지 파일 처리(multer)
클라우드(Vercel, Heroku)
상품 추천 API
머신러닝
Tensorflow.js
머신러닝, 딥러닝 메카니즘
Mobilenet
모바일 앱
React Native
Expo
React Navigation
ngrok
기타
Git & Github 버전관리
추가 개발 지식 (REST API, JSON 등)
Q. 개발을 모르는 비전공자인데 할 수 있을까요?
비전공자도 충분히 학습할 수 있도록 심혈을 기울여 만들었어요! 

한 강의에 많은 걸 알려주지 않습니다. 배운 내용을 계단 삼아 단계별로 학습합니다.
어려운 개념은 반복적으로 설명 및 활용합니다.
저 그랩은 이미 수많은 비전공자를 대상으로 글을 쓰고 강의를 진행했어요 :)
클래스 101 - 개발자가 되고 싶은 당신을 위해, 개발자가 되는 로드맵 A to Z
인프런 - IT 회사에서 살아남기 위한 모든 개발 지식 A to Z 강의
블로그 - IT 회사에서 살아남기 위한 모든 개발 지식 A to Z
리디셀렉트 - 비전공자를 위한 IT 싱크 맞추기
뉴스레터 - 그랩의 IT 뉴스레터
유튜브 - 그랩의 IT 열차
궁금한 점은 실시간으로 빠르게 답변드려요!
Q. 완강하고 난 다음에 뭘 해야 할까요?
본 강의를 들었다고 풀스택 개발자가 되는 것은 아닙니다. (절대!!) 
가장 먼저 지금까지 배운 지식을 토대로 본인이 관심 있는 분야를 정해서 전문성을 쌓는 걸 추천드립니다. 프론트엔드, 백엔드, 모바일 앱, 머신러닝 모두 좋아요. 그래도 헷갈리신다면 질문을 남겨주세요. 빠르게 답변드릴게요!

개인 프로젝트, 스터디, 온라인 강의 등을 활용해서 한 분야의 전문성을 쌓으세요. 여러분이 꿈꾸는 모습으로 한 걸음씩 전진하다 보면 어느새 전문가가 되어있을 겁니다.💪🏼

마지막으로 드리고 싶은 말
배우고자 하는 의지와 꾸준함만 있다면 여러분들 모두 충분히 하실 수 있어요! 저 그랩과 함께 느리더라도 꾸준히 하다 보면 어느 순간 개발 커리어의 초석을 쌓을 수 있을 거예요.
수업 때 다시 인사드리도록 할게요.😄 감사합니다.', -- 내용 2
'f394ec7e-36e9-4996-a3b2-3e354d979cb4.avif'
);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values ('admin', 
'[풀스택 완성] Supabase로 웹사이트 3개 클론하기 (Next.js 14)', -- 제목
'풀스택 개발에 날개를 달아줄 Supabase! 3가지의 클론 프로젝트를 통해 Next.js 14와 Supabase로 실무 수준의 풀스택 개발을 하는 법을 속성으로 배우게 됩니다.', -- 부제목
'백엔드', -- 카테고리
5.0,
now(),
'https://www.inflearn.com/course/%EC%9A%94%EC%A6%98%EC%97%94-supabase-%EB%8C%80%EC%84%B8%EC%A7%80-nextjs-%ED%81%B4%EB%A1%A0%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8', -- 출처
'Supabase로 서버없이 풀스택 개발을 하는 법 (회원인증, 파일 업로드, 실시간 채팅)

Next.js 14버전 (feat. App Router, Server Action)

카카오 OAuth 로그인 구현

Tailwind CSS, React Query, Recoil 라이브러리

Vercel과 AWS를 통한 프로젝트 배포방법

무한 스크롤, 드래그 앤 드롭 기능 구현

도메인 구매 및 연동', -- 내용 1
'풀스택 개발에 날개를 달아줄
파이어베이스의 강력한 대항마,
🚀 슈파베이스 🚀

PostgreSQL 기반으로 복잡한 요구사항도 쉽게 구현해요

실시간 데이터베이스 기능으로 실시간 채팅 개발도 더이상 무섭지 않아요

소셜 로그인 기능도 쉽게 구현해요 (카카오, 구글, etc)

SDK, GraphQL, API 등 나에게 편한 방식으로 손쉽게 연동해요

어드민 페이지와 자동으로 SQL을 만들어주는 AI 기능도 다 무료에요

이 강의를 수강하시는 모든 분들은
총 4개의 웹사이트를 혼자서 배포까지
모두 완료하게 됩니다 🚀

Next.js 14와 Supabase의 강력한 조합으로 🚀

가장 빠르게 풀스택 개발자로 만들어드립니다!

3가지의 클론 프로젝트를 통해 빠르게 실력을 향상시켜 드립니다 👩🏻‍🎓', -- 내용 2
'c7ce5e92-e46f-4467-b53a-18a8da567332.avif'
);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values ('admin', 
'Python을 위한 디자인 패턴', -- 제목
'내 코드를 한 단계 성장시키고 싶으신가요? 🚀 초보 개발자분들을 위해 Python 디자인 패턴 📖 강의를 준비했어요. 복잡한 설계 노하우를 실무 예제로 알기 쉽게 풀어냈습니다. 이 강의에서는 이런 것들을 배워요: 💼 실무 예제 중심: 바로 써먹을 수 있는 현실적인 예제들 📚 클래식 패턴 정복: GoF의 핵심 패턴 마스터하기 🐍 파이썬다운 꿀팁: 더 간결하고 효율적인 코드 작성법 코드 설계에 자신감을 더해줄 거예요! ✨', -- 부제목
'백엔드', -- 카테고리
5.0,
now(),
'https://www.inflearn.com/course/python%EC%9D%84-%EC%9C%84%ED%95%9C-%EB%94%94%EC%9E%90%EC%9D%B8-%ED%8C%A8%ED%84%B4', -- 출처
'반복되는 소프트웨어 설계 문제에 대한 검증된 해결책을 배웁니다.

상황에 맞는 최적의 디자인 패턴을 선택하고 적용하는 능력을 기릅니다.

유지보수와 확장이 쉬운 클린 코드를 작성하는 방법을 익힙니다.

객체지향 설계 원칙(OOP)에 대한 깊이 있는 이해를 갖게 됩니다.

데코레이터, 컨텍스트 매니저 등 파이썬다운 코딩 스타일을 마스터합니다.', -- 내용 1
'🚀 내 코드를 작동에서 작품으로, 파이썬 (Python) 디자인 패턴 마스터 클래스


코드는 잘 돌아가는데... 좋은 코드는 대체 뭘까?

다른 개발자들은 어떻게 저렇게 깔끔하고 유연하게 코드를 짜는 걸까?

파이썬 기초를 막 떼고 다음 단계로 나아가고 싶은 개발자라면 누구나 한 번쯤 해봤을 고민일 거예요. 이 질문에 대한 가장 확실한 해답 중 하나가 바로 디자인패턴(Design Pattern)에 있습니다.

디자인 패턴이란, 수십 년간 수많은 개발자들이 마주쳤던 반복적인 문제들을 해결하는 가장 우아하고 효율적인 방법들을 정리한 설계 청사진입니다. 단순히 코딩 스킬을 넘어, 좋은 소프트웨어를 만드는 사고의 틀을 배우는 것이죠.

하지만 디자인 패턴, 이름만 들어도 어렵고 딱딱하게 느껴지셨나요?

이 강의는 바로 그런 분들을 위해 탄생했습니다.



🤔 이 강의는 무엇이 다른가요?


1. 초급자의 눈높이에 맞춘 친절한 설명 추상적인 이론만 나열하는 강의가 아닙니다. "이 패턴이 왜 필요한지", "어떤 상황에서 힘을 발휘하는지"를 게임 캐릭터 생성, 데이터 처리 파이프라인, GUI 위젯 등 현실적인 예제를 통해 하나씩 차근차근 풀어갑니다. 복잡한 다이어그램 대신, 직관적인 파이썬 코드로 개념을 바로바로 확인할 수 있어요.



2. 파이썬다운 패턴 활용법 🐍 자바나 C++ 스타일의 딱딱한 패턴 설명은 이제 그만! 이 강의는 파이썬의 동적인 특성과 강력한 기능(일급 함수, 데코레이터, 컨텍스트 매니저 등)을 100% 활용하여 각 디자인 패턴을 얼마나 간결하고 아름답게 구현할 수 있는지 집중적으로 다룹니다. 다른 언어에서는 복잡했던 패턴이 파이썬에서는 얼마나 간단해지는지 경험하며 진정한 Pythonic Way를 체득하게 될 거예요.



3. 실무와 직결되는 커리큘럼 GoF(Gang of Four)가 제시한 23가지 클래식 패턴은 물론, 실무에서 자주 쓰이지만 책에서는 잘 알려주지 않는 파이썬 특화 패턴과 관용구까지 알차게 담았습니다. 이제 막 실무에 투입된 주니어 개발자도, 사이드 프로젝트를 근사하게 만들고 싶은 분도 바로 써먹을 수 있는 실용적인 지식을 얻어 가실 수 있습니다.



🙋‍♂ 이런 분들께 강력 추천해요!


파이썬 기초 문법은 알지만, 코드를 어떻게 구성해야 할지 막막한 분
스파게티처럼 얽힌 자신의 코드를 리팩토링하고 싶은 주니어 개발자
기술 면접에서 소프트웨어 설계 역량을 제대로 어필하고 싶은 분
오픈 소스나 팀 프로젝트에서 다른 사람의 코드를 쉽게 이해하고 기여하고 싶은 분
단순한 코더를 넘어, 소프트웨어 아키텍트로 성장하고 싶은 분


🎓 이 강의를 완강하면, 여러분은!


GoF의 핵심 패턴들을 자신 있게 설명하고 코드에 활용할 수 있습니다.
"이 상황엔 이 패턴!" 하고 문제에 맞는 최적의 설계를 떠올릴 수 있습니다.
유지보수와 확장이 쉬운 클린 코드가 무엇인지 몸으로 체득하게 됩니다.
다른 개발자와 설계에 대해 막힘없이 소통할 수 있는 공통 언어를 갖게 됩니다.
작동하는 코드를 넘어 자랑하고 싶은 코드를 짜는 개발자로 거듭납니다.
소프트웨어 설계라는 망망대해에서 든든한 나침반이 되어줄 이 강의와 함께, 여러분의 개발 역량을 한 단계 점프시켜 보세요!', -- 내용 2
'6223b5f3-7528-43d1-bcbf-25b069b6d4bb.png'
);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values ('admin', 
'프로덕션 레벨 실시간 채팅 서버 구축: 분산 처리부터 성능 최적화까지 (Kotlin & Spring)', -- 제목
'Spring Boot 3.x + Kotlin으로 3개 인스턴스 클러스터를 구축하고, Redis Pub/Sub 분산 메시징과 Nginx 로드밸런서를 활용하여 대용량 트래픽 처리가 가능한 확장 가능한 WebSocket 실시간 채팅 서버를 개발합니다.', -- 부제목
'백엔드', -- 카테고리
5.0,
now(),
'https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EB%8D%95%EC%85%98-%EB%A0%88%EB%B2%A8-%EC%8B%A4%EC%8B%9C%EA%B0%84-%EC%B1%84%ED%8C%85-%EC%84%9C%EB%B2%84-%EA%B5%AC%EC%B6%95', -- 출처
'Redis Pub/Sub을 활용하여 여러 서버 인스턴스 간 실시간 메시지를 동기화하는 분산 메시징 시스템

Docker Compose로 3개 인스턴스 클러스터를 구성하고 Nginx 로드밸런서로 트래픽을 분산 처리하는 방법

Spring WebSocket을 이용하여 양방향 실시간 통신을 구현

멀티모듈 구조로 Domain-driven Design을 적용하여 확장 가능한 서버 아키텍처를 설계하는 방법

Redis를 활용한 메시지 시퀀스 관리로 분산 환경에서도 메시지 순서를 보장하는 기법

Docker 컨테이너 기반 배포와 헬스체크, 로그 모니터링을 통한 서비스 운영 방법

Spring Boot 3.x + Kotlin의 최신 기능을 활용한 모던 백엔드 개발 방법

메시지 중복 처리 방지, 세션 정리, 리소스 해제 등 안정적인 서비스 운영을 위한 방어 코딩 기법', -- 내용 1
'이 강의를 통해 여러분은...
실제 서비스 환경에서 수만 명의 사용자를 감당할 수 있는 확장 가능한 서버 아키텍처를 직접 설계하고 구축하게 됩니다.



🏗. 단순히 기능을 구현하는 것을 넘어, 대규모 트래픽에도 안정적으로 동작하는 분산 시스템의 핵심 원리를 체득할 수 있습니다.

먼저, 여러 대의 서버가 하나의 시스템처럼 동작하도록 Nginx 로드밸런서로 트래픽을 분산하고 🌐, Redis Pub/Sub을 이용해 서버 간 메시지를 실시간으로 동기화하는 방법을 배웁니다. 이 과정에서 C10K (1만 동시 접속) 문제를 해결하는 노하우를 자연스럽게 익히게 될 것입니다.



또한 ⚡데이터베이스 커넥션 풀을 튜닝하고, JPA 배치 처리와 커서 기반 페이지네이션을 적용하여 응답 속도를 밀리초 단위까지 개선하는 경험을 하게 될 것입니다.

마지막으로, Docker 컨테이너🐳 를 통해 개발부터 배포까지 일관된 환경을 유지하고, Health Check와 모니터링 시스템🩺 을 구축하여 서비스 장애를 사전에 방지하는 안정적인 운영 기법까지 학습합니다. 이 모든 과정을 최신 스택인 Kotlin과 Spring Boot 3.x, websocket를 활용하여 세련되고 효율적인 코드로 구현합니다 ✨.

📦강의를 듣고 나면 이런 결과물을 만들 수 있어요

하나의 완전한 분산 채팅 애플리케이션 📦
명령어로 전체 시스템(서버, 데이터베이스, 캐시 등)을 한 번에 실행할 수 있는, 완전한 형태의 애플리케이션 패키지를 얻게 됩니다. 단순히 코드 조각의 집합이 아닌, 즉시 배포 가능한 서비스입니다.

system 
프로덕션 레벨 시스템 아키텍처 설계도 🗺
사용자의 요청부터 데이터 저장까지 전체 흐름을 담은 시스템 아키텍처 설계도를 직접 구축하고 이해하게 됩니다. 이 설계도는 여러분의 기술적 깊이를 증명하는 훌륭한 포트폴리오 자산이 됩니다.

실시간 양방향 채팅 기능 💬
사용자가 로그인하고, 채팅방을 만들고, 다양한 메시지를 실시간으로 주고받는 핵심 채팅 기능이 완벽하게 구현된 결과물을 갖게 됩니다.

수평 확장이 가능한 서버 클러스터 ↔
n개의 Spring Boot 서버가 Nginx 로드밸런서 아래에서 동작하는 서버 클러스터를 구축합니다. 이를 통해 트래픽이 증가할 때 서버 인스턴스를 추가하는 것만으로 간단하게 시스템을 확장하는 방법을 배우게 됩니다.

고가용성 메시징 시스템 📡
한 서버에 장애가 발생하거나, 사용자가 서로 다른 서버에 접속해도 Redis Pub/Sub을 통해 모든 메시지가 누락 없이 전달되는 안정적인 메시징 시스템을 구축합니다.

최적화된 데이터 관리 시스템 ⚡
PostgreSQL에 채팅 데이터를 영구적으로 저장하고, Redis에 세션 정보나 캐시 데이터를 저장하여 데이터의 안정성과 빠른 응답 속도를 모두 만족시키는 하이브리드 데이터 시스템을 완성합니다.

📚강의 주요 내용!
Redis Pub/Sub 분산 메시징
여러 서버 인스턴스 간 실시간 메시지 동기화를 위해 Redis의 발행-구독 패턴을 구현합니다. 중복 메시지 방지, 메시지 순서 보장, 서버별 격리 처리 등 프로덕션 레벨의 분산 메시징 시스템을 구축하여 확장 가능한 실시간 통신 기반을 마련합니다.

WebSocket 기반 실시간 통신
Spring WebSocket을 활용하여 HTTP보다 훨씬 효율적인 양방향 실시간 통신을 구현합니다. 세션 관리, 연결 상태 추적, 에러 핸들링 등을 통해 안정적인 실시간 채팅 기능을 제공하며 해결을 위한 최적화 기법도 적용합니다.

멀티모듈 DDD 아키텍처
Domain-driven Design 원칙에 따라 API, Domain, Persistence, WebSocket 계층을 독립적인 모듈로 분리합니다. 각 모듈의 책임을 명확히 구분하고 의존성 방향을 제어하여 유지보수성과 테스트 용이성을 극대화한 확장 가능한 서버 아키텍처를 구축합니다.

Nginx 로드밸런서 + WebSocket 프록시
n개의 Spring Boot 인스턴스에 트래픽을 균등 분산하는 Nginx 로드밸런서를 구성합니다. HTTP 요청뿐만 아니라 WebSocket 연결도 안정적으로 프록시하며, 프로덕션 레벨의 리버스 프록시 설정으로 고가용성 서비스 환경을 구축합니다.', -- 내용 2
'503b0c59-87bb-4c9f-b41f-a2cbe31120f3.png'
);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values ('admin', 
'코딩 없이 AI 자동화 전문가가 되는 법, n8n 완벽 가이드', -- 제목
'요즘도 직접 코딩하시나요? 이젠 바이브 코딩도 귀찮은 시대! 코딩 없이 n8n으로 AI 자동화 전문가가 되어보세요. 실무에 바로 적용 가능한 자동화 워크플로우 설계 가이드 업무 효율을 극대화하는 실전 중심의 자동화 전략. n8n으로 직관적인 워크플로우를 구축하며, 업무 혁신을 경험해보세요', -- 부제목
'AI 코딩', -- 카테고리
4.9,
now(),
'https://www.inflearn.com/course/ai-%EC%9E%90%EB%8F%99%ED%99%94-n8n', -- 출처
'AI Agent 제작

AI 자동화 실무

N8N 자동화 툴

AI 프롬프트 엔지니어링

RAG(검색 기반 생성) 구조

데이터 임베딩 실습

벡터 DB 활용법', -- 내용 1
'이 강의는 ‘강의를 위한 강의’를 하지 않습니다.
단순히 보여주기 위해 무언가를 설명하고, 실제로는 쓸 일이 없는 예제를 만드는 식의 강의는 지양합니다.
이 강의에서 다루는 모든 내용은 실제로 동작하고, 실무나 개인 프로젝트에서 즉시 활용 가능한 것들입니다.
강의를 위한 이론이 아니라, 현실에서 당장 써먹을 수 있는 자동화를 만들어보고 이해하고 응용하는 것이 목표입니다.

이 강의는 빠르게 변화하는 AI 시대에 대응하기 위해 지속적으로 업데이트 중 입니다. 강의 오픈 후 1개월 사이에 10개의 신규 및 보강 강의가 추가 되었으며 기존의 강의 수강자분들께는 무료로 제공되고 있습니다.

강의 업데이트 내용은 상단의 새소식 메뉴를 통해 확인 하실 수 있습니다.

🎯 개발자만 하던 자동화, 이제 당신도 할 수 있습니다.

메일도, 뉴스도, 이미지도, 영상도… 전부 노코드로 가능합니다!
한때는 자동화 시스템을 만들기 위해 복잡한 코드를 짜고, API를 연동하고, 서버를 설정하는 일이 필수였습니다. 하지만 생성형AI, 인공지능(AI) 을 통한 자동화는 더 이상 개발자만의 영역이 아닙니다. 이 강의에서는 누구나 사용할 수 있는 노코드 도구 N8N과 최신 생성형 AI 모델(OpenAI, Claude 등)을 활용해 실제로 동작하는 AI 자동화 시스템(AI Agent)을 직접 만들어봅니다.



이 강의는 단순히 ChatGPT에게 말을 걸고 끝나는 수준이 아닙니다.
AI가 메일을 읽고, 일정을 잡고, 뉴스를 요약하고, 이미지를 생성하고, 동영상을 제작하고, PDF나 유튜브 자막에 질문을 받고 대답하는 것까지. 이 모든 걸 코딩 없이, 직접 구현할 수 있도록 설계된 실전 강의입니다.

📌 이 강의는 단순한 ‘도구 소개’ 강의가 아닙니다
이 강의는 다음 세 가지에 중점을 둡니다.

AI와 자동화를 접목한 실전 시스템 설계 능력 향상
현업에서 바로 적용 가능한 자동화 사례 중심 실습
AI Agent의 구조를 통합적으로 이해하고 구현하는 능력 확보
🧠 이 강의로 얻게 되는 것
n8n 기초부터 고급 자동화 설계법까지
OpenAI, Claude 등 생성형 AI 모델 API 연동 실습
Google Sheets, Notion, Airtable 등 외부 도구 통합
벡터 검색 기반 RAG 시스템 구축과 활용법
디스코드/텔레그램 기반 AI Agent 자동화 프로젝트 완성
실무에 바로 적용 가능한 AI Agent 프로젝트
실전 자동화를 위한 고급 프롬프트 설계 방법 학습
LLM을 대화 상대가 아닌 작업자로 활용하는 방식
단순 Q&A가 아닌, 구조적 지시어/역할 부여 전략 습득
📌 이 강의의 특징
✅ AI 시대, 코딩 없이도 완성도 높은 자동화 시스템을 만들 수 있습니다!
복잡한 코딩 없이도 N8N의 노코드 환경만으로 실제 업무에 바로 적용 가능한 AI Agent 시스템을 구현할 수 있습니다.
✅ 실습 중심 구성 – 직접 만들어보며 배우는 진짜 자동화!
전체 강의의 90%가 실습입니다.
단순한 이론 설명이 아니라, 예제를 함께 만들어보며 현업 수준의 자동화 워크플로우를 몸에 익힐 수 있습니다.
✅ AI 모델과 다양한 도구들을 한 번에 통합 학습
OpenAI, Claude, Google API, 네이버 검색 API 등 다양한 기술을 활용해 AI Agent가 정보를 수집하고 처리하는 전체 과정을 직접 구현해봅니다.
✅ 완전 초보자도 OK!
개발 지식이 없어도 괜찮습니다.
시각적으로 구성된 인터페이스 덕분에, 따라 하기만 해도 결과물이 나오는 설계로 진행됩니다.', -- 내용 2
'cfe4dba8-13dd-4e3d-bbf9-218b21c70ed6.png'
);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values ('admin', 
'[말 한마디로 뚝딱!] AI와 함께 나만의 수익화 웹사이트를 만드는 법', -- 제목
'AI와 그누보드를 활용해, IT 초보라도 아이디어 하나로 ‘나만의 플랫폼 CEO’가 되는 시대! 기획부터 배포까지 실제 개발 과정을 낱낱이 시연하고, 광고·쇼핑몰 등 다양한 수익화 방법까지 알려드립니다. 지금 바로 온라인 비즈니스를 시작하세요!', -- 부제목
'AI 코딩', -- 카테고리
4.5,
now(),
'https://www.inflearn.com/course/it-%EC%98%AC%EC%9D%B8%EC%9B%90-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%88%98%EC%9D%B5%ED%99%94', -- 출처
'나만의 웹 서비스 플랫폼 구축하기

AI를 활용한 효율적인 개발 방법 익히기

프론트엔드부터 백엔드까지 전반적인 기술 스택 활용하기

광고 수익화 및 SEO 최적화 전략 배우기

실전 프로젝트 경험을 통한 포트폴리오 강화', -- 내용 1
'안녕하세요. 치트키 알려주는 남자, 치알남입니다.
“세상에 치트키를 알려주면 안 된다”고 하는 분들이 있지만, 이번만큼은 예외를 두겠습니다.
반값부동산(https://banbu.kr)이라는 부동산 플랫폼을 직접 기획하고 개발하며, 운영까지 혼자 경험해봤는데요. 기획부터 프론트엔드(Vue2, Vue3, React), 백엔드, 데이터베이스, 클라우드, 보안… 정말 끝이 없었습니다.

하지만 지금은 시대가 달라졌습니다. AI와 간단한 도구만 있다면, 누구나 나만의 온라인 비즈니스를 만들 수 있는 시대죠. 3년간 쌓아온 노하우를 전해드리기 위해 이 강의를 준비하게 되었습니다.

왜 이 강의를 기획했는가?
혼자서도 내 플랫폼을 만들 수 있다!

저는 부동산 플랫폼을 만들면서 수많은 시행착오를 겪었어요. 아무도 알려주는 사람이 없고, 어디에도 ‘어떻게 해라’ 이런 정보도 없었고, 구글 가이드는 ‘우리 기준에 맞춰 잘 만들면 된다’ 정도였다는 게 현실이었죠.

오롯이 혼자 웹의 완결성 있는 기능들을 만드는 게 쉬운 일이 아니었고,
Vue2, Vue3, Nuxt3, React 등 여러 가지 프론트엔드 프레임워크를 시도해도, 구글 에드센스 승인이 나지 않아 답답했어요.
그러던 중 그누보드라는 툴을 발견했습니다. 이미 필요한 웹 필수 기능들이 다 구현되어 있기에 손수 하나하나 개발하는 것에 대한 부담은 확 덜어지고, AI를 적절히 활용하면 비전공자라도 빠르고 쉽게 커뮤니티나 쇼핑몰 등 원하는 플랫폼을 만들 수 있겠다 싶었죠.


본업이 있어도 밤새도록 환경 설정에 시간을 쏟지 않아도 됩니다. 이 강의를 통해 여러분도 사이드 프로젝트를 곧장 시작하고, ‘혼자서 내 플랫폼을 만드는’ 즐거움을 느끼실 수 있도록 도와드리겠습니다.

무엇이 다른가?
개발 환경 설정부터 서버 배포, 그리고 광고 수익화까지 단 하나의 강의에서!
온라인 서비스 플랫폼을 구축하려면 방대한 지식과 정보, 노하우가 필요합니다. 조각조각 흩어진 정보를 매번 찾아 익히고 시행착오를 겪으며 힘들게 적용하는 대신, 단 하나의 강의에서 편리하게 만나보세요. 

처음부터 끝까지 실제 플랫폼을 구현하는 모습을 낱낱이 시연합니다.
반값부동산이라는 하나의 서비스를 3년 동안 직접 기획, 개발하며 얻은 모든 것을 체계적으로 알려드리는 방법은 전 과정을 실제로 보여드리는 것이라고 생각하여 새로 만드는 모습을 컴팩트하면서도 자세히 담았습니다.

코딩 몰라도 AI로 충분히 가능합니다.
IT현업개발자를 포함하여 취준생, 비전공자, IT입문자, 심지어 10대 분들도 따라하실 수 있도록 메가 트렌드인 AI의 여러가지 툴들을 적극 활용하는 치트키로 쉽게 구현하는 방법을 알려드립니다.
💡 무려 4,000만원 절약 +α의 가치!
참고로, 실제 회사에서 이런 플랫폼을 만들려면 과장급 기준으로 프론트엔드·백엔드·인프라·DB·기획자·마케터 등 다양한 인력이 1~2개월 정도 투입되어야 하고, 최소 약 4,000만 원 이상의 예산이 필요합니다. 그러나 본 강의를 통해서 이런 플랫폼을 직접 구축할 수 있으며, 한 번 익혀두면 원하는 플랫폼을 얼마든지 ‘셀프 생산’할 수 있으니 투자 대비 효용이 매우 높습니다. 꼭 숙지해두시는 것을 추천합니다.

이 강의를 통해 무엇을 얻을 수 있을까?
나만의 플랫폼을 만드는 실전 경험
웹 커뮤니티, 쇼핑몰, 광고 수익형 사이트 등 뭐든 만들 수 있어요.
그누보드, 스프링부트, AWS, Docker, Nginx, Gitlab CI/CD… 복잡해 보이는 기술 스택을 AI와 함께 쉽고 빠르게 활용해볼거에요.

효율적인 AI 활용 능력
“코드를 몰라도 될까?” 걱정 마세요. 필요한 부분은 ChatGPT, Cursor, Copilot 등 AI가 코딩을 보조해줍니다. (덕분에 실제로 코드 한 줄 직접 짜지 않았어요.)
막히는 부분마다 머리를 싸맬 필요 없이, AI로 빠른 솔루션을 얻는 방법을 디테일하게 알려드립니다.

빛나는 포트폴리오 & 사업화 기회
직접 웹 서비스를 운영해봤다는 경험은 취업과 이직에서 확실한 강점이 됩니다. 
서비스 구축에 이어 Google Adsense를 통한 광고 수익까지. 스타트업이나 사이드 프로젝트를 준비하시는 분들이라면 비즈니스의 주인공이 될 기회를 잡아보세요.

아이디어 → 서비스 구현 → 배포까지 올인원 경험
온라인 사업에 수반되는 개발 환경 설정부터 서버 배포, 검색엔진 최적화(SEO), 광고 수익화까지 이 모든 프로세스를 다룹니다.
하나라도 빠뜨렸더다면 꽤 골치 아플 이 모든 과정들을 한 번에 해결해보세요.
이런 내용을 배워요
프론트엔드(그누보드)
로컬 환경 설치
커뮤니티·쇼핑몰 핵심 기능 습득
백엔드(스프링부트)
REST API 개발 & 외부 API 연동
DB 연동·Bulk 처리
인프라(AWS, Docker, Nginx)
EC2, RDS 설정으로 안정적 서버 환경 구축
Docker & Nginx로 배포 환경 최적화
CI/CD(GitLab)
자동 배포 파이프라인 세칭
매번 빠른 업데이트와 유지보수 실현
Google 서비스(SEO & 수익화)
Search Console, Analytics, Adsense 활용
검색 노출 및 광고 수익화 방법 학습', -- 내용 2
'a6bfa276-f609-4ddb-877a-5c1227c3a602.jpg'
);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values ('admin', 
'AI로 기획부터 개발까지! LLM 주도 Next.js 쇼핑몰 제작', -- 제목
'v0, cursor AI 및 각종 LLM과 함께 PRD부터 AI 상품 추천까지! 실전 프로젝트로 Next.js 쇼핑몰을 완성하세요', -- 부제목
'웹 개발', -- 카테고리
4.3,
now(),
'https://www.inflearn.com/course/llm-nextjs-ai%EA%B8%B0%ED%9A%8D-%EC%87%BC%ED%95%91%EB%AA%B0%EC%A0%9C%EC%9E%91', -- 출처
'Next.js 기반 쇼핑몰 개발

v0를 활용한 PRD 작성 및 개발 자동화

PostgreSQL + Prisma로 DB 설계

AI 상품 추천 시스템 구축 (임베딩 활용)

토스페이먼츠 결제 시스템 연동

Next.js 기반 관리자 페이지 구현

Docker와 Nginx를 활용한 프로젝트 배포

VPN과 포트 포워딩을 활용한 로컬 배포 기초', -- 내용 1
'🛍 AI + Next.js로 쇼핑몰을 개발하는 최적의 방법!
이 강의에서는 ChatGPT와 각종 LLM을 활용하여 PRD(제품 요구사항 문서)를 자동 생성하고, v0를 이용해 디자인과 프로젝트 초안을 구성하며, nextjs와 Prisma, PostgreSQL을 사용하여 쇼핑몰을 구축하는 실전 개발 프로세스를 배웁니다.

뿐만 아니라, AI 임베딩을 활용한 상품 추천 시스템 구현, Docker기반 환경 설정, VPN을 활용한 로컬 배포 방법까지 경험할 수 있어, 단순한 웹사이트 제작을 넘어 AI와 최신 웹 기술을 적용한 실무급 프로젝트 개발을 목표로 합니다.

실습 환경
운영 체제 및 버전(OS): Windows, macOS, Linux(Ubuntu 포함)에서 실습 가능
사용 도구:
Next.js 개발을 위한 Node.js (최신 LTS 버전 권장)
데이터베이스 관리를 위한 PostgreSQL 및 Prisma
프로젝트 배포 및 운영을 위한 Docker & Nginx
AI 임베딩 및 자동화를 위한 ChatGPT API
PC 사양:
최소 8GB RAM, 권장 16GB 이상
CPU: 4코어 이상 (개발 및 Docker 컨테이너 실행을 고려)
스토리지: SSD 권장 (데이터베이스 및 프로젝트 빌드 속도 최적화)
GPU (선택사항): AI 임베딩 기능을 로컬에서 실행하려면 NVIDIA GPU 및 CUDA 지원 필요
📚 학습 자료
제공 방식:
✅ 강의 플랫폼의 학습자료 업로드 기능을 통해 제공
✅ 필요한 경우 GitHub 링크 추가 제공 (코드 및 실습 자료)
포함된 자료:
✅ GitHub 소스 코드 – 프로젝트 코드 및 샘플 예제 제공
✅ Docker 설정 파일 – 개발 및 배포 환경 구성 예제
✅ 추가 참고 자료 링크 – 공식 문서 및 관련 블로그
📌 선수 지식 및 유의사항
필요한 선수 지식:
✅ JavaScript 및 기본적인 TypeScript 문법 (Next.js 개발을 위해)
✅ Next.js 기본 개념 (페이지 라우팅, API Routes 등)
✅ SQL 및 데이터베이스 기초 (PostgreSQL과 Prisma 사용)
✅ RESTful API 개념 (데이터 통신 이해)', -- 내용 2
'a6f6a6ae-298f-4aa0-a5de-358691f4b760.cover'
);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values ('admin', 
'바이브 코딩으로 만드는 재미있는 재무제표 (커서 ai)', -- 제목
'프롬프트만으로 재무제표를 분석하고 시각화하는 매력적인 코딩 세계로 초대합니다. 코드 한 줄 없이 AI와 상의하며 서비스를 만들고 배포까지 진행합니다.', -- 부제목
'AI 코딩', -- 카테고리
4.9,
now(),
'https://www.inflearn.com/course/%EB%B0%94%EC%9D%B4%EB%B8%8C%EC%BD%94%EB%94%A9-%EC%9E%AC%EB%AF%B8%EC%9E%88%EB%8A%94-%EC%9E%AC%EB%AC%B4%EC%A0%9C%ED%91%9C', -- 출처
'전자공시사이트(Open DART)에서 데이터 불러오기

커서 AI를 활용한 재무제표 데이터 분석 및 시각화

단순한 프롬프트 작성법으로 복잡한 재무 분석 자동화하기

내가 만든 재무제표 웹서비스 직접 배포하기', -- 내용 1
'💡 바이브 코딩으로 펼치는 생동감 넘치는 재무제표 여행 (커서 AI)
프롬프트엔지니어링 만으로 재무제표를 분석하고 시각화하는 매력적인 코딩 세계로 초대합니다. 코드 한 줄 없이 AI의 힘으로 숫자 속에 숨겨진 비즈니스(business) 스토리를 발견하세요. 🧑‍💼

기술? 몰라도 괜찮습니다!
전자공시사이트에서 실시간으로 데이터를 불러오고, LLM과 대화하며 나만의 재무제표 서비스를 직접 만들어볼 수 있습니다.

지금, 커서 AI와 함께 살아 숨 쉬는 데이터를 경험하세요!

🌟 강의 특징
✅ 코딩 없이도 가능한 개발
코드를 직접 작성하지 않고도 AI와의 대화만으로 복잡한 웹 애플리케이션을 개발하는 방법 학습
커서 AI를 활용한 바이브 코딩 기법으로 개발 과정을 혁신적으로 간소화

✅ 실무적인 데이터 분석 및 시각화 기술
실제 기업 데이터를 활용한 재무 분석으로 비즈니스 인사이트 도출
인공지능을 활용한 재무 데이터 해석 및 보고서 자동화 기술 습득

✅ AI와 함께 배우는 웹앱 배포 실전 가이드
기술을 몰라도 LLM과 대화하면서 웹앱 배포
비전문가도 이해할 수 있는 쉬운 설명 생성 방법 마스터', -- 내용 2
'44a659c0-4463-4231-bccf-211cafa78e84.png'
);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values ('admin', 
'AI 시대, 이제 현업이 개발한다! Cursor와 ChatGPT로 시작하는 바이브코딩', -- 제목
'개발팀 눈치 보지 말고, 이제 내가 직접 만든다! 이 강의는 기획, 마케팅, 영업 등 실무 담당자가 개발자 도움 없이 AI도구를 활용하여 웹서비스의 핵심 기능을 직접 구현할 수 있도록 도와줍니다.', -- 부제목
'AI 코딩', -- 카테고리
4.6,
now(),
'https://www.inflearn.com/course/cursor-chatgpt-%EB%B0%94%EC%9D%B4%EB%B8%8C%EC%BD%94%EB%94%A9%EC%8B%9C%EC%9E%91', -- 출처
'웹사이트 개발에서 Cursor 활용법

데이터 전치리 및 SQL 작성을 위한 ChatGPT 활용법

Python 웹프레임워크인 FastAPI 활용법', -- 내용 1
'🚀 머릿속에 떠오른 아이디어, 오늘 웹서비스로 만들고 싶으신가요?
이제는 AI 도구와 약간의 노하우만 있다면, 누구나 자신의 아이디어를 직접 웹서비스로 만들어볼 수 있는 시대입니다.이 강의는 기획자, 마케터, 영업, 데이터 분석가 등 비개발 분야 실무자가 ChatGPT와 Cursor를 활용해 바이브코딩으로 웹서비스를 직접 기획하고 구현할 수 있도록 설계되었습니다.이 강의를 통해 기획서만 작성하는 데 그치지 않고, 직접 만든 웹서비스로 실험하고, 소통하며, 실행 중심의 커뮤니케이션을 경험해보세요.당신의 실무 역량은 분명히 한 단계 더 도약할 것입니다.

🤔AI 도구를 활용하면 실무자도 개발자가 될 수 있을까요?
지금의 AI 도구는 숙련된 개발자의 수준까지는 아니지만, 기획자나 마케터, 데이터 분석가가 직접 원하는 기능과 인터페이스를 빠르게 구현하고 실험해보는 데는 충분합니다.
저 역시 서비스 기획 및 데이터 분석 실무자로서, Cursor와 ChatGPT를 활용해 단 이틀 만에 서비스를 기획하고 개발, 실제 유저를 기반으로 테스트를 진행하고 개선사항을 반영한 뒤, 개발팀에 정식 요청까지 마친 경험이 있습니다.
이 강의는 단순한 기술 강의가 아니라, 당신의 아이디어를 실행 가능한 서비스로 바꾸고, 실무 소통을 획기적으로 개선할 수 있는 경험을 제공합니다.
이제, 기획서보다 먼저 서비스부터 만들어보세요.

이런 내용을 배워요.
본 강의는 2개의 메인 실습을 바탕으로 아이디어를 기획하고, 서비스를 개발하는 과정을 다룹니다.

2개의 실습 모두 Python FastAPI를 사용하여 웹서비스를 구현합니다.(아래 링크에서 확인해보세요.)
실습1. 대한민국 자살 통계 대시보드(바로가기)
실습2. 대한민국 축제 정보 서비스(바로가기)


실습 1. 공공데이터로 인터렉티브한 대시보드 만들기(통계청 KOSIS 데이터)
통계청 KOSIS 자살률 데이터를 ChatGPT로 전처리하고 Cursor를 통해 인터렉티브한 대시보드 만드는 실습입니다.

ChatGPT를 활용해 복잡한 데이터를 전처리
통계청 KOSIS데이터는 바로 대시보드에 적용하기 어렵기 때문에 전처리가 필요합니다. 과거에는 상당히 어렵고 오랜 시간이 걸렸던 전처리를 이제 ChatGPT로 손쉽게 수행할 수 있습니다.

조건에 따라 다양하게 변화하는 차트 웹페이지 구현

시도별로 자살률이 어떻게 다른지, 성별로 연령별로 구분했을 때, 어떤 집단이 고위험 군인지 궁금하시죠? 토글이나 셀렉트 박스로 다이나믹하게 변화는 차트를 구현하실 수 있습니다.

실습 2. 오픈API로 지역별 행사 찾기 서비스(공공데이터포털 관광정보 데이터)
공공데이터포털의 한국관광공사 행사 API를 활용해, 지자체별 실시간 행사 정보를 보여주는 벡엔드 API 를 개발하고, 행사 정보에 대한 프론트엔드 웹서비스를 구현합니다.

1) 데이터베이스 및 벡엔드 API 구축
ChatGPT를 활용해 DB SQL문 작성

SQL문이 익숙하지 않는 현업 담당자도 내가 하고자 하는 서비스에 대한 명확한 데이터만 정해지면 SQL문 자체는 ChatGPT에 질의해서 SQL을 손쉽게 생성할 수 있습니다.

FastAPI로 행사정보에 대한 벡엔드 API 만들기

공공데이터포털의 관광정보 API문서, API 테스트 기능, Cursor의 Agent 기능 등을 활용하여 FastAPI 기반 벡엔드 서버를 손쉽게 개발할 수 있습니다.

2) 프론트엔드 웹페이지 개발
지역별 행사 및 세부 정보를 제공하는 웹페이지 개발

벡엔드 API서버와 통신하여 달력UI로 각 지역별 행사 목록이 출력되는 웹페이지 작성합니다. 달력의 행사 목록이나 하단의 카드 목록을 클릭하면 각 행사에 대한 세부 정보를 확인할 수 있습니다.', -- 내용 2
'089cc453-c2fd-43a2-8ed4-5b9d757dd0b1.jpg'
);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values ('admin', 
'스트림릿(Streamlit)을 활용한 파이썬 웹앱 제작하기', -- 제목
'Streamlit을 활용해 빠르고 쉽게 나만의 데이터 분석 앱을 구축하고 공유해 보세요!', -- 부제목
'웹 개발', -- 카테고리
4.9,
now(),
'https://www.inflearn.com/course/%EC%8A%A4%ED%8A%B8%EB%A6%BC%EB%A6%BF-%ED%99%9C%EC%9A%A9-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%9B%B9%EC%95%B1-%EC%A0%9C%EC%9E%91', -- 출처
'Streamlit을 활용한 데이터 분석 웹 앱 구축

Streamlit을 활용한 데이터 시각화

다양한 API를 활용한 Streamlit 웹 앱 구현', -- 내용 1
'스트림릿(Streamlit)이란?
Streamlit은 데이터를 활용해 빠르게 프로토타입 형태의 웹 앱을 구현할 수 있는 도구입니다. 간단한 기능을 가진 데이터 웹 어플리케이션을, 빠르고 간단하게 눈으로 확인할 수 있는 웹 형태로 만들 수 있다는 것이 가장 큰 장점입니다.


Streamlit은 파이썬(Python)을 사용합니다. 데이터를 다루는 데 있어 친숙한 파이썬 환경이기에 사용하는데 거부감이 없고, Streamlit 패키지 설치 후 적절히 함수를 호출해주면 되기 때문에 간단합니다.

Streamlit은 파이썬 스크립트를 읽어서 간단한 웹 서버를 실행합니다. 결과물을 바로 확인할 수 있고, 스크립트를 업데이트 할 때마다 실시간으로 변경사항도 확인할 수 있습니다.

Streamlit의 활용도!
데이터 분석 보고서, 대시보드 구축, 머신러닝 모델 배포를 위한 데모용 웹앱을 매우 쉽게 만들 수 있습니다.
잠재적 고객에게 내가 구상한(혹은 이미 구축한) 데이터 분석/머신러닝 서비스를 직접 시연할 수 있습니다.
고객이 원하는 데이터를 직접 업로드하여 동적 시각화(대시보드)를 경험할 수 있습니다.
머신러닝 모델을 활용한 서비스 구축이 가능합니다.
그밖에 간단한 파이썬(Python) 코딩으로 웹앱을 쉽게 만들 수 있습니다.

Streamlit이 지원하는 다양한 위젯(Widgets)
아래에 나열된 위젯으로 쉽게 웹앱을 제작할 수 있습니다.


수강 전 확인해주세요!

초보자도 쉽게 배우고 따라할 수 있도록 프로젝트와 실습 중심으로 강의가 진행됩니다.
강의와 함께 실습자료가 별도로 제공됩니다.
본 강의와 연관된 영상은 유튜브 채널에서 학습할 수 있습니다.
또한, 테디노트 YouTube에 업로드된 강의 외에도 인프런에서만 시청하실 수 있는 유료 콘텐츠를 앞으로 계속 추가할 예정입니다.
인프런 유료 강의를 수강하실 경우, 수익금은 전액 불우한 어린이를 위한 기부금으로 사용됩니다.', -- 내용 2
'a26b53b8-b9d9-4166-842c-0f0bcd42b4f3.avif'
);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values ('admin', 
'[중급] 맛집 지도앱 만들기 (React Native & NestJS)', -- 제목
'리액트 네이티브를 이용해서 나만의 맛집 기록앱을 개발하고 출시하는 과정을 배우게 됩니다. 디자인부터 프론트 및 백엔드 개발, 배포까지 전 과정을 준비했어요.', -- 부제목
'모바일 앱 개발', -- 카테고리
4.9,
now(),
'https://www.inflearn.com/course/%EB%A7%9B%EC%A7%91-%EC%A7%80%EB%8F%84%EC%95%B1-%EB%A7%8C%EB%93%A4%EA%B8%B0-reactnative-nestjs', -- 출처
'React Native

React Query

Zustand

TypeScript

Nest.js

TypeORM', -- 내용 1
'강의 소개
최신 기술을 사용하여 나만의 맛집 지도 서비스를 만들어보세요! 프론트 및 백엔드 개발, 디자인, 배포까지 전 과정을 준비했어요. 피그마로 구축된 디자인시스템을 기반으로 앱을 개발합니다.

리액트네이티브로 앱을 개발하여 앱스토어/플레이스토어 모두 출시하기까지, 다양한 노하우를 알려드려요. 기술스택은 React Native, TypeScript, NestJS, react-query, zustand를 사용합니다.

프론트엔드/백엔드 모두 개발하여 처음부터 끝까지 하나의 서비스를 완성하게 됩니다.
강의 시간은 프론트엔드 14시간 + 백엔드 3시간으로 구성되어 있으며, 모든 수업별 소스코드를 제공합니다.

다루는 내용
React Navigation
TypeScript
Tanstack Query (React Query)
Zustand
서버 상태 & 전역 상태 관리
JWT 기반 인증
카카오 로그인, 애플 로그인
공통 컴포넌트 개발
합성 컴포넌트 패턴
커스텀 훅 패턴
Suspense, ErrorBoundary
앱 배포
구글맵 API, 카카오맵 API 연동
마커 표시 및 클러스터링
인피니트 스크롤링
캘린더 구현
다크모드
이미지 업로드
앱 권한 다루기
날짜 함수 다루기
라이브러리 수정하기
WebView
NestJS & TypeORM
PostgreSQL

⚡참고 사항
강의는 ReactNative CLI로 진행됩니다. Expo를 사용하지 않아요.
중급 이상(전문성을 높이는) 난이도 강의입니다. 입문/초급자에게는 맞지 않을 수 있어요.
JavaScript/CSS 지식이 필요해요. map, filter 등 ES6 문법을 설명하지 않아요.
React 지식이 필요해요. 기본 Hooks(useState, useEffect)등을 설명하지 않아요.
강의 개발환경은 Mac OS 입니다. (Window 사용자는 Android 개발만 가능합니다. iOS 앱개발을 위해서는 Mac 환경이 필요합니다.)
이 강의는 3840 × 2160 (4K) 해상도로 제작되어 높은 해상도를 선택하면 더 좋은 화질로 수강하실 수 있습니다.
유의사항 안내)

* 이 강의는 React Native CLI를 사용하는 중급 강의입니다. 이 점 꼭 유의하여 수강신청 해주세요! (Expo를 이용한 개발은 아바타 커뮤니티앱 만들기 강의를 참고해주세요.)

* 리뉴얼이 진행중인 강의입니다. 2025년 7월부터 시작하여 순차적으로 업데이트 강의(섹션13~24)가 업로드될 예정입니다. 이점 유의하여 수강신청 해주세요!', -- 내용 2
'7cc01cb7-c0b9-4448-b44b-f1d39036bd00.png'
);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values ('admin', 
'순수 html/css/js만을 활용한 반응형웹 제작[실전편] 부트캠프', -- 제목
'부트스트랩(BOOTSTRAP)을 사용하지 않고 순수하게 html/css/js 만을 활용하여 디바이스별로 다양한 레이아웃을 구현할 수 있는 반응형 웹페이지 만들기[2024년 최신판] 수업의 실전 제작수업입니다.', -- 부제목
'프론트엔드', -- 카테고리
5.0,
now(),
'https://www.inflearn.com/course/html-css-js-%EB%B0%98%EC%9D%91%ED%98%95%EC%9B%B9%ED%8E%98%EC%9D%B4%EC%A7%80-%EC%8B%A4%EC%A0%84%ED%8E%B8', -- 출처
'html을 활용하여 웹의 구조 생성하기

css3를 활용하여 구조에 스타일 입히기

js를 활용하여 클릭이나 스크롤같은 사용자의 이벤트시 동작하는 요소 만들기

@mediaquery를 이용하여 디바이스별로 스타일 다르게 변경하기

HTML/CSS/jQuery를 활용한 반응형 웹페이지 / 웹앱 / 웹 디자인 실제 제작하기

디바이스 장치에 따라 레이아웃이 바뀌는 반응형 웹 실무 메인 페이지를 clonning 해요

부트스트랩의 최대의 단점인 판박이처럼 찍어내는 비슷한 디자인과 구성요소를 활용하는 것이 아닌, 본인이 원하는 유니크한 반응형 웹 실무 사이트 싱글 페이지를 직접 코딩을 하여 제작해봐요', -- 내용 1
'🏆 이런 내용을 배워요.
🚀 웹 트랜드인 메인 이미지들이 회전하는 상단 캐로셀과 스크롤시 최상단의 네비게이션의 스타일이 바뀌며 달라붙는 sticky-top 네비게이션 바와 컨텐츠 내용에 따라서 자동으로 메뉴가 활성화되는 scrollspy를 html, css, js 코드를 직접 짜서 구현



💻📱 데스크탑, 타블렛, 스마트폰에서 컨텐츠의 사이즈와 레이아웃의 위치가 바뀌는 반응형 웹을 @mediaquery를 활용하여 구현



🎨 마우스 호버시 자연스럽게 이미지의 크기나 버튼의 색이 변화되는 css3의 transition, animation 과 디바이스 사이즈에 따라 레이아웃의 모양이 바뀌는 컨텐츠 구현



👁‍🗨 데스크탑, 타블렛에서는 보이고 스마트폰에서는 없어지는 컨텐츠와 토글 버튼을 클릭시 보이고 숨겨지는 컨텐츠를 html, css, js를 통해 직접 코딩으로 구현

🎬 강의소개
부트스트랩의 최대의 단점인 판박이처럼 찍어내는 비슷한 디자인과 구성요소를 활용하는 것이 아닌, 본인이 원하는 유니크한 반응형 웹 실무 사이트 싱글 페이지를 직접 코딩을 하여 제작해 봅니다.
이 강의에서는 디바이스 장치에 따라 레이아웃이 바뀌는 반응형 웹 페이지 개발을 위해 순수하게 html로 구조를 짜고 css로 스타일을 만드는 방법을 100% 활용하여 제작실습을 통해 완성도 있으면서도 유니크한 반응형 웹사이트를 제작할 수 있는 실력을 다질 수 있습니다.

웹 디자인, 반응형 웹을 제작하기 위한 HTML/CSS/js 이론에 대한 기본 지식이 있는 분들을 대상으로 하지만, 기본 지식이 부족하더라도 강의를 반복적으로 시청하며 따라할 수 있도록 제작하였으며 실무에서 사용하는 단축키를 이용하여 html 코드를 빠르게 만드는 방법인 emmet의 사용법도 학습할 수 있습니다.

강의내용인 제작실습을 눈으로 보는 것만이 아닌 실제로 따라 하면서 본인 스스로 반응형 웹 구성요소를 하나하나 구축하다 보면 어느새 코딩능력도 향상되고 퀄리티 있는 반응형 웹사이트가 자연스럽게 완성됩니다.

부트스트랩 같은 프레임워크를 이용하면 프레임워크들이 가지고 있는 다소 한정적인 스타일의 클래스명을 사용하거나, 이미 만들어져 있는 컴포넌트를 사용하기 때문에 웹페이지의 결과물이 다소 비슷하게 만들어지는 단점이 있는데 이를 극복하기 위하여 부트스트랩 같은 프레임워크를 사용하지 않고도 본인만의 유니크한 반응형 웹사이트를 직접 코딩을 통하여 제작하는 방법을 디테일하게 실습해 보실 수 있습니다.

완강 후에는 막연히 어렵게만 생각했던 반응형 웹 제작이 생각보다 쉽다라는 것을 느끼고 앞으로는 본인만의 반응형 웹을 손쉽고 다양하게 제작하게 될 것입니다.

✨ 이 강의의 특징
📌 반응형 레이아웃을 만들기 위한 css의 @mediaquery 사용법을 배우고 반복적으로 실습합니다.

📌 개발자가 원하는 레이아웃이나 스타일로 변형하는 부분 또한 반복적으로 실습합니다.

📌 동적으로 반응하는 요소도 직접 만들어보기 위해 실무에서 가장 많이 활용되는 캐로셀 요소와 스크롤시 상단의 네비게이션 메뉴의 활성화 스타일이 바뀌는 구성요소를 js 코드를 통해 구현합니다.

📌 실제 웹사이트에서 많이 활용되는 애니메이션 기법을 연습하여 원하는 요소에 마우스 호버시 디자인 요소가 애니메이션되는 인터랙티브 웹을 실습합니다.

📌 외부 플러그인을 연결하여 내가 만든 코드에 다양한 기능을 손쉽게 활용할 수 있는 라이브러리를 추가하는 방법을 실습합니다.', -- 내용 2
'5ab056d0-9459-4b7f-9d2f-9bfbc723ea12.avif'
);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values ('admin', 
'파이널 코딩테스트 : 프론트엔드', -- 제목
'실제 기업에서 출제되는 Front-End 코딩 테스트를 참고하여 제작된 10가지 문제를 함께 풀어보며 2차 코딩 테스트(구현 테스트)를 완벽하게 대비해 봅시다!', -- 부제목
'프론트엔드', -- 카테고리
4.6,
now(),
'https://www.inflearn.com/course/%ED%94%84%EB%A1%A0%ED%8A%B8%EC%97%94%EB%93%9C-%ED%8C%8C%EC%9D%B4%EB%84%90-%EC%BD%94%EB%94%A9%ED%85%8C%EC%8A%A4%ED%8A%B8', -- 출처
'2차 코딩 테스트(구현 테스트) 대비

HTML, CSS, JS 웹/앱 페이지 구현

Selenium

CyPress', -- 내용 1
'[검색 최적화를 위한 텍스트입니다]

실제 기업에서 출제되는 Front-End 코딩 테스트를 참고하여 제작된 10문제를 함께 풀어보며
2차 코딩 테스트(구현 테스트)를 대비할 수 있는 강의입니다.

프론트엔드 개발자 코딩 테스트!
왜 모의고사가 필요할까요?

1차 코딩 테스트인 알고리즘 테스트의 경우,
대비할 수 있는 강의, 웹 사이트, 모의 테스트 등을
쉽게 찾아볼 수 있습니다.

하지만 2차 코딩 테스트(구현 테스트)의 경우,
공개된 출제 문제를 찾아보기 힘들어요.
기업마다 어떤 문제가 출제될지 알 수 없어요.
어떤 코드를 작성해야 가산점이 있는지 모르겠어요.
나의 실력을 정확히 알 수 없어서 뭐부터 준비해야 할지 잘 모르겠어요!

그/래/서
파이널 코딩 테스트 : 프론트엔드 를 준비하였습니다!

10개의 2차 코딩 테스트 예제 문제를 함께 풀어보면서
구현 테스트에 대한 감을 잡을 수 있습니다.

보통 개발자의 취업 프로세스는 다음과 같습니다.

이력서 제출 → 1차 코딩 테스트 → 2차 코딩 테스트
→ 실무 면접 → 임원 면접 → 최종 합격

코딩 테스트 1차에서는 기본 소양을 알아보는 알고리즘 코딩 테스트를,
2차에서는 실무 능력을 알아보기 위한 과제를 제출합니다.

2차 코딩 테스트에서 과제 구현이란
정확히 어떤 것일까요?

실무 능력을 알아보기 위한 과제로는
보통 UI 디자인과 기능 명세를 주고
똑같이 구현하라는 문제가 출제됩니다.

주어진 과제를 구현하고 제출하면,
면접에서는 코드를 왜 그렇게 작성하였는지,
코드 구현에는 어떤 어려움이 있었고, 어떻게 극복했는지 등을
인터뷰하게 됩니다.

그렇다면 우리에게는 어떤 대비가 필요할까요?
✅  실제 기업에서 출제되는 유사 내용의 문제 풀어보기
✅  난이도별 학습(기업마다 문제 난이도가 상이하기 때문에)
✅  스스로 어떤 부분이 취약한지 체크
✅  반복 학습을 통한 Front-End 구현의 자신감 얻기

본 강의에서는 현직 Front-End 개발자와 함께
다양한 난이도로 구성된 10개의 예제 문제를 하나씩 차근차근 풀어보면서
스스로 어떤 부분이 취약한지 체크해보고,
페이지 구현에 대한 자신감을 얻으실 수 있습니다.

[강의 시뮬레이션]
1. 노션 페이지에서 문제 UI 디자인과 요구사항 명세를 확인한다.
2. 제공된 피그마 링크와 강의 자료를 활용하여 직접 코드를 작성해본다.
3. 강의 영상을 보면서 현직 개발자가 작성한 코드를 확인하고 문제점을 보완한다.

[파이널 코딩 테스트 강의 3/줄/요/약]
- 프론트엔드 개발자 취업/이직 준비
- 페이지 구현에 대한 자신감
- 디자이너와의 협업(in Figma)

본 강의에서는 예제의 UI 디자인 이미지와 함께 Figma(피그마) 링크가 제공됩니다.
Figma에서 디자이너가 디자인한 결과물의 픽셀 간격, 폰트 사이즈, 색상 코드 등을
직접 확인해가며 효율적인 협업을 연습해 볼 것입니다.

[Q&A]
Q. 프론트엔드 개발자 취업시 2차 코딩 테스트 대비는 필수인가요?
2차 코딩 테스트에서 어떤 난이도의 문제가 출제될지 알 수 없기 때문에
최대한 많은 예제 문제/튜토리얼을 경험해보시는 것이 좋습니다 :)

Q. Windows/MacOS 개발 환경을 제공하나요?
네, Windows, MacOS 모두 원활하게 수업을 들으실 수 있습니다 :)

Q. 어떤 코드 에디터를 사용하나요?
본 강의는 Visual Studio Code로 수업이 진행됩니다.

수강생 여러분들이 더 편리하게 공부하실 수 있도록
Notion 페이지 링크, 강의 자료(이미지 소스) 등 강의 수강 시 필요한 모든 것을 제공합니다.', -- 내용 2
'8f302a3f-fa3c-45af-be12-8bd595cfe07d.avif'
);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values ('admin', 
'[2024]리베하얀의 HTML 기초 실무', -- 제목
'HTML 기초문법을 다루는 강의입니다. 다양한 사례의 미션이 존재하며, 실무에서 사용가능한 최신문법까지 알려드립니다.', -- 부제목
'프론트엔드', -- 카테고리
5.0,
now(),
'https://www.inflearn.com/course/%EB%A6%AC%EB%B2%A0%ED%95%98%EC%96%80-html-%EA%B8%B0%EC%B4%88%EC%8B%A4%EB%AC%B4', -- 출처
'HTML

웹접근성

NVDA

웹표준', -- 내용 1
'리베하얀의 HTML 기초 실무 - 2024
해당 강의는 HTML 기본 문법을 다루며, 간단한 예제를 통해 응용을 해볼 수 있습니다.
무엇보다 중요한건 주어진 디자인 샘플로 콘텐츠 의미에 맞게 표현하는 것입니다.
표준문법을 준수하여 접근성, 사용성을 향상시켜 주세요.


이런 내용을 배워요

1⃣ HTML
읽는법부터 깊이있는 실무 이야기까지 다양하게 HTML을 소개해 드립니다. HTML 최신 스펙은 물론 추가된 속성(Attribute)까지 안내해드립니다.


2⃣ NVDA
HTML 표준문법을 준수하는것만으로 사용성과 접근성이 상당히 향상됩니다. 해당 과정에서는 중요한 요소마다 NVDA로 들어보게 됨으로써 일반인뿐만 아니라 장애인들은 어떻게 느끼는지 간접체험할 수 있습니다.


3⃣ 30가지 이상의 실무 미션
해당 과정의 주된 목적은 실무 응용입니다. 기초 문법을 학습하신 뒤 공유받은 피그마 샘플을 가지고 미션을 수행해보세요. 저와 함께 고민하면서 해결해 나아간다면 많은 자신감을 얻으실 수 있습니다.', -- 내용 2
'0ad272fb-0d47-4911-835d-b1e5cd2ee0d7.avif'
);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values ('admin', 
'[2025 리뉴얼]플러터플로우로 코딩 없이 한달 안에 앱 만들기', -- 제목
'세계 1위 노코드 앱빌더인 플러터플로우(FlutterFlow)를 통해 앱을 만들어 보는 입문 강의입니다! 🤗 트위터를 똑같이 만들면서 자연스럽게 개념을 배워나가니까... 쉽고 재밌을 거예요! 🥳', -- 부제목
'모바일 앱 개발', -- 카테고리
4.6,
now(),
'https://www.inflearn.com/course/%EB%85%B8%EC%BD%94%EB%93%9C-%ED%94%8C%EB%9F%AC%ED%84%B0%ED%94%8C%EB%A1%9C%EC%9A%B0-%EC%95%B1-%EB%A7%8C%EB%93%A4%EA%B8%B0', -- 출처
'아이디어를 상상만 하는 대신, 직접 만들 수 있게 됩니다.

서버 연동, 조건문 등 전반적인 프로그래밍적 사고를 익히게 됩니다. (다른 프로그래밍을 배울 때도 기반이 됩니다.)

짧으면 1주, 길어도 한 달! 엄청나게 빠르게 앱을 만들 수 있게 됩니다.', -- 내용 1
'83만명이 애용하는 세계 1위 노코드 앱 빌더,
플러터플로우(FlutterFlow)

👍🏻 빠르게 만드세요!
플러터플로우는 파워포인트 만들듯 앱을 만들 게 해줍니다.

정말 쉽고, 빠릅니다.

원래는 3개월 걸릴 일도, 플러터플로우는 3주 만에 가능하죠.

👍🏻 유연하게 만드세요!
노코드 툴은 장난감 같다구요?

플러터플로우에는 복잡한 로직 뿐만 아니라, 직접 코드까지 삽입할 수 있습니다.

플러터 코드로 다운로드도 됩니다.

만들어가면서, 차근차근 웬만한건 다 배울 거에요!
개념을 쭉 듣고, 나중에서야 실습이 시작되는 강의는 배우기도 어렵고 지루하죠?
본 강의에서는 바로 트위터를 만들어가면서, 코드없이 앱을 만드는 법을 배웁니다.
레이아웃 짜기부터 서버연동, 채팅, 구글로그인, 검색 등등..!


그리고 이 개념을 탑재한다면 플러터플로우로 웬만한 건 다 빠르게 만들 수 있게 될 것이고

새로운 것을 더 배우기도 수월할 거에요!

💪 이 강의는 투자 대비 비용을 10배 이상 아껴줄 수 있습니다.
코딩을 통해 직접 앱을 만드는 것은 정석적인 방법입니다.
하지만, 만약 앱이 실패한다면, 수많은 시간과 비용을 낭비하게 됩니다.

하루 개발자 인건비보다 적은 비용을 투자하여, 정말 빠르게 앱을 만들 수 있는 플러터플로우를 배워보세요.

👆강의는 계속해서 업데이트 됩니다!
플러터플로우는 계속 버젼이 업데이트 되어, 이전 강의가 소용 없을 때도 있습니다.

또, 강의 내용 중에서 미처 설명을 충분히 못하기도 합니다.

다행히, 본 강의자는 부족한 부분에 대해 매우 진심입니다! ㅎㅎ

강의를 신청하신 분들이 불편함이 없고 돈이 아깝지 않도록, 강의가 보완됩니다.

1차 업데이트 [23/12/21] : 강의 2개 추가 (약 15분 분량, 자막도 추가됨)
[신규추가] 수업의 목표 : 커리큘럼 초기에서 수업에서 무엇을 다룰 것이고, 다 배우고 나면 어떤 것을 할 수 있게 되는지, 그리고 다루지 않는 것은 무엇인지 청사진을 그려주는 단계를 추가했습니다.
[신규추가] 템플릿으로 시작하지 않은 이유 : 처음에 빈 프로젝트부터 만들었는데, 왜 템플릿부터 시작하지 않았는지 이유를 다루는 강의를 추가했습니다. 템플릿으로 시작하는게 더 어렵기 때문입니다.
2차 업데이트 [24/1/12] : 강의 11개 추가 (약 1시간 30분 분량, 자막도 추가됨)

[업데이트] 무엇을 만들고 배우게 될까요 : 2부 강의가 추가됨에 따라, 수업의 목표 강의를 재촬영 하였습니다.
[업데이트] 파이어베이스 서버 연동하기 : 최근에 클릭 한번에 설정할 수 있는 버튼이 추가됨에 따라, 서버연동 강의를 재촬영 하였습니다.
[신규추가] 편집기 팁 : 페이지와 위젯을 왔다갔다 하지 않고 한 화면에서 다루는 방법을 알아봅니다.
[신규추가] 애니메이션 : 애니메이션을 설정하는 방법을 알아봅니다.
[신규추가] 변수 : 앱 변수, 페이지 변수를 설정하고 활용하는 방법을 알아봅니다.
[신규추가] Drawer : 햄버거버튼 만드는 방법을 알아봅니다.
[신규추가] 중복개발 줄이기 : Theme Widget Style, Template, Component를 통해 중복개발을 줄이는 방법을 알아봅니다.
[신규추가] 컴포넌트 심화 : 트윗 하나를 통째로 컴포넌트로 변환하는 예시를 통해, 컴포넌트를 더 잘 활용하는 방법을 알아봅니다.
[신규추가] 채팅 : 1:1, 그룹채팅기능을 간단히 추가하는 방법을 알아봅니다,.
[신규추가] 검색 : Simple Search 기능을 추가하는 방법을 알아봅니다.
[신규추가] 구글 로그인 : 구글 로그인 설정하는 방법을 알아봅니다.

3차 업데이트 [24/3/18] : 강의 2개 추가
[신규추가] X, Y의 의미 : 위젯이 가리키는 위치 값인 X, Y 가 어떻게 작동하는지 알아봅니다.
[신규추가] DataType : 프로그래밍을 접해본 적 없는 분들을 대상으로, Data Type의 종류에 대해 간단히 짚고 넘어갑니다.
4차 업데이트 [25/2/28] : 14개의 강의 리뉴얼 촬영
플러터플로우의 달라진 UI 와 환경에 맞추어 재촬영을 진행했습니다.
이전에 이해하기 어려운 부분에 대해 설명을 보강했습니다.
쉽고 재미있게 플러터플로우를 시작할 수 있는 본 강의를 듣고
자신만의 앱 서비스를 최소비용, 최소시간으로 런칭하세요!', -- 내용 2
'b3cd4109-7c67-4c12-8551-c2bfb152d631.avif'
);

insert into lecture(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2, file1)
values ('admin', 
'[플러터플로우] 실전! 앱 출시를 위한 끝장 노하우!', -- 제목
'플러터플로우를 이용해서 실제 앱을 출시하기 위한 A-Z 노하우를 담았습니다 : 배포부터 인앱결제, 카톡로그인, Supbase까지!', -- 부제목
'모바일 앱 개발', -- 카테고리
5.0,
now(),
'https://www.inflearn.com/course/%ED%94%8C%EB%9F%AC%ED%84%B0%ED%94%8C%EB%A1%9C%EC%9A%B0-%EC%8B%A4%EC%A0%84%EC%95%B1%EC%A0%9C%EC%9E%91-%ED%86%B5%ED%95%A9%EB%85%B8%ED%95%98%EC%9A%B0', -- 출처
'플러터플로우

플러터

앱개발

노코드', -- 내용 1
'알고 계셨나요? 플러터플로우는 전세계 1위 노코드 앱빌더 입니다!
스크린샷 2024-04-26 오후 2.27.44
플러터플로우는 2022년부터 폭발적으로 성장해 현재는 독보적인 세계 1위 노코드 앱빌더 입니다.

(2위는 Adalo)

24년 4월 기준, 125만명이 사용 중입니다.

그렇게 단기간 내에 1위에 오른 이유는 : 코드 없이 만들 수 있음에도 앱을 제작하면 플러터 앱으로 제작되는 데다가, 코드 삽입이나 추출도 가능해 유연하기 때문이었습니다.

(링크 : 플러터플로우는 어떻게 노코드 앱 시장을 장악했을까)



하지만, 플러터플로우로 실제 앱 출시는 쉽지 않습니다.
플러터플로우는 쉽게 시작할 수 있지만, 실제로 마스터 하는 데에는 시간이 많이 걸립니다.

특히 다음과 같은 요인이 문제가 됩니다.

앱 출시에 필요한 절차에서

막히거나

카카오로그인, PG연동 등을 하는 법을 모르거나

코드연동 등


개발기능이 어렵습니다!

💪 그래서 준비했습니다.

플러터플로우로 실제 앱을 출시하는 데 필요한 노하우를 이 하나의 강의에 농축했습니다!
지금까지 웹에서도 찾을 수 없던 진짜 노하우가 공개됩니다!
하나, 앱을 테스트하고 출시하기 위한 구체적인 노하우!


플러터플로우로 만들어둔 앱을 Local Run/Testflight로 테스트하고, 마켓에도 출시를 하고 싶으신가요?

그런데 문서가 너무 어려웠나요?

구체적인 과정을 모두 보여드립니다!

둘, 카톡 로그인과 PG연동, 플러터플로우에서도 됩니다!
카카오 로그인, 플러터플로우에서 기본으로 제공이 안되어 망설이셨나요?

PG 결제, 플로터플로우에서 과연 될지 안될지 모르셨죠?

플러터플로우에서 되게 하는 방법을 A-Z로 다뤄 드립니다.


2025년 업데이트 : 기존 방식(SDK) 외에 자동배포를 활용할 수 있는 REST API 방식을 업데이트 하였습니다!

셋, 코드를 삽입해 가능성을 열배 확장하세요!
플러터플로우는 노코드 툴이지만, 플러터로 코드 추출 뿐만 아니라 Custom Action/Widget 을 통해 코드 삽입도 가능하죠.

PubDev를 통해 외부 라이브러리를 가져올 수도 있구요.

이번 강의에서 그 가능성을 펼치는 방법을 알려 드립니다!

넷, Firebase 말고 Supabase 연동하는 방법까지도!
Firebase 대신 오픈소스인 Supabase 선호하시는 분들도 많으시죠?

하지만 플러터플로우에서 Supabase를 잘 활용하기는 쉽지 않은데요.

연동하는 방법부터 주의점, 시행착오를 줄이는 모든 노하우를 담았습니다!


2025년 업데이트 : 카카오 로그인 강의가 추가되었습니다!', -- 내용 2
'f4bd71a5-ec70-43fe-ab23-3bbfa768ee8e.avif'
);



 insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'Do it! 알고리즘 코딩 테스트: 자바 편', -- title
'이지스퍼블리싱', -- publisher
now(), -- regDate
'2025-06-30 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000216912322', -- bookSource
'IT 기업 취준생의 필독서가 3년 만에 업그레이드해서 돌아왔다!
출제 경향을 완벽하게 반영한 문제로 코딩 테스트 한 번에 합격하자!
‘코딩 테스트는 어떻게 준비해야 할까?’ IT 기업으로 취업 또는 이직을 준비하는 사람들을 합격으로 이끌어 줄 책이 새롭게 출간되었습니다. 네이버, 카카오, 삼성 등 주요 IT 기업의 역대 기출 유형을 분석한 35가지 자료구조와 알고리즘 이론부터 ‘백준 온라인 저지’에서 엄선한 핵심 문제까지 코딩 테스트를 대비해 필요한 모든 것을 한 권에 정리했습니다. 이번 개정판에서는 최신 출제 경향을 반영해 알고리즘 유형과 문제를 보강하고 합격률을 높이는 비법 노트를 담았습니다.

시험이 코앞이라 책 한 권을 다 볼 시간이 없다면? ‘3일 모의고사’ 코스를 활용하세요. 중요한 알고리즘을 다룬 ‘핵심 유형’ 문제 16개, 시험에서 자주 다루는 ‘빈출 유형’ 문제 11개만 빠르게 공부할 수 있습니다. 모든 문제는 ‘백준 온라인 저지’(https://www.acmicpc.net/)에서 실습할 수 있으니, 먼저 책으로 공부한 다음, 백준 온라인 저지에서 다시 한번 풀면서 코딩 테스트를 완벽하게 대비해 보세요!', -- content
'85d21eeb-c83d-47e6-a0d1-01ba818036ae.jpg', -- file1
10.0, -- rating
'9791163037316', -- isbn
616 -- totalPageNum
 );
 
 insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'자바의 정석', -- title
'도우출판', -- publisher
now(), -- regDate
'2025-06-30 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000216877323', -- bookSource
'17년 전 첫 출간 이후로 오랫동안 자바 분야의 베스트 셀러인 자바의 정석의 최신판. 자바의 최신 기능을 자세하고 깊이있게 설명하였다.
저자가 카페에서 20년 넘게 직접 독자들에게 답변을 해오면서 초보자가 어려워하는 부분을 잘 파악하고 책에 반영하였다.
저자가 20년 넘게 꾸준히 집필해온 책으로 깊이와 세밀함 그리고 저자의 정성과 노력이 돋보이는 책이다.', -- content
'89c589db-e7d5-46fb-a12e-9157452cdabf.jpg', -- file1
10.0, -- rating
'9788994492001', -- isbn
 1118 -- totalPageNum
 );

 insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'개발자를 위한 IT 영어 온보딩 가이드', -- title
'한빛미디어', -- publisher
now(), -- regDate
'2025-06-30 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000216866314', -- bookSource
'AI 시대, 번역기로도 안 되는 실무 영어는 따로 있다!
실제 개발자가 마주하는 모든 영어 상황을 한 권에
〈요점 정리 노트〉, 원어민 발음 영상, 온라인 AI 튜터 학습 지원 프로그램 제공
글로벌 무대에서 경쟁력을 갖춘 개발자가 되기 위해 가장 먼저 준비해야 할 것은 바로 개발 현장의 영어이다. 『개발자를 위한 IT 영어 온보딩 가이드』는 코드 리뷰, 기술 문서, 협업 커뮤니케이션, 영어 면접, 해외 취업 준비 등 실무에서 마주치는 영어 상황을 생생하게 다루는 실전형 영어 가이드다. 해외 개발자들과 함께 일하는 실무자의 생생한 경험을 바탕으로, 국내 개발자가 자주 틀리는 표현부터 원어민의 사고방식, 자주 쓰는 업무용 표현까지 친절하게 풀어냈다.

영어 표현 + 용례 + 해설 + 퀴즈 + 실무 사례 + AI 학습 플랫폼까지!
영어, 진짜 써먹을 수 있게 만드는 온보딩 가이드
● 코드 리뷰, 이슈 트래킹, 풀 리퀘스트 작성 등 실무에서 쓰는 영어 표현 수록
● 영어 면접, 코딩 테스트, 포트폴리오 작성까지 커버하는 해외 취업 영어 대응 전략
● 챗GPT 등 AI 툴을 활용한 실전 프롬프트 예시와 오답 피드백 수록
● 음성 인식, 모의면접 훈련, 문장 첨삭 기능이 포함된 AI 학습 프로그램 지원', -- content
'99eb7d39-57dd-4cbb-908b-4af2b3d10c7d.jpg', -- file1
10.0, -- rating
'9791169213523', -- isbn
 320 -- totalPageNum
 );
 
  insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'생성형 AI를 위한 프롬프트 엔지니어링', -- title
'한빛미디어', -- publisher
now(), -- regDate
'2025-06-23 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000216858506', -- bookSource
'AI의 잠재력을 극대화하는 핵심 기술
프롬프트 엔지니어링 완전 정복!
생성형 AI는 우리가 정보를 생성하고 활용하는 방식을 혁신적으로 변화시키고 있습니다. 이 책은 챗GPT, 스테이블 디퓨전 같은 LLM과 확산 모델을 효과적으로 활용하는 방법을 안내합니다. AI 모델을 신뢰성 있고 효율적으로 활용하는 핵심 기술인 프롬프트 엔지니어링을 심층적으로 다루며, 실전에서 바로 적용할 수 있는 원칙과 사례를 제공합니다. AI의 정확성을 높이고, 자동화된 시스템에서 안정적으로 활용할 수 있도록 돕는 이 책은 개발자, 데이터 과학자, AI를 실무에서 활용하려는 모든 이에게 필수 지침서가 될 것입니다.', -- content
'194a9a9a-a4d3-496e-988f-cb72593b6b6c.jpg', -- file1
10.0, -- rating
'9791169213998', -- isbn
492 -- totalPageNum
 );
 
insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'이것이 스프링 부트다 with 자바', -- title
'한빛미디어', -- publisher
now(), -- regDate
'2025-06-20 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000216845943', -- bookSource
'자바 백엔드 실무를 위한 가장 실용적인 Spring Boot 입문서
REST API, 보안, AWS, 도커, 챗GPT 연동까지 직접 구현하며 배우는 실습 중심 학습
** 유튜브 강의, 깃허브 Q&A, 실습 소스, 챕터별 연습문제 등 풀패키지 구성
『이것이 스프링 부트다 with 자바』는 자바를 공부한 독자가 실무와 연결된 백엔드 개발 역량을 갖출 수 있도록 구성된 입문 실습서다. JPA, RESTful API, 보안, 테스트, AWS, 도커, 챗GPT 연동 등 실제 개발 현장에서 자주 사용하는 기술을 중심으로 실습을 따라가며 자연스럽게 습득할 수 있도록 설계했다. 전 과정을 게시판 프로젝트로 구성해 학습 흐름이 명확하며, 개념과 실습을 균형 있게 배치해 혼자서도 개발 실무를 체험할 수 있도록 도와준다. 최신 스프링 부트 3.5.0 버전을 기반으로 하고 있으며, 유튜브 강의와 깃허브 Q&A 등 다양한 학습 지원도 함께 제공한다.', -- content
'72e1a3d0-6e17-46c9-86c2-a4cd962701e2.jpg', -- file1
10.0, -- rating
'9791169213882', -- isbn
656 -- totalPageNum
 );
 
 insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'깃허브 액션으로 구현하는 실전 CI/CD 설계와 운영', -- title
'제이펍', -- publisher
now(), -- regDate
'2025-07-04 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000216841015', -- bookSource
'자동화를 다루는 개발자에서, 자동화를 설계하는 개발자로

테스트, 릴리스, 배포 과정을 자동화하면 개발 속도는 물론 협업 방식까지 달라진다. 깃허브 액션은 자동화를 코드로 구현하는 강력한 도구로, 단순한 시간 절약을 넘어 개발 환경을 직접 설계할 수 있게 해준다. 2025년 일본 ‘IT 엔지니어 도서 대상’ 기술서 부문 TOP 10에 선정된 이 책은 깃허브 액션을 활용해 실무에 맞는 CI/CD 시스템을 설계하고 운영하는 법을 다룬다. 워크플로 구성, 릴리스 자동화, 클라우드 연계, 보안 전략 등 핵심 요소를 체계적으로 설명하고, 다양한 실무 예제를 통해 유지 가능한 자동화 시스템 설계 방법을 제시한다. 복잡한 워크플로를 단순화하고 직접 자동화 시스템을 구축하고자 하는 개발자에게 확실한 길잡이가 되어줄 것이다.', -- content
'919033f1-a23d-4069-9706-f7a86b444d7d.jpg', -- file1
10.0, -- rating
'9791194587248', -- isbn
404 -- totalPageNum
 );
 
 insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'C# 교과서', -- title
'길벗', -- publisher
now(), -- regDate
'2025-06-20 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000216819747', -- bookSource
'기초에서 활용까지 기본기를 탄탄하게 다지는 C# 입문서
최적의 학습 순서로 더 쉽게, 더 효율적으로 배운다!
이 책의 최종 목표는 C# 프로그래밍에 입문하고 싶은 사람들에게 C#의 핵심 개념과 기능, 구체적인 실무 방향을 알려주는 것이다. 더 쉽고 효율적인 C# 입문을 위해 마이크로소프트 MVP이자 C#을 25년 이상 사용하고 강의해 온 저자가 핵심 개념과 기능을 엄선했으며, 최적의 학습 순서가 무엇일지를 치열하게 고민했다.
1부에서는 C# 프로그래밍을 학습하기 전 기본 개념과 개발 환경 설정을 다루고, 2부에서는 C#의 기초 문법과 사용법을 학습한다. 3부에서는 개체 지향 프로그래밍 기법과 C# 활용법을 익히며, 4부에서는 모던 C#의 확장 기능을 살펴보고 실무에서 유용한 기능을 소개한다.
C#의 쓰임새와 기초 문법부터 컬렉션, 제네릭, LINQ, 동적 형식, 스레드, 비동기 프로그래밍과 같은 활용과 확장 기능까지, 입문자에게 필요한 모든 것을 다루는 이 책으로 C#의 첫걸음을 떼 보자!', -- content
'5328c305-1670-4317-a46c-56b7b9f0941c.jpg', -- file1
10.0, -- rating
'9791140713806', -- isbn
796 -- totalPageNum
 );
 
 insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'대체불가 AI 노코드 업무 툴 & 게임 개발', -- title
'책바세', -- publisher
now(), -- regDate
'2025-06-25 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000216810761', -- bookSource
'개발에 개(開)자도 몰랐던 내가, 이 책 덕분에 바로 개발자가 되었다!
- 이 책을 펴낸 책바세 출판사 대표의 말 -

이 책의 기획자인 출판사 대표도 처음엔 코딩과 전혀 무관한 사람이었다. 하지만 이 책에서 소개하는 GPT와 클로드, 엑셀 매크로, 파이썬, API까지, 단계별 미션을 따라가며 직접 프로그램을 만들고, 파일을 자동 정리하고, 게임까지 개발하게 되었다.
배우는 내내 흥미로웠고, 재미있었으며, 몇 번의 막힘이 있었지만, 금방 해결하여 어느새 개발자가 되어 있었다.

주요 포인트
코딩? 하나도 몰라도 된니다!
AI가 코드를 대신 써주고, 당신은 지시만 내리면 된다. 질문만 잘 던지면, 챗GPT가 실행 가능한 코드로 바로 응답한다.

실무 자동화도 게임처럼 쉽다!
PDF 자르기, 파일 정리, 성적표 자동 발송, 틱택토·행맨 게임 제작 등, 회사, 학교, 가정에서도 바로 써먹을 수 있는 31가지 실전 미션이 수록 되었다.

개발이 돈이 된다던데? 이제 나도 잘 나가는 개발자!
개발자 시대, AI와 함께라면 당신이 바로 개발자다. 이제 개발자로 돈 되는 일에 도전해 보자.

질문이 막힐 땐, 이 책을 활용하자!
AI에게 정확히 무엇을 시키고, 오류가 나면 어떻게 수정하는지, 비개발자를 위한 AI 질문법과 협업 노하우까지 모두 담았다.

당신이 처음 AI와 개발을 만나게 될 단 한 권의 책이다. 이제 귀차니즘도, 기술 격차도 이제 문제되지 않는다. 이 책 한 권으로, 실전형 노코드 개발자가 될 수 있으니까!', -- content
'e6c7142f-99a3-4580-8a1b-394b0a92bb0a.jpg', -- file1
10.0, -- rating
'9791194000105', -- isbn
226 -- totalPageNum
 );
 
insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'Do it! 점프 투 파이썬', -- title
'이지스퍼블리싱', -- publisher
now(), -- regDate
'2023-06-15 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000202532365', -- bookSource
'프로그래밍 분야 8년 연속 베스트셀러!
《Do it! 점프 투 파이썬》 전면 개정 2판 출시!
중고등학생도, 비전공자도, 직장인도 프로그래밍에 눈뜨게 만든 바로 그 책이 전면 개정 2판으로 새롭게 태어났다! 챗GPT를 시작으로 펼쳐진 생성 AI 시대에 맞춰 설명과 예제를 다듬고, 최신 경향과 심화 내용을 보충했다. 또한 이번 개정 2판도 50만 코딩 유튜버인 조코딩과 협업을 통해 유튜브 동영상을 제공해 파이썬을 더 쉽게 공부할 수 있다.

8년 연속 베스트셀러! 위키독스 누적 방문 300만! 독자의 입에서 입으로 전해진 추천과 수많은 대학 및 학원의 교재 채택을 통해 검증은 이미 끝났다. 코딩을 처음 배우는 중고등학생부터 코딩 소양을 기르려는 비전공자, 자기계발에 진심인 직장인까지! 이 책과 함께 파이썬 프로그래밍의 세계로 점프해 보자!', -- content
'accb1812-c44b-4469-b19e-1ba558e29e4d.jpg', -- file1
9.8, -- rating
'9791163034735', -- isbn
432 -- totalPageNum
 );

insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'Do it! LLM을 활용한 AI 에이전트 개발 입문', -- title
'이지스퍼블리싱', -- publisher
now(), -- regDate
'2025-05-09 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000216406434', -- bookSource
'GPT API를 활용한 업무 자동화부터
랭체인과 랭그래프를 활용한 멀티에이전트 개발까지!
한 권으로 끝내는 AI 에이전트 개발 입문!
AI가 모두의 일상을 바꾸고 있는 지금, AI 기술을 제대로 이해하고 활용하는 방법을 소개하는 책이 출간되었습니다. 이 책은 AI 기술의 핵심인 LLM의 개념부터 시작해 LLM을 활용해 AI 에이전트를 개발하는 방법을 소개합니다. GPT API를 활용해 맞춤형 업무 자동화 프로그램을 만들고 랭체인과 랭그래프를 활용해 에이전트들이 협업하는 멀티에이전트 시스템까지 구현합니다.
또한 LLM의 한계와 이를 해결하는 전략은 물론, 보안과 비용 걱정 없이 로컬에서 언어 모델과 임베딩 모델을 사용하는 방법까지 폭넓게 다룹니다. 이 책과 함께라면 이전에는 상상할 수 없었던 생산적이고 창의적인 AI 에이전트를 직접 만들어 낼 수 있습니다.', -- content
'19a80b5b-f6ad-45f6-ab34-45a641551721.jpg', -- file1
9.9, -- rating
'9791163037057', -- isbn
504 -- totalPageNum
 );

insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'주니어 백엔드 개발자가 반드시 알아야 할 실무 지식', -- title
'한빛미디어', -- publisher
now(), -- regDate
'2025-04-28 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000216376461', -- bookSource
'실무에서 자주 겪는 다양한 문제를 효과적으로 해결하는 법
서비스 환경에서는 커넥션을 닫지 않아 서버가 멈추고 외부 API의 지연이 전체 장애로 번지며 사소한 설정 실수가 사용자 전체에 영향을 주는 일이 실제로 발생한다. 이 책은 주니어 백엔드 개발자가 실제 현장에서 자주 마주치는 문제들을 스스로 이해하고 해결할 수 있도록 돕는 실무 밀착 가이드다. 겉보기엔 잘 돌아가는 서비스라도 규모가 커지고 사용자가 늘어나면 언제든 위기 상황에 직면할 수 있다. 이 책은 성능 저하, DB 연결 오류, 비동기 연동 문제, 동시성 제어, 인프라 운영, 보안 취약점 등 서비스 운영 과정에서 겪게 되는 핵심 이슈를 살펴보면서 왜 이런 문제가 발생하는지, 어떻게 대응해야 하는지를 체계적으로 알려준다. 이 책으로 실무에서의 혼란과 시행착오를 줄이고 서비스 운영 과정에서 발생할 여러 문제를 예방하거나 해결하는 역량을 키울 수 있을 것이다.', -- content
'da4f2843-dd94-43c4-87e1-e6d5d36759ca.jpg', -- file1
10.0, -- rating
'9791169213745', -- isbn
356 -- totalPageNum
 );

insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'코딩 자율학습 나도코딩의 파이썬 입문', -- title
'길벗', -- publisher
now(), -- regDate
'2023-02-20 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000200929052', -- bookSource
'30만 명의 구독자와 2만 6천 명의 수강생이 증명한 최고의 파이썬 강의
나도코딩의 프로그래밍 학습 노하우를 배우자!
유튜브와 인프런 최고의 인기 강의를 한 권에 담았습니다. 일상 속 재미있는 예제로 파이썬 기본 개념을 배우고 1분 퀴즈, 실습 문제, 셀프체크로 이어지는 단계별 학습으로 파이썬을 완공할 수 있습니다. 이제 코딩은 선택이 아닌 필수! 코딩은 전공자만 배울 수 있다는 생각으로 지레 포기하지 마세요. 파이썬은 초보자가 가장 쉽게 배울 수 있는 프로그래밍 언어입니다. 관심만 있다면 누구나 코딩을 배울 수 있습니다. 나도코딩이 쉽고 재미있게 알려드립니다. 코딩을 처음 배우는 사람도 단계적 용어 설명과 친절한 지시선으로 막힘없이 따라 할 수 있습니다. 기본 설명 외에 팁, 노트 등을 적재적소에 배치해 혼자 공부할 때 생길 수 있는 의문점을 쉽게 해결할 수 있게 도와줍니다. 이제 〈코딩 자율학습 나도코딩의 파이썬 입문〉으로 완벽한 코딩 자율학습을 경험해 보세요.', -- content
'1836effb-7a5e-4bab-82d5-2d0d9ca3667d.jpg', -- file1
10.0, -- rating
'9791140703302', -- isbn
436 -- totalPageNum
 );

insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'10분 만에 따라 하는 Claude MCP 업무 자동화 혁신 가이드', -- title
'리코멘드', -- publisher
now(), -- regDate
'2025-07-10 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000216922517', -- bookSource
'★ ChatGPT를 쓰다가 답답했다면, AI한테 물어보기만 했다면, 이제 직접 일하는 AI를 만나 보세요.
★ 클로드로 만드는 나만의 AI 비서, Claude MCP
지금까지 AI를 ‘대답봇’으로만 사용했다면 당신은 곧 남들보다 한참 뒤쳐질지도 모른다. 이제는 AI를 내 스타일에 맞게 진화시켜 업무를 비롯한 각종 일을 함께 수행하는 ‘동료’로 키울 수 있는 시대다. 이 책은 이러한 변화로 이끈 핵심 기술인 MCP(Model Context Protocol)를 기반으로 기본 파일 생성 및 관리, 데이터 처리와 수집, 엑셀 파일 분석, 공공기관에서 주로 쓰는 한글 파일 분석 및 노션 활용까지 실무에서 바로 활용할 수 있는 핵심 기술만을 담아 업무 자동화를 빠르게 구축하는 방법을 알려준다.
Claude의 MCP를 활용해 AI가 어떻게 인간의 명령을 이해하고 API나 파일, 캘린더, 이메일 , 폴더 등을 직접 제어하게 되는지를 단계별로 설명한다. 개발 지식이 없거나 코딩을 몰라도 누구나 쉽게 따라할 수 있도록 실습 예제가 구성되어 있다. 이 책을 통해 AI를 단순한 도구가 아니라 나만의 진정한 업무 파트너로 만드는 경험을 꼭 해보길 바란다.', -- content
'4d7b529f-6ca1-4073-aa66-a252e65c7143.jpg', -- file1
0, -- rating
'9791194084198', -- isbn
240 -- totalPageNum
 );

insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'혼자 공부하는 파이썬', -- title
'한빛미디어', -- publisher
now(), -- regDate
'2022-06-01 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000061352349', -- bookSource
'혼자 해도 충분하다! 1:1 과외하듯 배우는 파이썬 프로그래밍 자습서
『혼자 공부하는 파이썬』이 더욱 흥미있고 알찬 내용으로 개정되었습니다. 프로그래밍이 정말 처음인 입문자도 따라갈 수 있는 친절한 설명과 단계별 학습은 그대로! 혼자 공부하더라도 체계적으로 계획을 세워 학습할 수 있도록 혼공 계획표를 새롭게 추가했습니다. 또한 입문자가 자주 물어보는 질문과 오류 해결 방법을 적재적소에 배치하여 예상치 못한 문제에 부딪혀도 좌절하지 않고 끝까지 완독할 수 있도록 도와줍니다. 단순한 문법 암기와 코딩 따라하기에 지쳤다면, 새로운 혼공파와 함께 ‘누적 예제’와 ‘도전 문제’로 프로그래밍의 신세계를 경험해 보세요! 배운 내용을 씹고 뜯고 맛보고 즐기다 보면 응용력은 물론 알고리즘 사고력까지 키워 코딩 실력이 쑥쑥 성장할 것입니다.

이 책은 독학으로 파이썬을 배우는 입문자가 꼭 필요한 내용을 제대로 학습할 수 있도록 구성했습니다. 뭘 모르는지조차 모르는 입문자의 막연한 마음에 십분 공감하여 과외 선생님이 알려주듯 친절하게, 핵심적인 내용만 콕콕 집어줍니다. 책의 첫 페이지를 펼쳐서 마지막 페이지를 덮을 때까지, 혼자서도 충분히 파이썬을 배울 수 있다는 자신감과 확신이 계속될 것입니다!

베타리더와 함께 입문자에게 맞는 난이도, 분량, 학습 요소 등을 적극 반영했습니다. 어려운 용어와 개념은 한 번 더 풀어쓰고, 복잡한 설명은 눈에 잘 들어오는 그림으로 풀어냈습니다. ‘혼자 공부해 본’ 여러 입문자의 초심과 눈높이가 책 곳곳에 반영된 것이 이 책의 가장 큰 장점입니다.', -- content
'befe9b44-d47c-48bf-8896-ed6fd4cce9d7.jpg', -- file1
9.8, -- rating
'9791162245651', -- isbn
 552 -- totalPageNum
 );

insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'50개의 프로젝트로 완성하는 파이썬 업무 자동화', -- title
'위즈앤북', -- publisher
now(), -- regDate
'2025-03-17 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000216123786', -- bookSource
'ㆍ퀴즈와 QR 코드 생성부터 나만의 음성 비서 제작까지 다양한 프로젝트 대공개!
ㆍ문서 번역, 파일 분류, 데이터 분석, 엑셀 문서, 영상 다운로드 등의 업무 자동화!
ㆍ뉴스, 날씨, 주식, 핫딜, 환율, 이커머스, 이미지 크롤링 등 데이터 자동 수집 및 관리!
ㆍChatGPT, Copilot, Gemini를 활용한 AI 스마트 자동화 노하우!

이 책은 실무에서 바로 사용할 수 있는 50개의 프로젝트를 이용하여 업무 시스템 자동화, 애플리케이션 자동화, 인터넷 자동화 모델을 효율적으로 구현해 보고 완성할 수 있습니다. 특히, 인공지능과 데이터 분석 등에서 가장 많이 사용하는 파이썬 프로그래밍을 실제 다양한 업무 현장에서 활용하고자 하는 모든 분들을 위해 야심차게 준비했습니다.', -- content
'ef417ee7-b703-4154-bfa2-53fdb1989f70.jpg', -- file1
9.4, -- rating
'9791198685353', -- isbn
 320 -- totalPageNum
 );

insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'네이처 오브 코드: 자바스크립트판', -- title
'제이펍', -- publisher
now(), -- regDate
'2025-07-22 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000216978147', -- bookSource
'세계에서 가장 웃긴 코딩 유튜버 대니얼 시프먼과 함께하는 특별한 모험경외심을 불러일으키는 새들의 군무 패턴이나 반딧불이의 최면 같은 춤을 코딩으로 재현할 수 있다면 어떨까요? 인기 유튜브 채널 ‘코딩 트레인’의 대니얼 시프먼이 쓴 《The Nature of Code》는 수많은 독자를 크리에이터로 변화시켜 과학, 예술, 기술 간의 장벽을 허물고 코드를 단순한 작업 도구가 아닌 무한한 창의성을 위한 캔버스로 인식하도록 유도했습니다. 이번에 출간된 《네이처 오브 코드(자바스크립트판)》은 예제 코드 언어를 프로세싱에서 자바스크립트(p5.js)로 바꾸고 내용을 추가한 최신 개정판입니다.

벡터의 개념으로 시작해 뉴턴의 운동 법칙, 진동, 삼각법을 통해 자바스크립트로 간단한 물리 엔진을 직접 만들어봅니다. 이를 토대로 복잡계, 조향력, 무리 지어 다니기를 시뮬레이션하고, 나아가 진화와 유전 알고리즘에서 신경망, 머신러닝, 신경진화 시스템 등 복잡한 주제까지 이해하기 쉽게 설명합니다. 주위에서 흔히 볼 수 있는 자연계의 현상을 객체지향 프로그래밍을 활용해 시각적으로 멋진 하나의 작품으로 완성해내는 과정은 정말 짜릿합니다. 책의 예제 코드는 모두 책의 공식 웹사이트에서 바로 돌려볼 수 있습니다.

모두 ‘코딩 트레인’에 탑승해서 창의적 코딩의 특별한 모험을 시작하세요. 코딩의 기본을 익히는 동시에 코드를 예술로 바꾸는 즐거움을 발견할 수 있습니다. 자연을 새로운 방식으로 바라보고 그 경이로움에서 창작물에 영감을 얻게 될 것입니다. 초보자이든 숙련된 프로그래머이든, 이 책이 여러분을 코드와 창의성이 만나는 놀라운 세계로 안내할 것입니다.

주요 내용물리 엔진: 중력의 밀고 당김을 시뮬레이션합니다.무리 지어 다니기: 새 떼의 매혹적인 군무를 연출해보세요.가지를 치는 나무: 생생하고 유기적인 나뭇가지 구조를 만들어봅니다.신경망: 학습하고 적응하는 지능형 시스템을 제작하세요.셀룰러 오토마타: 자기 조직화 패턴의 마법을 발견하세요.진화 알고리즘: 코드에서 자연선택을 직접 체험해보세요.', -- content
'64f2eba3-7903-4a58-9ddb-ce49cec88c39.jpg', -- file1
10.0, -- rating
'9791194587316', -- isbn
640 -- totalPageNum
 );

insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'혼자 공부하는 C 언어', -- title
'한빛미디어', -- publisher
now(), -- regDate
'2023-05-18 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000202252289', -- bookSource
'혼자 해도 충분하다! 1:1 과외하듯 배우는 C 프로그래밍 자습서
『혼자 공부하는 C 언어』가 더욱 흥미 있고 알찬 내용으로 돌아왔습니다. 혼자 공부하더라도 막히는 부분이 없도록 부가 설명용 동영상 QR 코드를 추가했습니다. 또한 최신 프로그램에서도 무리 없이 실습을 진행할 수 있도록 비주얼 스튜디오 2022 버전을 반영했습니다.

물론, 프로그래밍이 정말 처음인 사람에게 꼭 필요한 내용과 뭘 모르는지조차 모르는 마음에 십분 공감해 과외 선생님이 알려주듯 핵심 내용만 콕콕 짚어 주는 친절한 설명, 누구나 쉽게 따라 할 수 있도록 구성된 단계별 학습 그리고 입문자에게 맞는 난이도, 분량, 학습 요소 등을 베타리더의 의견에 따라 적극 반영한 건 여전합니다!

단순한 문법 암기와 코딩 따라하기에 지쳤다면 새롭게 돌아온 친절한 ‘혼공 씨’와 함께 C 언어라는, 프로그래밍 언어의 근본을 경험해 보세요. 책의 첫 페이지를 펼치고 마지막 페이지를 덮을 때까지, 혼자서도 충분히 C 언어를 배울 수 있다는 자신감과 확신이 계속 들 것입니다!', -- content
'e113512b-391e-4540-bb1e-82e5fa6c3c32.jpg', -- file1
10.0, -- rating
'9791169210911', -- isbn
664 -- totalPageNum
 );

insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'실용주의 프로그래머(20주년 기념판)', -- title
'인사이트', -- publisher
now(), -- regDate
'2022-02-24 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000001033128', -- bookSource
'실용주의 프로그래머 20주년 기념판
전문가를 향한 여정
《실용주의 프로그래머》는 당신이 읽고, 또 읽고, 수년간 또다시 읽게 될 몇 안 되는 기술 서적이다. 당신이 이 분야에 처음 발을 디딘 사람이건, 경험 많은 전문가이건 매번 새로운 통찰을 얻게 될 것이다.

데이비드 토머스와 앤드류 헌트는 소프트웨어 산업에 큰 영향을 미친 이 책의 1판을 1999년에 썼다. 고객들이 더 나은 소프트웨어를 만들고 코딩의 기쁨을 재발견하도록 돕기 위해서였다. 이 책의 가르침 덕분에 한 세대에 걸친 프로그래머들이 어떤 언어나 프레임워크, 방법론을 사용하든 상관없이 소프트웨어 개발의 본질을 돌아볼 수 있었다. 그리고 실용주의 철학은 수백 권의 책, 스크린캐스트, 오디오북으로 그리고 무수한 사람들의 경력과 성공 스토리로 퍼져 나갔다.', -- content
'8f09506e-867b-4919-bad3-a910b6953b03.jpg', -- file1
10, -- rating
'9788966263363', -- isbn
 476 -- totalPageNum
 );

insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'파이썬 머신러닝 완벽 가이드', -- title
'위키북스', -- publisher
now(), -- regDate
'2022-04-21 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000001766511', -- bookSource
'자세한 이론 설명과 파이썬 실습을 통해 머신러닝을 완벽하게 배울 수 있습니다!
《파이썬 머신러닝 완벽 가이드》는 이론 위주의 머신러닝 책에서 탈피해, 다양한 실전 예제를 직접 구현해 보면서 머신러닝을 체득할 수 있도록 만들었습니다. 캐글과 UCI 머신러닝 리포지토리에서 난이도가 있는 실습 데이터를 기반으로 실전 예제를 구성했고, XGBoost, LightGBM, 스태킹 기법 등 캐글의 많은 데이터 사이언스에서 애용하는 최신 알고리즘과 기법을 상세하게 설명했습니다.

이번 개정2판에서는 최신 사이킷런 버전(1.0.2)을 포함해 책에서 사용되는 모든 라이브러리를 최신 버전으로 업그레이드한 실습 코드를 구현하고, 다양한 유형의 하이퍼파라미터를 가지는 XGBoost나 LightGBM 모델의 최적 하이퍼파라미터 튜닝을 위한 베이지안 최적화 기법 적용 실습을 제공합니다. 또한 머신러닝 관련 데이터 분석에 널리 쓰이는 시각화 라이브러리인 matplotlib과 seaborn의 활용법을 다룬 장을 새롭게 추가했습니다.', -- content
'0d55a4a0-005a-4c70-af07-182b71019da5.jpg', -- file1
9.8, -- rating
'9791158393229', -- isbn
 724 -- totalPageNum
 );

insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'밑바닥부터 시작하는 딥러닝 3', -- title
'한빛미디어', -- publisher
now(), -- regDate
'2020-11-10 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000001810323', -- bookSource
'코드 3줄이 딥러닝 프레임워크가 되는 마법
이 책은 밑바닥부터 직접 만들어보며 즐겁게 딥러닝을 익히는 시리즈의 장점을 그대로 따랐습니다. 코드 3줄로 시작해 60단계까지 차근차근 구현해보세요. 어느새 파이토치, 텐서플로와 같은 현대적이지만 미니멀한 딥러닝 프레임워크가 완성돼 있을 것입니다. 딥러닝과 파이썬 지식을 어느 정도 갖췄다면 전편을 읽지 않고도 충분히 따라 할 수 있습니다. 동적 계산 그래프(Define-by-Run) 구조와 딥러닝 프레임워크 기본 설계, 두 마리 토끼를 잡아보세요!
☞ 선정 및 수상내역
2021 대한민국학술원 우수학술도서 선정도서', -- content
'03e06614-1474-4b82-807d-fc2b8dd91854.jpg', -- file1
9.7, -- rating
'9791162243596', -- isbn
552 -- totalPageNum
 );

insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'RAG 시스템 구축을 위한 랭체인 실전 가이드', -- title
'루비페이퍼', -- publisher
now(), -- regDate
'2024-10-30 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000214659381', -- bookSource
'LLM의 기본 개념부터 AI 서비스 구축, 배포까지
이 책은 랭체인 프레임워크를 기반으로 한 RAG 시스템의 개념과 원리에 대해 입문자도 이해할 수 있을 만큼 쉽게 설명합니다. 또 시스템의 각 구성 요소가 어떤 역할을 하는지, 어떻게 더 잘 활용할 수 있는지를 자세히 다룹니다. 특히 마지막 장에서는 지금까지 배운 이론과 실습을 토대로 구성한 ‘RAG 시스템 구축 실전 프로젝트’를 완성하며, 전반적인 RAG의 이해도를 높이고 실무에서도 직접 활용해볼 수 있도록 구성했습니다.', -- content
'95b72e9f-3978-43f5-966b-f07fa5f41f74.jpg', -- file1
9.7, -- rating
'9791193083239', -- isbn
278 -- totalPageNum
 );

insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'면접을 위한 CS 전공지식 노트', -- title
'길벗', -- publisher
now(), -- regDate
'2022-04-28 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000001834833', -- bookSource
'디자인 패턴, 네트워크, 운영체제, 데이터베이스, 자료 구조, 개발자 면접과 포트폴리오까지!
CS 전공지식 습득과 면접 대비, 이 책 한 권이면 충분하다!

개발자 면접에서 큰 비중을 차지하는 CS(Computer Science) 전공지식! 디자인 패턴부터 자료 구조까지 알아야 할 게 너무 많은데, 어떻게 준비해야 할까? 이 책은 디자인 패턴, 네트워크, 운영체제, 데이터베이스, 자료 구조 등 면접에 필요한 CS 전공지식을 모두 담고 있다. 200여 개의 그림과 코드로 이론을 자세히 설명하고, 실제 라이브러리에서 사용된 디자인 패턴 등으로 실무 활용법을 함께 다뤄 이론과 실무를 놓치지 않고 학습할 수 있도록 구성했다. 또한, 중요한 내용은 깊게, 덜 중요한 내용은 핵심만 설명하며, 책 곳곳에 70여 개의 용어 풀이도 담고 있다. 마지막으로 구글, 네이버, 카카오 등 탑티어급의 회사에 합격한 저자의 경험을 기반으로 한 포트폴리오 작성법과 챕터별 예상 질문, 면접 준비 노하우도 알려준다. 개발자 면접을 준비하거나 더 나은 개발자가 되기 위해 CS 전공지식을 배우고 싶다면 이 책으로 시작하자.', -- content
'e9f13bea-2dab-42f0-9ede-8897e6fe49a9.jpg', -- file1
9.3, -- rating
'9791165219529', -- isbn
 292 -- totalPageNum
 );

insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'자바 ORM 표준 JPA 프로그래밍', -- title
'에이콘출판', -- publisher
now(), -- regDate
'2015-07-28 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000000935744', -- bookSource
'자바 ORM 표준 JPA는 SQL 작성 없이 객체를 데이터베이스에 직접 저장할 수 있게 도와주고, 객체와 관계형 데이터베이스의 차이도 중간에서 해결해준다. 이 책은 JPA 기초 이론과 핵심 원리, 그리고 실무에 필요한 성능 최적화 방법까지 JPA에 대한 모든 것을 다룬다. 또한, 스프링 프레임워크와 JPA를 함께 사용하는 방법을 설명하고, 스프링 데이터 JPA, QueryDSL 같은 혁신적인 오픈 소스를 활용해서 자바 웹 애플리케이션을 효과적으로 개발하는 방법을 다룬다.

다음 링크에서 온라인 강의를 수강할 수 있다.

■ 강의 링크: https://www.inflearn.com/roadmaps/149
■ 온라인 강의 목록
-자바 ORM 표준 JPA 프로그래밍 - 기본편: https://www.inflearn.com/course/ORM-JPA-Basic
-실전! 스프링 부트와 JPA 활용1 - 웹 애플리케이션 개발: https://www.inflearn.com/course/스프링부트-JPA-활용-1
-실전! 스프링 부트와 JPA 활용2 - API 개발과 성능 최적화: https://www.inflearn.com/course/스프링부트-JPA-API개발-성능최적화#
-실전! 스프링 데이터 JPA: https://www.inflearn.com/course/스프링-데이터-JPA-실전
-실전! Querydsl: https://www.inflearn.com/course/Querydsl-실전', -- content
'83b280c7-a6cb-4bb9-990d-feec9ec832a0.jpg', -- file1
9.6, -- rating
'9788960777330', -- isbn
734 -- totalPageNum
 );
 
insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'하루 5분 UX', -- title
'유엑스리뷰', -- publisher
now(), -- regDate
'2022-08-05 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000061532882', -- bookSource
'UX 기획과 디자인 실무에 꼭 필요한 지식만 압축!
매일 조금씩 읽다 보면 UX 지식의 대부분을 이해하게 된다!
UX 입문자부터 실무 경험자까지 모두에게 필요한 인사이트와 지식을 오랜 경험을 갖춘 UX 디자인 관리자가 간결하고 유머러스한 설명으로 풀어낸다. UX의 본질적 개념은 물론 리서치, 콘텐츠, 프로토타입, 사용자 심리 등 거의 모든 영역을 100개의 레슨으로 세밀하게 나누어 아주 간단명료하게 강의한다. 이론을 위한 군더더기는 쏙 뺐다. 저자는 각 레슨을 짧은 템포로 전개하면서 핵심적인 내용만 콕 집어주고, 재미난 일러스트를 더하여 UX를 잘 모르는 독자들도 어려움 없이 이해하도록 진입장벽을 낮추었다. 스타트업, 유명 글로벌 브랜드, 기업의 인하우스 등 현장에서 오랜 세월 일해 온 저자는 그 모든 곳에서 UX와 관련된 기초적인 질문들을 반복해서 받았고, 그들에게 도움을 주고자 UX를 위한 핵심 팁을 온라인에서 공개했던 적이 있고, 그 속성 강의는 폭발적인 인기를 끌었다. 그것이 바로 이 책의 발단이 됐다. 저자는 가능한 많은 사람이 이 책을 재미있게 읽고 유용하게 이용하길 바라는 마음으로 끊임없이 피드백을 모았고, 업계 선두에 있는 UXer들에게 내용을 검수받았다. 《하루 5분 UX》는 분명 지금까지 출간된 UX 관련 책 중에서 가장 실용적이면서도 가성비 높은 책이 될 것이다.', -- content
'7adb43d2-d3f2-4fd9-b62c-2736d93d79b3.jpg', -- file1
9.9, -- rating
'9791192143378', -- isbn
388 -- totalPageNum
 );
 
insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'Do it! 플러터 앱 개발&출시하기', -- title
'이지스퍼블리싱', -- publisher
now(), -- regDate
'2025-07-08 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000216868949', -- bookSource
'플러터 분야 1위!
AI 활용법과 최신 기술을 담아 더 강력하게 돌아왔다!
수익 구조를 포함한 앱 기획부터 디자인, 개발, 출시, AI 활용까지 한 권으로 배우자
수익화 앱을 만들어 보는 〈Do it! 플러터 앱 개발 & 출시하기〉가 최신 기술과 AI 활용법을 담아 개정되었습니다. 프로그래밍 기본 언어는 공부했지만 앱 개발은 어떻게 시작해야 할지 막막했다면 이 책을 주목하세요! 수익 구조를 고려한 앱 기획부터 디자인, 사용할 수 있는 리소스, 개발, 출시, 제미나이 AI 활용법까지 앱 서비스를 완성하는 전 과정을 배웁니다. 일상에서 쉽게 접할 수 있는 상용화 앱을 서버리스 시스템으로 빠르게 구현하고 구글 파이어베이스의 기능과 제미나이 API를 활용해 강력하게 완성합니다. 애널리틱스 데이터를 활용한 앱 개선 방법과 광고 수익을 위한 애드몹 활용까지 다양한 실전 기능을 활용해 실제 서비스할 수 있는 앱을 만들어 보세요. 이 책을 다 배우고 나면 단순히 따라 하는 것을 넘어 여러분이 상상하던 앱을 직접 만들고 출시할 수 있습니다!', -- content
'85eaf253-51c4-461a-970f-5d3458d93f93.jpg', -- file1
10.0, -- rating
'9791163037309', -- isbn
544 -- totalPageNum
 );
 
insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'코틀린 아카데미: 이펙티브 코틀린', -- title
'인사이트', -- publisher
now(), -- regDate
'2025-06-09 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000216681102', -- bookSource
'코틀린 개발자들의 필독서 《이펙티브 코틀린》의 최신 개정판
모범 사례를 통해 코틀린의 60가지 품질 향상 전략을 알려 준다!
코틀린의 장점을 제대로 활용하려면 기능과 표준 라이브러리를 아는 것만으로는 충분하지 않습니다. 코틀린을 올바르게 사용하는 방법을 알아야 합니다. 《코틀린 아카데미: 이펙티브 코틀린》은 코틀린의 60가지 효과적인 활용법을 알려 주는 실용적인 안내서입니다. 이 책은 단순히 언어의 기능을 아는 것을 넘어, 언제 어떻게 사용해야 하는지에 대한 깊이 있는 통찰을 제공합니다. 다양한 코틀린 기능을 사용하여 안전성, 가독성, 유지보수성, 효율성 면에서 더 나은 코드를 만드는 방법을 제시하고, 인라인 함수와 클래스, 도메인 특화 언어(DSL), 플랫폼 타입과 같은 고급 주제도 다룹니다. 독자들은 이 책을 통해 실제 개발 현장에서 마주칠 수 있는 다양한 문제들에 대한 해결책을 얻을 수 있습니다.', -- content
'fb0de13c-a321-4eed-9029-0cc199409a7d.jpg', -- file1
10, -- rating
'9788966264612', -- isbn
448 -- totalPageNum
 );
  
insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'SwiftUI와 컴바인을 활용한 비동기 프로그래밍', -- title
'에이콘출판', -- publisher
now(), -- regDate
'2025-05-29 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000216564531', -- bookSource
'이 책은 SwiftUI, Combine, 그리고 async/await를 활용해 Apple 플랫폼에서 선언적이고 반응형 UI를 구축하는 방법을 안내하는 실용서다. SwiftUI의 상태 기반 UI 구성, Combine을 통한 비동기 이벤트 처리, 그리고 async/await를 이용한 네트워크 통신을 체계적으로 설명한다. 초급자부터 중급 개발자까지 단계별로 따라할 수 있는 예제와 함께, 실무에 바로 적용 가능한 아키텍처 설계 방법을 제공한다. SwiftUI와 Combine을 처음 접하는 독자에게도 유용한 입문서로 추천할 수 있다.', -- content
'a89dd91a-9d60-4e45-93e7-87967412332a.jpg', -- file1
0, -- rating
'9791161759739', -- isbn
444 -- totalPageNum
 );
 
 insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'소프트웨어 엔지니어 가이드북', -- title
'한빛미디어', -- publisher
now(), -- regDate
'2024-10-30 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000214576874', -- bookSource
'그날 나는 결심했다.
내가 매니저가 된다면 팀원들에게 성장에 필요한 조언을 주리라.
현대 IT 산업에서 소프트웨어 엔지니어로 성공적인 커리어를 쌓으려면, 뛰어난 코딩 실력만으로는 부족합니다. 빠르게 변화하는 기술 환경 속에서 직무를 효율적으로 수행하고, 장기적인 커리어 발전을 이루기 위해서는 더 많은 준비가 필요합니다. 이 책은 많은 기업에서 엔지니어링 매니저로 재직한 저자가 현업에서 팀원들에게 조언을 주는 과정에서 깨달은 경력 관리의 비법을 담고 있습니다.
소프트웨어 엔지니어가 실제 직장에서 겪을 다양한 상황에 대한 해결책을 소개하는 가이드북입니다. 단순한 이론을 넘어 실제 현장에서 바로 적용할 수 있는 유용한 정보까지 담았습니다. 주니어 엔지니어부터 시니어 엔지니어, 스태프 엔지니어에 이르기까지 경력 단계에 따라 다음 단계로 나아가는 데 필요한 정보와 커리어 발전을 위한 구체적인 로드맵, 장기적인 커리어 성공을 위한 청사진을 만나보세요.', -- content
'50a57970-edf7-4792-97a6-13b433fa35bf.jpg', -- file1
10, -- rating
'9791169213073', -- isbn
 568 -- totalPageNum
 );
 
 insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'Do it! JSCODE의 AWS 입문', -- title
'이지스퍼블리싱', -- publisher
now(), -- regDate
'2025-05-15 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000216485464', -- bookSource
'누구나 이해할 수 있는 개념 설명과 친절한 단계별 실습으로
입문자도 자신 있게 시작하는 AWS!
이 책은 클라우드, AWS, EC2 같은 단어만 들어도 막막했던 분들을 위한 클라우드 입문서입니다. 비전공자 출신 개발자인 저자의 경험을 바탕으로 AWS를 공부할 때 마주하는 낯선 용어와 복잡한 개념을 최대한 쉽게 풀어 설명했습니다. 실무에서 자주 사용하는 주요 서비스 6가지를 단계별 실습으로 배우며, AWS 환경에 웹 서비스를 배포하는 경험을 할 수 있습니다. 또한 최종 프로젝트에서는 프런트엔드와 백엔드를 직접 배포해 실무와 유사한 클라우드 서비스 경험도 쌓을 수 있습니다. 모든 실습은 친절한 설명과 실제 콘솔 화면 이미지를 함께 제공해 초보자도 자연스럽게 따라 할 수 있습니다. 이 책과 함께라면 클라우드 기초를 탄탄히 다지고, 클라우드 서비스를 다룰 수 있는 자신감을 얻을 수 있습니다.', -- content
'70657b0c-f3e1-4eee-9e15-dd1074f857f1.jpg', -- file1
10, -- rating
'9791163037118', -- isbn
240 -- totalPageNum
 );
 
 insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'선형대수와 통계학으로 배우는 머신러닝 with 파이썬', -- title
'비제이퍼블릭', -- publisher
now(), -- regDate
'2021-01-26 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000001842169', -- bookSource
'머신러닝에 필요한 선형대수, 통계학, 최적화 이론부터
파이썬, 사이킷런, 텐서플로를 활용한 실습까지
『선형대수와 통계학으로 배우는 머신러닝 with 파이썬』은 머신러닝의 기본적인 사용 방법뿐만 아니라 통계학, 선형대수, 최적화 이론 등 머신러닝에 필요한 배경 이론까지 다룬다. 머신러닝 알고리즘을 소개하는 것에 그치지 않고 이론적으로 이해가 필요한 부분은 수학 수식을 통해 자세히 설명함으로써, 해당 머신러닝 알고리즘의 작동 방식을 파악할 수 있다.

프로그래밍 실습은 머신러닝 파트에서는 사이킷런 라이브러리를, 딥러닝 파트에서는 텐서플로 라이브러리를 사용한다. 각 코드의 라인별 부가 설명을 통해 해당 코드의 역할을 이해할 수 있으며, 각 장 마지막의 전체 코드로 전체 흐름 또한 파악할 수 있다.
머신러닝의 배경 이론 이해를 바탕으로 실습하는 이 책을 통해, 머신러닝 기본기를 다지는 것을 넘어 자신의 분야에 응용할 수 있을 것이다.

이 책의 특징
- 머신러닝 수학 수식 전개 과정을 상세히 표현한다.
- 머신러닝 알고리즘 개념을 쉬운 그림으로 알기 쉽게 설명한다.
- 복잡한 수학 수식과 프로그래밍 코드를 자세하게 설명한다.

이 책이 필요한 독자
- 머신러닝 분야에 관심이 있고 머신러닝을 배우고 싶은 분
- 머신러닝을 공부한 경험이 있지만 실제 사용에 어려움을 느끼는 분
- 머신러닝 알고리즘의 원리를 이해하고 싶은 분', -- content
'177ee1bf-c81b-48e4-947d-dd42d18d9207.jpg', -- file1
9.7, -- rating
'9791165920395', -- isbn
624 -- totalPageNum
 );
 
insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'리팩터링', -- title
'한빛미디어', -- publisher
now(), -- regDate
'2020-04-01 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000001810241', -- bookSource
'개발자가 선택한 프로그램 가치를 높이는 최고의 코드 관리 기술
마틴 파울러의 『리팩터링』이 새롭게 돌아왔다.
지난 20년간 전 세계 프로그래머에게 리팩터링의 교본이었던 이 책의 1판은, 기존 코드의 디자인을 개선하고 소프트웨어 유지 관리 능력을 향상시켰으며 기존 코드를 이해하기 쉽게 만드는 데 도움을 주었습니다. 간절히 기다려온 이번 개정판에는 프로그래밍 환경의 중요한 변화가 대거 반영되었습니다.

새로운 리팩터링 카탈로그를 자바스크립트 코드로 제시합니다. 리팩터링 원칙부터 클래스 없이 리팩터링하는 방법과 데이터 조직화, 조건부 로직 간소화 방법을 다룹니다. 또한 언어에 상관없이 리팩터링을 성공적으로 수행하는 실질적인 방법을 알려줍니다.', -- content
'e66815fc-67ae-411b-963a-b760ea067c8d.jpg', -- file1
9.9, -- rating
'9791162242742', -- isbn
550 -- totalPageNum
 );
 
insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'이것이 자료구조+알고리즘이다 with C 언어', -- title
'한빛미디어', -- publisher
now(), -- regDate
'2022-08-03 00:00:00', -- pubDate
'https://product.kyobobook.co.kr/detail/S000061585515', -- bookSource
'자료구조+알고리즘의 개념부터 실습까지
포기 없이 즐겁게 배우자!
자료구조와 알고리즘은 IT 기업의 면접과 코딩 테스트 통과를 위한 필수 역량입니다. 알고리즘을 배워두면 단순히 취업뿐 아니라 더 좋은 개발자가 되는 데 큰 도움이 됩니다. 하지만 자료구조와 알고리즘은 배우기 어려우며 심지어 재미도 없다 보니 많은 개발자가 중도에 학습을 포기합니다. 『이것이 자료구조+알고리즘이다』는 독자가 마지막 페이지까지 읽도록 하는 것에 목표를 두었습니다.
처음 배우는 사람의 눈높이에 맞춰 리스트부터 백트래킹까지 자주 사용되는 자료구조와 알고리즘 개념을 위트 넘치는 이야기로 쉽게 설명합니다. 보기만 해도 헉 소리가 나는 복잡한 수식은 최소화하고 이해에 꼭 필요한 수식만 담았습니다. 또한 작동 원리를 단번에 이해할 수 있게 도와주는 다양한 그림과 바로 실행하고 확인할 수 있는 108개 소스 코드를 예제로 제공해 알고리즘의 얼개를 완벽히 이해할 수 있도록 구성했습니다.
『이것이 자료구조+알고리즘이다』와 함께 자료구조와 알고리즘의 주요 개념을 포기 없이 끝까지 배워봅시다!', -- content
'f543a8b6-77cd-44c9-8837-cf106e46633e.jpg', -- file1
9.2, -- rating
'9791169210034', -- isbn
 664 -- totalPageNum
 );


INSERT INTO problems (user_id, title, content, answer, hint, difficulty, category)
VALUES
('admin', '배열의 합 구하기', '정수 배열이 주어졌을 때, 배열의 모든 원소의 합을 구하는 함수를 작성하시오.', 'public int sumArray(int[] numbers) {
    int sum = 0;
    for (int num : numbers) {
        sum += num;
    }
    return sum;
}', '향상된 for문을 사용해보세요.', '하', 'JAVA'),
('admin', '문자열 뒤집기', '문자열을 거꾸로 반환하는 함수를 작성하시오.', 'public String reverseString(String input) {
    StringBuilder sb = new StringBuilder(input);
    return sb.reverse().toString();
}', 'StringBuilder 관련 메소드를 사용해보세요.', '하', 'JAVA'),
('admin', '최대 공약수 구하기', '두 정수를 입력받아 최대 공약수를 반환하는 함수를 작성하시오', 'public int gcd(int a, int b) {
    while (b != 0) {
        int temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}', '유클리드 호제법을 사용해보세요.', '중', 'JAVA'),
('admin', '회문 판별하기', '문자열이 앞으로 읽어도, 뒤로 읽어도 같은지 판별하는 함수를 작성하시오.','public boolean isPalindrome(String s) {
    int left = 0, right = s.length() - 1;
    while (left < right) {
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        left++;
        right--;
    }
    return true;
}','앞과 뒤에서 동시에 이동하면서 비교해보세요.', '중', 'JAVA'),
('admin', '괄호 검사하기', '괄호로만 이루어진 문자열이 주어질 때, 모든 괄호가 올바르게 짝을 이루었는지 검사하는 함수를 작성하시오.', "public boolean isValidParentheses(String s) {
    java.util.Stack<Character> stack = new java.util.Stack<>();
    for (char ch : s.toCharArray()) {
        if (ch == '(') {
            stack.push(ch);
        } else if (ch == ')') {
            if (stack.isEmpty()) {
                return false;
            }
            stack.pop();
        }
    }
    return stack.isEmpty();
}", '괄호 하나를 만날 때마다 어딘가에 저장해보세요.', '상', 'JAVA'),
('admin', '이진 트리 탐색하기', '이진 탐색 트리의 루트 노드와 찾으려는 값을 입력받아, 해당 값이 트리에 존재하는지 확인하는 함수를 작성하시오.
TreeNode 클래스는 다음과 같이 정의되어 있다.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}', 'public boolean searchBST(TreeNode root, int val) {
    if (root == null) return false;
    if (root.val == val) return true;
    else if (val < root.val) return searchBST(root.left, val);
    else return searchBST(root.right, val);
}', '재귀 호출을 이용해보세요.', '상', 'JAVA');


INSERT INTO problems (user_id, title, content, answer, hint, difficulty, category)
VALUES
('admin', '배열의 합 구하기', '정수로 이루어진 리스트가 주어졌을 때, 리스트에 있는 모든 숫자의 합을 반환하는 함수를 작성하시오.', 'def sum_list(numbers):
    total = 0
    for num in numbers:
        total += num
    return total
', 'for문을 이용해보세요.', '하', 'Python'),
('admin', '문자열 뒤집기', '문자열을 입력받아, 뒤집은 문자열을 반환하는 함수를 작성하시오.', 'def reverse_string(s):
    return s[::-1]
', '파이썬 배열 기능을 이용해보세요.', '하', 'Python'),
('admin', '최대 공약수 구하기', '두 개의 정수를 입력받아, 그들의 최대공약수를 반환하는 함수를 작성하시오.', 'def gcd(a, b):
    while b != 0:
        a, b = b, a % b
    return a
', '유클리드 호제법을 이용해보세요.', '중', 'Python'),
('admin', '회문 판별하기', ' 문자열이 앞으로 읽어도, 뒤로 읽어도 같은지 판별하는 함수를 작성하시오.','def is_palindrome(s):
    return s == s[::-1]
', '파이썬 배열의 기능 중 역순으로 찾는 기능을 이용해보세요.', '중', 'Python'),
('admin', '괄호 검사하기', '괄호로만 이루어진 문자열이 주어질 때, 모든 괄호가 올바르게 짝을 이루었는지 검사하는 함수를 작성하시오.', "def is_valid_parentheses(s):
    stack = []
    for ch in s:
        if ch == '(':
            stack.append(ch)
        elif ch == ')':
            if not stack:
                return False
            stack.pop()
    return len(stack) == 0
", '괄호를 찾을 때 마다 어딘가에 저장해보세요.', '상', 'Python'),
('admin', '이진 트리 탐색하기', '이진 탐색 트리의 루트 노드와 찾으려는 값을 입력받아, 해당 값이 트리에 존재하는지 확인하는 함수를 작성하시오.
TreeNode 클래스는 다음과 같이 정의되어 있다.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
', 'def search_bst(root, val):
    if root is None:
        return False
    if root.val == val:
        return True
    elif val < root.val:
        return search_bst(root.left, val)
    else:
        return search_bst(root.right, val)
','재귀 호출을 사용해보세요.', '상', 'Python');

INSERT INTO problems (user_id, title, content, answer, hint, difficulty, category)
VALUES
('admin', '배열의 평균 구하기', '숫자로 이루어진 배열이 주어질 때, 배열의 평균을 반환하는 함수를 작성하세요.', 'function getAverage(arr) {
  let sum = 0;
  for (let num of arr) {
    sum += num;
  }
  return sum / arr.length;
}', 'for문을 사용해보세요.', '하', 'Javascript'),
('admin', '문자 갯수 세기', '문자열과 문자가 주어졌을 때, 해당 문자가 문자열 내에 몇 번 등장하는지 반환하는 함수를 작성하세요.', 'function countChar(str, target) {
  let count = 0;
  for (let ch of str) {
    if (ch === target) count++;
  }
  return count;
}', '향상된 for문을 이용해보세요.', '하', 'Javascript'),
('admin', '배열 중복값 제거하기', '숫자로 이루어진 배열이 주어졌을 때, 중복을 제거한 새 배열을 반환하는 함수를 작성하세요.', 'function uniqueArray(arr) {
  return [...new Set(arr)];
}','중복을 허용하지 않는 컬렉션 타입을 생각해보세요.', '중', 'Javascript'),
('admin', '첫 글자 바꾸기', ' 문자열 문장을 입력받아 각 단어의 첫 글자를 대문자로 변환하는 함수를 작성하세요.','function toTitleCase(sentence) {
  return sentence
    .split(" ")
    .map(word => word.charAt(0).toUpperCase() + word.slice(1))
    .join(" ");
}','먼저 각 단어를 분리한 뒤 생각해보세요.', '중', 'Javascript'),
('admin', '괄호 검사하기', '괄호 문자열이 올바른지 검사하는 함수를 작성하세요. 괄호는 (), [], {} 세 종류가 포함될 수 있습니다.', "function isValidBrackets(s) {
  const stack = [];
  const pairs = { ')': '(', ']': '[', '}': '{' };

  for (let ch of s) {
    if ('([{'.includes(ch)) {
      stack.push(ch);
    } else if (')]}'.includes(ch)) {
      if (stack.pop() !== pairs[ch]) return false;
    }
  }

  return stack.length === 0;
}", '값이 쌓이는 타입의 컬렉션 타입을 생각해보세요.',  '상', 'Javascript'),
('admin', '타임아웃 다루기', '사용자가 빠르게 여러 번 호출하는 함수에 대해, 마지막 호출만 일정 시간 이후에 실행되도록 하는 함수를 작성하세요.
예시)
const debouncedLog = debounce(() => console.log("Hello"), 1000);
debouncedLog(); debouncedLog(); debouncedLog();
', 'function debounce(func, delay) {
  let timeoutId;
  return function (...args) {
    clearTimeout(timeoutId);
    timeoutId = setTimeout(() => func.apply(this, args), delay);
  };
}','timeout을 멈추고 설정해보세요.', '상', 'Javascript');


INSERT INTO project (user_id, name, description, status) VALUES
('admin', '테스트 프로젝트 A', '이 프로젝트는 테스트용으로 만들어진 프로젝트입니다. 내용이 길어질 경우 말줄임 처리됩니다.', '진행중'),
('admin', '프로젝트 B', '간단한 설명', '계획');

INSERT INTO project_checklist (project_id, content, is_done, priority) VALUES
(1, '프로젝트 초기 기획서 작성', TRUE, 1),
(1, '디자인 시안 검토 및 확정', FALSE, 2),
(1, '기본 기능 개발 시작', FALSE, 1),
(2, '요구사항 수집', TRUE, 2),
(2, '데이터베이스 설계', FALSE, 1);

-- project_diary 샘플 데이터
INSERT INTO project_diary (project_id, work_date, content) VALUES
(1, '2025-08-01', '오늘은 프로젝트 초기 기획서 작성과 관련해 팀 미팅을 진행했습니다. 세부 일정 조율과 역할 분담 완료.'),
(1, '2025-08-02', '디자인 시안 초안에 대해 리뷰하고 피드백을 수집했습니다. 다음 주까지 시안 확정 예정.'),
(2, '2025-07-20', '요구사항을 정리하여 문서화 작업을 완료했습니다. 주요 기능 목록을 확정.');

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'[KB국민은행] 제 7회 FUTURE FINANCE A.I. CHALLENGE', -- 제목
'KB국민은행', -- 주최기관
now(), -- 등록일
'2025-07-07 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-08-14 23:59:59', -- 마감일
'https://www.allforyoung.com/posts/67671', -- 출처
'[KB국민은행] 제 7회 FUTURE FINANCE A.I. CHALLENGE
내 아이디어로 세상을 바꿀 분 구합니다!
📌공모주제

A.I. 기술을 활용한 금융관련 서비스 및 아이디어

🙋‍♂️참가자격

- 개인 또는 팀(최대 3명)

- 전국 대학교/대학원생 재(휴)학생

제1~6회 수상자는 참가할 수 없습니다

📢접수

www.kb-aichallenge.com

제출 : 기술설명서(파워포인트)와 프로토타입(구현코드)

📆기간

-예선(기획안 제출) : 2025년 7월7일~8월14일

-예선결과발표 : 2025년 9월1일

-본선 : 2025년 9월11일(목), 이화여자대학교 ECC이삼봉홀

주최,후원 기관의 제반 상황에 따라 대회 일정 등이 변경될 수 있습니다.

🏆시상&특전

- 대상(1팀) 1000만원

- 최우수상(2팀) 각 600만원

- 우수상(2팀) 각300만원

※우수상 이상은 KB국민은행 신입행원(L1) IT데〮이터 부문 채용 시 서류필〮기 전형 합격의 특전을 제공(2026년까지 1회에 한함)

📞문의

KB국민은행 금융AI센터(P) 대리 노연희

☎(02)2073-6685✉ yeonhee.roh@kbfg.com', -- 내용
'8c31362f-cefe-45ce-a237-af934c9da8e4.jpg'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'[과기정보통부/KISIA] 제3회 KISIA 정보보호 개발자 해커톤', -- 제목
'과학기술정보통신부/한국정보보호산업협회', -- 주최기관
now(), -- 등록일
'2025-06-02 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-07-30 23:59:59', -- 마감일
'https://www.allforyoung.com/posts/65651', -- 출처
'2025 제3회 KISIA 정보보호 개발자 해커톤

※ 주제 : AI를 활용한 보안 서비스 개발

□ 개요

[주최/주관] 과학기술정보통신부 / 한국정보보호산업협회

[참가자격] 정보보호 개발에 관심이 있는 전국 대학(원)생 및 휴학생 *개인 또는 단체(4인 이하)로 참여 가능

[주제] AI를 활용한 보안 서비스 개발

□ 대회 일정

참가신청(예선) ‘25. 6. 2.(월) - 7. 30.(수) 대회 설명회 : 6. 26.(목) 14:00 - 15:00
대회 홈페이지 https://kisia.infosecdev.kr/ > 신청하기 > 개발기획서 다운로드 후 작성 > 지원서 제출하기 

예선 심사 8. 4.(월) - 8. 7.(수) *참가신청시 제출한 개발기획서 평가

예선 발표  ‘25. 8. 8.(금) 16:00 이후 

본선 사전설명회 8. 13.(수) 14:00 - 16:00 

본선  ‘25. 8. 20.(수) - 8. 21.(목) 무박2일 해커톤 *장소 : 스페이스쉐어 서울중부센터

시상식  대회종료 후 당일 진행  * 총 상금 1,100만원 상당의 상금과 과학기술정보통신부상 등 총 5점의 상장

□ 문의사항 infosecdev@cmcom.kr | 070-4849-2062', -- 내용
'b7289e20-2d80-49a3-8902-f0fda6b2d222.png'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'[과학기술정보통신부] AI·디지털 논문공모전', -- 제목
'과학기술정보통신부/한국지능정보사회진흥원, 한국정보방송통신대연합', -- 주최기관
now(), -- 등록일
'2025-07-21 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-09-26 16:00:00', -- 마감일
'https://www.allforyoung.com/posts/68250', -- 출처
'🟦 대회명

AI·디지털 논문공모전

🟦 참가자격

대학(원)생(휴학 및 졸업 예정자 가능, 개인 또는 3인 이내 공동연구 가능)

🟦 접수기간

2025.7.21.(월) ~ 2025.9.26(금) 16시까지

🟦 논문주제

AI 기본사회에서 예상되는 사회적 이슈 및 쟁점 해소를 위한 정책 방안 제시

AI와 일자리 변화, AI 알고리즘 편향성, AI 환각(할루시네이션) 등

🟦 시상내역

ㅇ (과학기술정보통신부 장관상) 300만원(1편)

ㅇ (한국지능정보사회진흥원 원장상) 200만원(1편)

ㅇ (디지털소사이어티 의장상) 200만원(1편)

🟦 접수방법

디지털소사이어티 홈페이지(https://digitalsociety.or.kr/home) 홈페이지 접수

🟦 문의사항

한국정보방송통신대연합 디지털정책협력본부(02-2132-2085, jaehoonko@kfict.or.kr)', -- 내용
'7fafd4a9-d637-4098-b5e1-a49deb136f3a.png'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'[과학기술정보통신부] AI·디지털 네이티브 토론대회', -- 제목
'과학기술정보통신부/한국지능정보사회진흥원, 한국정보방송통신대연합', -- 주최기관
now(), -- 등록일
'2025-07-21 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-08-22 16:00:00', -- 마감일
'https://www.allforyoung.com/posts/68247', -- 출처
'🟦 대회명

AI·디지털 네이티브 토론대회

🟦 참가자격

중등부, 고등부, 대학부(3개 부문), 팀 단위 신청(2인 1팀)

🟦 접수기간

2025.7.21.(월) ~ 2025.8.22(금) 16시까지

🟦 예선 토론주제

부문별 제시된 논제 중 하나를 선택하여 토론개요서 작성(세부 내용 홈페이지 참고)

ㅇ (중등부) AI와 일자리 변화

ㅇ(고등부) AI 알고리즘 편향성

ㅇ(대학부) AI 환각(할루시네이션)

🟦 시상내역

중등부

ㅇ (과학기술정보통신부 장관상) 100만원(1팀)

ㅇ (한국지능정보사회진흥원 원장상) 50만원(2팀)

ㅇ (한국정보통신대연합 회장상) 30만원(2팀)

고등부

ㅇ (과학기술정보통신부 장관상) 100만원(1팀)

ㅇ (한국지능정보사회진흥원 원장상) 50만원(2팀)

ㅇ (한국정보통신대연합 회장상) 30만원(2팀)

대학부

ㅇ (과학기술정보통신부 장관상) 300만원(1팀)

ㅇ (한국지능정보사회진흥원 원장상) 200만원(2팀)

ㅇ (한국정보통신대연합 회장상) 100만원(2팀)

🟦 접수방법

디지털소사이어티 홈페이지(https://digitalsociety.or.kr/home) 홈페이지 접수

🟦 문의사항

한국정보방송통신대연합 디지털정책협력본부(02-2132-2085, jaehoonko@kfict.or.kr)', -- 내용
'209c49a2-5d3c-4af6-a493-42b7baa1b620.png'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'[데이콘] 2025 동원 AI Challenge (~9/1)', -- 제목
'동원그룹', -- 주최기관
now(), -- 등록일
'2025-07-21 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-09-01 10:00:00', -- 마감일
'https://www.allforyoung.com/posts/68186', -- 출처
'○ 응모 주제

소비자 페르소나 기반 동원 신제품 월별 수요 예측

​

○ 공모전 사이트 링크

https://dacon.io/competitions/official/236546/overview/description

​

○ 응모자격

만 19세 이상 누구나 참여 가능

※ 외국인, 대학원생, 학계 연구자, 계약직 연구원 등은 참여할 수 있으나, 기업 소속자는 참여가 불가능합니다.

​

○ 공모전 마감일

2025.09.01 10:00

​

○ 시상 내역

총 상금 7,000만 원

​

○ 심사기준 및 방법

대회 홈페이지 내 규칙 탭 (https://dacon.io/competitions/official/236546/overview/rules) 참고

​

○ 유의사항 / 문의사항

대회 홈페이지 내 규칙 탭 (https://dacon.io/competitions/official/236546/overview/rules) 참고하여

대회 홈페이지 내 토론 탭 (https://dacon.io/competitions/official/236546/talkboard)에 문의', -- 내용
'ec9fc934-465b-42a9-81fb-239c48571c58.png'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'[데이콘] 제 7회 대구 빅데이터 분석 경진대회(~8/29)', -- 제목
'대구광역시/대구디지털혁신진흥원', -- 주최기관
now(), -- 등록일
'2025-07-21 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-08-29 09:59:00', -- 마감일
'https://www.allforyoung.com/posts/68172', -- 출처
'○ 응모 주제

- 데이터 분석 및 활용 분야

- 공공 부문 : 자유주제 및 지정주제 중 택 1(대회 홈페이지 참고)

- 산업 부문 : 산업 관련 데이터를 활용한 자유주제

- 데이터 시각화 분야

- 살기 좋은 대구를 만들기 위한 자유주제

- 빅데이터 리터러시에 기반해 과학적, 논리적 사고를 바탕으로 유의미한 해서고가 시각화 결과물 도출

○ 공모전 사이트 링크

- 데이터 분석 및 활용 분야 : 공공 부문

https://dacon.io/competitions/official/236545/overview/description

- 데이터 분석 및 활용 분야 : 산업 부문

https://dacon.io/competitions/official/236547/overview/description

- 데이터 시각화 분야

https://dacon.io/competitions/official/236548/overview/description

○ 응모자격

- 데이터 분석 및 활용 분야 : 전 국민 누구나

- 데이터 시각화 분야 : 중·고등학생

○ 공모전 마감일

2025.08.29(금) 09:59

○ 시상 내역

총 상금 2,100 만원

○ 심사기준 및 방법

- 데이터 분석 및 활용 분야 : 공공 부문

대회 홈페이지 내 규칙 탭 https://dacon.io/competitions/official/236545/overview/rules 참고

- 데이터 분석 및 활용 분야 : 산업 부문

대회 홈페이지 내 규칙 탭 https://dacon.io/competitions/official/236547/overview/rules 참고

- 데이터 시각화 분야

대회 홈페이지 내 규칙 탭 https://dacon.io/competitions/official/236548/overview/rules 참고

○ 유의사항 / 문의사항

- 데이터 분석 및 활용 분야 : 공공 부문

대회 홈페이지 내 규칙 탭 https://dacon.io/competitions/official/236545/overview/rules 참고하여

대회 홈페이지 내 토론 탭 https://dacon.io/competitions/official/236545/talkboard 에 문의

- 데이터 분석 및 활용 분야 : 산업 부문

대회 홈페이지 내 규칙 탭 https://dacon.io/competitions/official/236547/overview/rules 참고하여

대회 홈페이지 내 토론 탭 https://dacon.io/competitions/official/236547/talkboard 에 문의

- 데이터 시각화 분야

대회 홈페이지 내 규칙 탭 https://dacon.io/competitions/official/236548/overview/rules 참고하여

대회 홈페이지 내 토론 탭 https://dacon.io/competitions/official/236548/talkboard 에 문의', -- 내용
'f43dd70c-e8c1-4fa8-a2ba-21e7f0236ac4.jpg'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'NC AI 제 1회 ‘VARCO 3D’ 인디게임 공모전', -- 제목
'NC AI', -- 주최기관
now(), -- 등록일
'2025-07-18 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-07-31 23:59:59', -- 마감일
'https://www.allforyoung.com/posts/68071', -- 출처
'텍스트 한 줄로 나만의 게임을 만들어보세요! 
게임 제작, 어렵고 복잡하게 느껴지시나요?
이제는 AI 기술로 더 쉽고 빠르게 시작할 수 있습니다.

NC AI는 엔씨소프트의 오랜 게임 제작 경험을 바탕으로 고퀄리티 3D 에셋을 손쉽게 만들 수 있는 AI 기반 3D 생성 툴 ‘VARCO 3D’를 개발해왔습니다.
그리고 이제 이 기술을 누구나 사용할 수 있도록 SaaS 형태로 공개합니다!

VARCO 3D란?
‘VARCO 3D’는 텍스트 한 줄, 혹은 컨셉 스케치 한 장만으로도 3D 캐릭터, 소품, 무기, 배경 등 게임 속 다양한 에셋을 생성할 수 있는 AI 기반 3D 생성 및 편집 솔루션입니다. 게임 개발의 진입 장벽을 낮추고, 창작자들이 아이디어 구현에만 집중할 수 있도록 돕는 서비스예요.

공모전 개요
창작자 여러분의 상상력과 실력을 마음껏 펼칠 기회!
VARCO 3D를 활용한 나만의 게임을 만들어보세요.

✅ 모집 기간: 2025년 7월 18일 (금) ~ 7월 31일 (목)

✅ 대회 기간: 2025년 8월 1일 (금) ~ 8월 31일 (일)

✅ 수상작 발표 및 시상: 9월 22일 (월) (주최사 사정에 따라 변경될 수 있음)

✅ 신청 조건

✔ 대회 기간 내 게임을 완성할 수 있는 역량을 갖춘 개인 혹은 팀

✔ 게임 내 핵심 에셋의 50% 이상은 VARCO 3D 활용 필수

시상 및 혜택
✅ 상금

✔ 대상 (1팀): 300만원

✔ 최우수상 (1팀): 200만원

✔ 우수상 (5팀): 50만원

✅ 모든 참가자에게 ‘공모전 참가 내역서’ 발급

✅ ‘학생’ 참가자의 경우, NC AI 인턴십 지원 시 가산점 부여

NC AI 공식 홈페이지
https://nc-ai.com/ko
접수 신청 구글 폼 링크
https://forms.gle/PDYySQdYBmz3e4cL9', -- 내용
'1439d125-7345-4076-b794-44440b05900c.jpg'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'제3회 2025 네트워크 AI 해커톤', -- 제목
'한국전자통신연구원 (ETRI) , 부산광역시, AiNET Forum', -- 주최기관
now(), -- 등록일
'2025-07-14 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-08-15 23:59:59', -- 마감일
'https://www.allforyoung.com/posts/67883', -- 출처
'본 경진대회는 분야 1, 분야 2로 두개의 문제가 출제되며 각 문제의 Task 내 개요 및 데이터 소개란을 확인하시고 문제를 풀어보세요!

각각의 Task에 참가신청과 문제풀이 및 제출을 해주셔야 합니다.

● 지원자격

네트워크 데이터를 활용하여 인공지능을 통한 문제 해결에 관심있는 국내 대학(원)생 및 취업 준비생 누구나

개인 및 팀 (팀 구성 시 최대 4인으로 제한)

한 개인이 두 개 이상의 팀에 중복으로 참여 불가

참가신청시, 재학증명서 제출 필수

재학증명서 제출이 불가능한 취업준비생팀이 입상후보팀으로 선정되는 경우, 취업준비생임을 증빙하기 위하여 국민건강보험공단 인터넷 홈페이지에서 직접 발급이 가능한 ‘건강보험자격득실확인서’ 제출을 요구할 수 있습니다.

● 공모주제

아래 설명을 참고하시어 2가지 출제 분야에 대한 문제를 모두 해결해주시면 됩니다.

분야 #1: 완전 자율 네트워크 운영을 위한 초단기 네트워크 트래픽 예측

오늘날 클라우드 서비스와 스트리밍 서비스의 활성화로 인해 네트워크 트래픽의 급격한 변화가 빈번하게 발생합니다. 네트워크 트래픽의 예측 정확도는 서비스 품질 유지 및 네트워크 관리 효율성에 직접적인 영향을 미칩니다. 특히 초단기 예측의 경우 트래픽 급증에 따른 병목 현상 예방 및 신속한 대응에 매우 중요합니다. 본 문제를 통해 AI 모델의 초단기 시계열 예측 성능을 평가하고자 합니다.

분야1번 문제 참가신청 및 문제 링크 → https://aifactory.space/task/9162/overview

분야 #2: 서비스 품질 보장을 위한 플로우 특성 실시간 식별

네트워크 플로우 분석은 네트워크 관리 및 보안 위협 탐지에 매우 중요합니다. 플로우를 구성하는 초기 패킷들의 정보를 분석하여 플로우 지속시간과 전송량 등으로 플로우를 정확히 식별하면, 네트워크 성능 관리, 자원 할당, 비정상 행위 탐지 등에 유용하게 활용될 수 있습니다. 본 문제를 통해 AI 모델의 플로우 식별 정확도를 평가하고자 합니다.

분야2번 문제 참가신청 및 문제 링크 → https://aifactory.space/task/9163/overview

● 기간 및 일정

모집기간 : 7월 14일(월) 오전 9시  ~ 8월 15일(금) 오후 11시 59분

온라인 예선 : 8월1일(금) ~ 8월 31일(일) 오후 11시 59분

예선 온라인 해커톤 검증 기간 : 9월 1일(월) ~ 9월 14일(일)

본선 오프라인 진출팀 결과 발표 : 9월 15일 *총 6개 팀 선정 

본선 오프라인 발표일 및 시상식 : 9월 25일(목)

● 시상규모 (총 상금 1,200만원 규모, 6개 팀)

대상 1팀 : 500만원

최우수상 2팀 : 각 200만원

우수상 3팀 : 각 100만원

*시상금은 제세공과금 제외 후 지급되며, 팀이 수상하는 경우 팀 대표에게 상금이 일괄 지급됩니다

● 평가방식

분야 1과 2의 문제 풀이 점수를 정규화 후 합산하여 100점 만점으로 평가

● 지원방법

1) 온라인으로 진행되는 예선에서는 해커톤 순위와 모델 설명서에 대한 정성적 심사를 통해 상위 6개 팀을 1차적으로 선발

2) 코드 재현성 검증을 거친 이후 결격 사유가 없다면 오프라인 본선 진출

3) 코드 재현성 검증 시 심각한 결격 사유가 발견되는 경우 해당 팀의 본선 진출 자격을 박탈하며 차순위 팀에게 본선 진출 자격이 주어짐

4) 오프라인 본선 심사를 통해 온라인 예선 상위 6개 팀의 최종 순위 결정

상세 지원절차 : https://aifactory.space/task/9159/overview

● 문의

대회 운영 관련 - 인공지능팩토리 운영사무국

cs@aifactory.page

주관 기관 별 담당자

ETRI 네트워크연구본부 :  jeonhs@etri.re.kr

AI Network 포럼 사무국 : lim@osia.or.kr', -- 내용
'47a75086-cd0c-4570-9278-7fa6918d1787.jpg'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'제 2회 부산글로벌허브도시 청년 해커톤 대회', -- 제목
'부산광역시, KNN', -- 주최기관
now(), -- 등록일
'2025-07-14 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-08-03 23:59:59', -- 마감일
'https://www.allforyoung.com/posts/67861', -- 출처
'제 2회 부산글로벌허브도시 청년 해커톤 대회

 

‘대한민국의 미래, 북극항로 시대를 열다’

 

■ 주최 : 부산광역시, KNN

 

■ 일시 : 2025. 8. 20.(수) ~ 8. 21.(목) / 무박 2일

 

■ 장소 : 부산콘텐츠코리아랩(부산문화콘텐츠콤플렉스 4, 5층)

 

■ 대회 주제 : 남부권 혁신 거점이자 대한민국 신 성장동력으로서, 북극항로 시대를 열어갈 부산을 글로벌허브도시로 조성하기 위한 아이디어/정책 제안

 

■ 공모 분야

아래 5가지 분야와 관련된 주제 선정 및 정책 제안

① 글로벌 물류 ② 글로벌 금융 ③ 글로벌 디지털·첨단산업 ④ 글로벌 문화관광

⑤ 자유분야(4개 분야에 국한되지 않은 글로벌허브도시 융복합 아이디어 제시)

※ 주제 및 분야는 사전 신청을 통해 선정

※ 단, 일부 주제에 대해 쏠림 현상이 발생하는 경우, 대회 현장에서 추첨을 통해 랜덤으로 선정

 

■ 지원 자격

- 청년세대* 누구나

* 만 18세 이상 39세 이하(공고일 기준 1985. 1. 1. ~ 2007. 12. 31. 출생)

 

■ 모집 인원 : 15개팀

※ 운영상황에 따라 모집팀 수는 변경될 수 있음.

※ 팀은 3인 이상 6인 이하의 청년세대로 구성

 

■ 접수 기간 : 2025. 7. 14.(월) 12:00 ~ 8. 3.(일), 24:00까지

※ 참가신청은 접수기간에만 가능하며, 마감기한 내에 들어온 접수에 한하여 유효

 

■ 접수 방법 : 온라인 접수(구글폼)

- 참가신청링크 : https://forms.gle/MeS6ipacvc9mhNyJ8

 

■ 일정 안내

- 신청 및 접수 : 7. 14.(월), 12:00 ~ 8. 3.(일), 24:00

- 최종 참가자 발표 : 2025. 8. 8.(금) / 개별 연락 예정

- 해커톤 진행 : 2025. 8. 20.(수) ~ 8. 21.(목)

- 발표 및 시상식 : 8. 21.(목), 09:00 ~ 12:30

 

■ 시상 내역

구 분

상 격

시상내역

대상(1)

부산광역시장상

상장

KNN 사장상

200만원

최우수상(1)

KNN 사장상

상장 및 시상금(150만원)

우수상(2)

부산상공회의소 회장상

상장 및 시상금(100만원)

장려상(3)

부산연구원장

상장 및 시상금(50만원)

■ 문의

- 051-714-4674

- hackathon@beople.co.kr', -- 내용
'01d771f4-4611-4019-beff-54b641dd355b.jpg'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'2025 사이버공격방어대회(Cyber Conflict Exercise 2025) 참가자 모집', -- 제목
'국가정보원, 국가보안기술연구소', -- 주최기관
now(), -- 등록일
'2025-06-30 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-07-24 23:59:59', -- 마감일
'https://www.allforyoung.com/posts/67849', -- 출처
'2025 사이버공격방어대회(Cyber Conflict Exercise 2025) 참가자 모집

※ 사이버공격방어대회(CCE : Cyber Conflict Exercise)란?

사이버공격방어대회는 2017년부터 국가정보원과 국가보안기술연구소가 대국민 사이버보안 인식 제고와 미래 사이버보안 인재양성을 목적으로 개최하는 민·관·군 참여하는 사이버공격방어대회입니다.

■ 지원자격

대한민국 국민이라면 누구나 참가 가능

- 공공부문 : 국가 · 공공기관 종사자

- 일반부문 : 민간 · 대학(원)생 등

- 청소년부문 : 만 9세~ 만 18세의 청소년

■ 혜택

- 총 상금 : 100,000,000(1억원)

■ 대회 일정

- 예선 신청 기간 : 2025. 6. 30.(월) ~ 7. 24.(목) 23:59

- 모의 체험 : 2025. 7. 21.(월) 10:00 ~ 8. 3.(일) 18:00

- 예선전 : 2025. 8. 16.(토) 09:00 ~ 18:00 ※ 온라인

- 본선전 : 2025. 9. 11.(목) 09:00 ~ 18:00 ※ 오프라인, 서울 COEX 컨퍼런스룸 E

- 시상식 : 2025. 9. 24.(수) * 수상자 별도 안내

■ 신청방법

온라인 접수 https://bit.ly/CCE2025register

■ 문의처

- 사무국 전화번호 : 070-5172-4118

- 이메일 : cce2025@cybersummit.kr

- 공식 홈페이지 : https://cce.cstec.kr/

#사이버보안 #해커대회 #화이트해커 #CCE2025 #CSK2025 #사이버공격방어대회 #정보보안 #보안전문가 #사이버서밋코리아 #무료참가 #보안꿈나무 #사이버보안이벤트 #국가정보원 #해킹대회 #사이버전문가

문의사항

사무국 전화번호 : 070-5172-4118

이메일 : cce2025@cybersummit.kr

공식 홈페이지 : https://cce.cstec.kr/

국가정보원, 국가보안기술연구소', -- 내용
'9c28ec53-eaaf-4141-818b-35e72b154f1f.png'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'리얼톤 | Realthon', -- 제목
'쩜넷', -- 주최기관
now(), -- 등록일
'2025-07-14 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-07-31 23:59:59', -- 마감일
'https://www.allforyoung.com/posts/67789', -- 출처
'총 230만원 상당의 대중참여형 해커톤, 리얼톤에 여러분을 초대합니다!

🗣️ 리얼톤(Real-Thon) 참가자 모집
🔥 전국 IT 네트워킹 세션 "쩜넷"과 구름톤 유니브가 함께하는 리얼톤이 드디어 막을 올립니다!

🚀 이번 해커톤은 독특한 평가방법 도입 ㅡ 실제 사용자의 "실사용 후기"와 전문위원의 피드백을 바탕으로
기획력, UX, 구현력, 피칭까지 리얼하게 성장할 수 있는 기회!

💸 총상금 규모 230만원
⚡️ 100명 선착순 모집 중!

📅 8월 20일 수요일
📍 UNIST (울산 내 지역으로 변동 가능)


🗓️ 진행과정
사전준비기간(온라인): 7월 28일 (월) ~ 8월 19일 (토)
해커톤(오프라인): 8월 20일 (수) 오전 10시 ~ 오후 6시
모집마감: 7월 31일(목)

참가혜택
대중심사위원의 피드백을 들을 수 있는 기회
투자자, 스타트업 CEO의 전문 피드백
현업 풀스택 개발자와 AI 엔지니어의 멘토링 (신청 시)

🎲 놓치면 아쉬운 진짜 기회, 지금 바로 신청하세요!
👇 신청 바로가기
📌 [구글폼 링크]
https://forms.gle/sgFwf7bmhCBRWacHA

QA. 질의응답

Q. 팀빌딩은 어떻게 진행하나요?
A. 팀을 미리 만들어서 신청해주셔도 좋고, 개인 참가 이후 진행되는 팀 빌딩 온/오프라인 세션에서 팀을 조직하셔도 됩니다! 팀 빌딩이 어려우신 경우 팀 빌딩을 원활히 하실 수 있도록 쩜넷 코어 멤버들이 지원해드릴 예정입니다!

Q. 팀이 이미 있는데 팀 내용이 신청서에 없어요!
A. 팀 빌딩 및 조직은 개인 참가 이후 따로 진행할 예정입니다. 일단 리얼톤에 참가할 모든 분들은 구글 폼 작성 부탁드립니다!', -- 내용
'b6affb2b-5edd-4503-8c53-c5d56f6f614d.jpg'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'2025 금융 AI Challenge : 금융 AI 모델 경쟁(~8/29)', -- 제목
'금융보안원', -- 주최기관
now(), -- 등록일
'2025-07-14 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-08-29 14:00:00', -- 마감일
'https://www.allforyoung.com/posts/67751', -- 출처
'○ 응모 주제

FSKU 평가지표*를 기반으로 생성형 AI 모델의 성능을 개선

* FSKU(Financial Security Knowledge Understanding): 금융보안원이 개발한 금융보안 업무 적합도 벤치마크로 AI 모델의 성능을 비교 선택할 수 있도록 구성된 질문ㆍ답변 데이터

○ 공모전 사이트 링크

https://dacon.io/competitions/official/236527/overview/description

○ 응모자격

AI에 관심 있는 누구나

○ 공모전 마감일

2025.08.29(금) 14:00

○ 시상 내역

총 상금 3,300 만원

※ 수상자에게는 금융보안원 채용 특전 혜택이 제공

※ 대상 및 최우수상의 경우, 2025 금융 AI Challenge의 <Track2> 공모전과 통합하여 1팀이 선정

○ 심사기준 및 방법

대회 홈페이지 내 규칙 탭 https://dacon.io/competitions/official/236527/overview/rules 참고

○ 유의사항 / 문의사항

대회 홈페이지 내 규칙 탭 https://dacon.io/competitions/official/236527/overview/rules 참고하여

대회 홈페이지 내 토론 탭 https://dacon.io/competitions/official/236527/talkboard 에 문의', -- 내용
'0ee278f1-8c28-4bfe-be98-ad7795baee29.png'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'[데이콘] 2025 전력사용량 예측 AI 경진대회 (~8/25)', -- 제목
'한국에너지공단', -- 주최기관
now(), -- 등록일
'2025-07-14 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-08-25 10:00:00', -- 마감일
'https://www.allforyoung.com/posts/67735', -- 출처
'○ 응모 주제

건물의 전력사용량 예측 AI 모델 개발

​

○ 공모전 사이트 링크

https://dacon.io/competitions/official/236531/overview/description

​

○ 응모자격

일반인, 학생 등 누구나 대회 참가 가능

​

○ 공모전 마감일

2025.08.25 10:00

​

○ 시상 내역

[총 상금 2,000만 원]

🥇1등(1팀) - 1,000만 원

🥈2등(1팀) - 500만 원

🥉3등(1팀) - 300만 원

🥉4등(1팀) - 200만원

​

○ 심사기준 및 방법

대회 홈페이지 내 규칙 탭 (https://dacon.io/competitions/official/236531/overview/rules) 참고

​

○ 유의사항 / 문의사항

대회 홈페이지 내 규칙 탭 (https://dacon.io/competitions/official/236531/overview/rules) 참고하여

대회 홈페이지 내 토론 탭 (https://dacon.io/competitions/official/236531/talkboard)에 문의', -- 내용
'8d8dd605330c49489eac9664fb4eea45.png'
);

INSERT INTO contest(
    user_id, title, host_organization, reg_date, start_date, end_date, 
    contest_source, content, file1
)
VALUES (
    'admin',
    '2025년 통일부 공공데이터 활용 공모전',
    '통일부',
    NOW(),
    '2025-07-07 00:00:00',  -- 시작일
    '2025-08-08 18:00:00',  -- 마감일
    'https://www.allforyoung.com/posts/67504',
    '통일부에서는 통일 공공데이터에 대한 민간의 활용과 관심 촉진을 위해 ｢2025년 통일부 공공데이터 활용 공모전｣을 개최합니다. 국민 여러분의 많은 관심과 참여를 바랍니다.

o 공모 부문

① 아이디어 기획

② 공공데이터 활용 사례

o 참가 자격 : 통일부 공공데이터에 관심 있는 국민 누구나

- 개인 또는 팀(최대 4인) 단위 참여

o 공모 일정

- 접수기간 : 2025.7.7.(월) ~ 8.8.(금) 18시까지

- 서류심사 : 2025.8.11.(월) ~ 8.14.(목)

- 수상작 발표 : 2025.8.20.(수)

- 시상 : 개별 통보

* 공모전 상황에 따라 추진 일정이 일부 변경될 수 있음.

o 접수 방법 : 이메일 주소(data@unikorea.go.kr)로 참여서류 일체를 제출

- 세부 사항은 첨부파일(공모전 안내문) 참고

o 시상 내역 : 전체 10명(팀), 총상금 410만원, 통일부장관상

① 아이디어 기획 : 4명(팀), 210만원

② 공공데이터 활용 사례 : 6명(팀), 200만원

* 심사 결과 수상에 적합한 수준의 공모작이 없는 경우 시상 규모가 변경될 수 있음.

o 문의 사항

- 통일부 정보화담당관 02-2100-5746, 5992 / data@unikorea.go.kr',
    '533cd7c2-f5d0-4550-bddb-a64aa6914b4c.jpg'
);


insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'10분 게임 콘테스트 - 그때 그 게임편 -', -- 제목
'인디 드라이브(만들래)', -- 주최기관
now(), -- 등록일
'2025-08-25 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-08-31 23:59:59', -- 마감일
'https://www.allforyoung.com/posts/67497', -- 출처
'어릴적 재밌게 즐겼던 게임이 무엇인가요? 혹시 더 이상 할 수 없어서 아쉬운 게임이 있나요?

그 게임을 지금의 내가 만들 수 있지 않을까요?

어린시절의 나와 지금의 내가 함께 만드는 게임 10분게임 콘테스트 [그때 그 게임편]을 시작합니다!

■ 공모 주제

- "그때 그 게임" 

■ 공모 내용

- 플레이 타임 10분인 게임으로 참가할 수 있습니다.
- 플레이 기간동안 아무나 플레이해보고 평가를 남길 수 있습니다.
- 유저 평가가 높은 상위 10개의 게임은 결승전으로 넘어가며, 심사 점수와 유저 점수를 기준으로 상금을 지급합니다!

■ 지원 자격

- 플레이 타임 10분인 게임
- WebGL로 실행이 가능한 1인용 게임
- 미니게임, 단편게임, 프로토타입, 데모버전, 공모전 수상작 등 전부 참가 가능
- 장르 무관, 팀, 개인, 나이 무관

■ 주요 일정

- 8월 25일 ~ 31일 : 참가작 접수 기간
- 9월 5일 ~ 30일 : 플레이 기간
- 10월 1일 ~ 12일 : 결승전 (심사)
- 10월 18일 : 결과 발표 및 뒷풀이

■ 지원 방법

- 접수 기간에 별도 페이지 공개

■ 활동 혜택
이번 콘테스트는 더 재밌습니다!
1. 게임 플레이에 최적화된 새로운 환경에서 개최
2. 더 많은 플레이어들과 만나볼 수 있는 기회
3. 다양한 굿즈와 콘테스트 아카이브 전자책 제작!
자세한 내용은 모집 페이지를 확인해주세요.

■ 시상내역

- 1등 100만원
- 2등 50만원
- 3등 30만원

더 자세한 내용은 아래 링크를 참고해주세요
https://mandlemandle.com/article/e1c6e0', -- 내용
'f3300db8-6073-455a-8ce3-008f77df1cfa.png'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'넥슨 NYPC 코드배틀', -- 제목
'넥슨코리아', -- 주최기관
now(), -- 등록일
'2025-07-07 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-08-18 23:59:59', -- 마감일
'https://www.allforyoung.com/posts/67412', -- 출처
'', -- 내용
'8a963ae8-563b-446d-8be6-54ac1cfb423c.png'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'[데이콘] 데이콘 x BDA 학습자 수료 예측 AI 경진대회 (~08/25)', -- 제목
'데이콘', -- 주최기관
now(), -- 등록일
'2025-07-07 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-08-25 10:00:00', -- 마감일
'https://www.allforyoung.com/posts/67357', -- 출처
'○ 응모 주제

학습자의 수료 여부를 예측하는 AI 알고리즘 개발

○ 공모전 사이트 링크

https://dacon.io/competitions/official/236519/overview/description

○ 응모자격

데이커라면 누구나

○ 공모전 마감일

2025.08.25(월) 10:00

○ 시상 내역

대회 1등부터 3등까지는 수상 인증서 (Certification) 발급과 데이스쿨 Pro 구독권

○ 심사기준 및 방법

대회 홈페이지 내 규칙 탭 https://dacon.io/competitions/official/236519/overview/rules 참고

○ 유의사항 / 문의사항

대회 홈페이지 내 규칙 탭 https://dacon.io/competitions/official/236519/overview/rules 참고하여

대회 홈페이지 내 토론 탭 https://dacon.io/competitions/official/236519/talkboard 에 문의', -- 내용
'8941b63f-6000-4bd4-9c80-c5c28dc7e952.png'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'제 5회 KOPIS 빅데이터 공모전 (~7/30)', -- 제목
'문화체육관광부/예술경영지원센터', -- 주최기관
now(), -- 등록일
'2025-07-01 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-07-30 17:00:00', -- 마감일
'https://www.allforyoung.com/posts/67126', -- 출처
'[문화체육관광부] 총 상금 2,500만원! 제5회 KOPIS 빅데이터 공모전 개최 안내

- 공연예술 산업의 해결을 위한 좋은 아이디어가 있다면?

- 총 상금 2,500만원의 주인공이 되고 싶다면?

- 전문가의 컨설팅을 통한 아이디어의 구체화가 가능하다면?

<제5회 KOPIS 빅데이터 공모전> 은 공연예술 산업의 한계와 문제점을 개선하고, 해결을 통한 산업 발전에 기여할 수 있는 자유 주제로 창의적인 아이디어를 가진 지원자분들을 모집합니다.

빅데이터 분석, 서비스 개발 등에 관심있는 분들이라면 누구나 참여 가능하니 많은 관심 부탁드립니다!

▶ 공모 접수 바로가기: https://bigdata.kopis.or.kr/

✔️ 공모명: 제5회 KOPIS 빅데이터 공모전

✔️ 공모주제: 공연예술 산업의 한계 및 문제점 등을 개선 또는 해결하여 산업 발전에 기여할 수 있는 자유주제

✔️ 모집기간: 2025년 7월 1일(화) ~ 30일(수) 17:00 까지

✔️ 지원자격: 빅데이터 분석, 서비스 개발에 관심 있는 전 국민

✔️ 접수방법: KOPIS 공모전 홈페이지(bigdata.kopis.or.kr) > 참가신청 페이지

✔️ 시상내역: 총 2,500만원

[빅데이터 분석]

- 혁신상(1등)/ 1팀(명): 500만원

- 진흥상(2등)/ 1팀(명): 300만원

- 돋움상(3등)/ 1팀(명): 200만원

[서비스 개발]

- 혁신상(1등)/ 1팀(명): 600만원

- 진흥상(2등)/ 1팀(명): 500만원

- 돋움상(3등)/ 1팀(명): 400만원

▶ 공모 접수 바로가기: https://bigdata.kopis.or.kr/

※ 문의

제5회 KOPIS 빅데이터 분석 공모전 운영사무국

- 공식홈페이지: https://bigdata.kopis.or.kr/
- Tel : 070-7709-2350 (10:00~17:00)
- E-mail : kopisbigdata@gmail.com

', -- 내용
'c15ad69e-b936-4b6e-93cc-d4f10f3c1754.jpg'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'2025 블레이버스 MVP 개발 해커톤: 시즌2', -- 제목
'강동구청년해냄센터, 어치브모먼트(Blaybus)', -- 주최기관
now(), -- 등록일
'2025-06-30 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-08-04 23:59:59', -- 마감일
'https://www.allforyoung.com/posts/67029', -- 출처
'2025 블레이버스 MVP 개발 해커톤: 시즌2
초기창업팀의 아이디어를 현실로 만들 IT 전문가를 찾습니다!

블레이버스 MVP 개발 해커톤에서
실제 시장 검증에 사용될 MVP를 함께 구현하며
비즈니스 가치를 창출하는 특별한 경험을 함께하고
10일 간의 몰입을 통해 스타트업 현업의 생생함을 느껴보세요!

📌행사 정보
- 일시: 2025년 8월 13일(수) ~ 8월 25일(월)
- 장소: 온라﻿인 협업 및 오프라인 파이널데이(서울시 강동구)
- 주제: 실제 창업 아이템 기획서 기반 웹버전 MVP 구현
- 참가신청: 2025년 6월 30일(월) ~ 8월 4일(월)
- 총상금: 240만원
- 주최: 강동구 청년해냄센터, 어치브모먼트

🙋참가 대상
실전 창업 아이템 구현 및 창업 팀과의 협업에 관심있는 누구나 (고등학생 이상)

- PM, UX/UI디자인, 개발 직군
- 개인 혹은 팀 단위로 신청 가능
- 최대 100명까지 참여 가능

[안내]
- 신청자가 100명을 초과하는 경우 신청 내용 기반으로 참가자를 선발합니다.
- 개인 참가자의 경우 참가 확정 후 팀빌딩 기간 동안 팀을 구성해주세요.
- 팀 참가자도 팀빌딩 기간 동안 인원을 추가 영입할 수 있어요.
- 최종 팀 구성 시 PM 1명 이상, 디자이너 1명 이상, 개발자 2명 이상을 필수로 포함해주세요.

💡행사 구성
1. 온라인 팀빌딩: 참가 확정자 대상 팀빌딩 기간
2. 온라인 킥오프: 행사 상세 안내 및 창업 아이템 소개
3. 온라인 협업: 10일 간 온라인 협업
4. 파이널데이: 오프라인으로 진행되는 최종 PT 및 시연, 주제별 시상

📢문의처
- 인스타그램 @blaybus_
- 카카오톡채널 https://pf.kakao.com/_BFxgNG', -- 내용
'f496f1ea-2aa7-4de7-9219-d51f07a2a6be.png'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'퀄컴 Edge AI 개발자 해커톤', -- 제목
'퀄컴 테크날러지스 Inc., 마이크로소프트 코리아', -- 주최기관
now(), -- 등록일
'2025-06-30 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-07-24 23:59:59', -- 마감일
'https://www.allforyoung.com/posts/66925', -- 출처
'개요 
퀄컴에서 마이크로소프트 코리아, 고려대학교와 함께 Edge AI 개발자 해커톤을 개최합니다. 참가자들은 Snapdragon® X 시리즈 기반 Copilot+ PC에서 실행되는 엣지 AI 애플리케이션을 개발하며, 글로벌 무대에서 자신의 기술력을 자랑할 수 있습니다. 

 

주제 
Snapdragon® X 시리즈 프로세서가 탑재된 Copilot+ PC에서 실행되는 Edge AI애플리케이션을 개발하게 됩니다.  

 

상금: 총 1,000만원 

1등(1팀): 500만원 

2등(1팀): 300만원 

3등(1팀): 100만원 

4등(2팀): 각 50만원 (2팀) 

※ 상금은 제세공과금 포함 금액이며, 제세공과금 공제 후 지급 됩니다. 

 

참가 자격 (아래 모든 조건 충족) 

대한민국 거주자인 만 19세 이상인 국내 대학(원) 재학생 

퀄컴 ID 보유(무료 가입) 

퀄컴 스냅드래곤 인사이더즈 인스타그램 팔로잉 또는 유튜브 구독 

고려대학교 재학생 1인 이상 포함된 2~5인 팀 
※ 개인 참가 불가 

※ 참가 자격은 이벤트 페이지에 있는 공식 규칙 참조 

 

일정 (한국시간 기준) 

예선 등록: 6월 30일 ~ 7월 24일 

예선 제출 마감: 7월 27일 

본선 진출자 발표: 8월 8일 (이메일 개별 통보) 

본선 진출자 대면 교육: 8월 11일 오후 @ 마이크로소프트 코리아 사무실 

본선 제출 마감: 8월 26일 

본선: 8월 27일 

 

예선 제출물 요건 (기한: 2025년 7월27일까지) 

(필수 항목) 제안서, 유튜브 시연 영상 

(권장/추천 항목) 앱 및 GitHub 소스코드 및 문서 

이메일로 제출 

 

본선 진출자 대면 교육 (2025년8월11일 오후) 

장소: 마이크로소프트 코리아 사무실 

교육 및 Copilot+PC 대여 예정 

 

본선 제출물 (기한: 2025년8월26일까지) 

(필수 항목) 발표자료, 앱 및 GitHub 소스코드 및 문서 

이메일로 제출 

 

본선 (2025년 8월27일) 

오전: 시연, 기술검증 

오후: 발표, 심사, 시상 

 

문의 

이메일: hackathon2025.korea@qti.qualcomm.com 

 

이번 해커톤 행사에 관심 가져주셔서 감사하고, 이벤트에 대한 상세 정보 및 공식 규칙은 이벤트 공식 페이지에서 확인하세요! 

 

Snapdragon 및 Qualcomm 브랜드 제품은 Qualcomm Technologies, Inc. 및/또는 그 자회사의 제품입니다. 

', -- 내용
'7f42b7d0-2763-44a6-adbd-683f56fc77c2.png'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'NK이노베이션 해커톤 대회 참가자 모집 공고', -- 제목
'부산외국어대학교, 나비미래연구소', -- 주최기관
now(), -- 등록일
'2025-06-25 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-07-25 18:00:00', -- 마감일
'https://www.allforyoung.com/posts/66851', -- 출처
'NK이노베이션 해커톤 대회 참가자 모집 공고
2025년 NK이노베이션 해커톤 경진대회 계획을 다음과 같이 공고하오니 많은 참여바랍니다.

2025년 6월 25일

NK이노베이션

부산외국어대학교, 나비미래연구소

1. 경진대회 개요

□ 목적
◦ 북한이 개방될 때를 대비하여, 교육(Education), 기술(Technology)등, 여러 주요 영역(의식주, 문화예술, 인프라등)에서의 역량을 발휘하고, 기술성, 사업화 가능성 진단 등을 통해 우수한 아이디어를 발굴하는데 목적이 있다.
◦ 실제 현재와 미래에 연속 혹은 발전 가능성이 있는 비즈니스 아이디어를 발굴하여 사업성을 돕는데 기여하기 위함이다.

□ 해커톤팀 구성
◦ 트랙1: 1인 혹은 팀으로 구성하여 가상적으로 2030년 북한의 개방시 이노베이션이 필요한 여러 방면에서 참신한 아이디어를 발굴해 개발한다. 팀구성은 신청후 선발된 신청자 중 필요시 팀을 새로 만들 수도 있다.
◦ 트랙2: 곧바로 사업화가 가능한 비지니스 아이템을 중심으로 팀을 미리 구성하여 신청한다.
* 트랙1, 트랙2: 탈북민을 포함한 대한민국 및 해외의 모든 사람이 신청 가능함.
(단, 한국어로 소통이 가능한 자)

2. 신청방법
◦ (공고) ‘25. 6. 25 (수)
◦ (신청 ․ 접수 기간) ‘25. 6. 25(수) ~ 7. 25(금) 18:00 까지
◦ (접수방법) 이메일 nkinnovation.group@gmail.com으로 제출
◦ (제출서류) 신청서 및 세부내역서
트랙1: 자기소개서, 아이디어 소개서 및 가점서류(필요시)
트랙2: 자기소개서, 사업계획서 및 가점서류(필요시)
- 신청서 및 세부내역서 파일형식은 워드나 한글파일로 접수
- 가점서류는 해당자만 제출 (pdf, jpg 가능)

※ 자세한 사항은 첨부파일 확인바랍니다.

3. 문의처
◦(전 화) 010-3231-0996
◦(E-mail) nkinnovation.group@gmail.com', -- 내용
'b4ac1056-fa97-4aaa-a5b8-27b47ca614f1.png'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'2025년 천안시 데이터 분석 아이디어 경진대회', -- 제목
'천안', -- 주최기관
now(), -- 등록일
'2025-06-25 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-08-31 23:59:59', -- 마감일
'https://www.allforyoung.com/posts/66803', -- 출처
'■ 공모명

- 2025년 천안시 데이터 분석 아이디어 경진대회


■ 공모일정

- 과제접수 : 2025년 6월 25일 (수) ~ 8월 31일 (일)

- 서면평가 : 2025년 9월 초 (예정) 

- 본선 PT심사 : 2025년 10월 중순 (예정)

  ※ 시상일자는 추후 대상자들에게 통보

■ 응모자격

- 대한민국 국민 누구나 참여 가능하며, 개인 및 팀(1인 이상 4인 이내)으로 접수 가능

■ 시상내역 (총 상금 1,000만원)

- 대상: 1팀, 500만원

- 최우수상: 1팀, 200만원

- 우수상: 3팀, 100만원

■ 주제

- 천안시 행정 업무개선을 위한 데이터 분석

- 공공데이터 및 수집 가능한 각종 민간데이터 활용


지정과제

- [지역경제/소비]

천안시 지역경제/소비 활성화를 위한 데이터 분석 및 정책제언

- [환경/기후위기]

천안시 환경/기후위기 대응 방안을 위한 데이터 분석 및 정책제언

- [재난안전]

천안시 재난안전 관련 데이터 분석 및 정책제언

- [복지/사회]

천안시 복지/사회 활성화 관련 데이터 분석 및 정책제언
 

2. 자율과제

- 데이터 분석을 통한 천안시 행정서비스 개선 아이디어 기획

- 다양한 데이터로 표현하는 천안시의 현재 모습

※ 지정주제·자율주제 중 선택 및 지정주제 별도 가산점 없음

※ 지정주제 상세내용 브리핑 하단의 [공고 첨부파일] 참고


■ 제출물

- 결과물 : 분석보고서(데이터 전처리 및 분석방법 설명 ppt 및 pdf), 분석결과[시각화 대시보드(twbx), 파이썬 코드(ipynb), 시연영상 등]

- 개인정보 수집·이용 동의서
- 데이터 보안 서약서

  ※ 하단 첨부파일 작성 후 구글폼 양식 접수 및 제출


■ 활용 데이터

1. 활용 데이터 예시

- 공공데이터포털(https://www.data.go.kr/)

- 원윈도우(https://www.data1window.kr/)

- 충남데이터포털올담(https://alldam.chungnam.go.kr/)

- 천안시청(https://www.cheonan.go.kr/)

- 국가통계포털(https://kosis.kr/)

- 통계청 마이크로데이터(https://mdis.kostat.go.kr/)

- 지방재정365(https://www.lofin365.go.kr/)

- 기타 데이터 활용 시 출처 및 합법성 입증 필요

 

2. 기준

- 정식 API 또는 공공 포털 등을 통해 합법적으로 수집한 데이터만 허용

- 수집 시점, 범위, 주요 컬럼(변수) 등 기술 필수

※ 제안서 내 활용 데이터 명확히 명시, 출처(URL포함) 반드시 기재

※ 제3자의 라이선스를 침해하거나 상업적 목적의 민간 데이터 무단 사용은 엄격히 금지됨


■ 활용 시각화 도구

- 참가자가 원하는 데이터 시각화 솔루션(Tableau 등)

- 1차 예선심사는 PPT (Powerpoint)로 제출, 2차 PT 심사에서 대시보드를 활용한 시연 발표 진행


■ 문의처

- 운영사무국 문의 게시판
- 이메일 : gongmo@stunning.kr

■ 유의사항

- 데이터 분석 아이디어 경진대회 신청·접수 이후 공개된 수상작의 지적 재산권은 주관·주최기관인 천안시와 코데이터솔루션(주)에 귀속되며, 추후 본 서비스와 마케팅에 활용될 수 있음

- 본 공모전에 제출된 응모작들에 대한 저작권은 응모자에게 있으나, 주관기관은 응모작의 시연화면 또는 이와 유사한 결과물을 게시하거나, 출판, 전시하는 방법으로 활용할 수 있음

- 응모자와 별도의 협의를 통해 응모작의 일부 또는 전부를 주관기관이 제공하는 서비스에 적용하거나, 기타 목적으로 활용할 수 있음

- 제안된 아이디어는 순수 창작물이어야 하며, 응모자 본인이 직접 작성한 내용에 한하여 출품해야 함

- 동일자료로 타 기관 대회에서 입상하였거나, 타인의 저작권 침해 시 지원이 취소됨

- 본 공모전 진행 일정과 내용은 주관기관의 사정에 따라서 변경될 수 있음', -- 내용
'31ab8863-ec04-4fa9-901f-884d5a1d4fc7.jpg'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'[데이콘] 2025 Samsung Collegiate Programming Challenge : AI 챌린지 (~7/28)', -- 제목
'삼성전자,삼성리서치', -- 주최기관
now(), -- 등록일
'2025-06-19 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-07-28 10:00:00', -- 마감일
'https://www.allforyoung.com/posts/66663', -- 출처
'○ 응모 주제
사용자의 일상 사진을 이해하는 멀티모달 AI 모델 개발

○ 공모전 사이트 링크
https://dacon.io/competitions/official/236500/overview/description

○ 응모자격
대학(원) 재학 또는 휴학생
※ 전공 및 학년 제한 없음
※ 졸업유예생 참여불가
※ SCPC Algorithm 챌린지와 중복 참여 불가

○ 공모전 마감일
2025.07.28(월) 10:00

○ 시상 내역
총 상금 6,000 만원

○ 심사기준 및 방법
대회 홈페이지 내 규칙 탭 https://dacon.io/competitions/official/236500/overview/rules 참고

○ 유의사항 / 문의사항
대회 홈페이지 내 규칙 탭

https://dacon.io/competitions/official/236500/overview/rules 참고하여


대회 홈페이지 내 토론 탭

https://dacon.io/competitions/official/236500/talkboard 에 문의', -- 내용
'7c3f1ee2-266f-4514-a918-0e32abdf3573.jpg'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'[국립국어원] 2025년 인공지능(AI) 말평 경진대회 개최 (6/11 ~ 7/31)', -- 제목
'국립국어원', -- 주최기관
now(), -- 등록일
'2025-06-11 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-07-31 23:59:59', -- 마감일
'https://www.allforyoung.com/posts/66210', -- 출처
'국립국어원이 2025년 인공지능(AI)말평 경진대회를 개최합니다.

올해 대회의 과제는 한국어 어문 규범 기반 생성과 한국문화 질의응답 입니다.

[한국어 어문 규범 기반 생성]

- 한국어 어문 규범 지식 자료를 참조하여 문장을 교정하고 그 이유를 설명하는 과제

- (가) 유형(데이터 제한)으로만 운영: 외부 데이터를 사용하거나 데이터를 증강하여 사용 불가

[한국문화 질의응답]

- 한국의 문화 전반에 관한 지식을 묻는 문제에 단답형 및 서술형으로 답변하는 과제

- (가) 유형과 (나) 유형으로 나누어 운영(과제 카드를 선택하여 제출)

· (가) 유형(데이터 제한): 외부 데이터를 사용하거나 데이터를 증강하여 사용 불가

· (나) 유형(데이터 허용): 외부 데이터를 사용하거나 데이터를 증강하여 사용 가능

국립국어원 ‘인공지능(AI)말평(https://kli.korean.go.kr/benchmark)’을 통해 참가 신청 및 제출하실 수 있으며, 평가 점수와 순위는 인공지능(AI)말평’의 순위표 또는 Korean AI Malpyeong Leaderboard에서 확인하실 수 있습니다.

참가 기간은 6월 11일(수)부터 7월 31일(목)까지이며, 한국어 인공지능에 관심 있는 개인 또는 단체라면 누구나 참가할 수 있습니다. 시상식은 한글 및 한국어 정보처리 학술대회 워크숍에서 개최합니다(10. 17. 예정). 자세한 내용은 대회 포스터와 과제별 안내를 참고하여 주시기를 바랍니다.

▶ 지원 신청 관련 링크

접수 방법 안내

https://kli.korean.go.kr/benchmark/taskBoardsOrdtm/manualList.do

▶ 공식 카페/블로그 및 SNS

- 홈페이지 : https://kli.korean.go.kr/benchmark

▶ 기타

유의사항 / 문의사항은 과제 게시판 내 문의 메뉴 사용', -- 내용
'cbf3f4a0-1948-4e76-89f1-fb7d002b684d.png'
);

insert into contest(user_id, title, host_organization, reg_date, start_date, end_date, contest_source, content, file1)
values('admin',
'제9회 개방형 클라우드 플랫폼(K-PaaS) 활용 공모전(~10/31)', -- 제목
'과학기술정보통신부 / 한국지능정보사회진흥원', -- 주최기관
now(), -- 등록일
'2025-03-24 00:00:00', -- 시작일 '2025-07-23 00:00:00'
'2025-10-31 23:59:59', -- 마감일
'https://www.allforyoung.com/posts/63119', -- 출처
'제9회 개방형 클라우드 플랫폼(K-PaaS) 활용 공모전(~10/31)
■ 공모부문
1. 서비스 개발 : K-PaaS를 활용하여 개발·배포·시연 가능한 완성도 높은 클라우드 네이티브 기반 응용서비스 개발
2. 아이디어 기획 : K-PaaS 환경에서 개발·구현 가능하며 상품화, 서비서화, 창업할 수 있는 창의적인 서비스 아이디어 기획
*서비스 개발 부문과 아이디어 부문 동일 내용으로 동시 참가 불가
3. 공공부문 구축ㆍ활용사례 : K-PaaS를 활용해 정보시스템을 구축ㆍ운영 중인 중앙행정기관, 지자체, 공공기관의 우수사례 발굴

■ 참가대상
1. 서비스 개발 & 아이디어기획 : 학생·개인(예비창업자) 등5명 이내 팀
2. 공공부문 구축ㆍ활용사례 : K-PaaS 구축ㆍ활용 관련 중앙행정기관, 지자체, 공공기관

■ 신청방법
1. 서비스개발 & 아이디어 기획
공모전 홈페이지(contest.k-paas.org)를 통한 온라인 접수 후
작품제출 양식 작성 후 이메일(contest@k-paas.or.kr) 제출

2. 공공부문 구축ㆍ활용 사례
신청서 양식 작성 후 이메일(contest@k-paas.or.kr)제출
*서비스 개발&아이디어 기획 부문 작품제출 양식과 공공부문 구축ㆍ활용 사례
신청서 양식은 공모전 홈페이지에서 다운로드
※ 공모전 상세 추진 일정과 세부사항은 공모전 홈페이지 참고

■ 공모전 세부사항
- 참가신청 : 참가신청 및 행사 안내용 사이트 운영 (http://contest.k-paas.org)
참가팀 기본 정보 입력
(2025년 3월 24일 ~ 10월 31일, 온라인)
*6월까지 사전 신청한 서비스 개발 공모팀에 한정하여 교육&멘토링 지원
(아이디어 공모는 해당없음)

- 교육지원 : K-PaaS 활용(2주), MSA 활용(2주), CSP 환경 이용 가이드 등 온라인 교육 예정 (6월 예정)
*서비스 개발 부문 참가팀 대상

- 멘 토 링 : 서비스 개발 공모팀을 대상으로 개발 기획서 작성 및
클라우드 네이티브 기반의 서비스 개발을 위한 가이드 지도·조언 (8~10월 예정)

- 작품제출 : 2025년 10월 31일까지 공모 부문별 작품 제출
*공모전 대표메일(contest@k-paas.or.kr)로 제출, 양식은 공모전 사이트에서 다운로드
① 서비스 개발 부문 : 개발 상세 내용과 개발 서비스 URL을 포함한 신청서
② 아이디어 기획 부문 : 아이디어 상세 내용을 포함한 신청서
※ 본선진출팀의 경우 발표 자료(PPT, PDF 등) 및 시연 영상을 추가 제출 필요 (~11/18)
③ 공공부문 구축ㆍ활용사례 부문 : 신청서 양식 작성 후 이메일(contest@k-paas.or.kr) 제출

- 예선심사 : 사전 심사를 통해 본선 진출 12팀 선정
(2025년 11월 5~6일, 서면심사)
*서비스 개발 부문 10팀, 아이디어 2팀

- 본선·시상 : 본선 진출팀의 발표·시연을 전문 심사위원 및 참가자 평가
2025년 11월 19일(목)(예정), NIA 서울사무소 B1 회의실(예정)
※ 시상은 Open Cloud Platform PUB 2025 행사(12월)에서 진행 예정

■ 시상계획
총 14점(상금 3,000만원, 크레딧 600만원 규모)
< 서비스 개발 부문>
- 금상 : 과학기술정보통신부장관상 / 1 / 500만원
- 은상 : 한국지능정보사회진흥원장상 / 1 / 400만원
- 동상
* OPA의장상 / 1 / 300만원
* 숭실대학교총장상 / 1 / 300만원
- 특별상
* 네이버클라우드상 / 1 / 200만원
부상(크레딧) : 200만원(NCP)
* 엔에이치엔클라우드상 / 1 / 200만원
부상(크레딧) : 200만원(NHN)
* 케이티클라우드상 / 1 / 200만원
부상(크레딧) : 200만원(KT)
* CCCR이사장상 / 1 / 200만원
* KACI협회장상 / 1 / 200만원
* OPDC이사장상 / 1 / 200만원
*서비스 개발 수상작 중 일부 대상 클라우드 환경 이용 가능한 크레딧 제공

<아이디어 부문>
- 최우수상 : 과학기술정보통신부장관상 / 1 / 200만원
- 우수상 : 한국지능정보사회진흥원장상 / 1 / 100만원

<공공부문 활용 우수사례 부문>
- 최우수상 / 과학기술정보통신부장관상 / 1 / - / -
- 우수상 / 한국지능정보사회진흥원장상 / 1 / - / -

■ 문의처
개방형 클라우드 플랫폼 센터
T. 02-6372-5001/ E. contest@k-paas.or.kr

', -- 내용
'3235f819-52aa-46bb-abfd-500245b72c66.jpg'
);

INSERT INTO faq (question, answer) VALUES
('이 사이트는 어떤 목적으로 만들어졌나요?', '개발자들이 코딩 테스트 문제와 노하우를 공유하고, 협업할 수 있도록 돕기 위해 만들어졌습니다.'),
('회원 가입은 어떻게 하나요?', '상단 메뉴에서 회원 가입 버튼을 클릭해 이메일과 비밀번호를 입력하면 가입할 수 있습니다.'),
('AI 코딩 문제 풀이 지원 기능은 어떻게 사용하나요?', '문제 풀이 페이지에서 질문을 입력하면 AI가 풀이 예제와 설명을 제공합니다.'),
('내 작업물을 어떻게 저장하고 관리할 수 있나요?', '개인 다이어리 기능과 Git 연동을 통해 버전 관리하며 저장할 수 있습니다.'),
('코딩 작업용 음악 플레이리스트는 어떻게 활성화하나요?', '개인 도구 메뉴에서 플레이리스트 탭을 선택해 원하는 음악을 재생할 수 있습니다.'),
('문제 풀이 중 모르는 내용이 있으면 어디서 질문하나요?', '커뮤니티 게시판에 질문글을 작성하거나, 댓글로 다른 사용자와 소통할 수 있습니다.'),
('프로젝트 협업 기능은 무엇인가요?', '팀원들과 작업을 분담하고 진행 상황을 공유하는 협업 도구를 제공합니다.'),
('포인트는 어떻게 획득하나요?', '문제 풀이 완료, 게시글 작성, 다른 사용자 도움 등 다양한 활동으로 포인트를 받을 수 있습니다.'),
('포인트는 어디에 사용할 수 있나요?', '포인트는 사이트 내 특별 콘텐츠 접근, 아이템 구매 등 다양한 용도로 활용할 수 있습니다.'),
('비밀번호를 잊어버렸을 때 어떻게 하나요?', '로그인 페이지에서 ‘비밀번호 찾기’ 기능을 통해 이메일 인증 후 재설정할 수 있습니다.'),
('모바일에서도 사이트를 사용할 수 있나요?', '네, 모바일 최적화가 되어 있어 스마트폰과 태블릿에서도 편리하게 이용할 수 있습니다.'),
('내 프로필 정보는 어떻게 수정하나요?', '로그인 후 마이페이지에서 닉네임, 이메일 등 프로필 정보를 변경할 수 있습니다.'),
('사이트 이용 중 오류가 발생하면 어떻게 신고하나요?', '고객센터 또는 피드백 메뉴를 통해 상세 내용을 작성해 보내주시면 빠르게 처리하겠습니다.');

INSERT INTO qna (title, content, writer_id, is_answered, view_count) VALUES
('Java에서 NullPointerException 해결 방법은?', 'NullPointerException이 발생할 때 어떻게 디버깅하면 좋을까요?', 'user03', TRUE, 45),
('Python 리스트와 튜플 차이점 알려주세요.', '리스트와 튜플의 차이점과 사용 예시가 궁금합니다.', 'user07', TRUE, 32),
('JavaScript 비동기 처리 방법?', 'Promise와 async/await 중 어떤걸 쓰는 게 좋나요?', 'user01', TRUE, 51),
('Git에서 브랜치 병합 충돌 해결법', '충돌이 났을 때 안전하게 해결하는 팁이 있을까요?', 'user10', TRUE, 60),
('Spring Boot에서 @Autowired가 안 될 때?', '의존성 주입이 제대로 되지 않을 때 점검할 부분이 무엇인가요?', 'user05', TRUE, 27),
('JPA와 MyBatis 차이점', '두 기술의 장단점과 추천 사용 사례를 알고 싶어요.', 'user09', TRUE, 38),
('코딩 테스트 준비 방법 추천해주세요.', '효율적인 문제 풀이 방법과 공부 루틴을 알고 싶어요.', 'user06', FALSE, 15),
('CSS Flexbox와 Grid 차이점', '레이아웃 잡을 때 Flexbox와 Grid 중 언제 어떤걸 쓰면 좋나요?', 'user02', TRUE, 40),
('Python에서 딕셔너리 키 순서 보장되나요?', 'Python 3.7 이상에서 딕셔너리 키 순서가 유지되는지 궁금합니다.', 'user08', FALSE, 20),
('REST API 설계 원칙', '좋은 REST API를 설계하기 위한 기본 원칙은 무엇인가요?', 'user04', TRUE, 55),
('Docker 이미지 최적화 방법', '이미지 크기를 줄이고 빌드 속도를 높이는 팁이 있을까요?', 'user01', FALSE, 22),
('React 상태 관리 추천 라이브러리', '초보자가 배우기 좋은 상태 관리 라이브러리가 있을까요?', 'user03', TRUE, 30),
('SQL 인덱스 잘못 사용하면 어떤 문제가?', '잘못된 인덱스 사용 시 성능 문제 예시가 궁금해요.', 'user07', TRUE, 28);

INSERT INTO qna_reply (qna_id, content, writer_id) VALUES
(1, 'NullPointerException은 참조 변수가 null인 상태에서 접근할 때 발생합니다. 디버깅 시 변수 값을 꼼꼼히 체크하세요.', 'admin'),
(2, '리스트는 변경 가능하지만 튜플은 불변입니다. 튜플은 변경하지 않을 데이터에 적합합니다.', 'admin'),
(3, 'async/await가 가독성 면에서 좋으며, Promise를 더 간단히 사용할 수 있습니다.', 'admin'),
(4, '충돌 시 `git status`와 `git diff`를 확인하고, 수동으로 충돌 부분을 수정하세요.', 'admin'),
(5, '@Autowired가 작동하지 않을 땐 컴포넌트 스캔 설정과 빈 등록을 먼저 확인하세요.', 'admin'),
(6, 'JPA는 객체 중심이고 MyBatis는 SQL 중심입니다. 복잡한 쿼리는 MyBatis가 유리합니다.', 'admin'),
(8, 'Flexbox는 1차원 레이아웃, Grid는 2차원 레이아웃에 적합합니다.', 'admin'),
(10, 'REST API는 리소스 기반 URI, HTTP 메서드 활용, 무상태성을 지켜야 합니다.', 'admin'),
(12, 'React의 기본 useState 외에 Redux, Recoil 등이 많이 쓰입니다. 초보자는 Context API도 추천합니다.', 'admin'),
(13, '잘못된 인덱스는 오히려 쿼리 속도를 늦출 수 있으니 쿼리 실행 계획을 확인하세요.', 'admin');
