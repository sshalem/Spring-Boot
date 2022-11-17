-----------------------------------
-- Set FOREIGN KEY CHECK to 0 
-- TRUNCATE the data from tables
----------------------------------
SET FOREIGN_KEY_CHECKS=0; 
-- TRUNCATE TABLE roles_tb;
-- TRUNCATE TABLE users_tb;
-- TRUNCATE TABLE tutorial_tags;
-- TRUNCATE TABLE tutorials;
-- TRUNCATE TABLE tags;
TRUNCATE TABLE student_course;
TRUNCATE TABLE student_tb;
TRUNCATE TABLE course_tb;
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
--Insert into student_tb 
-------------------------

INSERT INTO student_tb(student_id, first_name, last_name, identity_number, email) 
	VALUES (1, 'shabtay', 'shalem', 33457763, 'shabtay.shalem@gmail.com');
	
INSERT INTO student_tb(student_id, first_name, last_name, identity_number, email) 
	VALUES (2, 'karin', 'shalem', 37475845, 'karin.shalem@gmail.com');
	
INSERT INTO student_tb(student_id, first_name, last_name, identity_number, email) 
	VALUES (3, 'avigail', 'shalem', 221218928, 'avigail.shalem@gmail.com');
	
INSERT INTO student_tb(student_id, first_name, last_name, identity_number, email) 
	VALUES (4, 'odel', 'shalem', 223675497, 'odel.shalem@gmail.com');

-------------------------
-- Insert into course_tb
-------------------------
INSERT INTO course_tb(course_id, course_name, course_number, learning_year, start_date, end_date) 
	VALUES (1, 'JAVA', 'JV-2001', 2020, '2018-04-30', '2019-02-12');

INSERT INTO course_tb(course_id, course_name, course_number, learning_year, start_date, end_date) 
	VALUES (2, 'C-Sharp', 'C-sharp-2001', 2020, '2018-04-22', '2019-02-15');

INSERT INTO course_tb(course_id, course_name, course_number, learning_year, start_date, end_date) 
	VALUES (3, 'PYTHON', 'PYTHON-2001', 2018, '2018-04-13', '2022-04-26');

INSERT INTO course_tb(course_id, course_name, course_number, learning_year, start_date, end_date) 
	VALUES (4, 'ReactJS', 'ReactJS-77', 2018, '2018-03-19', '2019-03-29');

INSERT INTO course_tb(course_id, course_name, course_number, learning_year, start_date, end_date) 
	VALUES (5, 'Angular', 'Angular-2018', 2018, '2018-03-17', '2019-02-12');

INSERT INTO course_tb(course_id, course_name, course_number, learning_year, start_date, end_date) 
	VALUES (6, 'ETL', 'ETL-1999', 2021, '2021-04-05', '2022-02-11');

INSERT INTO course_tb(course_id, course_name, course_number, learning_year, start_date, end_date) 
	VALUES (7, 'JAVASCRIPT', 'JS-15', 2021, '2021-04-08', '2022-02-12');

INSERT INTO course_tb(course_id, course_name, course_number, learning_year, start_date, end_date) 
	VALUES (8, 'NODEJS', 'NODEJS-57', 2021, '2021-04-09', '2022-02-15');


-------------------------
-- Insert into course_tb
-------------------------
INSERT INTO student_course(course_id, student_id) VALUES(1,1);
INSERT INTO student_course(course_id, student_id) VALUES(1,2);
INSERT INTO student_course(course_id, student_id) VALUES(1,3);
INSERT INTO student_course(course_id, student_id) VALUES(1,4);
INSERT INTO student_course(course_id, student_id) VALUES(2,1);
INSERT INTO student_course(course_id, student_id) VALUES(2,2);
INSERT INTO student_course(course_id, student_id) VALUES(2,3);
INSERT INTO student_course(course_id, student_id) VALUES(2,4);
INSERT INTO student_course(course_id, student_id) VALUES(3,1);
INSERT INTO student_course(course_id, student_id) VALUES(3,2);
INSERT INTO student_course(course_id, student_id) VALUES(3,3);
INSERT INTO student_course(course_id, student_id) VALUES(3,4);
INSERT INTO student_course(course_id, student_id) VALUES(4,1);
INSERT INTO student_course(course_id, student_id) VALUES(4,2);
INSERT INTO student_course(course_id, student_id) VALUES(5,1);
INSERT INTO student_course(course_id, student_id) VALUES(5,2);
INSERT INTO student_course(course_id, student_id) VALUES(7,1);
INSERT INTO student_course(course_id, student_id) VALUES(6,2);
INSERT INTO student_course(course_id, student_id) VALUES(6,3);
INSERT INTO student_course(course_id, student_id) VALUES(7,4);


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