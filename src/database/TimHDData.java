package database;

import java.io.File;

public class TimHDData {
    private File fileName = null;

    public TimHDData(File fileName) {
        this.fileName = fileName;
    }

    public boolean timHD(String hoaDonID, File fileName) {
        return HoaDonDatabase.timHoaDon(hoaDonID, fileName);
    }
}
