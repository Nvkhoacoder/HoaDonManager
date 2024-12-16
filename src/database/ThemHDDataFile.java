package database;

import entity.HoaDon;
import entity.HoaDonGio;
import entity.HoaDonNgay;


import java.io.*;

public class ThemHDDataFile {
    private File fileData = null;

    public ThemHDDataFile(String fileName) {
        this.fileData = new File(fileName);
    }

    public void themHD(HoaDon hd) {
        FileWriter fileStream = null;
        BufferedWriter bw = null;

        try {
            // Open file in append mode
            fileStream = new FileWriter(fileData, true);
            bw = new BufferedWriter(fileStream);

            // Check if hd is an instance of HoaDonGio or HoaDonNgay
            if (hd instanceof HoaDonGio) {
                // Cast hd to HoaDonGio to access the gioThue property
                HoaDonGio hoaDonGio = (HoaDonGio) hd;
                // Write the data in the format for HoaDonGio
                bw.write(hd.getHoaDonID() + "," + hd.getNgayHoaDon() + "," + hd.getTenKhachHang() + ","
                        + hd.getMaPhong() + "," + hd.getDonGia() + "," + hoaDonGio.getGioThue() + "," + hd.getLoaiHD());
            } else if (hd instanceof HoaDonNgay) {
                // Cast hd to HoaDonNgay to access the ngayThue property
                HoaDonNgay hoaDonNgay = (HoaDonNgay) hd;
                // Write the data in the format for HoaDonNgay
                bw.write(hd.getHoaDonID() + "," + hd.getNgayHoaDon() + "," + hd.getTenKhachHang() + ","
                        + hd.getMaPhong() + "," + hd.getDonGia() + "," + hoaDonNgay.getNgayThue() + "," + hd.getLoaiHD());
            }

            // Add a new line to separate entries
            bw.newLine();

            // Flush and close the BufferedWriter
            bw.flush();
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();  // Handle FileNotFoundException
        } catch (IOException e) {
            e.printStackTrace();  // Handle IOException
        }
    }

}
