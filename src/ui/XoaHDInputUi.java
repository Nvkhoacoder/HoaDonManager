package ui;

import controller.XoaHDController;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class XoaHDInputUi {
    private XoaHDController xoaHDController = null;
    private Scanner keyBoard = null;
    private PrintWriter screenPrompt = null;
    private String maHoaDonPrompt;
    private File fileData = null;

    public XoaHDInputUi(XoaHDController xoaHDController, Scanner keyBoard, PrintWriter screenPrompt, File fileData) {
        this.xoaHDController = xoaHDController;
        this.keyBoard = keyBoard;
        this.screenPrompt = screenPrompt;
        this.fileData = fileData;

        maHoaDonPrompt = "MA HOA DON: ";
    }

    public void nhapMaHoaDonCanXoa(){
        screenPrompt.print(maHoaDonPrompt); screenPrompt.flush();
        String maHoaDon = keyBoard.nextLine();
        xoaHDController.xoaHD(maHoaDon, fileData);
    }
}
