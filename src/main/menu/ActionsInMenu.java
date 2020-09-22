package main.menu;

import main.db.DataBaseFileCreator;
import main.db.DataBaseDAO;
import main.db.DataBaseWriter;
import main.model.Entity;
import main.model.Organization;
import main.model.Person;
import main.model.gender.Gender;
import main.validator.PhoneNumberValidator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActionsInMenu {

    Scanner scanner = new Scanner(System.in);
    private DataBaseDAO dao = new DataBaseDAO();
    private PhoneNumberValidator validator = new PhoneNumberValidator();

    //===========================================================================================
    // ADD

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

    //===========================================================================================
    // EDIT

    public void edit(Entity entity) {
        int index = dao.getIndex(entity);
        if (dao.isEmpty()) {
            System.out.println("No records to edit!");
        } else {
            if (entity.getClass() == Person.class) {
                Person person = (Person) entity;
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
                dao.changeEntity(index, person);
            } else if (entity.getClass() == Organization.class) {
                Organization organizationToEdit = (Organization) entity;
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
                dao.changeEntity(index, organizationToEdit);
            }
            System.out.println("Saved");
        }
    }

    //===========================================================================================

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
        System.out.println();
    }

    public void delete(Entity entity) {
        dao.removeEntity(entity);
        System.out.println("The record removed!");
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

    //////////////////////////////////////////////////////////////////////////////////////////////

    public void search() {
        String action;
        do {
            List<Entity> searchResults = searchRecords();
            showSearchResults(searchResults);
            System.out.print("[search] Enter action ([number], back, again): ");
            action = scanner.nextLine();
            if (isNumeric(action)) {
                changeRecord(searchResults.get(Integer.parseInt(action) - 1));
                action = "back";
            } else {
                switch (action) {
                    case "again":
                        break;
                    case "back":
                        break;
                    default:
                        System.out.println("No such option.");
                }
            }
        } while (!action.equals("back"));
        System.out.println();
    }

    private List<Entity> searchRecords() {
        System.out.println("Enter search query:");
        String query = scanner.nextLine();
        Pattern pattern = Pattern.compile(".*" + query + ".*", Pattern.CASE_INSENSITIVE);

        List<Entity> results = new ArrayList<>();
        List<Entity> allEntities = dao.getAll();

        for (Entity entity : allEntities) {
            String fields = fieldsToString(entity);
            Matcher matcher = pattern.matcher(fields);
            if (matcher.matches()) {
                results.add(entity);
            }
        }
        return results;
    }

    public void showSearchResults(List<Entity> searchResults) {
        System.out.println("Found " + searchResults.size() + " results:");
        for (int i = 0; i < searchResults.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, searchResults.get(i).getFullName());
        }
    }

    public void changeRecord(Entity entity) {
        String action;
        do {
            System.out.println(entity.toString());
            System.out.println("[record] Enter action (edit, delete, menu):");
            action = scanner.nextLine();
            switch (action) {
                case "edit":
                    edit(entity);
                    break;
                case "delete":
                    delete(entity);
                    action = "menu";
                    break;
                case "menu":
                    break;
                default:
                    System.out.println("No such option");
            }
        } while (!action.equals("menu"));
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private String fieldsToString(Entity entity) {
        String value = null;
        StringBuilder builder = new StringBuilder();
        if (entity.getClass() == Person.class) {
            Person person = (Person) entity;
            value = builder.append(person.getName())
                    .append(person.getSurname())
                    .toString();
        } else if (entity.getClass() == Organization.class) {
            Organization organization = (Organization) entity;
            value = builder
                    .append(organization.getOrganizationName())
                    .toString();
        }
        return value;
    }

    public void addSomeData() {

        DataBaseFileCreator creator = new DataBaseFileCreator();
        creator.initDB();

//        Person.Builder personBuilder = new Person.Builder()
//                .setName("Alice")
//                .setSurname("Wonderlanded")
//                .setDateOfBirth("[no data]")
//                .setGender(Gender.F)
//                .setPhoneNumber("+123123 (123) 12-23-34-45")
//                .setTimeCreated(LocalDateTime.now())
//                .setLastEdit(LocalDateTime.now());
//
//        Person person = personBuilder.build();
//        dao.addEntity(person);
//
//        Organization.Builder organizationBuilder = new Organization.Builder()
//                .setOrganizationName("New Car Shop")
//                .setOrganizationAddress("Wall St. 3")
//                .setPhoneNumber("+0 (123) 456-789-9999")
//                .setTimeCreated(LocalDateTime.now())
//                .setLastEdit(LocalDateTime.now());
//
//        Organization organization = organizationBuilder.build();
//        dao.addEntity(organization);
    }

    public void saveDB() {
        DataBaseWriter dataBaseWriter = new DataBaseWriter();
        dataBaseWriter.saveDB(dao.getAll());
    }
}


