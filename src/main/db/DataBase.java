package main.db;

import main.model.Person;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    public List<Person> personList = new ArrayList<>();

    public void saveDB(List<Person> personList){
        this.personList = personList;
    }

}
