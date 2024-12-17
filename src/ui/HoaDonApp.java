package ui;

import controller.*;
import database.*;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class HoaDonApp {
    public static void main(String[] args) {
        File fileData = new File("HoaDonDatabase.txt");
        ThemHDDataFile themHDDataFile = new ThemHDDataFile("HoaDonDatabase.txt");
        SuaHDDao suaHDDao = new SuaHDDao("HoaDonDatabase.txt");
        ThemHDDao themHDDao = new ThemHDDao();
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
        TimHDData timHDData = new TimHDData(fileData);
        TimHDOutputUi timHDOutputUi = new TimHDOutputUi(screenPrompt);
        TimHDController timHDController = new TimHDController(timHDData,timHDOutputUi);
        TimHDInputUi timHDInputUi = new TimHDInputUi(timHDController,keyBoard,screenPrompt,fileData);
        TongDoanhThuDao tongDoanhThuDao = new TongDoanhThuDao(fileData);
        DoanhThuTheoThangOutputUi doanhThuTheoThangOutputUi = new DoanhThuTheoThangOutputUi(screenPrompt);
        TongDoanhThuController tongDoanhThuController = new TongDoanhThuController(tongDoanhThuDao,doanhThuTheoThangOutputUi);
        DoanhThuInputUi doanhThuInputUi = new DoanhThuInputUi(tongDoanhThuController,keyBoard,screenPrompt,fileData);
        DemHDData demHDData = new DemHDData(fileData);
        DemSoLuongHDController demSoLuongHDController = new DemSoLuongHDController(demHDData);
        InDanhSach inDanhSach = new InDanhSach();
        InDanhSachDao inDanhSachDao = new InDanhSachDao(fileData);
        InDanhSachController inDanhSachController = new InDanhSachController(inDanhSachDao);
        InDanhSachUi inDanhSachUi = new InDanhSachUi(screenPrompt,inDanhSachController);
        HoaDonMenuConsoleUi hoaDonMenuConsoleUi = new HoaDonMenuConsoleUi(keyBoard,screenPrompt,themHDInputUi,suaHDInputUi,xoaHDInputUi,timHDInputUi,doanhThuInputUi,demSoLuongHDController,inDanhSachUi);

        hoaDonMenuConsoleUi.hoaDonProgram();
    }
}