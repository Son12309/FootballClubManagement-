package service;

import dao.CauThuDAO;
import dao.CauThuDAO.PlayerPhongDo;
import Model.CauThu;

import java.util.List;

public class CauThuService {
    private final CauThuDAO dao = new CauThuDAO();

    // in danh sách (mục 1 phần in danh sách)
    public void printAllPlayers() {
        List<CauThu> all = dao.getAll();
        System.out.printf("%-4s %-25s %-5s %-10s\n", "ID", "HoTen", "VT", "Luong");
        for (CauThu ct : all) {
            System.out.printf("%-4d %-25s %-5s %.2f\n", ct.getMaCT(), ct.getHoTen(), ct.getViTri(), ct.getLuong());
        }
    }

    // tìm kiếm
    public void searchByName(String name) {
        List<CauThu> list = dao.searchByName(name);
        if (list.isEmpty()) System.out.println("Không tìm thấy.");
        else list.forEach(ct -> System.out.printf("%d | %s | %s | %.2f\n", ct.getMaCT(), ct.getHoTen(), ct.getViTri(), ct.getLuong()));
    }

    public void searchBySoAo(int soAo) {
        List<CauThu> list = dao.searchBySoAo(soAo);
        if (list.isEmpty()) System.out.println("Không tìm thấy.");
        else list.forEach(ct -> System.out.printf("%d | %s | %s | %d\n", ct.getMaCT(), ct.getHoTen(), ct.getViTri(), ct.getSoAo()));
    }

    public void searchByViTri(String viTri) {
        List<CauThu> list = dao.searchByViTri(viTri);
        if (list.isEmpty()) System.out.println("Không tìm thấy.");
        else list.forEach(ct -> System.out.printf("%d | %s | %s | %.2f\n", ct.getMaCT(), ct.getHoTen(), ct.getViTri(), ct.getLuong()));
    }

    // top scorer (mục 2)
    public void printTopScorers(int topN) {
        List<String> top = dao.getTopScorers(topN);
        top.forEach(System.out::println);
    }

    // sắp xếp theo giá và lương (mục 3 & 4)
    public void printSortedByGiaMua() {
        dao.getPlayersSortedByGiaMua().forEach(ct -> System.out.printf("%d | %s | %.2f\n", ct.getMaCT(), ct.getHoTen(), ct.getGiaMua()));
    }

    public void printSortedByLuongDesc() {
        dao.getPlayersSortedByLuongDesc().forEach(ct -> System.out.printf("%d | %s | %.2f\n", ct.getMaCT(), ct.getHoTen(), ct.getLuong()));
    }

    // phong do cao nhat (muc 5)
    public void printTopPerformer() {
        PlayerPhongDo p = dao.getTopPlayerByPhongDo();
        if (p == null) System.out.println("Không có dữ liệu phong độ.");
        else System.out.println("Top phong độ: " + p);
    }

    // thanh ly (muc 6)
    public void printPlayersToLiquidate() {
        List<PlayerPhongDo> list = dao.getPlayersToLiquidate();
        if (list.isEmpty()) System.out.println("Không có ai cần thanh lý.");
        else list.forEach(p -> System.out.println(p));
    }

    // doi hinh 11 (muc 7)
    public void printStarting11() {
        List<PlayerPhongDo> team = dao.getStarting11();
        System.out.println("=== ĐỘI HÌNH 11 ĐỀ XUẤT ===");
        int idx = 1;
        for (PlayerPhongDo p : team) {
            System.out.printf("%2d. %s\n", idx++, p);
        }
    }

    // CRUD wrapper (muc 8)
    public boolean addPlayer(CauThu ct) { return dao.insert(ct); }
    public boolean updatePlayer(CauThu ct) { return dao.update(ct); }
    public boolean deletePlayer(int maCT) { return dao.delete(maCT); }
    public CauThu findPlayer(int maCT) { return dao.findById(maCT); }

    // thong ke (muc 9)
    public void printTotalSalaryByCLB(int maCLB) {
        double s = dao.getTotalSalaryByCLB(maCLB);
        System.out.printf("Tổng lương CLB %d: %.2f\n", maCLB, s);
    }

    public void printTop5PhongDo() {
        dao.getTop5PhongDo().forEach(System.out::println);
    }
}

