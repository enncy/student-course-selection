create table if not exists courses_selections
(
  id    	  	int 			auto_increment primary key  comment '自增主键',
  student_id  	int(11)  		not null comment '学生id',
  give_courses_id int(11)		not null comment '授课id',
  create_time 	bigint			not null comment '创建时间',
  update_time 	bigint			not null comment '更新时间',
  unique index sc(student_id,give_courses_id)
)engine=innoDB default charset=utf8 comment='选课表';