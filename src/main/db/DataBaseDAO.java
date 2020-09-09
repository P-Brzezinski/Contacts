package main.db;

import main.model.Person;

import java.util.ArrayList;
import java.util.List;

public class DataBaseDAO {

    private List<Person> personList = new ArrayList<>();

    public boolean isEmpty(){
        if (personList.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    public int size(){
        return personList.size();
    }

    public void addPerson(Person person){
        personList.add(person);
    }

    public Person getPerson(int index){
        return personList.get(index);
    }

    public void changePerson(int index, Person person){
        personList.set(index, person);
    }

    public void removePerson(int index){
        personList.remove(index);
    }


}
