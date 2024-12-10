package database;

import entity.HoaDon;

public class ThemHDDao {

    public void themHD(HoaDon hd) {
        HoaDonDatabase.addHoaDon(hd);
    }
}
