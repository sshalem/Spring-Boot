-------------------------
--Insert into USERS_TB --
-------------------------

INSERT INTO tutorials(id , title ,description, published) VALUES (1, 'JAVA tutorial' , 'learn JAVA SPRING' ,1);
INSERT INTO tutorials(id , title ,description, published) VALUES (2, 'C Sharp tutorial' , 'learn C Sharp' ,0);
INSERT INTO tutorials(id , title ,description, published) VALUES (3, 'Python tutorial' , 'learn Python' ,1);
INSERT INTO tutorials(id , title ,description, published) VALUES (4, 'React tutorial' , 'learn React' ,0);

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