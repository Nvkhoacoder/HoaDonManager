package database;

import entity.HoaDon;
import entity.HoaDonGio;
import entity.HoaDonNgay;
import entity.LoaiHD;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HoaDonDatabase {
    private static ArrayList<HoaDon> listHD = new ArrayList<>();;
    private static int count = 0;

    public static void initDatabase() {
        addHoaDon(new HoaDonGio("nvk544", LocalDate.of(2024,04,05),"Nguyễn Vũ Khoa","LM10",100000,15,LoaiHD.GIO));
        addHoaDon(new HoaDonNgay("tnat2806",LocalDate.of(2024,06,28),"Trần Như Ái Trinh","AR10",120000,10,LoaiHD.NGAY));
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

    public static void suaHoaDon(String hoaDonID, LocalDate ngayHoaDon, String tenKhachHang, String maPhong, double donGia, double loaiHD, File fileData) {
        docTuFile(fileData);
        boolean found = false;

        if (hoaDonID != null) {
            for (HoaDon hoaDon : listHD) {
                if (hoaDon.getHoaDonID().equals(hoaDonID)) {
                    hoaDon.setNgayHoaDon(ngayHoaDon);
                    hoaDon.setTenKhachHang(tenKhachHang);
                    hoaDon.setMaPhong(maPhong);
                    hoaDon.setDonGia(donGia);
                    if (hoaDon instanceof HoaDonGio) {
                        ((HoaDonGio) hoaDon).setGioThue(loaiHD);
                    } else if (hoaDon instanceof HoaDonNgay) {
                        ((HoaDonNgay) hoaDon).setNgayThue((int) loaiHD);
                    }
                    found = true;
                    break;
                }
            }

            if (found) {
                try (FileWriter fileWriter = new FileWriter(fileData);
                     BufferedWriter bw = new BufferedWriter(fileWriter)) {
                    for (HoaDon hoaDon : listHD) {
                        if (hoaDon instanceof HoaDonNgay) {
                            HoaDonNgay hoaDonNgay = (HoaDonNgay) hoaDon;
                            bw.write(hoaDonNgay.getHoaDonID() + "," + hoaDonNgay.getNgayHoaDon() + ","
                                    + hoaDonNgay.getTenKhachHang() + "," + hoaDonNgay.getMaPhong() + ","
                                    + hoaDonNgay.getDonGia() + "," + hoaDonNgay.getNgayThue());
                        } else if (hoaDon instanceof HoaDonGio) {
                            HoaDonGio hoaDonGio = (HoaDonGio) hoaDon;
                            bw.write(hoaDonGio.getHoaDonID() + "," + hoaDonGio.getNgayHoaDon() + ","
                                    + hoaDonGio.getTenKhachHang() + "," + hoaDonGio.getMaPhong() + ","
                                    + hoaDonGio.getDonGia() + "," + hoaDonGio.getGioThue());
                        }
                        bw.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Không tìm thấy hóa đơn với mã: " + hoaDonID);
            }
        } else {
            System.out.println("Mã hóa đơn không được để trống.");
        }
    }

    public static boolean xoaHoaDon(String maHoaDon, File fileData) {
        if (maHoaDon == null || maHoaDon.trim().isEmpty()) {
            System.out.println("Mã hóa đơn không được để trống.");
            return false;
        }

        List<String> lines = new ArrayList<>();
        boolean found = false;

        // Đọc file và lọc các dòng không chứa mã hóa đơn cần xóa
        try (BufferedReader reader = new BufferedReader(new FileReader(fileData))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(maHoaDon)) {
                    found = true; // Tìm thấy mã hóa đơn
                } else {
                    lines.add(line); // Giữ lại các dòng khác
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file: " + e.getMessage());
            return false;
        }

        if (!found) {
            System.out.println("Không tìm thấy hóa đơn với mã: " + maHoaDon);
            return false;
        }

        // Ghi lại các dòng còn lại vào file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileData))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file: " + e.getMessage());
            return false;
        }

        return true;
    }

    public static boolean timHoaDon(String hoaDonID, File fileData) {
        if (hoaDonID != null && !hoaDonID.trim().isEmpty()) {
            return kiemTraHoaDonTonTai(hoaDonID, fileData);
        }
        return false;
    }

    public static ArrayList<HoaDon> docTuFile(File fileName) {
        listHD.clear();
        count = 0;

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
                    String loaiString = data[6].trim();
                    LoaiHD loai = LoaiHD.valueOf(loaiString.toUpperCase());
                    HoaDon hoaDon;
                    if(loai == LoaiHD.NGAY){
                        int ngayThue = Integer.parseInt(data[5]);
                        hoaDon = new HoaDonNgay(hoaDonID, ngayHoaDon, tenKhachHang, maPhong, donGia, ngayThue, loai);
                    } else {
                        double gioThue = Double.parseDouble(data[5]);
                        hoaDon = new HoaDonGio(hoaDonID, ngayHoaDon, tenKhachHang, maPhong, donGia, gioThue, loai);
                    }
                    listHD.add(hoaDon);
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listHD;
    }

    public static boolean kiemTraHoaDonTonTai(String maHoaDon, File fileData) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileData))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0) {
                    String hoaDonID = data[0].trim();
                    if (hoaDonID.equals(maHoaDon)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
