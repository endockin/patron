CREATE DATABASE VMFACTORY;

CREATE TABLE Users(
  userid int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) DEFAULT NULL,
  password varchar(100) DEFAULT NULL,
  firstname varchar(45) DEFAULT NULL,
  lastname varchar(45) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  type int(10) NOT NULL,
  PRIMARY KEY (userid)
);

CREATE TABLE Categories(
  categoryid int(11) NOT NULL AUTO_INCREMENT,
  categoryname varchar(45) DEFAULT NULL,
  PRIMARY KEY (categoryid)
);

CREATE TABLE Images(
  imageid int(11) NOT NULL AUTO_INCREMENT,
  categoryid int(11) NOT NULL,
  imagename varchar(45) NOT NULL,
  imagedescription varchar(100) DEFAULT NULL, 
  PRIMARY KEY (imageid),
  FOREIGN KEY (categoryid) REFERENCES Categories(categoryid)
);

alter table images add column imageLogo varchar(100) default null;