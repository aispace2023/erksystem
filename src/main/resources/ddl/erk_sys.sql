use ERK_SYS;

-- user_tbl 이 provider_tbl 을 참조하므로 먼저 삭제해야 한다.
DROP TABLE IF EXISTS service_user_tbl;
DROP TABLE IF EXISTS service_provider_tbl;

CREATE TABLE service_provider_tbl (
    `org_id` INT(11) NOT NULL,
    `org_name` VARCHAR(100) NOT NULL,
    `org_pwd` VARCHAR(100) NOT NULL,
    `service_duration` CHAR(8) NOT NULL,
    `user_number` INT(11) NOT NULL,
    `service_type` INT(11) NOT NULL,
    `ts` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(`org_id`),
    UNIQUE KEY service_provider_tbl.idx1 (`org_name`)
);

CREATE TABLE service_user_tbl (
    `org_id` INT(11) NOT NULL,
    `user_id` INT(11) NOT NULL,
    `user_name` VARCHAR(100),
    `user_pwd` VARCHAR(100),
    `service_duration` CHAR(8) NOT NULL,
    `user_number` INT(11),
    `age` TINYINT,
    `sex` TINYINT,
    `mbti` VARCHAR(32),
    `user_type` TINYINT,
    `service_type` INT(11) NOT NULL,
    `ts` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(`org_id`, `user_id`),
    FOREIGN KEY(`org_id`) REFERENCES service_provider_tbl(`org_id`)
);
