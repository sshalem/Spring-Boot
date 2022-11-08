-----------------------------------
-- Set FOREIGN KEY CHECK to 0 
-- TRUNCATE the data from tables
----------------------------------
-- SET FOREIGN_KEY_CHECKS=0; 
-- TRUNCATE TABLE roles_tb;
-- TRUNCATE TABLE users_tb;
-- TRUNCATE TABLE tutorial_tags;
-- TRUNCATE TABLE tutorials;
-- TRUNCATE TABLE tags;
-- TRUNCATE TABLE course_student;
-- TRUNCATE TABLE student_tb;
-- TRUNCATE TABLE course_tb;
-- SET FOREIGN_KEY_CHECKS=1;

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
--Insert into USERS_TB --
-------------------------

INSERT INTO users_tb(id , pid ,name ,email, password) VALUES (1, 1111 , 'shabtay shalem' , 'shabtay.shalem@gmail.com' ,123);
INSERT INTO users_tb(id , pid ,name ,email, password) VALUES (2, 2222 , 'karin shalem' , 'karin.shalem@gmail.com' ,456);
INSERT INTO users_tb(id , pid ,name ,email, password) VALUES (3, 3333 , 'avigail shalem' , 'avigail.shalem@gmail.com' ,789);
INSERT INTO users_tb(id , pid ,name ,email, password) VALUES (4, 4444 , 'odel shalem' , 'odel.shalem@gmail.com' ,012);

-------------------------
-- Insert into ROLES_TB
-------------------------
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (1, 1111 , 'ADMIN' , 1);
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (2, 1111 , 'SUPER-ADMIN' , 1);
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (3, 1111 , 'MANAGER' , 1);
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (4, 1111 , 'CEO' , 1);

INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (5, 2222 , 'ADMIN' , 2);
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (6, 2222 , 'SUPER-ADMIN' , 2);
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (7, 2222 , 'MANAGER' , 2);
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (8, 2222 , 'CEO' , 2);

INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (9, 3333 , 'ADMIN' , 3);
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (10, 3333 , 'MANAGER' , 3);

INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (11, 4444 , 'ADMIN' , 4);
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (12, 4444 , 'MANAGER' , 4);


