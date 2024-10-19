

--
-- Cơ sở dữ liệu: `traveldb`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `accommodations`
--
use traveldb;
CREATE TABLE `accommodations` (
  `accommodation_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `address` text DEFAULT NULL,
  `description` text DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `accommodations`
--

INSERT INTO `accommodations` (`accommodation_id`, `name`, `type`, `address`, `description`, `rating`, `status`) VALUES
(1, 'Khách sạn ABC', 'Khách sạn', '123 Đường 1, Hà Nội', 'Khách sạn sang trọng với đầy đủ tiện nghi.', 5, 1),
(2, 'Nhà nghỉ XYZ', 'Nhà nghỉ', '456 Đường 2, Hồ Chí Minh', 'Nhà nghỉ sạch sẽ và giá cả hợp lý.', 4, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `activities`
--

CREATE TABLE `activities` (
  `activity_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `difficulty_level` varchar(255) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `activities`
--

INSERT INTO `activities` (`activity_id`, `name`, `description`, `duration`, `difficulty_level`, `status`) VALUES
(1, 'Tham quan phố cổ', 'Khám phá vẻ đẹp của phố cổ Hà Nội.', 2, 'Dễ', 1),
(2, 'Du lịch biển', 'Tắm biển tại bãi biển Đà Nẵng.', 4, 'Dễ', 1),
(3, 'Khám phá ẩm thực', 'Thưởng thức các món ăn đặc sản.', 3, 'Trung bình', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bookings`
--

CREATE TABLE `bookings` (
  `booking_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `tour_date_id` int(11) DEFAULT NULL,
  `booking_date` datetime DEFAULT NULL,
  `number_of_participants` int(11) DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `bookings`
--

INSERT INTO `bookings` (`booking_id`, `user_id`, `tour_date_id`, `booking_date`, `number_of_participants`, `total_price`, `status`) VALUES
(1, 1, 1, '2024-09-30 16:25:10', 2, 10000000.00, 'Đã xác nhận'),
(2, 2, 2, '2024-09-30 16:25:10', 3, 24000000.00, 'Đã xác nhận'),
(3, 3, 3, '2024-09-30 16:25:10', 5, 35000000.00, 'Đã xác nhận');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `booking_promotions`
--

CREATE TABLE `booking_promotions` (
  `booking_id` int(11) NOT NULL,
  `promotion_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `booking_promotions`
--

INSERT INTO `booking_promotions` (`booking_id`, `promotion_id`) VALUES
(1, 1),
(2, 2),
(3, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `companies`
--

CREATE TABLE `companies` (
  `company_id` int(11) NOT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `companies`
--

INSERT INTO `companies` (`company_id`, `company_name`, `description`, `status`) VALUES
(1, 'Công ty Du lịch ABC', 'Công ty chuyên tổ chức tour du lịch trong nước và quốc tế.', 1),
(2, 'Công ty Du lịch XYZ', 'Công ty nổi tiếng với các tour khám phá văn hóa và ẩm thực.', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `destinations`
--

CREATE TABLE `destinations` (
  `destination_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `climate` varchar(255) DEFAULT NULL,
  `best_time_to_visit` varchar(255) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `destinations`
--

INSERT INTO `destinations` (`destination_id`, `name`, `country`, `description`, `climate`, `best_time_to_visit`, `status`) VALUES
(1, 'Hà Nội', 'Việt Nam', 'Thủ đô của Việt Nam, nổi tiếng với văn hóa và lịch sử.', 'Nhiệt đới gió mùa', 'Tháng 9 đến tháng 11', 1),
(2, 'Hồ Chí Minh', 'Việt Nam', 'Thành phố lớn nhất Việt Nam, trung tâm kinh tế và văn hóa.', 'Nhiệt đới', 'Tháng 12 đến tháng 4', 1),
(3, 'Đà Nẵng', 'Việt Nam', 'Thành phố biển nổi tiếng với bãi biển đẹp và các di tích lịch sử.', 'Nhiệt đới gió mùa', 'Tháng 5 đến tháng 8', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `package_accommodations`
--

CREATE TABLE `package_accommodations` (
  `package_id` int(11) NOT NULL,
  `accommodation_id` int(11) NOT NULL,
  `nights_stay` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `package_accommodations`
--

INSERT INTO `package_accommodations` (`package_id`, `accommodation_id`, `nights_stay`) VALUES
(1, 1, 2),
(2, 2, 4),
(3, 1, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `package_activities`
--

CREATE TABLE `package_activities` (
  `package_id` int(11) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `included_in_price` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `package_activities`
--

INSERT INTO `package_activities` (`package_id`, `activity_id`, `included_in_price`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `package_features`
--

CREATE TABLE `package_features` (
  `package_id` int(11) NOT NULL,
  `feature_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `package_features`
--

INSERT INTO `package_features` (`package_id`, `feature_id`) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `package_vehicles`
--

CREATE TABLE `package_vehicles` (
  `package_id` int(11) NOT NULL,
  `vehicle_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `package_vehicles`
--

INSERT INTO `package_vehicles` (`package_id`, `vehicle_id`) VALUES
(1, 1),
(2, 2),
(3, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `payments`
--

CREATE TABLE `payments` (
  `payment_id` int(11) NOT NULL,
  `booking_id` int(11) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `payment_date` datetime DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `transaction_id` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `payments`
--

INSERT INTO `payments` (`payment_id`, `booking_id`, `amount`, `payment_date`, `payment_method`, `transaction_id`, `status`) VALUES
(1, 1, 10000000.00, '2024-09-30 16:25:10', 'Chuyển khoản', 'TRANS001', 'Thành công'),
(2, 2, 24000000.00, '2024-09-30 16:25:10', 'Thẻ tín dụng', 'TRANS002', 'Thành công'),
(3, 3, 35000000.00, '2024-09-30 16:25:10', 'Tiền mặt', 'TRANS003', 'Thành công');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `price_details`
--

CREATE TABLE `price_details` (
  `price_detail_id` int(11) NOT NULL,
  `included_price` text DEFAULT NULL,
  `excluded_price` text DEFAULT NULL,
  `surcharge` text DEFAULT NULL,
  `cancellation_policy` text DEFAULT NULL,
  `notes` text DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `price_details`
--

INSERT INTO `price_details` (`price_detail_id`, `included_price`, `excluded_price`, `surcharge`, `cancellation_policy`, `notes`, `status`) VALUES
(1, 'Bao gồm: ăn sáng, vé tham quan', 'Không bao gồm: phí tự túc', 'Thêm 10% vào ngày lễ', 'Hoàn tiền 50% nếu hủy trước 7 ngày', 'Liên hệ để biết thêm chi tiết.', 1),
(2, 'Bao gồm: ăn uống, xe đưa đón', 'Không bao gồm: phí bảo hiểm', 'Thêm 20% vào dịp lễ', 'Không hoàn tiền nếu hủy sau 3 ngày', 'Đặt chỗ trước 30 ngày để được giảm giá.', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `promotions`
--

CREATE TABLE `promotions` (
  `promotion_id` int(11) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `discount_percentage` decimal(5,2) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `promotions`
--

INSERT INTO `promotions` (`promotion_id`, `code`, `description`, `discount_percentage`, `start_date`, `end_date`, `status`) VALUES
(1, 'GIAM10', 'Giảm giá 10% cho tour đầu tiên', 10.00, '2024-09-01', '2024-12-31', 1),
(2, 'MUA1TANG1', 'Mua 1 tour tặng 1 tour', 100.00, '2024-10-01', '2024-11-30', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `reviews`
--

CREATE TABLE `reviews` (
  `review_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `package_id` int(11) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `comment` text DEFAULT NULL,
  `review_date` datetime DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `reviews`
--

INSERT INTO `reviews` (`review_id`, `user_id`, `package_id`, `rating`, `comment`, `review_date`, `status`) VALUES
(1, 1, 1, 5, 'Tour rất tuyệt vời, hướng dẫn viên thân thiện.', '2024-09-30 16:25:10', 1),
(2, 2, 2, 4, 'Chuyến đi thú vị nhưng giá hơi cao.', '2024-09-30 16:25:10', 1),
(3, 3, 3, 5, 'Đà Nẵng thật đẹp, sẽ quay lại lần nữa!', '2024-09-30 16:25:10', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour_dates`
--

CREATE TABLE `tour_dates` (
  `tour_date_id` int(11) NOT NULL,
  `package_id` int(11) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `available_spots` int(11) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tour_dates`
--

INSERT INTO `tour_dates` (`tour_date_id`, `package_id`, `start_date`, `end_date`, `available_spots`, `status`) VALUES
(1, 1, '2024-10-01', '2024-10-03', 10, 1),
(2, 2, '2024-10-10', '2024-10-14', 15, 1),
(3, 3, '2024-11-01', '2024-11-04', 20, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour_date_guides`
--

CREATE TABLE `tour_date_guides` (
  `tour_date_id` int(11) NOT NULL,
  `guide_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tour_date_guides`
--

INSERT INTO `tour_date_guides` (`tour_date_id`, `guide_id`) VALUES
(1, 1),
(2, 2),
(3, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour_features`
--

CREATE TABLE `tour_features` (
  `feature_id` int(11) NOT NULL,
  `feature_name` varchar(255) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tour_features`
--

INSERT INTO `tour_features` (`feature_id`, `feature_name`, `description`, `status`) VALUES
(1, 'Hướng dẫn viên', 'Hướng dẫn viên chuyên nghiệp đi cùng đoàn.', 1),
(2, 'Xe đưa đón', 'Xe đưa đón tại sân bay và khách sạn.', 1),
(3, 'Bữa ăn', 'Bao gồm các bữa ăn trong chương trình.', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour_guides`
--

CREATE TABLE `tour_guides` (
  `guide_id` int(11) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `languages` varchar(255) DEFAULT NULL,
  `experience` int(11) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tour_guides`
--

INSERT INTO `tour_guides` (`guide_id`, `full_name`, `email`, `phone_number`, `languages`, `experience`, `status`) VALUES
(1, 'Nguyễn Văn D', 'nguyenvand@example.com', '0123456789', 'Tiếng Việt, Tiếng Anh', 5, 1),
(2, 'Trần Thị E', 'tranthie@example.com', '0987654321', 'Tiếng Việt, Tiếng Pháp', 3, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour_packages`
--

CREATE TABLE `tour_packages` (
  `package_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `max_participants` int(11) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `price_detail_id` int(11) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tour_packages`
--

INSERT INTO `tour_packages` (`package_id`, `name`, `description`, `duration`, `price`, `max_participants`, `company_id`, `price_detail_id`, `status`) VALUES
(1, 'Tour Hà Nội 3 ngày', 'Khám phá thủ đô Hà Nội với các điểm du lịch nổi tiếng.', 3, 5000000.00, 15, 1, 1, 1),
(2, 'Tour Hồ Chí Minh 5 ngày', 'Chương trình du lịch đầy đủ trải nghiệm tại TP.HCM.', 5, 8000000.00, 20, 2, 2, 1),
(3, 'Tour Đà Nẵng 4 ngày', 'Tham quan các bãi biển và di tích lịch sử tại Đà Nẵng.', 4, 7000000.00, 25, 1, 1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `date_of_birth` datetime(6) DEFAULT NULL,
  `registration_date` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `email`, `full_name`, `phone_number`, `address`, `date_of_birth`, `registration_date`, `updated_at`, `deleted_at`, `status`) VALUES
(1, 'nguyenF', '$2a$10$Wed9nVxPpYVXL1BcT7QKtetcvq0t7uf4foAovjVXmNjsEOZJ8iGSK', 'nguyenvana@example.com', 'Nguyễn Văn ', '0123456789', '123 Đường ABC, Hà Nội', '1990-01-01 00:00:00.000000', '2024-10-15 10:42:01', NULL, NULL, 1),
(2, 'nguyenG', '$2a$10$reUn4sMSv2sbOXHEBWNaK.NbOO51a2hRN2vb.KHgcNmF3XZk/g3hy', 'nguyenvang@example.com', 'Nguyễn Văn ', '0123456789', '123 Đường ABC, Hà Nội', '1990-01-01 00:00:00.000000', '2024-10-15 11:01:12', NULL, NULL, 1),
(3, 'lehoangC', 'password789', 'lehoangC@example.com', 'Lê Hoàng C', '0912345678', '789 Đường GHI, Đà Nẵng', '1988-10-10 00:00:00.000000', '2024-09-30 16:25:10', NULL, NULL, 1),
(4, 'john_doe', '$2a$10$6RppndDeCM9m8UwhH2CFIuhEA5bHytGAejnqY3zKxPylUw.I7kpLi', 'john.doe@example.com', 'John Doe', '1234567890', '123 Main St, City', '2024-10-12 07:00:00.000000', '2024-10-12 07:00:00', NULL, NULL, 1),
(52, 'nguyenQ', '$2a$10$wZ6BDhh6QrQFOBg2yaZaveJJjjhPjrAaYJu2he/B47H18P9/eQMOW', 'nguyenvanQ@example.com', 'Nguyễn Văn ', '0123456789', '123 Đường ABC, Hà Nội', '1990-01-01 00:00:00.000000', '2024-10-15 11:16:26', NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users_seq`
--

CREATE TABLE `users_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users_seq`
--

INSERT INTO `users_seq` (`next_val`) VALUES
(151);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `vehicles`
--

CREATE TABLE `vehicles` (
  `vehicle_id` int(11) NOT NULL,
  `vehicle_name` varchar(255) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `vehicles`
--

INSERT INTO `vehicles` (`vehicle_id`, `vehicle_name`, `description`, `status`) VALUES
(1, 'Xe Limousine', 'Xe limousine sang trọng cho các tour du lịch.', 1),
(2, 'Xe Bus', 'Xe bus tiện nghi cho đoàn đông người.', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `accommodations`
--
ALTER TABLE `accommodations`
  ADD PRIMARY KEY (`accommodation_id`);

--
-- Chỉ mục cho bảng `activities`
--
ALTER TABLE `activities`
  ADD PRIMARY KEY (`activity_id`);

--
-- Chỉ mục cho bảng `bookings`
--
ALTER TABLE `bookings`
  ADD PRIMARY KEY (`booking_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `tour_date_id` (`tour_date_id`);

--
-- Chỉ mục cho bảng `booking_promotions`
--
ALTER TABLE `booking_promotions`
  ADD PRIMARY KEY (`booking_id`,`promotion_id`),
  ADD KEY `promotion_id` (`promotion_id`);

--
-- Chỉ mục cho bảng `companies`
--
ALTER TABLE `companies`
  ADD PRIMARY KEY (`company_id`);

--
-- Chỉ mục cho bảng `destinations`
--
ALTER TABLE `destinations`
  ADD PRIMARY KEY (`destination_id`);

--
-- Chỉ mục cho bảng `package_accommodations`
--
ALTER TABLE `package_accommodations`
  ADD PRIMARY KEY (`package_id`,`accommodation_id`),
  ADD KEY `accommodation_id` (`accommodation_id`);

--
-- Chỉ mục cho bảng `package_activities`
--
ALTER TABLE `package_activities`
  ADD PRIMARY KEY (`package_id`,`activity_id`),
  ADD KEY `activity_id` (`activity_id`);

--
-- Chỉ mục cho bảng `package_features`
--
ALTER TABLE `package_features`
  ADD PRIMARY KEY (`package_id`,`feature_id`),
  ADD KEY `feature_id` (`feature_id`);

--
-- Chỉ mục cho bảng `package_vehicles`
--
ALTER TABLE `package_vehicles`
  ADD PRIMARY KEY (`package_id`,`vehicle_id`),
  ADD KEY `vehicle_id` (`vehicle_id`);

--
-- Chỉ mục cho bảng `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`payment_id`),
  ADD KEY `booking_id` (`booking_id`);

--
-- Chỉ mục cho bảng `price_details`
--
ALTER TABLE `price_details`
  ADD PRIMARY KEY (`price_detail_id`);

--
-- Chỉ mục cho bảng `promotions`
--
ALTER TABLE `promotions`
  ADD PRIMARY KEY (`promotion_id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Chỉ mục cho bảng `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`review_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `package_id` (`package_id`);

--
-- Chỉ mục cho bảng `tour_dates`
--
ALTER TABLE `tour_dates`
  ADD PRIMARY KEY (`tour_date_id`),
  ADD KEY `package_id` (`package_id`);

--
-- Chỉ mục cho bảng `tour_date_guides`
--
ALTER TABLE `tour_date_guides`
  ADD PRIMARY KEY (`tour_date_id`,`guide_id`),
  ADD KEY `guide_id` (`guide_id`);

--
-- Chỉ mục cho bảng `tour_features`
--
ALTER TABLE `tour_features`
  ADD PRIMARY KEY (`feature_id`);

--
-- Chỉ mục cho bảng `tour_guides`
--
ALTER TABLE `tour_guides`
  ADD PRIMARY KEY (`guide_id`);

--
-- Chỉ mục cho bảng `tour_packages`
--
ALTER TABLE `tour_packages`
  ADD PRIMARY KEY (`package_id`),
  ADD KEY `company_id` (`company_id`),
  ADD KEY `price_detail_id` (`price_detail_id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Chỉ mục cho bảng `vehicles`
--
ALTER TABLE `vehicles`
  ADD PRIMARY KEY (`vehicle_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `accommodations`
--
ALTER TABLE `accommodations`
  MODIFY `accommodation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `activities`
--
ALTER TABLE `activities`
  MODIFY `activity_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `companies`
--
ALTER TABLE `companies`
  MODIFY `company_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `destinations`
--
ALTER TABLE `destinations`
  MODIFY `destination_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `price_details`
--
ALTER TABLE `price_details`
  MODIFY `price_detail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `promotions`
--
ALTER TABLE `promotions`
  MODIFY `promotion_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `reviews`
--
ALTER TABLE `reviews`
  MODIFY `review_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `tour_dates`
--
ALTER TABLE `tour_dates`
  MODIFY `tour_date_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `tour_features`
--
ALTER TABLE `tour_features`
  MODIFY `feature_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `tour_guides`
--
ALTER TABLE `tour_guides`
  MODIFY `guide_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `tour_packages`
--
ALTER TABLE `tour_packages`
  MODIFY `package_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT cho bảng `vehicles`
--
ALTER TABLE `vehicles`
  MODIFY `vehicle_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `bookings`
--
ALTER TABLE `bookings`
  ADD CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `bookings_ibfk_2` FOREIGN KEY (`tour_date_id`) REFERENCES `tour_dates` (`tour_date_id`);

--
-- Các ràng buộc cho bảng `booking_promotions`
--
ALTER TABLE `booking_promotions`
  ADD CONSTRAINT `booking_promotions_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`booking_id`),
  ADD CONSTRAINT `booking_promotions_ibfk_2` FOREIGN KEY (`promotion_id`) REFERENCES `promotions` (`promotion_id`);

--
-- Các ràng buộc cho bảng `package_accommodations`
--
ALTER TABLE `package_accommodations`
  ADD CONSTRAINT `package_accommodations_ibfk_1` FOREIGN KEY (`package_id`) REFERENCES `tour_packages` (`package_id`),
  ADD CONSTRAINT `package_accommodations_ibfk_2` FOREIGN KEY (`accommodation_id`) REFERENCES `accommodations` (`accommodation_id`);

--
-- Các ràng buộc cho bảng `package_activities`
--
ALTER TABLE `package_activities`
  ADD CONSTRAINT `package_activities_ibfk_1` FOREIGN KEY (`package_id`) REFERENCES `tour_packages` (`package_id`),
  ADD CONSTRAINT `package_activities_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`);

--
-- Các ràng buộc cho bảng `package_features`
--
ALTER TABLE `package_features`
  ADD CONSTRAINT `package_features_ibfk_1` FOREIGN KEY (`package_id`) REFERENCES `tour_packages` (`package_id`),
  ADD CONSTRAINT `package_features_ibfk_2` FOREIGN KEY (`feature_id`) REFERENCES `tour_features` (`feature_id`);

--
-- Các ràng buộc cho bảng `package_vehicles`
--
ALTER TABLE `package_vehicles`
  ADD CONSTRAINT `package_vehicles_ibfk_1` FOREIGN KEY (`package_id`) REFERENCES `tour_packages` (`package_id`),
  ADD CONSTRAINT `package_vehicles_ibfk_2` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`);

--
-- Các ràng buộc cho bảng `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`booking_id`);

--
-- Các ràng buộc cho bảng `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`package_id`) REFERENCES `tour_packages` (`package_id`);

--
-- Các ràng buộc cho bảng `tour_dates`
--
ALTER TABLE `tour_dates`
  ADD CONSTRAINT `tour_dates_ibfk_1` FOREIGN KEY (`package_id`) REFERENCES `tour_packages` (`package_id`);

--
-- Các ràng buộc cho bảng `tour_date_guides`
--
ALTER TABLE `tour_date_guides`
  ADD CONSTRAINT `tour_date_guides_ibfk_1` FOREIGN KEY (`tour_date_id`) REFERENCES `tour_dates` (`tour_date_id`),
  ADD CONSTRAINT `tour_date_guides_ibfk_2` FOREIGN KEY (`guide_id`) REFERENCES `tour_guides` (`guide_id`);

--
-- Các ràng buộc cho bảng `tour_packages`
--
ALTER TABLE `tour_packages`
  ADD CONSTRAINT `tour_packages_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `companies` (`company_id`),
  ADD CONSTRAINT `tour_packages_ibfk_2` FOREIGN KEY (`price_detail_id`) REFERENCES `price_details` (`price_detail_id`);
COMMIT;

