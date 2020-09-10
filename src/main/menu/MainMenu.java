package main.menu;


import java.util.Scanner;

public class MainMenu {

    private final static String ADD = "add";
    private final static String REMOVE = "remove";
    private final static String EDIT = "edit";
    private final static String COUNT = "count";
    private final static String LIST = "list";
    private final static String INFO = "info";
    private final static String EXIT = "exit";

    //TODO Scanner to singleton?
    Scanner scanner = new Scanner(System.in);
    private ActionsInMenu action = new ActionsInMenu();

    public void showMenu(){
        String choice = "";
        do {
            System.out.println("Enter action (add, remove, edit, count, info, exit):");
            choice = scanner.next();
            switch (choice){
                case ADD:
                    action.addEntity();
                    break;
                case REMOVE:
                    action.remove();
                    break;
                case EDIT:
                    action.edit();
                    break;
                case COUNT:
                    action.count();
                    break;
                case INFO:
                    action.showInfo();
                    break;
                case EXIT:
                    break;
                default:
                    System.out.println("No such option to choose. Try again.");
            }
        }while (!choice.equals(EXIT));
    }
}
