-- ======================================================
-- STORED PROCEDURES & TRIGGERS (FIXED)
-- ======================================================

DELIMITER $$

-- Stored Procedures
DROP PROCEDURE IF EXISTS TimCauThuTheoCLB $$
CREATE PROCEDURE TimCauThuTheoCLB(IN tenCLB VARCHAR(100))
BEGIN
    SELECT c.HoTen, c.ViTri, c.Luong
    FROM CauThu c
    JOIN CLB clb ON c.MaCLB = clb.MaCLB
    WHERE clb.TenCLB = tenCLB;
END $$

DROP PROCEDURE IF EXISTS TopCauThuPhongDo $$
CREATE PROCEDURE TopCauThuPhongDo()
BEGIN
    SELECT * FROM PhongDo
    ORDER BY PhongDo DESC
    LIMIT 5;
END $$

DROP PROCEDURE IF EXISTS CapNhatLuong $$
CREATE PROCEDURE CapNhatLuong(IN tyle DECIMAL(5,2))
BEGIN
    UPDATE CauThu
    SET Luong = Luong * (1 + tyle / 100);
END $$
