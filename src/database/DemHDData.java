package database;

import entity.HoaDon;
import entity.HoaDonGio;
import entity.HoaDonNgay;

import java.io.File;
import java.util.List;

public class DemHDData {
    private File fileData = null;

    public DemHDData(File fileData){
        this.fileData = fileData;
    }

    public void demSoLuongHD() {
        List<HoaDon> danhSachHoaDon = HoaDonDatabase.docTuFile(fileData);
        int tongGio = 0;
        int tongNgay = 0;
        for (HoaDon hoaDon : danhSachHoaDon) {
            if (hoaDon instanceof HoaDonGio) {
                tongGio++;
            } else if (hoaDon instanceof HoaDonNgay) {
                tongNgay++;
            }
        }
        System.out.println("Số lượng hóa đơn giờ: " + tongGio);
        System.out.println("Số lượng hóa đơn ngày: " + tongNgay);
    }
}
