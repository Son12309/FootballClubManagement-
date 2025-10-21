// code này còn cần sửa nhiều
package dao;

import Model.CauThu;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CauThuDAO {

    // ------------- CRUD -------------
    public boolean insert(CauThu ct) {
        String sql = "INSERT INTO CauThu (HoTen, QuocTich, ViTri, NgaySinh, GiaMua, Luong, SoAo, MaCLB) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ct.getHoTen());
            ps.setString(2, ct.getQuocTich());
            ps.setString(3, ct.getViTri());
            ps.setDate(4, ct.getNgaySinh() == null ? null : java.sql.Date.valueOf(ct.getNgaySinh()));
            ps.setDouble(5, ct.getGiaMua());
            ps.setDouble(6, ct.getLuong());
            ps.setInt(7, ct.getSoAo());
            ps.setInt(8, ct.getMaCLB());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(CauThu ct) {
        String sql = "UPDATE CauThu SET HoTen=?, QuocTich=?, ViTri=?, NgaySinh=?, GiaMua=?, Luong=?, SoAo=?, MaCLB=? WHERE MaCT=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ct.getHoTen());
            ps.setString(2, ct.getQuocTich());
            ps.setString(3, ct.getViTri());
            ps.setDate(4, ct.getNgaySinh() == null ? null : java.sql.Date.valueOf(ct.getNgaySinh()));
            ps.setDouble(5, ct.getGiaMua());
            ps.setDouble(6, ct.getLuong());
            ps.setInt(7, ct.getSoAo());
            ps.setInt(8, ct.getMaCLB());
            ps.setInt(9, ct.getMaCT());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int maCT) {
        String sql = "DELETE FROM CauThu WHERE MaCT = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maCT);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            // có trigger ngăn xóa khi đang thi đấu sẽ throw exception - xử lý hiển thị
            System.err.println("Lỗi khi xóa: " + e.getMessage());
            return false;
        }
    }

    public CauThu findById(int maCT) {
        String sql = "SELECT * FROM CauThu WHERE MaCT = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maCT);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCauThu(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ------------- Basic list -------------
    public List<CauThu> getAll() {
        List<CauThu> list = new ArrayList<>();
        String sql = "SELECT * FROM CauThu";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapResultSetToCauThu(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // ------------- Tìm kiếm (mục 1) -------------
    public List<CauThu> searchByName(String name) {
        List<CauThu> list = new ArrayList<>();
        String sql = "SELECT * FROM CauThu WHERE HoTen LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapResultSetToCauThu(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public List<CauThu> searchBySoAo(int soAo) {
        List<CauThu> list = new ArrayList<>();
        String sql = "SELECT * FROM CauThu WHERE SoAo = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, soAo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapResultSetToCauThu(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public List<CauThu> searchByViTri(String viTri) {
        List<CauThu> list = new ArrayList<>();
        String sql = "SELECT * FROM CauThu WHERE ViTri = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, viTri);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapResultSetToCauThu(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // ------------- Top scorer (mục 2) -------------
    public List<String> getTopScorers(int limit) {
        List<String> result = new ArrayList<>();
        String sql = "SELECT c.HoTen, SUM(t.BanThang) AS TongBan " +
                     "FROM CauThu c LEFT JOIN TrongTranDau t ON c.MaCT = t.MaCT " +
                     "GROUP BY c.MaCT, c.HoTen ORDER BY TongBan DESC LIMIT ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(rs.getString("HoTen") + " - " + rs.getInt("TongBan") + " bàn");
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return result;
    }

    // ------------- Sắp xếp (mục 3 & 4) -------------
    public List<CauThu> getPlayersSortedByGiaMua() {
        return getPlayersByOrder("GiaMua ASC");
    }

    public List<CauThu> getPlayersSortedByLuongDesc() {
        return getPlayersByOrder("Luong DESC");
    }

    private List<CauThu> getPlayersByOrder(String orderBy) {
        List<CauThu> list = new ArrayList<>();
        String sql = "SELECT * FROM CauThu ORDER BY " + orderBy;
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapResultSetToCauThu(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // ------------- PhongDo từ view PhongDo (mục 5) -------------
    // Lấy tất cả bản ghi view PhongDo (MaCT, HoTen, ViTri, PhongDo)
    public List<PlayerPhongDo> getPhongDoAll() {
        List<PlayerPhongDo> list = new ArrayList<>();
        String sql = "SELECT MaCT, HoTen, ViTri, PhongDo FROM PhongDo";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new PlayerPhongDo(rs.getInt("MaCT"),
                                           rs.getString("HoTen"),
                                           rs.getString("ViTri"),
                                           rs.getDouble("PhongDo")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public PlayerPhongDo getTopPlayerByPhongDo() {
        String sql = "SELECT MaCT, HoTen, ViTri, PhongDo FROM PhongDo ORDER BY PhongDo DESC LIMIT 1";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return new PlayerPhongDo(rs.getInt("MaCT"), rs.getString("HoTen"),
                                         rs.getString("ViTri"), rs.getDouble("PhongDo"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // ------------- Thanh lý (mục 6) -------------
    // Logic thresholds: CF: PhongDo < 0.3, CM/CB: PhongDo < 2
    public List<PlayerPhongDo> getPlayersToLiquidate() {
        List<PlayerPhongDo> list = new ArrayList<>();
        String sql = "SELECT MaCT, HoTen, ViTri, PhongDo FROM PhongDo WHERE " +
                     "(ViTri IN ('CF','LW','RW') AND PhongDo < 0.3) OR " +
                     "(ViTri IN ('CM','CDM','CAM','CB') AND PhongDo < 2) OR " +
                     "(ViTri = 'GK' AND PhongDo < 0.5)";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new PlayerPhongDo(rs.getInt("MaCT"), rs.getString("HoTen"),
                                           rs.getString("ViTri"), rs.getDouble("PhongDo")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // ------------- Đội hình 11 (mục 7) -------------
    // Chọn: 1 GK, 4 defenders (CB,RB,LB), 4 midfielders (CM,CDM,CAM), 2 forwards (CF,LW,RW)
    public List<PlayerPhongDo> getStarting11() {
        List<PlayerPhongDo> team = new ArrayList<>();
        String[] gk = {"GK"};
        String[] def = {"CB","RB","LB"};
        String[] mid = {"CM","CDM","CAM"};
        String[] fwd = {"CF","LW","RW"};

        // helper to select top N by viTri set
        team.addAll(selectTopByPositions(1, gk));
        team.addAll(selectTopByPositions(4, def));
        team.addAll(selectTopByPositions(4, mid));
        team.addAll(selectTopByPositions(2, fwd));

        // nếu chưa đủ 11, lấy thêm theo phong độ chung
        if (team.size() < 11) {
            List<PlayerPhongDo> all = getPhongDoAll();
            all.sort((a,b) -> Double.compare(b.getPhongDo(), a.getPhongDo()));
            for (PlayerPhongDo p : all) {
                boolean exists = false;
                for (PlayerPhongDo q : team) if (q.getMaCT() == p.getMaCT()) { exists = true; break; }
                if (!exists) {
                    team.add(p);
                    if (team.size() == 11) break;
                }
            }
        }

        return team.size() > 11 ? team.subList(0,11) : team;
    }

    private List<PlayerPhongDo> selectTopByPositions(int limit, String[] positions) {
        List<PlayerPhongDo> list = new ArrayList<>();
        StringBuilder inClause = new StringBuilder();
        for (int i = 0; i < positions.length; i++) {
            inClause.append("'").append(positions[i]).append("'");
            if (i < positions.length - 1) inClause.append(",");
        }
        String sql = String.format("SELECT MaCT, HoTen, ViTri, PhongDo FROM PhongDo WHERE ViTri IN (%s) ORDER BY PhongDo DESC LIMIT %d",
                inClause.toString(), limit);
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new PlayerPhongDo(rs.getInt("MaCT"), rs.getString("HoTen"),
                                           rs.getString("ViTri"), rs.getDouble("PhongDo")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // ------------- Thống kê & in (mục 9) -------------
    public double getTotalSalaryByCLB(int maCLB) {
        String sql = "SELECT SUM(Luong) AS TongLuong FROM CauThu WHERE MaCLB = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maCLB);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getDouble("TongLuong");
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public List<String> getTop5PhongDo() {
        List<String> res = new ArrayList<>();
        String sql = "SELECT HoTen, PhongDo FROM PhongDo ORDER BY PhongDo DESC LIMIT 5";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                res.add(rs.getString("HoTen") + " - " + rs.getDouble("PhongDo"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return res;
    }

    // ------------- Utility mapping -------------
    private CauThu mapResultSetToCauThu(ResultSet rs) throws SQLException {
        CauThu ct = new CauThu();
        ct.setMaCT(rs.getInt("MaCT"));
        ct.setHoTen(rs.getString("HoTen"));
        ct.setQuocTich(rs.getString("QuocTich"));
        ct.setViTri(rs.getString("ViTri"));
        Date d = rs.getDate("NgaySinh");
        ct.setNgaySinh(d == null ? null : d.toString());
        ct.setGiaMua(rs.getDouble("GiaMua"));
        ct.setLuong(rs.getDouble("Luong"));
        ct.setSoAo(rs.getInt("SoAo"));
        ct.setMaCLB(rs.getInt("MaCLB"));
        return ct;
    }

    // Inner helper class to represent PhongDo rows
    public static class PlayerPhongDo {
        private int maCT;
        private String hoTen;
        private String viTri;
        private double phongDo;

        public PlayerPhongDo(int maCT, String hoTen, String viTri, double phongDo) {
            this.maCT = maCT;
            this.hoTen = hoTen;
            this.viTri = viTri;
            this.phongDo = phongDo;
        }
        public int getMaCT() { return maCT; }
        public String getHoTen() { return hoTen; }
        public String getViTri() { return viTri; }
        public double getPhongDo() { return phongDo; }

        @Override
        public String toString() {
            return hoTen + " (" + viTri + ") - PhongDo: " + phongDo;
        }
    }
}

