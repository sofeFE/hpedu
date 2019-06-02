静态资源 目录优先级 :meta-inf/resources >  resources > static > public

springboot提供了两种自定义静态资源路径的方法(此处为映射到磁盘路径)：

第一种为继承WebMvcConfigurerAdapter类并重写addResourceHandlers 方法
	addResourceLocations方法中参数, 前面加入的路径 优先级要高于 后面路径

第二种配置方法为通过配置文件，static-location的顺序影响优先级别
spring.mvc.static-path-pattern:/roadpath/** 
spring-;locations:file:d:/roadpath/
(注意不能写成/** ,否则 默认路径 将被覆盖. 
如果要写成/**, 需要将默认 四个路径 添加到 locations 上. 
)




实体入参验证: jsr 303验证 


TODO 2019年5月16日02:06:26  提交答题后,将简答题回答 发送至数据库,等待老师打分.

















