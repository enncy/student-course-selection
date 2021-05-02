create table if not exists settings
(
  id    	  	int 			auto_increment primary key  comment '自增主键',
  name			varchar(255)	unique not null comment '键',
  value		varchar(255)	not null comment '值',
  description	varchar(255)	  comment '备注',
  create_time 	bigint			not null comment '创建时间',
  update_time 	bigint			not null comment '更新时间'
)engine=innoDB default charset=utf8 comment='系统配置';