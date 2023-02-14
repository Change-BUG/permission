-- auto Generated on 2023-02-13
-- DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user(
	_id INT (11) NOT NULL AUTO_INCREMENT COMMENT '_id',
	avatar TEXT COMMENT 'avatar',
	account VARCHAR (16) COMMENT 'account',
	`password` VARCHAR (50) COMMENT 'password',
	introduction TEXT COMMENT 'introduction',
	department VARCHAR (20) COMMENT 'department',
	`status` INT (11) COMMENT 'status',
	add_time VARCHAR (50) COMMENT 'add_time',
	upd_time VARCHAR (50) COMMENT 'upd_time',
	PRIMARY KEY (_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'sys_user';
