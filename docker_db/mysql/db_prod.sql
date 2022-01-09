-- DROP DATABASE IF EXISTS MEDISCREENDB

/** PRODUCTION DB **/

-- CREATE Database
CREATE DATABASE IF NOT EXISTS `MEDISCREENDB`;
	USE `MEDISCREENDB`;
    SET autocommit=1;

-- CREATE Tables
create table if not exists PATIENTS (
  ID bigint not null auto_increment,
  LAST_NAME varchar(125) not null,
  FIRST_NAME varchar(125) not null,
  DATE_OF_BIRTH varchar(10) not null,
  SEX char(1) not null,
  ADDRESS varchar(150) default null,
  PHONE_NUMBER varchar(20) default null,
  primary key (ID)
)
engine INNODB
AUTO_INCREMENT=0;

-- INSERT data
LOCK TABLES patients WRITE;
    
INSERT INTO `patients` (`address`, `date_of_birth`, `first_name`, `last_name`, `phone_number`, `sex`) VALUES ('1202 Bumble Dr', '1959-06-28', 'Piers', 'Bailey', '747-815-0557', 'M');
INSERT INTO `patients` (`address`, `date_of_birth`, `first_name`, `last_name`, `phone_number`, `sex`) VALUES ('12 Beechwood Road', '1964-06-18', 'Natalie', 'Clark', '241-467-9197', 'F') ;
INSERT INTO `patients` (`address`, `date_of_birth`, `first_name`, `last_name`, `phone_number`, `sex`) VALUES ('193 Vale St', '1945-06-24', 'Max', 'Buckland', '833-534-0864', 'M') ;
INSERT INTO `patients` (`address`, `date_of_birth`, `first_name`, `last_name`, `phone_number`, `sex`) VALUES ('12 Cobblestone St', '1966-12-31', 'Claire', 'Wilson', '300-452-1091', 'F') ;
INSERT INTO `patients` (`address`, `date_of_birth`, `first_name`, `last_name`, `phone_number`, `sex`) VALUES ('40 Sulphur Springs Dr', '1949-12-07', 'Tracey', 'Ross', '131-396-5049', 'F') ;
INSERT INTO `patients` (`address`, `date_of_birth`, `first_name`, `last_name`, `phone_number`, `sex`) VALUES ('4 Southampton Road', '1958-06-29', 'Wendy', 'Ince', '802-911-9975', 'F') ;
INSERT INTO `patients` (`address`, `date_of_birth`, `first_name`, `last_name`, `phone_number`, `sex`) VALUES ('894 Hall Street', '1946-11-26', 'Anthony', 'Sharp', '451-761-8383', 'M') ;
INSERT INTO `patients` (`address`, `date_of_birth`, `first_name`, `last_name`, `phone_number`, `sex`) VALUES ('599 East Garden Ave', '1952-11-11', 'Edward', 'Arnold', '123-727-2779', 'M') ;
INSERT INTO `patients` (`address`, `date_of_birth`, `first_name`, `last_name`, `phone_number`, `sex`) VALUES ('745 West Valley Farms Drive', '1952-09-27', 'Pippa', 'Rees', '628-423-0993', 'F') ;
INSERT INTO `patients` (`address`, `date_of_birth`, `first_name`, `last_name`, `phone_number`, `sex`) VALUES ('2 Warren Street', '2021-06-22', 'Lucas', 'Ferguson', '387-866-1399', 'M') ;

UNLOCK TABLES;