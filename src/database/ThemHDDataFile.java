package database;

import entity.HoaDon;


import java.io.*;

public class ThemHDDataFile {
    private File fileData = null;

    public ThemHDDataFile(String fileName) {
        this.fileData = new File(fileName);
    }

    public void themHD(HoaDon hd) {
        FileWriter fileStream = null;
        BufferedWriter bw = null;
        FileReader fileReader = null;
        BufferedReader br = null;

        try {
            fileStream = new FileWriter(fileData, true);
            bw = new BufferedWriter(fileStream);
            bw.write(hd.toString());
            bw.newLine();
            bw.flush();
            bw.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
