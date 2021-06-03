create table if not exists optional_courses
(
  id    	  	int 			auto_increment primary key  comment '自增主键',
  give_courses_id int(11)		unique not null comment '授课id',
  create_time 	bigint			not null comment '创建时间',
  update_time 	bigint			not null comment '更新时间'
)engine=innoDB default charset=utf8 comment='可选课程信息表';