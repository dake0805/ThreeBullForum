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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`id`,`username`,`password`) values 
(1,'ad','12345');

/*Table structure for table `reply` */

DROP TABLE IF EXISTS `reply`;

CREATE TABLE `reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) DEFAULT NULL,
  `content` varchar(1100) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `reply` */

/*Table structure for table `topic` */

DROP TABLE IF EXISTS `topic`;

CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varbinary(1100) DEFAULT NULL,
  `content` varchar(1100) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `top_status` tinyint(1) DEFAULT '0',
  `top_time` datetime DEFAULT NULL,
  `post_time` datetime DEFAULT NULL,
  `follow_number` int(11) DEFAULT NULL,
  `click_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `topic` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(11) DEFAULT NULL,
  `password` varchar(11) DEFAULT NULL,
  `lock_status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`) values(1,'user','123456');
insert  into `user`(`id`,`username`,`password`) values(2,'user2','123456');
insert  into `user`(`id`,`username`,`password`) values(3,'user3','123456');

insert into `topic`(`id`,`name`,`content`,`user_id`,`post_time`,`follow_number`,`click_number`) values
(1,'111','11111111',1,'2017-08-31 00:00:00',5,8);
insert into `topic`(`id`,`name`,`content`,`user_id`,`post_time`,`follow_number`,`click_number`) values
(2,'222','11111111',1,'2017-08-31 02:00:00',7,4);
insert into `topic`(`id`,`name`,`content`,`user_id`,`post_time`,`follow_number`,`click_number`) values
(3,'333','11111111',1,'2017-08-31 04:00:00',3,6);
insert into `topic`(`id`,`name`,`content`,`user_id`,`post_time`,`follow_number`,`click_number`) values
(4,'444','11111111',1,'2017-08-31 05:00:00',5,8);
insert into `topic`(`id`,`name`,`content`,`user_id`,`post_time`,`follow_number`,`click_number`) values
(5,'555','11111111',1,'2017-08-31 06:00:00',7,4);
insert into `topic`(`id`,`name`,`content`,`user_id`,`post_time`,`follow_number`,`click_number`) values
(6,'666','11111111',1,'2017-08-31 07:00:00',3,6);
insert into `topic`(`id`,`name`,`content`,`user_id`,`post_time`,`follow_number`,`click_number`) values
(7,'777','11111111',1,'2017-08-31 08:00:00',5,8);
insert into `topic`(`id`,`name`,`content`,`user_id`,`post_time`,`follow_number`,`click_number`) values
(8,'888','11111111',1,'2017-08-31 09:00:00',7,4);
insert into `topic`(`id`,`name`,`content`,`user_id`,`post_time`,`follow_number`,`click_number`) values
(9,'999','11111111',1,'2017-08-31 10:00:00',3,6);
insert into `topic`(`id`,`name`,`content`,`user_id`,`post_time`,`follow_number`,`click_number`) values
(10,'101010','11111111',1,'2017-08-31 11:00:00',5,8);
insert into `topic`(`id`,`name`,`content`,`user_id`,`post_time`,`follow_number`,`click_number`) values
(11,'111111','11111111',1,'2017-08-31 12:00:00',7,4);
insert into `topic`(`id`,`name`,`content`,`user_id`,`post_time`,`follow_number`,`click_number`) values
(12,'121212','11111111',1,'2017-08-31 13:00:00',3,6);

insert into `reply`(`id`,`topic_id`,`content`,`user_id`,`time`) values
(1,10,'This is a reply.',1,'2019-08-31 12:00:00');
insert into `reply`(`id`,`topic_id`,`content`,`user_id`,`time`) values
(2,10,'This is a reply.',2,'2019-08-31 12:00:01');
insert into `reply`(`id`,`topic_id`,`content`,`user_id`,`time`) values
(3,10,'This is a reply.',3,'2019-08-31 12:00:02');
insert into `reply`(`id`,`topic_id`,`content`,`user_id`,`time`) values
(4,10,'This is a reply.',1,'2019-08-31 12:00:03');
insert into `reply`(`id`,`topic_id`,`content`,`user_id`,`time`) values
(5,10,'This is a reply.',2,'2019-08-31 12:00:04');
insert into `reply`(`id`,`topic_id`,`content`,`user_id`,`time`) values
(6,10,'This is a reply.',3,'2019-08-31 12:00:05');
insert into `reply`(`id`,`topic_id`,`content`,`user_id`,`time`) values
(7,10,'This is a reply.',1,'2019-08-31 12:00:06');
insert into `reply`(`id`,`topic_id`,`content`,`user_id`,`time`) values
(8,10,'This is a reply.',2,'2019-08-31 12:00:07');
insert into `reply`(`id`,`topic_id`,`content`,`user_id`,`time`) values
(9,10,'This is a reply.',3,'2019-08-31 12:00:08');
insert into `reply`(`id`,`topic_id`,`content`,`user_id`,`time`) values
(10,10,'This is a reply.',1,'2019-08-31 12:00:09');
insert into `reply`(`id`,`topic_id`,`content`,`user_id`,`time`) values
(11,10,'This is a reply.',2,'2019-08-31 12:00:10');
insert into `reply`(`id`,`topic_id`,`content`,`user_id`,`time`) values
(12,10,'This is a reply.',3,'2019-08-31 12:00:11');
insert into `reply`(`id`,`topic_id`,`content`,`user_id`,`time`) values
(13,10,'This is a reply.',1,'2019-08-31 12:00:12');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
