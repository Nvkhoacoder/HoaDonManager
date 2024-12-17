package controller;

import database.ThemDao;
import database.ThemHDDao;
import database.ThemHDDataFile;
import entity.HoaDon;
import entity.HoaDonGio;
import entity.HoaDonNgay;
import entity.LoaiHD;
import ui.ThemHDOutputUi;

import java.time.LocalDate;

public class ThemHDController {
    private ThemDao themDao = null;
    private ThemHDOutputUi themHDOutputUi = null;

    public ThemHDController(ThemDao themDao, ThemHDOutputUi themHDOutputUi) {
        this.themDao = themDao;
        this.themHDOutputUi = themHDOutputUi;
    }

    public void themHD(String hoaDonID, LocalDate ngayHoaDon, String tenKhachHang, String maPhong, double donGia, double gioThue, LoaiHD loaiHD) {
        HoaDonGio hoaDonGio = new HoaDonGio(hoaDonID, ngayHoaDon, tenKhachHang, maPhong, donGia, gioThue,LoaiHD.GIO);
        themHDData(hoaDonGio);
    }

    public void themHD(String hoaDonID, LocalDate ngayHoaDon, String tenKhachHang, String maPhong, double donGia, int ngayThue, LoaiHD loaiHD) {
        HoaDonNgay hoaDonNgay = new HoaDonNgay(hoaDonID, ngayHoaDon, tenKhachHang, maPhong, donGia, ngayThue, LoaiHD.NGAY);
        themHDData(hoaDonNgay);
    }

    private void themHDData(HoaDon hoaDon) {
        themDao.themHD(hoaDon);
        themHDOutputUi.hienThongBao();
    }
}
