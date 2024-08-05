-- Dumping database structure for rest_with_spring_boot_erudio
CREATE DATABASE IF NOT EXISTS `purchasing_itens` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `purchasing_itens`;

ALTER TABLE items
    ADD category_id INT DEFAULT 0