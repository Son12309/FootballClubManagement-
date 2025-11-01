-- ======================================================
-- TRIGGERS CHO DATABASE ARSENAL
-- ======================================================

USE FootballDB;

CREATE TABLE IF NOT EXISTS LogCauThu (
    MaLog INT AUTO_INCREMENT PRIMARY KEY,
    NoiDung VARCHAR(255),
    NgayThucHien DATETIME DEFAULT CURRENT_TIMESTAMP
);

DROP TRIGGER IF EXISTS After_Insert_CauThu;
CREATE TRIGGER After_Insert_CauThu
AFTER INSERT ON CauThu
FOR EACH ROW
BEGIN
    INSERT INTO LogCauThu (NoiDung)
    VALUES (CONCAT('Đã thêm cầu thủ mới: ', NEW.HoTen));
END;

DROP TRIGGER IF EXISTS Before_Delete_CauThu;
CREATE TRIGGER Before_Delete_CauThu
BEFORE DELETE ON CauThu
FOR EACH ROW
BEGIN
    IF EXISTS (SELECT * FROM TrongTranDau WHERE MaCT = OLD.MaCT) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Không thể xóa cầu thủ đang thi đấu!';
    END IF;
END;

