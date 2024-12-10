package entity;

import java.time.LocalDate;

public abstract class HoaDon {
    protected String hoaDonID;
    protected LocalDate ngayHoaDon;
    protected String tenKhachHang;
    protected String maPhong;
    protected double donGia;

    public HoaDon(String hoaDonID, LocalDate ngayHoaDon, String tenKhachHang, String maPhong, double donGia) {
        this.hoaDonID = hoaDonID;
        this.ngayHoaDon = ngayHoaDon;
        this.tenKhachHang = tenKhachHang;
        this.maPhong = maPhong;
        this.donGia = donGia;
    }

    public String getHoaDonID() {
        return hoaDonID;
    }

    public void setHoaDonID(String hoaDonID) {
        this.hoaDonID = hoaDonID;
    }

    public LocalDate getNgayHoaDon() {
        return ngayHoaDon;
    }

    public void setNgayHoaDon(LocalDate ngayHoaDon) {
        this.ngayHoaDon = ngayHoaDon;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public abstract double tongTien();
}
