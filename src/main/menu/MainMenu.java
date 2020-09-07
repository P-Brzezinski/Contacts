package main.menu;

import main.db.DAO;

import java.util.Scanner;

public class MainMenu {

    private final static String ADD = "add";
    private final static String REMOVE = "remove";
    private final static String EDIT = "edit";
    private final static String COUNT = "count";
    private final static String LIST = "list";
    private final static String EXIT = "exit";

    Scanner scanner = new Scanner(System.in);
    private DAO dao = new DAO();

    public void showMenu(){
        String choice = "";
        do {
            System.out.println("Enter action (add, remove, edit, count, list, exit):");
            choice = scanner.next();
            switch (choice){
                case ADD:
                    dao.addPerson(scanner);
                    break;
                case REMOVE:
                    System.out.println("remove");
                    break;
                case EDIT:
                    dao.edit(scanner);
                    break;
                case COUNT:
                    System.out.println("count");
                    break;
                case LIST:
                    dao.showList();
                    break;
                case EXIT:
                    System.out.println("exit");
                    break;
                default:
                    System.out.println("No such option to choose. Try again.");
            }
        }while (!choice.equals(EXIT));
    }
}
