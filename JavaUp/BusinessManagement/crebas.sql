/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/1/20 星期日 13:06:40                       */
/*==============================================================*/

/*=========day01=========*/
drop table if exists DEPT_P;

drop table if exists USER_P;

/*==============================================================*/
/* Table: DEPT_P                                                */
/*==============================================================*/
create table DEPT_P
(
   DEPT_ID              varchar(50) not null comment '编号',
   DPET_NAME            varchar(32) comment '部门名称',
   PARENT_ID            varchar(50),
   STATE                char(1) comment '状态 1启用0停用默认为1',
   primary key (DEPT_ID)
);

/*==============================================================*/
/* Table: USER_P                                                */
/*==============================================================*/
create table USER_P
(
   USER_ID              varchar(50) not null,
   DEPT_ID              varchar(50) comment '编号',
   USERNAME             varchar(48),
   PASSWORD             varchar(100),
   primary key (USER_ID)
);

alter table USER_P add constraint FK_Reference_1 foreign key (DEPT_ID)
      references DEPT_P (DEPT_ID) on delete restrict on update restrict;

