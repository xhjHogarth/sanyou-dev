/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50729
Source Host           : localhost:3306
Source Database       : sanyou

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2021-06-05 02:53:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for factory
-- ----------------------------
DROP TABLE IF EXISTS `factory`;
CREATE TABLE `factory` (
  `id` varchar(20) NOT NULL,
  `factory_name` varchar(100) NOT NULL,
  `layer` tinyint(4) DEFAULT NULL,
  `parentId` varchar(20) DEFAULT NULL,
  `delete_mark` tinyint(4) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `deletetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of factory
-- ----------------------------
INSERT INTO `factory` VALUES ('1', 'aaa', '2', '210602DK3F951DD4', '0', '2021-06-02 19:29:19', null, null);
INSERT INTO `factory` VALUES ('2105261HWC2S51P0', '金冠铜业', '1', null, '0', '2021-05-26 02:14:44', '2021-06-02 20:45:47', '2021-06-02 20:45:47');
INSERT INTO `factory` VALUES ('2105268394P0Y8M8', '杭州铜业', '1', null, '0', '2021-05-26 11:22:33', '2021-06-02 20:45:52', '2021-06-02 20:45:52');
INSERT INTO `factory` VALUES ('210602DK3F951DD4', 'xsxxx', '1', null, '0', '2021-06-02 19:04:20', '2021-06-02 20:45:54', '2021-06-02 20:45:54');
INSERT INTO `factory` VALUES ('210602F5ZC5K7DYW', 'aaaaa', '1', null, '0', '2021-06-02 19:54:56', '2021-06-02 19:54:56', null);
INSERT INTO `factory` VALUES ('210602F67XB7FBXP', '2222', '1', null, '0', '2021-06-02 19:55:51', '2021-06-02 19:55:51', null);
INSERT INTO `factory` VALUES ('210602F6D5DP18H0', 'aasdasd', '1', null, '0', '2021-06-02 19:56:25', '2021-06-02 19:56:25', null);
INSERT INTO `factory` VALUES ('210602F6G4703N54', 'saacxc', '1', null, '0', '2021-06-02 19:56:37', '2021-06-02 19:56:37', null);
INSERT INTO `factory` VALUES ('210602FCP1S8ACH0', '1111', '2', '2105261HWC2S51P0', '0', '2021-06-02 20:15:10', '2021-06-02 20:15:10', null);

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` varchar(20) NOT NULL,
  `res_code` varchar(255) NOT NULL,
  `title` varchar(100) NOT NULL,
  `layer` tinyint(4) DEFAULT NULL,
  `parent_id` varchar(64) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `deletetime` datetime DEFAULT NULL,
  `delete_mark` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('210526ANMT2MPZ2W', 'user_manage', '用户管理', '0', null, '2021-05-26 14:59:46', '2021-05-31 16:38:54', '2021-05-31 16:38:54', '0');
INSERT INTO `resource` VALUES ('210526B60RS7DSY8', 'user_manage_add', '添加用户', '2', '210526B8X5B5RXGC', '2021-05-26 15:42:52', '2021-05-31 16:39:03', '2021-05-31 16:39:03', '0');
INSERT INTO `resource` VALUES ('210526B63DGKD028', 'user_manage_delete', '删除用户', '2', '210526B8X5B5RXGC', '2021-05-26 15:43:10', '2021-05-31 16:39:03', '2021-05-31 16:39:03', '0');
INSERT INTO `resource` VALUES ('210526B664G1846W', 'user_manage_edit', '编辑用户', '2', '210526B8X5B5RXGC', '2021-05-26 15:43:27', '2021-05-31 16:39:03', '2021-05-31 16:39:03', '0');
INSERT INTO `resource` VALUES ('210526B6TWADCKKP', 'user_manage_enable', '启用/禁用用户', '2', '210526B8X5B5RXGC', '2021-05-26 15:45:21', '2021-05-31 16:39:03', '2021-05-31 16:39:03', '0');
INSERT INTO `resource` VALUES ('210526B6ZZ4F0YRP', 'user_manage_search', '查看用户', '2', '210526B8X5B5RXGC', '2021-05-26 15:45:47', '2021-05-31 16:39:03', '2021-05-31 16:39:03', '0');
INSERT INTO `resource` VALUES ('210526B8X5B5RXGC', 'user_list', '用户列表', '1', '210526ANMT2MPZ2W', '2021-05-26 15:51:30', '2021-05-31 16:39:03', '2021-05-31 16:39:03', '0');
INSERT INTO `resource` VALUES ('210526BA6WG208DP', 'user_manage_download', '用户资料下载', '2', '210526B8X5B5RXGC', '2021-05-26 15:55:32', '2021-05-31 16:39:03', '2021-05-31 16:39:03', '0');
INSERT INTO `resource` VALUES ('210526BAS9F27XAW', 'user_manage_auth', '用户设备权限', '2', '210526B8X5B5RXGC', '2021-05-26 15:57:11', '2021-05-31 16:39:03', '2021-05-31 16:39:03', '0');
INSERT INTO `resource` VALUES ('210526BBP4P7BMW0', 'factory_manage', '厂家管理', '1', '210526ANMT2MPZ2W', '2021-05-26 15:59:58', '2021-05-31 16:39:03', '2021-05-31 16:39:03', '0');
INSERT INTO `resource` VALUES ('210526BC256DHPH0', 'factory_manage_add', '添加厂家', '2', '210526BBP4P7BMW0', '2021-05-26 16:01:02', '2021-05-31 16:39:03', '2021-05-31 16:39:03', '0');
INSERT INTO `resource` VALUES ('210526BCD1SGM5P0', 'factory_manage_edit', '编辑厂家', '2', '210526BBP4P7BMW0', '2021-05-26 16:02:12', '2021-05-26 17:22:53', null, '0');
INSERT INTO `resource` VALUES ('210526BCG87BBB0H', 'factory_manage_delete', '删除厂家', '2', '210526BBP4P7BMW0', '2021-05-26 16:02:26', '2021-05-26 16:02:26', null, '0');
INSERT INTO `resource` VALUES ('210526BCPSDXGK68', 'factory_manage_search', '查看厂家', '2', '210526BBP4P7BMW0', '2021-05-26 16:03:02', '2021-05-26 16:03:02', null, '0');
INSERT INTO `resource` VALUES ('210526CGH887KT7C', 'auth_manage', '权限管理', '0', null, '2021-05-26 17:35:38', '2021-05-26 17:35:38', null, '0');
INSERT INTO `resource` VALUES ('210526CK50NMBDYW', 'role_manage', '角色管理', '1', '210526CGH887KT7C', '2021-05-26 17:40:25', '2021-05-26 17:40:25', null, '0');
INSERT INTO `resource` VALUES ('210526CNA7NZZKGC', 'resource_manage', '资源管理', '1', '210526CGH887KT7C', '2021-05-26 17:46:59', '2021-05-26 17:46:59', null, '0');
INSERT INTO `resource` VALUES ('210526CNK9N3TSCH', 'resource_manage_add', '添加资源', '2', '210526CNA7NZZKGC', '2021-05-26 17:47:45', '2021-05-26 17:47:45', null, '0');
INSERT INTO `resource` VALUES ('210526CNZNP9FT0H', 'resource_manage_edit', '更新资源', '2', '210526CNA7NZZKGC', '2021-05-26 17:48:52', '2021-05-26 17:48:52', null, '0');
INSERT INTO `resource` VALUES ('210526CP2M93K4ZC', 'resource_manage_delete', '删除资源', '2', '210526CNA7NZZKGC', '2021-05-26 17:49:10', '2021-05-26 17:49:10', null, '0');
INSERT INTO `resource` VALUES ('210526FB6N5D9Z54', 'role_manage_add', '添加角色', '2', '210526CK50NMBDYW', '2021-05-26 20:10:43', '2021-05-26 20:10:43', null, '0');
INSERT INTO `resource` VALUES ('210526FBMBTWXZF8', 'role_manage_edit', '更新角色', '2', '210526CK50NMBDYW', '2021-05-26 20:11:59', '2021-05-26 20:11:59', null, '0');
INSERT INTO `resource` VALUES ('210526FBRX6DW5GC', 'role_manage_delete', '删除角色', '2', '210526CK50NMBDYW', '2021-05-26 20:12:21', '2021-05-26 20:12:21', null, '0');
INSERT INTO `resource` VALUES ('210526FC6R5M3YA8', 'role_manage_auth', '角色分配资源', '2', '210526CK50NMBDYW', '2021-05-26 20:13:44', '2021-05-26 20:13:44', null, '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(20) NOT NULL,
  `role_code` varchar(50) NOT NULL,
  `title` varchar(50) NOT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `deletetime` datetime DEFAULT NULL,
  `delete_mark` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('210526G6A5T5WNXP', 'client', '客户', '2021-05-26 21:20:10', '2021-06-01 16:57:34', null, '0');
INSERT INTO `role` VALUES ('210531APA38M4T54', 'test', '测试', '2021-05-31 15:01:50', '2021-05-31 19:32:32', null, '0');

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `id` varchar(20) NOT NULL,
  `roleId` varchar(20) NOT NULL,
  `resourceId` varchar(20) NOT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('210601C2T0APC4X4', '210526G6A5T5WNXP', '210526FB6N5D9Z54', '2021-06-01 16:57:19');
INSERT INTO `role_resource` VALUES ('210601C2T0AZ76RP', '210526G6A5T5WNXP', '210526FBMBTWXZF8', '2021-06-01 16:57:19');
INSERT INTO `role_resource` VALUES ('210601C2T0B628M8', '210526G6A5T5WNXP', '210526FBRX6DW5GC', '2021-06-01 16:57:19');
INSERT INTO `role_resource` VALUES ('210601C2T0BCXAFW', '210526G6A5T5WNXP', '210526FC6R5M3YA8', '2021-06-01 16:57:19');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(20) NOT NULL,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `realname` varchar(50) DEFAULT NULL COMMENT '真名',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别(保密：1，男：2，女：3)',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机',
  `qq` varchar(50) DEFAULT NULL COMMENT 'qq',
  `wechat` varchar(50) DEFAULT NULL COMMENT '微信',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮件',
  `province` varchar(50) DEFAULT NULL COMMENT '省',
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  `area` varchar(50) DEFAULT NULL COMMENT '区',
  `address` varchar(255) DEFAULT NULL COMMENT '详细住址',
  `enable_mark` tinyint(4) DEFAULT '1' COMMENT '启用禁用标志',
  `delete_mark` tinyint(4) DEFAULT '0' COMMENT '删除标志',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '修改时间',
  `deletetime` datetime DEFAULT NULL COMMENT '删除时间',
  `regist_ip` varchar(50) DEFAULT NULL COMMENT '注册ip',
  `last_login_ip` varchar(50) DEFAULT NULL COMMENT '最后登录ip',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `factory_id` varchar(20) DEFAULT NULL COMMENT '厂家id',
  `sub_factory_id` varchar(20) DEFAULT NULL COMMENT '子厂家id',
  `depart` varchar(50) DEFAULT NULL COMMENT '部门',
  `position` varchar(50) DEFAULT NULL COMMENT '职位',
  `group_id` varchar(20) DEFAULT NULL COMMENT '用户组id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2105260FBK56RZTC', 'xhj', '4QrcOUm6Wau+VuBX8g+IPg==', 'asdja', '2', '21312', '的eawda', 'adadasd', 'dasda', null, null, null, '12d12dqwd', '1', '0', '2021-05-26 00:43:17', '2021-06-05 02:03:55', '2021-06-05 01:13:36', null, null, null, '210602DK3F951DD4', '1', '休假回家啊', 'ceo', '2');
INSERT INTO `user` VALUES ('210603ASC488YB7C', 'ceshi', 'xMpCOKC5I4INzFCab3WEmw==', 'hhhhhh', '1', '21312', '', '', '', null, null, null, '', '0', '0', '2021-06-03 15:08:04', '2021-06-05 02:04:16', '2021-06-05 01:13:36', null, null, null, '', '', '', '', '1');
INSERT INTO `user` VALUES ('210603ATX2HX8MK4', 'zz', 's928UC4wdmXzRsvW5SzBDQ==', '嘻嘻嘻嘻嘻', '3', '21312', '312', '321', '12313', null, null, null, '312', '0', '0', '2021-06-03 15:12:27', '2021-06-05 02:04:44', '2021-06-05 00:50:02', null, null, null, '2105261HWC2S51P0', '210602FCP1S8ACH0', '3123', '13123', null);

-- ----------------------------
-- Table structure for usergroup
-- ----------------------------
DROP TABLE IF EXISTS `usergroup`;
CREATE TABLE `usergroup` (
  `id` varchar(20) NOT NULL,
  `group_name` varchar(50) NOT NULL,
  `group_type` tinyint(4) NOT NULL,
  `group_level` tinyint(4) NOT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `deletetime` datetime DEFAULT NULL,
  `delete_mark` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usergroup
-- ----------------------------
INSERT INTO `usergroup` VALUES ('1', '超管', '1', '1', '2021-06-01 20:58:30', null, null, '0');
INSERT INTO `usergroup` VALUES ('2', '客户', '1', '1', '2021-06-01 20:58:48', '2021-06-01 23:56:52', null, '0');
INSERT INTO `usergroup` VALUES ('210601G2GMANCY3C', 'test嘻嘻嘻嘻嘻嘻嘻', '2', '1', '2021-06-01 21:08:44', '2021-06-01 23:50:54', null, '0');
INSERT INTO `usergroup` VALUES ('210601G3XRDMCFNC', 'test2shkdhaksdhakj', '2', '1', '2021-06-01 21:12:49', '2021-06-01 23:57:09', null, '0');
INSERT INTO `usergroup` VALUES ('210601G48WK6TDKP', 'test4', '2', '3', '2021-06-01 21:14:01', '2021-06-01 21:35:22', null, '0');
INSERT INTO `usergroup` VALUES ('210601G534GCK9WH', '111', '2', '1', '2021-06-01 21:16:24', '2021-06-01 21:35:20', null, '0');
INSERT INTO `usergroup` VALUES ('210601G55837CPX4', '111111', '2', '2', '2021-06-01 21:16:38', '2021-06-01 21:35:18', null, '0');
INSERT INTO `usergroup` VALUES ('210601G5778729KP', '23123', '2', '2', '2021-06-01 21:16:50', '2021-06-01 21:35:09', null, '0');
INSERT INTO `usergroup` VALUES ('210601GF9PG4CZ2W', '111', '2', '1', '2021-06-01 21:44:08', '2021-06-01 21:44:10', null, '0');
INSERT INTO `usergroup` VALUES ('210601HPZB523R40', '测试', '2', '1', '2021-06-01 23:28:07', null, null, '0');
INSERT INTO `usergroup` VALUES ('210601HRAKBG63C0', '啊啊啊', '2', '1', '2021-06-01 23:29:19', null, null, '0');
INSERT INTO `usergroup` VALUES ('210601HRZDR9846W', '啊啊是', '2', '1', '2021-06-01 23:31:07', null, null, '0');
INSERT INTO `usergroup` VALUES ('210601HTB3K7DHBC', '对格式', '2', '1', '2021-06-01 23:35:22', null, null, '0');
INSERT INTO `usergroup` VALUES ('210601HZXR0XD968', 'test夏华军', '2', '1', '2021-06-01 23:48:57', null, null, '0');
INSERT INTO `usergroup` VALUES ('3', '普通用户', '1', '2', '2021-06-01 20:59:03', null, null, '0');
INSERT INTO `usergroup` VALUES ('4', '数据上传', '1', '2', '2021-06-01 20:59:23', null, null, '0');

-- ----------------------------
-- Table structure for usergroup_role
-- ----------------------------
DROP TABLE IF EXISTS `usergroup_role`;
CREATE TABLE `usergroup_role` (
  `id` varchar(20) NOT NULL,
  `usergroupId` varchar(20) NOT NULL,
  `roleId` varchar(20) NOT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usergroup_role
-- ----------------------------
INSERT INTO `usergroup_role` VALUES ('1', '1', '210526G6A5T5WNXP', '2021-06-02 14:29:54');
INSERT INTO `usergroup_role` VALUES ('2', '1', '210531APA38M4T54', '2021-06-02 14:29:58');
INSERT INTO `usergroup_role` VALUES ('210602C59G35DNMW', '3', '210526G6A5T5WNXP', '2021-06-02 17:04:53');
INSERT INTO `usergroup_role` VALUES ('210602C5BTH63TR4', '210601G48WK6TDKP', '210531APA38M4T54', '2021-06-02 17:05:07');
INSERT INTO `usergroup_role` VALUES ('210602C9GX18DH28', '2', '210526G6A5T5WNXP', '2021-06-02 17:17:34');
INSERT INTO `usergroup_role` VALUES ('210602C9HYMB3NF8', '210601HRAKBG63C0', '210531APA38M4T54', '2021-06-02 17:17:41');
INSERT INTO `usergroup_role` VALUES ('210602C9KGBFZRS8', '210601HPZB523R40', '210526G6A5T5WNXP', '2021-06-02 17:17:45');
INSERT INTO `usergroup_role` VALUES ('210602C9KWT6A98H', '210601GF9PG4CZ2W', '210526G6A5T5WNXP', '2021-06-02 17:17:47');
INSERT INTO `usergroup_role` VALUES ('210602C9KWZAAD68', '210601GF9PG4CZ2W', '210531APA38M4T54', '2021-06-02 17:17:47');
SET FOREIGN_KEY_CHECKS=1;
