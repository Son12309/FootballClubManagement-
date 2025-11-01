-- ======================================================
-- VIEW TRONG DATABASE ARSENAL 2025
-- ======================================================

USE FootballDB;

CREATE OR REPLACE VIEW PhongDo AS
SELECT c.MaCT, c.HoTen, c.ViTri,
       CASE 
         WHEN c.ViTri IN ('CF','RW','LW') THEN SUM(t.BanThang)
         WHEN c.ViTri IN ('CB','RB','LB') THEN SUM(t.SoLanTakle)
         WHEN c.ViTri = 'GK' THEN SUM(t.SoLanCuuThua)
         ELSE 0
       END AS PhongDo
FROM CauThu c
LEFT JOIN TrongTranDau t ON c.MaCT = t.MaCT
GROUP BY c.MaCT, c.HoTen, c.ViTri;

CREATE OR REPLACE VIEW LuongToanDoi AS
SELECT MaCLB, SUM(Luong) AS TongLuong
FROM CauThu
GROUP BY MaCLB;

CREATE OR REPLACE VIEW ThongKeBanThang AS
SELECT c.HoTen, SUM(t.BanThang) AS TongBan
FROM CauThu c
LEFT JOIN TrongTranDau t ON c.MaCT = t.MaCT
GROUP BY c.HoTen;

