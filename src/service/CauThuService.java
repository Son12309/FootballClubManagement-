//CRUD, tìm kiếm , sắp xếp
package service;

import dao.CauThuDAO;
import Model.CauThu;
import java.util.List;

public class CauThuService {
    private final CauThuDAO dao = new CauThuDAO();

    public void printAllPlayers() {
        List<CauThu> all = dao.getAll();
        System.out.printf("%-4s %-25s %-5s %-10s\n", "ID", "HoTen", "VT", "Luong");
        for (CauThu ct : all) {
            System.out.printf("%-4d %-25s %-5s %.2f\n", ct.getMaCT(), ct.getHoTen(), ct.getViTri(), ct.getLuong());
        }
    }

    public void searchByName(String name) {
        List<CauThu> list = dao.searchByName(name);
        if (list.isEmpty()) System.out.println("Not Found!");
        else list.forEach(ct -> System.out.printf("%d | %s | %s | %.2f\n", 
            ct.getMaCT(), ct.getHoTen(), ct.getViTri(), ct.getLuong()));
    }

    public void searchBySoAo(int soAo) {
        List<CauThu> list = dao.searchBySoAo(soAo);
        if (list.isEmpty()) System.out.println("Not Found!");
        else list.forEach(ct -> System.out.printf("%d | %s | %s | %d\n",
            ct.getMaCT(), ct.getHoTen(), ct.getViTri(), ct.getSoAo()));
    }

    public void searchByViTri(String viTri) {
        List<CauThu> list = dao.searchByViTri(viTri);
        if (list.isEmpty()) System.out.println("Not Found!");
        else list.forEach(ct -> System.out.printf("%d | %s | %s | %.2f\n",
            ct.getMaCT(), ct.getHoTen(), ct.getViTri(), ct.getLuong()));
    }

    public void printSortedByGiaMua() {
        dao.getPlayersSortedByGiaMua().forEach(ct -> 
            System.out.printf("%d | %s | %.2f\n", ct.getMaCT(), ct.getHoTen(), ct.getGiaMua()));
    }

    public void printSortedByLuongDesc() {
        dao.getPlayersSortedByLuongDesc().forEach(ct ->
            System.out.printf("%d | %s | %.2f\n", ct.getMaCT(), ct.getHoTen(), ct.getLuong()));
    }

    public boolean addPlayer(CauThu ct) { return dao.insert(ct); }
    public boolean updatePlayer(CauThu ct) { return dao.update(ct); }
    public boolean deletePlayer(int maCT) { return dao.delete(maCT); }
    public CauThu findPlayer(int maCT) { return dao.findById(maCT); }
}
