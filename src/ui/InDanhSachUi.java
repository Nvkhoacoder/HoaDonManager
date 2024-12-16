package ui;

import controller.InDanhSachController;
import entity.HoaDon;
import entity.HoaDonGio;
import entity.HoaDonNgay;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class InDanhSachUi {
    private PrintWriter screenOut = null;
    private InDanhSachController inDSHDControl = null;
    private File fileName = null;

    public InDanhSachUi(PrintWriter screenOut, InDanhSachController inDSHDControl, File fileName) {
        this.screenOut = screenOut;
        this.inDSHDControl = inDSHDControl;
        this.fileName = fileName;
    }

    public void hienThiDanhSachHoaDon() {
        List<HoaDon> danhSachHoaDon = inDSHDControl.layDanhSachHoaDon(fileName);

        if (danhSachHoaDon.isEmpty()) {
            screenOut.println("Danh Sach Hoa Don Trong!!!");
        } else {
            screenOut.println("Danh Sach Hoa Don: ");
            for (HoaDon hoaDon : danhSachHoaDon) {
                screenOut.println("Ma Hoa Don: " + hoaDon.getHoaDonID());
                screenOut.println("Ngay Hoa Don: " + hoaDon.getNgayHoaDon());
                screenOut.println("Ten Khach Hang: " + hoaDon.getTenKhachHang());
                if (hoaDon instanceof HoaDonGio) {
                    HoaDonGio hdGio = (HoaDonGio) hoaDon;
                    screenOut.println("Loai Hoa Don: Theo Gio");
                    screenOut.println("Ma Phong: " + hoaDon.getMaPhong());
                    screenOut.println("So Gio Thue: " + hdGio.getGioThue());
                    screenOut.println("Đơn Giá: " + hdGio.getDonGia());
                } else if (hoaDon instanceof HoaDonNgay) {
                    HoaDonNgay hdNgay = (HoaDonNgay) hoaDon;
                    screenOut.println("Loai Hoa Don: Theo Ngay");
                    screenOut.println("Ma Phong: " + hoaDon.getMaPhong());
                    screenOut.println("So Ngay Thue: " + hdNgay.getNgayThue());
                    screenOut.println("Đơn Giá: " + hdNgay.getDonGia());
                }
                screenOut.println("---------------------------------");
            }
            screenOut.flush();
        }
    }
}
