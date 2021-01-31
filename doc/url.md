# 请求接口文档

## 测试启动是否成功

```
http://127.0.0.1:9091/home/other
```

## 注册用户登录

`http://127.0.0.1:9091/user/v1/login`

```
{
    "username": "admin321",
    "password": "123456"
}
```

## 增加学生信息

`http://127.0.0.1:9091/v1/student/insertSelective`

```
{
    "address": "月球",
    "name": "牛洪龙",
    "age": 38,
    "id": 2
}
```

## 删除学生信息

`http://127.0.0.1:9091/v1/student/delete`

```
{
    "id": 2
}
```

## 增加用户信息

`http://127.0.0.1:9091/v1/user/insert`

```
{
    "name": "王宇"
}
```

## 查询城市列表

```
http://127.0.0.1:9091/city/v1/findAll
```

## 查询地铁信息

`http://127.0.0.1:9091/heart/tenant/select/subway/information`

```
{
	"stationName": "肖家河"
}
```

## 管理员创建新用户

`http://127.0.0.1:9091/api/manage/createNewUser`

```
{
    "phone": "",
    "password": "",
    "username": " ",
    "sex": "0",
    "weChat": "",
    "qq": " ",
    "operator": "",
    "token": ""
}
```

## 更新技术文章

`http://127.0.0.1:9091/v1/technologyArticle/updateById`

```
{
    "id": 396,
    "title": "我的世界里"
}
```

## 查询个人工作经历

```
http://127.0.0.1:9091/abo/exp/findAll
```