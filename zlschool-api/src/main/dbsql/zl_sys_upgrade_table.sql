/*--3.补丁升级  */
DROP TABLE IF EXISTS t_sys_app;						    /*APP类型表*/
DROP TABLE IF EXISTS t_sys_app_version;				/*APP版本表*/
DROP TABLE IF EXISTS t_sys_app_package;				/*APP补丁文件表*/
DROP TABLE IF EXISTS t_sys_app_upgrade;				/*APP升级日志表*/

/*--3.1升级包:应用程序  */
CREATE TABLE t_sys_app(
    `id`              	varchar(36)     NOT NULL 	 COMMENT '应用ID(appId)',
    `app_name`         	varchar(100)    DEFAULT NULL COMMENT '英文名',      
    `name`        		varchar(100)    DEFAULT NULL COMMENT '中文名',    
    `version_id`   		varchar(36)     DEFAULT NULL COMMENT '最新版本号',
    constraint PK_SYS_APP primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='APP类型表';

/*--3.2升级包:历史版本  */
CREATE TABLE t_sys_app_version(
    `id`              	varchar(36)     NOT NULL 	 COMMENT '安装包编码',
    `app_id`          	varchar(36)     NOT NULL 	 COMMENT '所属APP',
    `version`         	varchar(36)     NOT NULL 	 COMMENT '版本号',
    `release_time`     	datetime        DEFAULT NULL COMMENT '发布时间',
    `download_times`   	int             DEFAULT 0 	 COMMENT '下载次数',
    `memo`           	varchar(500)    DEFAULT NULL COMMENT '备注说明',
    constraint PK_SYS_APP_VERSION primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='APP版本表';

/*--3.3升级包:补丁文件  */
/*MYSQL最大存储分别为：TinyBlob 最大 255, Blob 最大 65K, MediumBlob 最大 16M, LongBlob 最大 4G；*/
/*MSSQL: IMAGE*/
CREATE TABLE t_sys_app_package (
    `id`              	varchar(36)     NOT NULL 	 COMMENT '编码',
    `app_id`           	varchar(36)     NOT NULL 	 COMMENT '所属APP',		
    `version_id`       	varchar(36)     NOT NULL 	 COMMENT '版本ID',		
    `file_name`        	varchar(255)    NOT NULL 	 COMMENT '补丁文件名',
    `file_size`        	int             DEFAULT NULL COMMENT '补丁文件大小(KB)',
    `content`         	LongBlob        DEFAULT NULL COMMENT '补丁文件内容(二进制存储在数据)',
    `download_url`      varchar(255)    DEFAULT NULL COMMENT '下载地址URL',
    constraint PK_SYS_APP_PACKAGE primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='APP补丁文件表';

/*--3.4升级包:升级记录  */
CREATE TABLE t_sys_app_upgrade (
	`id`              	varchar(36)     NOT NULL 	 COMMENT '编码',
	`client_id`			varchar(36)		NOT NULL 	 COMMENT '终端编码(由具体应用保持唯一)',
	`app_id`			varchar(36)		NOT NULL 	 COMMENT '所属APP',
	`version_id`		varchar(36)		NOT NULL 	 COMMENT '版本号',
	`upgrade_time`		datetime		NOT NULL 	 COMMENT '升级时间',
	constraint PK_SYS_APP_UPGRADE primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='APP升级日志表';