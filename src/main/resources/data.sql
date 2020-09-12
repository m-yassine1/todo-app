DROP TABLE IF exists todo cascade;
DROP TABLE IF exists login_token cascade;
DROP TABLE IF exists forgot_password cascade;
DROP TABLE IF exists user  cascade;

CREATE TABLE user (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  mobile_number VARCHAR(255) NOT NULL UNIQUE,
  birthday DATE NOT NULL,
  gender varchar(255)
);

CREATE TABLE todo (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description TEXT,
  due_date DATETIME,
  status varchar(255) DEFAULT 'intial',
  category varchar(255),
  user_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE login_token (
  token VARCHAR(255) PRIMARY KEY,
  user_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE forgot_password (
  token VARCHAR(255) PRIMARY KEY,
  user_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id)
);