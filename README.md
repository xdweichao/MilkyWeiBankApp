# MilkyWeiBankApp
Revature: Project 0, MilkyWei Bank Application; Java; TDD JUnit; RDMS DBeaver


--SQL Database Code--
DROP TABLE USERS;
DROP TABLE BANKS;
DROP TABLE ACCOUNTS;


CREATE TABLE USERS (
Username VARCHAR (25) PRIMARY key not NULL,
User_PW VARCHAR (100) not null
);

CREATE TABLE BANKS (
Bank_ID SERIAL PRIMARY KEY,
Bank_Name VARCHAR(50) not null,
Bank_Type VARCHAR(10) not null,
balance decimal(10,2) DEFAULT '0.00'
);

CREATE TABLE ACCOUNTS(
FK_USERS_Username VARCHAR REFERENCES USERS(Username) not NULL,
FK_BANKS_Bank_ID  INTEGER REFERENCES BANKS(Bank_ID),
CONSTRAINT Comp_Key_Accounts primary key(FK_USERS_Username,FK_BANKS_Bank_ID)
);

insert into USERS (Username, User_PW) values 
('Andy','AndyPassword'),
('Bella', 'BellaPassword'),
('Carrie', 'CarriePassword'),
('Diana', 'DianaPassword')
;

insert into BANKS (Bank_Name, Bank_Type, balance) values 
('AndysSingleBank','savings', '0.00'),
('BellaAndCarrieSavingsAccount','savings', '100.00'),
('CarriePersonalBank','checking', '100.00')
;

insert into ACCOUNTS(FK_USERS_Username, FK_BANKS_Bank_ID) values 
('Andy', '1'),
('Bella', '2'),
('Carrie','2'),
('Carrie', '3')
;
