# student-course-selection
scs - student-course-selection 学生选课系统   java + jdbc + swing

#  学生选课系统 

`课程信息`包括：课程编号,课程名称,课程性质,学时,理论课学时,实验课学时,学分,开课学期。

`学生选课信息`包括：学号,所选课程编号。

学生选课系统的功能要求：

管理员: 

（1） `专业信息`管理

（2） `班级信息`管理（班级属于某个专业）

（3） `学生信息`管理（学生属于某个班级）

（4） `教师信息`录入

（5） `可选课程信息`录入（含课程编号,学分,与授课教师信息绑定,最多可上课人数）； 

（6） 修改课程信息；

（7） 查找已知课程编号的课程信息；

（8） 查找已知课程名称的课程信息；

（9） 设置`选课开始结束时间`,并发布`选课公告`

（10） 设置`选课规则`：每个学生最多选 3 门,`3 可设置`

（11） 查看选课结果（看每门课已有多少人选课 ,并显示 已选人数/最大选课人数 的值）

（12） 按课程编号浏览选了该课的学生信息

学生：

（13） 登录

（14） 查看选课公告,获知选课开始结束时间

（15） 查看可选课程信息（课程简介,教师简介）

（16） 选课

（17） 自动选课：在选课开始结束期间方能使用的功能,当课程名额有空缺时,最早对该课程使用自动选课功能的学生获得名额。一个学生最多能同时对 3 门课程使用自动选课。当学生选够 3 门课程（选课规则中的设置）时,其设置的自动选课功能失效。

（18） 撤销选课（选课未结束前可撤销,最多可撤销 5 次） 

（19） 查看`已选课程`

注意：课程信息和学生选课信息的关联性。

注意：系统自动对各种数据合理性的检查



## 数据库设计

### 管理员表

 ```mysql
create table if not exists managers
(
  id    	  	int 			auto_increment primary key  comment '自增主键',
  name        	varchar(20)   	not null comment '管理员名字',
  account     	varchar(32)   	not null unique comment '管理员账号',
  pwd         	varchar(32)   	not null comment '密码',
  create_time 	bigint   		not null comment '创建时间',
  update_time 	bigint			not null comment '更新时间'
)engine=innoDB default charset=utf8 comment='管理员表';
 ```

### 学生表

```mysql
create table if not exists students
(
  id    	  	int 			auto_increment primary key  comment '自增主键',
  name        	varchar(20)   	not null comment '学生名字',
  number	  	varchar(32)		not null unique comment '学号',
  pwd         	varchar(32)   	not null comment '学生登录密码',
  class_id    	int(11)		not null comment '班级id',
  create_time 	bigint   		not null comment '创建时间',
  update_time 	bigint			not null comment '更新时间'
)engine=innoDB default charset=utf8 comment='学生表';
```

### 教师表

```mysql
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
```

### 专业信息表

```mysql
create table if not exists majors
(
  id    	  	int 			auto_increment primary key  comment '自增主键',
  name 		  	varchar(32)	 	not null unique  comment '专业名称',
  description 	varchar(255)	null default '无' comment '专业简介',
  create_time 	bigint		 	not null comment '创建时间',
  update_time 	bigint			not null comment '更新时间'
)engine=innoDB default charset=utf8 comment='专业信息表';
```

### 课程信息表

```mysql
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
```

### 班级信息表

```mysql
create table if not exists classes
(
  id    	  	int 			auto_increment primary key  comment '自增主键', 
  name		  	varchar(32)		not null unique comment	'班级名称',
  major_id	  	int(11) 		not null comment '专业id',
  create_time 	bigint			not null comment '创建时间',
  update_time 	bigint			not null comment '更新时间'
)engine=innoDB default charset=utf8 comment='班级信息表';
```

### 可选课程信息表

```mysql
create table if not exists optional_courses
(
  id    	  	int 			auto_increment primary key  comment '自增主键',
  course_id  	int(11) 		not null comment '课程id',
  teacher_id  	int(11)			not null comment '教师id',
  max_num		int(11)			not null comment '最大选课人数',
  create_time 	bigint			not null comment '创建时间',
  update_time 	bigint			not null comment '更新时间'
)engine=innoDB default charset=utf8 comment='可选课程信息表';
```

### 系统配置表

```mysql
create table if not exists settings
(
  id    	  	int 			auto_increment primary key  comment '自增主键',
  `key`			varchar(255)	primary key not null comment '键',
  `value`		varchar(255)	not null comment '值',
  create_time 	bigint			not null comment '创建时间',
  update_time 	bigint			not null comment '更新时间'
)engine=innoDB default charset=utf8 comment='系统配置';
```



 ### 学生选课表

```mysql
create table if not exists courses_selections
(
  id    	  	int 			auto_increment primary key  comment '自增主键', 
  student_id  	int(11)  		not null comment '学生id',
  course_id  	int(11)			not null comment '课程id',
  create_time 	bigint			not null comment '创建时间',
  update_time 	bigint			not null comment '更新时间'
)engine=innoDB default charset=utf8 comment='选课表';
```

### 自动选课表

```mysql
create table if not exists auto_courses_selections
(
  id    	  	int 			auto_increment primary key  comment '自增主键', 
  student_id  	int(11)  		not null comment '学生id',
  course_id  	int(11)			not null comment '课程id',
  create_time 	bigint			not null comment '创建时间',
  update_time 	bigint			not null comment '更新时间'
)engine=innoDB default charset=utf8 comment='自动选课表';
```

### 选课撤销表

```mysql
create table if not exists cancel_selections
(
  id    	  	int 			auto_increment primary key  comment '自增主键', 
  selection_id	int(11)			not null comment '选课操作id',
  create_time 	bigint			not null comment '创建时间',
  update_time 	bigint			not null comment '更新时间'
)engine=innoDB default charset=utf8 comment='选课撤销表';
```



## 界面设计

![image-20210421182003737](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20210421182003737.png)

