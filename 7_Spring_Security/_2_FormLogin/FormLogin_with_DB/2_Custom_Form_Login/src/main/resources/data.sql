/* 
Link for reference
https://ozenero.com/sql-tutorial-mysql-many-to-many-relationship  

Retruns numbers of rows in table:  
select count(*) from BOOKS_TB;


This creates table in DB:
--------------------------
	create table books_tb (
		id INT AUTO_INCREMENT,  
		author varchar(255), 
		book_image_url varchar(255), 
		bookname varchar(255), 
		primary key (id)
	);
	

This is a stored procedure run in MySql
---------------------------------------
	DELIMITER $$
	CREATE PROCEDURE InitializeDB(startNumber INT, endNumber INT)
	BEGIN   
		
		DECLARE counter INT DEFAULT startNumber;
		DECLARE en INT DEFAULT endNumber;

		WHILE counter <= en DO
			insert into books_tb(bookname, author, book_image_url) values('בראשית', 'משה רבנו', 'book_bereshit');
			SET counter = counter + 1;
		END WHILE;

	END$$
	DELIMITER ;

This is calling the procedure to Run on MySql
---------------------------------------------
CALL InitializeDB(1400, 2000);

*/

/* 
when Inser to the User and Role tables , Ialse set the id so I could know 
how to match the tables with Many To Many association
*/
insert into users_tb(id, user_id, username, password, email) values(101, 36452832,'shabtay','$2a$10$dP1Pqe23nQFKK5ax/xiQF.ULrwqn1X4kyW0XPNmMpssl8SwEOm0Uu','shabtay@com');
insert into users_tb(id, user_id, username, password, email) values(102, 12384539,'karin','$2a$10$dP1Pqe23nQFKK5ax/xiQF.ULrwqn1X4kyW0XPNmMpssl8SwEOm0Uu','karin@com');
insert into users_tb(id, user_id, username, password, email) values(103, 26748434,'avigail','$2a$10$dP1Pqe23nQFKK5ax/xiQF.ULrwqn1X4kyW0XPNmMpssl8SwEOm0Uu','avigail@com');
insert into users_tb(id, user_id, username, password, email) values(104, 23111134,'ariel','$2a$10$dP1Pqe23nQFKK5ax/xiQF.ULrwqn1X4kyW0XPNmMpssl8SwEOm0Uu','ariel@com');
insert into users_tb(id, user_id, username, password, email) values(105, 23999994,'odel','$2a$10$dP1Pqe23nQFKK5ax/xiQF.ULrwqn1X4kyW0XPNmMpssl8SwEOm0Uu','odel@com');
insert into users_tb(id, user_id, username, password, email) values(106, 23325774,'itamar','$2a$10$dP1Pqe23nQFKK5ax/xiQF.ULrwqn1X4kyW0XPNmMpssl8SwEOm0Uu','itamar@com');

insert into roles_tb(id, role) values(201,'USER');
insert into roles_tb(id, role) values(202,'ADMIN');
insert into roles_tb(id, role) values(203,'SUPER_ADMIN');

insert into users_roles values(101,203);
insert into users_roles values(102,203);
insert into users_roles values(103,201);
insert into users_roles values(104,201);
insert into users_roles values(105,201);
insert into users_roles values(106,201);

/*
Here I insert to tables also setting the id column , 
But its not necessaery because the DB will assign by itself an id number
In the first 40 rows I added Id 
In the next I didn't add them
*/

insert into books_tb(id, bookname, author, book_image_url) values(1, 'בראשית', 'משה רבנו', 'book_1_bereshit');
insert into books_tb(id, bookname, author, book_image_url) values(2, 'שמות', 'משה רבנו', 'book_2_shmot');
insert into books_tb(id, bookname, author, book_image_url) values(3, 'ויקרא', 'משה רבנו', 'book_3_vayikra');
insert into books_tb(id, bookname, author, book_image_url) values(4, 'במדבר', 'משה רבנו', 'book_4_bamidbar');
insert into books_tb(id, bookname, author, book_image_url) values(5, 'דברים', 'משה רבנו', 'book_5_dvarim');
insert into books_tb(id, bookname, author, book_image_url) values(6, 'יהושע', 'יהושוע בן נון', 'book_6_yehoshua');
insert into books_tb(id, bookname, author, book_image_url) values(7, 'שופטים', 'שמואל הנביא', 'book_7_shoftim');
insert into books_tb(id, bookname, author, book_image_url) values(8, 'שמואל', 'שמואל הנביא', 'book_8_shmuel');
insert into books_tb(id, bookname, author, book_image_url) values(9, 'מלכים', 'שמואל הנביא', 'book_9_melachim');
insert into books_tb(id, bookname, author, book_image_url) values(10, 'ישעיהו', 'ישעיהו הנביא', 'book_10_yeshaayahu');
insert into books_tb(id, bookname, author, book_image_url) values(11, 'ירמיהו', 'ירמיהו הנביא', 'book_11_yermiyahu');
insert into books_tb(id, bookname, author, book_image_url) values(12, 'יחזקאל', 'יחזקאל הנביא', 'book_12_yehezkel');
insert into books_tb(id, bookname, author, book_image_url) values(13, 'דניאל', 'חז"ל', 'book_13_daniel');
insert into books_tb(id, bookname, author, book_image_url) values(14, 'הושע', 'חז"ל', 'book_14_hoshea');
insert into books_tb(id, bookname, author, book_image_url) values(15, 'יואל', 'חז"ל', 'book_15_yoel');
insert into books_tb(id, bookname, author, book_image_url) values(16, 'עמוס', 'חז"ל', 'book_16_amos');
insert into books_tb(id, bookname, author, book_image_url) values(17, 'עובדיה', 'חז"ל', 'book_17_ovadya');
insert into books_tb(id, bookname, author, book_image_url) values(18, 'יונה', 'חז"ל', 'book_18_yona');
insert into books_tb(id, bookname, author, book_image_url) values(19, 'מיכה', 'חז"ל', 'book_19_micha');
insert into books_tb(id, bookname, author, book_image_url) values(20, 'נחום', 'חז"ל', 'book_20_nachum');
insert into books_tb(id, bookname, author, book_image_url) values(21, 'חבקוק', 'חז"ל', 'book_21_chavakuk');
insert into books_tb(id, bookname, author, book_image_url) values(22, 'צפניה', 'חז"ל', 'book_22_tsfanya');
insert into books_tb(id, bookname, author, book_image_url) values(23, 'חגי', 'חז"ל', 'book_23_chagai');
insert into books_tb(id, bookname, author, book_image_url) values(24, 'זכריה', 'חז"ל', 'book_24_zecharya');
insert into books_tb(id, bookname, author, book_image_url) values(25, 'מלאכי', 'דוד המלך', 'book_25_malaachi');
insert into books_tb(id, bookname, author, book_image_url) values(26, 'תהלים', 'דוד המלך', 'book_26_tehilim');
insert into books_tb(id, bookname, author, book_image_url) values(27, 'משלי', 'שלמה המלך', 'book_27_mishle');
insert into books_tb(id, bookname, author, book_image_url) values(28, 'שיר השירים', 'שלמה המלך', 'book_28_shir_hashirim');
insert into books_tb(id, bookname, author, book_image_url) values(29, 'קהלת', 'שלמה המלך', 'book_29_kohelet');
insert into books_tb(id, bookname, author, book_image_url) values(30, 'רות', 'חז"ל', 'book_30_rut');
insert into books_tb(id, bookname, author, book_image_url) values(31, 'איכה', 'חז"ל', 'book_31_echa');
insert into books_tb(id, bookname, author, book_image_url) values(32, 'אסתר', 'מרדכי', 'book_32_ester');
insert into books_tb(id, bookname, author, book_image_url) values(33, 'עזרא', 'חז"ל', 'book_33_ezra');
insert into books_tb(id, bookname, author, book_image_url) values(34, 'נחמיה', 'חז"ל', 'book_34_venechemya');
insert into books_tb(id, bookname, author, book_image_url) values(35, 'דברי הימים', 'חז"ל', 'book_35_divere_hayamim');


insert into books_tb(bookname, author, book_image_url) values('בראשית', 'משה רבנו', 'book_1_bereshit');
insert into books_tb(bookname, author, book_image_url) values('שמות', 'משה רבנו', 'book_2_shmot');
insert into books_tb(bookname, author, book_image_url) values('ויקרא', 'משה רבנו', 'book_3_vayikra');
insert into books_tb(bookname, author, book_image_url) values('במדבר', 'משה רבנו', 'book_4_bamidbar');
insert into books_tb(bookname, author, book_image_url) values('דברים', 'משה רבנו', 'book_5_dvarim');
insert into books_tb(bookname, author, book_image_url) values('יהושע', 'יהושוע בן נון', 'book_6_yehoshua');
insert into books_tb(bookname, author, book_image_url) values('שופטים', 'שמואל הנביא', 'book_7_shoftim');
insert into books_tb(bookname, author, book_image_url) values('שמואל', 'שמואל הנביא', 'book_8_shmuel');
insert into books_tb(bookname, author, book_image_url) values('מלכים', 'שמואל הנביא', 'book_9_melachim');
insert into books_tb(bookname, author, book_image_url) values('ישעיהו', 'ישעיהו הנביא', 'book_10_yeshaayahu');
insert into books_tb(bookname, author, book_image_url) values('ירמיהו', 'ירמיהו הנביא', 'book_11_yermiyahu');
insert into books_tb(bookname, author, book_image_url) values('יחזקאל', 'יחזקאל הנביא', 'book_12_yehezkel');
insert into books_tb(bookname, author, book_image_url) values('דניאל', 'חז"ל', 'book_13_daniel');
insert into books_tb(bookname, author, book_image_url) values('הושע', 'חז"ל', 'book_14_hoshea');
insert into books_tb(bookname, author, book_image_url) values('יואל', 'חז"ל', 'book_15_yoel');
insert into books_tb(bookname, author, book_image_url) values('עמוס', 'חז"ל', 'book_16_amos');
insert into books_tb(bookname, author, book_image_url) values('עובדיה', 'חז"ל', 'book_17_ovadya');
insert into books_tb(bookname, author, book_image_url) values('יונה', 'חז"ל', 'book_18_yona');
insert into books_tb(bookname, author, book_image_url) values('מיכה', 'חז"ל', 'book_19_micha');
insert into books_tb(bookname, author, book_image_url) values('נחום', 'חז"ל', 'book_20_nachum');
insert into books_tb(bookname, author, book_image_url) values('חבקוק', 'חז"ל', 'book_21_chavakuk');
insert into books_tb(bookname, author, book_image_url) values('צפניה', 'חז"ל', 'book_22_tsfanya');
insert into books_tb(bookname, author, book_image_url) values('חגי', 'חז"ל', 'book_23_chagai');
insert into books_tb(bookname, author, book_image_url) values('זכריה', 'חז"ל', 'book_24_zecharya');
insert into books_tb(bookname, author, book_image_url) values('מלאכי', 'דוד המלך', 'book_25_malaachi');
insert into books_tb(bookname, author, book_image_url) values('תהלים', 'דוד המלך', 'book_26_tehilim');
insert into books_tb(bookname, author, book_image_url) values('משלי', 'שלמה המלך', 'book_27_mishle');
insert into books_tb(bookname, author, book_image_url) values('שיר השירים', 'שלמה המלך', 'book_28_shir_hashirim');
insert into books_tb(bookname, author, book_image_url) values('קהלת', 'שלמה המלך', 'book_29_kohelet');
insert into books_tb(bookname, author, book_image_url) values('רות', 'חז"ל', 'book_30_rut');
insert into books_tb(bookname, author, book_image_url) values('איכה', 'חז"ל', 'book_31_echa');
insert into books_tb(bookname, author, book_image_url) values('אסתר', 'מרדכי', 'book_32_ester');
insert into books_tb(bookname, author, book_image_url) values('עזרא', 'חז"ל', 'book_33_ezra');
insert into books_tb(bookname, author, book_image_url) values('נחמיה', 'חז"ל', 'book_34_venechemya');
insert into books_tb(bookname, author, book_image_url) values('דברי הימים', 'חז"ל', 'book_35_divere_hayamim');



insert into books_tb(bookname, author, book_image_url) values('בראשית', 'משה רבנו', 'book_1_bereshit');
insert into books_tb(bookname, author, book_image_url) values('שמות', 'משה רבנו', 'book_2_shmot');
insert into books_tb(bookname, author, book_image_url) values('ויקרא', 'משה רבנו', 'book_3_vayikra');
insert into books_tb(bookname, author, book_image_url) values('במדבר', 'משה רבנו', 'book_4_bamidbar');
insert into books_tb(bookname, author, book_image_url) values('דברים', 'משה רבנו', 'book_5_dvarim');
insert into books_tb(bookname, author, book_image_url) values('יהושע', 'יהושוע בן נון', 'book_6_yehoshua');
insert into books_tb(bookname, author, book_image_url) values('שופטים', 'שמואל הנביא', 'book_7_shoftim');
insert into books_tb(bookname, author, book_image_url) values('שמואל', 'שמואל הנביא', 'book_8_shmuel');
insert into books_tb(bookname, author, book_image_url) values('מלכים', 'שמואל הנביא', 'book_9_melachim');
insert into books_tb(bookname, author, book_image_url) values('ישעיהו', 'ישעיהו הנביא', 'book_10_yeshaayahu');
insert into books_tb(bookname, author, book_image_url) values('ירמיהו', 'ירמיהו הנביא', 'book_11_yermiyahu');
insert into books_tb(bookname, author, book_image_url) values('יחזקאל', 'יחזקאל הנביא', 'book_12_yehezkel');
insert into books_tb(bookname, author, book_image_url) values('דניאל', 'חז"ל', 'book_13_daniel');
insert into books_tb(bookname, author, book_image_url) values('הושע', 'חז"ל', 'book_14_hoshea');
insert into books_tb(bookname, author, book_image_url) values('יואל', 'חז"ל', 'book_15_yoel');
insert into books_tb(bookname, author, book_image_url) values('עמוס', 'חז"ל', 'book_16_amos');
insert into books_tb(bookname, author, book_image_url) values('עובדיה', 'חז"ל', 'book_17_ovadya');
insert into books_tb(bookname, author, book_image_url) values('יונה', 'חז"ל', 'book_18_yona');
insert into books_tb(bookname, author, book_image_url) values('מיכה', 'חז"ל', 'book_19_micha');
insert into books_tb(bookname, author, book_image_url) values('נחום', 'חז"ל', 'book_20_nachum');
insert into books_tb(bookname, author, book_image_url) values('חבקוק', 'חז"ל', 'book_21_chavakuk');
insert into books_tb(bookname, author, book_image_url) values('צפניה', 'חז"ל', 'book_22_tsfanya');
insert into books_tb(bookname, author, book_image_url) values('חגי', 'חז"ל', 'book_23_chagai');
insert into books_tb(bookname, author, book_image_url) values('זכריה', 'חז"ל', 'book_24_zecharya');
insert into books_tb(bookname, author, book_image_url) values('מלאכי', 'דוד המלך', 'book_25_malaachi');
insert into books_tb(bookname, author, book_image_url) values('תהלים', 'דוד המלך', 'book_26_tehilim');
insert into books_tb(bookname, author, book_image_url) values('משלי', 'שלמה המלך', 'book_27_mishle');
insert into books_tb(bookname, author, book_image_url) values('שיר השירים', 'שלמה המלך', 'book_28_shir_hashirim');
insert into books_tb(bookname, author, book_image_url) values('קהלת', 'שלמה המלך', 'book_29_kohelet');
insert into books_tb(bookname, author, book_image_url) values('רות', 'חז"ל', 'book_30_rut');
insert into books_tb(bookname, author, book_image_url) values('איכה', 'חז"ל', 'book_31_echa');
insert into books_tb(bookname, author, book_image_url) values('אסתר', 'מרדכי', 'book_32_ester');
insert into books_tb(bookname, author, book_image_url) values('עזרא', 'חז"ל', 'book_33_ezra');
insert into books_tb(bookname, author, book_image_url) values('נחמיה', 'חז"ל', 'book_34_venechemya');
insert into books_tb(bookname, author, book_image_url) values('דברי הימים', 'חז"ל', 'book_35_divere_hayamim');



insert into books_tb(bookname, author, book_image_url) values('בראשית', 'משה רבנו', 'book_1_bereshit');
insert into books_tb(bookname, author, book_image_url) values('שמות', 'משה רבנו', 'book_2_shmot');
insert into books_tb(bookname, author, book_image_url) values('ויקרא', 'משה רבנו', 'book_3_vayikra');
insert into books_tb(bookname, author, book_image_url) values('במדבר', 'משה רבנו', 'book_4_bamidbar');
insert into books_tb(bookname, author, book_image_url) values('דברים', 'משה רבנו', 'book_5_dvarim');
insert into books_tb(bookname, author, book_image_url) values('יהושע', 'יהושוע בן נון', 'book_6_yehoshua');
insert into books_tb(bookname, author, book_image_url) values('שופטים', 'שמואל הנביא', 'book_7_shoftim');
insert into books_tb(bookname, author, book_image_url) values('שמואל', 'שמואל הנביא', 'book_8_shmuel');
insert into books_tb(bookname, author, book_image_url) values('מלכים', 'שמואל הנביא', 'book_9_melachim');
insert into books_tb(bookname, author, book_image_url) values('ישעיהו', 'ישעיהו הנביא', 'book_10_yeshaayahu');
insert into books_tb(bookname, author, book_image_url) values('ירמיהו', 'ירמיהו הנביא', 'book_11_yermiyahu');
insert into books_tb(bookname, author, book_image_url) values('יחזקאל', 'יחזקאל הנביא', 'book_12_yehezkel');
insert into books_tb(bookname, author, book_image_url) values('דניאל', 'חז"ל', 'book_13_daniel');
insert into books_tb(bookname, author, book_image_url) values('הושע', 'חז"ל', 'book_14_hoshea');
insert into books_tb(bookname, author, book_image_url) values('יואל', 'חז"ל', 'book_15_yoel');
insert into books_tb(bookname, author, book_image_url) values('עמוס', 'חז"ל', 'book_16_amos');
insert into books_tb(bookname, author, book_image_url) values('עובדיה', 'חז"ל', 'book_17_ovadya');
insert into books_tb(bookname, author, book_image_url) values('יונה', 'חז"ל', 'book_18_yona');
insert into books_tb(bookname, author, book_image_url) values('מיכה', 'חז"ל', 'book_19_micha');
insert into books_tb(bookname, author, book_image_url) values('נחום', 'חז"ל', 'book_20_nachum');
insert into books_tb(bookname, author, book_image_url) values('חבקוק', 'חז"ל', 'book_21_chavakuk');
insert into books_tb(bookname, author, book_image_url) values('צפניה', 'חז"ל', 'book_22_tsfanya');
insert into books_tb(bookname, author, book_image_url) values('חגי', 'חז"ל', 'book_23_chagai');
insert into books_tb(bookname, author, book_image_url) values('זכריה', 'חז"ל', 'book_24_zecharya');
insert into books_tb(bookname, author, book_image_url) values('מלאכי', 'דוד המלך', 'book_25_malaachi');
insert into books_tb(bookname, author, book_image_url) values('תהלים', 'דוד המלך', 'book_26_tehilim');
insert into books_tb(bookname, author, book_image_url) values('משלי', 'שלמה המלך', 'book_27_mishle');
insert into books_tb(bookname, author, book_image_url) values('שיר השירים', 'שלמה המלך', 'book_28_shir_hashirim');
insert into books_tb(bookname, author, book_image_url) values('קהלת', 'שלמה המלך', 'book_29_kohelet');
insert into books_tb(bookname, author, book_image_url) values('רות', 'חז"ל', 'book_30_rut');
insert into books_tb(bookname, author, book_image_url) values('איכה', 'חז"ל', 'book_31_echa');
insert into books_tb(bookname, author, book_image_url) values('אסתר', 'מרדכי', 'book_32_ester');
insert into books_tb(bookname, author, book_image_url) values('עזרא', 'חז"ל', 'book_33_ezra');
insert into books_tb(bookname, author, book_image_url) values('נחמיה', 'חז"ל', 'book_34_venechemya');
insert into books_tb(bookname, author, book_image_url) values('דברי הימים', 'חז"ל', 'book_35_divere_hayamim');



insert into books_tb(bookname, author, book_image_url) values('בראשית', 'משה רבנו', 'book_1_bereshit');
insert into books_tb(bookname, author, book_image_url) values('שמות', 'משה רבנו', 'book_2_shmot');
insert into books_tb(bookname, author, book_image_url) values('ויקרא', 'משה רבנו', 'book_3_vayikra');
insert into books_tb(bookname, author, book_image_url) values('במדבר', 'משה רבנו', 'book_4_bamidbar');
insert into books_tb(bookname, author, book_image_url) values('דברים', 'משה רבנו', 'book_5_dvarim');
insert into books_tb(bookname, author, book_image_url) values('יהושע', 'יהושוע בן נון', 'book_6_yehoshua');
insert into books_tb(bookname, author, book_image_url) values('שופטים', 'שמואל הנביא', 'book_7_shoftim');
insert into books_tb(bookname, author, book_image_url) values('שמואל', 'שמואל הנביא', 'book_8_shmuel');
insert into books_tb(bookname, author, book_image_url) values('מלכים', 'שמואל הנביא', 'book_9_melachim');
insert into books_tb(bookname, author, book_image_url) values('ישעיהו', 'ישעיהו הנביא', 'book_10_yeshaayahu');
insert into books_tb(bookname, author, book_image_url) values('ירמיהו', 'ירמיהו הנביא', 'book_11_yermiyahu');
insert into books_tb(bookname, author, book_image_url) values('יחזקאל', 'יחזקאל הנביא', 'book_12_yehezkel');
insert into books_tb(bookname, author, book_image_url) values('דניאל', 'חז"ל', 'book_13_daniel');
insert into books_tb(bookname, author, book_image_url) values('הושע', 'חז"ל', 'book_14_hoshea');
insert into books_tb(bookname, author, book_image_url) values('יואל', 'חז"ל', 'book_15_yoel');
insert into books_tb(bookname, author, book_image_url) values('עמוס', 'חז"ל', 'book_16_amos');
insert into books_tb(bookname, author, book_image_url) values('עובדיה', 'חז"ל', 'book_17_ovadya');
insert into books_tb(bookname, author, book_image_url) values('יונה', 'חז"ל', 'book_18_yona');
insert into books_tb(bookname, author, book_image_url) values('מיכה', 'חז"ל', 'book_19_micha');
insert into books_tb(bookname, author, book_image_url) values('נחום', 'חז"ל', 'book_20_nachum');
insert into books_tb(bookname, author, book_image_url) values('חבקוק', 'חז"ל', 'book_21_chavakuk');
insert into books_tb(bookname, author, book_image_url) values('צפניה', 'חז"ל', 'book_22_tsfanya');
insert into books_tb(bookname, author, book_image_url) values('חגי', 'חז"ל', 'book_23_chagai');
insert into books_tb(bookname, author, book_image_url) values('זכריה', 'חז"ל', 'book_24_zecharya');
insert into books_tb(bookname, author, book_image_url) values('מלאכי', 'דוד המלך', 'book_25_malaachi');
insert into books_tb(bookname, author, book_image_url) values('תהלים', 'דוד המלך', 'book_26_tehilim');
insert into books_tb(bookname, author, book_image_url) values('משלי', 'שלמה המלך', 'book_27_mishle');
insert into books_tb(bookname, author, book_image_url) values('שיר השירים', 'שלמה המלך', 'book_28_shir_hashirim');
insert into books_tb(bookname, author, book_image_url) values('קהלת', 'שלמה המלך', 'book_29_kohelet');
insert into books_tb(bookname, author, book_image_url) values('רות', 'חז"ל', 'book_30_rut');
insert into books_tb(bookname, author, book_image_url) values('איכה', 'חז"ל', 'book_31_echa');
insert into books_tb(bookname, author, book_image_url) values('אסתר', 'מרדכי', 'book_32_ester');
insert into books_tb(bookname, author, book_image_url) values('עזרא', 'חז"ל', 'book_33_ezra');
insert into books_tb(bookname, author, book_image_url) values('נחמיה', 'חז"ל', 'book_34_venechemya');
insert into books_tb(bookname, author, book_image_url) values('דברי הימים', 'חז"ל', 'book_35_divere_hayamim');



insert into books_tb(bookname, author, book_image_url) values('בראשית', 'משה רבנו', 'book_1_bereshit');
insert into books_tb(bookname, author, book_image_url) values('שמות', 'משה רבנו', 'book_2_shmot');
insert into books_tb(bookname, author, book_image_url) values('ויקרא', 'משה רבנו', 'book_3_vayikra');
insert into books_tb(bookname, author, book_image_url) values('במדבר', 'משה רבנו', 'book_4_bamidbar');
insert into books_tb(bookname, author, book_image_url) values('דברים', 'משה רבנו', 'book_5_dvarim');
insert into books_tb(bookname, author, book_image_url) values('יהושע', 'יהושוע בן נון', 'book_6_yehoshua');
insert into books_tb(bookname, author, book_image_url) values('שופטים', 'שמואל הנביא', 'book_7_shoftim');
insert into books_tb(bookname, author, book_image_url) values('שמואל', 'שמואל הנביא', 'book_8_shmuel');
insert into books_tb(bookname, author, book_image_url) values('מלכים', 'שמואל הנביא', 'book_9_melachim');
insert into books_tb(bookname, author, book_image_url) values('ישעיהו', 'ישעיהו הנביא', 'book_10_yeshaayahu');
insert into books_tb(bookname, author, book_image_url) values('ירמיהו', 'ירמיהו הנביא', 'book_11_yermiyahu');
insert into books_tb(bookname, author, book_image_url) values('יחזקאל', 'יחזקאל הנביא', 'book_12_yehezkel');
insert into books_tb(bookname, author, book_image_url) values('דניאל', 'חז"ל', 'book_13_daniel');
insert into books_tb(bookname, author, book_image_url) values('הושע', 'חז"ל', 'book_14_hoshea');
insert into books_tb(bookname, author, book_image_url) values('יואל', 'חז"ל', 'book_15_yoel');
insert into books_tb(bookname, author, book_image_url) values('עמוס', 'חז"ל', 'book_16_amos');
insert into books_tb(bookname, author, book_image_url) values('עובדיה', 'חז"ל', 'book_17_ovadya');
insert into books_tb(bookname, author, book_image_url) values('יונה', 'חז"ל', 'book_18_yona');
insert into books_tb(bookname, author, book_image_url) values('מיכה', 'חז"ל', 'book_19_micha');
insert into books_tb(bookname, author, book_image_url) values('נחום', 'חז"ל', 'book_20_nachum');
insert into books_tb(bookname, author, book_image_url) values('חבקוק', 'חז"ל', 'book_21_chavakuk');
insert into books_tb(bookname, author, book_image_url) values('צפניה', 'חז"ל', 'book_22_tsfanya');
insert into books_tb(bookname, author, book_image_url) values('חגי', 'חז"ל', 'book_23_chagai');
insert into books_tb(bookname, author, book_image_url) values('זכריה', 'חז"ל', 'book_24_zecharya');
insert into books_tb(bookname, author, book_image_url) values('מלאכי', 'דוד המלך', 'book_25_malaachi');
insert into books_tb(bookname, author, book_image_url) values('תהלים', 'דוד המלך', 'book_26_tehilim');
insert into books_tb(bookname, author, book_image_url) values('משלי', 'שלמה המלך', 'book_27_mishle');
insert into books_tb(bookname, author, book_image_url) values('שיר השירים', 'שלמה המלך', 'book_28_shir_hashirim');
insert into books_tb(bookname, author, book_image_url) values('קהלת', 'שלמה המלך', 'book_29_kohelet');
insert into books_tb(bookname, author, book_image_url) values('רות', 'חז"ל', 'book_30_rut');
insert into books_tb(bookname, author, book_image_url) values('איכה', 'חז"ל', 'book_31_echa');
insert into books_tb(bookname, author, book_image_url) values('אסתר', 'מרדכי', 'book_32_ester');
insert into books_tb(bookname, author, book_image_url) values('עזרא', 'חז"ל', 'book_33_ezra');
insert into books_tb(bookname, author, book_image_url) values('נחמיה', 'חז"ל', 'book_34_venechemya');
insert into books_tb(bookname, author, book_image_url) values('דברי הימים', 'חז"ל', 'book_35_divere_hayamim');



insert into books_tb(bookname, author, book_image_url) values('בראשית', 'משה רבנו', 'book_1_bereshit');
insert into books_tb(bookname, author, book_image_url) values('שמות', 'משה רבנו', 'book_2_shmot');
insert into books_tb(bookname, author, book_image_url) values('ויקרא', 'משה רבנו', 'book_3_vayikra');
insert into books_tb(bookname, author, book_image_url) values('במדבר', 'משה רבנו', 'book_4_bamidbar');
insert into books_tb(bookname, author, book_image_url) values('דברים', 'משה רבנו', 'book_5_dvarim');
insert into books_tb(bookname, author, book_image_url) values('יהושע', 'יהושוע בן נון', 'book_6_yehoshua');
insert into books_tb(bookname, author, book_image_url) values('שופטים', 'שמואל הנביא', 'book_7_shoftim');
insert into books_tb(bookname, author, book_image_url) values('שמואל', 'שמואל הנביא', 'book_8_shmuel');
insert into books_tb(bookname, author, book_image_url) values('מלכים', 'שמואל הנביא', 'book_9_melachim');
insert into books_tb(bookname, author, book_image_url) values('ישעיהו', 'ישעיהו הנביא', 'book_10_yeshaayahu');
insert into books_tb(bookname, author, book_image_url) values('ירמיהו', 'ירמיהו הנביא', 'book_11_yermiyahu');
insert into books_tb(bookname, author, book_image_url) values('יחזקאל', 'יחזקאל הנביא', 'book_12_yehezkel');
insert into books_tb(bookname, author, book_image_url) values('דניאל', 'חז"ל', 'book_13_daniel');
insert into books_tb(bookname, author, book_image_url) values('הושע', 'חז"ל', 'book_14_hoshea');
insert into books_tb(bookname, author, book_image_url) values('יואל', 'חז"ל', 'book_15_yoel');
insert into books_tb(bookname, author, book_image_url) values('עמוס', 'חז"ל', 'book_16_amos');
insert into books_tb(bookname, author, book_image_url) values('עובדיה', 'חז"ל', 'book_17_ovadya');
insert into books_tb(bookname, author, book_image_url) values('יונה', 'חז"ל', 'book_18_yona');
insert into books_tb(bookname, author, book_image_url) values('מיכה', 'חז"ל', 'book_19_micha');
insert into books_tb(bookname, author, book_image_url) values('נחום', 'חז"ל', 'book_20_nachum');
insert into books_tb(bookname, author, book_image_url) values('חבקוק', 'חז"ל', 'book_21_chavakuk');
insert into books_tb(bookname, author, book_image_url) values('צפניה', 'חז"ל', 'book_22_tsfanya');
insert into books_tb(bookname, author, book_image_url) values('חגי', 'חז"ל', 'book_23_chagai');
insert into books_tb(bookname, author, book_image_url) values('זכריה', 'חז"ל', 'book_24_zecharya');
insert into books_tb(bookname, author, book_image_url) values('מלאכי', 'דוד המלך', 'book_25_malaachi');
insert into books_tb(bookname, author, book_image_url) values('תהלים', 'דוד המלך', 'book_26_tehilim');
insert into books_tb(bookname, author, book_image_url) values('משלי', 'שלמה המלך', 'book_27_mishle');
insert into books_tb(bookname, author, book_image_url) values('שיר השירים', 'שלמה המלך', 'book_28_shir_hashirim');
insert into books_tb(bookname, author, book_image_url) values('קהלת', 'שלמה המלך', 'book_29_kohelet');
insert into books_tb(bookname, author, book_image_url) values('רות', 'חז"ל', 'book_30_rut');
insert into books_tb(bookname, author, book_image_url) values('איכה', 'חז"ל', 'book_31_echa');
insert into books_tb(bookname, author, book_image_url) values('אסתר', 'מרדכי', 'book_32_ester');
insert into books_tb(bookname, author, book_image_url) values('עזרא', 'חז"ל', 'book_33_ezra');


