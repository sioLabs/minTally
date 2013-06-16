-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 10, 2013 at 12:12 PM
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
-- Table structure for table `item`
--

CREATE TABLE IF NOT EXISTS `item` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(50) NOT NULL,
  `item_group` int(11) NOT NULL,
  `item_unit_1` int(1) NOT NULL,
  `item_rate` float NOT NULL,
  `item_rate_unit` int(11) NOT NULL,
  `item_unit_2` varchar(5) DEFAULT NULL,
  `item_vat_perc` float NOT NULL,
  `item_open_stock` float NOT NULL,
  `ite_present_stock` float NOT NULL,
  `item_total_value` int(11) NOT NULL,
  PRIMARY KEY (`item_id`),
  KEY `item_group` (`item_group`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`item_id`, `item_name`, `item_group`, `item_unit_1`, `item_rate`, `item_rate_unit`, `item_unit_2`, `item_vat_perc`, `item_open_stock`, `ite_present_stock`, `item_total_value`) VALUES
(4, 'Xperia', 2, 1, 10, 1, NULL, 12.5, 1000, 1000, 10000),
(5, 'Surface Pro', 4, 1, 12, 1, NULL, 10, 1000, 1000, 12000);

-- --------------------------------------------------------

--
-- Table structure for table `item_group`
--

CREATE TABLE IF NOT EXISTS `item_group` (
  `item_group_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_group_name` varchar(25) NOT NULL,
  PRIMARY KEY (`item_group_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='table for item groups' AUTO_INCREMENT=5 ;

--
-- Dumping data for table `item_group`
--

INSERT INTO `item_group` (`item_group_id`, `item_group_name`) VALUES
(1, 'Sony'),
(2, 'Apple'),
(3, 'Microsoft'),
(4, 'HP');

-- --------------------------------------------------------

--
-- Table structure for table `ledger_group_table`
--

CREATE TABLE IF NOT EXISTS `ledger_group_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;

--
-- Dumping data for table `ledger_group_table`
--

INSERT INTO `ledger_group_table` (`id`, `group_name`) VALUES
(1, 'Capital Accounts'),
(2, 'Current Assets'),
(3, 'Bank Accounts'),
(4, 'Bank OD A/c'),
(5, 'Cash - In - Hand'),
(6, 'Current Liabilities'),
(7, 'Deposits (Asset)'),
(8, 'Direct Expenses'),
(9, 'Duties and Taxes'),
(10, 'Direct Incomes'),
(11, 'Loans and Advances (Asset)'),
(12, 'Fixed Assets'),
(13, 'Provisions'),
(14, 'Indirect Expenses'),
(15, 'Reserves and Surplus'),
(16, 'Indirect Incomes'),
(17, 'Secured Loans'),
(18, 'Investments'),
(19, 'Stock-in-Hand'),
(20, 'Loans (Liability)'),
(21, 'Sundry Creditors'),
(22, 'Misc. Expenses (Asset)'),
(23, 'Sundry Debtors'),
(24, 'Purchase Accounts'),
(25, 'Unsecured Loans'),
(26, 'Sales Accounts'),
(27, 'Suspense A/c');

-- --------------------------------------------------------

--
-- Table structure for table `ledger_table`
--

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `ledger_table`
--

INSERT INTO `ledger_table` (`ledger_id`, `ledger_name`, `ledger_person_name`, `ledger_type`, `ledger_open_bal`, `ledger_open_bal_type`, `ledger_present_bal`, `ledger_present_bal_type`, `ledger_address`, `ledger_contact_no`, `ledger_contact_email`, `ledger_vat_tin`, `ledger_cst_tin`, `ledger_create_date`, `ledger_modification_date`) VALUES
(1, 'Cash', 'Self', 8, '1000', 0, '1000', 0, '', '', '', '', '', '0000-00-00', '0000-00-00'),
(2, 'Ashutosh Singh', 'Ashutosh Singh', 21, '10000', 0, '10000', 0, 'CDAC\nJuhu	', '9021813612', 'dh_ashu@outlook.com', '123456', '123456', '2013-05-23', '2013-05-23'),
(3, 'Deepak Seth', 'Deepak Seth', 9, '1000', 0, '1000', 0, 'ola	', '9389331908', 'virus.virus99@gmail.com', '1234546', '2345678', '2013-05-23', '2013-05-23'),
(4, 'B V Singh', 'B V Singh', 3, '10000', 1, '10000', 1, 'Luickow\nMau garvi	', '9335048062', 'sucrotechengineers@gmail.com', '123445', '1234567', '2013-05-23', '2013-05-23'),
(8, 'Nirmal Parwate', 'nirmal Parwate', 2, '2000', 1, '2000', 1, 'sadhlkhasfkjhkj	', '812612', 'nirmal@gmail.com', '', '', '2013-05-25', '2013-05-25'),
(9, 'Sadhna Singh', 'SadhnaSingh', 8, '50000', 1, '50000', 1, 'sadfjkdsfkl', '9389331908', 'sadhna@gmail.com', '132324324', '12232324', '2013-05-25', '2013-05-25'),
(10, 'Nikhil Rajput', NULL, 4, '1000', 1, '1000', 1, NULL, NULL, NULL, NULL, NULL, '2013-05-25', '2013-05-25');

-- --------------------------------------------------------

--
-- Table structure for table `units`
--

CREATE TABLE IF NOT EXISTS `units` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_type` int(1) NOT NULL,
  `unit_name` varchar(20) NOT NULL,
  `conv` float NOT NULL,
  `second_unit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `units`
--

INSERT INTO `units` (`id`, `unit_type`, `unit_name`, `conv`, `second_unit`) VALUES
(1, 1, 'Meters', 1, 0),
(2, 1, 'Foot', 1, 0),
(3, 2, 'MT perfoot', 0.126, 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`item_group`) REFERENCES `item_group` (`item_group_id`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
