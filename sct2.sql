/*
 Navicat Premium Data Transfer

 Source Server         : java-mysql
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : sct2

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 14/06/2022 16:28:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '14e1b600b1fd579f47433b88e8d85291', '管理员');
INSERT INTO `admin` VALUES (2, 'admin_test', '14e1b600b1fd579f47433b88e8d85291', '测试工具人');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `cId` int(11) NOT NULL AUTO_INCREMENT,
  `cName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`cId`) USING BTREE,
  INDEX `FK_Reference_1`(`tId`) USING BTREE,
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`tId`) REFERENCES `teacher` (`tId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 'Java面向对象开发', 1);
INSERT INTO `course` VALUES (2, 'html5入门', 1);
INSERT INTO `course` VALUES (3, 'javascript深入', 1);
INSERT INTO `course` VALUES (4, 'python从0到1', 2);
INSERT INTO `course` VALUES (5, 'spark大数据', 2);
INSERT INTO `course` VALUES (6, 'hadoop大数据', 3);
INSERT INTO `course` VALUES (7, 'ruby元编程', 3);
INSERT INTO `course` VALUES (8, 'jdbc与MySQL', 6);
INSERT INTO `course` VALUES (10, 'CSS从入门到精通', 6);
INSERT INTO `course` VALUES (11, '精通JPA与Hibernate', 6);
INSERT INTO `course` VALUES (12, 'c#开发', 67);

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc`  (
  `scId` int(11) NOT NULL AUTO_INCREMENT,
  `stuId` int(11) NULL DEFAULT NULL,
  `cId` int(11) NULL DEFAULT NULL,
  `score` decimal(5, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`scId`) USING BTREE,
  INDEX `FK_Reference_2`(`stuId`) USING BTREE,
  INDEX `FK_Reference_3`(`cId`) USING BTREE,
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`stuId`) REFERENCES `student` (`stuId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`cId`) REFERENCES `course` (`cId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 215 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sc
-- ----------------------------
INSERT INTO `sc` VALUES (84, 78, 3, 78.00);
INSERT INTO `sc` VALUES (85, 78, 4, 85.00);
INSERT INTO `sc` VALUES (86, 78, 5, 75.00);
INSERT INTO `sc` VALUES (87, 78, 6, 88.00);
INSERT INTO `sc` VALUES (88, 79, 3, 85.00);
INSERT INTO `sc` VALUES (89, 79, 4, 87.00);
INSERT INTO `sc` VALUES (90, 79, 5, 86.00);
INSERT INTO `sc` VALUES (91, 79, 7, 96.00);
INSERT INTO `sc` VALUES (92, 79, 8, 85.00);
INSERT INTO `sc` VALUES (93, 80, 1, 99.00);
INSERT INTO `sc` VALUES (94, 80, 3, 77.00);
INSERT INTO `sc` VALUES (95, 80, 4, 72.00);
INSERT INTO `sc` VALUES (96, 80, 5, 95.00);
INSERT INTO `sc` VALUES (97, 80, 6, 75.00);
INSERT INTO `sc` VALUES (98, 80, 7, 85.00);
INSERT INTO `sc` VALUES (99, 81, 1, 99.00);
INSERT INTO `sc` VALUES (100, 81, 2, 78.00);
INSERT INTO `sc` VALUES (101, 81, 3, 68.00);
INSERT INTO `sc` VALUES (102, 81, 4, 86.00);
INSERT INTO `sc` VALUES (103, 81, 5, 46.00);
INSERT INTO `sc` VALUES (104, 81, 6, 67.00);
INSERT INTO `sc` VALUES (105, 81, 7, 74.00);
INSERT INTO `sc` VALUES (106, 81, 8, 96.00);
INSERT INTO `sc` VALUES (107, 82, 1, 99.00);
INSERT INTO `sc` VALUES (108, 82, 2, 67.00);
INSERT INTO `sc` VALUES (109, 82, 3, 94.00);
INSERT INTO `sc` VALUES (110, 82, 4, 70.00);
INSERT INTO `sc` VALUES (111, 82, 5, 82.00);
INSERT INTO `sc` VALUES (112, 82, 6, 52.00);
INSERT INTO `sc` VALUES (113, 82, 8, 74.00);
INSERT INTO `sc` VALUES (114, 83, 1, 99.00);
INSERT INTO `sc` VALUES (115, 83, 2, 39.00);
INSERT INTO `sc` VALUES (116, 83, 4, 59.00);
INSERT INTO `sc` VALUES (117, 83, 5, 77.00);
INSERT INTO `sc` VALUES (118, 83, 7, 63.00);
INSERT INTO `sc` VALUES (119, 83, 8, 52.00);
INSERT INTO `sc` VALUES (120, 84, 1, 99.00);
INSERT INTO `sc` VALUES (121, 84, 2, 88.00);
INSERT INTO `sc` VALUES (122, 84, 3, 86.00);
INSERT INTO `sc` VALUES (123, 84, 4, 85.00);
INSERT INTO `sc` VALUES (124, 84, 5, 63.00);
INSERT INTO `sc` VALUES (125, 84, 6, 94.00);
INSERT INTO `sc` VALUES (126, 84, 8, 63.00);
INSERT INTO `sc` VALUES (127, 85, 1, 99.00);
INSERT INTO `sc` VALUES (128, 85, 3, 62.00);
INSERT INTO `sc` VALUES (129, 85, 4, 77.00);
INSERT INTO `sc` VALUES (130, 85, 6, 86.00);
INSERT INTO `sc` VALUES (131, 85, 7, 52.00);
INSERT INTO `sc` VALUES (132, 85, 8, 75.00);
INSERT INTO `sc` VALUES (133, 86, 1, 99.00);
INSERT INTO `sc` VALUES (134, 86, 2, 55.00);
INSERT INTO `sc` VALUES (135, 86, 4, 67.00);
INSERT INTO `sc` VALUES (136, 86, 5, 91.00);
INSERT INTO `sc` VALUES (137, 86, 7, 85.00);
INSERT INTO `sc` VALUES (138, 86, 8, 94.00);
INSERT INTO `sc` VALUES (139, 87, 1, 99.00);
INSERT INTO `sc` VALUES (140, 87, 2, 98.00);
INSERT INTO `sc` VALUES (141, 87, 3, 58.00);
INSERT INTO `sc` VALUES (142, 87, 4, 91.00);
INSERT INTO `sc` VALUES (143, 87, 5, 88.00);
INSERT INTO `sc` VALUES (144, 87, 6, 71.00);
INSERT INTO `sc` VALUES (145, 87, 7, 96.00);
INSERT INTO `sc` VALUES (146, 88, 1, 39.00);
INSERT INTO `sc` VALUES (147, 88, 2, 66.00);
INSERT INTO `sc` VALUES (148, 88, 3, 67.00);
INSERT INTO `sc` VALUES (149, 88, 5, 49.00);
INSERT INTO `sc` VALUES (150, 88, 6, 59.00);
INSERT INTO `sc` VALUES (151, 88, 7, 74.00);
INSERT INTO `sc` VALUES (152, 88, 8, 86.00);
INSERT INTO `sc` VALUES (153, 89, 1, 85.00);
INSERT INTO `sc` VALUES (154, 89, 2, 70.00);
INSERT INTO `sc` VALUES (155, 89, 3, 46.00);
INSERT INTO `sc` VALUES (156, 89, 4, 55.00);
INSERT INTO `sc` VALUES (157, 89, 5, 81.00);
INSERT INTO `sc` VALUES (158, 89, 6, 60.00);
INSERT INTO `sc` VALUES (159, 89, 7, 58.00);
INSERT INTO `sc` VALUES (160, 89, 8, 81.00);
INSERT INTO `sc` VALUES (161, 90, 1, 75.00);
INSERT INTO `sc` VALUES (162, 90, 2, 76.00);
INSERT INTO `sc` VALUES (163, 90, 4, 40.00);
INSERT INTO `sc` VALUES (164, 90, 5, 76.00);
INSERT INTO `sc` VALUES (165, 90, 6, 88.00);
INSERT INTO `sc` VALUES (166, 90, 7, 94.00);
INSERT INTO `sc` VALUES (167, 91, 1, 73.00);
INSERT INTO `sc` VALUES (168, 91, 2, 50.00);
INSERT INTO `sc` VALUES (169, 91, 4, 78.00);
INSERT INTO `sc` VALUES (170, 91, 5, 82.00);
INSERT INTO `sc` VALUES (171, 91, 6, 93.00);
INSERT INTO `sc` VALUES (172, 91, 7, 86.00);
INSERT INTO `sc` VALUES (173, 91, 8, 73.00);
INSERT INTO `sc` VALUES (174, 92, 1, 55.00);
INSERT INTO `sc` VALUES (175, 92, 2, 88.00);
INSERT INTO `sc` VALUES (176, 92, 3, 80.00);
INSERT INTO `sc` VALUES (177, 92, 4, 64.00);
INSERT INTO `sc` VALUES (178, 92, 5, 77.00);
INSERT INTO `sc` VALUES (179, 92, 6, 100.00);
INSERT INTO `sc` VALUES (180, 92, 7, 77.00);
INSERT INTO `sc` VALUES (181, 92, 8, 85.00);
INSERT INTO `sc` VALUES (185, 96, 1, 85.00);
INSERT INTO `sc` VALUES (186, 96, 2, 85.00);
INSERT INTO `sc` VALUES (187, 96, 3, 85.00);
INSERT INTO `sc` VALUES (188, 96, 4, 85.00);
INSERT INTO `sc` VALUES (189, 96, 5, 85.00);
INSERT INTO `sc` VALUES (190, 96, 6, 85.00);
INSERT INTO `sc` VALUES (191, 96, 7, 85.00);
INSERT INTO `sc` VALUES (192, 96, 8, 85.00);
INSERT INTO `sc` VALUES (193, 96, 10, 85.00);
INSERT INTO `sc` VALUES (194, 96, 11, 85.00);
INSERT INTO `sc` VALUES (195, 96, 12, 74.00);
INSERT INTO `sc` VALUES (196, 98, 1, 74.00);
INSERT INTO `sc` VALUES (197, 98, 2, 74.00);
INSERT INTO `sc` VALUES (198, 98, 3, 74.00);
INSERT INTO `sc` VALUES (207, 99, 1, 99.00);
INSERT INTO `sc` VALUES (208, 99, 2, 40.00);
INSERT INTO `sc` VALUES (209, 99, 3, 40.00);
INSERT INTO `sc` VALUES (210, 99, 4, 44.00);
INSERT INTO `sc` VALUES (211, 99, 5, 44.00);
INSERT INTO `sc` VALUES (212, 99, 6, 8.00);
INSERT INTO `sc` VALUES (213, 99, 7, 48.00);
INSERT INTO `sc` VALUES (214, 99, 8, 90.00);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `stuId` int(11) NOT NULL AUTO_INCREMENT,
  `stuName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stuNo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stuPwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`stuId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (78, '张三', '0001', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `student` VALUES (79, '李四', '0002', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `student` VALUES (80, '王五', '0003', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `student` VALUES (81, '王五六', '0004', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `student` VALUES (82, '王五六七', '0005', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `student` VALUES (83, '刘一', '0006', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `student` VALUES (84, '刘二', '0007', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `student` VALUES (85, '刘三', '0008', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `student` VALUES (86, '刘四', '0009', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `student` VALUES (87, '刘五', '0010', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `student` VALUES (88, '刘六', '0011', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `student` VALUES (89, '王亦', '0012', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `student` VALUES (90, '汤姆猫', '0013', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `student` VALUES (91, '汤米', '0014', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `student` VALUES (92, '沙拉', '0015', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `student` VALUES (93, '李亦', '0016', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `student` VALUES (94, '珊珊', '0017', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `student` VALUES (95, '树子', '0018', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `student` VALUES (96, '庞三', '0020', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `student` VALUES (97, '阿达', '0019', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `student` VALUES (98, '工具人', '0021', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `student` VALUES (99, '测试学生工具人2', '0022', '14e1b600b1fd579f47433b88e8d85291');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `tId` int(11) NOT NULL AUTO_INCREMENT,
  `tName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '阳羡', 'yangxian', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `teacher` VALUES (2, '曹露', 'caolu', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `teacher` VALUES (3, '莫范', 'mofan', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `teacher` VALUES (4, '陈瑞', 'chengrui', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `teacher` VALUES (5, '李所', 'lisuo', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `teacher` VALUES (6, '黄习', 'huangxi', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `teacher` VALUES (7, '陈程', 'chengcheng', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `teacher` VALUES (8, '杨人', 'yangren', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `teacher` VALUES (66, '李阿里', 'liali', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `teacher` VALUES (67, '李里', 'lili', '14e1b600b1fd579f47433b88e8d85291');

SET FOREIGN_KEY_CHECKS = 1;
