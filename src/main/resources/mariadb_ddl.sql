use ERK_SYS;

CREATE TABLE IF NOT EXISTS user_info (
    `user_id` INT(11) NOT NULL,
    `org_id` INT(11) NOT NULL,
    `ts` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(`user_id`),
    KEY `idx_org_id` (`org_id`)
);

CREATE TABLE IF NOT EXISTS engine_info (
    `id` INT(11) NOT NULL AUTO_INCREMENT
    `type` VARCHAR(32) NOT NULL,
    `ipv4_addr` VARCHAR(32) NOT NULL,
    `status` TINYINT(1) DEFAULT 0,
    `ts` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(`id`),
    KEY `idx_type` (`type`),
    KEY `idx_addr` (`ipv4_addr`)
);