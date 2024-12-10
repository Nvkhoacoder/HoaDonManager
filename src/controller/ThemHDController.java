package controller;

import database.ThemHDDao;
import database.ThemHDDataFile;
import entity.HoaDon;
import entity.HoaDonGio;
import entity.HoaDonNgay;
import ui.ThemHDOutputUi;

import java.time.LocalDate;

public class ThemHDController {
    private ThemHDDataFile themHDDataFile = null;
    private ThemHDOutputUi themHDOutputUi = null;

    public ThemHDController(ThemHDDataFile themHDDataFile, ThemHDOutputUi themHDOutputUi) {
        this.themHDDataFile = themHDDataFile;
        this.themHDOutputUi = themHDOutputUi;
    }

    public void themHD(String hoaDonID, LocalDate ngayHoaDon, String tenKhachHang, String maPhong, double donGia, double gioThue) {
        HoaDonGio hoaDonGio = new HoaDonGio(hoaDonID, ngayHoaDon, tenKhachHang, maPhong, donGia, gioThue);
        themHDData(hoaDonGio);
    }

    public void themHD(String hoaDonID, LocalDate ngayHoaDon, String tenKhachHang, String maPhong, double donGia, int ngayThue) {
        HoaDonNgay hoaDonNgay = new HoaDonNgay(hoaDonID, ngayHoaDon, tenKhachHang, maPhong, donGia, ngayThue);
        themHDData(hoaDonNgay);
    }

    private void themHDData(HoaDon hoaDon) {
        themHDDataFile.themHD(hoaDon);
        themHDOutputUi.hienThongBao();
    }
}
