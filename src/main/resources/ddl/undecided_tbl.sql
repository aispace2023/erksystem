
DROP TABLE IF EXISTS service_info_tbl;
CREATE TABLE service_info_tbl (
    `service_id` INT(11) NOT NULL,
    `service_name` VARCHAR(32) NOT NULL,
    `ts` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(`service_id`),
    UNIQUE KEY service_info_tbl.idx1 (`service_name`)
);