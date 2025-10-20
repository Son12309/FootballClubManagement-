package Model;
import java.time.LocalDate;

public class CauThu {
    private String hoTen;
    private String quocTich;
    private String viTri;
    private LocalDate ngaySinh;
    private int giaMua;    
    private int luong;        
    private int soAo;
    private int maCLB;
   
    public CauThu() {
    }
    

    public CauThu(String hoTen, String quocTich, String viTri, LocalDate ngaySinh, 
                  int giaMua, int luong, int soAo, int maCLB) {
        this.hoTen = hoTen;
        this.quocTich = quocTich;
        this.viTri = viTri;
        this.ngaySinh = ngaySinh;
        this.giaMua = giaMua;
        this.luong = luong;
        this.soAo = soAo;
        this.maCLB = maCLB;
    }
    
    // Getter và Setter
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
    
    public LocalDate getNgaySinh() {
        return ngaySinh;
    }
    
    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    
    public int getGiaMua() {
        return giaMua;
    }
    
    public void setGiaMua(int giaMua) {
        this.giaMua = giaMua;
    }
    
    public int getLuong() {
        return luong;
    }
    
    public void setLuong(int luong) {
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
        return "Cau Thu{" +
                "Ho Ten:'" + hoTen + '\'' +
                ", Quoc Tich:'" + quocTich + '\'' +
                ", Vi Tri:'" + viTri + '\'' +
                ", Ngay Sinh=" + ngaySinh +
                ", Gia Mua:" + giaMua +
                ", Luong:" + luong +
                ", So Ao:" + soAo +
                ", Ma CLB:" + maCLB +
                '}';
    }
}
