CREATE TABLE `anubis_partner`
(
    `id`   int(11) NOT NULL AUTO_INCREMENT,
    `code` varchar(10) DEFAULT NULL,
    `name` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `anubis_partner_key`
(
    `id`         int(11) NOT NULL AUTO_INCREMENT,
    `partner_id` int(11) NOT NULL,
    `pub_key`    varchar(5000) NOT NULL,
    `expire_at`  bigint(20),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
