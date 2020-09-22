package main.db;

import main.model.Entity;
import java.util.ArrayList;
import java.util.List;


public class DataBaseDAO {

    private List<Entity> database = new ArrayList<>();

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

    public void changeEntity(int index, Entity entity){
        database.set(index, entity);
    }

    public void removeEntity(int index){
        database.remove(index);
    }

    public List<Entity> getAll(){
        return database;
    }


}
