package database;

import entity.HoaDon;

import java.io.*;

public class ThemHDDataFile {
    private File fileData = null;

    public ThemHDDataFile(String fileName) {
        fileData = new File(fileName);
    }

    public void themHD(HoaDon hd) {
        FileOutputStream fileStream = null;
        ObjectOutputStream os = null;

        try {
            //mở kênh đến file cần ghi đối tượng
            fileStream = new FileOutputStream(fileData);
            //dùng công cụ để ghi xuống file thông qua
            //kênh fileStream
            os = new ObjectOutputStream(fileStream);
            os.writeObject(hd);
            os.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
