create table if not exists classes
(
  id    	  	int 			auto_increment primary key  comment '自增主键',
  name		  	varchar(32)		not null unique comment	'班级名称',
  major_id	  	int(11) 		not null comment '专业id',
  create_time 	bigint			not null comment '创建时间',
  update_time 	bigint			not null comment '更新时间'
)engine=innoDB default charset=utf8 comment='班级信息表';