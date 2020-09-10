package main.menu;

import main.db.DataBaseDAO;
import main.model.Entity;
import main.model.Organization;
import main.model.Person;
import main.model.gender.Gender;
import main.validator.PhoneNumberValidator;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ActionsInMenu {

    Scanner scanner = new Scanner(System.in);
    private DataBaseDAO dao = new DataBaseDAO();
    private PhoneNumberValidator validator = new PhoneNumberValidator();

    public void addEntity() {
        System.out.println("Enter the type (person, organization):");
        String type = scanner.nextLine();
        switch (type) {
            case "person":
                Person.Builder personBuilder = new Person.Builder();
                System.out.println("Enter name:");
                personBuilder.setName(scanner.nextLine());
                System.out.println("Enter the surname:");
                personBuilder.setSurname(scanner.nextLine());
                System.out.println("Enter the birth date:");
                String birthDate = scanner.nextLine();
                if (birthDate.isBlank()) {
                    System.out.println("Bad birth date!");
                    personBuilder.setDateOfBirth("[no data]");
                } else {
                    personBuilder.setDateOfBirth(birthDate);
                }
                System.out.println("Enter the Gender (M, F):");
                String gender = scanner.nextLine();
                if (gender.isBlank()) {
                    System.out.println("Bad gender!");
                    personBuilder.setGender(Gender.NA);
                } else {
                    personBuilder.setGender(Gender.genderFromString(gender));
                }
                System.out.println("Enter the number:");
                String validatedNumber = scanner.nextLine();
                validatedNumber = validator.checkNumber(validatedNumber);
                personBuilder.setPhoneNumber(validatedNumber);
                personBuilder.setTimeCreated(LocalDateTime.now());
                personBuilder.setLastEdit(LocalDateTime.now());
                Person person = personBuilder.build();
                dao.addEntity(person);
                System.out.println("The record added.");
                break;
            case "organization":
                Organization.Builder organizationBuilder = new Organization.Builder();
                System.out.println("Enter the organization name:");
                organizationBuilder.setOrganizationName(scanner.nextLine());
                System.out.println("Enter the address:");
                organizationBuilder.setOrganizationAddress(scanner.nextLine());
                System.out.println("Enter the number:");
                organizationBuilder.setPhoneNumber(scanner.nextLine());
                organizationBuilder.setTimeCreated(LocalDateTime.now());
                organizationBuilder.setLastEdit(LocalDateTime.now());
                Organization organization = organizationBuilder.build();
                dao.addEntity(organization);
                System.out.println("The record added");
                break;
            default:
                System.out.println("No such option.");
        }
        System.out.println();
    }

    public void showList() {
        if (dao.isEmpty()) {
            System.out.println("The Phone Book has 0 records.");
        } else {
            for (int i = 0; i < dao.size(); i++) {
                if (dao.getEntity(i).getClass() == Person.class) {
                    Person person = (Person) dao.getEntity(i);
                    System.out.println((i + 1) + ". " + person.getName());
                } else {
                    Organization organization = (Organization) dao.getEntity(i);
                    System.out.println((i + 1) + ". " + organization.getOrganizationName());
                }
            }
        }
    }

    public void showInfo() {
        showList();
        System.out.println("Enter index to show info:");
        int record = scanner.nextInt() - 1;
        Entity entity = dao.getEntity(record);
        System.out.println(entity.toString());
        System.out.println();
    }

    public void edit() {
        if (dao.isEmpty()) {
            System.out.println("No records to edit!");
        } else {
            showList();
            System.out.println("Select a record:");
            int record = Integer.parseInt(scanner.nextLine()) - 1;
            Entity entityToEdit = dao.getEntity(record);
            if (entityToEdit.getClass() == Person.class) {
                Person person = (Person) entityToEdit;
                System.out.println("Select a field (name, surname, birth, gender, number):");
                String fieldToEdit = scanner.nextLine();
                switch (fieldToEdit) {
                    case "name":
                        System.out.println("Enter name:");
                        person.setName(scanner.nextLine());
                        break;
                    case "surname":
                        System.out.println("Enter surname:");
                        person.setSurname(scanner.nextLine());
                        break;
                    case "number":
                        System.out.println("Enter number:");
                        String validatedNumber = scanner.nextLine();
                        validatedNumber = validator.checkNumber(validatedNumber);
                        person.setPhoneNumber(validatedNumber);
                        break;
                    case "birth":
                        System.out.println("Enter birth:");
                        person.setDateOfBirth(scanner.nextLine());
                        break;
                    case "gender":
                        System.out.println("Enter gender:");
                        String gender = scanner.nextLine();
                        person.setGender(Gender.genderFromString(gender));
                        break;
                    default:
                        System.out.println("No such field to edit.");
                }
                person.setLastEdit(LocalDateTime.now());
                dao.changeEntity(record, person);
            } else if (entityToEdit.getClass() == Organization.class) {
                Organization organizationToEdit = (Organization) entityToEdit;
                System.out.println("Select a field (name, address, number):");
                String fieldToEdit = scanner.nextLine();
                switch (fieldToEdit) {
                    case "name":
                        System.out.println("Enter organization name:");
                        organizationToEdit.setOrganizationName(scanner.nextLine());
                        break;
                    case "address":
                        System.out.println("Enter address:");
                        organizationToEdit.setOrganizationAddress(scanner.nextLine());
                        break;
                    case "number":
                        System.out.println("Enter number:");
                        String validatedNumber = scanner.nextLine();
                        validatedNumber = validator.checkNumber(validatedNumber);
                        organizationToEdit.setPhoneNumber(validatedNumber);
                        break;
                    default:
                        System.out.println("No such field to edit.");
                }
                organizationToEdit.setLastEdit(LocalDateTime.now());
                dao.changeEntity(record, organizationToEdit);
            }
            System.out.println("The record updated!");
        }
        System.out.println();
    }

    public void remove() {
        if (dao.isEmpty()) {
            System.out.println("No records to remove");
        } else {
            showList();
            System.out.println("Select a record:");
            int record = scanner.nextInt() - 1;
            dao.removeEntity(record);
            System.out.println("The record removed!");
        }
        System.out.println();
    }

    public void count() {
        if (dao.isEmpty()) {
            System.out.println("The Phone Book has 0 records.");
        } else {
            int size = dao.size();
            System.out.printf("The Phone Book has %d records.\n", size);
        }
        System.out.println();
    }
}

