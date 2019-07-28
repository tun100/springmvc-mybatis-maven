create database dbtest;
create table dbtest.user (
  id int primary key auto_increment,
  aname varchar(20),
  apassword varchar(20),
  createtime timestamp default current_timestamp()
);
create table dbtest.system_log (
  id int primary key auto_increment,
  atype int,
  atitle varchar(20),
  acontent varchar(500),
  is_read int default 0, -- 0 is not read, 1 is already read
  createtime timestamp default current_timestamp()
);