/ sửa nhiều
package dao;

import Model.TrongTranDau;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrongTranDauDAO {

    // Lấy tất cả bản ghi TrongTranDau
    public List<TrongTranDau> getAll() {
        List<TrongTranDau> list = new ArrayList<>();
        String sql = "SELECT MaTD, MaCT, TheVang, TheDo, BanThang, SoLanCuuThua, SoLanTakle FROM TrongTranDau";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // Lấy tất cả cầu thủ tham gia 1 trận (theo MaTD)
    public List<TrongTranDau> getByMatch(int maTD) {
        List<TrongTranDau> list = new ArrayList<>();
        String sql = "SELECT * FROM TrongTranDau WHERE MaTD = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTD);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // Lấy tất cả bản ghi liên quan tới 1 cầu thủ (theo MaCT)
    public List<TrongTranDau> getByPlayer(int maCT) {
        List<TrongTranDau> list = new ArrayList<>();
        String sql = "SELECT * FROM TrongTranDau WHERE MaCT = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maCT);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // Lấy 1 bản ghi cụ thể (khóa kép)
    public TrongTranDau find(int maTD, int maCT) {
        String sql = "SELECT * FROM TrongTranDau WHERE MaTD = ? AND MaCT = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTD);
            ps.setInt(2, maCT);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // Thêm 1 bản ghi
    public boolean insert(TrongTranDau t) {
        String sql = "INSERT INTO TrongTranDau (MaTD, MaCT, TheVang, TheDo, BanThang, SoLanCuuThua, SoLanTakle) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, t.getMaTD());
            ps.setInt(2, t.getMaCT());
            ps.setInt(3, t.getTheVang());
            ps.setInt(4, t.getTheDo());
            ps.setInt(5, t.getBanThang());
            ps.setInt(6, t.getSoLanCuuThua());
            ps.setInt(7, t.getSoLanTakle());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    // Cập nhật 1 bản ghi
    public boolean update(TrongTranDau t) {
        String sql = "UPDATE TrongTranDau SET TheVang = ?, TheDo = ?, BanThang = ?, SoLanCuuThua = ?, SoLanTakle = ? WHERE MaTD = ? AND MaCT = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, t.getTheVang());
            ps.setInt(2, t.getTheDo());
            ps.setInt(3, t.getBanThang());
            ps.setInt(4, t.getSoLanCuuThua());
            ps.setInt(5, t.getSoLanTakle());
            ps.setInt(6, t.getMaTD());
            ps.setInt(7, t.getMaCT());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    // Xóa 1 bản ghi
    public boolean delete(int maTD, int maCT) {
        String sql = "DELETE FROM TrongTranDau WHERE MaTD = ? AND MaCT = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTD);
            ps.setInt(2, maCT);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    // Kiểm tra tồn tại
    public boolean exists(int maTD, int maCT) {
        String sql = "SELECT 1 FROM TrongTranDau WHERE MaTD = ? AND MaCT = ? LIMIT 1";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTD);
            ps.setInt(2, maCT);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // Tổng bàn thắng của một cầu thủ trong tất cả trận (hữu ích cho top scorer)
    public int getTotalGoalsByPlayer(int maCT) {
        String sql = "SELECT COALESCE(SUM(BanThang),0) AS TongBan FROM TrongTranDau WHERE MaCT = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maCT);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("TongBan");
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    // Tổng thẻ vàng/thẻ đỏ của 1 cầu thủ
    public int getTotalYellowByPlayer(int maCT) {
        String sql = "SELECT COALESCE(SUM(TheVang),0) AS Tong FROM TrongTranDau WHERE MaCT = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maCT);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("Tong");
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    // Tổng bàn thắng 1 trận (hữu ích cho thống kê trận)
    public int getTotalGoalsByMatch(int maTD) {
        String sql = "SELECT COALESCE(SUM(BanThang),0) AS TongBan FROM TrongTranDau WHERE MaTD = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTD);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("TongBan");
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    // Lấy danh sách cầu thủ (MaCT) và bàn thắng trong 1 trận (sắp xếp giảm dần theo bàn)
    public List<TrongTranDau> getGoalsByMatchSorted(int maTD) {
        List<TrongTranDau> list = new ArrayList<>();
        String sql = "SELECT * FROM TrongTranDau WHERE MaTD = ? ORDER BY BanThang DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTD);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // Map ResultSet -> Model.TrongTranDau
    private TrongTranDau mapRow(ResultSet rs) throws SQLException {
        TrongTranDau t = new TrongTranDau();
        t.setMaTD(rs.getInt("MaTD"));
        t.setMaCT(rs.getInt("MaCT"));
        t.setTheVang(rs.getInt("TheVang"));
        t.setTheDo(rs.getInt("TheDo"));
        t.setBanThang(rs.getInt("BanThang"));
        t.setSoLanCuuThua(rs.getInt("SoLanCuuThua"));
        t.setSoLanTakle(rs.getInt("SoLanTakle"));
        return t;
    }
}

