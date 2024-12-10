package database;

import entity.HoaDon;

public class ThemHDDao {

    public void ThemHD(HoaDon hd) {
        HoaDonDatabase.addHoaDon(hd);
    }
}
