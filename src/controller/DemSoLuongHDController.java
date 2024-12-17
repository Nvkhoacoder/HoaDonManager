package controller;

import database.DemHDData;


public class DemSoLuongHDController {
    private DemHDData demHDData = null;


    public DemSoLuongHDController(DemHDData demHDData){
        this.demHDData = demHDData;
    }

    public void demSoLuongHD() {
        demHDData.demSoLuongHD();
    }
}
