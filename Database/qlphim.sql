-- MySQL dump 10.13  Distrib 9.3.0, for Linux (x86_64)
--
-- Host: localhost    Database: qlphim
-- ------------------------------------------------------
-- Server version	9.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `idBill` varchar(45) NOT NULL,
  `idTaiKhoan` varchar(45) NOT NULL,
  `thoiGianDat` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tongTien` int NOT NULL,
  PRIMARY KEY (`idBill`),
  KEY `FK_DV_TK_idx` (`idTaiKhoan`),
  CONSTRAINT `FK_DV_TK` FOREIGN KEY (`idTaiKhoan`) REFERENCES `tai_khoan` (`idTaiKhoan`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES ('Bill_19a84757-6b3a-4778-9f55-270f4f9bb95d','TK_3577b5687b5547a1ad5fc9ea924f447d','2025-12-13 23:29:44',50000),('Bill001','TK_0001','2025-06-09 01:20:27',580000),('Bill002','TK_0001','2025-06-09 01:21:17',410000),('Bill003','TK_0002','2025-06-09 01:22:57',580000),('Bill004','TK_0002','2025-06-09 01:23:39',260000);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ghe`
--

DROP TABLE IF EXISTS `ghe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ghe` (
  `idGhe` varchar(45) NOT NULL,
  `loaiGhe` enum('Tiêu chuẩn','VIP','Triple') NOT NULL,
  PRIMARY KEY (`idGhe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ghe`
--

LOCK TABLES `ghe` WRITE;
/*!40000 ALTER TABLE `ghe` DISABLE KEYS */;
INSERT INTO `ghe` VALUES ('A1','Tiêu chuẩn'),('A2','Tiêu chuẩn'),('A3','Tiêu chuẩn'),('A4','Tiêu chuẩn'),('A5','Tiêu chuẩn'),('A6','Tiêu chuẩn'),('A7','Tiêu chuẩn'),('A8','Tiêu chuẩn'),('A9','Tiêu chuẩn'),('B1','Tiêu chuẩn'),('B2','Tiêu chuẩn'),('B3','VIP'),('B4','VIP'),('B5','VIP'),('B6','Tiêu chuẩn'),('B7','Tiêu chuẩn'),('B8','Tiêu chuẩn'),('B9','Tiêu chuẩn'),('C1','Tiêu chuẩn'),('C2','Tiêu chuẩn'),('C3','VIP'),('C4','VIP'),('C5','VIP'),('C6','Tiêu chuẩn'),('C7','Tiêu chuẩn'),('C8','Tiêu chuẩn'),('C9','Tiêu chuẩn'),('D1','Triple'),('D2','Triple'),('D3','Triple');
/*!40000 ALTER TABLE `ghe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gia`
--

DROP TABLE IF EXISTS `gia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gia` (
  `idGia` varchar(50) NOT NULL,
  `tieuChuan` int NOT NULL,
  `vip` int NOT NULL,
  `triple` int NOT NULL,
  PRIMARY KEY (`idGia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gia`
--

LOCK TABLES `gia` WRITE;
/*!40000 ALTER TABLE `gia` DISABLE KEYS */;
INSERT INTO `gia` VALUES ('Gia01',70000,120000,220000),('Gia02',90000,140000,240000),('Gia03',110000,160000,260000);
/*!40000 ALTER TABLE `gia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lich_chieu`
--

DROP TABLE IF EXISTS `lich_chieu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lich_chieu` (
  `idLichChieu` varchar(100) NOT NULL,
  `gioChieu` datetime DEFAULT NULL,
  `soGheConLai` int DEFAULT NULL,
  `idPhim` varchar(50) NOT NULL,
  `idPhongChieu` varchar(10) NOT NULL,
  `idGia` varchar(50) NOT NULL,
  PRIMARY KEY (`idLichChieu`),
  UNIQUE KEY `unique_lichChieu` (`idPhim`,`idPhongChieu`,`gioChieu`),
  KEY `FK_LC_P_idx` (`idPhim`),
  KEY `FK_LC_PC_idx` (`idPhongChieu`) /*!80000 INVISIBLE */,
  KEY `FK_LC_G_idx` (`idGia`),
  CONSTRAINT `FK_LC_P` FOREIGN KEY (`idPhim`) REFERENCES `phim` (`idPhim`),
  CONSTRAINT `FK_LC_PC` FOREIGN KEY (`idPhongChieu`) REFERENCES `phong_chieu` (`idPhongChieu`),
  CONSTRAINT `FK_LichChieu_Gia` FOREIGN KEY (`idGia`) REFERENCES `gia` (`idGia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lich_chieu`
--

LOCK TABLES `lich_chieu` WRITE;
/*!40000 ALTER TABLE `lich_chieu` DISABLE KEYS */;
INSERT INTO `lich_chieu` VALUES ('LC_250610_01','2025-06-10 16:00:00',27,'P001','PC001','Gia01'),('LC_250610_02','2025-06-10 16:05:00',30,'P002','PC002','Gia01'),('LC_250610_03','2025-06-10 16:10:00',30,'P003','PC003','Gia02'),('LC_250610_04','2025-06-10 16:30:00',27,'P004','PC004','Gia03'),('LC_250610_05','2025-06-10 20:00:00',27,'P001','PC004','Gia02'),('LC_250610_06','2025-06-10 21:00:00',30,'P002','PC003','Gia03'),('LC_250611_01','2025-06-11 08:00:00',27,'P003','PC002','Gia01'),('LC_250611_02','2025-06-11 09:00:00',30,'P004','PC001','Gia02'),('LC_250611_03','2025-06-11 09:30:00',27,'P002','PC004','Gia03'),('LC_250611_04','2025-06-11 13:00:00',30,'P001','PC002','Gia03'),('LC_250611_05','2025-06-11 15:00:00',30,'P002','PC001','Gia03'),('LC_250611_06','2025-06-11 20:00:00',30,'P003','PC004','Gia02'),('LC_251214_01','2025-12-14 14:20:00',30,'P001','PC001','Gia01');
/*!40000 ALTER TABLE `lich_chieu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lichchieu_ghe`
--

DROP TABLE IF EXISTS `lichchieu_ghe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lichchieu_ghe` (
  `idLichChieu` varchar(50) NOT NULL,
  `idGhe` varchar(5) NOT NULL,
  `trangThai` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`idLichChieu`,`idGhe`),
  KEY `FK_LC_G_idx` (`idGhe`),
  CONSTRAINT `FK_LCG_G` FOREIGN KEY (`idGhe`) REFERENCES `ghe` (`idGhe`),
  CONSTRAINT `FK_LCG_LC` FOREIGN KEY (`idLichChieu`) REFERENCES `lich_chieu` (`idLichChieu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lichchieu_ghe`
--

LOCK TABLES `lichchieu_ghe` WRITE;
/*!40000 ALTER TABLE `lichchieu_ghe` DISABLE KEYS */;
INSERT INTO `lichchieu_ghe` VALUES ('LC_250610_01','A6','Đã đặt'),('LC_250610_01','B5','Đã đặt'),('LC_250610_01','D3','Đã đặt'),('LC_250610_04','B5','Đã đặt'),('LC_250610_04','C5','Đã đặt'),('LC_250610_04','D2','Đã đặt'),('LC_250610_05','A1','Đang xử lý'),('LC_250610_05','A2','Đang xử lý'),('LC_250610_05','A3','Đang xử lý'),('LC_250611_01','B5','Đã đặt'),('LC_250611_01','B7','Đã đặt'),('LC_250611_01','C7','Đã đặt'),('LC_250611_03','B3','Đã đặt'),('LC_250611_03','C3','Đã đặt'),('LC_250611_03','D1','Đã đặt');
/*!40000 ALTER TABLE `lichchieu_ghe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phim`
--

DROP TABLE IF EXISTS `phim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phim` (
  `idPhim` varchar(50) NOT NULL,
  `tenPhim` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tacGia` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dienVien` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `thoiLuong` varchar(20) NOT NULL,
  `ngonNgu` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `moTa` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `anhPhim` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`idPhim`),
  UNIQUE KEY `unique_PHIM` (`tenPhim`),
  UNIQUE KEY `idPhim_UNIQUE` (`idPhim`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phim`
--

LOCK TABLES `phim` WRITE;
/*!40000 ALTER TABLE `phim` DISABLE KEYS */;
INSERT INTO `phim` VALUES ('P001','Yêu Em Không Cần Lời Nói','Cho Sun Ho','Hong Kyung, Roh Yoon Seo, Kim Min Ju, Jung Yong Ju, Hyun Bong Sik, Jung Hye Young ','1 giờ 50 phút','Tiếng Hàn, phụ đề Tiếng Việt','Yêu Em Không Cần Lời Nói xoay quanh Yong Joon, chàng trai bán thời gian tại quán cơm của gia đình. Dịp tình cờ đưa anh gặp gỡ Yeo Reum, cô gái chăm sóc em gái khiếm thính, và từ đó, tình yêu ấm áp giữa họ dần nảy nở.','C:\\icons_imgs\\yekcln.png'),
('P002','NGÔI ĐỀN KỲ QUÁI 4','Mike Phontharis','Mean Phiravich, James Bhuripat, Aim Witthawat','2 giờ 45 phút','Thái Lan, phụ đề Tiếng Việt','NGÔI ĐỀN KỲ QUÁI 4 kể về hồn ma Nak với sức mạnh khủng khiếp nhất mà bộ đôi hài hước Balloon và First phải đối mặt để giải cứu chàng trai đẹp Min Joon. Liệu nhóm bạn này có thể vượt qua những thử thách do chính họ tạo ra, hay sẽ tan rã từ đây? Hãy đón xem trên Motchill để khám phá nhé.','C:\\icons_imgs\\ndkq4.png'),
('P003','Điều Kỳ Diệu Trong Phòng Giam Số 7','Frog Tea','Ryu Seung Ryong, Gal So Won, Oh Dal Soo, Park Won Sang, Kim Jung Tae, Jung Man Shik, Kim Gi Cheon','2 giờ 08 phút','Tiếng Hàn','Điều Kỳ Diệu Trong Phòng Giam Số 7 kể về Yong Goo bị kết án tử hình vì bị kẻ xấu vu cho tội sát nhân. Ước mơ cuối cùng của ông là được nhìn thấy con gái mình - Ye Seung. Tuy nhiên, cánh cửa nhà tù nơi ông đang bị giam giữ lại không bao giờ mở cho người nhà vào thăm. Vì vậy, những người cùng phòng giam đã lên kế hoạch để 2 cha con họ có thể hội ngộ...','C:\\icons_imgs\\dkdtpgs7.png'),
('P004','Cung điện ma ám','Yoon Sung Shilk','Yook Sung Jae','2 giờ 15 phút','Tiếng Hàn, phụ đề Tiếng Việt','Cung Điện Ma Ám Phim Cung Điện Ma Ám  là một câu chuyện tình yêu đầy huyền bí giữa Yun Gap, một thư sinh thông minh được vua trọng dụng, và Yeo Ri, một nữ pháp sư trẻ tuổi. Cuộc sống của Yun Gap đảo lộn khi anh bị ác thần xâm chiếm, khiến cho chỉ Yeo Ri - cháu gái của một pháp sư lừng danh - mới có thể nhìn thấy sự hiện diện của thế lực đầy nguy hiểm này.','C:\\icons_imgs\\cdma.png');
/*!40000 ALTER TABLE `phim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phim_theloai`
--

DROP TABLE IF EXISTS `phim_theloai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phim_theloai` (
  `idPhim` varchar(50) NOT NULL,
  `idTheLoai` varchar(100) NOT NULL,
  PRIMARY KEY (`idPhim`,`idTheLoai`),
  KEY `FK_PTL_TL_idx` (`idTheLoai`),
  KEY `FK_PTL_P_idx` (`idPhim`),
  CONSTRAINT `FK_PTL_P` FOREIGN KEY (`idPhim`) REFERENCES `phim` (`idPhim`),
  CONSTRAINT `FK_PTL_TL` FOREIGN KEY (`idTheLoai`) REFERENCES `the_loai` (`idTheLoai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phim_theloai`
--

LOCK TABLES `phim_theloai` WRITE;
/*!40000 ALTER TABLE `phim_theloai` DISABLE KEYS */;
INSERT INTO `phim_theloai` VALUES ('P001','tl_GiaDinhHocDuong'),('P001','tl_TamLyTinhCam'),('P002','tl_HaiHuoc'),('P002','tl_HoatHinh'),('P002','tl_KinhDiMa'),('P003','tl_GiaDinhHocDuong'),('P003','tl_TamLyTinhCam'),('P004','tl_CoTrangThanThoai'),('P004','tl_KinhDiMa'),('P004','tl_TamLyTinhCam');
/*!40000 ALTER TABLE `phim_theloai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phong_chieu`
--

DROP TABLE IF EXISTS `phong_chieu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phong_chieu` (
  `idPhongChieu` varchar(10) NOT NULL,
  `tenPhong` varchar(45) NOT NULL,
  PRIMARY KEY (`idPhongChieu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phong_chieu`
--

LOCK TABLES `phong_chieu` WRITE;
/*!40000 ALTER TABLE `phong_chieu` DISABLE KEYS */;
INSERT INTO `phong_chieu` VALUES ('PC001','Phòng chiếu 01'),('PC002','Phòng chiếu 02'),('PC003','Phòng chiếu 03'),('PC004','Phòng chiếu 04');
/*!40000 ALTER TABLE `phong_chieu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tai_khoan`
--

DROP TABLE IF EXISTS `tai_khoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tai_khoan` (
  `idTaiKhoan` varchar(45) NOT NULL,
  `soDienThoai` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `matKhau` varchar(45) NOT NULL,
  `hoDem` varchar(45) NOT NULL,
  `ten` varchar(45) NOT NULL,
  `ngaySinh` varchar(20) DEFAULT NULL,
  `diaChi` varchar(45) DEFAULT NULL,
  `gioiTinh` enum('Nam','Nữ') NOT NULL,
  PRIMARY KEY (`idTaiKhoan`),
  UNIQUE KEY `soDienThoai_UNIQUE` (`soDienThoai`),
  UNIQUE KEY `idTaiKhoan_UNIQUE` (`idTaiKhoan`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tai_khoan`
--

LOCK TABLES `tai_khoan` WRITE;
/*!40000 ALTER TABLE `tai_khoan` DISABLE KEYS */;
INSERT INTO `tai_khoan` VALUES ('AD0001','0966281026','vuonghung123456789@gmail.com','Hung2005!!','Trịnh Vương','Hưng','2005-06-06','Nghệ An','Nữ'),('AD0002','0987654321','tienanh123456789@gmail.com','Anh2005!','Nguyễn Tiến','Anh','2005-09-20','Hà Nội','Nam'),('TK_0001','0865788519','tuanminh18122005@gmail.com','Aireak@@@','AIREAK','AIREAK','18/12/2006','123123123','Nam'),('TK_0002','0123456789','duylong123456789@gmail.com','Long2005!','Lê Duy ','Long','2005-07-15','Thái Bình','Nam'),('TK_1a39b3b284f64a23980dc25b74deacdb','0987658768','aerjgna@gmail.com','Hungcuacua','123','Frogfgg','18/12/2006','123','Nữ'),('TK_3577b5687b5547a1ad5fc9ea924f447d','0987654678','123123123@gmail.com','Minh@2005','Nguyen Mih','Tuan',NULL,'','Nam');
/*!40000 ALTER TABLE `tai_khoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `the_loai`
--

DROP TABLE IF EXISTS `the_loai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `the_loai` (
  `idTheLoai` varchar(100) NOT NULL,
  `tenTheLoai` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`idTheLoai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `the_loai`
--

LOCK TABLES `the_loai` WRITE;
/*!40000 ALTER TABLE `the_loai` DISABLE KEYS */;
INSERT INTO `the_loai` VALUES ('tl_CoTrangThanThoai','Cổ trang - Thần thoại'),('tl_GiaDinhHocDuong','Gia đình - Học đường'),('tl_HaiHuoc','Hài hước'),('tl_HoatHinh','Hoạt hình'),('tl_KinhDiMa','Kinh dị - Ma'),('tl_TamLyTinhCam','Tâm lý - Tình cảm');
/*!40000 ALTER TABLE `the_loai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ve`
--

DROP TABLE IF EXISTS `ve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ve` (
  `idVe` varchar(50) NOT NULL,
  `idLichChieu` varchar(100) NOT NULL,
  `idGhe` varchar(45) NOT NULL,
  `idGia` varchar(10) NOT NULL,
  `idBill` varchar(45) NOT NULL,
  PRIMARY KEY (`idVe`),
  KEY `FK_V_G_idx` (`idGhe`),
  KEY `FK_V_Gia_idx` (`idGia`),
  KEY `FK_V_B_idx` (`idBill`),
  KEY `FK_V_LC_idx` (`idLichChieu`),
  CONSTRAINT `FK_V_B` FOREIGN KEY (`idBill`) REFERENCES `bill` (`idBill`),
  CONSTRAINT `FK_V_G` FOREIGN KEY (`idGhe`) REFERENCES `ghe` (`idGhe`),
  CONSTRAINT `FK_V_Gia` FOREIGN KEY (`idGia`) REFERENCES `gia` (`idGia`),
  CONSTRAINT `FK_V_LC` FOREIGN KEY (`idLichChieu`) REFERENCES `lich_chieu` (`idLichChieu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ve`
--

LOCK TABLES `ve` WRITE;
/*!40000 ALTER TABLE `ve` DISABLE KEYS */;
INSERT INTO `ve` VALUES ('aa3da64f-814a-4538-928b-154c52d20cbe','LC_250610_05','A1','Gia02','Bill_19a84757-6b3a-4778-9f55-270f4f9bb95d'),('cdd085b6-45e5-4a8d-9746-38c0ec508193','LC_250610_05','A2','Gia02','Bill_19a84757-6b3a-4778-9f55-270f4f9bb95d'),('f7fb72fb-0271-4536-b2b3-4cff98f3d38f','LC_250610_05','A3','Gia02','Bill_19a84757-6b3a-4778-9f55-270f4f9bb95d'),('Ve1001','LC_250610_04','B5','Gia03','Bill001'),('Ve1002','LC_250610_04','C5','Gia03','Bill001'),('Ve1003','LC_250610_04','D2','Gia03','Bill001'),('Ve1004','LC_250610_01','A6','Gia01','Bill002'),('Ve1005','LC_250610_01','B5','Gia01','Bill002'),('Ve1006','LC_250610_01','D3','Gia01','Bill002'),('Ve1007','LC_250611_03','B3','Gia03','Bill003'),('Ve1008','LC_250611_03','C3','Gia03','Bill003'),('Ve1009','LC_250611_03','D1','Gia03','Bill003'),('Ve1010','LC_250611_01','B7','Gia01','Bill004'),('Ve1011','LC_250611_01','C7','Gia01','Bill004'),('Ve1012','LC_250611_01','B5','Gia01','Bill004');
/*!40000 ALTER TABLE `ve` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-14  5:39:50
