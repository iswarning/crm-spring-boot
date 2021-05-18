-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 18, 2021 at 12:15 PM
-- Server version: 8.0.23-0ubuntu0.20.04.1
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `contracts`
--

CREATE TABLE `contracts` (
  `id` bigint UNSIGNED NOT NULL,
  `contract_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Số hợp đồng',
  `area_signed` double(8,2) NOT NULL COMMENT 'Diện tích kí',
  `customer_id` int NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Loại hợp đồng',
  `signed` tinyint(1) NOT NULL COMMENT 'Đã kí hay chưa',
  `signed_date` date NOT NULL COMMENT 'Ngày kí',
  `value` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Giá bán',
  `lot_number` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Mã lô',
  `status` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Trạng thái hợp đồng',
  `project_id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Mã dự án',
  `status_created_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Nhân viên hoặc khách nhận giữ chỗ',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `contracts`
--

INSERT INTO `contracts` (`id`, `contract_no`, `area_signed`, `customer_id`, `type`, `signed`, `signed_date`, `value`, `lot_number`, `status`, `project_id`, `status_created_by`, `created_at`, `updated_at`) VALUES
(13, 'new test 2', 93.00, 1, 'Aliquip sapiente lor', 0, '2021-05-10', '9', '984', '2', '3', '0', NULL, NULL),
(14, 'Amet duis qui delen', 18.00, 1, 'Qui reiciendis ut la', 1, '2021-05-04', '7', '1', '1', '2', '0', NULL, NULL),
(15, 'Sed quam quibusdam l', 13.00, 2, 'Cumque ea quidem sed', 0, '2021-05-02', '21', '571', '1', '1', '1', NULL, NULL),
(16, 'Commodo irure sit ut', 49.00, 2, 'Aperiam voluptatem v', 0, '2021-05-08', '53', '36', '2', '3', '1', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` int NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birthday` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cmnd` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `household` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `address`, `birthday`, `cmnd`, `household`, `name`, `phone`) VALUES
(1, 'Eaque quae in provid', '2014-05-01', '22222777', 'Voluptatem tempora c', 'aaaaaaaaaaaaaaa', '10'),
(2, 'Quisquam et aut sunt', '2020-05-10', '38', 'Nulla repudiandae ma', 'Vielka Burke', '50'),
(43, 'Est pariatur Amet', '2021-05-04', '8', 'Mollitia pariatur Q', 'Hilary Stuart', '46'),
(44, '', '2021-05-12', '0', '', 'wqeqweqeqw', '0'),
(45, 'Nobis vero veniam d', NULL, '2', 'Et molestiae et quae', 'Indira Cruz', '1'),
(46, 'Mollit nihil reprehe', '2021-05-11', '45', 'Minim culpa aut anim', 'Bruce Fuentes', '59');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(47);

-- --------------------------------------------------------

--
-- Table structure for table `juridical`
--

CREATE TABLE `juridical` (
  `id` bigint UNSIGNED NOT NULL,
  `contract_info` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Thông tin hợp đồng',
  `status` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Tình trạng sổ',
  `notarized_date` date DEFAULT NULL COMMENT 'Ngày công chứng',
  `registration_procedures` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Thủ tục đăng bộ',
  `delivery_book_date` date DEFAULT NULL COMMENT 'Ngày bàn giao sổ',
  `liquidation` tinyint NOT NULL COMMENT 'Thanh lý hợp đồng',
  `bill_profile` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Hồ sơ thu lai của khách hàng',
  `book_holder` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Bộ phận giữ sổ',
  `delivery_land_date` date DEFAULT NULL COMMENT 'Ngày bàn giao đất',
  `commitment` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Cam kết thỏa thuận',
  `contract_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `juridical`
--

INSERT INTO `juridical` (`id`, `contract_info`, `status`, `notarized_date`, `registration_procedures`, `delivery_book_date`, `liquidation`, `bill_profile`, `book_holder`, `delivery_land_date`, `commitment`, `contract_id`, `created_at`, `updated_at`) VALUES
(74, '1', 'Aliquid libero delec', '2021-05-24', 'Esse veniam officii', '2021-05-13', 1, 'Velit ipsam exceptu', '1', '2021-05-15', 'Nam accusamus corpor', 13, NULL, NULL),
(75, '2', 'Culpa eos omnis se', '2021-05-04', 'Est maxime et laboru', '2021-05-20', 0, 'Ullamco voluptas omn', '0', '2021-05-20', 'Occaecat exercitatio', 14, NULL, NULL),
(76, '2', 'In consectetur do su', '2021-05-05', 'Adipisicing in volup', '2021-05-20', 1, 'Minus totam minus qu', '1', '2021-05-12', 'Ipsum at esse imped', 15, NULL, NULL),
(77, '1', 'Assumenda est nesciu', '2021-05-05', 'Id culpa totam quas', '2021-05-29', 0, 'Reprehenderit aut ni', '0', '2021-05-13', 'Eiusmod accusantium ', 16, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `id` bigint UNSIGNED NOT NULL,
  `payment_progress` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `payment_date_95` date DEFAULT NULL COMMENT 'Ngày thanh toán đủ 95%',
  `payment_status` tinyint DEFAULT NULL COMMENT 'Tình trạng thanh toán',
  `contract_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`id`, `payment_progress`, `payment_date_95`, `payment_status`, `contract_id`, `created_at`, `updated_at`) VALUES
(62, '11', '2021-05-14', 0, 13, NULL, NULL),
(63, '74', '2021-05-08', 0, 14, NULL, NULL),
(64, '69', '2021-05-06', 0, 15, NULL, NULL),
(65, '92', '2021-05-13', 0, 16, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

CREATE TABLE `projects` (
  `id` bigint UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Mô tả dự án'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `projects`
--

INSERT INTO `projects` (`id`, `name`, `created_at`, `updated_at`, `description`) VALUES
(1, 'Riverside', '2021-02-20 02:57:11', '2021-02-20 02:57:11', 'Dự án Riverside'),
(2, 'Novaland', '2021-02-20 02:57:11', '2021-02-20 02:57:11', 'Dự án Novaland'),
(3, 'Diamond', '2021-02-20 02:57:11', '2021-02-20 02:57:11', 'Dự án Diamond');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `role_id` int NOT NULL,
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`role_id`, `name`) VALUES
(1, 'USER'),
(2, 'CREATOR'),
(3, 'EDITOR'),
(4, 'ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `sessions`
--

CREATE TABLE `sessions` (
  `id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` bigint UNSIGNED DEFAULT NULL,
  `ip_address` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_agent` text COLLATE utf8mb4_unicode_ci,
  `payload` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_activity` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `sessions`
--

INSERT INTO `sessions` (`id`, `user_id`, `ip_address`, `user_agent`, `payload`, `last_activity`) VALUES
('auB9IshsYzIwPLvnM9K7Q0vbawwaxQEejjZqFkhI', NULL, '127.0.0.1', 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Safari/537.36', 'YTozOntzOjY6Il90b2tlbiI7czo0MDoiUmRXN3luSk1mZ0lhYURZZHhBU3I4THJtemlsT0JzQ2ZadUFPeENKMCI7czo5OiJfcHJldmlvdXMiO2E6MTp7czozOiJ1cmwiO3M6Mzc6Imh0dHA6Ly9jcm0udGVzdC9zZWFyY2g/cXVlcnk9JnNlYXJjaD0iO31zOjY6Il9mbGFzaCI7YToyOntzOjM6Im9sZCI7YTowOnt9czozOiJuZXciO2E6MDp7fX19', 1617597726),
('HRb2RFvyhNpaiFSW38SOgFGNyQbB9yPsJ8tZvjR6', 1, '127.0.0.1', 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Safari/537.36', 'YTo1OntzOjY6Il90b2tlbiI7czo0MDoibmVZQWh6Q01pbU1kcmZVZEt6cUJaaHFIV3ZYQk84Z0VGUlBlMG44dSI7czo5OiJfcHJldmlvdXMiO2E6MTp7czozOiJ1cmwiO3M6MjU6Imh0dHA6Ly9jcm0udGVzdC9jdXN0b21lcnMiO31zOjY6Il9mbGFzaCI7YToyOntzOjM6Im9sZCI7YTowOnt9czozOiJuZXciO2E6MDp7fX1zOjUwOiJsb2dpbl93ZWJfNTliYTM2YWRkYzJiMmY5NDAxNTgwZjAxNGM3ZjU4ZWE0ZTMwOTg5ZCI7aToxO3M6MTc6InBhc3N3b3JkX2hhc2hfd2ViIjtzOjYwOiIkMnkkMTAkR2FaanpaRWZzaG9FUm5rTTQueUszdWY5aGtGcXp6U2E1RUpSZktFWWpPRTZncW85cW9nNHEiO30=', 1616818374),
('sMd8RA4m3uq6t8NPw03e0OCWS7oVgIJNsk9O3JDU', NULL, '127.0.0.1', 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Safari/537.36', 'YTozOntzOjY6Il90b2tlbiI7czo0MDoiQ1diQ2pVNGFrNTVoNGkzMXZOOE9DUmZBckJMMXdOV1dwekVwdlJnViI7czo5OiJfcHJldmlvdXMiO2E6MTp7czozOiJ1cmwiO3M6Mzc6Imh0dHA6Ly9jcm0udGVzdC9zZWFyY2g/cXVlcnk9JnNlYXJjaD0iO31zOjY6Il9mbGFzaCI7YToyOntzOjM6Im9sZCI7YTowOnt9czozOiJuZXciO2E6MDp7fX19', 1617002081);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int NOT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `email`, `password`, `enabled`) VALUES
(1, 'patrick', NULL, '$2a$10$cTUErxQqYVyU2qmQGIktpup5chLEdhD2zpzNEyYqmxrHHJbSNDOG.', 1),
(2, 'alex', NULL, '$2a$10$.tP2OH3dEG0zms7vek4ated5AiQ.EGkncii0OpCcGq4bckS9NOULu', 1),
(3, 'john', NULL, '$2a$10$E2UPv7arXmp3q0LzVzCBNeb4B4AtbTAGjkefVDnSztOwE7Gix6kea', 1),
(4, 'namhm', NULL, '$2a$10$GQT8bfLMaLYwlyUysnGwDu6HMB5G.tin5MKT/uduv2Nez0.DmhnOq', 1),
(5, 'admin', NULL, '$2a$10$75qEhDgmIBLTnwGik9uD4OCrDFs85I20XhXne8j37HjQJVnA0C6bC', 1);

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 2),
(4, 3),
(5, 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `contracts`
--
ALTER TABLE `contracts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `juridical`
--
ALTER TABLE `juridical`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `sessions`
--
ALTER TABLE `sessions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sessions_user_id_index` (`user_id`),
  ADD KEY `sessions_last_activity_index` (`last_activity`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`);

--
-- Indexes for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD KEY `user_fk_idx` (`user_id`),
  ADD KEY `role_fk_idx` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `contracts`
--
ALTER TABLE `contracts`
  MODIFY `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `juridical`
--
ALTER TABLE `juridical`
  MODIFY `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT for table `projects`
--
ALTER TABLE `projects`
  MODIFY `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `role_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  ADD CONSTRAINT `user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
