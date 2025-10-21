package Model;

public class CLB {
    private int maCLB;
    private String tenCLB;
    private String quocGia;
    private String ngayThanhLap; 
    private String sanNha;
    private String giaiDau;

    public CLB() {}

    public CLB(int maCLB, String tenCLB, String quocGia, String ngayThanhLap, String sanNha, String giaiDau) {
        this.maCLB = maCLB;
        this.tenCLB = tenCLB;
        this.quocGia = quocGia;
        this.ngayThanhLap = ngayThanhLap;
        this.sanNha = sanNha;
        this.giaiDau = giaiDau;
    }

    // Getter & Setter
    public int getMaCLB() {
        return maCLB;
    }

    public void setMaCLB(int maCLB) {
        this.maCLB = maCLB;
    }

    public String getTenCLB() {
        return tenCLB;
    }

    public void setTenCLB(String tenCLB) {
        this.tenCLB = tenCLB;
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    public String getNgayThanhLap() {
        return ngayThanhLap;
    }

    public void setNgayThanhLap(String ngayThanhLap) {
        this.ngayThanhLap = ngayThanhLap;
    }

    public String getSanNha() {
        return sanNha;
    }

    public void setSanNha(String sanNha) {
        this.sanNha = sanNha;
    }

    public String getGiaiDau() {
        return giaiDau;
    }

    public void setGiaiDau(String giaiDau) {
        this.giaiDau = giaiDau;
    }

    @Override
    public String toString() {
        return "CLB{" +
                "MaCLB: " + maCLB +
                ", TenCLB: '" + tenCLB + '\'' +
                ", QuocGia: '" + quocGia + '\'' +
                ", NgayThanhLap: '" + ngayThanhLap + '\'' +
                ", SanNha: '" + sanNha + '\'' +
                ", GiaiDau: '" + giaiDau + '\'' +
                '}';
    }
}

