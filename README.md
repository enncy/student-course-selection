# student-course-selection
scs - student-course-selection 学生选课系统   java + jdbc + swing



## 界面设计

![image-20210421182003737](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20210421182003737.png)

## 核心流程图

MVC 架构

![img.png](E:\github\enncy\student-course-selection\src\resource\img.png)



## 以下是程序设计报告内容

**目录**

学生选课系统	

摘要	

第 一 章 绪 论	

1.1 基本要求	

1.2 开发环境	

1.3 本报告的主要内容	

第二章 需求分析	

2.1 系统需求简介	

第三章 总体设计	

3.1 设计概述	

3.2 系统总体结构及功能模块划分	

3.2.1 业务层	

3.2.2 视图层	

3.2.3 控制层	

3.3 系统数据库概念结构设计	

3.4 界面设计	

3.5 安全保密设计	

第四章 详细设计	

4.1 概述	

4.2 系统程序流程图	

4.3 系统主要功能模块简介	

4.3.1 项目结构	

4.3.2 项目结构解析	

4.3.3 数据表初始化系统

4.3.4 Mybatis数据业务层系统	

4.3.5 Controller 控制器系统	

第五章 主要功能模块代码	

5.1 数据表初始化系统	

5.2 MVC 架构实现	

5.3 登录界面代码设计	

5.4 管理员首页界面代码设计	

5.5 专业信息管理	

5.6 班级信息管理	

5.7 学生信息管理

5.8 教师信息管理	

5.9 课程信息管理	

5.10 授课信息管理	

5.11 可选课程信息管理	

5.12 选课信息管理	

5.13 学生选课逻辑设计	

5.14 通用增删改查代码设计	

第六章 课程设计心得	

参考文献	

5 篇，含 3 篇期刊 2 篇图书





**学生选课系统**

# **摘要**

本文描述的是基于Java Swing[4] 的学生选课系统，主要编程思想有， java 封装继承多态[1], java 设计模式[2]， java 反射[3]，还有MYSQL数据库[5] 的增删改查功能的实现。主要功能模块包括：课程信息包括：课程编号，课程名称，课程性质，学时，理论课学时，实验课学时，学分，开课学期。学生选课信息包括：学 号，所选课程编号。学生选课系统的功能要求： 

***管理员:***

（1） 专业信息管理 

（2） 班级信息管理（班级属于某个专业） 

（3） 学生信息管理（学生属于某个班级） 

（4） 教师信息录入 

（5） 可选课程信息录入（含课程编号，学分，与授课教师信息绑定，最多可上课人数）； 

（6） 修改课程信息； 

（7） 查找已知课程编号的课程信息； 

（8） 查找已知课程名称的课程信息； 

（9） 设置选课开始结束时间，并发布选课公告 

（10） 设置选课规则：每个学生最多选 3 门，3 可设置 

（11） 查看选课结果（看每门课已有多少人选课 ，并显示 已选人数/最大选课人数 的值） 

（12） 按课程编号浏览选了该课的学生信息 

***学生：*** 

（13） 登录 

（14） 查看选课公告，获知选课开始结束时间 

（15） 查看可选课程信息（课程简介，教师简介） 

（16） 选课 

（17） 自动选课：在选课开始结束期间方能使用的功能，当课程名额有空缺时，最早对该课程使用自动选课功能的学生获得名额。 一个学生最多能同时对 3 门课程使用自动选课。当学生选够 3 门课程（选课规则中的设置）时，其设置的自动选课功能失效。 

（18） 撤销选课（选课未结束前可撤销，最多可撤销 5 次） 

（19） 查看已选课程 

  本篇报告介绍一个学生选课系统的从分析到设计最后到开发的全过程为，给出了学生选课系统的设计和技术实现的过程，特别在细节上分析功能和函数的实现思想。涉及到学生选课系统管理的基本功能在本报告中都有相应的描述。 



 

## ***第 一 章*** ***绪 论***

 

### **1.1** ***基本要求***

所有题目都需要 GUI 实现界面，如果需要存储数据的，可选择 mysql 或 sqlite(文本数据库，不需要 安装，推荐)。对于输入的数据，要求做到一次录入，重复使用，如题 2，录入好部门 A 信息后，添加部门 A 的 员工时，不需要再手动输入部门 A，而是使用下拉框形式选择。

 

### **1.2** ***开发环境***

***数据库***: ***Mysql8.0 ,***  关系型数据库,MySQL是一种关系型数据库管理系统，关系数据库将数据保存在不同的表中，而不是将所有数据放在一个大仓库内，这样就增加了速度并提高了灵活性。

***开发工具***: ***IDEA 2020*** , IDEA 全称 IntelliJ IDEA，是java编程语言开发的集成环境。IntelliJ在业界被公认为最好的java开发工具，尤其在智能代码助手、代码自动提示、重构、JavaEE支持、各类版本工具([git](https://baike.baidu.com/item/git/12647237)、[svn](https://baike.baidu.com/item/svn/3311103)等)、JUnit、CVS整合、代码分析、 创新的GUI设计等方面的功能可以说是超常的。

***开发语言: Java 。***Java是一门[面向对象](https://baike.baidu.com/item/面向对象)编程语言，不仅吸收了[C++](https://baike.baidu.com/item/C++)语言的各种优点，还摒弃了C++里难以理解的[多继承](https://baike.baidu.com/item/多继承)、[指针](https://baike.baidu.com/item/指针/2878304)等概念，因此Java语言具有功能强大和简单易用两个特征。Java语言作为静态面向对象编程语言的代表，极好地实现了面向对象理论，允许程序员以优雅的思维方式进行复杂的编程 。

***包管理: Maven 。*** Maven项目对象模型(POM)，可以通过一小段描述信息来管理项目的构建，报告和[文档](https://baike.baidu.com/item/文档/1009768)的[项目管理工具](https://baike.baidu.com/item/项目管理工具/6854630)软件。

***框架:*** ***BeautyEye*** ***。*** BeautyEye 是一款 Java Swing 跨平台外观（look and feel）实现。得益于 Android 的 GUI 基础技术，BeautyEye 的实现完全不同于其它外观。

 

 

### **1.3** ***本报告的主要内容***

 

本报告详细的介绍了学生信息管理系统的开发过程，主要涉及到的工作如下：系统的需求分析、系统的总体设计、系统的概念设计、系统各模块的详细设计、系统运行与测试。 

 



 

## ***第二章*** **需求分析**

 

### **2.1** ***系统需求简介***

 

#### 2.1.1 系统目标

（1） 根据SQL查询实现对账号，专业，班级，学生，教师，课程，可选课程，选课等信息的查询

（2） 账号，专业，班级，学生，教师，课程，可选课程，选课，等信息的增加、删除、修改 

 (3） 对基本信息完成增加、删除、修改时，需注意表与表之间的关联 

 

#### 2.1.2 功能需求分析 

（1） 信息查询：每个实体信息都会对应到数据库中的相应字段，而每个实体都有相应的ID，也就是唯一标识码与之对应，可以使用 ID对每个实体进行相应的查询。 

 2） 信息管理：根据ID对信息更新、插入、删除； 

（3） 信息录入：读取输入的实体信息，并生成对应的随机ID，使用数据检验系统，进行对数据的检验，最后插入到数据库中。

 

#### 2.1.3 性能需求分析

（4） 登录、用户界面需求：简洁、易懂、易用、友好的用户界面。 

（5） 安全保密性需求：只有凭借用户名和密码登陆系统，才能进行信息的管理等。 

 

##  **第三章** **总体设计**

 

### **3.1** ***设计概述***

根据需求把整个系统分化成不同的模块，每个模块完成一个特定的子功能。把这些模块结合起来组成一个整体。逐一实现各个功能；

### **3.2** ***系统总体结构及功能模块划分***

系统总体结构，我将划分为三层： 业务层，视图层，控制层，也就是经典的 MVC 架构模式。

 

![img](E:\github\enncy\student-course-selection\img\README\wps1.jpg) 

#### **3.2.1** ***业务层***

业务层也就是MVC 架构模式中的 M - model 业务模型。业务层我将分为2个部分，第一个部分是 数据层，第二个部分是业务层。 数据层的功能就是对数据的控制，增删改查，业务层就是对数据层的进一步封装，并且进行业务逻辑的处理。

#### **3.2.2** ***视图层***

视图层也就是 MVC 架构模式中的 V- view 用户界面。 视图层将采用 Java Swing GUI 框架 + BeautyEye 样式美化，进行视图层的设计。

#### **3.2.3** ***控制层***

控制层也就是 MVC 架构模式中的 C - controller 控制器。 控制层将采用一个自定义的JWT组件，此组件可以通过结合 业务层和视图层，进行业务层和视图层的代码分离。

 

### **3.3** ***系统数据库概念结构设计***

 根据对数据项与数据结构的分析，设计出能够满足系统需求的各种实体，及它们之间的关系，为后面的逻辑结构设计打下基础。

（一）管理员表

![img](E:\github\enncy\student-course-selection\img\README\wps2.jpg) 

（二）学生表

![img](E:\github\enncy\student-course-selection\img\README\wps3.jpg) 

（三）教师表

![img](E:\github\enncy\student-course-selection\img\README\wps4.jpg) 

（四）专业信息表

![img](E:\github\enncy\student-course-selection\img\README\wps5.jpg) 

（五）课程信息表

![img](E:\github\enncy\student-course-selection\img\README\wps6.jpg) 

（六）班级信息表

![img](E:\github\enncy\student-course-selection\img\README\wps7.jpg) 

（七）可选课程信息表

![img](E:\github\enncy\student-course-selection\img\README\wps8.jpg) 

（八）系统配置表

![img](E:\github\enncy\student-course-selection\img\README\wps9.jpg) 

（九）学生选课表

![img](E:\github\enncy\student-course-selection\img\README\wps10.jpg) 

（十）自动选课表

![img](E:\github\enncy\student-course-selection\img\README\wps11.jpg) 

（十一）选课撤销表

 

![img](E:\github\enncy\student-course-selection\img\README\wps12.jpg)	

 

 

 

 

 

### **3.4** ***界面设计***

![img](E:\github\enncy\student-course-selection\img\README\wps13.jpg) 

### **3.5** ***安全保密设计***

#### 3.5.1 用户登录安全性

系统设计了登录界面，每个合法用户有用户名及一个密码，只有当用户输入正确的用户名及密码组合后才能够对学生信息进行操作。 

 



## **第四章** **详细设计**

 

### **4.1** ***概述***

 

详细设计阶段的根本目标是确定应该怎样具体的实现所要求的系统，也就是说，经过这个阶段的设计工作，应该得出目标系统的精确描述，从而在编码阶段可以把这个描述直接翻译成用某种程序设计语言书写的程序。 

### **4.2** ***系统程序流程图***

  程序流程图又称为程序框图，它是历史悠久使用最广泛的描述软件设计的方法。它可将整个程序的总体流程清楚明白的显示出来。如图 4.2.1 系统总流程图结构。 

![img](E:\github\enncy\student-course-selection\img\README\wps14.png) 

 

 

图 4.2.1 系统总体流程图



 

### **4.3** ***系统主要功能模块简介***

#### **4.3.1** ***项目结构***

![img](E:\github\enncy\student-course-selection\img\README\wps15.jpg) 

 

 

 

#### **4.3.2** ***项目结构解析***





 |-- cn

  |  |-- enncy			***cn.enncy 包***

  |    |-- io		***I/O 工具类包***

  |    |-- mybatis	***手写实现的模拟 Mybatis 框架包***

  |    |-- reflect		***java 反射工具类包***

  |    |-- scanner	***java 包扫描器***

  |    |-- scs		***学生管理系统核心源码***

  |      |-- Application.java		***Main 类***

  |      |-- exception			***异常包***

  |      |-- factory			***工厂模式包***

  |      |-- mapper			***数据层包***

  |      |-- pojo				***java pojo 对象包***

  |      |-- service			***业务层包***

  |      |-- swing				***swing 组件包***

  |      |  |-- component			***通用组件***

  |      |  |  |-- dialog				***弹窗***

  |      |  |  |-- frame				***通用窗体***

  |      |  |  |-- panel				***容器***

  |      |  |  |-- scroll				***滚动组件***

  |      |  |  |-- table				***表格组件***

  |      |  |  |-- title				***自定义标题栏窗体***

  |      |  |-- constant			***常量***

  |      |  |-- frame				***窗体***

  |      |  |  |-- base			

  |      |  |    |-- view		***视图***

  |      |  |      |-- index		***首页***

  |      |  |        |-- card				***卡片布局组件***

  |      |  |          |-- component			***通用组件***

  |      |  |          |  |-- dialog				***自定义弹窗***

  |      |  |          |-- courses			***课程卡片***

  |      |  |          |-- information			***信息卡片***

  |      |  |          |-- personal			***个人信息卡片***

  |      |  |          |-- statistics			***统计卡片***

  |      |  |-- utils		***组件工具类***

  |      |-- utils		***工具类***

  |-- resource		***资源文件***

​    |-- icon			***图标***

​    |-- sql			***mysql 脚本文件***

 

 

 

 

#### **4.3.3** ***数据表初始化******系统***

使用 Java 提供的***类反射原理*** + ***注解*** + ***Java 包扫描 ，***来构建一个初始化系统数据表系统。使用 Java 包扫描，扫描本项目的指定包下的所有 java 类，并将扫描到的类，加载到一个 Class[] 数组中，并返回。 使用 java 反射原理， 通过包扫描获取扫描到的类，进行反射，获取类上的 ***@Mapper*** 注解，获取指定的 name 属性 ， 然后到资源文件夹 sql 中，读取到指定name的 sql 文件, 并且执行 sql 文件中的建表文件，如果表已经存在，那么将不会创建。

 

#### **4.3.4** ***Mybatis数据业务层系统***

​	

使用 Java 提供的***类反射原理*** + ***注解 + 动态代理的设计模式 ，*** 来构建一个Mybatis数据层系统， 首先通过一个 ***ServiceComponentFactory*** 业务组件工厂， 来静态初始化所需的业务组件， 然后在业务组件初始化的时候， 通过 ***SqlSession*** 进行对组件的 ***动态代理，*** 当业务组件中执行业务的时候， 读取业务方法上的 ***@SQL*** 注解，执行注解的 sql 语句，并进行***解析和执行***，最后对返回数据进行***封装***，封装后***返回***最终的数据。

 

#### **4.3.5** ***C******ontroller 控制器系统***

 

使用 Java ***封装，继承，多态***的特点。来构建一个控制器系统，首先通过 ***ServiceComponentFactory*** 业务组件工厂，进行组件的初始化后，返回一个抽象类的 Swing 组件 ***ServiceComponent*** ， 这个组件有 初始化组件，设置组件，更新组件，设置数据，更新数据，获取数据，获取业务，等等抽象方法， 通过各种与界面交互，和与数据交互的方法，来实现 界面与数据库的交互，并解决了代码的耦合。子类可以通过拓展 ***ServiceComponent  ，*** 来进行业务组件的拓展。

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

## **第五章** **主要功能模块代码**

 

### **5.1** ***数据表初始化系统***

**（一）*****扫描 cn.enncy.scs.mapper 包***

 

![img](E:\github\enncy\student-course-selection\img\README\wps16.jpg) 

 

**（二）*****通过包扫描获取的类，进行注解分析***

 

![img](E:\github\enncy\student-course-selection\img\README\wps17.jpg) 

 

**（三）*****通过注解获取相应的 sql 文件名，并获取文件内容***

![img](E:\github\enncy\student-course-selection\img\README\wps18.jpg) 

 

**（四）** ***读取内容，建表***

![img](E:\github\enncy\student-course-selection\img\README\wps19.jpg) 

 

![img](E:\github\enncy\student-course-selection\img\README\wps20.jpg) 

 

 

### **5.2** ***M******VC 架构实现***

![img](E:\github\enncy\student-course-selection\img\README\wps21.jpg) 

 

（一）使用***S******erviceComponentFactory******.java 组件******静态工厂***模式，通过静态代码块执行 ***initServiceComponent***() 方法， 判断组件是否在 Map 集合中存在，如果存在，则获取并返回组件，如果不存在，***则通过反射获取组件的构造器，并且传入相应的业务参数，最后实例化组件，并且保存到 Map 集合中***。![img](E:\github\enncy\student-course-selection\img\README\wps22.jpg)

![img](E:\github\enncy\student-course-selection\img\README\wps23.jpg) 

 

（二） 当业务组件在 ***initServiceComponent******()*** 方法中被实例化的时候，会被注入一个 ***BaseService***参数，而这个参数会从 ***ServiceFactory.java*** 静态业务工厂中实例化并获取。 当业务实例化的时候，会自动被 ***Sqlsession.getMapper(BaseMapper) 方法中被动态代理，***

![img](E:\github\enncy\student-course-selection\img\README\wps24.jpg) 

![img](E:\github\enncy\student-course-selection\img\README\wps25.jpg) 

 

 

 

 

 

 

 

 

 

 

 

 

（三） 当子类调用 ***BaseService***中的方法的时候， ***动态代理***会读取并执行方法上的 ***@SQL*** 注解，并执行相应的 sql 语句，通过反射获取方法上的***返回值，***确定返回值的类型后，***通过一系列的方法将 sql 执行结果转换成 返回值的类属性***， 最后通过反射获取到返回值的构造器，通过构造器实例化，***并将实例化结果 赋值到 动态代理的返回值***

![img](E:\github\enncy\student-course-selection\img\README\wps26.jpg) 

![img](E:\github\enncy\student-course-selection\img\README\wps27.jpg) 

 

（四）最后， ***BaseService***会在初始化的时候，通过***S******erviceComponentFactory******工厂，***获取相应的业务组件。然后将代理返回的结果，返回给***MainFrame***窗体中的***ServiceComponent*** 子组件，并且通过每个子组件的 updatexxx 方法，通知子组件进行视图的更新。

![img](E:\github\enncy\student-course-selection\img\README\wps28.jpg) 

### **5.3** ***登录界面代码设计***

![img](E:\github\enncy\student-course-selection\img\README\wps29.jpg) 

![img](E:\github\enncy\student-course-selection\img\README\wps30.jpg) 

![img](E:\github\enncy\student-course-selection\img\README\wps31.jpg) 

### **5.4** ***管理员首页界面代码设计***

![img](E:\github\enncy\student-course-selection\img\README\wps32.jpg) 

 

 

![img](E:\github\enncy\student-course-selection\img\README\wps33.jpg) 

![img](E:\github\enncy\student-course-selection\img\README\wps34.jpg) 

### **5.5** ***专业信息管理***

![img](E:\github\enncy\student-course-selection\img\README\wps35.jpg) 

![img](E:\github\enncy\student-course-selection\img\README\wps36.jpg) 

### **5.6** ***班级信息管理***

![img](E:\github\enncy\student-course-selection\img\README\wps37.jpg) 

![img](E:\github\enncy\student-course-selection\img\README\wps38.jpg) 

### **5.7** ***学生信息管理***

 

 

![img](E:\github\enncy\student-course-selection\img\README\wps39.jpg) 

![img](E:\github\enncy\student-course-selection\img\README\wps40.jpg) 

 

![img](E:\github\enncy\student-course-selection\img\README\wps41.jpg) 



 ![img](E:\github\enncy\student-course-selection\img\README\wps42.jpg)



 

### **5.8** ***教师信息管理***

![img](E:\github\enncy\student-course-selection\img\README\wps43.jpg) 

![img](E:\github\enncy\student-course-selection\img\README\wps44.jpg) 

### **5.9** ***课程信息管理***

![img](E:\github\enncy\student-course-selection\img\README\wps45.jpg) 

![img](E:\github\enncy\student-course-selection\img\README\wps46.jpg) 

 

### **5.10** ***授课信息管理***

![img](E:\github\enncy\student-course-selection\img\README\wps47.jpg) 

![img](E:\github\enncy\student-course-selection\img\README\wps48.jpg) 

 

### **5.11** ***可选课程信息管理***

![img](E:\github\enncy\student-course-selection\img\README\wps49.jpg) 

![img](E:\github\enncy\student-course-selection\img\README\wps50.jpg) 

### **5.12** ***选课信息管理***

（一）学生界面

![img](E:\github\enncy\student-course-selection\img\README\wps51.jpg) 

 

 

 

 

 

 

 

 

 

 

 

 

（二）选课结果

![img](E:\github\enncy\student-course-selection\img\README\wps52.jpg) 

 

（三）撤销选课

![img](E:\github\enncy\student-course-selection\img\README\wps53.jpg) 

（四）选课详情信息

![img](E:\github\enncy\student-course-selection\img\README\wps54.jpg) 

### **5.13** ***学生选课辑设计***

 

学生选课逻辑的设计是本次综合实训的核心思想，也是核心代码，所以我们来说一下这个应该怎么样设计。

（一）首先分析选课逻辑

 

1. 创建专业

2. 创建班级

3. 创建老师

4. 创建课程

5. 创建学生

6. 将课程分派给指定教师

7. 将课程标记为可选课程

8. 选课开始

9. 学生查看可选课程

10. 学生查看可选课程信息

11. 学生进行选课

12. 系统判断选课是否成功

13. 如果选课不成功，则开启自动选课

14. 创建学生选课信息

15. 学生查看选课结果

16. 学生查看选课结果信息

17. 选课结束

 

 

（二）代码实现

 

1. 创建专业

2. 创建班级

3. 创建老师

4. 创建课程

5. 创建学生

6. 将课程分派给指定教师

7. 将课程标记为可选课程

   以上几个代码的实现，主要是通过 BaseMapper 中的 ***@SQL 注解*** ， 在调用 insert 方法后，方法会被动态代理解析，最后执行相应的 sql 语句

   INSERT IGNORE INTO #{表名}(#{属性列的名字}) value(#{属性列的值})

   而表名，属性列的名字，属性列的值， 动态代理会根据相应的 POJO 类， 进行反射获取，

   ***例如专业类: Major.java***

   ![img](E:\github\enncy\student-course-selection\img\README\wps55.jpg) 

   专业类有 name 和 description 2个属性，而这2个属性也对应着数据库的字段名，每个 POJO 类都是如此。

   #{TABLE_NAME} 相应的也就会被解析成 : majors

   (#{KEY_ARRAY}) 相应的也就会被解析成 : (name,description)

   (#{VALUE_ARRAY}) 相应的也就会被解析成 : (“计算机科学与技术”,”计算机科学与技术专业”)

   所以，最终的解析结果是 :  

   INSERT IGNORE INTO majors(name,description) value (“计算机科学与技术”,”计算机科学与技术专业”);![img](E:\github\enncy\student-course-selection\img\README\wps56.jpg) 

   每个Service 类都是如此，所以创建专业，创建班级，创建老师，创建课程，创建学生，等等操作，都可以得代码的复用，以及解耦

8. 选课开始

   通过初始化类，我们可以在数据库中初始化选课开始时间![img](E:\github\enncy\student-course-selection\img\README\wps57.jpg) 

   如果当前是选课时间，则管理员可以设置选课公告![img](E:\github\enncy\student-course-selection\img\README\wps58.jpg) ![img](E:\github\enncy\student-course-selection\img\README\wps59.jpg) 

   当学生选课时，程序会判断当前时间是否在选课时间

   ![img](E:\github\enncy\student-course-selection\img\README\wps60.jpg) 

   学生查看可选课程通过 OptioncalCourseMapper 我们查询可以获取可选的课程，也就是可选的授课。![img](E:\github\enncy\student-course-selection\img\README\wps61.jpg) 

9. 学生查看可选课程信息

10. 学生进行选课

11. 系统判断选课是否成功

12. 如果选课不成功，则开启自动选课

13. 创建学生选课信息

    通过 SelectionService 我们可以在学生插入课程的时候，加入一些条件，例如![img](E:\github\enncy\student-course-selection\img\README\wps62.jpg) ![img](E:\github\enncy\student-course-selection\img\README\wps63.jpg) 

    选课业务中调用以上方法，系统自动判断是否在选课时间，是否开启自动选课，学生是否成功选课，以及选课成功后添加信息。![img](E:\github\enncy\student-course-selection\img\README\wps64.jpg)	 

14. 学生查看选课结果

15. 学生查看选课结果信息

16. 通过选课表进行信息的查询

17. 选课结束

### **5.14** ***通用增删改查代码设计***

***BaseMapper.java***

![img](E:\github\enncy\student-course-selection\img\README\wps65.jpg) 



***BaseService.java***

![img](E:\github\enncy\student-course-selection\img\README\wps66.jpg) 

![img](E:\github\enncy\student-course-selection\img\README\wps67.jpg) 

 

## 第六章 课程设计心得

 

本次的 java 综合实训 课程设计，让我对 java 有了一个全新的认识，从 java 的封装继承多态中，我学到了如何组织代码中的各个部分，从 java swing 中，我学会了如何管理各种组件，以及组件之间的通讯，传值，调用，依赖，并严格遵守设计模式的6大原则:

1、单一职责原则，实现类要职责单一；

2、里氏替换原则，不要破坏继承体系；

3、依赖倒置原则，要面向接口编程；

4、接口隔离原则，在设计接口的时候要精简单一；

5、迪米特原则，要降低耦合；

6、开闭原则，要对扩展开放，对修改关闭。

 

 

实现了 java 的代码复用，实现了低内聚，高耦合的设计模式。并且我对于 java 反射原理也更加进一步的理解并实现， 反射机制其实在很多语言之中都有出现，而反射机制其实简单的来说，其实就是程序在运行的过程中，可以获取到程序本身。

 

通过 java的反射机制 + 注解 + 动态代理，我自己手动模拟并实现了一个简单的 Mybatis 数据持久层框架， 

 

通过 java 的反射机制 + 注解 + 包扫描，又实现了一个依赖注入原理类似的数据库表初始化系统。

 

 

Java 的反射机制是动态程序的关键因素，由此应运而生了许许多多的优秀框架， 从我们最开始接触的Tomcat + Servlet，到现在最流行的 spring, springmvc, springboot,springcloud 等等等等。不计其数的优秀框架开创了 java 的动态技术的先河。

 

这次的综合实训让我受益匪浅，希望在以后的实训中我能学到更多！

 

 

 

 

 

**参考文献**

[1] (美)Bruce Eckel.Java编程思想[M].机械工业出版社,2007

[2] 龚炳江;文志诚;高建国.Java程序设计[M].[人民邮电出版社](https://kns.cnki.net/KNS8/Navi?DBCode=WBFD&BaseID=人民邮电出版社),2016

[3] 王昊天,于航,商贝宁.Java反射机制概述[J].电子世界,2020

[4] 张丹丹.[浅析Java swing组件窗体设计](https://kns.cnki.net/KNS8/Detail?sfield=fn&QueryID=0&CurRec=8&recid=&FileName=DNZS202021068&DbName=CJFDLAST2020&DbCode=CJFD&yx=&pr=&URLID=)[J].[电脑知识与技术](https://kns.cnki.net/KNS8/Navi?DBCode=CJFD&BaseID=DNZS),2020

[5] 兰旭辉,熊家军,邓刚.基于MySQL的应用程序设计[J].计算机工程与设计,2004

5 篇，含 3 篇期刊 2 篇图书

 