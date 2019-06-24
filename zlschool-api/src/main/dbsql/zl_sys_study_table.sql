DROP TABLE IF EXISTS `t_zl_message`;				      /*--消息管理(最新公告/培训活动)*/
DROP TABLE IF EXISTS `t_zl_home`;				          /*--APP首页滚动图片管理*/
DROP TABLE IF EXISTS `t_zl_label`;					      /*--标签管理*/
DROP TABLE IF EXISTS `t_zl_label_detail`;			    /*--拥有标签详情*/
DROP TABLE IF EXISTS `t_zl_check_info`;				    /*--综合考核管理(月度/年度)*/
DROP TABLE IF EXISTS `t_zl_check_detail`;			    /*--综合考核详情(每个人)*/
DROP TABLE IF EXISTS `t_zl_jifen_activity`;			  /*--积分获取详情*/
DROP TABLE IF EXISTS `t_certificate_type`;			  /*--证书类型*/
DROP TABLE IF EXISTS `t_certificate`;				      /*--证书中心*/
DROP TABLE IF EXISTS `t_course_type`;				      /*--课程类型*/
DROP TABLE IF EXISTS `t_course_under`;				    /*--线下课程*/
DROP TABLE IF EXISTS `t_course_under_activity`;	  /*--线下课程活动*/
DROP TABLE IF EXISTS `t_course_online`;				    /*--线上课程*/
DROP TABLE IF EXISTS `t_course_online_activity`;	/*--线上课程活动*/
DROP TABLE IF EXISTS `t_train`;						        /*--岗位培训*/
DROP TABLE IF EXISTS `t_train_task`;				      /*--岗位培训任务详情*/
DROP TABLE IF EXISTS `t_train_file`;				      /*--岗位培训资料*/
DROP TABLE IF EXISTS `t_train_activity`;			    /*--岗位培训活动*/
DROP TABLE IF EXISTS `t_paper`;						        /*--试卷*/
DROP TABLE IF EXISTS `t_paper_type`;			        /*--试卷类型*/
DROP TABLE IF EXISTS `t_paper_question`;			    /*--试卷题目中间表*/
DROP TABLE IF EXISTS `t_question_info`;				    /*--题库题目*/
DROP TABLE IF EXISTS `t_question_options`;			  /*--题库题目选项*/
DROP TABLE IF EXISTS `t_exam_online`;				      /*--线上考试情况*/
DROP TABLE IF EXISTS `t_exam_online_detail`;		  /*--线上考试作答详情*/
DROP TABLE IF EXISTS `t_exam_under`;				      /*--线下考试情况*/
DROP TABLE IF EXISTS `t_exam_under_detail`;			  /*--线下考试详情*/
DROP TABLE IF EXISTS `t_groups_community`;			  /*--社区小组*/
DROP TABLE IF EXISTS `t_groups_user`;				      /*--小组-组员中间表*/
DROP TABLE IF EXISTS `t_groups_topic`;				    /*--社区话题*/
DROP TABLE IF EXISTS `t_groups_activity`;			    /*--社区活动情况*/

CREATE TABLE `t_zl_message` (
	`eid` 				  varchar(36) 	  NOT NULL 	    COMMENT '企业编码',
	`id` 				    varchar(36) 	  NOT NULL 	    COMMENT '消息编码',
	`title` 			  varchar(255) 	DEFAULT NULL  COMMENT '标题',
	`classname` 		varchar(255) 	DEFAULT NULL  COMMENT '类型名称',
	`describe` 			varchar(500) 	DEFAULT NULL  COMMENT '描述',
	`src` 				  varchar(255) 	DEFAULT NULL  COMMENT '图片(图标)',
	`content` 			longtext 		    DEFAULT NULL  COMMENT '内容',
	`type` 				  int 			      DEFAULT 0 	    COMMENT '消息类型(1通知;2培训)',
	`create_user` 	varchar(36) 	  DEFAULT NULL  COMMENT '创建人ID',
	`created_time` 	datetime 		    DEFAULT NULL  COMMENT '创建日期',
	`update_user` 	varchar(36) 	  DEFAULT NULL  COMMENT '更新人ID',
	`updated_time` 	datetime 		    DEFAULT NULL  COMMENT '更新时间',
	`memo` 				  varchar(500) 	DEFAULT NULL  COMMENT '备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息管理';

CREATE TABLE `t_zl_home` (
	`eid` 				  varchar(36) 	  NOT NULL 	    COMMENT '企业编码',
	`id` 				    varchar(36) 	  NOT NULL 	    COMMENT '图片编码',
	`name` 			    varchar(255) 	DEFAULT NULL  COMMENT '名称',
	`src` 				  varchar(255) 	DEFAULT NULL  COMMENT '图片地址',
	`state` 			  int 			      DEFAULT 0 	    COMMENT '图片状态(1:启用 2:禁用)',
	`create_user` 	varchar(36) 	  DEFAULT NULL  COMMENT '创建人ID',
	`created_time` 	datetime 		    DEFAULT NULL  COMMENT '创建日期',
	`update_user` 	varchar(36)   	DEFAULT NULL  COMMENT '更新人ID',
	`updated_time` 	datetime 		    DEFAULT NULL  COMMENT '更新时间',
	`memo` 				  varchar(500) 	DEFAULT NULL  COMMENT '备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='APP首页轮播图片管理';

CREATE TABLE `t_zl_label` (
	`eid` 				   varchar(36) 	NOT NULL 	    COMMENT '企业编码',
	`id` 				     varchar(36) 	NOT NULL 	    COMMENT '标签编码',
	`name` 				   varchar(255) 	DEFAULT NULL  COMMENT '标签名称',
	`parent_id`			 varchar(36)		NOT NULL	      COMMENT '上级编码',
	`create_user` 	 varchar(36) 	DEFAULT NULL  COMMENT '创建人ID',
	`created_time` 	 datetime 		  DEFAULT NULL  COMMENT '创建日期',
	`update_user` 	 varchar(36) 	DEFAULT NULL  COMMENT '更新人ID',
	`updated_time` 	 datetime 		  DEFAULT NULL  COMMENT '更新时间',
	`memo` 				   varchar(500) 	DEFAULT NULL  COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签管理';

CREATE TABLE `t_zl_label_detail` (
	`eid` 				varchar(36) 	NOT NULL 	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '编码',
	`lable_id` 			varchar(36) 	NOT NULL 	 COMMENT '标签编码',
	`relation_id` 		varchar(36) 	NOT NULL 	 COMMENT '关联编码',
	`type` 				int 			DEFAULT 0 	 COMMENT '消息类型(1用户;2课程类型;3题库;4试卷;5培训)',
	`create_user` 		varchar(36) 	DEFAULT NULL COMMENT '创建人ID',
	`created_time` 		datetime 		DEFAULT NULL COMMENT '创建日期',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='拥有标签详情';

CREATE TABLE `t_zl_check_info` (
	`eid` 				varchar(36) 	NOT NULL 	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '编号',
	`name` 				varchar(36) 	DEFAULT NULL COMMENT '考核名称',
	`content` 			varchar(36) 	DEFAULT NULL COMMENT '考核内容',
	`check_time` 		datetime 		DEFAULT NULL COMMENT '考核时间',
	`create_time` 	datetime 		DEFAULT NULL COMMENT '创建时间',
	`create_user` 		varchar(36) 	DEFAULT NULL COMMENT '创建人ID',
	`update_time` 		datetime 		DEFAULT NULL COMMENT '更新时间',
	`update_user` 		varchar(36) 	DEFAULT NULL COMMENT '更新人ID',
	`memo` 				   varchar(500) 	DEFAULT NULL  COMMENT '备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='综合考核管理';

CREATE TABLE `t_zl_check_detail` (
	`eid` 				varchar(36) 	NOT NULL 	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '编号',
	`check_id` 			varchar(36) 	DEFAULT NULL COMMENT '考核编号',
	`user_id` 			varchar(36) 	DEFAULT NULL COMMENT '用户编号',
	`check_score` 		int 			DEFAULT 0 	 COMMENT '考核得分',
	`total_score` 		int 			DEFAULT 0 	 COMMENT '综合得分',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='综合考核详情';

CREATE TABLE `t_zl_jifen_activity` (
	`eid` 				varchar(36) 	NOT NULL 	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '编号',
	`user_id` 			varchar(36) 	DEFAULT NULL COMMENT '用户编号',
	`type` 				int 			DEFAULT 0 	 COMMENT '积分获取途径(0技术积分1奖励积分)',
	`integral` 			int 			DEFAULT 0 	 COMMENT '积分数量',
	`plus_minus` 		int 			DEFAULT 0 	 COMMENT '增加/扣除',
	`createdt_time` 	datetime 		DEFAULT NULL COMMENT '获取时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分获取情况';

CREATE TABLE `t_certificate_type` (
	`eid` 				varchar(36) 	NOT NULL	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '证书类型编码',
	`name` 				varchar(255) 	DEFAULT NULL COMMENT '类型名称',
	`state` 			int 			DEFAULT 0 	 COMMENT '状态(0禁用;1启用)',
	`integral` 			int 			DEFAULT 0 	 COMMENT '获取积分',
	`created_time` 		datetime 		DEFAULT NULL COMMENT '创建时间',
	`create_user` 		varchar(36) 	DEFAULT NULL COMMENT '创建人ID',
	`updated_time` 		datetime 		DEFAULT NULL COMMENT '更新时间',
	`update_user` 		varchar(36) 	DEFAULT NULL COMMENT '更新人ID',
	`memo` 				varchar(500) 	DEFAULT NULL COMMENT '备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='证书类型';

CREATE TABLE `t_certificate` (
	`eid` 				varchar(36) 	NOT NULL	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '证书编码',
	`user_id` 			varchar(36) 	DEFAULT NULL COMMENT '用户ID',
	`name` 				varchar(255) 	DEFAULT NULL COMMENT '证书名称',
	`file_src` 			varchar(255) 	DEFAULT NULL COMMENT '证书路径',
	`file_size` 		bigint		 	DEFAULT 0	 COMMENT '文件大小(线上)',
	`type_id` 			varchar(36) 	DEFAULT NULL COMMENT '证书类型',
	`state` 			int 			DEFAULT 0 	 COMMENT '证书状态(1:启用 2:禁用)',
	`valid_time` 		datetime 		DEFAULT NULL COMMENT '有效期',
	`memo` 				varchar(500) 	DEFAULT NULL COMMENT '备注',
	`created_time` 		datetime 		DEFAULT NULL COMMENT '创建时间',
	`create_user` 		varchar(36) 	DEFAULT NULL COMMENT '创建人ID',
	`updated_time` 		datetime 		DEFAULT NULL COMMENT '更新时间',
	`update_user` 		varchar(36) 	DEFAULT NULL COMMENT '更新人ID',
	`pro_content` 		varchar(500) 		DEFAULT NULL COMMENT'审批内容',
	`pro_time` 		  datetime 			DEFAULT NULL COMMENT'审批时间',
	`pro_status` 		int 			DEFAULT NULL COMMENT'审批状态(1:待审核 2:审核不通过 3:审核通过)',
	`pro_userid` 		varchar(100)			DEFAULT NULL COMMENT'审批人ID',
	`pro_memo` 		varchar(500) 		DEFAULT NULL COMMENT'审批备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='证书中心';

CREATE TABLE `t_course_type` (
	`eid` 				varchar(36) 	NOT NULL 	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '课程类型编码',
	`name` 				varchar(255) 	DEFAULT NULL COMMENT '课程类型名称',
	`position`			int				DEFAULT 0 	 COMMENT '显示顺序',
	`memo` 				varchar(500) 	DEFAULT NULL COMMENT '备注',
	`created_time` 		datetime 		DEFAULT NULL COMMENT '创建时间',
	`create_user` 		varchar(36) 	DEFAULT NULL COMMENT '创建人ID',
	`updated_time` 		datetime 		DEFAULT NULL COMMENT '更新时间',
	`update_user` 		varchar(36) 	DEFAULT NULL COMMENT '更新人ID',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程类型';

CREATE TABLE `t_course_under` (
	`eid` 				varchar(36) 	NOT NULL 	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '线下课程编码',
	`name` 				varchar(255) 	DEFAULT NULL COMMENT '线下课程名称',
	`type_id` 			varchar(36) 	DEFAULT NULL COMMENT '课程类型',
	`descript` 			varchar(1000) 	DEFAULT NULL COMMENT '课程简介',
	`state` 			int 			DEFAULT 0 	 COMMENT '课程状态(1已发布;2未发布;)',
	`qrcode_src` 		varchar(255) 	DEFAULT NULL COMMENT '生成二维码路径(线下)',
	`address` 			varchar(255) 	DEFAULT NULL COMMENT '学习地址(线下)',
	`study_time` 		datetime 		DEFAULT NULL COMMENT '开始时间(线下)',
	`total_time`		int				DEFAULT 0 	 COMMENT '课程时长(分钟)',
	`position`			int				DEFAULT 0 	 COMMENT '显示顺序',
	`memo` 				varchar(500) 	DEFAULT NULL COMMENT '备注',
	`created_time` 		datetime 		DEFAULT NULL COMMENT '创建时间',
	`create_user` 		varchar(36) 	DEFAULT NULL COMMENT '创建人ID',
	`updated_time` 		datetime 		DEFAULT NULL COMMENT '更新时间',
	`update_user` 		varchar(36) 	DEFAULT NULL COMMENT '更新人ID',
	`pro_content` 		varchar(500) 		DEFAULT NULL COMMENT'审批内容',
	`pro_time` 		  datetime 			DEFAULT NULL COMMENT'审批时间',
	`pro_status` 		int 			DEFAULT NULL COMMENT'审批状态(1:待审核 2:审核不通过 3:审核通过)',
	`pro_userid` 		varchar(100)			DEFAULT NULL COMMENT'审批人ID',
	`pro_memo` 		varchar(500) 		DEFAULT NULL COMMENT'审批备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='线下课程';

CREATE TABLE `t_course_under_activity` (
	`eid` 				varchar(36) 	NOT NULL 	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '活动编码',
	`under_id`			varchar(36) 	NOT NULL 	 COMMENT '线下课程编码',
	`user_id` 			varchar(36) 	DEFAULT NULL COMMENT '用户ID',
	`spend_time` 		int			 	DEFAULT 0	 COMMENT '学习时长',
	`state` 			int 			DEFAULT 0 	 COMMENT '学习状态(1未进行;2已学完;3未学习)',
	`created_time` 		datetime 		DEFAULT NULL COMMENT '创建时间',
	`updated_time` 		datetime 		DEFAULT NULL COMMENT '更新时间',
	`memo` 				varchar(500) 	DEFAULT NULL COMMENT '备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='线下课程活动';

CREATE TABLE `t_course_online` (
	`eid` 				  varchar(36)      NOT NULL 	 COMMENT '企业编码',
	`id` 				    varchar(36) 	    NOT NULL 	 COMMENT '线上课程编码',
	`name` 				  varchar(255) 	  DEFAULT NULL COMMENT '线上课程名称',
	`type_id` 			varchar(36) 	    DEFAULT NULL COMMENT '课程类型',
	`logo_src` 			varchar(255) 	  DEFAULT NULL COMMENT 'PC图片(线上)',
	`mobile_src` 		varchar(255) 	  DEFAULT NULL COMMENT '手机图片(线上)',
	`file_type` 		int 			        DEFAULT 0 	 COMMENT '文件类型(线上)(1视频,2PDF,3EXCEL,4WORD)',
	`file_src` 			varchar(255) 	  DEFAULT NULL COMMENT '文件路径(线上)',
	`file_size` 		bigint		 	      DEFAULT 0	 COMMENT '文件大小(线上)',
	`descript` 			varchar(1000) 	  DEFAULT NULL COMMENT '课程简介',
	`state` 			  int 			        DEFAULT 0 	 COMMENT '课程状态(1已发布;2未发布;)',
	`position`			int				        DEFAULT 0 	 COMMENT '显示顺序',
	`total_time`		int				        DEFAULT 0 	 COMMENT '课程时长(分钟)',
	`isfree`		    int				        DEFAULT 0 	 COMMENT '是否免费（1是2否）',
  `isintegral`		int				        DEFAULT 0 	 COMMENT '是否可用积分兑换（1是2否）',
  `integral`			int				        DEFAULT 0 	 COMMENT '学习所需积分',
  `ismoney`		    int	              DEFAULT 0 	 COMMENT '是否可用金额兑换（1是2否）',
	`money`		      decimal(10.2)	  DEFAULT 0 	 COMMENT '学习所需金额',
	`memo`			    varchar(500) 	  DEFAULT NULL COMMENT '备注',
	`created_time` 	datetime 		      DEFAULT NULL COMMENT '创建时间',
	`create_user` 	varchar(36) 	    DEFAULT NULL COMMENT '创建人ID',
	`updated_time` 	datetime 		      DEFAULT NULL COMMENT '更新时间',
	`update_user` 	varchar(36) 	    DEFAULT NULL COMMENT '更新人ID',
  `pro_content` 	varchar(500) 		DEFAULT NULL COMMENT'审批内容',
	`pro_time` 		  datetime 			    DEFAULT NULL COMMENT'审批时间',
	`pro_status` 		int 			        DEFAULT NULL COMMENT'审批状态(1:待审核 2:审核不通过 3:审核通过)',
	`pro_userid` 		varchar(100)			DEFAULT NULL COMMENT'审批人ID',
	`pro_memo` 		  varchar(500) 		DEFAULT NULL COMMENT'审批备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='线上课程';

CREATE TABLE `t_course_online_activity` (
	`eid` 				varchar(36) 	NOT NULL 	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '活动编码',
	`online_id`			varchar(36) 	NOT NULL 	 COMMENT '线下课程编码',
	`user_id` 			varchar(36) 	NOT NULL 	 COMMENT '用户ID',
	`type` 				int 			DEFAULT 0 	 COMMENT '活动类型(1浏览;2学习;3点赞;4收藏;5评论)',
	`state` 			int 			DEFAULT 0 	 COMMENT '学习状态(1未学习; 2:进行中 3已学习)',
	`comment` 			varchar(1000) 	DEFAULT NULL COMMENT '评论内容',
	`spend_time` 		int 			DEFAULT 0	 COMMENT '学习时长',
	`created_time` 		datetime 		DEFAULT NULL COMMENT '创建时间',
	`updated_time` 		datetime 		DEFAULT NULL COMMENT '更新时间',
	`memo` 				varchar(500) 	DEFAULT NULL COMMENT '备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='线上课程活动';

CREATE TABLE `t_train` (
	`eid` 				varchar(36) 	NOT NULL	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '培训编码',
	`name`				varchar(36) 	NOT NULL 	 COMMENT '培训名称',
	`start_time` 		datetime 		DEFAULT NULL COMMENT '开始时间',
	`end_time` 			datetime 		DEFAULT NULL COMMENT '结束时间',
	`status` 			int 			DEFAULT 0	 COMMENT '状态(1已发布 2未发布)',
	`integral`			int				DEFAULT 0 	 COMMENT '培训所得积分',
	`certificate`		int				DEFAULT 0 	 COMMENT '培训所得证书',
	`position`			int				DEFAULT 0 	 COMMENT '显示顺序',
	`created_time` 		datetime 		DEFAULT NULL COMMENT '创建时间',
	`create_user` 		varchar(36) 	DEFAULT NULL COMMENT '创建人ID',
	`updated_time` 		datetime 		DEFAULT NULL COMMENT '更新时间',
	`update_user` 		varchar(36) 	DEFAULT NULL COMMENT '更新人ID',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='岗位培训';

CREATE TABLE `t_train_task` (
	`eid` 				varchar(36) 	NOT NULL 	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '任务编码',
	`train_id`			varchar(36) 	NOT NULL 	 COMMENT '关联培训编码',
	`name`				varchar(36) 	NOT NULL 	 COMMENT '任务名称',
	`relation_id`		varchar(36) 	DEFAULT NULL COMMENT '关联课程id或试卷id',
	`type`				int		 		DEFAULT 0 	 COMMENT '任务类型(1线上课程;2线下课程;3线下考试;4线上考试)',
	`position`			int				DEFAULT 0 	 COMMENT '任务顺序',
	`end_time` 			datetime	 	DEFAULT NULL COMMENT '结束时间',
	`memo` 				varchar(500) 	DEFAULT NULL COMMENT '备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='培训任务详情';

CREATE TABLE `t_train_file` (
	`eid` 				varchar(36) 	NOT NULL 	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '资料编码',
	`name`				varchar(36) 	NOT NULL 	 COMMENT '资料名称',
	`position`			int				DEFAULT 0 	 COMMENT '显示顺序',
	`train_id`			varchar(36) 	NOT NULL 	 COMMENT '关联培训id',
	`file_type` 		int 			DEFAULT 0 	 COMMENT '文件类型(1视频,2PDF,3EXCEL,4WORD)',
	`file_src` 			varchar(255) 	DEFAULT NULL COMMENT '文件路径',
	`file_size` 		bigint 			DEFAULT 0 	 COMMENT '文件大小',
	`created_time` 		datetime 		DEFAULT NULL COMMENT '创建时间',
	`create_user` 		varchar(36) 	DEFAULT NULL COMMENT '创建人ID',
	`updated_time` 		datetime 		DEFAULT NULL COMMENT '更新时间',
	`update_user` 		varchar(36) 	DEFAULT NULL COMMENT '更新人ID',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='岗位培训资料';

CREATE TABLE `t_train_activity` (
	`eid` 				varchar(36) 	NOT NULL 	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '活动编码',
	`user_id`			varchar(36) 	NOT NULL 	 COMMENT '关联用户id',
	`train_id`			varchar(36) 	NOT NULL 	 COMMENT '培训id',
	`task_id`			varchar(36) 	NOT NULL 	 COMMENT '培训任务id',
	`status`			int				DEFAULT 0 	 COMMENT '1未开始2进行中3已学完4未学习',
	`created_time` 		datetime 		DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='岗位培训活动';

CREATE TABLE `t_paper` (
	`eid` 				varchar(36) 	NOT NULL 	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '编号',
	`type_id` 			varchar(36) 	NOT NULL 	 COMMENT '试卷类型',
	`name` 				varchar(255) 	DEFAULT NULL COMMENT '试卷名称',
	`total_score` 		int 			DEFAULT 0	 COMMENT '试卷总分',
	`pass_line` 		int 			DEFAULT 0 	 COMMENT '及格线',
	`spend_time` 		int 			DEFAULT 0 	 COMMENT '考试总时长',
	`release_status` 	int 			DEFAULT 0 	 COMMENT '发布状态(1已发布2未发布)',
	`integral` 			int 			DEFAULT 0	 COMMENT '通过后可以获取的积分数量',
	`single` 			int 			DEFAULT 0 	 COMMENT '单选题数',
	`multiple` 			int 			DEFAULT 0 	 COMMENT '多选题数',
	`judge` 			int 			DEFAULT 0	 COMMENT '判断题数',
	`position` 			int 			DEFAULT 0 	 COMMENT '显示顺序',
	`created_time` 		datetime 		DEFAULT NULL COMMENT '创建时间',
	`create_user` 		varchar(36) 	DEFAULT NULL COMMENT '创建人ID',
	`updated_time` 		datetime 		DEFAULT NULL COMMENT '更新时间',
	`update_user` 		varchar(36) 	DEFAULT NULL COMMENT '更新人ID',
	`memo` 				varchar(500) 	DEFAULT NULL COMMENT '备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='试卷';

CREATE TABLE `t_paper_type` (
	`eid` 				varchar(36) 	NOT NULL 	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '类型编号',
	`name` 				varchar(255) 	DEFAULT NULL COMMENT '类型名称',
	`position` 			int 			DEFAULT 0 	 COMMENT '显示顺序',
	`created_time` 		datetime 		DEFAULT NULL COMMENT '创建时间',
	`create_user` 		varchar(36) 	DEFAULT NULL COMMENT '创建人ID',
	`updated_time` 		datetime 		DEFAULT NULL COMMENT '更新时间',
	`update_user` 		varchar(36) 	DEFAULT NULL COMMENT '更新人ID',
	`memo` 				varchar(500) 	DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='试卷类型';

CREATE TABLE `t_paper_question` (
	`eid` 				varchar(36) 	NOT NULL 	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '编号',
	`paper_id` 			varchar(36) 	NOT NULL 	 COMMENT '试卷编号',
	`question_id` 		varchar(36)  	NOT NULL 	 COMMENT '问题编号',
	`position` 			int 			DEFAULT 0 	 COMMENT '显示顺序',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='试卷题目中间表';

CREATE TABLE `t_question_info` (
	`eid` 				varchar(36) 	NOT NULL	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '题目编码',
	`name`				varchar(500) 	DEFAULT NULL COMMENT '题目内容',
	`type`				int		 		DEFAULT 0 	 COMMENT '题型(1单选题2多选题3判断题)',
	`image`				varchar(255) 	NOT NULL 	 COMMENT '题目图片路径',
	`score` 			int 			DEFAULT 0 	 COMMENT '分值',
	`answer`			varchar(36) 	NOT NULL 	 COMMENT '正确答案选项(ABCDE YES NO)',
	`remake` 			varchar(1000) 	DEFAULT NULL COMMENT '解析',
	`created_time` 		datetime 		DEFAULT NULL COMMENT '创建时间',
	`create_user` 		varchar(36) 	DEFAULT NULL COMMENT '创建人ID',
	`updated_time` 		datetime 		DEFAULT NULL COMMENT '更新时间',
	`update_user` 		varchar(36) 	DEFAULT NULL COMMENT '更新人ID',
	`memo` 				varchar(500) 	DEFAULT NULL COMMENT '备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='题目';

CREATE TABLE `t_question_options` (
	`eid` 				varchar(36) 	NOT NULL 	 COMMENT '企业编码',
	`id` 				varchar(32) 	NOT NULL 	 COMMENT '选项编号',
	`question_id` 		varchar(32) 	NOT NULL 	 COMMENT '关联问题id',
	`options_id` 		varchar(16) 	DEFAULT NULL COMMENT '选项号ABCDE',
	`content` 			varchar(1000)	DEFAULT NULL COMMENT '选项内容',
	`position` 			int 			DEFAULT 0 	 COMMENT '显示顺序',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='题目答案选项';

CREATE TABLE `t_exam_online` (
	`eid` 				varchar(36) 	NOT NULL 	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '编号',
	`user_id` 			varchar(36) 	NOT NULL 	 COMMENT '用户编码',
	`paper_id` 			varchar(36) 	NOT NULL 	 COMMENT '试卷ID',
	`start_time` 		datetime 		DEFAULT NULL COMMENT '开始时间',
	`end_time` 			datetime 		DEFAULT NULL COMMENT '结束时间',
	`total_score` 		int				DEFAULT NULL COMMENT '得分',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='线上考试情况表';

CREATE TABLE `t_exam_online_detail` (
	`eid` 				varchar(36) 	NOT NULL 	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '编号',
	`user_id` 			varchar(36) 	DEFAULT NULL COMMENT '用户编号',
	`paper_id` 			varchar(36) 	DEFAULT NULL COMMENT '试卷编号',
	`question_id` 		varchar(36) 	DEFAULT NULL COMMENT '题目编号',
	`answer` 			varchar(36) 	DEFAULT NULL COMMENT '用户输入答案(ABCDE YES NO)',
	`correct` 			int 			DEFAULT 0 	 COMMENT '是否正确(0不正确 1正确)',
	`score` 			int 			DEFAULT 0 	 COMMENT '本题得分',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户考试作答详情';

CREATE TABLE `t_exam_under` (
	`eid` 				varchar(36) 	NOT NULL 	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '编号',
	`name` 				varchar(255) 	DEFAULT NULL COMMENT '线下考试名称',
	`address` 			varchar(255) 	DEFAULT NULL COMMENT '学习地址(线下)',
	`study_time` 		datetime 		DEFAULT NULL COMMENT '开始时间(线下)',
	`total_time`		int				DEFAULT 0 	 COMMENT '考试时长(分钟)',
	`descript` 			varchar(1000) 	DEFAULT NULL COMMENT '考试简介',
	`state` 			int 			DEFAULT 0 	 COMMENT '考试状态(1已发布;2未发布)',
	`qrcode_src` 		varchar(255) 	DEFAULT NULL COMMENT '生成二维码路径(线下)',
	`position`			int				DEFAULT 0 	 COMMENT '显示顺序',
	`memo` 				varchar(500) 	DEFAULT NULL COMMENT '备注',
	`createdt_time` 	datetime 		DEFAULT NULL COMMENT '创建时间',
	`create_user` 		varchar(36) 	DEFAULT NULL COMMENT '创建人ID',
	`updated_time` 		datetime 		DEFAULT NULL COMMENT '更新时间',
	`update_user` 		varchar(36) 	DEFAULT NULL COMMENT '更新人ID',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='线下考试任务表';

CREATE TABLE `t_exam_under_detail` (
	`eid` 				varchar(36) 	NOT NULL 	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '编号',
	`under_id` 			varchar(36) 	DEFAULT NULL COMMENT '线下考试编号',
	`user_id` 			varchar(36) 	DEFAULT NULL COMMENT '用户编号',
	`spend_time` 		int			 	DEFAULT 0 	 COMMENT '学习时长',
	`state` 			int 			DEFAULT 0 	 COMMENT '学习状态(1未进行;2已完成;3未考试)',
	`total_score` 		int 			DEFAULT 0 	 COMMENT '得分',
	`created_time` 		datetime 		DEFAULT NULL COMMENT '创建时间',
	`updated_time` 		datetime 		DEFAULT NULL COMMENT '更新时间',
	`memo` 				varchar(500) 	DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='线下考试详情';

CREATE TABLE `t_groups_community` (
	`eid` 				varchar(36) 	NOT NULL	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '小组编号',
	`name` 				varchar(36) 	DEFAULT NULL COMMENT '小组名称',
	`src` 				varchar(255) 	DEFAULT NULL COMMENT '图片(图标)',
	`state` 			int 			DEFAULT 0 	 COMMENT '小组状态(0禁用;1启用;)',
	`createdt_time` 	datetime 		DEFAULT NULL COMMENT '创建时间',
	`create_user` 		varchar(36) 	DEFAULT NULL COMMENT '创建人ID',
	`updated_time` 		datetime 		DEFAULT NULL COMMENT '更新时间',
	`update_user` 		varchar(36) 	DEFAULT NULL COMMENT '更新人ID',
	`memo` 				varchar(500) 	DEFAULT NULL COMMENT '备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='社区小组';

CREATE TABLE `t_groups_user` (
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '编号',
	`user_id` 			varchar(36) 	NOT NULL  	 COMMENT '组员编号',
	`groups_id` 		varchar(36) 	NOT NULL  	 COMMENT '小组编号',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='小组-组员中间表';

CREATE TABLE `t_groups_topic` (
	`eid` 				varchar(36) 	NOT NULL 	 COMMENT '企业编码',
	`id` 				varchar(36) 	NOT NULL 	 COMMENT '编号',
	`groups_id` 		varchar(36) 	DEFAULT NULL COMMENT '小组编号',
	`name` 				varchar(36) 	DEFAULT NULL COMMENT '话题名称',
	`descript` 			varchar(1000) 	DEFAULT NULL COMMENT '话题简介',
	`content` 			longtext	 	DEFAULT NULL COMMENT '话题内容',
	`state` 			int 			DEFAULT 0 	 COMMENT '话题状态(0禁用;1启用;)',
	`createdt_time` 	datetime 		DEFAULT NULL COMMENT '创建时间',
	`create_user` 		varchar(36) 	DEFAULT NULL COMMENT '创建人ID',
	`memo` 				varchar(500) 	DEFAULT NULL COMMENT '备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='社区话题';

CREATE TABLE `t_groups_activity` (
  `eid` 				varchar(36) 	NOT NULL 	 COMMENT '企业编码',
  `id` 					varchar(36) 	NOT NULL 	 COMMENT '活动编码',
  `user_id`				varchar(36) 	DEFAULT NULL COMMENT '关联用户id',
  `topic_id` 			varchar(36) 	DEFAULT NULL COMMENT '话题编号',
  `type` 				int 			DEFAULT 0 	 COMMENT '活动类型(1浏览;2点赞;3评论)',
  `comment` 			varchar(1000) 	DEFAULT NULL COMMENT '评论内容',
  `created_time` 		datetime 		DEFAULT NULL COMMENT '创建时间(评论时间)',
  `updated_time` 		datetime 		DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='话题活动情况';
