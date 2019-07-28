# Spring MVC项目基础框架
> 作者: 赖焕民

## app.properties配置
```$xslt
jdbc.maxWait=15000
jdbc.testOnReturn=true
jdbc.testOnBorrow=true
jdbc.TestWhileIdle=true
jdbc.validationQuery=select 1
jdbc.driverClass=com.mysql.jdbc.Driver
jdbc.jdbcUrl=jdbc:mysql://127.0.0.1:3306/dbtest
jdbc.user=root
jdbc.password=12345
```

## 开发环境  
1, 进入com.laihuanmin.project.entry.ServerEntry.java   
2, 运行main方法即可   

## 部署环境
1, mvn package -DSkipTests=true   
2, 把war包放在Jetty或者Tomcat指定目录下

## 注意事项
1, 为了避免隐私泄露，我在.gitignore里配置忽略了app.properties，如有需要请修改。    
2, 如果需要初始化database，请在数据库执行resources下的init.sql文件

