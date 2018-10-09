# spring-boot-learn
学习spring-boot
第九章：我们将静态资源放到/static目录下，可以有多个层级，
SpringBoot默认给我们配置了静态资源的地址转发，我们只需要将静态文件放到/resources/static目录下，就可以直接访问了。
但是这样往往会暴露给用户我们的项目结构，针对这一点我们需要修改静态资源的路径，配置SpringBoot修改内置SpringMVC静态资源路径，
映射/resources/**相当于/resources/static

第十章：
	@Controller注解和@restController注解的区别，
		@Controller注解需要配合模板使用，跳转页面，即返回的值是模板的地址值，（路径jsp，html等）
		@restController 原来返回json需要@ResponseBody和@Controller配合。即@RestController是@ResponseBody和@Controller的组合注解。
本章主要讲解了SpringBoot如何创建Servlet以及使用两种方式装配到项目中，一种是手动装配的方式、而另外一种是自动装配。
	手动装配的方式：没有成功
	自动装配：可以匹配多个servlet，创建SpringBoot配置类，在配置类内添加@ServletComponentScan和@Configuration两个注解，在servlet类中添加@WebServlet(urlPatterns = "/test2")注解及路径映射
		
第十一章：
	遇到的问题时页面传入的多文件在controller层接收不到
	
第十二章：
	学习SpringBoot项目中使用LogBack记录日志到控制台和文件之中，根据不同的级别输出不同形式日志信息。
	
第十三章：
	SpringBoot实战SpringDataJPA
	
第十四章：本章主要讲解了SpringBoot项目下SpringDataJPA如果整合QueryDSL完成查询操作，单独QueryDSL查询如何完成查询，简单封装了查询对象Inquirer。
	建表语句
		CREATE TABLE good_type(
			tt_id INT PRIMARY KEY AUTO_INCREMENT,
			tt_name VARCHAR(20),
			tt_is_show INT,
			tt_order INT
			);

		CREATE TABLE good_info(
			t_id INT PRIMARY KEY AUTO_INCREMENT,
			t_title VARCHAR(20),
			t_price DOUBLE(10,2),
			t_unit VARCHAR(20),
			t_order INT(11),
			t_type_id INT,
			CONSTRAINT FOREIGN KEY fk_emp(t_type_id) REFERENCES good_type(tt_id)
		);

第十五章：使用SpringBoot validator对参数进行校验，
	自定义验证需要我们提供两个文件内容，一个是注解、另外一个是对应注解继承ConstraintValidator的实现类	
第十六章：
	Redis数据库内有很多个命令下面我简单介绍几个大家在日常开发中常用到的。
	flushdb：清空当前数据库。
	select [index]：选择索引数据库，index为索引值名，如：select 1。
	del [key]：删除一条指定key的值。
	keys *：查看数据库内所有的key。
	flushall：清空所有数据库。
	quit：退出客户端连接。
第十七章：SpringSecurity是专门针对基于Spring项目的安全框架，充分利用了依赖注入和AOP来实现安全管控。
	CREATE TABLE login_user(
	u_id INT PRIMARY KEY AUTO_INCREMENT,
	u_username VARCHAR(20) NOT NULL,
	u_password VARCHAR(20) NOT NULL
	);
	CREATE TABLE roles(
	r_id INT PRIMARY KEY AUTO_INCREMENT,
	r_name VARCHAR(20) NOT NULL
	);
	CREATE TABLE user_roles(
	ur_id INT PRIMARY KEY AUTO_INCREMENT,
	ur_user_id INT,
	ur_role_id INT,
	FOREIGN KEY(ur_user_id) REFERENCES login_user(u_id),
	FOREIGN KEY(ur_role_id) REFERENCES roles(r_id)
	);
	UserEntity实体类中的变量须与重写的UserDetails中的方法名保持一致，最好使用username
	
第十八章：测试没有通过，
第二十二章：SpringBoot项目多模块运用与设计，测试通过
第二十三章：测试通过。
	将服务部署到tomcat中需要修改server将服务部署到tomcat中需要修改server.xml文件，
	在HOST标签下添加<Context path="项目名（可以不写）" docBase="D:\JAVA\Develop\Tomcat\apache-tomcat-8.5.34\webapps\pager项目解压后的路径" debug="0" reloadable="true" />
	访问时需要在url路径下写项目名，
	也可以在pom.xml文件中 添加打包名称
		<build>
			<finalName>pager</finalName>
		</build> 
第二十四章：项目报错		
第二十五章：基于SpringBooot项目搭建可以站外Ajax请求访问的跨域资源服务器。测试通过
	index.xml放置在 resources下了，file:///C:/Users/Administrator/Desktop/index.html测试时放置在项目外，
	
CREATE DATABASE books;
USE books;
CREATE TABLE t_user(
	t_id INT NOT NULL PRIMARY KEY,
	t_name VARCHAR(20),
	t_age INT,
	t_adress VARCHAR(100),
	t_pwd VARCHAR(100)
);
CREATE TABLE book(
	b_id INT NOT NULL PRIMARY KEY,
	b_name VARCHAR(20)
);
第二十六章：基于SpringBoot架构完成注解@Scheduled的定时任务配置。测试通过
	cron属性
这是一个时间表达式，可以通过简单的配置就能完成各种时间的配置，我们通过CRON表达式几乎可以完成任意的时间搭配，它包含了六或七个域：

Seconds : 可出现", - * /"四个字符，有效范围为0-59的整数
Minutes : 可出现", - * /"四个字符，有效范围为0-59的整数
Hours : 可出现", - * /"四个字符，有效范围为0-23的整数
DayofMonth : 可出现", - * / ? L W C"八个字符，有效范围为0-31的整数
Month : 可出现", - * /"四个字符，有效范围为1-12的整数或JAN-DEc
DayofWeek : 可出现", - * / ? L C #"四个字符，有效范围为1-7的整数或SUN-SAT两个范围。1表示星期天，2表示星期一， 依次类推
Year : 可出现", - * /"四个字符，有效范围为1970-2099年

下面简单举几个例子：

"0 0 12 * * ?"    每天中午十二点触发
"0 15 10 ? * *"    每天早上10：15触发
"0 15 10 * * ?"    每天早上10：15触发
"0 15 10 * * ? *"    每天早上10：15触发
"0 15 10 * * ? 2005"    2005年的每天早上10：15触发
"0 * 14 * * ?"    每天从下午2点开始到2点59分每分钟一次触发
"0 0/5 14 * * ?"    每天从下午2点开始到2：55分结束每5分钟一次触发
"0 0/5 14,18 * * ?"    每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发
"0 0-5 14 * * ?"    每天14:00至14:05每分钟一次触发
"0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发
"0 15 10 ? * MON-FRI"    每个周一、周二、周三、周四、周五的10：15触发

作者：恒宇少年
链接：https://www.jianshu.com/p/c7492aeb35a1
來源：简书
简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
	