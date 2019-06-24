/*--参数配置表*/
delete from t_sys_param;
INSERT INTO `t_sys_param` VALUES ('00', '00', 'Employee', '01,0103', '员工所拥有的权限');
INSERT INTO `t_sys_param` VALUES ('00', '00', 'EnterpriseAdmin', '01,0101,010101,010102,010103,0102,0103', '企业管理员所拥有的权限');
INSERT INTO `t_sys_param` VALUES ('00', '00', 'qiniuAccessKey', '2zASCnOlLvBBBwIyiVwJmP9Nu04zn7NB2bnc7fhO', '七牛云的accessKey');
INSERT INTO `t_sys_param` VALUES ('00', '00', 'QiniuBucket', 'zlschool', '七牛云存储空间名称');
INSERT INTO `t_sys_param` VALUES ('00', '00', 'QiniuSecretKey', 'eNOUTXmCkzvi9xDNd2bGq4qW_gaG1s0T4Ve2lm7P', '七牛云的secretKey');
INSERT INTO `t_sys_param` VALUES ('00', '00', 'QiniuUrl', 'https://zlres.baoluaninfo.com', '七牛云地址');
INSERT INTO `t_sys_param` VALUES ('00', '00', 'QiniuZone', '0', '七牛云对应的区域: 0->华东, 1->??, 2->华南');

/*--1.企业表 默认总企业*/
delete from t_sys_enterprise;
insert t_sys_enterprise(id,name,code,parent_id,allow_size,allow_user,validate,status,create_userid,created_time)
values('01', '南京宝銮信息技术有限公司', '0001', 'root', 20480000, 1000,'2500-12-31',3,'000000','2019-05-23');
insert t_sys_enterprise(id,name,code,parent_id,allow_size,allow_user,validate,status,create_userid,created_time)
values('0101', 'A部门', '0002', '01', 20480000, 1000,'2500-12-31',3,'000000','2019-05-23');
insert t_sys_enterprise(id,name,code,parent_id,allow_size,allow_user,validate,status,create_userid,created_time)
values('0102', 'B部门', '0003', '01', 20480000, 1000,'2500-12-31',3,'000000','2019-05-23');

/*--2.用户表 默认超级管理员*/
delete from t_sys_user;
insert t_sys_user(id,tele_phone,real_name,password,created_time,last_eid)
values('000001', '18205094262', '超级管理员', '18205094262', '2019-05-31', '01');

/*--3.角色表 默认超级管理员角色*/
delete from t_sys_role;
insert t_sys_role(eid, id, name, memo) values('00', '000000', '个人用户', '');
insert t_sys_role(eid, id, name, memo) values('01', '000001', '超级管理员', '');
insert t_sys_role(eid, id, name, memo) values('01', '000002', '企业管理员', '');
insert t_sys_role(eid, id, name, memo) values('01', '000003', '员工', '');

/*--4.企业用户表*/
delete from t_sys_enterprise_user;
insert t_sys_enterprise_user(eid, depart_id, user_id, allow_size, make_size, status, created_time)
values('01', '01', '000001', 204800, 0, 1, '2019-05-31');

/*--5.用户角色表*/
delete from t_sys_user_power;
insert t_sys_user_power(eid, user_id, role_id)
values('01', '000001', '000001');

/*--6.系统菜单表  */
delete from t_sys_menu;
insert t_sys_menu(id, name, parent_id, link_url, type, position) values('01',   '系统管理', 'root', '',0, 1);
insert t_sys_menu(id, name, parent_id, link_url, type, position) values('0101', '组织管理', '01',   'zl/system/user-list', 0, 1);
insert t_sys_menu(id, name, parent_id, link_url, type, position) values('010101', '新增',   '0101', '', 1, 1);
insert t_sys_menu(id, name, parent_id, link_url, type, position) values('010102', '修改',   '0101', '', 1, 1);
insert t_sys_menu(id, name, parent_id, link_url, type, position) values('010103', '删除',   '0101', '', 1, 1);
insert t_sys_menu(id, name, parent_id, link_url, type, position) values('0102', '角色管理', '01',   'zl/system/role-list', 0, 2);
insert t_sys_menu(id, name, parent_id, link_url, type, position) values('0103', '日志管理', '01',   'zl/system/log-list',  0, 3);

/*--7.角色权限表  */
delete from t_sys_role_power;
insert t_sys_role_power(eid, role_id, menu_id) values('01', '000001', '01');
insert t_sys_role_power(eid, role_id, menu_id) values('01', '000001', '0101');
insert t_sys_role_power(eid, role_id, menu_id) values('01', '000001', '010101');
insert t_sys_role_power(eid, role_id, menu_id) values('01', '000001', '010102');
insert t_sys_role_power(eid, role_id, menu_id) values('01', '000001', '010103');
insert t_sys_role_power(eid, role_id, menu_id) values('01', '000001', '0102');
insert t_sys_role_power(eid, role_id, menu_id) values('01', '000001', '0103');

/*--8.证书类型表 默认五类证书*/
INSERT INTO `t_certificate_type` VALUES ('00', '000001', '学历证书', '1', '5');
INSERT INTO `t_certificate_type` VALUES ('00', '000002', '学位证书', '1', '5');
INSERT INTO `t_certificate_type` VALUES ('00', '000003', '职称证书', '1', '5');
INSERT INTO `t_certificate_type` VALUES ('00', '000004', '从业资格证书', '1', '5');
INSERT INTO `t_certificate_type` VALUES ('00', '000005', '专业工作年限', '1', '5');