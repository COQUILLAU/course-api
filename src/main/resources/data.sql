CREATE TABLE IF NOT EXISTS user (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
    );

INSERT INTO user (username, password) VALUES ('user1', 'password1');
INSERT INTO user (username, password) VALUES ('user2', 'password2');
INSERT INTO user (username, password) VALUES ('user3', 'password3');
