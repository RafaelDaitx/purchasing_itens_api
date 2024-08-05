-- Dumping database structure for rest_with_spring_boot_erudio
CREATE DATABASE IF NOT EXISTS `purchasing_itens` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `purchasing_itens`;

-- Dumping structure for table rest_with_spring_boot_erudio.person
CREATE TABLE IF NOT EXISTS `categories` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `category_name` varchar(100) NOT NULL,
    PRIMARY KEY (`id`)
    );

