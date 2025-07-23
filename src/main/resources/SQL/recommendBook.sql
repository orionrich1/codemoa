## DATABASE 생성 및 선택
## DROP DATABASE IF EXISTS springboot;
CREATE DATABASE IF NOT EXISTS springboot;
use springboot;

## recommendList 테이블
drop table if exists book;
CREATE TABLE IF NOT EXISTS book (
    recommend_no INTEGER AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(20) NOT NULL,
    title VARCHAR(50) NOT NULL,
    subtitle VARCHAR(300),  
    category VARCHAR(50),
    rating DECIMAL(2,1),
    reg_date TIMESTAMP NOT NULL,
    lecture_source VARCHAR(300),
    content1 VARCHAR(1000),
    content2 TEXT,  -- TEXT로 변경
    recommend_num INTEGER DEFAULT 0
);

insert into book(user_id, title, subtitle, category, rating, reg_date, lecture_source, content1, content2) 
values ('admin', 
'한 입 크기로 잘라먹는 타입스크립트(TypeScript)',
'문법을 넘어 동작 원리와 개념 이해까지 배워도 배워도 헷갈리는 타입스크립트 이제 제대로 배워보세요! 여러분을 타입스크립트 마법사로 만들어드립니다.',
'Typescript',
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
아무리 어렵고 복잡한 개념이더라도 쉽고 재미있게 살펴볼 거예요.'
);

select * from book;
