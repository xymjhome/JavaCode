create database store28;
use store28;

CREATE TABLE `user` (
		  `uid` varchar(32) NOT NULL,
		  `username` varchar(20) DEFAULT NULL,
		  `password` varchar(100) DEFAULT NULL,
		  `name` varchar(20) DEFAULT NULL,
		  `email` varchar(30) DEFAULT NULL,
		  `telephone` varchar(20) DEFAULT NULL,
		  `birthday` date DEFAULT NULL,
		  `sex` varchar(10) DEFAULT NULL,
		  `state` int(11) DEFAULT NULL,
		  `code` varchar(64) DEFAULT NULL,
		  PRIMARY KEY (`uid`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;