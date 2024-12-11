-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 11, 2024 at 11:44 AM
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
(1, 'Royal', 'Drinks & Beverages', 15, 18, 'Available', '2025-09-15'),
(2, 'Fita', 'Snacks', 9, 10, 'Available', '2025-05-10'),
(3, 'Fudgee bar', 'Snacks', 9, 6, 'Available', '2025-08-15'),
(4, 'Fish Cracker', 'Snacks', 10, 10, 'Available', '2025-03-25'),
(5, 'Sky Flakes', 'Snacks', 9, 9, 'Available', '2024-12-07'),
(6, 'Coke', 'Drinks & Beverages', 15, 10, 'Available', '2025-12-01'),
(7, 'Sprite', 'Drinks & Beverages', 15, 12, 'Available', '2025-12-01'),
(8, 'Oishi', 'Snacks', 9, 10, 'Available', '2025-07-20'),
(9, 'Corned Beef', 'Canned Goods', 35, 0, 'Out of stock', '2026-01-05'),
(10, 'Sanmarino Big', 'Canned Goods', 45, 0, 'Out of stock', '2025-11-10'),
(11, 'Salt', 'Condiments & Seasonings', 5, 8, 'Available', '0001-12-31'),
(12, 'Oil', 'Oil', 5, 5, 'Available', '2024-12-06'),
(13, 'Vinegar', 'Condiments & Seasonings', 5, 5, 'Available', '0001-12-31'),
(14, 'Sugar', 'Condiments & Seasonings', 20, 10, 'Available', '0001-12-31'),
(15, 'KitKat', 'Confectionery', 40, 0, 'Out of stock', '2024-12-10'),
(16, 'Bingo', 'Snacks', 9, 8, 'Available', '2025-12-09'),
(17, 'Paper', 'Stationery & School Supplies', 25, 10, 'Available', '0001-12-31'),
(18, 'Daiper Small', 'Hygiene Products', 12, 19, 'Available', '0001-12-31'),
(19, 'Biogesic', 'Medicinal Products', 8, 15, 'Available', '2025-03-10'),
(20, 'Neosip', 'Medicinal Products', 9, 15, 'Available', '2025-05-20'),
(21, 'Surf Powder', 'Household Items', 9, 10, 'Available', '2025-06-30'),
(22, 'Lighter', 'Household Items', 10, 5, 'Available', '0001-12-31'),
(23, 'Shampoo', 'Personal Care Products', 8, 15, 'Available', '2025-12-31'),
(24, 'Rice 5Kg', 'Grains', 250, 9, 'Available', '0001-12-31'),
(25, 'Rice 1Kg', 'Grains', 50, 16, 'Available', '0001-12-31'),
(26, 'Instant Noodles ', 'Instant Meals', 40, 5, 'Available', '2025-07-01'),
(27, 'Cigarettes 1pack', 'Cigarettes & Tobacco', 140, 20, 'Available', '0001-12-31'),
(28, 'Cigarettes', 'Cigarettes & Tobacco', 7, 40, 'Available', '0001-12-31'),
(29, 'Condensed Milk', 'Cooking Essentials', 40, 5, 'Available', '2025-11-10'),
(30, 'Soy Sauce Big', 'Condiments & Seasonings', 25, 5, 'Available', '2025-06-20'),
(31, 'Canned Sardines', 'Canned Goods', 20, 6, 'Available', '2025-05-10'),
(32, 'Canned Tuna', 'Canned Goods', 30, 7, 'Available', '2025-04-15'),
(33, 'Presto', 'Snacks', 9, 6, 'Available', '2025-02-10'),
(34, 'Bearch Tree', 'Dairy Products', 10, 15, 'Available', '2025-03-17'),
(35, 'Egg', 'Poultry Products', 10, 90, 'Available', '0001-12-31'),
(36, 'Swak', 'Dairy Products', 10, 0, 'Out of stock', '2025-02-18'),
(37, 'Greatest White', 'Dairy Products', 12, 11, 'Available', '2025-07-16'),
(41, 'Hotdog', 'Poultry Products', 65, 10, 'Available', '2025-05-03'),
(45, 'Rice Lami', 'Grains', 60, 95, 'Available', '0001-12-31'),
(46, 'Whisper With Wings', 'Hygiene Products', 9, 10, 'Available', '0001-12-31');

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
(14, 9, 4, '2024-12-07', '11:59:04'),
(15, 1, 1, '2024-12-10', '21:15:29'),
(16, 15, 5, '2024-12-10', '21:19:39'),
(17, 10, 6, '2024-12-10', '21:21:05'),
(18, 3, 15, '2024-12-10', '23:10:46'),
(19, 1, 1, '2024-12-10', '23:11:40'),
(20, 35, 10, '2024-12-11', '00:03:21'),
(21, 11, 1, '2024-12-11', '00:03:44'),
(22, 36, 6, '2024-12-11', '00:04:03'),
(23, 25, 4, '2024-12-11', '00:04:30'),
(24, 45, 5, '2024-12-11', '00:06:16'),
(25, 16, 2, '2024-12-11', '00:07:05'),
(26, 18, 1, '2024-12-11', '00:07:17'),
(27, 37, 2, '2024-12-11', '00:12:11'),
(28, 24, 1, '2024-12-11', '00:13:14'),
(29, 6, 1, '2024-12-11', '00:13:32');

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
  `email` varchar(100) NOT NULL,
  `image` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_table`
--

INSERT INTO `user_table` (`id`, `fname`, `lname`, `uname`, `pass`, `contact`, `status`, `type`, `email`, `image`) VALUES
(1, 'david', 'villondo', 'admin', '7c222fb2927d828af22f592134e8932480637c0d', '09123456789', 'Active', 'Admin', 'david', '/u_default/blank_pfp.jpg'),
(2, 'sailas', 'romano', 'user', '7c222fb2927d828af22f592134e8932480637c0d', '09987654321', 'Active', 'User', 'sailas', '/u_default/blank_pfp.jpg'),
(3, 'ashlyn', 'batol', 'admin1', '7c222fb2927d828af22f592134e8932480637c0d', '', 'Pending', 'Admin', 'ash', '/u_default/blank_pfp.jpg'),
(4, 'sample', 'sample', 'sample', 'fa585d89c851dd338a70dcf535aa2a92fee7836dd6aff12265', '', 'Archived', 'User', 'sample', '/u_default/blank_pfp.jpg'),
(5, 'test', 'test', 'test', '7c222fb2927d828af22f592134e8932480637c0d', '09123789456', 'Archived', 'User', 'test', '/u_default/blank_pfp.jpg');

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
  MODIFY `prod_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `sale_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `user_table`
--
ALTER TABLE `user_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
