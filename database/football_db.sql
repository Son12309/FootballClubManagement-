SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;
SET collation_connection = utf8mb4_unicode_ci;

-- ======================================================
-- DATABASE ARSENAL 2025 (FULL DATA - 5 MATCHES)
-- Môi trường chạy: MySQL 8.x
-- ======================================================

DROP DATABASE IF EXISTS FootballDB;
CREATE DATABASE FootballDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE FootballDB;

SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;
SET collation_connection = utf8mb4_unicode_ci;

-- ======================================================
-- PHẦN 1: TẠO CÁC BẢNG CHÍNH
-- ======================================================

CREATE TABLE CLB (
    MaCLB INT PRIMARY KEY AUTO_INCREMENT,
    TenCLB VARCHAR(100),
    QuocGia VARCHAR(50),
    NgayThanhLap DATE,
    SanNha VARCHAR(100),
    GiaiDau VARCHAR(100)
);

CREATE TABLE HuanLuyenVien (
    MaHLV INT PRIMARY KEY AUTO_INCREMENT,
    HoTen VARCHAR(100),
    QuocTich VARCHAR(50),
    NgaySinh DATE,
    Luong DECIMAL(12,2),
    KinhNghiem INT,
    LoiChoi VARCHAR(100),
    MaCLB INT,
    FOREIGN KEY (MaCLB) REFERENCES CLB(MaCLB)
);

CREATE TABLE CauThu (
    MaCT INT PRIMARY KEY AUTO_INCREMENT,
    HoTen VARCHAR(100),
    QuocTich VARCHAR(50),
    ViTri VARCHAR(20),
    NgaySinh DATE,
    GiaMua DECIMAL(15,2),
    Luong DECIMAL(12,2),
    SoAo INT,
    MaCLB INT,
    FOREIGN KEY (MaCLB) REFERENCES CLB(MaCLB)
);

CREATE TABLE TranDau (
    MaTD INT PRIMARY KEY AUTO_INCREMENT,
    DiaDiem VARCHAR(100),
    NgayThiDau DATE,
    DoiChuNha INT,
    DoiKhach INT,
    FOREIGN KEY (DoiChuNha) REFERENCES CLB(MaCLB),
    FOREIGN KEY (DoiKhach) REFERENCES CLB(MaCLB)
);

CREATE TABLE TrongTranDau (
    MaTD INT,
    MaCT INT,
    TheVang INT DEFAULT 0,
    TheDo INT DEFAULT 0,
    BanThang INT DEFAULT 0,
    SoLanCuuThua INT DEFAULT 0,
    SoLanTakle INT DEFAULT 0,
    PRIMARY KEY (MaTD, MaCT),
    FOREIGN KEY (MaTD) REFERENCES TranDau(MaTD),
    FOREIGN KEY (MaCT) REFERENCES CauThu(MaCT)
);

-- ======================================================
-- PHẦN 2: DỮ LIỆU MẪU (CẬP NHẬT 5 TRẬN)
-- ======================================================

INSERT INTO CLB (TenCLB, QuocGia, NgayThanhLap, SanNha, GiaiDau)
VALUES 
('Arsenal', 'Anh', '1886-10-01', 'Emirates Stadium', 'Premier League'),
('Manchester City', 'Anh', '1880-04-16', 'Etihad Stadium', 'Premier League'),
('Chelsea', 'Anh', '1905-03-10', 'Stamford Bridge', 'Premier League'),
('Liverpool', 'Anh', '1892-06-03', 'Anfield', 'Premier League'),
('Tottenham Hotspur', 'Anh', '1882-09-05', 'Tottenham Hotspur Stadium', 'Premier League'),
('Manchester United', 'Anh', '1878-03-05', 'Old Trafford', 'Premier League');

INSERT INTO HuanLuyenVien (HoTen, QuocTich, NgaySinh, Luong, KinhNghiem, LoiChoi, MaCLB)
VALUES ('Mikel Arteta', 'Tây Ban Nha', '1982-03-26', 700000, 10, 'Kiểm soát bóng', 1);

-- ========== DANH SÁCH 30 CẦU THỦ ARSENAL 2025 ==========
-- Thủ môn
INSERT INTO CauThu (HoTen, QuocTich, ViTri, NgaySinh, GiaMua, Luong, SoAo, MaCLB) VALUES
('David Raya', 'Tây Ban Nha', 'GK', '1995-09-15', 40000000, 200000, 1, 1),
('Aaron Ramsdale', 'Anh', 'GK', '1998-05-14', 35000000, 180000, 22, 1),
('Karl Hein', 'Estonia', 'GK', '2002-04-13', 1000000, 30000, 31, 1);

-- Hậu vệ
INSERT INTO CauThu (HoTen, QuocTich, ViTri, NgaySinh, GiaMua, Luong, SoAo, MaCLB) VALUES
('Ben White', 'Anh', 'RB', '1997-10-08', 50000000, 220000, 4, 1),
('Takehiro Tomiyasu', 'Nhật Bản', 'RB', '1998-11-05', 25000000, 120000, 18, 1),
('William Saliba', 'Pháp', 'CB', '2001-03-24', 70000000, 250000, 2, 1),
('Gabriel Magalhães', 'Brazil', 'CB', '1997-12-19', 45000000, 200000, 6, 1),
('Jakub Kiwior', 'Ba Lan', 'CB', '2000-02-15', 25000000, 80000, 15, 1),
('Oleksandr Zinchenko', 'Ukraine', 'LB', '1996-12-15', 40000000, 180000, 35, 1),
('Jurriën Timber', 'Hà Lan', 'RB', '2001-06-17', 40000000, 150000, 12, 1),
('Reuell Walters', 'Anh', 'CB', '2004-12-16', 500000, 20000, 32, 1);

-- Tiền vệ
INSERT INTO CauThu (HoTen, QuocTich, ViTri, NgaySinh, GiaMua, Luong, SoAo, MaCLB) VALUES
('Martin Ødegaard', 'Na Uy', 'CM', '1998-12-17', 85000000, 320000, 8, 1),
('Declan Rice', 'Anh', 'CDM', '1999-01-14', 105000000, 350000, 41, 1),
('Thomas Partey', 'Ghana', 'CDM', '1993-06-13', 45000000, 250000, 5, 1),
('Jorginho', 'Ý', 'CM', '1991-12-20', 10000000, 150000, 20, 1),
('Fabio Vieira', 'Bồ Đào Nha', 'CAM', '2000-05-30', 35000000, 100000, 21, 1),
('Emile Smith Rowe', 'Anh', 'CAM', '2000-07-28', 30000000, 120000, 10, 1),
('Kai Havertz', 'Đức', 'CAM', '1999-06-11', 65000000, 280000, 29, 1),
('Mohamed Elneny', 'Ai Cập', 'CDM', '1992-07-11', 5000000, 80000, 25, 1);

-- Tiền đạo
INSERT INTO CauThu (HoTen, QuocTich, ViTri, NgaySinh, GiaMua, Luong, SoAo, MaCLB) VALUES
('Bukayo Saka', 'Anh', 'RW', '2001-09-05', 90000000, 300000, 7, 1),
('Gabriel Jesus', 'Brazil', 'CF', '1997-04-03', 65000000, 270000, 9, 1),
('Leandro Trossard', 'Bỉ', 'LW', '1994-12-04', 30000000, 160000, 19, 1),
('Gabriel Martinelli', 'Brazil', 'LW', '2001-06-18', 70000000, 180000, 11, 1),
('Eddie Nketiah', 'Anh', 'CF', '1999-05-30', 25000000, 120000, 14, 1),
('Reiss Nelson', 'Anh', 'RW', '1999-12-10', 15000000, 100000, 24, 1),
('Marquinhos', 'Brazil', 'RW', '2003-04-07', 8000000, 60000, 27, 1),
('Charles Sagoe Jr', 'Anh', 'LW', '2004-07-24', 500000, 20000, 39, 1);


-- ========== TRẬN ĐẤU ==========
INSERT INTO TranDau (DiaDiem, NgayThiDau, DoiChuNha, DoiKhach)
VALUES 
('Emirates Stadium', '2025-03-01', 1, 2),
('Emirates Stadium', '2025-03-08', 1, 3),
('Anfield', '2025-03-15', 4, 1),
('Tottenham Hotspur Stadium', '2025-03-22', 5, 1),
('Emirates Stadium', '2025-03-30', 1, 6);

-- ========== TRONG TRẬN ==========
INSERT INTO TrongTranDau (MaTD, MaCT, BanThang, TheVang, TheDo, SoLanCuuThua, SoLanTakle) VALUES
(1, 7, 1, 0, 0, 0, 0),
(1, 9, 1, 0, 0, 0, 0),
(1, 8, 1, 0, 0, 0, 0),
(1, 1, 0, 0, 0, 5, 0),
(1, 6, 0, 1, 0, 0, 4),
(2, 7, 1, 0, 0, 0, 0),
(2, 19, 1, 0, 0, 0, 0),
(2, 1, 0, 0, 0, 4, 0),
(2, 13, 0, 1, 0, 0, 3),
(3, 7, 1, 0, 0, 0, 0),
(3, 9, 0, 0, 0, 0, 1),
(3, 1, 0, 0, 0, 6, 0),
(3, 6, 0, 0, 0, 0, 5),
(4, 7, 1, 0, 0, 0, 0),
(4, 18, 1, 0, 0, 0, 0),
(4, 2, 0, 1, 0, 0, 4),
(4, 1, 0, 0, 0, 3, 0),
(5, 7, 2, 0, 0, 0, 0),
(5, 11, 1, 0, 0, 0, 0),
(5, 8, 1, 0, 0, 0, 0),
(5, 1, 0, 0, 0, 7, 0);
