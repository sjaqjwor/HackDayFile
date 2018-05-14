-- =============================================
-- Author: @min16
-- =============================================

CREATE TABLE IF NOT EXISTS user(
  user_id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  role VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password CHAR(64) NOT NULL,
  PRIMARY KEY (user_id)
) ENGINE='InnoDB' default charset='utf8';

CREATE TABLE IF NOT EXISTS manuscript(
  manuscript_id INT(11) NOT NULL AUTO_INCREMENT,
  user_id INT(11) NOT NULL,
  title VARCHAR(255) NOT NULL,
  summary VARCHAR(255) NOT NULL,
  turn INT(11) NOT NULL,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (manuscript_id),
  FOREIGN KEY (user_id) REFERENCES user(user_id) on delete cascade
) ENGINE='InnoDB' default charset='utf8';

CREATE TABLE IF NOT EXISTS vote(
  user_id INT(11) NOT NULL,
  manuscript_id INT(11) NOT NULL,
  turn INT(11) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(user_id) on delete cascade,
  FOREIGN KEY (manuscript_id) REFERENCES manuscript(manuscript_id) on delete cascade
) ENGINE='InnoDB' default charset='utf8';

CREATE TABLE IF NOT EXISTS image(
  user_id INT(11) NOT NULL,
  manuscript_id INT(11) NOT NULL,
  sequence INT(11) NOT NULL,
  path VARCHAR(255) NOT NULL,
  media_type VARCHAR(255) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(user_id) on delete cascade,
  FOREIGN KEY (manuscript_id) REFERENCES manuscript(manuscript_id) on delete cascade
) ENGINE='InnoDB' default charset='utf8';

CREATE TABLE IF NOT EXISTS thumbnail(
  user_id INT(11) NOT NULL,
  manuscript_id INT(11) NOT NULL,
  path VARCHAR(255) NOT NULL,
  media_type VARCHAR(255) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(user_id) on delete cascade,
  FOREIGN KEY (manuscript_id) REFERENCES manuscript(manuscript_id) on delete cascade
) ENGINE='InnoDB' default charset='utf8';

CREATE TABLE IF NOT EXISTS alarm(
  sender INT(11) NOT NULL,
  receiver INT(11) NOT NULL,
  content VARCHAR(255) NOT NULL,
  turn INT(11) NOT NULL,
  FOREIGN KEY (sender) REFERENCES user(user_id) on delete cascade,
  FOREIGN KEY (receiver) REFERENCES user(user_id) on delete cascade
) ENGINE='InnoDB' default charset='utf8';

CREATE TABLE IF NOT EXISTS battle (
  applicant INT(11) NOT NULL,
  accepter INT(11) NOT NULL,
  state VARCHAR(255) NOT NULL,
  turn INT(11) NOT NULL,
  winner INT(11) NOT NULL,
  FOREIGN KEY (applicant) REFERENCES user(user_id) on delete cascade,
  FOREIGN KEY (accepter) REFERENCES user(user_id) on delete cascade,
  FOREIGN KEY (winner) REFERENCES user(user_id) on delete cascade
) ENGINE='InnoDB' default charset='utf8';

CREATE TABLE IF NOT EXISTS round (
  turn INT(11) NOT NULL,
  start_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  end_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE='InnoDB' default charset='utf8';