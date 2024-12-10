package ui;


import controller.SuaHDController;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SuaHDInputUi {
    private SuaHDController suaHDController = null;
    private Scanner keyBoard = null;
    private PrintWriter screenPrompt = null;
    private String hoaDonIDPrompt, ngayHoaDonPrompt, tenKhachHangPrompt, maPhongPrompt,
            donGiaPrompt, gioThuePrompt, ngayThuePrompt, loaiHD;

    public SuaHDInputUi(SuaHDController suaHDController, Scanner keyBoard, PrintWriter screenPrompt) {
        this.suaHDController = suaHDController;
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

    public void nhapThongTinSuaHD() {
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

            suaHDController.suaHD(maHoaDon,ngayHoaDon,tenKhachHang,maPhong,donGia,gioThue);
        } else if("Ngay".equalsIgnoreCase(loaiHD)){
            screenPrompt.print(ngayThuePrompt);screenPrompt.flush();
            int ngayThue = keyBoard.nextInt();keyBoard.nextLine();

            suaHDController.suaHD(maHoaDon,ngayHoaDon,tenKhachHang,maPhong,donGia,ngayThue);
        }
    }
}
