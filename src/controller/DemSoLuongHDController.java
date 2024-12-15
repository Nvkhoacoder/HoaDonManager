package controller;

import database.DemHDData;
import ui.DemSoLuongHDOuputUi;


public class DemSoLuongHDController {
    private DemHDData demHDData = null;
    private DemSoLuongHDOuputUi demSoLuongHDOuputUi = null;

    public DemSoLuongHDController(DemHDData demHDData, DemSoLuongHDOuputUi demSoLuongHDOuputUi){
        this.demHDData = demHDData;
        this.demSoLuongHDOuputUi = demSoLuongHDOuputUi;
    }

    public void demSoLuongHD() {
        demHDData.demSoLuongHD();
    }
}
