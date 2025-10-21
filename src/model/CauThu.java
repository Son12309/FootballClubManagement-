package Model;

public class CauThu {
    private int maCT;
    private String hoTen;
    private String quocTich;
    private String viTri;
    private String ngaySinh; 
    private double giaMua;
    private double luong;
    private int soAo;
    private int maCLB;

    // Constructor không tham số
    public CauThu() {}

    // Constructor đầy đủ
    public CauThu(int maCT, String hoTen, String quocTich, String viTri, String ngaySinh, double giaMua, double luong, int soAo, int maCLB) {
        this.maCT = maCT;
        this.hoTen = hoTen;
        this.quocTich = quocTich;
        this.viTri = viTri;
        this.ngaySinh = ngaySinh;
        this.giaMua = giaMua;
        this.luong = luong;
        this.soAo = soAo;
        this.maCLB = maCLB;
    }

    // Getter & Setter
    public int getMaCT() {
        return maCT;
    }

    public void setMaCT(int maCT) {
        this.maCT = maCT;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public double getGiaMua() {
        return giaMua;
    }

    public void setGiaMua(double giaMua) {
        this.giaMua = giaMua;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public int getSoAo() {
        return soAo;
    }

    public void setSoAo(int soAo) {
        this.soAo = soAo;
    }

    public int getMaCLB() {
        return maCLB;
    }

    public void setMaCLB(int maCLB) {
        this.maCLB = maCLB;
    }

    @Override
    public String toString() {
        return "CauThu{" +
                "MaCT: " + maCT +
                ", HoTen: '" + hoTen + '\'' +
                ", QuocTich: '" + quocTich + '\'' +
                ", ViTri: '" + viTri + '\'' +
                ", NgaySinh: '" + ngaySinh + '\'' +
                ", GiaMua: " + giaMua +
                ", Luong: " + luong +
                ", SoAo: " + soAo +
                ", MaCLB: " + maCLB +
                '}';
    }
}
