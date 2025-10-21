//Top score, phong độ, form
package service;

import dao.CauThuDAO;
import dao.CauThuDAO.PlayerPhongDo;
import java.util.List;

public class PhongDoService {
    private final CauThuDAO dao = new CauThuDAO();

    public void printTopScorers(int topN) {
        List<String> top = dao.getTopScorers(topN);
        top.forEach(System.out::println);
    }

    public void printTopPerformer() {
        PlayerPhongDo p = dao.getTopPlayerByPhongDo();
        if (p == null) System.out.println("No form data available!");
        else System.out.println("Top performer: " + p);
    }

    public void printTop5PhongDo() {
        dao.getTop5PhongDo().forEach(System.out::println);
    }
}

