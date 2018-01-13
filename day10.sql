use day09;

select * from user;

insert into user(username,password,email,name,sex,birthday,hobby) values("xiao","123","xiao@163.com","xiaoming","男","2018-01-13",null);
insert into user(username,password,email,name,sex,birthday,hobby) values("xiaom","111","xiaom@123.com","女","2018-01-30",null);
insert into user(username,password,email,name,sex,birthday,hobby) values("xiaom","111","xiaom@123.com","xiaoming","男","2018-01-22",null);
insert into user(username,password,email,name,sex,birthday,hobby) values (?,?,?,?,?,?,?)