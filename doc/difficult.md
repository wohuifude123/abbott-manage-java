# 技术问题总结

<h5>1、如果想要提供两个不同端口的URL地址，需要写两个main()函数。</h5>

例如

```
http://127.0.0.1:9090/aggregation/v1?start=0&dataLength=100

http://127.0.0.1:9091/abo/exp/findAll
```

<h5>2、项目中要求动态进行数据的升序、降序排序</h5>

```
<!--根据用户名查询-->
<select id="selectByName" resultMap="BaseResultMap">
    SELECT
    <include refid="Base_Column_List" />
    FROM student WHERE name = #{name}
    ORDER BY id ${sort} limit #{start}, #{dataLength}
</select>
```

【特别注意】此处SQL的`sort`参数，一定要使用`${ }`接收，而不能使用`#{ }`。否则，不会到达预期的效果。

`${ }`不会对传入的字符串进行处理。比如：传入的是`desc`，`${ }`处理后的效果是`ORDER BY pls.event_time desc`，可以实现按照`pls.event_time`字段倒序排序的效果。

`#{ }`会对传入的字符串进行处理。比如：传入的是desc，`#{ }`处理后的效果是`ORDER BY pls.event_time 'desc'`，会当成字符串常量，达不到按照`pls.event_time`字段倒序排序的效果

当然，`${ }`可能会引发SQL注入。一般情况下，都是使用 `#{ }`的。只有这种不需要对传入的值进行转换的场景，才会使用`${ }`。

为了实现标题中的功能，恰好用到了`${ }`的 这个特性而已。