package controller;

import database.XoaHDDao;
import ui.XoaHDOutputUi;

import java.io.File;

public class XoaHDController {
    private XoaHDDao xoaHDDao = null;
    private XoaHDOutputUi xoaHDOutputUi = null;

    public XoaHDController(XoaHDDao xoaHDDao, XoaHDOutputUi xoaHDOutputUi) {
        this.xoaHDDao = xoaHDDao;
        this.xoaHDOutputUi = xoaHDOutputUi;
    }

    public void xoaHD(String hoaDonID, File fileData) {
        xoaHDDao.xoaHoaDon(hoaDonID, fileData);
        xoaHDOutputUi.hienThongBao();
    }
}
