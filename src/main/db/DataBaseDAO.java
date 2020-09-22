package main.db;

import main.model.Entity;

import java.util.List;

public class DataBaseDAO {

    private DataBaseReader dataBaseReader = new DataBaseReader();
    private List<Entity> database = dataBaseReader.readDB();

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
