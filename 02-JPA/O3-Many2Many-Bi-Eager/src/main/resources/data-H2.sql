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

