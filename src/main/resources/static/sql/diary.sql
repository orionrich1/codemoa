DROP TABLE IF EXISTS project_diary;
DROP TABLE IF EXISTS project_checklist;
DROP TABLE IF EXISTS project;

CREATE TABLE project (
    project_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(10) NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    status ENUM('계획', '진행중', '종료') NOT NULL DEFAULT '계획',
    created_at DATETIME DEFAULT NOW()
);

CREATE TABLE project_checklist (
    checklist_id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT NOT NULL,
    content VARCHAR(255) NOT NULL,
    is_done BOOLEAN NOT NULL DEFAULT FALSE,
    priority TINYINT NOT NULL DEFAULT 3,
    created_at DATETIME DEFAULT NOW(),
    FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE
);

CREATE TABLE project_diary (
    diary_id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT NOT NULL,
    work_date DATE NOT NULL,
    content TEXT,
    created_at DATETIME DEFAULT NOW(),
    FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE,
    UNIQUE KEY (project_id, work_date)
);