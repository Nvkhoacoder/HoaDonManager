package ui;

import controller.ThemHDController;
import entity.LoaiHD;

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
        hoaDonIDPrompt = "MA HOA DON: ";
        ngayHoaDonPrompt = "NGAY LAM HOA DON (dd/MM/yyyy): ";
        tenKhachHangPrompt = "TEN KHACH HANG: ";
        maPhongPrompt = "MA PHONG: ";
        donGiaPrompt = "DON GIA: ";
        gioThuePrompt = "SO GIO THUE: ";
        ngayThuePrompt = "SO NGAY THUE: ";
        loaiHD = "LOAI HOA DON [\"Gio\" / \"Ngay\"]: ";
    }

    public void nhapThongTinHoaDon() {
        screenPrompt.print(hoaDonIDPrompt);screenPrompt.flush();
        String maHoaDon = keyBoard.nextLine();
        if (maHoaDon == null || maHoaDon.isEmpty()) {
            screenPrompt.println("Ma hoa don khong duoc de trong.");
            return;
        }

        screenPrompt.print(ngayHoaDonPrompt); screenPrompt.flush();
        String ngayHoaDonStr = keyBoard.nextLine();
        LocalDate ngayHoaDon;
        try {
            ngayHoaDon = LocalDate.parse(ngayHoaDonStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            screenPrompt.println("Ngay hoa don khong hop le.");
            return;
        }

        screenPrompt.print(tenKhachHangPrompt);screenPrompt.flush();
        String tenKhachHang = keyBoard.nextLine();
        if (tenKhachHang == null || tenKhachHang.isEmpty()) {
            screenPrompt.println("Ten khach hang khong duoc de trong.");
            return;
        }

        screenPrompt.print(maPhongPrompt);screenPrompt.flush();
        String maPhong = keyBoard.nextLine();
        if (maPhong == null || maPhong.isEmpty()) {
            screenPrompt.println("Ma phong khong duoc de trong.");
            return;
        }

        screenPrompt.print(donGiaPrompt);screenPrompt.flush();
        double donGia;
        try {
            donGia = keyBoard.nextDouble();keyBoard.nextLine();
        } catch (Exception e) {
            screenPrompt.println("Don gia phai la so hop le.");
            keyBoard.nextLine();
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
                screenPrompt.println("So gio thue phai la so hop le.");
                keyBoard.nextLine();
                return;
            }

            themHDController.themHD(maHoaDon, ngayHoaDon, tenKhachHang, maPhong, donGia, gioThue, LoaiHD.GIO);
        } else if ("Ngay".equalsIgnoreCase(loaiHD)) {
            screenPrompt.print(ngayThuePrompt);screenPrompt.flush();
            int ngayThue;
            try {
                ngayThue = keyBoard.nextInt();keyBoard.nextLine();
            } catch (Exception e) {
                screenPrompt.println("So ngay thue phai la so hop le.");
                keyBoard.nextLine();
                return;
            }

            themHDController.themHD(maHoaDon, ngayHoaDon, tenKhachHang, maPhong, donGia, ngayThue,LoaiHD.NGAY);
        } else {
            screenPrompt.println("Loai hoa don khong hop le. Vui long nhap 'Gio' hoac 'Ngay'.");
        }
    }
}
