package ui;

import controller.SuaHDController;
import controller.ThemHDController;
import database.HoaDonDatabase;
import database.SuaHDDao;
import database.ThemHDDao;
import database.ThemHDDataFile;

import java.io.PrintWriter;
import java.util.Scanner;

public class HoaDonApp {
    public static void main(String[] args) {
        HoaDonDatabase.initDatabase();

        ThemHDDataFile themHDDataFile = new ThemHDDataFile("HoaDonDatabase.txt");
        SuaHDDao suaHDDao = new SuaHDDao("HoaDonDatabase.txt");
        PrintWriter screenPrompt = new PrintWriter(System.out, true);
        Scanner keyBoard = new Scanner(System.in);
        ThemHDOutputUi themHDOutputUi = new ThemHDOutputUi(screenPrompt);
        ThemHDController themHDController = new ThemHDController(themHDDataFile, themHDOutputUi);
        SuaHDOutputUi suaHDOutputUi = new SuaHDOutputUi(screenPrompt);
        SuaHDController suaHDController = new SuaHDController(suaHDDao, suaHDOutputUi);
        ThemHDInputUi themHDInputUi = new ThemHDInputUi(themHDController,keyBoard,screenPrompt);
        SuaHDInputUi suaHDInputUi = new SuaHDInputUi(suaHDController,keyBoard,screenPrompt);
        HoaDonMenuConsoleUi hoaDonMenuConsoleUi = new HoaDonMenuConsoleUi(keyBoard,screenPrompt,themHDInputUi,suaHDInputUi);

        hoaDonMenuConsoleUi.hoaDonProgram();
    }
}