package controller;

import database.ThemHDDao;
import entity.HoaDon;
import entity.HoaDonGio;
import entity.HoaDonNgay;
import ui.ThemHDOutputUi;

import java.time.LocalDate;

public class ThemHDController {
    private ThemHDDao themHDDao = null;
    private ThemHDOutputUi themHDOutputUi = null;

    public ThemHDController(ThemHDDao themHDDao, ThemHDOutputUi themHDOutputUi) {
        this.themHDDao = themHDDao;
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
        themHDDao.ThemHD(hoaDon);
        themHDOutputUi.hienThongBao();
    }
}
