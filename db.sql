/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 5.6.45-log : Database - threebullforum
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`threebullforum` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `threebullforum`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(11) DEFAULT NULL,
  `password` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `admin` */

insert  into `admin`(`id`,`username`,`password`) values 
(1,'admin','12345'),
(2,'ad12321','12345'),
(3,'add','123123');

/*Table structure for table `reply` */

DROP TABLE IF EXISTS `reply`;

CREATE TABLE `reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) DEFAULT NULL,
  `content` text,
  `user_id` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

/*Data for the table `reply` */

insert  into `reply`(`id`,`topic_id`,`content`,`user_id`,`time`) values 
(17,14,'?',4,'2019-11-29 16:27:38'),
(18,14,'?',4,'2019-11-29 16:27:48'),
(19,13,'111',4,'2019-11-29 17:00:39');

/*Table structure for table `topic` */

DROP TABLE IF EXISTS `topic`;

CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1100) DEFAULT NULL,
  `content` text,
  `user_id` int(11) DEFAULT NULL,
  `top_status` tinyint(1) DEFAULT '0',
  `top_time` datetime DEFAULT NULL,
  `post_time` datetime DEFAULT NULL,
  `follow_number` int(11) DEFAULT NULL,
  `click_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

/*Data for the table `topic` */

insert  into `topic`(`id`,`name`,`content`,`user_id`,`top_status`,`top_time`,`post_time`,`follow_number`,`click_number`) values 
(14,'??','?',4,1,'2019-11-29 17:38:13','2019-11-29 16:27:24',2,8),
(15,'e2r','1341',0,0,NULL,'2019-11-29 16:38:43',0,0),
(16,'32414','144',0,0,NULL,'2019-11-29 16:38:56',0,0),
(18,'123124','14',8,0,NULL,'2019-11-29 16:44:51',0,0),
(19,'fasf','asdsaa',4,0,NULL,'2019-11-29 17:22:16',0,0),
(20,'111','11111111111111111',4,0,NULL,'2019-11-29 17:22:20',0,0),
(21,'22222','222222222222222',4,1,'2019-11-29 17:38:15','2019-11-29 17:22:23',0,0);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(11) DEFAULT NULL,
  `password` varchar(11) DEFAULT NULL,
  `lock_status` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`lock_status`) values 
(4,'user','123456',0),
(5,'user1','123456',0),
(6,'test','123456',0),
(7,'testtest','123456',0),
(8,'zzz','123',0),
(9,'      ','123',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
