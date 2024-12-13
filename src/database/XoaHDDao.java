package database;

import java.io.File;

public class XoaHDDao {
    private File fileData = null;

    public XoaHDDao(File fileData){
        this.fileData = fileData;
    }

    public void xoaHoaDon(String hoaDonId, File fileData){
        HoaDonDatabase.xoaHoaDon(hoaDonId, fileData);
    }
}
