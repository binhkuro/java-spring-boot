-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 30, 2023 lúc 03:40 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `springcommerce`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `userid` int(15) NOT NULL,
  `productid` int(15) NOT NULL,
  `image` varchar(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `cart`
--

INSERT INTO `cart` (`id`, `userid`, `productid`, `image`, `name`, `quantity`, `price`) VALUES
(47, 6, 3, 'images/3_derby.jpg', 'COUNTRY DERBY', 1, 650),
(48, 11, 8, 'images/8_sneaker.jpg', 'CLASSIC SNEAKER', 1, 800),
(49, 11, 3, 'images/3_derby.jpg', 'COUNTRY DERBY', 1, 650);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `ido` int(11) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `location` varchar(200) NOT NULL,
  `price` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'chờ xác nhận'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `id` int(15) NOT NULL,
  `image` varchar(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `price` double NOT NULL,
  `description` varchar(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`id`, `image`, `name`, `price`, `description`) VALUES
(1, 'images/1_oxford.jpg', 'SEMI OXFORD', 750, 'Sở hữu phom dáng mũi vuông toát lên sự sang trọng, giúp quý ông tạo nên một vẻ ngoài thật bảnh bao'),
(2, 'images/2_loafer.jpg', 'OMW LOAFER', 850, 'Mang phom dáng sang trọng và hiện đại sẽ mang đến cho bạn một phong cách mới thật lịch lãm'),
(3, 'images/3_derby.jpg', 'COUNTRY DERBY', 650, 'Phù hợp cho các bạn sinh viên đi làm, đi học. Chất liệu da co giãn giúp cho bàn chân được thoải mái.'),
(4, 'images/4_derby.jpg', 'CLASSY DERBY', 750, 'Giày tây công sở, da bò mộc tạo cảm giác êm và thoải mái trông rất sành điệu cho các bạn sinh viên'),
(5, 'images/5_oxford.jpg', 'JAMES OXFORD', 850, 'Sở hữu chất liệu da bò Ý màu sắc huyền bí. Tạo cảm giác lịch lãm, sang trọng và thời thượng cho các quý ông'),
(6, 'images/6_loafer.jpg', 'NEWGEN LOAFER', 950, 'Loafer phù hợp với nhiều lứa tuổi, đôi giày tạo cảm giác chỉn chu sang trọng phù hợp đi ăn cưới'),
(7, 'images/7_oxford.jpg', 'CHARLES OXFORD', 650, 'Giày tây sành điệu, khơi bật phong cách quý ông, lịch lãm và không kém phần sang trọng '),
(8, 'images/8_sneaker.jpg', 'CLASSIC SNEAKER', 800, 'Đôi Sneaker có thiết kế cơ bản làm cho ngoại hình của cánh đàn ông trẻ trung hơn bao giờ hết'),
(9, 'images/9_oxford.jpg', 'CHISEL OXFORD', 880, 'Sở hữu phom dáng mũi vuông toát lên sự sang trọng, giúp quý ông tạo nên một vẻ ngoài bảnh bao'),
(10, 'images/10_loafer.jpg', 'TASSEL LOAFER', 750, 'Loafer Tassel làm cho quý ông trở nên dịu dàng nhưng vẫn đủ để toát lên vẻ nam tính lịch thiệp'),
(11, 'images/11_derby.jpg', 'JAMES DERBY', 750, 'Một trong những đôi Derby sang trọng nhất trong BST giày Derby. Toát lên vẻ quyến rũ cho quý ông'),
(12, 'images/12_loafer.jpg', 'PENNY LOAFER', 950, 'Đôi giày Loafer đế da sang trọng và dành cho những Quý Ông thích sự lịch lãm với những bộ Suit'),
(13, 'images/13_loafer.jpg', 'CLASSIC LOAFER', 750, 'Là một siêu phẩm, đây là dòng đầu tiên của Loafer tại Be Classy. Đúng chất cổ điển từ phom dáng đến thiết kế'),
(14, 'images/14_oxford.jpg', 'CLASSIC OXFORD', 850, 'Giày da cải tiến theo phom dáng người Việt, lịch sự, nhã nhặn, ôn hòa nhưng không kém phần mạnh mẽ'),
(15, 'images/15_derby.jpg', 'NEWGEN DERBY', 950, 'Đây là dòng giày Tây Derby được lựa chọn nhiều nhất tại Be Classy từ khi ra mắt. Tôn bật sự nam tính của quý ông'),
(16, 'images/16_sneaker.jpg', 'CLASSIC SNEAKERS', 650, 'Đôi Sneaker cho nam có thiết kế cơ bản làm cho ngoại hình của cánh đàn ông trẻ trung hơn bao giờ hết'),
(17, 'images/17_sneaker.jpg', 'CLASSIC SNEAKERS ', 750, 'Đôi Sneaker trẻ trung đến từ BST Sneaker mới của Be Classy. Phom dáng thanh lịch và hoàn toàn làm từ da Bò Ý'),
(18, 'images/18_oxford.jpg', 'BROGUES OXFORD', 750, 'Thuộc dòng Oxord cao cấp của Be Classy, đôi GIBSON BROGUES  đế da được làm từ da Bò Ý rất giá trị '),
(19, 'images/19_oxford.jpg', 'CAPTOE OXFORD', 970, 'Thuộc dòng Oxford Premium, GIBSON CAPTOE OXFORD rất sang trọng bởi đặc trưng là phần đế da Bò Ý khác biệt'),
(20, 'images/20_oxford.jpg', 'TONKIN OXFORD', 850, 'Giày tây da bò Ý công sở phong cách cổ điển, đầy lịch lãm toát lên khí chất đàn ông của phái mạnh'),
(21, 'images/21_boot.jpg', 'DON BOOTS', 990, 'THE DON CHELSEA BOOTS là một đôi boots hàng hiệu đẳng cấp dành cho phái mạnh thể hiện bản thân'),
(22, 'images/22_boot.jpg', 'CLASSY BOOTS', 990, 'CLASSY CHELSEA BOOTS là một đôi boots làm từ da bò được nhiều bạn trẻ ưa chuộng hiện nay'),
(23, 'images/23_boot.jpg', 'GIBSON BOOTS', 995, 'Mang thiết kế đơn giản dễ phối đồ, dòng Boots mới của cửa hàng được đa số các Quý Ông đón nhận'),
(24, 'images/24_boot.jpg', 'ZIP BOOTS', 995, 'GIBSON ZIP BOOTS là một trong những đôi Boots đẹp nhất tại cửa hàng từ khi thành lập đến nay'),
(25, 'images/25_wallet.jpg', 'CLASSIC WALLET ', 450, 'Không còn thời của những chiếc ví 2 ngăn dày cộm, sự tinh gọn lên ngôi cùng những chiếc ví nhiều ngăn, gọn nhẹ'),
(26, 'images/26_belt.jpg', 'CLASSIC BELT', 380, 'Thắt lưng da bền được làm hoàn toàn từ da Bò Ý, được làm thủ công những người thợ lành nghề lâu năm'),
(27, 'images/27_belt.jpg', 'CLASSIC BELT', 380, 'Một trong những mẫu thắt lưng đẹp, nằm trong BST thắt lưng basic nhất được tạo nên một cách công phu'),
(28, 'images/28_oxford.jpg', 'SEMI-BROGUES', 850, 'Đây là sản phẩm Oxford kinh điển mà đa số người Việt lựa chọn cho đôi bàn chân của mình'),
(29, 'images/29_oxford.jpg', 'CLASSIC OXFORD', 850, 'Được ra mắt vào tháng 12/2021, mệnh danh là \"đôi giày quốc dân\", chưa kịp ra mắt đã bán hết'),
(30, 'images/30_loafer.jpg', 'PENNY LOAFER', 950, 'Thanh lịch và dễ dàng mix trang phục chính là yếu tố chính từ đôi Penny Loafer làm từ da bò nhập khẩu'),
(31, 'images/31_oxford.jpg', 'STITCHES OXFORD', 850, 'Giống như các đôi giày khác của cửa hàng, một đôi giày mang lại trải nghiệm tuyệt vời cho cánh mày râu'),
(32, 'images/32_oxford.jpg', 'BROGUES OXFORD', 870, 'WINGTIP BROGUES OXFORD - đôi Oxford sang trọng được làm hoàn toàn từ da Bò Ý. Sang trọng, quý phái'),
(33, 'images/33_loafer.jpg', 'BUCKLE LOAFER', 880, 'Giày lười làm bằng da bò Ý cho giới trẻ mang một cách thoải mái và tự tin khi sải bước xuống phố'),
(34, 'images/34_loafer.jpg', 'FLEX LOAFER ', 850, 'Mẫu giày lười có màu sắc độc đáo phù hợp cho các cánh mày râu trầm tính nhưng ẩn chứa thông điệp quan trọng '),
(35, 'images/35_oxford.jpg', 'WHOLE OXFORD', 870, 'Một đôi giày Oxford đơn giản không họa tiết trơn nhám sẽ là một lựa chọn tuyệt vời nơi công sở '),
(36, 'images/36_wallet.jpg', 'GROSS WALLET', 500, 'Một trong những món phụ kiện không thể thiếu - ví nam đẹp GROSSS BI-FOLD WALLET mới ra mắt'),
(37, 'images/37_monkstrap.jpg', 'STITCHES MONKSTRAP', 950, 'Một đôi MONKSTRAP sẽ là một lựa chọn thú vị vì sự độc đáo cũng như sáng tạo mà nó mang lại'),
(38, 'images/38_card.jpg', 'GROSS CARD HOLDER', 270, 'Một trong những món phụ kiện không thể thiếu - ví nam da Bò GROSSS CARD HOLDER mới ra mắt '),
(39, 'images/39_card.jpg', 'LIZARD CARD HOLDER', 270, 'Một thiết kế để giữ những chiếc card thanh toán của bạn một cách tiện lợi, gọn nhẹ và ngăn nắp '),
(40, 'images/40_card.jpg', 'GENTS CARD HOLDER', 280, 'Một trong những món phụ kiện không thể thiếu - ví nam THE GENTS CARD HOLDER mới ra mắt'),
(41, 'images/41_brush.jpg', 'HORSEHAIR BRUSH', 28, 'Lông bàn chải làm bằng lông ngựa, mềm không làm xước bề mặt da giày và không bị cong theo thời gian'),
(42, 'images/42_horn.jpg', 'DARK SHOES HORN ', 40, 'Muỗng da (Shoe Horn) là một trong những phụ kiện mà một người đàn ông \"chơi\" giày Tây cần phải có'),
(43, 'images/43_cream.jpg', 'PREMIUM SHOES CREAM ', 110, 'Công dụng của xi dưỡng da giày dùng để dưỡng ẩm, bổ sung màu, làm mới và làm bóng cho da'),
(44, 'images/44_socks.jpg', 'CLASSIC RIBBED 3-PACK', 153, 'Vớ mang giày Tây chất lượng với chất liệu cotton thấm hút tốt và mang lại cảm giác thoáng cho chân'),
(45, 'images/45_socks.jpg', 'CLASSIC RIBBED 3-PACK', 153, 'Tất mang giày Tây chất lượng với chất liệu cotton thấm hút tốt và mang lại cảm giác thoáng cho chân'),
(46, 'images/46_socks.jpg', 'CLASSIC SOCKS BLACK', 51, 'Vớ mang giày Tây chất lượng với chất liệu cotton thấm hút tốt và mang lại cảm giác thoáng cho chân'),
(47, 'images/47_socks.jpg', 'CLASSIC SOCKS PINDOT ', 51, 'Vớ nam chất lượng với chất liệu cotton thấm hút tốt và mang lại cảm giác thoáng cho chân, không bị bí bách'),
(48, 'images/48_socks.jpg', 'CLASSIC SOCKS BROWN', 51, 'Tất nam cao cổ chất lượng với chất liệu cotton thấm hút tốt và mang lại cảm giác thoáng cho chân'),
(49, 'images/49_socks.jpg ', 'CLASSIC RIBBED SOCK', 51, 'Tất dài nam tại cửa hàng với chất liệu cotton thấm hút tốt và mang lại cảm giác thoáng cho chân'),
(50, 'images/50_wallet.jpg ', 'G. BI-FOLD WALLET ', 330, 'Một trong những món phụ kiện không thể thiếu - ví nam đẹp GROSSS BI-FOLD WALLET mới ra mắt'),
(51, 'images/51_bag.jpg ', 'TÚI', 875, '13256456'),
(52, 'images/52_gift.jpg ', 'GIFT CARD 1', 800, 'Thiết kế nhỏ gọn, tiện lợi. Màu sắc sang trọng và được đóng gói chỉn chu để làm quà tặng giảm giá'),
(53, 'images/53_gift.jpg ', 'GIFT CARD 2', 900, 'Thiết kế nhỏ gọn, tiện lợi. Màu sắc sang trọng và được đóng gói chỉn chu để làm quà tặng giảm giá'),
(55, 'images/17_sneaker.jpg', 'ABCD', 123, 'ABCDEFGH');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(60) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `name`) VALUES
(1, 'admin@gmail.com', '123456789', 'Admin'),
(2, 'caonguyenbinh12@gmail.com', '$2a$10$oBJ5llkdhl.XeubweIo/G.e1ppmdaDzNJvu2tdn.HxkthKfWTAXXa', 'Cao Nguyên Bình'),
(3, 'cnbinhbl@gmail.com', '$2a$10$MVupZyzzJJF1wJXOLM89kul9gueP2r9UdTWnQyAogW8O1DTfrsGUW', 'Cao Bình Nguyên'),
(4, 'user@gmail.com', '$2a$10$iNTYmlKZcqlBSKIhRFDw5u.9GTt3u61YtqIEJ0LgFASFcPTew.DM.', 'User Spring Commerce'),
(5, 'huhu@gmail.com', '$2a$10$2moBorf8EZCEJntG80y5s.dYrGsH92cipthZUmdVTjmabsGtf1poK', 'Tôi Là Bình'),
(6, 'binhnguyencao@gmail.com', '$2a$10$ERDp.JSjdryhTH6d6D13UeiqznRjZXPuAY8uPSO7erVQDZ8ExGrqi', 'Bình Kao'),
(7, 'toilatho@gmail.com', '$2a$10$oidnGOqFDe0hXEFQYYPwq.T3gQ9/F3jjMD3DHWhKSW1lmqw4kmbSy', 'Thỏ Con'),
(8, 'caobinh12@Gmail.com', '$2a$10$88IKybGsOsYvi39yhExU6uA8CbhQVsTQSXEhmMQLsEfrlRlCUe0uq', 'caobinh12@gmail.com'),
(9, 'ABCD@gmail.com', '$2a$10$aNNk.d4HwIQcfe87egrXse/3yGEMFlbIqjSRnLlmB71o/J3c44XAe', 'Tôi Là Bình'),
(10, 'binhcao@gmail.com', '$2a$10$8/d9n6syV9wThI5jHqGybux0mNFBC5JwCZdGSx/cfUk..tm5Gd3kO', 'Bình bình ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_response`
--

CREATE TABLE `user_response` (
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `topic` varchar(100) NOT NULL,
  `content` text NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user_response`
--

INSERT INTO `user_response` (`name`, `email`, `topic`, `content`, `id`) VALUES
('Bình Bình Bình', 'binhcaonguyen@gmail.com', 'Giúp đỡ sử dụng giao diện', 'Giao diện khó nhìn quá', 1),
('User 123', 'user@gmail.com', 'Tôi cần hỗ trợ thanh toán', 'Hey, tôi có thể thanh toán bằng cách nào', 2),
('Cao Nguyên Bình', 'caonguyenbinh12@gmail.com', 'Hệ thống rất tốt', 'Tôi sẽ sử dụng lâu dài hệ thống này', 3),
('HSHDSHD', 'huhu@gmail.com', 'Đây là bài tập giữa kì', 'Giữa kì Spring Boot', 4),
('Thỏ Con', 'toilatho@gmail.com', 'Tối ưu giao diện giữa kì', 'Đã tối ưu giao diện, tôi rất hài lòng', 5),
('Cao Nguyên Bình', 'nguyenbinh12@Gmail.com', 'Hệ thống rất tốt', 'abcd ádui ovhnfoivnhioer ádasdq', 9);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`),
  ADD KEY `userid` (`userid`),
  ADD KEY `productid` (`productid`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user_response`
--
ALTER TABLE `user_response`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `user_response`
--
ALTER TABLE `user_response`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
