package database;

import java.io.File;

public class XoaHDDao {
    private File fileData = null;

    public XoaHDDao(File fileData){
        this.fileData = fileData;
    }

    public boolean xoaHoaDon(String hoaDonId, File fileData){
        return HoaDonDatabase.xoaHoaDon(hoaDonId, fileData);
    }
}
