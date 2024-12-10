package database;



import java.io.*;
import java.time.LocalDate;


public class SuaHDDao {
    private File fileData = null;

    public SuaHDDao(String fileName) {
        this.fileData = new File(fileName);
    }

    public void suaHD(String hoaDonID, LocalDate ngayHoaDon, String tenKhachHang, String maPhong, double donGia, double loaiHD, File fileData) {
        HoaDonDatabase.suaHoaDon(hoaDonID, ngayHoaDon, tenKhachHang, maPhong, donGia, loaiHD, fileData);
    }
}
