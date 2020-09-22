package main.db;

import main.model.Entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseFile {

    private String path = "src/main/db/dataBase.txt";
    private File file = new File(path);

    public void initFile() {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveData(List<Entity> entities) {
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

    public List<Entity> readData() {
        List<Entity> entities = new ArrayList<>();
        if (file.length() != 0) { //in case that file exists but no data in it
            try {
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                ObjectInputStream ois = new ObjectInputStream(bis);

                Object entity;

                while (true) {
                    try {
                        entity = ois.readObject();
                        if (entity != null) {
                            if (entity instanceof Entity) {
                                entities.add((Entity) entity);
                            }
                        }
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return entities;
    }
}
