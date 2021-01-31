# 技术文章表

```
DROP TABLE IF EXISTS `technology_article`;
CREATE TABLE `technology_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `userID` int(11) NOT NULL COMMENT '用户索引',
  `content` varchar(10000) DEFAULT NULL COMMENT '求租要求',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `tags` varchar(50) DEFAULT NULL COMMENT '标签',
  `qr_code_path` varchar(100) DEFAULT NULL COMMENT '二维码路径',
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='技术文章表';
```
