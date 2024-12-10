package database;

import entity.HoaDon;
import entity.HoaDonGio;
import entity.HoaDonNgay;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SuaHDDao {
    private File fileData;

    public SuaHDDao(String fileName) {
        this.fileData = new File(fileName);
    }

    public void suaHD(String hoaDonID, LocalDate ngayHoaDon, String tenKhachHang, String maPhong, double donGia, double loaiHD) {
        List<HoaDon> danhSachHoaDon = new ArrayList<>();
        FileReader fileReader = null;
        BufferedReader br = null;
        FileWriter fileWriter = null;
        BufferedWriter bw = null;

        try {
            // Đọc file hiện tại
            fileReader = new FileReader(fileData);
            br = new BufferedReader(fileReader);
            String line;

            while ((line = br.readLine()) != null) {
                // Chuyển đổi dòng thành đối tượng HoaDon
                HoaDon hd = HoaDon.fromString(line);
                if (hd.getHoaDonID().equals(hoaDonID)) {
                    // Cập nhật thông tin hóa đơn
                    hd.setNgayHoaDon(ngayHoaDon);
                    hd.setTenKhachHang(tenKhachHang);
                    hd.setMaPhong(maPhong);
                    hd.setDonGia(donGia);
                    if(hoaDon instanceof HoaDonGio) {
                        ((HoaDonGio) hoaDon).setGioThue(loaiHD);
                    } else if(hoaDon instanceof HoaDonNgay) {
                        ((HoaDonNgay) hoaDon).setNgayThue((int) loaiHD);
                    }
                }
                danhSachHoaDon.add(hd);
            }
            br.close();

            // Ghi lại toàn bộ danh sách vào file
            fileWriter = new FileWriter(fileData);
            bw = new BufferedWriter(fileWriter);

            for (HoaDon hd : danhSachHoaDon) {
                bw.write(hd.toString());
                bw.newLine();
            }
            bw.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (fileReader != null) fileReader.close();
                if (bw != null) bw.close();
                if (fileWriter != null) fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
