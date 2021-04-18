create table if not exists cancel_selections
(
  id    	  	int 			auto_increment primary key  comment '自增主键',
  selection_id	int(11)			not null comment '选课操作id',
  create_time 	bigint			not null comment '创建时间',
  update_time 	bigint			not null comment '更新时间'
)engine=innoDB default charset=utf8 comment='选课撤销表';