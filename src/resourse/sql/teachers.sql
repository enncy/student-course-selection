create table if not exists teachers
(
  id    	  	int 			auto_increment primary key  comment '自增主键',
  name        	varchar(20)   	not null comment '教师名字',
  number	  	varchar(32)		not null unique comment '教师号' ,
  pwd         	varchar(32)   	not null comment '教师登录密码',
  description 	varchar(255)	null default '无' comment '教师简介',
  create_time 	bigint   not null comment '创建时间',
  update_time 	bigint			not null comment '更新时间'
)engine=innoDB default charset=utf8 comment='教师表';