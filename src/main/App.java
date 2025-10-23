package main;

import Model.CauThu;
import service.CauThuService;
import service.DoiHinhService;
import service.PhongDoService;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CauThuService cauThuService = new CauThuService();
        DoiHinhService doiHinhService = new DoiHinhService();
        PhongDoService phongDoService = new PhongDoService();

        int choice;
        do {
            System.out.println("Mời bạn chọn một trong các tính năng dưới đây:");
            System.out.println("1. Danh sách cầu thủ");
            System.out.println("2. Tìm kiếm cầu thủ");
            System.out.println("3. Sắp xếp cầu thủ");
            System.out.println("4. Thêm / Sửa / Xóa cầu thủ");
            System.out.println("5. Đội hình ra sân & thanh lý");
            System.out.println("6. Thống kê phong độ & top scorers");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                // ------------------------
                case 1 -> {
                    System.out.println("\nDanh sách cầu thủ:");
                    cauThuService.printAllPlayers();
                }

                // ------------------------
                case 2 -> {
                    System.out.println("\nTìm kiếm cầu thủ theo đặc điểm:");
                    System.out.println("1. Theo tên");
                    System.out.println("2. Theo số áo");
                    System.out.println("3. Theo vị trí");
                    System.out.print("Chọn: ");
                    int sub = sc.nextInt(); sc.nextLine();

                    switch (sub) {
                        case 1 -> {
                            System.out.print("Nhập tên: ");
                            String name = sc.nextLine();
                            cauThuService.searchByName(name);
                        }
                        case 2 -> {
                            System.out.print("Nhập số áo: ");
                            int soAo = sc.nextInt();
                            cauThuService.searchBySoAo(soAo);
                        }
                        case 3 -> {
                            System.out.print("Nhập vị trí (GK, DF, MF, FW): ");
                            String vt = sc.nextLine();
                            cauThuService.searchByViTri(vt);
                        }
                    }
                }

                // ------------------------
                case 3 -> {
                    System.out.println("\nSắp xếp cầu thủ:");
                    System.out.println("1. Theo giá mua tăng dần");
                    System.out.println("2. Theo lương giảm dần");
                    System.out.print("Chọn: ");
                    int sub = sc.nextInt();
                    if (sub == 1) cauThuService.printSortedByGiaMua();
                    else cauThuService.printSortedByLuongDesc();
                }

                // ------------------------
                case 4 -> {
                    System.out.println("\nCập nhật dữ liệu cầu thủ:");
                    System.out.println("1. Thêm cầu thủ");
                    System.out.println("2. Cập nhật cầu thủ");
                    System.out.println("3. Xóa cầu thủ");
                    System.out.print("Chọn: ");
                    int sub = sc.nextInt();
                    sc.nextLine();

                    switch (sub) {
                        case 1 -> {
                            CauThu ct = new CauThu();
                            System.out.print("Họ tên: "); ct.setHoTen(sc.nextLine());
                            System.out.print("Vị trí: "); ct.setViTri(sc.nextLine());
                            System.out.print("Số áo: "); ct.setSoAo(sc.nextInt());
                            System.out.print("Lương: "); ct.setLuong(sc.nextDouble());
                            System.out.print("Giá mua: "); ct.setGiaMua(sc.nextDouble());
                            System.out.print("Mã CLB: "); ct.setMaCLB(sc.nextInt());
                            System.out.println(cauThuService.addPlayer(ct)
                                    ? "Thêm thành công!"
                                    : "Thêm thất bại!");
                        }
                        case 2 -> {
                            System.out.print("Nhập mã cầu thủ cần sửa: ");
                            int id = sc.nextInt(); sc.nextLine();
                            CauThu ct = cauThuService.findPlayer(id);
                            if (ct == null) {
                                System.out.println("Không tìm thấy cầu thủ!");
                            } else {
                                System.out.print("Tên mới (" + ct.getHoTen() + "): ");
                                String ten = sc.nextLine();
                                if (!ten.isEmpty()) ct.setHoTen(ten);
                                System.out.print("Lương mới (" + ct.getLuong() + "): ");
                                double luong = sc.nextDouble();
                                if (luong > 0) ct.setLuong(luong);
                                System.out.println(cauThuService.updatePlayer(ct)
                                        ? "Cập nhật thành công!"
                                        : "Lỗi khi cập nhật!");
                            }
                        }
                        case 3 -> {
                            System.out.print("Nhập mã cầu thủ cần xóa: ");
                            int id = sc.nextInt();
                            System.out.println(cauThuService.deletePlayer(id)
                                    ? "Đã xóa thành công!"
                                    : "Không thể xóa (có thể đang thi đấu)!");
                        }
                    }
                }

                // ------------------------
                case 5 -> {
                    System.out.println("\nĐội hình và thanh lý");
                    System.out.println("1. In đội hình ra sân");
                    System.out.println("2. Danh sách cầu thủ cần thanh lý");
                    System.out.print("Chọn: ");
                    int sub = sc.nextInt();
                    if (sub == 1) doiHinhService.printStarting11();
                    else doiHinhService.printPlayersToLiquidate();
                }

                // ------------------------
                case 6 -> {
                    System.out.println("\nPhong độ và top score:");
                    System.out.println("1. Top ghi bàn");
                    System.out.println("2. Cầu thủ phong độ cao nhất");
                    System.out.println("3. Top 5 phong độ");
                    System.out.print("Chọn: ");
                    int sub = sc.nextInt();

                    switch (sub) {
                        case 1 -> {
                            System.out.print("Nhập số lượng top N: ");
                            int n = sc.nextInt();
                            phongDoService.printTopScorers(n);
                        }
                        case 2 -> phongDoService.printTopPerformer();
                        case 3 -> phongDoService.printTop5PhongDo();
                    }
                }

                // ------------------------
                case 0 -> System.out.println("Thoát chương trình. Cám ơn bạn đã tin tưởng app của chúng tôi!\n Tạm biệt và hẹn gặp lại!.");
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (choice != 0);

        sc.close();
    }
}

