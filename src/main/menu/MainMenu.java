package main.menu;


import main.db.DataBaseWriter;

import java.util.Scanner;

public class MainMenu {

    private final static String ADD = "add";
    private final static String SEARCH = "search";
    private final static String REMOVE = "remove";
    private final static String EDIT = "edit";
    private final static String COUNT = "count";
    private final static String LIST = "list";
    private final static String INFO = "info";
    private final static String EXIT = "exit";

    private Scanner scanner = new Scanner(System.in);
    private ActionsInMenu action = new ActionsInMenu();

    public void showMenu() {
        String choice = "";
        action.addSomeData();
        do {
            System.out.println("[menu] Enter action (add, list, search, count, exit):");
            choice = scanner.next();
            switch (choice) {
                case ADD:
                    action.addEntity();
                    break;
                case LIST:
                    action.showList();
                    break;
                case SEARCH:
                    action.search();
                    break;
                case COUNT:
                    action.count();
                    break;
                case EXIT:
                    choice = EXIT;
                    action.saveDB();
                    break;
                default:
                    System.out.println("No such option to choose. Try again.");
            }
        } while (!choice.equals(EXIT));
    }
}
