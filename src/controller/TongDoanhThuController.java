package controller;

import database.TongDoanhThuDao;
import entity.HoaDon;
import ui.DoanhThuTheoThangOutputUi;

import java.io.File;
import java.util.List;

public class TongDoanhThuController {
    private TongDoanhThuDao tongDoanhThuDao = null;
    private DoanhThuTheoThangOutputUi doanhThuTheoThangOutputUi = null;

    public TongDoanhThuController(TongDoanhThuDao tongDoanhThuDao, DoanhThuTheoThangOutputUi doanhThuTheoThangOutputUi) {
        this.tongDoanhThuDao = tongDoanhThuDao;
        this.doanhThuTheoThangOutputUi = doanhThuTheoThangOutputUi;
    }

    public void tinhThanhTienMonthHD(int thang, int nam, File fileData) {
        // Kiểm tra dữ liệu tháng và năm hợp lệ
        if (thang < 1 || thang > 12) {
            doanhThuTheoThangOutputUi.hienThongBao("Thang khong hop le. Vui long nhap lai.");
            return;
        }

        if (nam <= 0) {
            doanhThuTheoThangOutputUi.hienThongBao("Nam khong hop le. Vui long nhap lai.");
            return;
        }

        // Giả sử bạn có phương thức lấy danh sách hóa đơn theo tháng và năm
        List<HoaDon> hoaDons =tongDoanhThuDao.getHoaDonTheoThang(thang,nam,fileData);

        if (hoaDons.isEmpty()) {
            doanhThuTheoThangOutputUi.hienThongBao("Khong co hoa don trong thang " + thang + " nam " + nam);
            return;
        }

        double tongThanhTien = hoaDons.stream().mapToDouble(HoaDon::tongTien).sum();
        double trungBinhThanhTien = tongThanhTien / hoaDons.size();

        doanhThuTheoThangOutputUi.hienThongBao("Tong thanh tien trong thang " + thang + " nam " + nam + ": " + tongThanhTien);
        doanhThuTheoThangOutputUi.hienThongBao("Trung binh thanh tien trong thang " + thang + " nam " + nam + ": " + trungBinhThanhTien);
    }
}
