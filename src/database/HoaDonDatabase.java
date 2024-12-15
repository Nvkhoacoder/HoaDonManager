package database;

import entity.HoaDon;
import entity.HoaDonGio;
import entity.HoaDonNgay;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HoaDonDatabase {
    private static ArrayList<HoaDon> listHD = new ArrayList<>();;
    private static int count = 0;


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

    public static boolean xoaHoaDon(String hoaDonID,File fileData) {
        if (hoaDonID == null || hoaDonID.trim().isEmpty()) {
            System.out.println("Mã hóa đơn không được để trống.");
            return false;
        }

        List<HoaDon> listHD = new ArrayList<>();
        HoaDon hoaDonToRemove = null;

        try (BufferedReader br = new BufferedReader(new FileReader(fileData))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    String id = data[0];
                    LocalDate ngayHoaDon = LocalDate.parse(data[1]);
                    String tenKhachHang = data[2];
                    String maPhong = data[3];
                    double donGia = Double.parseDouble(data[4]);
                    double loaiHD = Double.parseDouble(data[5]);

                    HoaDon hoaDon;
                    if (loaiHD % 1 == 0) {
                        hoaDon = new HoaDonNgay(id, ngayHoaDon, tenKhachHang, maPhong, donGia, (int) loaiHD);
                    } else {
                        hoaDon = new HoaDonGio(id, ngayHoaDon, tenKhachHang, maPhong, donGia, loaiHD);
                    }
                    listHD.add(hoaDon);

                    if (id.equalsIgnoreCase(hoaDonID)) {
                        hoaDonToRemove = hoaDon;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (hoaDonToRemove != null) {
            listHD.remove(hoaDonToRemove);

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileData))) {
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
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean timHoaDon(String hoaDonID, File fileData) {
        if (hoaDonID != null && !hoaDonID.trim().isEmpty()) {
            return kiemTraHoaDonTonTai(hoaDonID, fileData);
        }
        return false;
    }

    public static void ghiVaoFile(File fileName) {
        try {
            FileWriter fileStream = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fileStream);
            for (HoaDon hoaDon : listHD) {
                bw.write(hoaDon.toString());
                bw.newLine();
            }
            bw.flush();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static List<HoaDon> docTuFile(File fileName) {
        listHD.clear();
        count = 0;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fileReader);
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    String hoaDonID = data[0];
                    LocalDate ngayHoaDon = LocalDate.parse(data[1]);
                    String tenKhachHang = data[2];
                    String maPhong = data[3];
                    double donGia = Double.parseDouble(data[4]);
                    double loaiHD = Double.parseDouble(data[5]);

                    HoaDon hoaDon;
                    if (loaiHD % 1 == 0) {
                        hoaDon = new HoaDonNgay(hoaDonID, ngayHoaDon, tenKhachHang, maPhong, donGia, (int) loaiHD);
                    } else {
                        hoaDon = new HoaDonGio(hoaDonID, ngayHoaDon, tenKhachHang, maPhong, donGia, loaiHD);
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
