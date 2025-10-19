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

--------------------------------
--Insert into USERS_TB MYSQL  --
--------------------------------
INSERT INTO users_tb(pid ,name ,email, password, created_date, created_by, last_modified_by, last_modified_date) VALUES (1111 , 'shabtay shalem' , 'shabtay.shalem@gmail.com' ,123 , '2024-02-12 02:32:02', 'REGISTER', 'MOCKAROO', '2024-03-19 00:10:09');
INSERT INTO users_tb(pid ,name ,email, password, created_date, created_by, last_modified_by, last_modified_date) VALUES (2222 , 'karin shalem' , 'karin.shalem@gmail.com' ,456, '2024-08-31 08:07:05', 'REGISTER', 'MOCKAROO', '2025-01-06 23:20:07');
INSERT INTO users_tb(pid ,name ,email, password, created_date, created_by, last_modified_by, last_modified_date) VALUES (3333 , 'avigail shalem' , 'avigail.shalem@gmail.com' ,789 ,'2024-11-02 12:17:14', 'REGISTER', 'MOCKAROO', '2024-11-27 23:33:55');
INSERT INTO users_tb(pid ,name ,email, password, created_date, created_by, last_modified_by, last_modified_date) VALUES (4444 , 'odel shalem' , 'odel.shalem@gmail.com' ,012, '2024-07-15 21:13:31', 'REGISTER', 'MOCKAROO', '2024-06-19 06:09:19');



-- This creates duplication erro in postgres thus I use the above query w/o initializing the id field, 
-- OtherwiseIn need to sync postgresql DB (See commects in my GIT hub)

-- INSERT INTO users_tb(id , pid ,name ,email, password) VALUES (1, 1111 , 'shabtay shalem' , 'shabtay.shalem@gmail.com' ,123);
-- INSERT INTO users_tb(id , pid ,name ,email, password) VALUES (2, 2222 , 'karin shalem' , 'karin.shalem@gmail.com' ,456);
-- INSERT INTO users_tb(id , pid ,name ,email, password) VALUES (3, 3333 , 'avigail shalem' , 'avigail.shalem@gmail.com' ,789);
-- INSERT INTO users_tb(id , pid ,name ,email, password) VALUES (4, 4444 , 'odel shalem' , 'odel.shalem@gmail.com' ,012);

-------------------------
-- Insert into ROLES_TB
-------------------------

INSERT INTO roles_tb(pid ,role ,user_id ,created_date, created_by, last_modified_by, last_modified_date) VALUES (1111 , 'ADMIN' , 1 , '2024-02-12 02:32:02', 'REGISTER', 'MOCKAROO', '2024-03-19 00:10:09');
INSERT INTO roles_tb(pid ,role ,user_id ,created_date, created_by, last_modified_by, last_modified_date) VALUES (1111 , 'SUPER-ADMIN' , 1 , '2024-08-31 08:07:05', 'REGISTER', 'MOCKAROO', '2025-01-06 23:20:07');
INSERT INTO roles_tb(pid ,role ,user_id ,created_date, created_by, last_modified_by, last_modified_date) VALUES (1111 , 'MANAGER' , 1 , '2024-11-02 12:17:14', 'REGISTER', 'MOCKAROO', '2024-11-27 23:33:55');
INSERT INTO roles_tb(pid ,role ,user_id ,created_date, created_by, last_modified_by, last_modified_date) VALUES (1111 , 'CEO' , 1, '2024-07-15 21:13:31', 'REGISTER', 'MOCKAROO', '2024-06-19 06:09:19');

INSERT INTO roles_tb(pid ,role ,user_id ,created_date, created_by, last_modified_by, last_modified_date) VALUES (2222 , 'ADMIN' , 2 , '2024-02-12 02:32:02', 'REGISTER', 'MOCKAROO', '2024-03-19 00:10:09');
INSERT INTO roles_tb(pid ,role ,user_id ,created_date, created_by, last_modified_by, last_modified_date) VALUES (2222 , 'SUPER-ADMIN' , 2, '2024-08-31 08:07:05', 'REGISTER', 'MOCKAROO', '2025-01-06 23:20:07');
INSERT INTO roles_tb(pid ,role ,user_id ,created_date, created_by, last_modified_by, last_modified_date) VALUES (2222 , 'MANAGER' , 2 , '2024-11-02 12:17:14', 'REGISTER', 'MOCKAROO', '2024-11-27 23:33:55');
INSERT INTO roles_tb(pid ,role ,user_id ,created_date, created_by, last_modified_by, last_modified_date) VALUES (2222 , 'CEO' , 2, '2024-07-15 21:13:31', 'REGISTER', 'MOCKAROO', '2024-06-19 06:09:19');

INSERT INTO roles_tb(pid ,role ,user_id ,created_date, created_by, last_modified_by, last_modified_date) VALUES (3333 , 'ADMIN' , 3, '2024-02-12 02:32:02', 'REGISTER', 'MOCKAROO', '2024-03-19 00:10:09');
INSERT INTO roles_tb(pid ,role ,user_id ,created_date, created_by, last_modified_by, last_modified_date) VALUES (3333 , 'MANAGER' , 3, '2024-08-31 08:07:05', 'REGISTER', 'MOCKAROO', '2025-01-06 23:20:07');

INSERT INTO roles_tb(pid ,role ,user_id ,created_date, created_by, last_modified_by, last_modified_date) VALUES (4444 , 'ADMIN' , 4,'2024-02-12 02:32:02', 'REGISTER', 'MOCKAROO', '2024-03-19 00:10:09');
INSERT INTO roles_tb(pid ,role ,user_id ,created_date, created_by, last_modified_by, last_modified_date) VALUES (4444 , 'MANAGER' , 4, '2024-08-31 08:07:05', 'REGISTER', 'MOCKAROO', '2025-01-06 23:20:07');

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