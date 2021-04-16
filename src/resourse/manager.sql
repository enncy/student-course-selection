create table if not exists manager
(
  uid         varchar(32)  unique  null	comment '唯一id',
  id    	  int auto_increment primary key unique comment '自增主键',
  name        varchar(20) default '管理员' not null comment '管理员名字',
  account     varchar(32)   unique  not null comment '管理员账号',
  pwd         varchar(32)   not null comment '密码',
  create_time bigint   not null comment '创建时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员';