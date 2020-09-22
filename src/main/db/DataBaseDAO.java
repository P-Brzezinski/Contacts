package main.db;

import main.model.Entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DataBaseDAO {

    private List<Entity> database = new ArrayList<>();

    public void initDB() {
        String path = "src/main/db/dataBase.txt";
        File file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isEmpty(){
        if (database.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    public int size(){
        return database.size();
    }

    public void addEntity(Entity entity){
        database.add(entity);
    }

    public Entity getEntity(int index){
        return database.get(index);
    }

    public int getIndex(Entity entity){
        return database.indexOf(entity);
    }

    public void changeEntity(int index, Entity entity){
        database.set(index, entity);
    }

    public void removeEntity(Entity entity){
        database.remove(entity);
    }

    public List<Entity> getAll(){
        return database;
    }


}
