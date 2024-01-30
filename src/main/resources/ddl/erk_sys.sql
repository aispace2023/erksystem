use ERK_SYS;

DROP TABLE IF EXISTS service_provider_tbl;
CREATE TABLE service_provider_tbl (
    `org_id` INT(11) NOT NULL,
    `org_name` VARCHAR(32) NOT NULL,
    `org_pwd` VARCHAR(32) NOT NULL,
    `service_duration` CHAR(8) NOT NULL,
    `user_number` INT(11) NOT NULL,
    `service_type` VARCHAR(32) NOT NULL,
    `ts` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(`org_id`),
    UNIQUE KEY service_provider_tbl.idx1 (`org_name`)
);

DROP TABLE IF EXISTS service_user_tbl;
CREATE TABLE service_user_tbl (
    `org_id` INT(11) NOT NULL,
    `user_id` INT(11) NOT NULL,
    `user_name` VARCHAR(32),
    `user_pwd` VARCHAR(32),
    `service_duration` CHAR(8) NOT NULL,
    `user_number` INT(11),
    `age` TINYINT,
    `sex` TINYINT,
    `mbti` VARCHAR(32),
    `user_type` TINYINT,
    `service_type` VARCHAR(32) NOT NULL,
    `ts` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(`org_id`, `user_id`),
    FOREIGN KEY(`org_id`) REFERENCES service_provider_tbl(`org_id`)
);
