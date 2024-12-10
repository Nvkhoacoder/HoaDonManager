package controller;

import database.SuaHDDao;
import ui.SuaHDOutputUi;

import java.time.LocalDate;

public class SuaHDController {
    private SuaHDDao suaHDDao = null;
    private SuaHDOutputUi suaHDOutputUi = null;

    public SuaHDController(SuaHDDao suaHDDao, SuaHDOutputUi suaHDOutputUi) {
        this.suaHDDao = suaHDDao;
        this.suaHDOutputUi = suaHDOutputUi;
    }

    public void suaHD(String hoaDonID, LocalDate ngayHoaDon, String tenKhachHang, String maPhong, double donGia, double gioThue) {
        suaHDDao.suaHD(hoaDonID, ngayHoaDon, tenKhachHang, maPhong, donGia, gioThue);
        suaHDOutputUi.hienThongBao();
    }

    public void suaHD(String hoaDonID, LocalDate ngayHoaDon, String tenKhachHang, String maPhong, double donGia, int ngayThue) {
        suaHDDao.suaHD(hoaDonID, ngayHoaDon, tenKhachHang, maPhong, donGia, ngayThue);
        suaHDOutputUi.hienThongBao();
    }
}
