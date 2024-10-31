CREATE TABLE IF NOT EXISTS `users` (
     `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `username` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `email` varchar(255) DEFAULT NULL,
    `zip_code` varchar(10) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`)
    ) ENGINE=InnoDB;
