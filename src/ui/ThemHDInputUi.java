package ui;

import controller.ThemHDController;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ThemHDInputUi {
    private ThemHDController themHDController = null;
    private Scanner keyBoard = null;
    private PrintWriter screenPrompt = null;
    private String hoaDonIDPrompt, ngayHoaDonPrompt, tenKhachHangPrompt, maPhongPrompt,
            donGiaPrompt, gioThuePrompt, ngayThuePrompt, loaiHD;

    public ThemHDInputUi(ThemHDController themHDController, Scanner keyBoard, PrintWriter screenPrompt) {
        this.themHDController = themHDController;
        this.keyBoard = keyBoard;
        this.screenPrompt = screenPrompt;
        hoaDonIDPrompt = "MÃ HOÁ ĐƠN: ";
        ngayHoaDonPrompt = "NGÀY LÀM HOÁ ĐƠN (dd/MM/yyyy): ";
        tenKhachHangPrompt = "TÊN KHÁCH HÀNG: ";
        maPhongPrompt = "MÃ PHÒNG: ";
        donGiaPrompt = "ĐƠN GIÁ: ";
        gioThuePrompt = "SỐ GIỜ THUÊ: ";
        ngayThuePrompt = "SỐ NGÀY THUÊ: ";
        loaiHD = "LOẠI HOÁ ĐƠN [\"Gio\" / \"Ngay\"]: ";
    }

    public void nhapThongTinHoaDon() {
        screenPrompt.print(hoaDonIDPrompt);screenPrompt.flush();
        String maHoaDon = keyBoard.nextLine();
        screenPrompt.print(ngayHoaDonPrompt); screenPrompt.flush();
        String ngayHoaDonStr = keyBoard.nextLine();
        LocalDate ngayHoaDon = LocalDate.parse(ngayHoaDonStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        screenPrompt.print(tenKhachHangPrompt);screenPrompt.flush();
        String tenKhachHang = keyBoard.nextLine();
        screenPrompt.print(maPhongPrompt);screenPrompt.flush();
        String maPhong = keyBoard.nextLine();
        screenPrompt.print(donGiaPrompt);screenPrompt.flush();
        double donGia = keyBoard.nextDouble();keyBoard.nextLine();
        screenPrompt.print(loaiHD);screenPrompt.flush();
        String loaiHD = keyBoard.nextLine();
        keyBoard.nextLine();

        if("Gio".equalsIgnoreCase(loaiHD)){
            screenPrompt.print(gioThuePrompt);screenPrompt.flush();
            double gioThue = keyBoard.nextDouble();keyBoard.nextLine();

            themHDController.themHD(maHoaDon,ngayHoaDon,tenKhachHang,maPhong,donGia,gioThue);
        } else if("Ngay".equalsIgnoreCase(loaiHD)){
            screenPrompt.print(ngayThuePrompt);screenPrompt.flush();
            int ngayThue = keyBoard.nextInt();keyBoard.nextLine();

            themHDController.themHD(maHoaDon,ngayHoaDon,tenKhachHang,maPhong,donGia,ngayThue);
        }
    }
}
