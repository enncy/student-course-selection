create table if not exists majors
(
  id    	  	int 			auto_increment primary key  comment '自增主键',
  name 		  	varchar(32)	 	not null unique  comment '专业名称',
  description 	varchar(255)	null default '无' comment '专业简介',
  create_time 	bigint		 	not null comment '创建时间',
  update_time 	bigint			not null comment '更新时间'
)engine=innoDB default charset=utf8 comment='专业信息表';