DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS phones;

CREATE TABLE `user_roles` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `username` varchar(50) NOT NULL,
                              `AUTHORITY` varchar(50) NOT NULL,
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `uni_username_role` (`AUTHORITY`,`username`)
);

CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       created_at VARCHAR(255) NOT NULL,
                       last_login VARCHAR(255) NOT NULL,
                       token VARCHAR(255) NOT NULL,
                       enabled BIT NOT NULL DEFAULT 1
);

CREATE TABLE phones (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        user_id INT NOT NULL,
                        number BIGINT UNSIGNED NOT NULL,
                        city_code INT NOT NULL,
                        country_code VARCHAR(255) NOT NULL,
                        FOREIGN KEY (user_id) REFERENCES users(id)
);