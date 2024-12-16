package controller;

import database.InDanhSachDao;
import entity.HoaDon;

import java.io.File;
import java.util.List;

public class InDanhSachController {
    private InDanhSachDao inDanhSachDao = null;

    public InDanhSachController(InDanhSachDao inDanhSachDao) {
        this.inDanhSachDao = inDanhSachDao;
    }

    public List<HoaDon> layDanhSachHoaDon(File fileName){
        return inDanhSachDao.layDanhSachHD(fileName);
    }
}
