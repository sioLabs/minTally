-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 16, 2013 at 10:37 AM
-- Server version: 5.5.24-log
-- PHP Version: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `innuinfocomm`
--

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
CREATE TABLE IF NOT EXISTS `items` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_group` int(11) NOT NULL,
  `item_sub_group` int(11) DEFAULT NULL,
  `item_first_unit` int(11) NOT NULL,
  `item_second_unit` int(11) DEFAULT NULL,
  `item_open_stock` double DEFAULT NULL,
  `item_rack_no` varchar(20) DEFAULT NULL,
  `item_code` varchar(20) DEFAULT NULL,
  `item_rate` double DEFAULT NULL,
  `item_vat_perc` double DEFAULT NULL,
  `item_name` varchar(80) NOT NULL,
  PRIMARY KEY (`item_id`),
  KEY `item_group` (`item_group`,`item_sub_group`),
  KEY `item_sub_group` (`item_sub_group`),
  KEY `item_first_unit` (`item_first_unit`),
  KEY `item_second_unit` (`item_second_unit`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5176 ;

-- --------------------------------------------------------

--
-- Table structure for table `item_group`
--

DROP TABLE IF EXISTS `item_group`;
CREATE TABLE IF NOT EXISTS `item_group` (
  `item_group_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_group_name` varchar(50) NOT NULL,
  `item_group_parent` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`item_group_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=136 ;

-- --------------------------------------------------------

--
-- Table structure for table `ledger_group_table`
--

DROP TABLE IF EXISTS `ledger_group_table`;
CREATE TABLE IF NOT EXISTS `ledger_group_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;

-- --------------------------------------------------------

--
-- Table structure for table `ledger_table`
--

DROP TABLE IF EXISTS `ledger_table`;
CREATE TABLE IF NOT EXISTS `ledger_table` (
  `ledger_id` int(11) NOT NULL AUTO_INCREMENT,
  `ledger_name` varchar(45) NOT NULL,
  `ledger_person_name` varchar(45) DEFAULT NULL,
  `ledger_type` int(11) NOT NULL COMMENT '		',
  `ledger_open_bal` decimal(10,0) NOT NULL,
  `ledger_open_bal_type` tinyint(1) NOT NULL,
  `ledger_present_bal` decimal(10,0) NOT NULL,
  `ledger_present_bal_type` tinyint(1) NOT NULL,
  `ledger_address` varchar(150) DEFAULT NULL,
  `ledger_contact_no` varchar(15) DEFAULT NULL,
  `ledger_contact_email` varchar(30) DEFAULT NULL,
  `ledger_vat_tin` varchar(15) DEFAULT NULL,
  `ledger_cst_tin` varchar(15) DEFAULT NULL,
  `ledger_create_date` date NOT NULL,
  `ledger_modification_date` date NOT NULL,
  PRIMARY KEY (`ledger_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

-- --------------------------------------------------------

--
-- Table structure for table `salebill_item`
--

DROP TABLE IF EXISTS `salebill_item`;
CREATE TABLE IF NOT EXISTS `salebill_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `item_name` varchar(80) NOT NULL,
  `item_qnty` double NOT NULL,
  `item_unit` int(11) NOT NULL,
  `item_rate` double NOT NULL,
  `total` double NOT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `item_vat_rs` int(11) NOT NULL,
  `sale_bill_no` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sale_bill_no` (`sale_bill_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `sale_bill`
--

DROP TABLE IF EXISTS `sale_bill`;
CREATE TABLE IF NOT EXISTS `sale_bill` (
  `sale_bill_no` int(11) NOT NULL AUTO_INCREMENT,
  `sale_bill_date` date NOT NULL,
  `sale_bill_customer` int(11) NOT NULL,
  `sale_bill_site` varchar(100) NOT NULL,
  `sale_bil_remark` varchar(100) NOT NULL,
  `sale_bill_company` varchar(80) NOT NULL,
  `sale_bill_totalvat` double NOT NULL,
  `sale_bill_frieghtCharges` double NOT NULL,
  `sale_bill_discount` double NOT NULL,
  `sale_bill_total_amount` double NOT NULL,
  PRIMARY KEY (`sale_bill_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `units`
--

DROP TABLE IF EXISTS `units`;
CREATE TABLE IF NOT EXISTS `units` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_type` int(1) NOT NULL,
  `unit_name` varchar(20) NOT NULL,
  `conv` float NOT NULL,
  `second_unit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `items`
--
ALTER TABLE `items`
  ADD CONSTRAINT `items_ibfk_1` FOREIGN KEY (`item_group`) REFERENCES `item_group` (`item_group_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `items_ibfk_2` FOREIGN KEY (`item_sub_group`) REFERENCES `item_group` (`item_group_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `items_ibfk_3` FOREIGN KEY (`item_first_unit`) REFERENCES `units` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `items_ibfk_4` FOREIGN KEY (`item_second_unit`) REFERENCES `units` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `salebill_item`
--
ALTER TABLE `salebill_item`
  ADD CONSTRAINT `salebill_item_ibfk_1` FOREIGN KEY (`sale_bill_no`) REFERENCES `sale_bill` (`sale_bill_no`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
