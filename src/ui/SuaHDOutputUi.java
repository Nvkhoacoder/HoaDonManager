package ui;

import java.io.PrintWriter;

public class SuaHDOutputUi {
    private PrintWriter screenPronmpt = null;

    public SuaHDOutputUi(PrintWriter screenPronmpt) {
        this.screenPronmpt = screenPronmpt;
    }

    public void hienThongBao(){
        screenPronmpt.println("Bạn đã sữa thành công Hoá Đơn");
    }
}
