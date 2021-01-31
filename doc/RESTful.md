# API接口设计标准及规范
> RESTful

HTTP方法

通过标准HTTP方法对资源CRUD：

GET：查询

```
GET /zoos
GET /zoos/1
GET /zoos/1/employees
```

POST：创建单个资源。POST一般向“资源集合”型uri发起

```
POST /animals  //新增动物
POST /zoos/1/employees //为id为1的动物园雇佣员工
```

PUT：更新单个资源（全量），客户端提供完整的更新后的资源。与之对应的是 PATCH，PATCH 负责部分更新，客户端提供要更新的那些字段。PUT/PATCH一般向“单个资源”型uri发起

```
PUT /animals/1
PUT /zoos/1
```

DELETE：删除

```
DELETE /zoos/1/employees/2
DELETE /zoos/1/employees/2;4;5
DELETE /zoos/1/animals  //删除id为1的动物园内的所有动物
```