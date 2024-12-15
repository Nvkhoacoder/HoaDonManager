package ui;

import java.io.PrintWriter;

public class DemSoLuongHDOuputUi {
    private PrintWriter screenPrompt = null;

    public DemSoLuongHDOuputUi(PrintWriter screenPrompt){
        this.screenPrompt = screenPrompt;
    }

    public void inThongBao(int tongGio, int tongNgay, String message) {
        screenPrompt.println(message + tongGio);
        screenPrompt.println(message + tongNgay);
    }
}
