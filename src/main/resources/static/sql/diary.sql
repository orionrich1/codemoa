DROP TABLE IF EXISTS project_diary;
DROP TABLE IF EXISTS project_checklist;
DROP TABLE IF EXISTS project;

CREATE TABLE project (
    project_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(10) NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    status ENUM('계획', '진행중', '종료') NOT NULL DEFAULT '계획',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE project_checklist (
    checklist_id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT NOT NULL,
    content VARCHAR(255) NOT NULL,
    is_done BOOLEAN NOT NULL DEFAULT FALSE,
    priority TINYINT NOT NULL DEFAULT 3,
    FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE
);

CREATE TABLE project_diary (
    diary_id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT NOT NULL,
    work_date DATE NOT NULL,
    content TEXT,
    FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE,
    UNIQUE KEY (project_id, work_date)
);

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

