create database day12;
use day12;
create table product(
	id int primary key auto_increment,
	pname varchar(20),
	price double,
	pdesc varchar(20)
);

insert into product values (null,'电视机',3200,'液晶曲面大电视');
insert into product values (null,'韭菜盒子',3,'味重请小心食用');
insert into product values (null,'益达',10,'韭菜伴侣');
insert into product values (null,'十三香',12,'守义牌');