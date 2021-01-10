# ABBOTT-ADMIN 后台管理系统

>Author: Abbott.liu

>Date: 2020/12/30

## 项目简介

一个基于 Spring Boot 2.1.0 、 Spring Boot Jpa、 JWT、Spring Security、Redis、Vue的前后端分离的后台管理系统

**账号密码：** `admin321 / 123456`

## 主要特性
- 使用最新技术栈，社区资源丰富。
- 支持数据字典，可方便地对一些状态进行管理

## 系统功能
- 用户管理：提供用户的相关配置，新增用户后，默认密码为123456
- 角色管理：对权限与菜单进行分配，可根据部门设置角色的数据权限

## 项目结构

项目采用按功能分模块的开发方式，结构如下

- `abbott-common` 为系统的公共模块，各种工具类，公共配置存在该模块

- `abbott-system` 为系统核心模块也是项目入口模块，也是最终需要打包部署的模块

- `abbott-generator` 为系统的代码生成模块，代码生成的模板在 system 模块中

## 详细结构

```
- abbott-common 公共模块
    - annotation 为系统自定义注解
    - aspect 自定义注解的切面
    - base 提供了Entity、DTO基类和mapstruct的通用mapper
    - config 自定义权限实现、redis配置、swagger配置、Rsa配置等
    - exception 项目统一异常的处理
    - utils 系统通用工具类
- abbott-system 系统核心模块（系统启动入口）
	- config 配置跨域与静态资源，与数据权限
	    - thread 线程池相关
	- modules 系统相关模块(登录授权、系统监控、定时任务、运维管理等)
- abbott-generator 系统代码生成模块
```

## 相关指令

### 打包
```
mvn clean package
```

### 上传JAR包到服务器
```
scp /Users/Eminem/Desktop/heart/fight_chess_java/target/fight_chess_202004262141.jar root@39.105.168.44:/usr/package
```

### 后端启动JAR包
```
nohup java -jar JavaWebSocket-1.0-SNAPSHOT.jar > websocket_log.file 2>&1 &
```

### 停止JAR包
```
netstat -lnp|grep 9091
kill -9 2995
```

# 接口日志

### 测试启动是否成功

```
http://127.0.0.1:9091/home/other
```

### 查询城市列表

```
http://127.0.0.1:9091/city/v1/findAll
```

### 查询个人工作经历

```
http://127.0.0.1:9091/abo/exp/findAll
```
