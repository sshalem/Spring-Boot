-----------------------------------
-- Set FOREIGN KEY CHECK to 0 
-- TRUNCATE the data from tables
----------------------------------
SET FOREIGN_KEY_CHECKS=0; 
TRUNCATE TABLE tutorial_tags;
TRUNCATE TABLE tutorials;
TRUNCATE TABLE tags;
SET FOREIGN_KEY_CHECKS=1;

-----------------------------------
-- DROP TABLEs if exist
----------------------------------
-- DROP TABLE IF EXISTS roles_tb;
-- DROP TABLE IF EXISTS users_tb;
-- DROP TABLE IF EXISTS tutorial_tags;
-- DROP TABLE IF EXISTS tutorials;
-- DROP TABLE IF EXISTS tags;
-- DROP TABLE IF EXISTS course_student;
-- DROP TABLE IF EXISTS student_tb;
-- DROP TABLE IF EXISTS course_tb;


-------------------------
--Insert into tags 
-------------------------
insert into tags (id, name) values(1, 'good tag');
insert into tags (id, name) values(2, 'Excelent tag');
insert into tags (id, name) values(3, 'marvales tag');

-------------------------
--Insert into tutorials 
-------------------------
insert into tutorials (id, description, published, title) values(1, 'many to many', 1, 'JAVA Title');
insert into tutorials (id, description, published, title) values(2, 'many to one', 0, 'C sharp Title');
insert into tutorials (id, description, published, title) values(3, 'one 2 many ', 0, 'Node Title');
insert into tutorials (id, description, published, title) values(4, 'one 2 one ', 1, 'Python Title');
insert into tutorials (id, description, published, title) values(5, 'annonymos', 1, 'PHP Title');

-------------------------
--Insert into tutorial_tags 
-------------------------
insert into tutorial_tags(tutorial_id, tag_id) values(1,1);
insert into tutorial_tags(tutorial_id, tag_id) values(1,2);
insert into tutorial_tags(tutorial_id, tag_id) values(1,3);
insert into tutorial_tags(tutorial_id, tag_id) values(2,1);
insert into tutorial_tags(tutorial_id, tag_id) values(2,2);
insert into tutorial_tags(tutorial_id, tag_id) values(2,3);
insert into tutorial_tags(tutorial_id, tag_id) values(4,1);
insert into tutorial_tags(tutorial_id, tag_id) values(4,2);


-- DROP TABLE IF EXISTS roles_tb;

-- CREATE TABLE IF NOT EXISTS roles_tb (
--   id bigint(20) NOT NULL AUTO_INCREMENT,
--   pid bigint(20) NOT NULL,
--   role varchar(255) DEFAULT NULL,
--   user_id bigint(20) DEFAULT NULL,
--   PRIMARY KEY (id),  
--   FOREIGN KEY (user_id) REFERENCES users_tb (id)
-- );

-- DESCRIBE roles_tb;
-- SELECT * FROM roles_tb;


-------------------------------------------------

-- DROP TABLE IF EXISTS users_tb;

-- CREATE TABLE IF NOT EXISTS users_tb(    
-- 	id bigint(20) NOT NULL AUTO_INCREMENT,
--     email varchar(255) DEFAULT NULL,
--     name varchar(255) DEFAULT NULL,
--     password varchar(255) DEFAULT NULL,
--     pid bigint(20) NOT NULL,
--     PRIMARY KEY (id)
-- );

-- DESCRIBE users_tb;
-- SELECT * FROM users_tb;