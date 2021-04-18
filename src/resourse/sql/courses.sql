create table if not exists courses
(
  id    	  	int 			auto_increment primary key  comment '自增主键',
  name 		  	varchar(32)		not null unique comment '课程名称',
  type		  	varchar(20)		not null comment '课程性质',
  hour  	  	int(11)			not null comment '学时',
  theory_hour 	int(11)			not null comment '理论课学时',
  experiment_hour int(11) 		not null comment '实验课学时',
  credit	  	int(11) 		not null comment '学分',
  semester	  	int(11) 		not null comment '开课学期',
  description 	varchar(255)	null default '无' comment '课程简介',
  create_time 	bigint			not null comment '创建时间',
  update_time 	bigint			not null comment '更新时间'
)engine=innoDB default charset=utf8 comment='课程信息表';