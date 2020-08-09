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

--完成.

TODO 2019年6月19日23:41:12  视频很容易被窃取. 如何防治 ? 
-- 需要的技术性过强, 没得法儿.

todo 2019年6月20日00:26:51  使用redis记录 网站 每天的访问量.

当一个用户访问的时候，如果用户登陆过，那么我们就使用用户的id，
如果用户没有登陆过，那么我们也能够前端页面随机生成一个key用来标识用户，
当用户访问的时候，我们可以使用HSET命令，key可以选择URI与对应的日期进行拼凑，
field可以使用用户的id或者随机标识，value可以简单设置为1。

当我们要统计某一个网站某一天的访问量的时候，就可以直接使用HLEN来得到最终的结果了















