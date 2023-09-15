-- --------------------------------------------------------
-- Máy chủ:                      127.0.0.1
-- Server version:               11.1.2-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Phiên bản:           12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for mydb
CREATE DATABASE IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `mydb`;

-- Dumping structure for table mydb.account
CREATE TABLE IF NOT EXISTS `account` (
  `account_id` varchar(50) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table mydb.account: ~3 rows (approximately)
INSERT INTO `account` (`account_id`, `full_name`, `password`, `email`, `phone`, `status`) VALUES
	('Ac1', 'NguyenVanLoc', '123456', NULL, NULL, 1),
	('Ac2', 'VoNgocUyenNhi', '56789', 'uyennhi@gmail.com', '0335321', 1),
	('Ac3', 'DoVanNguyen', '11111', 'nguyendo@gmail.com', '089764213', 0);

-- Dumping structure for table mydb.grant_access
CREATE TABLE IF NOT EXISTS `grant_access` (
  `role_id` varchar(50) NOT NULL,
  `account_id` varchar(50) NOT NULL,
  `is_grant` enum('1','0') NOT NULL,
  `note` varchar(250) DEFAULT '',
  PRIMARY KEY (`role_id`,`account_id`),
  KEY `account_grant` (`account_id`),
  CONSTRAINT `account_grant` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_grant` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table mydb.grant_access: ~5 rows (approximately)
INSERT INTO `grant_access` (`role_id`, `account_id`, `is_grant`, `note`) VALUES
	('r1', 'Ac1', '1', ''),
	('r2', 'Ac2', '1', ''),
	('r2', 'Ac3', '0', ''),
	('r3', 'Ac2', '1', ''),
	('r3', 'Ac3', '1', NULL);

-- Dumping structure for table mydb.log
CREATE TABLE IF NOT EXISTS `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(50) NOT NULL,
  `login_time` datetime NOT NULL,
  `logout_time` datetime NOT NULL,
  `notes` varchar(250) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci COMMENT='ghi logs';

-- Dumping data for table mydb.log: ~12 rows (approximately)
INSERT INTO `log` (`id`, `account_id`, `login_time`, `logout_time`, `notes`) VALUES
	(7, 'Ac1', '2023-09-15 23:12:09', '2023-09-15 23:26:29', ''),
	(8, 'Ac1', '2023-09-15 23:12:28', '2023-09-15 23:26:29', ''),
	(9, 'Ac1', '2023-09-15 23:13:11', '2023-09-15 23:26:29', ''),
	(10, 'Ac1', '2023-09-15 23:18:27', '2023-09-15 23:26:29', ''),
	(11, 'Ac1', '2023-09-15 23:19:40', '2023-09-15 23:26:29', ''),
	(12, 'Ac1', '2023-09-15 23:26:22', '2023-09-15 23:26:29', ''),
	(13, 'Ac1', '2023-09-15 23:28:16', '2023-09-15 23:28:20', ''),
	(14, 'Ac1', '2023-09-15 23:29:01', '2023-09-15 23:29:02', ''),
	(15, 'Ac2', '2023-09-15 23:30:14', '2023-09-15 23:30:14', ''),
	(16, 'Ac2', '2023-09-15 23:30:57', '2023-09-15 23:30:58', ''),
	(17, 'Ac3', '2023-09-15 23:32:20', '2023-09-15 23:32:23', ''),
	(18, 'Ac1', '2023-09-15 23:32:45', '2023-09-15 23:32:59', '');

-- Dumping structure for table mydb.role
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` varchar(50) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table mydb.role: ~4 rows (approximately)
INSERT INTO `role` (`role_id`, `role_name`, `description`, `status`) VALUES
	('r1', 'admin', 'all', NULL),
	('r2', 'add', 'them', NULL),
	('r3', 'update', 'sua', NULL),
	('r4', 'delete', 'xoa', NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
