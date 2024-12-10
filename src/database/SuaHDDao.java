package database;

import java.time.LocalDate;

public class SuaHDDao {

    public void suaHD(String hoaDonID, LocalDate ngayHoaDon, String tenKhachHang, String maPhong, double donGia, double loaiHD) {
        HoaDonDatabase.suaHoaDon(hoaDonID, ngayHoaDon, tenKhachHang, maPhong, donGia, loaiHD);
    }
}
