DROP TABLE IF EXISTS  comment;
DROP TABLE IF EXISTS  community_board;
DROP TABLE IF EXISTS  point_log;


CREATE TABLE community_board (
  board_no INT AUTO_INCREMENT PRIMARY KEY,
  user_id VARCHAR(10), -- user 테이블 참조 (외래키)

  title VARCHAR(255) NOT NULL,
  content TEXT NOT NULL,
  recommend INT,
  file1 VARCHAR(255),
  category VARCHAR(255) NOT NULL,

  post_type ENUM('NORMAL', 'QUESTION') NOT NULL,
  staked_points INT,
  is_adopted BOOLEAN DEFAULT FALSE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT fk_board_user
    FOREIGN KEY (user_id)
    REFERENCES user(user_id)
    ON DELETE CASCADE
);

CREATE TABLE comment (
  comment_no INT AUTO_INCREMENT PRIMARY KEY,
  content TEXT NOT NULL,
  is_adopted BOOLEAN DEFAULT FALSE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  user_id VARCHAR(10),
  board_no INT,

  CONSTRAINT fk_comment_user
    FOREIGN KEY (user_id)
    REFERENCES user(user_id)
    ON DELETE CASCADE,

  CONSTRAINT fk_comment_board
    FOREIGN KEY (board_no)
    REFERENCES community_board(board_no)
    ON DELETE CASCADE
);

CREATE TABLE point_log (
  point_log_id INT AUTO_INCREMENT PRIMARY KEY,
  change_amount INT NOT NULL,
  reason VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  user_id VARCHAR(10),

  CONSTRAINT fk_pointlog_user
    FOREIGN KEY (user_id)
    REFERENCES user(user_id)
    ON DELETE CASCADE
);