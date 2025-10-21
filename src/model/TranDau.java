package Model;

public class TranDau {
    private int maTD;
    private String diaDiem;
    private String ngayThiDau;
    private int doiChuNha;
    private int doiKhach;

    public TranDau() {}

    public TranDau(int maTD, String diaDiem, String ngayThiDau, int doiChuNha, int doiKhach) {
        this.maTD = maTD;
        this.diaDiem = diaDiem;
        this.ngayThiDau = ngayThiDau;
        this.doiChuNha = doiChuNha;
        this.doiKhach = doiKhach;
    }

    // Getter & Setter
    public int getMaTD() {
        return maTD;
    }

    public void setMaTD(int maTD) {
        this.maTD = maTD;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public String getNgayThiDau() {
        return ngayThiDau;
    }

    public void setNgayThiDau(String ngayThiDau) {
        this.ngayThiDau = ngayThiDau;
    }

    public int getDoiChuNha() {
        return doiChuNha;
    }

    public void setDoiChuNha(int doiChuNha) {
        this.doiChuNha = doiChuNha;
    }

    public int getDoiKhach() {
        return doiKhach;
    }

    public void setDoiKhach(int doiKhach) {
        this.doiKhach = doiKhach;
    }

    @Override
    public String toString() {
        return "TranDau{" +
                "MaTD: " + maTD +
                ", DiaDiem: '" + diaDiem + '\'' +
                ", NgayThiDau: '" + ngayThiDau + '\'' +
                ", DoiChuNha: " + doiChuNha +
                ", DoiKhach: " + doiKhach +
                '}';
    }
}

