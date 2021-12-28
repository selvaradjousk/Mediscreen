create database if not exists MEDISCREENDB;

use `MEDISCREENDB`;
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
engine INNODB;