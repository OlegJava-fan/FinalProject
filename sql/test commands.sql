INSERT INTO certificate VALUES(2,4,4,4,4,4,4,4,4,4,4,40);
INSERT INTO account (
id, login,password,firstName,lastName,middleName,email,city,region,
certificate_id,roles_id,studyForm_id,faculties_name
) values (2,'Юзер2','81DC9BDB52D04DC20036DBD8313ED055','Юрий','Гагарин','Алексеевич','poehali@mail.com','Москва','Московская',2,2,1,'matriculate');
START TRANSACTION;
USE `AddCom`;
INSERT INTO `AddCom`.`order` (`id`, `account_id`, `status_id`, `faculties_id`) VALUES (1, 1, 1, 1);


COMMIT;
DELETE FROM account WHERE id =3;
SELECT * FROM account;
UPDATE `addcom`.`account` SET `login` = 'Юзер' WHERE (`id` = '3');
 INSERT INTO faculties (name ,passingScoreFreeForm, passingScorePayForm, allPlaces
 , freeFormPlaces,payFormPlaces)VALUES ('FacultetBiology', 40, 30, 100, 20, 80);
 SELECT * FROM faculties;
 SELECT * FROM studyForm;
delete from `order` WHERE id>2;
  DELETE FROM certificate WHERE id =5;
  DELETE FROM account WHERE id >2;
SELECT * FROM account;
SELECT * FROM certificate;
SELECT * FROM roles;
SELECT * FROM `order` where account_id = 2;
SELECT * FROM `order`;
SELECT * FROM status;
DELETE  FROM `order` where account_id=2;
SELECT * FROM act where account_id =2;
SELECT * FROM act;
delete from act where account_id  =2;
SELECT * FROM `order` WHERE account_id =3 AND faculties_id =7;

show open tables where in_use>0;
show processlist;


SHOW ENGINE INNODB STATUS;
SHOW PROCESSLIST;
kill 74;
kill 75;
kill 76;
kill 80;
use addcom;