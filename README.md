# spring-boot-learn
学习spring-boot
第九章：我们将静态资源放到/static目录下，可以有多个层级，
SpringBoot默认给我们配置了静态资源的地址转发，我们只需要将静态文件放到/resources/static目录下，就可以直接访问了。
但是这样往往会暴露给用户我们的项目结构，针对这一点我们需要修改静态资源的路径，配置SpringBoot修改内置SpringMVC静态资源路径，
映射/resources/**相当于/resources/static
