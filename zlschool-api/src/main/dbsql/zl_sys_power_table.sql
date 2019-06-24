/* 不要用tab键盘替代空格，否则mysql不能批量建库  */
/*--1.基础表*/
DROP TABLE IF EXISTS t_sys_idcontrol;				  /*--物理主键控制表(分区) */
DROP TABLE IF EXISTS t_sys_param;					    /*--系统参数表(非00为分区--00为全局)*/
DROP TABLE IF EXISTS t_sys_sms_code;				  /*--短信表*/
DROP TABLE IF EXISTS t_sys_area;					    /*--地理区域树 */
DROP TABLE IF EXISTS t_sys_exception_log;		  /*--异常日志表 */

/*--2.权限管理表*/
DROP TABLE IF EXISTS t_sys_enterprise;				/*--企业组织表 */
DROP TABLE IF EXISTS t_sys_department;				/*--企业部门表 */
DROP TABLE IF EXISTS t_sys_enterprise_user;		/*--企业用户表 */
DROP TABLE IF EXISTS t_sys_menu;					    /*--系统菜单和操作表 */
DROP TABLE IF EXISTS t_sys_role;					    /*--系统角色表(分区) */
DROP TABLE IF EXISTS t_sys_role_power;				/*--系统角色菜单和操作权限表(分区) */
DROP TABLE IF EXISTS t_sys_user;					    /*--系统用户表(分区) */
DROP TABLE IF EXISTS t_sys_user_power;				/*--系统用户所属角色权限表(分区) */
DROP TABLE IF EXISTS t_sys_log;						    /*--系统日志表(分区) */

/*--1.1.键值控制表*/
create table t_sys_idcontrol(
	eid             varchar(36)			NOT NULL 	   COMMENT'所属组织',
	idkey			      varchar(100)		  NOT NULL 	   COMMENT'表名',
	curvalue		    bigint				    NOT NULL 	   COMMENT'最大ID',
	constraint PK_SYS_IDCONTROL primary key (eid, idkey)
);

/*--1.2.系统参数表*/
create table t_sys_param(
	eid             varchar(36)			NOT NULL 	   COMMENT'所属组织',
	user_id			    varchar(36)			NOT NULL 	   COMMENT'所属用户(0表示系统全局参数)',
	pname			      varchar(100)		  NOT NULL 	   COMMENT'参数名',
	pvalue			    varchar(100)		  DEFAULT NULL COMMENT'参数值',
	memo			      varchar(500)		  DEFAULT NULL COMMENT'备注说明',
	constraint PK_sys_param primary key(eid, user_id, pname)
);

/*--1.3.手机验证码*/
create table t_sys_sms_code (
	mobile			    varchar(20)			NOT NULL	     COMMENT'手机号',
	type			      int					      NOT NULL	     COMMENT'验证码类型(1->注册, 2->修改密码)',
	smscode			    varchar(100)		  NOT NULL	     COMMENT'验证码',
	requestdt		    datetime			    NOT NULL	     COMMENT'请求时间',
	expiredtime		  int					      NOT NULL	     COMMENT'有效时间(秒)',
	constraint PK_sys_smscode primary key(mobile,type)
);

/*--1.4.地理区域树*/
create table t_sys_area(
	id				      varchar(36)			NOT NULL	     COMMENT'地域编码',
	name			      varchar(200)		  NOT NULL	     COMMENT'地域名称',
	parent_id		    varchar(36)			NOT NULL	     COMMENT'上级地域编码',
	area_code		    varchar(36)			DEFAULT NULL COMMENT'业务编码(如省码)',
	type			      int					      DEFAULT NULL COMMENT'地域类型(1-国家, 2->省份/直辖市, 3->市, 4->县/区)',
	status 			    int 				      DEFAULT 0	   COMMENT'启用状态(1->启用, 0->未启用)',
	constraint PK_bd_area primary key(id)
);

/*--1.5.异常日志表*/
CREATE TABLE t_sys_exception_log(
	id 				      varchar(36) 		  NOT NULL 	   COMMENT'编码',
	param 			    varchar(800) 		DEFAULT NULL COMMENT'参数',
	exception_info 	text 				      DEFAULT NULL COMMENT'异常信息',
	create_time 	  datetime 			    DEFAULT NULL COMMENT'创建时间',
	action_key 		  varchar(512) 		DEFAULT NULL COMMENT'请求地址',
	ip 				      varchar(100) 		DEFAULT NULL COMMENT'IP地址',
	port 			      int 				      DEFAULT 0	   COMMENT'端口',
	PRIMARY KEY (`id`)
);

/*--2.1.企业表   */
create table t_sys_enterprise (
	id				      varchar(36)			NOT NULL	     COMMENT'内码',
	name			      varchar(100)		  NOT NULL      COMMENT'企业名称',
	sys_name			  varchar(100)		  NOT NULL      COMMENT'系统名称',
	sys_logo			  varchar(100)		  NOT NULL      COMMENT'系统LOGO',
	code			      varchar(36)			NOT NULL	     COMMENT'企业编码',
	invite_link		  varchar(500)		  DEFAULT NULL COMMENT'企业邀请链接',
	allow_size		  bigint	 			    DEFAULT 0	   COMMENT'允许上传文件大小(默认20480000kb)',
	make_size		    bigint	 			    DEFAULT 0	   COMMENT'已上传文件大小',
	allow_user		  int				 	      DEFAULT 0	   COMMENT'允许使用的用户数(默认1000)',
	make_user		    int	 				      DEFAULT 0	   COMMENT'已有用户数',
	validate		    datetime			    DEFAULT NULL COMMENT'该企业有效期(默认一年)',
	status			    int					      DEFAULT 0	   COMMENT'状态(1:启用 2:禁用)',
	license			    varchar(100)		  DEFAULT NULL COMMENT'营业执照URL地址',
	license_term    datetime			    DEFAULT NULL COMMENT'营业执照有效期',
	create_userid	  varchar(36) 		  DEFAULT NULL COMMENT'创建人ID',
	created_time	  datetime 			    DEFAULT NULL COMMENT'创建日期',
	update_userid 	varchar(36) 		  DEFAULT NULL COMMENT'更新人ID',
	updated_time 	  datetime 			    DEFAULT NULL COMMENT'更新时间',
	pro_content 		varchar(500) 		DEFAULT NULL COMMENT'审批内容',
	pro_time 		    datetime 			    DEFAULT NULL COMMENT'审批时间',
	pro_status 		  int 			        DEFAULT NULL COMMENT'审批状态(1:待审核 2:审核不通过 3:审核通过)',
	pro_userid 		  varchar(100)			DEFAULT NULL COMMENT'审批人ID',
	pro_memo 		    varchar(500) 		DEFAULT NULL COMMENT'审批备注',
	CONSTRAINT PK_SYS_ENTERPRISE PRIMARY KEY (id)
);

/*--2.2.部门表 */
create table t_sys_department (
  eid             varchar(36)			NOT NULL	     COMMENT'所属组织',
	id				      varchar(36)			NOT NULL	     COMMENT'内码',
	name			      varchar(100)		  NOT NULL      COMMENT'部门名称',
	code			      varchar(36)			NOT NULL	     COMMENT'部门邀请码',
	invite_link		  varchar(500)		  DEFAULT NULL COMMENT'部门邀请链接',
	parent_id		    varchar(36)			NOT NULL	     COMMENT'上级编码',
	create_userid	  varchar(36) 		  DEFAULT NULL COMMENT'创建人ID',
	created_time	  datetime 			    DEFAULT NULL COMMENT'创建日期',
	update_userid 	varchar(36) 		  DEFAULT NULL COMMENT'更新人ID',
	updated_time 	  datetime 			    DEFAULT NULL COMMENT'更新时间',
	CONSTRAINT PK_SYS_DEPARTMENT PRIMARY KEY (id)
);

/*--2.3.员工表 */
CREATE TABLE t_sys_enterprise_user (
	eid             varchar(36)			NOT NULL	     COMMENT'所属组织',
	depart_id       varchar(36)			NOT NULL	     COMMENT'所属部门',
	user_id         varchar(36) 		  NOT NULL	     COMMENT'用户编码',
	post			      varchar(50)			DEFAULT NULL COMMENT'职务',
	position		    varchar(50)			DEFAULT NULL COMMENT'职称',
	allow_size		  bigint	 			    DEFAULT 0	   COMMENT'允许上传文件大小(默认204800kb)',
	make_size		    bigint	 			    DEFAULT 0	   COMMENT'已上传文件大小',
	integral		    int	 				      DEFAULT 0	   COMMENT'用户总积分',
	reg_status		  int					      DEFAULT 0	   COMMENT'是否注册（0未注册-导入，1已注册）',
	type			      int					      DEFAULT 0	   COMMENT'用户类型（0个人用户，1企业用户）',
	status			    int					      DEFAULT 0	   COMMENT'用户状态（0表示未启用，1表示启用）',
	created_time	  datetime			    DEFAULT NULL COMMENT'注册时间',
	modifyd_time	  datetime			    DEFAULT NULL COMMENT'最后修改时间',
  	constraint PK_SYS_ENTERPRISE_USER primary key (eid, user_id)
);

/*--2.4.系统菜单表  */
create table t_sys_menu(
	id				      varchar(36)			NOT NULL	 COMMENT'菜单编码',
	name			      varchar(100)		  NOT NULL	 COMMENT'菜单名称',
	parent_id		    varchar(36)			NOT NULL	 COMMENT'上级编码',
	link_url		    varchar(100)		  DEFAULT NULL COMMENT'连接路径',
	pic_url			    varchar(100)		  DEFAULT NULL COMMENT'图标路径',
	type 			      int					      DEFAULT 0 	 COMMENT'类型 0菜单 1按钮',
	position		    int					      DEFAULT 0	 COMMENT'显示顺序',
	memo			      varchar(500)		  DEFAULT NULL COMMENT'描述',
	note			      varchar(500)		  DEFAULT NULL COMMENT'备注(注意事项)',
	constraint PK_SYS_MENU primary key (id)
);

/*--2.5.用户角色 */
CREATE TABLE t_sys_role(
	eid             varchar(36)			NOT NULL	 COMMENT'所属组织',
	id          	  varchar(36)			NOT NULL	 COMMENT'角色编码',
	name			      varchar(100)		  NOT NULL	 COMMENT'角色名称',
	memo			      varchar(500)		  DEFAULT NULL COMMENT'备注',
	constraint PK_SYS_ROLE primary key (eid, id)
);

/*--2.6.角色权限表 */
create table t_sys_role_power(
	eid             varchar(36)			NOT NULL	 COMMENT'所属组织',
	role_id			    varchar(36)			NOT NULL	 COMMENT'角色编号',
	menu_id			    varchar(36)			NOT NULL	 COMMENT'菜单编号'
	constraint PK_SYS_ROLE_POWER primary key (eid,role_id,menu_id)
);

/*--2.7.用户表 */
CREATE TABLE t_sys_user (
	id          	  varchar(36) 		  NOT NULL	 COMMENT'用户编码',
  tele_phone   	  varchar(50)			DEFAULT NULL COMMENT'手机号码',
  real_name		    varchar(50)			DEFAULT NULL COMMENT'用户真名',
  password    	  varchar(50)			DEFAULT NULL COMMENT'用户密码',
  email       	  varchar(50)			DEFAULT NULL COMMENT'邮箱',
	age       		  datetime			    DEFAULT NULL COMMENT'出生日期',
	sex				      int					      DEFAULT 0 	 COMMENT'性别(0男1女)',
	head_icon   	  varchar(255)		  DEFAULT NULL			COMMENT'用户头像',
	education		    varchar(50)			DEFAULT NULL COMMENT'学历',
	degree			    varchar(50)			DEFAULT NULL COMMENT'学位',
	created_time	  datetime			    DEFAULT NULL COMMENT'注册时间',
	modifyd_time	  datetime			    DEFAULT NULL COMMENT'最后修改时间',
	last_login		  datetime			    DEFAULT NULL COMMENT'最后登陆时间',
	last_eid        varchar(36)			NOT NULL	 COMMENT'最后一次登陆组织',
	memo			      varchar(500)		  DEFAULT NULL COMMENT'备注',
	wx_open_id		  varchar(100)		  DEFAULT NULL COMMENT'映射微信OpenID',
	ali_user_id		  varchar(100)		  DEFAULT NULL COMMENT'映射支付宝UserID',
  constraint PK_SYS_USER primary key (id)
);

/*--2.8.用户角色表 */
create table t_sys_user_power (
	eid             varchar(36)			NOT NULL	 COMMENT'所属组织',
	user_id			    varchar(36)			NOT NULL	 COMMENT'用户编号',
	role_id			    varchar(36)			NOT NULL	 COMMENT'角色编号',
	constraint PK_SYS_USERPOWER primary key (eid, user_id, role_id)
);
 
/*--2.9.用户日志表  */
create table t_sys_log(
	eid             varchar(36)			NOT NULL	 COMMENT'所属组织', 
	user_id      	  varchar(36)			NOT NULL	 COMMENT'用户编码',
	logdt			      datetime			    NOT NULL	 COMMENT'创建时间',
	service		    	varchar(50)   		NOT NULL	 COMMENT'调用接口',
	level	    	    int					      DEFAULT NULL COMMENT'日志等级',
	content     	  varchar(500)  		DEFAULT NULL COMMENT'接口说明',
	spend_time		  int			 		      DEFAULT NULL COMMENT'花费时间'
);