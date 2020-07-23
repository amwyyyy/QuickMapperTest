## 项目介绍
> 此项目用来整合各种新奇技术，并且能和完好的结合在一起
	
## 使用技术
- spring
- spring mvc
- mybatis
- jdk8
- shiro权限管理
- redis缓存
- 支持数据库读写分离
- durid数据源管理
- logback日志
- Hibernate validation web层数据校验
- mybatis generator 自动生成代码

## 使用步骤
1. 打开GeneratorMain类，修改数据库连接。builder.addTable()中改为数据库实际的表名。运行main方法。
2. 刷新工程，这时候已经生成dao, po, mapper等文件。
3. 修改dev/base.properties，改为实际数据库连接。
4. 工程加入tomcat，启动运行。访问http://localhost:8080/QuickMapperTest/ 到控制台看效果。

## TODO
- spring零配置 (90%)
- dbunit -spring-test
- 集成lts分布式任务调度框架
- 图片保存到7牛 （后续用来保存用户头像）
- 集成环信
- 消息推送（提醒用户收到环信消息，使用长轮询或websocket）
- 邮件发送（异常报错邮件通知）
- kafka消息中间件
- solr搜索引入
