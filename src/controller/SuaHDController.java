package controller;

import database.SuaHDDao;
import ui.SuaHDOutputUi;

import java.io.File;
import java.time.LocalDate;

public class SuaHDController {
    private SuaHDDao suaHDDao = null;
    private SuaHDOutputUi suaHDOutputUi = null;

    public SuaHDController(SuaHDDao suaHDDao, SuaHDOutputUi suaHDOutputUi) {
        this.suaHDDao = suaHDDao;
        this.suaHDOutputUi = suaHDOutputUi;
    }

    public boolean kiemTraHoaDonTonTai(String hoaDonID, File fileData){
        return suaHDDao.kiemTraHoaDonTonTai(hoaDonID, fileData);
    }

    public void suaHD(String hoaDonID, LocalDate ngayHoaDon, String tenKhachHang, String maPhong, double donGia, double gioThue, File fileData) {
        suaHDDao.suaHD(hoaDonID, ngayHoaDon, tenKhachHang, maPhong, donGia, gioThue, fileData);
        suaHDOutputUi.hienThongBao();
    }

    public void suaHD(String hoaDonID, LocalDate ngayHoaDon, String tenKhachHang, String maPhong, double donGia, int ngayThue, File fileData) {
        suaHDDao.suaHD(hoaDonID, ngayHoaDon, tenKhachHang, maPhong, donGia, ngayThue, fileData);
        suaHDOutputUi.hienThongBao();
    }
}
