package database;

import entity.HoaDon;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TongDoanhThuDao {
    private File fileData = null;

    public TongDoanhThuDao(File fileData) {
        this.fileData = fileData;
    }

    public ArrayList<HoaDon> getHoaDonTheoThang(int thang, int nam, File fileData) {
        // Initialize danhSachHoaDon as ArrayList
        ArrayList<HoaDon> danhSachHoaDon = HoaDonDatabase.docTuFile(fileData);

        // Filter the list based on the month and year and return as ArrayList
        return danhSachHoaDon.stream()
                .filter(hd -> hd.getNgayHoaDon() != null &&
                        hd.getNgayHoaDon().getMonthValue() == thang &&
                        hd.getNgayHoaDon().getYear() == nam)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
