# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.10)
# Database: beetl_bbs
# Generation Time: 2017-02-27 05:34:55 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table bbs_module
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bbs_module`;

CREATE TABLE `bbs_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `detail` varchar(100) DEFAULT NULL,
  `turn` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `bbs_module` WRITE;
/*!40000 ALTER TABLE `bbs_module` DISABLE KEYS */;

INSERT INTO `bbs_module` (`id`, `name`, `detail`, `turn`)
VALUES
	(1,'课程','',1),
	(2,'BBS',NULL,2);

/*!40000 ALTER TABLE `bbs_module` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bbs_post
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bbs_post`;

CREATE TABLE `bbs_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `content` text NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `has_reply` bit(1) NOT NULL DEFAULT b'0',
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `topicID_P` (`topic_id`),
  KEY `userID_P` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `bbs_post` WRITE;
/*!40000 ALTER TABLE `bbs_post` DISABLE KEYS */;

INSERT INTO `bbs_post` (`id`, `topic_id`, `user_id`, `content`, `create_time`, `has_reply`, `update_time`)
VALUES
	(201,59,1,'<div style=\"margin:0px 0px 0px 15px;padding:20px 0px;font-family:Verdana, Arial, 宋体;background-color:#F9F9F9;\">\r\n	<h2 style=\"font-family:微软雅黑;font-size:14px;\">\r\n		实例\r\n	</h2>\r\n	<p>\r\n		本例演示如何通过 closest() 完成事件委托。当被最接近的列表元素或其子后代元素被点击时，会切换黄色背景：\r\n	</p>\r\n<pre>$( document ).bind(\"click\", function( e ) {\r\n    $( e.target ).closest(\"li\").toggleClass(\"hilight\");\r\n  });\r\n</pre>\r\n	<p class=\"tiy\" style=\"vertical-align:middle;color:#FFFFFF;text-align:center;background-color:#F88E8B;\">\r\n		<a target=\"_blank\" href=\"http://www.w3school.com.cn/tiy/t.asp?f=jquery_traversing_closest\">亲自试一试</a>\r\n	</p>\r\n</div>\r\n<div style=\"margin:0px 0px 0px 15px;padding:20px 0px;font-family:Verdana, Arial, 宋体;background-color:#F9F9F9;\">\r\n	<h2 style=\"font-family:微软雅黑;font-size:14px;\">\r\n		定义和用法\r\n	</h2>\r\n	<p>\r\n		closest() 方法获得匹配选择器的第一个祖先元素，从当前元素开始沿 DOM 树向上。\r\n	</p>\r\n</div>','2016-07-13 10:52:31',b'0',NULL),
	(202,61,1,'<p>&nbsp; &nbsp; &nbsp;dsf df&nbsp;</p>','2016-07-13 11:47:17',b'0',NULL),
	(203,61,1,'<p>&nbsp;<img src=\"/codeweb//bbs/showPic/1468381645615blob.png\" _src=\"/codeweb//bbs/showPic/1468381645615blob.png\"/></p>','2016-07-13 11:47:29',b'0',NULL),
	(204,62,1,'<p>dsfsdfd</p><p><br/></p><p><span style=\"line-height: 1.5;\">fd fd<br/>dffdf</span></p><p><span style=\"line-height: 1.5;\">dfdf<span><span><span>df&nbsp;<span style=\"background-color: rgb(221, 217, 195);\">fdfdf d &nbsp;dfd&nbsp;</span></span></span></span></span></p><p><span style=\"line-height: 1.5;\"><span><span><span><span style=\"background-color: rgb(221, 217, 195);\"><br/></span></span></span></span></span></p><p><span style=\"line-height: 1.5;\"><span><span><span><span style=\"background-color: rgb(221, 217, 195);\"><img src=\"/codeweb//bbs/showPic/1468381782525blob.png\" _src=\"/codeweb//bbs/showPic/1468381782525blob.png\"/></span></span></span></span></span></p><p><span style=\"line-height: 1.5;\"><span><span><span><span style=\"background-color: rgb(221, 217, 195);\"><br/></span></span></span></span></span></p><p><span style=\"line-height: 1.5;\"><span><span><span><span style=\"background-color: rgb(221, 217, 195);\"><br/></span></span></span></span></span></p>','2016-07-13 11:49:45',b'0',NULL),
	(205,62,1,'<p><img src=\"/codeweb//bbs/showPic/1468381801253blob.png\" _src=\"/codeweb//bbs/showPic/1468381801253blob.png\"/></p>','2016-07-13 11:50:04',b'0',NULL),
	(209,64,1,'<p>&nbsp; &nbsp; &nbsp;sdf sdfs</p>','2016-07-13 13:25:37',b'0',NULL),
	(210,65,1,'<p>&nbsp; &nbsp; &nbsp;sdfdfsdfsdfsdf</p>','2016-07-13 13:27:06',b'0',NULL),
	(211,66,1,'<p>sdfsdfsd</p>','2016-07-13 13:27:28',b'0',NULL),
	(212,66,1,'<p>&nbsp; &nbsp; &nbsp;dsfsdf&nbsp;</p>','2016-07-13 13:27:47',b'0',NULL),
	(213,66,1,'<p><a href=\"http://127.0.0.1:7700/codeweb/bbs/topic/66-1\" target=\"_blank\" title=\"课程\">http://127.0.0.1:7700/codeweb/bbs/topic/66-1&nbsp;</a></p>','2016-07-13 13:47:07',b'0',NULL),
	(214,67,1,'<h2>第三方斯蒂芬放到</h2><p>dfdfdf<br/></p><p><br/></p><p><br/></p>','2016-07-13 13:49:12',b'0',NULL),
	(215,68,1,'<p><img src=\"/codeweb//bbs/showPic/1468389086446blob.png\" _src=\"/codeweb//bbs/showPic/1468389086446blob.png\"/></p>','2016-07-13 13:51:28',b'0',NULL),
	(216,69,4,'<p><img src=\"/codeweb//bbs/showPic/1468391755464blob.png\" _src=\"/codeweb//bbs/showPic/1468391755464blob.png\" style=\"width: 754px; height: 585px;\"/></p><p><br/></p><p><br/></p><p>ok，试试看了多发点 多发点，明天搞</p>','2016-07-13 14:35:57',b'0',NULL),
	(217,69,4,'<p>&nbsp; &nbsp;<img src=\"/codeweb//bbs/showPic/1468391773228blob.png\" _src=\"/codeweb//bbs/showPic/1468391773228blob.png\" style=\"width: 680px; height: 445px;\"/></p>','2016-07-13 14:36:14',b'0',NULL),
	(218,69,4,'<p>什么时候讲？</p>','2016-07-13 14:37:49',b'0',NULL),
	(219,69,4,'<p><img src=\"/codeweb//bbs/showPic/1468392229548blob.png\" _src=\"/codeweb//bbs/showPic/1468392229548blob.png\" style=\"width: 700px; height: 444px;\"/></p>','2016-07-13 14:43:51',b'0',NULL);

/*!40000 ALTER TABLE `bbs_post` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bbs_reply
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bbs_reply`;

CREATE TABLE `bbs_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) NOT NULL DEFAULT '1',
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `content` varchar(300) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `topicID_R` (`topic_id`),
  KEY `postID_R` (`post_id`),
  KEY `userID_R` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `bbs_reply` WRITE;
/*!40000 ALTER TABLE `bbs_reply` DISABLE KEYS */;

INSERT INTO `bbs_reply` (`id`, `topic_id`, `post_id`, `user_id`, `content`, `create_time`)
VALUES
	(3,59,201,1,'三东方闪电','2016-07-13 10:52:40'),
	(4,59,201,1,'辅导费','2016-07-13 10:52:42'),
	(5,59,201,1,'第三代','2016-07-13 11:09:03'),
	(6,61,203,1,'dsfds ','2016-07-13 11:47:33'),
	(7,61,203,1,'df ','2016-07-13 11:47:35'),
	(8,62,205,1,'thanks','2016-07-13 11:50:23'),
	(9,62,204,1,'dsfsdf dfd ','2016-07-13 11:51:49'),
	(12,66,211,1,'fdfdf','2016-07-13 13:27:52'),
	(13,68,215,1,'好困难','2016-07-13 13:51:38'),
	(14,69,216,4,'看着不错','2016-07-13 14:37:30'),
	(15,69,216,4,'精彩','2016-07-13 14:37:34'),
	(16,69,218,4,'有时间就讲','2016-07-13 14:43:19');

/*!40000 ALTER TABLE `bbs_reply` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bbs_topic
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bbs_topic`;

CREATE TABLE `bbs_topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `module_id` int(11) NOT NULL,
  `post_count` int(11) NOT NULL DEFAULT '1',
  `reply_count` int(11) NOT NULL DEFAULT '0',
  `pv` int(11) NOT NULL DEFAULT '0',
  `content` varchar(150) NOT NULL,
  `emotion` tinyint(2) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_nice` bit(1) NOT NULL DEFAULT b'0',
  `is_up` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `moduleID_T` (`module_id`),
  KEY `userID_T` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `bbs_topic` WRITE;
/*!40000 ALTER TABLE `bbs_topic` DISABLE KEYS */;

INSERT INTO `bbs_topic` (`id`, `user_id`, `module_id`, `post_count`, `reply_count`, `pv`, `content`, `emotion`, `create_time`, `is_nice`, `is_up`)
VALUES
	(59,1,2,1,0,4,'再发表一次看看那',NULL,'2016-07-13 10:52:31',b'0',b'1'),
	(60,1,2,1,0,3,'地方对双方都',NULL,'2016-07-13 11:45:14',b'1',b'1'),
	(61,1,2,2,0,4,'dfdf ',NULL,'2016-07-13 11:47:17',b'0',b'0'),
	(62,1,2,2,0,12,'再发表一次看看那',NULL,'2016-07-13 11:49:45',b'0',b'0'),
	(64,1,2,1,0,3,'sdfsdf',NULL,'2016-07-13 13:25:37',b'0',b'0'),
	(65,1,1,1,0,2,'sfsfs',NULL,'2016-07-13 13:27:06',b'0',b'0'),
	(66,1,1,3,0,10,'hello go',NULL,'2016-07-13 13:27:28',b'0',b'0'),
	(67,1,2,1,0,2,'',NULL,'2016-07-13 13:49:12',b'0',b'0'),
	(68,1,1,1,0,9,'关于什么什么的课程卡缴纳困难是发顺丰的',NULL,'2016-07-13 13:51:28',b'0',b'0'),
	(69,4,1,4,0,82,'Zookeeper',NULL,'2016-07-13 14:35:57',b'1',b'1');

/*!40000 ALTER TABLE `bbs_topic` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bbs_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bbs_user`;

CREATE TABLE `bbs_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT '0' COMMENT '积分',
  `level` int(11) DEFAULT '1' COMMENT '积分换算成等级，新生，老生，班主任，教导主任，校长',
  `balance` int(11) DEFAULT '0' COMMENT '积分余额',
  `corp` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `bbs_user` WRITE;
/*!40000 ALTER TABLE `bbs_user` DISABLE KEYS */;

INSERT INTO `bbs_user` (`id`, `user_name`, `password`, `email`, `score`, `level`, `balance`, `corp`)
VALUES
	(1,'测试哟','e10adc3949ba59abbe56e057f20f883e','xxx',0,1,0,'xxx'),
	(4,'李家智','e10adc3949ba59abbe56e057f20f883e',NULL,140,2,0,'xxx'),
	(5,'赵晴文','e10adc3949ba59abbe56e057f20f883e','zhaoqingwen@coamc.com',1000,5,0,'xxx'),
	(6,'石萌','e10adc3949ba59abbe56e057f20f883e','shimeng@coamc.com',12,1,0,'xxx'),
	(95,'admin','e10adc3949ba59abbe56e057f20f883e','xxxx@coamc.com',0,1,0,'xxx'),
	(97,'andyjoe','e10adc3949ba59abbe56e057f20f883e','158633490@qq.com',10,1,10,NULL);

/*!40000 ALTER TABLE `bbs_user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
