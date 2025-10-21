package Model;

public class TrongTranDau {
    private int maTD;            // Mã trận đấu
    private int maCT;            // Mã cầu thủ
    private int theVang;         // Số thẻ vàng
    private int theDo;           // Số thẻ đỏ
    private int banThang;        // Số bàn thắng
    private int soLanCuuThua;    // Số lần cứu thua (thủ môn)
    private int soLanTakle;      // Số lần tắc bóng

    // Constructor không tham số
    public TrongTranDau() {}

    // Constructor đầy đủ
    public TrongTranDau(int maTD, int maCT, int theVang, int theDo, int banThang, int soLanCuuThua, int soLanTakle) {
        this.maTD = maTD;
        this.maCT = maCT;
        this.theVang = theVang;
        this.theDo = theDo;
        this.banThang = banThang;
        this.soLanCuuThua = soLanCuuThua;
        this.soLanTakle = soLanTakle;
    }

    // Getter & Setter
    public int getMaTD() {
        return maTD;
    }

    public void setMaTD(int maTD) {
        this.maTD = maTD;
    }

    public int getMaCT() {
        return maCT;
    }

    public void setMaCT(int maCT) {
        this.maCT = maCT;
    }

    public int getTheVang() {
        return theVang;
    }

    public void setTheVang(int theVang) {
        this.theVang = theVang;
    }

    public int getTheDo() {
        return theDo;
    }

    public void setTheDo(int theDo) {
        this.theDo = theDo;
    }

    public int getBanThang() {
        return banThang;
    }

    public void setBanThang(int banThang) {
        this.banThang = banThang;
    }

    public int getSoLanCuuThua() {
        return soLanCuuThua;
    }

    public void setSoLanCuuThua(int soLanCuuThua) {
        this.soLanCuuThua = soLanCuuThua;
    }

    public int getSoLanTakle() {
        return soLanTakle;
    }

    public void setSoLanTakle(int soLanTakle) {
        this.soLanTakle = soLanTakle;
    }

    @Override
    public String toString() {
        return "TrongTranDau{" +
                "MaTD: " + maTD +
                ", MaCT: " + maCT +
                ", TheVang: " + theVang +
                ", TheDo: " + theDo +
                ", BanThang: " + banThang +
                ", SoLanCuuThua: " + soLanCuuThua +
                ", SoLanTakle: " + soLanTakle +
                '}';
    }
}

