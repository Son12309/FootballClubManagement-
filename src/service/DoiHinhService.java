//Đội hình ra sân, thanh lý
package service;

import dao.CauThuDAO;
import dao.CauThuDAO.PlayerPhongDo;
import java.util.List;

public class DoiHinhService {
    private final CauThuDAO dao = new CauThuDAO();

    public void printStarting11() {
        List<PlayerPhongDo> team = dao.getStarting11();
        System.out.println("=== ĐỘI HÌNH CHÍNH ===");
        int i = 1;
        for (PlayerPhongDo p : team)
            System.out.printf("%2d. %s\n", i++, p);
    }

    public void printPlayersToLiquidate() {
        List<PlayerPhongDo> list = dao.getPlayersToLiquidate();
        if (list.isEmpty()) System.out.println("Không có cầu thủ cần thanh lý.");
        else list.forEach(System.out::println);
    }
}

