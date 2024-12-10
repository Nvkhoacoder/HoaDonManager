package database;

import entity.HoaDon;
import entity.HoaDonGio;
import entity.HoaDonNgay;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        List<HoaDon> danhSachHoaDon = new ArrayList<>();
        FileReader fileReader = null;
        BufferedReader br = null;
        FileWriter fileWriter = null;
        BufferedWriter bw = null;
        if(hoaDonID != null) {
            for (HoaDon hoaDon : listHD) {
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
            System.out.println("Danh sách hóa đơn từ file:");
            String line;
            while ((line = br.readLine()) != null) {
                // Tách chuỗi theo dấu ',' để lấy các giá trị của hóa đơn
                String[] data = line.split(",");
                if (data.length == 6) {
                    // Kiểm tra xem dữ liệu có đúng theo định dạng
                    String hoaDonID = data[0];
                    LocalDate ngayHoaDon = LocalDate.parse(data[1]);
                    String tenKhachHang = data[2];
                    String maPhong = data[3];
                    double donGia = Double.parseDouble(data[4]);
                    double loaiHD = Double.parseDouble(data[5]);

                    // Tạo đối tượng HoaDon tương ứng (HoaDonGio hoặc HoaDonNgay)
                    HoaDon hoaDon;
                    if (loaiHD % 1 == 0) {  // Kiểm tra nếu loaiHD là số nguyên (ứng với HoaDonNgay)
                        hoaDon = new HoaDonNgay(hoaDonID, ngayHoaDon, tenKhachHang, maPhong, donGia, (int) loaiHD);
                    } else {  // Nếu loaiHD không phải số nguyên, là HoaDonGio
                        hoaDon = new HoaDonGio(hoaDonID, ngayHoaDon, tenKhachHang, maPhong, donGia, loaiHD);
                    }

                    // Thêm hóa đơn vào danh sách
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
