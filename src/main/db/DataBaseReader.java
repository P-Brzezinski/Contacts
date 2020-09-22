package main.db;

import main.model.Entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseReader {

    private DataBaseFileCreator dataBaseFileCreator = new DataBaseFileCreator();
    private File file = dataBaseFileCreator.getFile();

    public List<Entity> readDB() {
        List<Entity> entities = new ArrayList<>();
        if (file.length() != 0) {
            try {
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                ObjectInputStream ois = new ObjectInputStream(bis);

                Object entity = null;
                boolean flag = true;

                while (flag){
                    try {
                        entity = ois.readObject();
                        if (entity != null){
                            if (entity instanceof Entity){
                                entities.add((Entity) entity);
                            }
                        }
                    }catch (EOFException e){
                        break;
                    }
                }
            }catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        return entities;
    }
}
