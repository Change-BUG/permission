-- auto Generated on 2023-02-13
-- DROP TABLE IF EXISTS sys_resource;
CREATE TABLE sys_resource(
	_id INT (11) NOT NULL AUTO_INCREMENT COMMENT '编号',
	introduction VARCHAR (50) NOT NULL COMMENT '描述',
	`status` INT (11) NOT NULL COMMENT '状态',
	add_time DATETIME NOT NULL COMMENT '创建时间',
	upd_time DATETIME NOT NULL COMMENT '修改时间',
	`name` VARCHAR (50) NOT NULL COMMENT '接口名称',
	url TEXT NOT NULL COMMENT '接口URL',
	permission VARCHAR (50) NOT NULL COMMENT '接口权限',
	PRIMARY KEY (_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'sys_resource';
