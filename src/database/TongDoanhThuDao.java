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

    public List<HoaDon> getHoaDonTheoThang(int thang, int nam, File fileData){
        List<HoaDon> danhSachHoaDon = HoaDonDatabase.docTuFile(fileData);

        // Filter the list based on the month and year
        return danhSachHoaDon.stream()
                .filter(hd -> hd.getNgayHoaDon() != null &&
                        hd.getNgayHoaDon().getMonthValue() == thang &&
                        hd.getNgayHoaDon().getYear() == nam)
                .collect(Collectors.toList());
    }
}
