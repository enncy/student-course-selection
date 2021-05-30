create table if not exists give_courses(
    id    	  	int 			auto_increment primary key  comment '自增主键',
    course_id  	int(11) 		not null comment '课程id',
    teacher_id  	int(11)			not null comment '教师id',
    max_num		int(11)			not null comment '最大选课人数',
    create_time 	bigint			not null comment '创建时间',
    update_time 	bigint			not null comment '更新时间',
    unique index ct(course_id,teacher_id)
)engine=innoDB default charset=utf8 comment='教师授课表';