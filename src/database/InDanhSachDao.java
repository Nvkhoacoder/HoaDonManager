package database;

import entity.HoaDon;

import java.io.File;
import java.util.List;

public class InDanhSachDao {
    private File fileName = null;

    public InDanhSachDao(File fileName) {
        this.fileName = fileName;
    }
    public  List<HoaDon> layDanhSachHD(File fileName){
        return HoaDonDatabase.docTuFile(fileName);
    }
}
