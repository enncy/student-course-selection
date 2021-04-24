create table if not exists students
(
  id    	  	int 			auto_increment primary key  comment '自增主键',
  name        	varchar(20)   	not null comment '学生名字',
  number	  	varchar(32)		not null unique comment '学号',
  pwd         	varchar(32)   	not null comment '学生登录密码',
  class_id    	int(11)		not null comment '班级id',
  create_time 	bigint   		not null comment '创建时间',
  update_time 	bigint			not null comment '更新时间'
)engine=innoDB default charset=utf8 comment='学生表';