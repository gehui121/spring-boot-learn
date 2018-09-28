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
