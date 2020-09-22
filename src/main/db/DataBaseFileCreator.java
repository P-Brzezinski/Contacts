package main.db;

import java.io.File;
import java.io.IOException;

public class DataBaseFileCreator {

    private String path = "src/main/db/dataBase.txt";
    private File file = new File(path);

    public void initDB() {
        try {
            if (file.createNewFile()) {
                System.out.println("DB created");
            } else {
                System.out.println("DB exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected File getFile() {
        return this.file;
    }
}
