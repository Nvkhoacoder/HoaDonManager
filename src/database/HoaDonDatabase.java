package database;

import entity.HoaDon;
import entity.HoaDonGio;
import entity.HoaDonNgay;

import java.time.LocalDate;
import java.util.ArrayList;

public class HoaDonDatabase {
    private static ArrayList<HoaDon> listHD = null;
    private static int count = 0;

    public static void initDatabase() {
        listHD = new ArrayList<>();
        addHoaDon(new HoaDonGio("nvk544", LocalDate.of(2024,04,05),"Nguyễn Vũ Khoa","LM10",100000,15));
        addHoaDon(new HoaDonNgay("tnat2806",LocalDate.of(2024,06,28),"Trần Như Ái Trinh","AR10",120000,10));
    }

    public static void addHoaDon(HoaDon hoaDon) {
        listHD.add(hoaDon);
        count++;
    }

    public static ArrayList<HoaDon> queryAllHoaDon() {
        return listHD;
    }

    public static int getCountHD() {
        return count;
    }

    public static void suaHoaDon(String hoaDonID, LocalDate ngayHoaDon, String tenKhachHang, String maPhong, double donGia, double loaiHD) {
        boolean found = false;
        if(hoaDonID != null) {
            for (HoaDon hoaDon : listHD) {
                if(hoaDon.getHoaDonID().equalsIgnoreCase(hoaDonID)) {
                    hoaDon.setNgayHoaDon(ngayHoaDon);
                    hoaDon.setTenKhachHang(tenKhachHang);
                    hoaDon.setMaPhong(maPhong);
                    hoaDon.setDonGia(donGia);
                    if(hoaDon instanceof HoaDonGio) {
                        ((HoaDonGio) hoaDon).setGioThue(loaiHD);
                    } else if(hoaDon instanceof HoaDonNgay) {
                        ((HoaDonNgay) hoaDon).setNgayThue((int) loaiHD);
                    }
                    found = true;
                    break;
                }
            }
            if(!found) {
                System.out.println("Không tìm thấy Hoá Đơn " + hoaDonID + " trong danh sách Hoá Đơn");
            }
        }
    }

    public static HoaDon xoaHoaDon(String hoaDonID) {
        if(hoaDonID != null) {
            for (HoaDon hoaDon : listHD) {
                if(hoaDon.getHoaDonID().equalsIgnoreCase(hoaDonID)) {
                    listHD.remove(hoaDon);
                    return hoaDon;
                }
            }
        }
        return null;
    }

    public static HoaDon timHoaDon(String hoaDonID) {
        if(hoaDonID != null) {
            for (HoaDon hoaDon : listHD) {
                if(hoaDon.getHoaDonID().equalsIgnoreCase(hoaDonID)) {
                    return hoaDon;
                }
            }
        }
        return null;
    }
}
