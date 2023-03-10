DROP TABLE IF EXISTS phones;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id VARCHAR(120) PRIMARY KEY,
                       user_name VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE phones (
                        id VARCHAR(120) PRIMARY KEY,
                        user_id VARCHAR(120) NOT NULL,
                        number BIGINT NOT NULL,
                        city_code INT NOT NULL,
                        country_code VARCHAR(255) NOT NULL
);

INSERT INTO users (id, user_name, email, password) VALUES ('1', 'nv', 'nv@test.com', '$2y$10$18hJHKHFhYnh9TcPD6/KDOtsI.JD7qewqLaqVjOEhurALMHopwn.i');

INSERT INTO phones (id, user_id, number, city_code, country_code) VALUES ('1','1', 303, 456, 'ARG');
INSERT INTO phones (id, user_id, number, city_code, country_code) VALUES ('2','1', 303, 456, 'ARG');
INSERT INTO phones (id, user_id, number, city_code, country_code) VALUES ('3','1', 303, 456, 'ARG');
