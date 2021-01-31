# 学生表

```
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `address` varchar(60) DEFAULT NULL COMMENT '地址',
  `flag_delete` int(10) DEFAULT '0' COMMENT '删除标志',
  `native_place` varchar(50) DEFAULT NULL COMMENT '籍贯',
  `folk` varchar(20) DEFAULT NULL COMMENT '民族',
  `political_status` varchar(10) DEFAULT NULL COMMENT '政治面貌',
  `telephone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `english_level` varchar(10) DEFAULT NULL COMMENT '英语水平',
  `graduate` varchar(20) DEFAULT NULL COMMENT '毕业院校',
  `major` varchar(10) DEFAULT NULL COMMENT '专业',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;
```