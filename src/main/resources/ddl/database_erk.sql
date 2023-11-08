use ERK_SYS;

DROP TABLE IF EXISTS service_provider_tbl;
CREATE TABLE service_provider_tbl (
    `org_id` INT(11) NOT NULL AUTO_INCREMENT,
    `org_name` VARCHAR(16) NOT NULL,
    `org_pwd` VARCHAR(16) NOT NULL,
    `service_duration` VARCHAR(8) NOT NULL,
    `user_number` INT(11) NOT NULL,
    `service_type` VARCHAR(32) NOT NULL,
    `ts` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(`org_id`),
    UNIQUE KEY service_provider_tbl.idx1 (`org_name`)
);

--FOREIGN KEY(`org_id`) REFERENCES service_provider_tbl(`org_id`),
DROP TABLE IF EXISTS service_user_tbl;
CREATE TABLE service_user_tbl (
    `user_id` INT(11) NOT NULL AUTO_INCREMENT,
    `org_id` INT(11) NOT NULL,
    `user_name` VARCHAR(16),
    `user_pwd` VARCHAR(16),
    `service_duration` VARCHAR(8) NOT NULL,
    `age` TINYINT,
    `user_type` VARCHAR(64),
    `service_type` VARCHAR(32) NOT NULL,
    `ts` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(`user_id`, `org_id`),
    UNIQUE KEY service_user_tbl.idx1 (`user_name`)
);
