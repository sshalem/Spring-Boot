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
-- TRUNCATE TABLE course_student;
-- TRUNCATE TABLE student_tb;
-- TRUNCATE TABLE course_tb;
SET FOREIGN_KEY_CHECKS=1;

insert into products(id, product_name, price, desc) values(1, 'car', 50000, 'kia');

