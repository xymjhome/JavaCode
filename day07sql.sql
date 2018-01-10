CREATE DATABASE day07;
USE day07;	
		
create table category(
	cid varchar(20) primary key,
	cname varchar(20)
);


insert into category values('c002','服饰');
insert into category values('c003','化妆品');
insert into category values('c004','书籍');