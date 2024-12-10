package entity;

import java.time.LocalDate;

public class HoaDonNgay extends HoaDon {
    private int ngayThue;

    public HoaDonNgay(String hoaDonID, LocalDate ngayHoaDon, String tenKhachHang, String maPhong, double donGia, int ngayThue) {
        super(hoaDonID, ngayHoaDon, tenKhachHang, maPhong, donGia);
        this.ngayThue = ngayThue;
    }

    public int getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(int ngayThue) {
        this.ngayThue = ngayThue;
    }

    @Override
    public double tongTien() {
        if(ngayThue <= 7){
            return ngayThue * donGia;
        } else {
            return ((donGia * 7) + (ngayThue - 7) * donGia * 0.8);
        }
    }
}
