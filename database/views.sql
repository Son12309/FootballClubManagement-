-- PHẦN 3: VIEW CHO CÁC THỐNG KÊ TỰ ĐỘNG
-- ======================================================

-- View 1: Tính phong độ cầu thủ
DROP VIEW IF EXISTS PhongDo;
CREATE VIEW PhongDo AS
SELECT 
    ct.MaCT,
    ct.HoTen,
    ct.ViTri,
    ct.MaCLB,
    COALESCE(
        SUM(
            ttd.BanThang * 3
          + ttd.SoLanCuuThua * 2
          + ttd.SoLanTakle * 1
          - ttd.TheVang * 2
          - ttd.TheDo * 3
        ), 
        0
    ) AS PhongDo
FROM CauThu ct
LEFT JOIN TrongTranDau ttd ON ct.MaCT = ttd.MaCT
GROUP BY ct.MaCT, ct.HoTen, ct.ViTri, ct.MaCLB;

-- View 2: Tổng lương của từng câu lạc bộ
DROP VIEW IF EXISTS LuongToanDoi;
CREATE VIEW LuongToanDoi AS
SELECT 
    clb.TenCLB,
    SUM(ct.Luong) AS TongLuong
FROM CLB clb
JOIN CauThu ct ON clb.MaCLB = ct.MaCLB
GROUP BY clb.TenCLB;

-- View 3: Thống kê bàn thắng theo cầu thủ
DROP VIEW IF EXISTS ThongKeBanThang;
CREATE VIEW ThongKeBanThang AS
SELECT 
    ct.MaCT,
    ct.HoTen,
    COALESCE(SUM(ttd.BanThang), 0) AS TongBan
FROM CauThu ct
LEFT JOIN TrongTranDau ttd ON ct.MaCT = ttd.MaCT
GROUP BY ct.MaCT, ct.HoTen;
