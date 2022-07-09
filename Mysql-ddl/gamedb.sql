/*
 Navicat Premium Data Transfer

 Source Server         : connection1
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : gamedb

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 09/07/2022 16:50:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart_item
-- ----------------------------
DROP TABLE IF EXISTS `cart_item`;
CREATE TABLE `cart_item`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `game` int NULL DEFAULT NULL,
  `userBean` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_cart_game`(`game`) USING BTREE,
  INDEX `FK_cart_user`(`userBean`) USING BTREE,
  CONSTRAINT `FK_cart_game` FOREIGN KEY (`game`) REFERENCES `game` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_cart_user` FOREIGN KEY (`userBean`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 273 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart_item
-- ----------------------------

-- ----------------------------
-- Table structure for game
-- ----------------------------
DROP TABLE IF EXISTS `game`;
CREATE TABLE `game`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `gameImg` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gameName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double(10, 2) NULL DEFAULT NULL,
  `company` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `saleCount` int NULL DEFAULT NULL,
  `description` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `detailGameImg` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gameType` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of game
-- ----------------------------
INSERT INTO `game` VALUES (12, 'darksouls3.jpg', '黑暗之魂3', 299.00, 'FromSoftware', 10012, '当火渐熄，世界趋于毁灭，您将再踏上面临更多磨难、大量敌人与难关的旅途。玩家将沉浸在史诗氛围的世界之中，感受更快速的游玩节奏与棘手的战斗强度带来的黑暗气息。不论新手或是老玩家，都将因著名的游戏体验、实境般的游戏画面为之着迷。\n如今仅剩余火尚存……作好准备，再次拥抱黑暗！', 'darksouls3_detail.jpg', 'soulLike');
INSERT INTO `game` VALUES (13, 'darksouls2.jpg', '黑暗之魂2', 299.00, 'FromSoftware', 10015, 'FROM SOFTWARE隆重呈獻，繼2011年DARK SOULS後，萬眾矚目的系列續作DARK SOULS II正式推出！獨特的動作RPG玩法，加上超乎人智的艱困挑戰，將引領玩家進入遊戲的黑暗世界中，感受前所未有的戰慄體驗。 Dark Souls II將維持系列一貫灰暗晦澀的故事，並於單人劇情及多人遊戲中加入更多創新要素，帶來更加扣人心弦的遊戲歷程。', 'darksouls2_detail.jpg', 'soulLike');
INSERT INTO `game` VALUES (14, 'darksouls.webp', '黑暗之魂', 299.00, 'FromSoftware', 10010, '《黑暗之魂》是一款设定在充满暗黑奇幻元素世界的全新动作RPG游戏。刺激的地下城，激烈地怪物战斗和突破性的在线元素构成了一款真正独特的RPG体验。《黑暗之魂》用无缝连接的世界设定打破了阻隔，激发玩家探险游戏世界和创造独特的游戏体验。当玩家碰上强大的敌人，探索出全新的地方，这一准确的挑战构建系统和奖励机制将带来很大的成就感。', 'darksouls_detail.jpg', 'soulLike');
INSERT INTO `game` VALUES (15, 'sekiro.jpeg', '只狼：影逝二度', 299.00, 'FromSoftware', 10009, '在《Sekiro: Shadows Die Twice》中你是“独臂之狼”，一个名誉不再、伤痕累累的忍者，一个从死亡边缘捡回一命的战士。你效忠守护继承古老血统的年轻皇子，与危险的苇名一族以及众多凶恶之徒为敌。年轻的皇子被抓走后，为挽回荣誉，你将不畏死亡，踏上危机四伏的征程。', 'sekiro_detail.webp', 'soulLike');
INSERT INTO `game` VALUES (16, 'forzaHorizon5.webp', '极限竞速：地平线5', 299.00, 'Xbox Game Studios', 10001, '您的终极地平线冒险正等着您展开！探索墨西哥充满活力且不断变化的开放世界景色，驾驶百辆世界级好车，享受无拘无束又有趣的驾驶体验。立即展开您的地平线冒险并将本游戏添加到您的愿望列表吧！', 'forzaHorizon5_detail.webp', 'racing');
INSERT INTO `game` VALUES (17, 'residentevil.webp', '生化危机8', 299.00, 'CAPCOM', 10001, '在《Resident Evil》系列最新游戏中展开令人毛骨悚然的绝命拚搏。 伊森一家的平静生活因克里斯·雷德菲尔德而又陷入混乱之中，他再次身陷无法逃离的梦魇中。 这款系列第8部主要作品由日益成熟的游戏引擎“RE ENGINE”创造，将带来最新生存“动作”恐怖游戏体验。', 'residentevil_detail.webp', 'survivalHorror');
INSERT INTO `game` VALUES (18, 'civilization6.webp', '文明6', 135.00, 'Firaxis Games', 4892, '《文明VI》提供了多种新方式让您与世界互动、在地图上扩张城市、发展文明，以及对抗历史上的伟大领袖，以建立起经得起时间考验的强盛文明。共有20位史上著名的领袖任君挑选，包括秦始皇。', 'civilization6_detail.webp', 'strategy');
INSERT INTO `game` VALUES (22, 'elden_ring.png', '艾尔登法环', 298.00, 'FromSoftware', 10007, '艾尔登法环是以正统黑暗奇幻世界为舞台的动作RPG游戏。 走进辽阔的场景与地下迷宫探索未知，挑战困难重重的险境，享受克服困境时的成就感吧。 不仅如此，登场角色之间的利害关系谱成的群像剧，更是不容错过。', 'EldenRing.png', 'soulLike,openWorld');
INSERT INTO `game` VALUES (23, 'deadcells.webp', '死亡细胞', 60.00, 'Motion Twin', 10005, '欢迎来到《死亡细胞》，这是一款将Roguelite与银河战士恶魔城类特点融为一炉的2D平台动作游戏。游戏中并无检查点，玩家将体验魂味战斗，一路挑战诸多守卫，在杀戮与死亡的反复轮回中探索一座房间不断变化的巨大城堡。', 'deadcells_detail.webp', 'rogueLike');
INSERT INTO `game` VALUES (24, 'crashlands.webp', '崩溃大陆', 59.00, 'Butterscotch Shenanigans', 1001, '在一场由三个包裹引发的战争中，不会说段子的快递员不是好主角。坠落在陌生的外星大陆上，唯有依靠辛勤的劳作，不懈的锻造，以及过人的“动手”能力，你才能完成使命。崩溃的沃诺普星球欢迎你！', 'crashlands_detail.jpg', 'openWorld');
INSERT INTO `game` VALUES (25, 'bloodborne.webp', '血源诅咒', 298.00, 'FromSoftware', 2004, '《血源诅咒》是由FromSoftware与索尼电脑娱乐Japan Studio合作开发的一款动作角色扮演游戏，本作被视为是《黑暗之魂》系列的精神续作，以巨大的难度和挑战性为特点。\r\n游戏的舞台在人烟罕至的山区古都“亚南”，这是个被诅咒的城市，是自古流传着怪兽瘟疫的奇特地方。玩家将扮演为了求医而来到亚南的外国冒险者，在这里与可怕强大的敌人进行拼死决斗并取得最终胜利。', 'bloodborne_detail.webp', 'soulLike');
INSERT INTO `game` VALUES (26, 'hollowknight.webp', '空洞骑士', 78.00, 'Team Cherry', 2546, '在《空洞骑士》中打造属于自己的冒险之旅！穿越一个庞大却废弃的属于昆虫与英雄的王国，开启史诗般的冒险旅程。探索幽深的洞穴，与被感染的生物战斗，结识友好又奇异的昆虫，整个游戏都是经典的手绘 2D 风格。', 'hollowknight_detail.webp', 'rogueLike');

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `game` int NULL DEFAULT NULL,
  `orderBean` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_detail_game`(`game`) USING BTREE,
  INDEX `FK_detail_order`(`orderBean`) USING BTREE,
  CONSTRAINT `FK_detail_game` FOREIGN KEY (`game`) REFERENCES `game` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_detail_order` FOREIGN KEY (`orderBean`) REFERENCES `t_order` (`orderNo`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 141 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES (138, 13, '165483164470717');
INSERT INTO `order_item` VALUES (139, 13, '165484231961817');
INSERT INTO `order_item` VALUES (140, 14, '165484231961817');
INSERT INTO `order_item` VALUES (141, 13, '165484322266217');

-- ----------------------------
-- Table structure for own_game
-- ----------------------------
DROP TABLE IF EXISTS `own_game`;
CREATE TABLE `own_game`  (
  `userId` int NOT NULL,
  `gameId` int NOT NULL,
  PRIMARY KEY (`userId`, `gameId`) USING BTREE,
  INDEX `gameId`(`gameId`) USING BTREE,
  CONSTRAINT `own_game_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `own_game_ibfk_2` FOREIGN KEY (`gameId`) REFERENCES `game` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of own_game
-- ----------------------------
INSERT INTO `own_game` VALUES (17, 13);

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `orderNo` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `orderDate` datetime NULL DEFAULT NULL,
  `orderUser` int NULL DEFAULT NULL,
  `orderMoney` double(10, 2) NULL DEFAULT NULL,
  `orderStatus` int NULL DEFAULT NULL,
  PRIMARY KEY (`orderNo`) USING BTREE,
  INDEX `FK_order_user`(`orderUser`) USING BTREE,
  CONSTRAINT `FK_order_user` FOREIGN KEY (`orderUser`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('165483164470717', '2022-06-10 11:27:25', 17, 299.00, 2);
INSERT INTO `t_order` VALUES ('165484231961817', '2022-06-10 14:25:20', 17, 598.00, 2);
INSERT INTO `t_order` VALUES ('165484322266217', '2022-06-10 14:40:23', 17, 299.00, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pwd` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` int NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uname`(`uname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (17, 'admin1', '1', '20307130140@fudan.edu.cn', 0);
INSERT INTO `user` VALUES (32, 'yhp', 'yhp', 'yhp@163.com', 1);
INSERT INTO `user` VALUES (33, 'mc', 'mc', '1', 1);
INSERT INTO `user` VALUES (38, 'Michael', '123456', 'tengfeiwangpan@163.com', 1);
INSERT INTO `user` VALUES (39, 'admin2', '2', '1658047827@qq.com', 2);
INSERT INTO `user` VALUES (40, 'testAccount', '123123', 'test@qq.com', 1);

SET FOREIGN_KEY_CHECKS = 1;
