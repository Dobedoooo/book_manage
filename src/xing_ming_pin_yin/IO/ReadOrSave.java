package xing_ming_pin_yin.IO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadOrSave {
    public static List<String> read(String path) {
        BufferedReader reader = null;
        FileReader fr = null;
        File file = new File(path);
        List<String> list = new ArrayList<>();
        try {
            fr = new FileReader(file);
            reader = new BufferedReader(fr);
            String line = "";
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static void save() {
        FileWriter fw = null;
        File file = new File("./files/haha.txt");
        String str = "aakkkka";
        try {
            fw = new FileWriter(file, true);
//            for (int i = 0; i < 4; i++) {
                fw.write(str + "\r\n");
//            }
            fw.flush();
            fw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        save();
    }
}
