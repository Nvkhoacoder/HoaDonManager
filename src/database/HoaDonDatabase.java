package database;

import entity.HoaDon;
import entity.HoaDonGio;
import entity.HoaDonNgay;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        if(hoaDonID != null) {
            for (HoaDon hoaDon : listHD) {
                if (hoaDon.getHoaDonID().equals(hoaDonID)) {
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
                System.out.println("Hóa đơn không tìm thấy.");
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

    public static void docTuFile(File fileName) {
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
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
