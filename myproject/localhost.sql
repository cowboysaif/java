-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 02, 2012 at 06:44 PM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `program_data`
--
CREATE DATABASE `program_data` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `program_data`;

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE IF NOT EXISTS `accounts` (
  `date` varchar(20) NOT NULL DEFAULT '0',
  `Customer_name` varchar(50) NOT NULL,
  `invoice` varchar(30) NOT NULL,
  `Previous_due` varchar(20) NOT NULL,
  `Current_bill` varchar(20) NOT NULL,
  `Paid` varchar(20) NOT NULL,
  `Final_due` varchar(20) NOT NULL,
  `payment_type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`date`, `Customer_name`, `invoice`, `Previous_due`, `Current_bill`, `Paid`, `Final_due`, `payment_type`) VALUES
('06-09-2012', 'Anik', '0', '44', '0', '20', '24', 'Cash'),
('15-09-2012', 'Tushar', '0', '28', '0', '2', '26', 'Cash');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`name`, `address`, `phone`) VALUES
('Anik', 'Dhaka', '01717-356438'),
('Md Rabbi', 'Dhaka', '01717-653232'),
('Sakib', 'dhaka', '01554-552645'),
('Shimul', 'Dhaka', '01717-356438'),
('Tushar', 'faridpur', '01717-485532');

-- --------------------------------------------------------

--
-- Table structure for table `due`
--

CREATE TABLE IF NOT EXISTS `due` (
  `name` varchar(30) NOT NULL,
  `due_amount` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `due`
--

INSERT INTO `due` (`name`, `due_amount`) VALUES
('Anik', 24),
('Md Rabbi', 0),
('Sakib', 0),
('Shimul', 0),
('Tushar', 26);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `id` varchar(30) NOT NULL,
  `pass` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `pass`) VALUES
('Admin', '123456'),
('Sales', '123456'),
('shimul', '123'),
('Tushar', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `id` int(20) NOT NULL DEFAULT '0',
  `name` varchar(30) NOT NULL,
  `unit_buy_price` int(10) NOT NULL,
  `unit_sell_price` int(10) NOT NULL,
  `quantity` int(10) NOT NULL,
  `date` varchar(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `unit_buy_price`, `unit_sell_price`, `quantity`, `date`) VALUES
(101, 'Biscuits', 15, 19, 1, '15-4-2012'),
(103, 'product3', 18, 25, 13, '01-08-2012'),
(104, 'product4', 45, 52, 1, '01-08-2012'),
(105, 'product5', 35, 45, 1, '01-08-2012'),
(106, 'product6', 25, 34, 1, '01-08-2012'),
(107, 'product7', 25, 32, 1, '01-08-2012'),
(108, 'product8', 28, 32, 1, '01-08-2012'),
(109, 'product9', 34, 40, 8, '01-08-2012'),
(110, 'product10', 21, 27, 8, '01-08-2012'),
(111, 'product22', 12, 12, 9, '11-09-2012'),
(112, 'product23', 15, 16, 11, '11-09-2012'),
(113, 'product24', 12, 16, 11, '18-09-2012'),
(115, 'product26', 15, 19, 8, '19-09-2012');

-- --------------------------------------------------------

--
-- Table structure for table `sell_product`
--

CREATE TABLE IF NOT EXISTS `sell_product` (
  `invoice` int(30) NOT NULL,
  `pid` int(20) NOT NULL,
  `pname` varchar(50) NOT NULL,
  `sell_price` double NOT NULL,
  `quantity` int(20) NOT NULL,
  `customer_name` varchar(50) NOT NULL,
  `soldby` varchar(50) NOT NULL,
  `date` varchar(20) NOT NULL,
  `total` int(50) NOT NULL,
  `Grand_total` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sell_product`
--


-- --------------------------------------------------------

--
-- Table structure for table `settings`
--

CREATE TABLE IF NOT EXISTS `settings` (
  `img_name` varchar(50) NOT NULL,
  `a` int(5) NOT NULL,
  `b` int(5) NOT NULL,
  `c` int(5) NOT NULL,
  UNIQUE KEY `img_name` (`img_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `settings`
--

INSERT INTO `settings` (`img_name`, `a`, `b`, `c`) VALUES
('image6.jpg', 0, 0, 0);
