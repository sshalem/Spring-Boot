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

INSERT INTO users_tb(id , name ,email, published) VALUES (1, 'shabtay shalem' , 'shabtay.shalem@gmail.com' ,1);
INSERT INTO users_tb(id , name ,email, published) VALUES (2, 'karin shalem' , 'karin.shalem@gmail.com' ,0);
INSERT INTO users_tb(id , name ,email, published) VALUES (3, 'avigail shalem' , 'avigail.shalem@gmail.com' ,1);
INSERT INTO users_tb(id , name ,email, published) VALUES (4, 'odel shalem' , 'odel.shalem@gmail.com' ,0);

-------------------------
-- Insert into ADDRESS_TB
-------------------------
-- INSERT INTO address_tb(street, city ,user_id) VALUES ('Menachem Begin 15/15' , 'Holon', 1);
-- INSERT INTO address_tb(street, city ,user_id) VALUES ('Dvora Bartonov Begin 2/15' , 'Holon', 2);
-- INSERT INTO address_tb(street, city ,user_id) VALUES ('Menachem Begin 15/15' , 'Holon', 3);
-- INSERT INTO address_tb(street, city ,user_id) VALUES ('Dvora Bartonov Begin 2/15' , 'Holon', 4);


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