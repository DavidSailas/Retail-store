-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 07, 2024 at 11:36 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rs`
--

-- --------------------------------------------------------

--
-- Table structure for table `product_table`
--

CREATE TABLE `product_table` (
  `prod_id` int(50) NOT NULL,
  `prod_name` varchar(100) NOT NULL,
  `category` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `quantity` int(100) NOT NULL,
  `prod_status` varchar(100) NOT NULL,
  `expire` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product_table`
--

INSERT INTO `product_table` (`prod_id`, `prod_name`, `category`, `price`, `quantity`, `prod_status`, `expire`) VALUES
(1, 'Royal', 'Drinks', 15, 14, 'Available', '2025-09-15'),
(2, 'Fita', 'Snacks', 9, 10, 'Available', '2025-05-10'),
(3, 'Fudgee bar', 'Snacks', 9, 0, 'Out of stock', '2025-08-15'),
(4, 'Fish Cracker', 'Crackers', 10, 10, 'Available', '2025-03-25'),
(5, 'Sky Flakes', 'Snacks', 9, 9, 'Available', '2024-12-07'),
(6, 'Coke', 'Drinks', 15, 11, 'Available', '2025-12-01'),
(7, 'Sprite', 'Drinks', 15, 12, 'Available', '2025-12-01'),
(8, 'Oishi', 'Crackers', 9, 10, 'Available', '2025-07-20'),
(9, 'Corned Beef', 'Canned goods', 35, 0, 'Out of stock', '2026-01-05'),
(10, 'Sanmarino Big', 'Canned goods', 45, 4, 'Available', '2025-11-10'),
(11, 'Salt', 'Condiments', 5, 9, 'Available', '0001-12-31'),
(12, 'Oil', 'Oil', 5, 5, 'Available', '2024-12-06'),
(13, 'Vinegar', 'Condiments', 5, 5, 'Available', '0001-12-31'),
(14, 'Sugar', 'Condiments', 20, 10, 'Available', '0001-12-31'),
(15, 'KitKat', 'Dairy', 40, 5, 'Available', '2024-12-10');

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `sale_id` int(50) NOT NULL,
  `prod_id` int(50) NOT NULL,
  `quantity_sold` int(100) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`sale_id`, `prod_id`, `quantity_sold`, `date`, `time`) VALUES
(1, 4, 1, '2024-11-27', '08:20:17'),
(2, 2, 1, '2024-12-02', '14:35:11'),
(3, 4, 4, '2024-12-03', '22:09:43'),
(4, 5, 5, '2024-12-04', '00:15:40'),
(5, 5, 2, '2024-12-04', '00:18:25'),
(6, 5, 2, '2024-12-04', '00:35:15'),
(7, 6, 3, '2024-12-04', '02:37:01'),
(8, 10, 2, '2024-12-04', '02:40:55'),
(9, 7, 3, '2024-12-04', '02:41:27'),
(10, 6, 6, '2024-12-04', '02:41:52'),
(11, 11, 1, '2024-12-07', '09:26:29'),
(12, 1, 1, '2024-12-07', '09:34:11'),
(13, 9, 1, '2024-12-07', '09:54:00'),
(14, 9, 4, '2024-12-07', '11:59:04');

-- --------------------------------------------------------

--
-- Table structure for table `user_table`
--

CREATE TABLE `user_table` (
  `id` int(11) NOT NULL,
  `fname` varchar(100) NOT NULL,
  `lname` varchar(100) NOT NULL,
  `uname` varchar(20) NOT NULL,
  `pass` varchar(100) NOT NULL,
  `contact` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `type` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_table`
--

INSERT INTO `user_table` (`id`, `fname`, `lname`, `uname`, `pass`, `contact`, `status`, `type`, `email`) VALUES
(1, 'david', 'villondo', 'admin', '7c222fb2927d828af22f592134e8932480637c0d', '09123456789', 'Active', 'Admin', 'david'),
(2, 'sailas', 'romano', 'user', '7c222fb2927d828af22f592134e8932480637c0d', '09987654321', 'Active', 'User', 'sailas');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product_table`
--
ALTER TABLE `product_table`
  ADD PRIMARY KEY (`prod_id`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`sale_id`),
  ADD KEY `sales` (`prod_id`);

--
-- Indexes for table `user_table`
--
ALTER TABLE `user_table`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product_table`
--
ALTER TABLE `product_table`
  MODIFY `prod_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `sale_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `user_table`
--
ALTER TABLE `user_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `sales`
--
ALTER TABLE `sales`
  ADD CONSTRAINT `sales` FOREIGN KEY (`prod_id`) REFERENCES `product_table` (`prod_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
