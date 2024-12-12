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
        if (maHoaDon == null || maHoaDon.isEmpty()) {
            screenPrompt.println("Mã hóa đơn không được để trống.");
            return;
        }

        screenPrompt.print(ngayHoaDonPrompt); screenPrompt.flush();
        String ngayHoaDonStr = keyBoard.nextLine();
        LocalDate ngayHoaDon;
        try {
            ngayHoaDon = LocalDate.parse(ngayHoaDonStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            screenPrompt.println("Ngày hóa đơn không hợp lệ.");
            return;
        }

        screenPrompt.print(tenKhachHangPrompt);screenPrompt.flush();
        String tenKhachHang = keyBoard.nextLine();
        if (tenKhachHang == null || tenKhachHang.isEmpty()) {
            screenPrompt.println("Tên khách hàng không được để trống.");
            return;
        }

        screenPrompt.print(maPhongPrompt);screenPrompt.flush();
        String maPhong = keyBoard.nextLine();
        if (maPhong == null || maPhong.isEmpty()) {
            screenPrompt.println("Mã phòng không được để trống.");
            return;
        }

        screenPrompt.print(donGiaPrompt);screenPrompt.flush();
        double donGia;
        try {
            donGia = keyBoard.nextDouble();keyBoard.nextLine();
        } catch (Exception e) {
            screenPrompt.println("Đơn giá phải là số hợp lệ.");
            keyBoard.nextLine(); // Clear buffer
            return;
        }

        screenPrompt.print(loaiHD);screenPrompt.flush();
        String loaiHD = keyBoard.nextLine();
        if ("Gio".equalsIgnoreCase(loaiHD)) {
            screenPrompt.print(gioThuePrompt);screenPrompt.flush();
            double gioThue;
            try {
                gioThue = keyBoard.nextDouble();keyBoard.nextLine();
            } catch (Exception e) {
                screenPrompt.println("Số giờ thuê phải là số hợp lệ.");
                keyBoard.nextLine(); // Clear buffer
                return;
            }

            themHDController.themHD(maHoaDon, ngayHoaDon, tenKhachHang, maPhong, donGia, gioThue);
        } else if ("Ngay".equalsIgnoreCase(loaiHD)) {
            screenPrompt.print(ngayThuePrompt);screenPrompt.flush();
            int ngayThue;
            try {
                ngayThue = keyBoard.nextInt();keyBoard.nextLine();
            } catch (Exception e) {
                screenPrompt.println("Số ngày thuê phải là số hợp lệ.");
                keyBoard.nextLine(); // Clear buffer
                return;
            }

            themHDController.themHD(maHoaDon, ngayHoaDon, tenKhachHang, maPhong, donGia, ngayThue);
        } else {
            screenPrompt.println("Loại hóa đơn không hợp lệ. Vui lòng nhập 'Gio' hoặc 'Ngay'.");
        }
    }
}
