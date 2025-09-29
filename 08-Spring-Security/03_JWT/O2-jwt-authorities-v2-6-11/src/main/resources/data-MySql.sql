-------------------------
-- Insert into users_tb
-------------------------
insert into users_tb(user_id,name,email,password) values(1, 'shabtay shalem', 'shabtay.shalem@gmail.com','$2a$10$ITuqt3CLVrMuo0yGH7GhHuWttO.45i4aCH.jcX3s1IUTOiJLcXVk6');
insert into users_tb(user_id,name,email,password) values(2, 'karin shalem', 'karin.shalem@gmail.com','$2a$10$ITuqt3CLVrMuo0yGH7GhHuWttO.45i4aCH.jcX3s1IUTOiJLcXVk6');
insert into users_tb(user_id,name,email,password) values(3, 'avigail shalem', 'avigail.shalem@gmail.com','$2a$10$ITuqt3CLVrMuo0yGH7GhHuWttO.45i4aCH.jcX3s1IUTOiJLcXVk6');
insert into users_tb(user_id,name,email,password) values(4, 'ariel shalem', 'ariel.shalem@gmail.com','$2a$10$ITuqt3CLVrMuo0yGH7GhHuWttO.45i4aCH.jcX3s1IUTOiJLcXVk6');

-------------------------
-- Insert into roles_tb
-------------------------
insert into roles_tb(role_id,role) values (1, 'SUPER-ADMIN');
insert into roles_tb(role_id,role) values (2, 'ADMIN');
insert into roles_tb(role_id,role) values (3, 'USER');

-------------------------
-- Insert into course_tb
-------------------------
INSERT INTO user_role(user_id, role_id) VALUES(1,1);
INSERT INTO user_role(user_id, role_id) VALUES(1,2);
INSERT INTO user_role(user_id, role_id) VALUES(1,3);
INSERT INTO user_role(user_id, role_id) VALUES(2,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,2);
INSERT INTO user_role(user_id, role_id) VALUES(2,3);
INSERT INTO user_role(user_id, role_id) VALUES(3,3);
