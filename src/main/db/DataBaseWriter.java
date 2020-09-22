package main.db;

import main.model.Entity;

import java.io.*;
import java.util.List;

public class DataBaseWriter {

    private DataBaseFileCreator creator = new DataBaseFileCreator();
    private File file = creator.getFile();

    public void saveDB(List<Entity> entities) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            for (Entity entity : entities) {
                oos.writeObject(entity);
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
