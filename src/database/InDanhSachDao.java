package database;

import entity.HoaDon;
import entity.HoaDonGio;
import entity.HoaDonNgay;
import entity.LoaiHD;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class InDanhSachDao implements InDSDao{
    private File fileName = null;

    public InDanhSachDao(File fileName) {
        this.fileName = fileName;
    }


    public ArrayList<HoaDon> layDanhSachHD(){
        ArrayList<HoaDon> layDanhSach = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fileReader);
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {
                    String hoaDonID = data[0];
                    LocalDate ngayHoaDon = LocalDate.parse(data[1]);
                    String tenKhachHang = data[2];
                    String maPhong = data[3];
                    double donGia = Double.parseDouble(data[4]);
                    String loaiString = data[6].trim(); // Remove extra spaces
                    LoaiHD loai = LoaiHD.valueOf(loaiString.toUpperCase());
                    HoaDon hoaDon;
                    if(loai == LoaiHD.NGAY){
                        int ngayThue = Integer.parseInt(data[5]);
                        hoaDon = new HoaDonNgay(hoaDonID, ngayHoaDon, tenKhachHang, maPhong, donGia, ngayThue, loai);
                    } else {
                        double gioThue = Double.parseDouble(data[5]);
                        hoaDon = new HoaDonGio(hoaDonID, ngayHoaDon, tenKhachHang, maPhong, donGia, gioThue, loai);
                    }
                    layDanhSach.add(hoaDon);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return layDanhSach;
    }
}
