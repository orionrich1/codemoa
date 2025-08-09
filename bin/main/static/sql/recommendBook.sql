
## DATABASE 생성 및 선택
## DROP DATABASE IF EXISTS springboot;
## CREATE DATABASE IF NOT EXISTS springboot;
use springboot;

## recommendList 테이블
drop table if exists book;
CREATE TABLE IF NOT EXISTS book (
    book_no INTEGER AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(20) NOT NULL,
    title VARCHAR(100) NOT NULL,
    publisher VARCHAR(300),  
    reg_date TIMESTAMP NOT NULL,
    pub_date TIMESTAMP,
    book_source VARCHAR(300),
    content TEXT,  -- TEXT로 변경
    file1 VARCHAR(100),
    rating DECIMAL(3,1),
    isbn VARCHAR(60),
    total_page_num INTEGER,
    view_count INTEGER DEFAULT 0
    -- FOREIGN KEY (user_id) REFERENCES user(user_id)
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

  insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'', -- title
'', -- publisher
now(), -- regDate
' 00:00:00', -- pubDate
'', -- bookSource
'', -- content
'', -- file1
, -- rating
'', -- isbn
 -- totalPageNum
 );

  insert into book(user_id, title, publisher, reg_date, pub_date, book_source, content, file1, rating, isbn, total_page_num)
values('admin',
'', -- title
'', -- publisher
now(), -- regDate
' 00:00:00', -- pubDate
'', -- bookSource
'', -- content
'', -- file1
, -- rating
'', -- isbn
 -- totalPageNum
 );