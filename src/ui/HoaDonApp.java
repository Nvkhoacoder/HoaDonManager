package ui;

import controller.SuaHDController;
import controller.ThemHDController;
import controller.XoaHDController;
import database.*;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class HoaDonApp {
    public static void main(String[] args) {
        File fileData = new File("HoaDonDatabase.txt");
        ThemHDDataFile themHDDataFile = new ThemHDDataFile("HoaDonDatabase.txt");
        SuaHDDao suaHDDao = new SuaHDDao("HoaDonDatabase.txt");
        PrintWriter screenPrompt = new PrintWriter(System.out, true);
        Scanner keyBoard = new Scanner(System.in);
        ThemHDOutputUi themHDOutputUi = new ThemHDOutputUi(screenPrompt);
        ThemHDController themHDController = new ThemHDController(themHDDataFile, themHDOutputUi);
        SuaHDOutputUi suaHDOutputUi = new SuaHDOutputUi(screenPrompt);
        SuaHDController suaHDController = new SuaHDController(suaHDDao, suaHDOutputUi);
        ThemHDInputUi themHDInputUi = new ThemHDInputUi(themHDController,keyBoard,screenPrompt);
        SuaHDInputUi suaHDInputUi = new SuaHDInputUi(suaHDController,keyBoard,screenPrompt,fileData);
        XoaHDDao xoaHDDao = new XoaHDDao(fileData);
        XoaHDOutputUi xoaHDOutputUi = new XoaHDOutputUi(screenPrompt);
        XoaHDController xoaHDController = new XoaHDController(xoaHDDao, xoaHDOutputUi);
        XoaHDInputUi xoaHDInputUi = new XoaHDInputUi(xoaHDController,keyBoard,screenPrompt,fileData);
        HoaDonMenuConsoleUi hoaDonMenuConsoleUi = new HoaDonMenuConsoleUi(keyBoard,screenPrompt,themHDInputUi,suaHDInputUi,xoaHDInputUi);

        hoaDonMenuConsoleUi.hoaDonProgram();
    }
}