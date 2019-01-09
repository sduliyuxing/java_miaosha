-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1:3306
-- Generation Time: 2019-01-09 08:56:13
-- 服务器版本： 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `miaosha`
--

-- --------------------------------------------------------

--
-- 表的结构 `goods`
--

CREATE TABLE IF NOT EXISTS `goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `goods_title` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '商品标题',
  `goods_img` varchar(100) DEFAULT NULL,
  `goods_detail` varchar(100) DEFAULT NULL COMMENT '商品详情',
  `goods_price` decimal(10,2) DEFAULT NULL,
  `goods_stock` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='商品表' AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `goods`
--

INSERT INTO `goods` (`id`, `goods_name`, `goods_title`, `goods_img`, `goods_detail`, `goods_price`, `goods_stock`) VALUES
(1, 'iPhone', '苹果手机', 'http://localhost:8080/img/iphonex.png', NULL, '6000.00', 100),
(2, 'mate10', '华为手机', 'http://localhost:8080/img/meta10.png', NULL, '5000.00', 100);

-- --------------------------------------------------------

--
-- 表的结构 `miaosha_goods`
--

CREATE TABLE IF NOT EXISTS `miaosha_goods` (
  `id` int(20) NOT NULL,
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `miaosha_price` decimal(10,2) DEFAULT NULL COMMENT '秒杀价格',
  `stock_count` int(11) DEFAULT NULL,
  `start_date` timestamp NULL DEFAULT NULL,
  `end_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `goodid` (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='秒杀商品表';

--
-- 转存表中的数据 `miaosha_goods`
--

INSERT INTO `miaosha_goods` (`id`, `goods_id`, `miaosha_price`, `stock_count`, `start_date`, `end_date`) VALUES
(1, 1, '1.00', 10, '2019-01-02 07:42:17', '2019-01-05 08:12:00'),
(2, 2, '1.00', 3, '2019-01-03 07:38:01', '2019-01-24 07:44:55');

-- --------------------------------------------------------

--
-- 表的结构 `miaosha_order`
--

CREATE TABLE IF NOT EXISTS `miaosha_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `goods_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

-- --------------------------------------------------------

--
-- 表的结构 `miaosha_user`
--

CREATE TABLE IF NOT EXISTS `miaosha_user` (
  `id` bigint(20) NOT NULL,
  `nickname` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `head` varchar(100) DEFAULT NULL,
  `registerDate` date DEFAULT NULL,
  `lastLoginDate` date DEFAULT NULL,
  `loginCount` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `miaosha_user`
--

INSERT INTO `miaosha_user` (`id`, `nickname`, `password`, `salt`, `head`, `registerDate`, `lastLoginDate`, `loginCount`) VALUES
(17865197065, 'abc', 'df85dcd2e949d37cec7624626bbb6e21', '135792468', NULL, '2018-12-29', NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `order_info`
--

CREATE TABLE IF NOT EXISTS `order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `goods_id` bigint(20) DEFAULT NULL,
  `delivery_addr_id` bigint(20) DEFAULT NULL COMMENT '收货地址id',
  `goods_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `goods_price` decimal(10,2) DEFAULT NULL,
  `goods_count` int(20) DEFAULT NULL COMMENT '商品数量',
  `order_channel` int(10) DEFAULT NULL COMMENT '订单设备',
  `status` int(10) DEFAULT NULL COMMENT '0未支付1已支付2已发货3已收货4已退款5已完成',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '下单时间',
  `pay_date` tinyint(4) DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='订单表' AUTO_INCREMENT=15 ;

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `name`) VALUES
(1, 'abc'),
(2, 'asd');

--
-- 限制导出的表
--

--
-- 限制表 `miaosha_goods`
--
ALTER TABLE `miaosha_goods`
  ADD CONSTRAINT `goodid` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
