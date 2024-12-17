package controller;

import database.TimHDData;
import ui.TimHDOutputUi;

import java.io.File;

public class TimHDController {
    private TimHDData timHDData = null;
    private TimHDOutputUi timHDOutputUi = null;

    public TimHDController(TimHDData timHDData, TimHDOutputUi timHDOutputUi) {
        this.timHDData = timHDData;
        this.timHDOutputUi = timHDOutputUi;
    }

    public void timHD(String hoaDonID, File fileData){
        boolean isFind = timHDData.timHD(hoaDonID, fileData);
        if(isFind){
            timHDOutputUi.hienThongBao();

        } else {
            System.out.println("Không tìm thấy Hoá Đơn: " + hoaDonID);
        }

    }
}
