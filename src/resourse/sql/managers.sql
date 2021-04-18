create table if not exists managers
(
  id    	  	int 			auto_increment primary key  comment '自增主键',
  name        	varchar(20)   	not null comment '管理员名字',
  account     	varchar(32)   	not null unique comment '管理员账号',
  pwd         	varchar(32)   	not null comment '密码',
  create_time 	bigint   		not null comment '创建时间',
  update_time 	bigint			not null comment '更新时间'
)engine=innoDB default charset=utf8 comment='管理员表';