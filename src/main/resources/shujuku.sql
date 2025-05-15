-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.22 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.1.0.6116
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 jdbc_learn 的数据库结构
DROP DATABASE IF EXISTS `jdbc_learn`;
CREATE DATABASE IF NOT EXISTS `jdbc_learn` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `jdbc_learn`;

-- 导出  表 jdbc_learn.customers 结构
DROP TABLE IF EXISTS `customers`;
CREATE TABLE IF NOT EXISTS `customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(15) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `photo` mediumblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  jdbc_learn.customers 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` (`id`, `name`, `email`, `birth`, `photo`) VALUES
	(1, '汪峰', 'wf@126.com', '2010-02-02', NULL),
	(2, '王菲', 'wangf@163.com', '1988-12-26', NULL),
	(3, '林志玲', 'linzl@gmail.com', '1984-06-12', NULL),
	(4, '汤唯', 'tangw@sina.com', '1986-06-13', NULL),
	(5, '成龙', 'Jackey@gmai.com', '1955-07-14', NULL),
	(6, '迪丽热巴', 'reba@163.com', '1983-05-17', NULL),
	(7, '刘亦菲', 'liuyifei@qq.com', '1991-11-14', NULL),
	(8, '陈道明', 'bdf@126.com', '2014-01-17', NULL),
	(10, '周杰伦', 'zhoujl@sina.com', '1979-11-15', NULL),
	(12, '黎明', 'LiM@126.com', '1998-09-08', NULL),
	(13, '张学友', 'zhangxy@126.com', '1998-12-21', NULL),
	(16, '朱茵', 'zhuyin@126.com', '2014-01-16', NULL),
	(18, '贝多芬', 'beidf@126.com', '2014-01-17', NULL);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;

-- 导出  表 jdbc_learn.examstudent 结构
DROP TABLE IF EXISTS `examstudent`;
CREATE TABLE IF NOT EXISTS `examstudent` (
  `FlowID` int NOT NULL AUTO_INCREMENT,
  `Type` int DEFAULT NULL,
  `IDCard` varchar(18) DEFAULT NULL,
  `ExamCard` varchar(15) DEFAULT NULL,
  `StudentName` varchar(20) DEFAULT NULL,
  `Location` varchar(20) DEFAULT NULL,
  `Grade` int DEFAULT NULL,
  PRIMARY KEY (`FlowID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gb2312;

-- 正在导出表  jdbc_learn.examstudent 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `examstudent` DISABLE KEYS */;
INSERT INTO `examstudent` (`FlowID`, `Type`, `IDCard`, `ExamCard`, `StudentName`, `Location`, `Grade`) VALUES
	(1, 4, '412824195263214584', '200523164754000', '张锋', '郑州', 85),
	(2, 4, '222224195263214584', '200523164754001', '孙朋', '大连', 56),
	(3, 6, '342824195263214584', '200523164754002', '刘明', '沈阳', 72),
	(4, 6, '100824195263214584', '200523164754003', '赵虎', '哈尔滨', 95),
	(5, 4, '454524195263214584', '200523164754004', '杨丽', '北京', 64),
	(6, 4, '854524195263214584', '200523164754005', '王小红', '太原', 60);
/*!40000 ALTER TABLE `examstudent` ENABLE KEYS */;

-- 导出  表 jdbc_learn.order 结构
DROP TABLE IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `order_name` varchar(20) DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  jdbc_learn.order 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` (`order_id`, `order_name`, `order_date`) VALUES
	(1, 'AA', '2010-03-04'),
	(2, 'BB', '2000-02-01'),
	(4, 'GG', '1994-06-28');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;

-- 导出  表 jdbc_learn.user 结构
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `password` varchar(15) NOT NULL DEFAULT '123456',
  `address` varchar(25) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  jdbc_learn.user 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `name`, `password`, `address`, `phone`) VALUES
	(1, '章子怡', 'qwerty', 'Beijing', '13788658672'),
	(2, '郭富城', 'abc123', 'HongKong', '15678909898'),
	(3, '林志玲', '654321', 'Taiwan', '18612124565'),
	(4, '梁静茹', '987654367', 'malaixiya', '18912340998'),
	(5, 'LadyGaGa', '123456', 'America', '13012386565');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- 导出  表 jdbc_learn.user_table 结构
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE IF NOT EXISTS `user_table` (
  `user` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `balance` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  jdbc_learn.user_table 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `user_table` DISABLE KEYS */;
INSERT INTO `user_table` (`user`, `password`, `balance`) VALUES
	('AA', '123456', 1000),
	('BB', '654321', 1000),
	('CC', 'abcd', 2000),
	('DD', 'abcder', 3000);
/*!40000 ALTER TABLE `user_table` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
