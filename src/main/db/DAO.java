package main.db;


import main.model.Person;

import java.util.List;
import java.util.Scanner;

public class DAO {

    private DataBase dataBase = new DataBase();
    private List<Person> personList = dataBase.personList;

    public void addPerson(Scanner scanner) {
        Person.Builder builder = new Person.Builder();
        System.out.println("Enter name:");
        builder.setName(scanner.next());
        System.out.println("Enter the surname:");
        builder.setSurname(scanner.next());
        System.out.println("Enter the number:");
        builder.setPhoneNumber(scanner.next());
        Person person = builder.build();
        personList.add(person);
        dataBase.saveDB(personList);
        System.out.println("The record added.");
    }

    public void showList() {
        if (personList.isEmpty()) {
            System.out.println("The Phone Book has 0 records.");
        } else {
            for (int i = 0; i < dataBase.personList.size(); i++) {
                System.out.println((i + 1) + ". " + personList.get(i));
            }
        }
    }

    public void edit(Scanner scanner) {
        boolean edited = true;
        if (personList.isEmpty()) {
            System.out.println("No record to edit!");
        } else {
            showList();
            System.out.println("Select a record:");
            int record = scanner.nextInt() - 1;
            Person personToEdit = personList.get(record);
            System.out.println("Select a field (name, surname, number):");
            String fieldToEdit = scanner.next();
            switch (fieldToEdit) {
                case "name":
                    System.out.println("Enter name:");
                    personToEdit.setName(scanner.next());
                    break;
                case "surname":
                    System.out.println("Enter surname:");
                    personToEdit.setSurname(scanner.next());
                    break;
                case "number":
                    System.out.println("Enter number:");
                    personToEdit.setPhoneNumber(scanner.next());
                    break;
                default:
                    System.out.println("No such field to edit.");
                    edited = false;
            }
            if (edited){
                personList.set(record, personToEdit);
                System.out.println("The record updated!");
            }else {
                System.out.println("The record not updated!");
            }
        }
    }
}
