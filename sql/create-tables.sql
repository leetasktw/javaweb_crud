CREATE DATABASE 'demo';
USE demo;

create table members (
   id  int(3) NOT NULL AUTO_INCREMENT,
   name varchar(120) NOT NULL,
   password varchar(120) NOT NULL,
   email varchar(220),
   PRIMARY KEY (id)
);

create table users (
	id  int(3) NOT NULL AUTO_INCREMENT,
	name varchar(120) NOT NULL,
	email varchar(220),
	country varchar(120),
	PRIMARY KEY (id)
);

