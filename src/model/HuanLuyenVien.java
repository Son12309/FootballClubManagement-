package Model;

public class HuanLuyenVien {
    private int maHLV;
    private String hoTen;
    private String quocTich;
    private String ngaySinh; 
    private double luong;
    private int kinhNghiem;
    private String loiChoi;
    private int maCLB;

  
    public HuanLuyenVien() {}


    public HuanLuyenVien(int maHLV, String hoTen, String quocTich, String ngaySinh, double luong, int kinhNghiem, String loiChoi, int maCLB) {
        this.maHLV = maHLV;
        this.hoTen = hoTen;
        this.quocTich = quocTich;
        this.ngaySinh = ngaySinh;
        this.luong = luong;
        this.kinhNghiem = kinhNghiem;
        this.loiChoi = loiChoi;
        this.maCLB = maCLB;
    }

    // Getter & Setter
    public int getMaHLV() {
        return maHLV;
    }

    public void setMaHLV(int maHLV) {
        this.maHLV = maHLV;
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

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public int getKinhNghiem() {
        return kinhNghiem;
    }

    public void setKinhNghiem(int kinhNghiem) {
        this.kinhNghiem = kinhNghiem;
    }

    public String getLoiChoi() {
        return loiChoi;
    }

    public void setLoiChoi(String loiChoi) {
        this.loiChoi = loiChoi;
    }

    public int getMaCLB() {
        return maCLB;
    }

    public void setMaCLB(int maCLB) {
        this.maCLB = maCLB;
    }

    @Override
    public String toString() {
        return "HuanLuyenVien{" +
                "maHLV: " + maHLV +
                ", HoTen: '" + hoTen + '\'' +
                ", QuocTich: '" + quocTich + '\'' +
                ", NgaySinh: '" + ngaySinh + '\'' +
                ", Luong: " + luong +
                ", KinhNghiem: " + kinhNghiem +
                ", LoiChoi: '" + loiChoi + '\'' +
                ", MaCLB: " + maCLB +
                '}';
    }
}

