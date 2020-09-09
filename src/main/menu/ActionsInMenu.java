package main.menu;

import main.db.DataBaseDAO;
import main.model.Person;
import main.validator.PhoneNumberValidator;

import java.util.Scanner;

public class ActionsInMenu {

    Scanner scanner = new Scanner(System.in);
    private DataBaseDAO dao = new DataBaseDAO();
    private PhoneNumberValidator validator = new PhoneNumberValidator();

    public void addPerson() {
        Person.Builder builder = new Person.Builder();
        System.out.println("Enter name:");
        builder.setName(scanner.nextLine());
        System.out.println("Enter the surname:");
        builder.setSurname(scanner.nextLine());
        System.out.println("Enter the number:");
        String validatedNumber = scanner.nextLine();
        validatedNumber = validator.checkNumber(validatedNumber);
        builder.setPhoneNumber(validatedNumber);
        Person person = builder.build();
        dao.addPerson(person);
        System.out.println("The record added.");
    }

    public void showList() {
        if (dao.isEmpty()) {
            System.out.println("The Phone Book has 0 records.");
        } else {
            for (int i = 0; i < dao.size(); i++) {
                System.out.println((i + 1) + ". " + dao.getPerson(i));
            }
        }
    }

    public void edit() {
        if (dao.isEmpty()) {
            System.out.println("No records to edit!");
        } else {
            showList();
            System.out.println("Select a record:");
            int record = Integer.parseInt(scanner.nextLine()) - 1;
            Person personToEdit = dao.getPerson(record);
            System.out.println("Select a field (name, surname, number):");
            String fieldToEdit = scanner.nextLine();
            switch (fieldToEdit) {
                case "name":
                    System.out.println("Enter name:");
                    personToEdit.setName(scanner.nextLine());
                    break;
                case "surname":
                    System.out.println("Enter surname:");
                    personToEdit.setSurname(scanner.nextLine());
                    break;
                case "number":
                    System.out.println("Enter number:");
                    String validatedNumber = scanner.nextLine();
                    validatedNumber = validator.checkNumber(validatedNumber);
                    personToEdit.setPhoneNumber(validatedNumber);
                    break;
                default:
                    System.out.println("No such field to edit.");
            }
            dao.changePerson(record, personToEdit);
            System.out.println("The record updated!");
        }
    }

    public void remove() {
        if (dao.isEmpty()) {
            System.out.println("No records to remove");
        } else {
            showList();
            System.out.println("Select a record:");
            int record = scanner.nextInt() - 1;
            dao.removePerson(record);
            System.out.println("The record removed!");
        }
    }

    public void count() {
        if (dao.isEmpty()) {
            System.out.println("The Phone Book has 0 records.");
        } else {
            int size = dao.size();
            System.out.printf("The Phone Book has %d records.\n", size);
        }
    }
}
