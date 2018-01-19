create database day13;
use day13;
create table account(
	name varchar(20),
	money int
);

insert into account values('aa','1000');
insert into account values('bb','1000');

update account set money=10000;
