/*
Navicat MySQL Data Transfer

Source Server         : 天下无双
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : pet_forge

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2023-05-08 22:36:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `adopt`
-- ----------------------------
DROP TABLE IF EXISTS `adopt`;
CREATE TABLE `adopt` (
  `adopt_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '领养表的id',
  `adopt_code` varchar(16) NOT NULL COMMENT '订单编号',
  `pet_id` bigint(20) NOT NULL COMMENT '领养宠物的id',
  `client_id` bigint(20) NOT NULL COMMENT '领养人id',
  `adopt_money` int(12) NOT NULL DEFAULT '0' COMMENT '领养押金',
  `adopt_info` varchar(64) DEFAULT NULL COMMENT '订单备注',
  `in_adopt` tinyint(4) NOT NULL DEFAULT '2' COMMENT '是否通过，2为待审核，1为通过，0为未通过',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间，也是领养时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '逻辑删除，默认0，填充删除日期',
  PRIMARY KEY (`adopt_id`),
  UNIQUE KEY `adopt_code` (`adopt_code`,`is_del`),
  KEY `pet_id` (`pet_id`),
  KEY `client_id` (`client_id`),
  CONSTRAINT `adopt_ibfk_1` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`pet_id`),
  CONSTRAINT `adopt_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='领养宠物订单';

-- ----------------------------
-- Records of adopt
-- ----------------------------
INSERT INTO `adopt` VALUES ('1', '22301121', '26', '1', '234', '领养三毛', '1', '2023-01-12 16:45:49', '2023-02-12 21:58:52', '0');
INSERT INTO `adopt` VALUES ('2', '22301122', '34', '3', '300', '小狐狸^_^', '1', '2023-01-12 21:38:33', '2023-01-12 21:38:33', '0');
INSERT INTO `adopt` VALUES ('3', '22301123', '22', '3', '10', '五花比四花多一花', '1', '2023-01-12 21:51:59', '2023-01-12 21:51:59', '0');
INSERT INTO `adopt` VALUES ('4', '22301194', '19', '1', '888', '喵喵喵', '0', '2023-01-19 12:12:01', '2023-01-19 12:12:01', '0');
INSERT INTO `adopt` VALUES ('5', '22301225', '28', '6', '200', '养着玩', '1', '2023-01-22 18:58:42', '2023-01-22 18:58:42', '0');
INSERT INTO `adopt` VALUES ('6', '22302136', '23', '3', '32323', '测试', '2', '2023-02-13 21:25:05', '2023-02-13 21:25:05', '0');
INSERT INTO `adopt` VALUES ('7', '22302137', '19', '2', '2333', '狸二花，名花有主', '2', '2023-02-13 21:39:15', '2023-02-18 15:14:32', '0');
INSERT INTO `adopt` VALUES ('8', '22302188', '24', '6', '233', '金毛嘿嘿嘿', '2', '2023-02-18 14:17:15', '2023-02-18 14:17:15', '0');

-- ----------------------------
-- Table structure for `appointment`
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
  `appointment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '挂号单id',
  `client_id` bigint(20) NOT NULL COMMENT '用户id',
  `pet_id` bigint(20) NOT NULL COMMENT '宠物id',
  `appointment_date` datetime NOT NULL COMMENT '就诊时间',
  `department_id` bigint(20) NOT NULL COMMENT '部门id',
  `doctor_id` bigint(20) NOT NULL COMMENT '医生id',
  `appointment_info` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '挂号简短信息',
  `is_suc` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否处理',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '逻辑删除，默认0，填充删除日期',
  PRIMARY KEY (`appointment_id`),
  KEY `fk_appointment_client` (`client_id`),
  KEY `fk_appointment_pet` (`pet_id`),
  KEY `fk_appointment_department` (`department_id`),
  KEY `fk_appointment_doctor` (`doctor_id`),
  CONSTRAINT `fk_appointment_client` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`),
  CONSTRAINT `fk_appointment_department` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`),
  CONSTRAINT `fk_appointment_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`),
  CONSTRAINT `fk_appointment_pet` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`pet_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='挂号单表';

-- ----------------------------
-- Records of appointment
-- ----------------------------
INSERT INTO `appointment` VALUES ('1', '1', '5', '2022-12-22 11:17:54', '1', '1', '严重的肛裂', '1', '2022-12-22 11:18:04', '2023-03-03 15:51:32', '0');
INSERT INTO `appointment` VALUES ('2', '4', '5', '2023-01-08 21:03:00', '4', '5', '尝试第一次挂号', '0', '2023-01-08 21:04:35', '2023-01-09 13:26:39', '0');
INSERT INTO `appointment` VALUES ('3', '3', '12', '2023-01-08 23:30:00', '8', '4', '熬夜写网页', '1', '2023-01-08 23:31:23', '2023-03-08 18:22:09', '0');
INSERT INTO `appointment` VALUES ('4', '1', '3', '2023-01-16 21:24:00', '8', '4', '查看骨骼', '1', '2023-01-16 21:24:51', '2023-03-08 18:21:37', '0');
INSERT INTO `appointment` VALUES ('5', '3', '12', '2023-01-17 20:37:00', '4', '5', '测试挂号', '0', '2023-01-17 20:37:12', '2023-01-21 15:41:44', '0');
INSERT INTO `appointment` VALUES ('6', '3', '12', '2023-02-13 23:49:00', '8', '4', '熬夜写网页', '1', '2023-02-13 21:49:47', '2023-03-08 18:21:58', '0');
INSERT INTO `appointment` VALUES ('7', '3', '12', '2023-02-18 14:30:00', '8', '4', '测试挂号', '0', '2023-02-18 14:30:21', '2023-03-08 18:21:48', '0');

-- ----------------------------
-- Table structure for `client`
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `client_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `client_username` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户账号',
  `client_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '6b6864bf70c40ccbc2752cd9ef11e77b' COMMENT '用户密码',
  `client_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户姓名',
  `client_gender` tinyint(1) DEFAULT '0' COMMENT '用户性别',
  `client_tel` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户电话',
  `client_age` date DEFAULT '2001-01-03' COMMENT '用户生日',
  `client_info` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '一个新用户' COMMENT '用户简介',
  `client_photo` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'zs.jpg' COMMENT '用户照片名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '逻辑删除，默认0，填充删除日期',
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `client_username` (`client_username`,`is_del`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES ('1', 'ying', '6b6864bf70c40ccbc2752cd9ef11e77b', '莹', '0', 'sanyu0201@163.com', '2001-01-03', '来自提瓦特❥(^_-)', 'ying.jpg', '2022-12-21 19:24:32', '2023-03-02 19:52:19', '0');
INSERT INTO `client` VALUES ('2', 'lei-jun', '6b6864bf70c40ccbc2752cd9ef11e77b', '雷军', '0', 'sanyu0201@163.com', '1990-01-01', '喜欢宅家撸猫', 'lei-jun.jpg', '2023-01-01 20:03:13', '2023-01-08 16:14:02', '0');
INSERT INTO `client` VALUES ('3', 'sanyu', '6b6864bf70c40ccbc2752cd9ef11e77b', '散玉', '1', 'sanyu0201@163.com', '2000-01-01', '作者', 'san-yu.jpg', '2023-01-01 21:07:33', '2023-01-09 13:20:26', '0');
INSERT INTO `client` VALUES ('4', 'fox', '6b6864bf70c40ccbc2752cd9ef11e77b', '狐狸', '0', 'sanyu0201@163.com', '2000-01-07', '粉毛狐狸', 'ba-chong.jpg', '2023-01-07 16:01:10', '2023-01-08 16:13:26', '0');
INSERT INTO `client` VALUES ('5', 'hong', 'd7d203f6e81e3b6e26ed8e5d72c97926', '红中', '1', 'sanyu0201@163.com', '2023-01-22', '一个新用户', 'zs.jpg', '2023-01-22 14:44:24', '2023-03-02 20:35:10', '0');
INSERT INTO `client` VALUES ('6', 'bei', '96f39677a13f2756f55bda6b38659f32', '北风', '0', 'sanyu0201@163.com', '2000-01-03', '黜聪明，拙肢体，同于大道', 'zs.jpg', '2023-01-22 14:54:49', '2023-01-22 22:55:31', '0');
INSERT INTO `client` VALUES ('7', 'wang', '6b6864bf70c40ccbc2752cd9ef11e77b', '骰子', '1', 'sanyu0201@163.com', '2023-01-22', '一个新用户', 'zs.jpg', '2023-01-22 15:23:53', '2023-01-22 15:23:53', '2023-01-22 15:24:57');
INSERT INTO `client` VALUES ('9', 'wang', '6b6864bf70c40ccbc2752cd9ef11e77b', '骰子', '1', 'sanyu0201@163.com', '2023-01-22', '一个新用户', 'zs.jpg', '2023-01-22 15:30:02', '2023-03-02 20:35:01', '0');
INSERT INTO `client` VALUES ('10', 'liu666', '5eb2a76d38d58bf5ac206908c3cd722b', '六饼', '0', 'sanyu0201@163.com', '2023-01-22', '一个新用户', 'zs.jpg', '2023-01-22 15:38:26', '2023-01-22 15:38:26', '0');
INSERT INTO `client` VALUES ('11', 'wu', '3a714b07ec44c1f6eca4bb7b7bddad1f', '五饼', '1', 'sanyu0201@163.com', '2023-01-23', '一个新用户', 'zs.jpg', '2023-01-23 10:28:24', '2023-01-23 10:28:24', '0');
INSERT INTO `client` VALUES ('12', 'liu-tiao', '01fc1652d99677fdbb9c5a5b11b2376f', '六条', '1', 'sanyu0201@163.com', '2023-01-23', '666666', 'zs.jpg', '2023-01-23 11:00:28', '2023-01-23 11:00:41', '0');
INSERT INTO `client` VALUES ('13', 'ba-tiao', '6b6864bf70c40ccbc2752cd9ef11e77b', '八条', '1', 'sanyu0201@163.com', '2023-02-09', '一个新用户', 'zs.jpg', '2023-02-09 15:27:34', '2023-02-09 15:27:34', '0');

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `department_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '科室id',
  `department_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '科室名称',
  `department_info` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '科室简介',
  `department_address` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '科室地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '逻辑删除，默认0，填充删除日期',
  PRIMARY KEY (`department_id`),
  UNIQUE KEY `department_name` (`department_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '犬猫科', '主要整治猫猫狗狗', '一楼227', '2022-12-22 11:11:19', '2023-03-08 18:16:54', '0');
INSERT INTO `department` VALUES ('2', '骨科', '专治跌打损伤', '三楼238', '2023-01-03 21:00:02', '2023-03-08 18:17:03', '0');
INSERT INTO `department` VALUES ('3', '化验室', '进行化验', '三楼226', '2023-01-03 21:02:17', '2023-03-08 18:19:01', '0');
INSERT INTO `department` VALUES ('4', '美容室', '对宠物进行美容', '三楼327', '2023-01-03 21:04:50', '2023-03-08 18:17:32', '0');
INSERT INTO `department` VALUES ('8', '中兽科', '传统中医治兽', '一楼125', '2023-01-07 20:11:51', '2023-03-08 18:18:35', '0');

-- ----------------------------
-- Table structure for `doctor`
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor` (
  `doctor_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '医生id',
  `department_id` bigint(20) DEFAULT NULL COMMENT '部门id',
  `doctor_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '医生工号',
  `doctor_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '医生姓名',
  `doctor_gender` tinyint(1) NOT NULL DEFAULT '0' COMMENT '医生性别，1男0女',
  `doctor_age` date NOT NULL DEFAULT '2001-01-03' COMMENT '医生生日',
  `doctor_photo` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'zs.jpg' COMMENT '医生照片名称',
  `doctor_tel` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '医生联系方式',
  `doctor_job` varchar(16) DEFAULT '医生' COMMENT '医生职位',
  `doctor_info` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '医生简介',
  `doctor_password` varchar(32) NOT NULL DEFAULT '6b6864bf70c40ccbc2752cd9ef11e77b' COMMENT '医生密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '逻辑删除，默认0，填充删除日期',
  PRIMARY KEY (`doctor_id`),
  UNIQUE KEY `doctor_code` (`doctor_code`) USING BTREE,
  KEY `fk_doctor_department` (`department_id`),
  CONSTRAINT `fk_doctor_department` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='医生表，外键部门';

-- ----------------------------
-- Records of doctor
-- ----------------------------
INSERT INTO `doctor` VALUES ('1', '1', '32312221', '童德统', '1', '1990-10-10', 'doctor-1.jpg', '1114185977@qq.com', '副院长', '国内著名医学专家，擅长绝育', '6b6864bf70c40ccbc2752cd9ef11e77b', '2022-12-22 11:16:11', '2023-03-02 22:31:36', '0');
INSERT INTO `doctor` VALUES ('2', '4', '32301062', '爱丽丝', '0', '2003-02-01', 'doctor-2.jpg', 'mix_pet_doctor@sina.com', '麻醉科主任', '擅长麻醉', '6b6864bf70c40ccbc2752cd9ef11e77b', '2023-01-06 12:25:54', '2023-04-03 22:02:26', '0');
INSERT INTO `doctor` VALUES ('4', '8', '32301063', '汤姆', '1', '2000-01-11', 'doctor-3.jpg', '1114185977@qq.com', '医生', '擅长x光拍片', '6b6864bf70c40ccbc2752cd9ef11e77b', '2023-01-06 12:29:10', '2023-03-08 18:19:36', '0');
INSERT INTO `doctor` VALUES ('5', '4', '32301064', '张大嘴', '0', '2002-01-05', 'doctor-4.jpg', 'mix_pet_doctor@sina.com', '医生', '著名宠物医美专家', '6b6864bf70c40ccbc2752cd9ef11e77b', '2023-01-06 12:30:19', '2023-04-03 22:02:39', '0');
INSERT INTO `doctor` VALUES ('6', '3', '32301065', '李妙手', '1', '2001-01-06', '3b161abe-7142-449c-ab36-07a689da65de.jpg', '1114185977@qq.com', '医生', '国外著名医学专家', '6b6864bf70c40ccbc2752cd9ef11e77b', '2023-01-06 12:31:30', '2023-04-03 21:36:43', '0');
INSERT INTO `doctor` VALUES ('8', '2', '32301076', '李正骨', '0', '2000-01-28', '3cd8e749-e177-4e5b-846c-930efab7bd38.jpg', '1114185977@qq.com', '医生', '国内外著名骨科专家', '6b6864bf70c40ccbc2752cd9ef11e77b', '2023-01-07 16:35:23', '2023-04-03 21:35:09', '0');
INSERT INTO `doctor` VALUES ('9', '8', '32302189', '钟回春', '1', '2000-02-10', '92833192-79d4-42d6-a657-d000d8ca6295.jpg', 'mix_pet_doctor@sina.com', '医生', '多年老中医', '6b6864bf70c40ccbc2752cd9ef11e77b', '2023-02-18 14:08:38', '2023-04-03 22:02:53', '0');

-- ----------------------------
-- Table structure for `employee`
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employee_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `employee_username` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '管理员账号',
  `employee_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '6b6864bf70c40ccbc2752cd9ef11e77b' COMMENT '管理员密码',
  `employee_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '管理员姓名',
  `employee_level` int(8) NOT NULL DEFAULT '0' COMMENT '管理员等级',
  `employee_tel` varchar(12) NOT NULL COMMENT '管理员联系方式',
  `employee_photo` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'zs.jpg' COMMENT '管理员照片名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '逻辑删除，默认0，填充删除日期',
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `employee_username` (`employee_username`,`is_del`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='员工表';

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', 'admin', '6b6864bf70c40ccbc2752cd9ef11e77b', '炒鸡管理员', '6', '13882244666', 'admm.jpg', '2022-12-22 11:08:31', '2023-04-04 13:58:16', '0');
INSERT INTO `employee` VALUES ('2', 'yun', 'af2292f0a8074e588713093386058d4d', '李云', '4', '13882244666', 'doctor-1.jpg', '2023-01-05 21:34:23', '2023-03-02 23:01:21', '0');
INSERT INTO `employee` VALUES ('3', 'ra9', '6b6864bf70c40ccbc2752cd9ef11e77b', '马库斯', '2', '13882244666', 'ju-cat.jpg', '2023-01-05 22:02:30', '2023-01-12 10:36:05', '0');
INSERT INTO `employee` VALUES ('4', 'wheat', '6b6864bf70c40ccbc2752cd9ef11e77b', '作者', '4', '13882244666', 'san-yu.jpg', '2023-01-07 20:25:08', '2023-03-02 15:57:58', '0');
INSERT INTO `employee` VALUES ('5', 'zs', '6b6864bf70c40ccbc2752cd9ef11e77b', '张三', '0', '13882244666', 'zs.jpg', '2023-01-12 12:25:41', '2023-01-13 16:17:12', '0');

-- ----------------------------
-- Table structure for `foster`
-- ----------------------------
DROP TABLE IF EXISTS `foster`;
CREATE TABLE `foster` (
  `foster_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '寄养表的id',
  `foster_code` varchar(16) NOT NULL COMMENT '订单编号',
  `pet_id` bigint(20) NOT NULL COMMENT '寄养宠物的id',
  `client_id` bigint(20) NOT NULL COMMENT '寄养人id',
  `foster_term` date NOT NULL COMMENT '寄养到期时间',
  `foster_money` int(12) NOT NULL DEFAULT '0' COMMENT '寄养押金',
  `foster_info` varchar(64) DEFAULT NULL COMMENT '订单备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '逻辑删除，默认0，填充删除日期',
  PRIMARY KEY (`foster_id`),
  UNIQUE KEY `foster_code` (`foster_code`,`is_del`),
  KEY `pet_id` (`pet_id`),
  KEY `client_id` (`client_id`),
  CONSTRAINT `foster_ibfk_1` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`pet_id`),
  CONSTRAINT `foster_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='寄养表';

-- ----------------------------
-- Records of foster
-- ----------------------------
INSERT INTO `foster` VALUES ('1', '12301121', '8', '1', '2023-01-28', '100', '寄养波斯', '2023-01-12 16:44:32', '2023-01-12 21:51:29', '0');
INSERT INTO `foster` VALUES ('2', '12301122', '32', '3', '2023-01-13', '800', '好好照顾', '2023-01-12 20:04:22', '2023-01-12 20:04:22', '0');
INSERT INTO `foster` VALUES ('3', '12301123', '33', '2', '2023-07-28', '20000', '调教ing', '2023-01-12 20:08:46', '2023-01-12 21:48:02', '0');
INSERT INTO `foster` VALUES ('4', '12301124', '5', '4', '2023-07-13', '300', '', '2023-01-12 20:10:45', '2023-01-12 20:10:45', '0');
INSERT INTO `foster` VALUES ('5', '12301125', '32', '3', '2023-09-15', '200', '有事外出', '2023-01-12 21:52:28', '2023-01-24 17:59:59', '0');

-- ----------------------------
-- Table structure for `msg`
-- ----------------------------
DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg` (
  `msg_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `client_id` bigint(20) NOT NULL COMMENT '用户ID',
  `doctor_id` bigint(20) NOT NULL COMMENT '医生ID',
  `is_client` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1为用户发送，0为医生',
  `msg_content` varchar(64) DEFAULT NULL COMMENT '消息内容',
  `is_img` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为图片消息',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_del` varchar(32) NOT NULL DEFAULT '0' COMMENT '逻辑删除，默认0，填充删除日期',
  PRIMARY KEY (`msg_id`),
  KEY `client_id` (`client_id`),
  KEY `doctor_id` (`doctor_id`),
  CONSTRAINT `msg_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`),
  CONSTRAINT `msg_ibfk_2` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg
-- ----------------------------
INSERT INTO `msg` VALUES ('1', '1', '4', '1', '在吗？', '0', '2023-02-26 12:46:22', '2023-02-25 12:19:46', '0');
INSERT INTO `msg` VALUES ('2', '1', '4', '0', '你好，在的', '0', '2023-02-26 12:46:24', '2023-02-25 12:20:02', '0');
INSERT INTO `msg` VALUES ('3', '1', '2', '0', '你好，有什么需要帮助的吗？', '0', '2023-02-26 12:46:26', '2023-02-26 10:35:20', '0');
INSERT INTO `msg` VALUES ('4', '1', '4', '0', '你有什么想问的', '0', '2023-02-26 12:54:24', '2023-02-26 12:54:24', '0');
INSERT INTO `msg` VALUES ('5', '1', '4', '1', '就是那个', '0', '2023-02-26 12:57:06', '2023-02-26 12:57:06', '0');
INSERT INTO `msg` VALUES ('6', '1', '4', '1', '我家的鱼的鳞片都立起来了', '0', '2023-02-26 12:57:18', '2023-02-26 12:57:18', '0');
INSERT INTO `msg` VALUES ('7', '1', '4', '1', '昨天还好好的', '0', '2023-02-26 12:58:32', '2023-02-26 12:58:32', '0');
INSERT INTO `msg` VALUES ('8', '2', '4', '1', '你好啊', '0', '2023-03-03 21:45:15', '2023-03-03 21:45:15', '0');
INSERT INTO `msg` VALUES ('9', '1', '1', '1', '童医生，你好', '0', '2023-03-03 22:55:24', '2023-03-03 22:55:24', '0');
INSERT INTO `msg` VALUES ('10', '1', '2', '1', '暂时没有', '0', '2023-03-03 23:20:04', '2023-03-03 23:20:04', '0');
INSERT INTO `msg` VALUES ('11', '1', '4', '0', '看你的描述，可能是立鳞病', '0', '2023-03-06 13:14:24', '2023-03-06 13:14:24', '0');
INSERT INTO `msg` VALUES ('12', '1', '4', '0', '水质太差了就会这样', '0', '2023-03-06 13:15:12', '2023-03-06 13:15:12', '0');
INSERT INTO `msg` VALUES ('13', '1', '1', '0', '你好', '0', '2023-03-06 13:15:36', '2023-03-06 13:15:36', '0');
INSERT INTO `msg` VALUES ('14', '1', '1', '1', '我的鱼的鳞片为什么都竖起来了', '0', '2023-03-06 21:54:27', '2023-03-06 21:54:27', '0');
INSERT INTO `msg` VALUES ('15', '1', '1', '1', '前两天还好好的', '0', '2023-03-06 21:54:57', '2023-03-06 21:54:57', '0');
INSERT INTO `msg` VALUES ('16', '1', '1', '0', '可能染上了立鳞病', '0', '2023-03-06 21:56:26', '2023-03-06 21:56:26', '0');
INSERT INTO `msg` VALUES ('17', '1', '1', '1', '立鳞病？那是什么', '0', '2023-03-07 16:09:39', '2023-03-07 16:09:39', '0');
INSERT INTO `msg` VALUES ('18', '1', '1', '0', '其会使病鱼体表肿胀，部分鳞片向外张开使鳞片竖起。', '0', '2023-03-07 16:11:48', '2023-03-07 16:11:48', '0');
INSERT INTO `msg` VALUES ('19', '1', '1', '1', '真的吗', '0', '2023-03-07 16:12:06', '2023-03-07 16:12:06', '0');
INSERT INTO `msg` VALUES ('20', '1', '1', '1', '那怎么办啊', '0', '2023-03-07 16:14:37', '2023-03-07 16:14:37', '0');
INSERT INTO `msg` VALUES ('21', '1', '4', '1', '啊这。。。。。', '0', '2023-03-07 16:20:52', '2023-03-07 16:20:52', '0');
INSERT INTO `msg` VALUES ('22', '1', '2', '0', '好的，有需要随时叫我', '0', '2023-03-07 16:30:48', '2023-03-07 16:30:48', '0');
INSERT INTO `msg` VALUES ('23', '1', '2', '1', '❥(^_-)', '0', '2023-03-07 16:31:05', '2023-03-07 16:31:05', '0');
INSERT INTO `msg` VALUES ('24', '1', '1', '0', '你发张照片我看看', '0', '2023-03-07 17:16:24', '2023-03-07 17:16:24', '0');
INSERT INTO `msg` VALUES ('25', '1', '1', '1', '这样行吗？', '0', '2023-03-08 13:30:33', '2023-03-08 13:30:33', '0');
INSERT INTO `msg` VALUES ('26', '1', '1', '1', 'ms-img-01.png', '1', '2023-03-09 12:58:19', '2023-03-09 12:58:19', '0');
INSERT INTO `msg` VALUES ('27', '1', '1', '0', 'ms-img-02.png', '1', '2023-03-09 12:58:51', '2023-03-09 12:58:51', '0');
INSERT INTO `msg` VALUES ('28', '1', '1', '1', 'ms-img-03.png', '1', '2023-03-09 12:59:03', '2023-03-09 12:59:03', '0');

-- ----------------------------
-- Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `notice_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '公告id',
  `notice_title` varchar(64) NOT NULL COMMENT '公告标题',
  `notice_file` varchar(64) NOT NULL COMMENT '文本文件名称',
  `creat_id` bigint(20) NOT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `is_del` varchar(32) NOT NULL DEFAULT '0' COMMENT '逻辑删除，默认0，填充删除日期',
  PRIMARY KEY (`notice_id`),
  KEY `creat_id` (`creat_id`),
  KEY `update_id` (`update_id`),
  CONSTRAINT `notice_ibfk_1` FOREIGN KEY (`creat_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='公告表';

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '宠物医院开业公告', 'notice-1.txt', '1', '2023-01-02 17:22:55', '1', '2023-04-04 13:58:38', '0', '0');
INSERT INTO `notice` VALUES ('4', '论纯白', '4d7dcc2e-7465-49aa-b476-c85a255162a9.txt', '2', '2023-01-11 17:52:06', '2', '2023-01-11 17:52:58', '1', '0');
INSERT INTO `notice` VALUES ('5', '做好宠物的疾病防治', 'f91d4670-9d59-44c8-af5d-b74ef6f4dc76.txt', '2', '2023-01-11 17:53:56', '2', '2023-04-04 11:46:40', '0', '0');
INSERT INTO `notice` VALUES ('6', 'dd', 'fa9c33fc-1bb0-446f-8a76-5aed34228976.txt', '3', '2023-01-11 17:54:22', '3', null, '0', '2023-01-11 18:05:16');
INSERT INTO `notice` VALUES ('7', '开始采用宠物医院管理系统', '4b50890c-a992-4d15-b5da-1dc26306df12.txt', '3', '2023-01-11 19:08:19', '2', '2023-04-04 11:46:49', '0', '0');
INSERT INTO `notice` VALUES ('8', '加强节粮减损，杜绝粮食浪费', '9a9214e5-333c-4146-9f96-4a5c00b52215.txt', '3', '2023-01-11 19:09:20', '3', '2023-01-11 19:43:22', '0', '0');
INSERT INTO `notice` VALUES ('9', '绅士笑话集', 'af588adb-9a60-4506-b058-98012ad9ea2c.txt', '2', '2023-01-15 19:48:42', '2', '2023-01-15 19:48:42', '1', '0');

-- ----------------------------
-- Table structure for `pet`
-- ----------------------------
DROP TABLE IF EXISTS `pet`;
CREATE TABLE `pet` (
  `pet_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '宠物的id',
  `pet_name` varchar(16) NOT NULL DEFAULT '未名' COMMENT '宠物姓名',
  `pet_variety` varchar(16) NOT NULL DEFAULT '未知' COMMENT '宠物的品种',
  `pet_sex` tinyint(1) NOT NULL DEFAULT '0' COMMENT '宠物性别，0母1公',
  `pet_age` date NOT NULL DEFAULT '2001-01-03' COMMENT '宠物生日',
  `pet_status` varchar(16) DEFAULT '健康' COMMENT '宠物状态',
  `pet_info` varchar(64) DEFAULT '无' COMMENT '宠物动态',
  `client_id` bigint(20) DEFAULT NULL COMMENT '宠物主人id',
  `pet_photo` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '宠物照片名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` varchar(32) NOT NULL DEFAULT '0' COMMENT '逻辑删除，默认0，填充删除日期',
  PRIMARY KEY (`pet_id`),
  KEY `fk_pet_client` (`client_id`) USING BTREE,
  CONSTRAINT `fk_pet_client` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='宠物信息表，外键用户表';

-- ----------------------------
-- Records of pet
-- ----------------------------
INSERT INTO `pet` VALUES ('1', 'saber', '金毛犬', '0', '2005-01-13', '怀孕待产', null, null, null, '2022-11-30 15:52:47', '2022-12-01 15:52:53', '2023-01-07 19:12:53');
INSERT INTO `pet` VALUES ('2', '甘雨', '椰羊', '0', '1905-01-13', '频繁掉毛', null, null, null, '2022-11-30 15:53:07', '2022-12-01 15:53:10', '2023-01-07 19:12:55');
INSERT INTO `pet` VALUES ('3', '心海', '观赏鱼', '0', '2000-01-13', '非常健康', '心海十分开心', '1', 'f66eb858-e13c-438a-b235-8889d2d416be.png', '2022-11-30 15:55:04', '2023-04-03 22:16:54', '0');
INSERT INTO `pet` VALUES ('4', '神里凌华', '柴犬', '0', '2004-01-13', '肛裂', null, null, null, '2022-11-30 15:56:41', '2022-12-01 15:56:48', '2023-01-07 19:17:09');
INSERT INTO `pet` VALUES ('5', '雷电小兵', '吉娃娃', '1', '1990-08-09', '绝育手术', '这宠物十分开心', '4', 'a78d73bc-a557-41e0-8a8f-8ddc5c22e7ce.png', '2022-11-30 15:58:30', '2023-04-03 22:17:33', '0');
INSERT INTO `pet` VALUES ('6', '笔试测试调试', '但是', '1', '2022-12-29', '良好', null, null, null, '2022-12-23 10:55:30', '2022-12-23 10:55:30', '2023-01-18 17:31:31');
INSERT INTO `pet` VALUES ('7', '波一斯', '波斯猫', '0', '2016-12-15', '频繁掉毛', '这宠物十分开心', null, 'boss-cat.jpg', '2022-12-23 11:03:06', '2023-02-18 14:28:14', '0');
INSERT INTO `pet` VALUES ('8', '波二斯', '波斯猫', '1', '2016-12-17', '良好', '这宠物十分开心', null, 'fd1cdd8a-3411-4b95-a20d-5ad3993c94af.jpg', '2022-12-23 11:07:29', '2023-04-03 22:04:33', '0');
INSERT INTO `pet` VALUES ('9', '波三斯', '波斯猫', '0', '2013-12-23', '频繁掉毛', '这宠物十分开心', null, 'a943938f-7d21-49d3-a909-22f2c2dc2c9b.jpg', '2022-12-23 11:23:08', '2023-04-03 22:04:26', '0');
INSERT INTO `pet` VALUES ('10', '波四斯', '波斯猫', '0', '2012-12-03', '良好', '这宠物十分开心', '2', '92d90558-d92f-4dd8-bca3-5088825e0729.jpg', '2022-12-23 11:25:13', '2023-04-03 22:04:39', '0');
INSERT INTO `pet` VALUES ('11', '大橘莹', '橘猫', '0', '2007-12-23', '体重超标', '这宠物十分开心', null, 'ju-cat.jpg', '2022-12-23 11:36:09', '2023-02-18 14:28:29', '0');
INSERT INTO `pet` VALUES ('12', '二橘', '橘猫', '1', '2009-12-24', '体重超标', '这宠物十分开心', '3', '280906db-7875-44f7-babc-da106823b59e.jpg', '2022-12-23 11:40:07', '2023-04-03 22:06:22', '0');
INSERT INTO `pet` VALUES ('13', '三橘', '橘猫', '0', '2009-12-23', '良好', '这宠物十分开心', null, '42e28f7b-c1ab-49fd-a486-4f4ce35c565b.jpg', '2022-12-23 11:42:23', '2023-04-03 22:06:33', '0');
INSERT INTO `pet` VALUES ('14', '四橘', '橘猫', '1', '2010-12-23', '需要绝育', '这宠物十分开心', null, '3e56e4f8-5459-4559-85af-f7b0ecc2ff1d.jpg', '2022-12-23 11:43:21', '2023-04-03 22:06:42', '0');
INSERT INTO `pet` VALUES ('15', '五橘', '橘猫', '0', '2012-12-23', '头部肿胀', null, null, '', '2022-12-23 11:49:51', '2022-12-23 11:49:51', '2023-01-07 19:17:16');
INSERT INTO `pet` VALUES ('16', '五橘', '橘猫', '0', '2012-12-23', '头部肿胀', '它好想好想有人将它领回家', null, 'cf037563-88bd-40dd-b139-908b8ac72fbd.jpg', '2022-12-23 11:49:59', '2023-04-03 22:06:56', '0');
INSERT INTO `pet` VALUES ('17', '六橘', '橘猫', '1', '2012-12-23', '发情期', '它好想好想有人将它领回家', null, 'a6f7dc61-0f4f-47b9-bfee-535d5c6d80f6.jpg', '2022-12-23 16:34:36', '2023-04-03 22:07:47', '0');
INSERT INTO `pet` VALUES ('18', '狸大花', '狸花猫', '0', '2012-12-23', '发情期', '这宠物十分开心', null, 'li-hua.jpg', '2022-12-23 17:25:49', '2023-02-18 14:28:51', '0');
INSERT INTO `pet` VALUES ('19', '狸二花', '狸花猫', '1', '2013-12-23', '需要绝育', '这宠物十分开心', '3', 'f0050984-abe6-4091-bbbf-c34efef0a70c.jpg', '2022-12-23 17:28:29', '2023-04-03 22:09:11', '0');
INSERT INTO `pet` VALUES ('20', '狸三花', '狸花猫', '0', '2012-12-23', '发情期', '它好想好想有人将它领回家', null, '0a7107fe-bc9b-4a6a-a55e-f32b81c0aae1.jpg', '2022-12-23 18:36:17', '2023-04-03 22:09:21', '0');
INSERT INTO `pet` VALUES ('21', '狸四花', '狸花猫', '0', '2013-12-30', '需要绝育', '它好想好想有人将它领回家', null, 'a4ef72a4-c8a3-4ae9-a830-adf0861763e9.jpg', '2022-12-23 19:07:33', '2023-04-03 22:09:27', '0');
INSERT INTO `pet` VALUES ('22', '狸五花', '狸花猫', '0', '2018-12-23', '体重超标', '这宠物十分开心', null, 'ae30b79c-3ff9-4890-83ef-7c88b842128f.jpg', '2022-12-23 19:34:04', '2023-04-03 22:10:32', '0');
INSERT INTO `pet` VALUES ('23', '狸六花', '狸花猫', '0', '2022-12-24', '频繁掉毛', '它好想好想有人将它领回家', null, 'bd8ca20c-6aa7-42eb-8413-d6b041e374f0.jpg', '2022-12-23 19:37:43', '2023-04-03 22:10:40', '0');
INSERT INTO `pet` VALUES ('24', '金大毛', '金毛犬是', '0', '2012-12-24', '良好', '它好想好想有人将它领回家', null, 'jin-mao.jpg', '2022-12-24 10:51:45', '2023-01-07 18:56:24', '0');
INSERT INTO `pet` VALUES ('25', '金二毛', '金毛犬', '0', '2014-12-24', '发情期', '它好想好想有人将它领回家', null, 'c0aff136-1496-4837-a90e-39438a276852.png', '2022-12-24 11:13:54', '2023-04-03 22:11:45', '0');
INSERT INTO `pet` VALUES ('26', '金三毛', '金毛犬', '1', '2012-12-31', '发情期', '这宠物十分开心', null, 'f72b2d65-0d23-47bc-acf0-7f60be5e91e1.png', '2022-12-29 15:38:38', '2023-04-03 22:11:52', '0');
INSERT INTO `pet` VALUES ('27', '金四毛', '金毛犬', '0', '2012-12-29', '乱叫', '它好想好想有人将它领回家', null, '328d2c1b-d6e4-4554-9b80-cd15a43d7541.png', '2022-12-29 15:52:09', '2023-04-03 22:12:00', '0');
INSERT INTO `pet` VALUES ('28', '金五毛', '金毛犬', '1', '2022-12-30', '体重超标', '它好想好想有人将它领回家', '6', 'jin-mao.jpg', '2022-12-30 23:10:04', '2023-01-09 13:01:38', '0');
INSERT INTO `pet` VALUES ('29', '布大偶', '布偶猫', '0', '2012-12-30', '脱水', '它好想好想有人将它领回家', null, 'bu-ou.jpg', '2022-12-30 23:10:22', '2023-01-09 13:01:47', '0');
INSERT INTO `pet` VALUES ('30', '金七毛', '金毛犬', '0', '2022-12-30', '频繁掉毛', null, null, '', '2022-12-30 23:19:56', '2022-12-30 23:19:56', '2023-01-07 19:17:22');
INSERT INTO `pet` VALUES ('31', '布小偶', '布偶猫', '0', '2012-12-31', '频繁掉毛', '它好想好想有人将它领回家', null, '3d61f5ee-cb55-4350-8c93-e3c66b14acc2.png', '2022-12-30 23:22:07', '2023-04-03 22:12:48', '0');
INSERT INTO `pet` VALUES ('32', '布偶二', '布偶猫', '1', '2012-12-30', '良好', '这宠物十分开心', '3', '3b5d2e67-8af1-4a58-9bed-91d5876a1d97.png', '2022-12-30 23:24:18', '2023-04-03 22:12:54', '0');
INSERT INTO `pet` VALUES ('33', '八重神子', '藏狐', '0', '2013-01-01', '需要绝育', '这宠物十分开心', '2', '05b8baa0-6488-433b-848b-03bb098b256d.png', '2023-01-01 21:00:05', '2023-04-03 22:14:40', '0');
INSERT INTO `pet` VALUES ('34', '九重狐狸', '红狐狸', '0', '2013-01-10', '良好', '这宠物十分开心', null, '94af26d2-80ff-4cdf-85ab-330c9a436c3c.png', '2023-01-10 21:10:44', '2023-04-03 22:15:28', '0');
