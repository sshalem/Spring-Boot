-------------------------
--Insert into USERS_TB --
-------------------------

INSERT INTO users_tb(id , pid ,name ,email, password) VALUES (1, 1111 , 'shabtay shalem' , 'shabtay.shalem@gmail.com' ,123);
INSERT INTO users_tb(id , pid ,name ,email, password) VALUES (2, 2222 , 'karin shalem' , 'karin.shalem@gmail.com' ,123);
INSERT INTO users_tb(id , pid ,name ,email, password) VALUES (3, 3333 , 'avigail shalem' , 'avigail.shalem@gmail.com' ,123);
INSERT INTO users_tb(id , pid ,name ,email, password) VALUES (4, 4444 , 'odel shalem' , 'odel.shalem@gmail.com' ,123);

-------------------------
-- Insert into ROLES_TB
-------------------------
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (1, 1111 , 'ADMIN' , 1);
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (2, 1111 , 'SUPER-ADMIN' , 1);
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (3, 1111 , 'MANAGER' , 1);
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (4, 1111 , 'USER' , 1);
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (5, 1111 , 'VP' , 1);
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (6, 1111 , 'CEO' , 1);

INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (11, 2222 , 'ADMIN' , 2);
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (12, 2222 , 'SUPER-ADMIN' , 2);
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (13, 2222 , 'MANAGER' , 2);
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (20, 2222 , 'CEO' , 2);

INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (21, 3333 , 'ADMIN' , 3);
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (22, 3333 , 'SUPER-ADMIN' , 3);
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (23, 3333 , 'MANAGER' , 3);

INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (41, 4444 , 'ADMIN' , 4);
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (42, 4444 , 'SUPER-ADMIN' , 4);
INSERT INTO roles_tb(id , pid ,role ,user_id) VALUES (43, 4444 , 'MANAGER' , 4);


