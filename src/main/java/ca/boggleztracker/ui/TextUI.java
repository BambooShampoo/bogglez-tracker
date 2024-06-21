package ca.boggleztracker.ui;

import ca.boggleztracker.model.ProductManager;

public class TextUI {
    private final ProductManager manager;

    public TextUI(ProductManager manager) {
        this.manager = manager;
    }

    public void start() {
        TextMenu.MenuEntry[] menuEntries = new TextMenu.MenuEntry[]{
                new TextMenu.MenuEntry("Customer.", this::customerMenu),
                new TextMenu.MenuEntry("Issue.", this::issueMenu),
                new TextMenu.MenuEntry("Product.", this::productMenu),
                new TextMenu.MenuEntry("Reports.", this::reportsMenu),
                new TextMenu.MenuEntry("Exit.", null)
        };

        boolean menuToRepeat = true;
        TextMenu mainMenu = new TextMenu("Main Menu", menuToRepeat, menuEntries);
        mainMenu.doMenu();
    }

    public void customerMenu() {
        System.out.println("Customer Sub Menu");
    }

    public void issueMenu() {
        System.out.println("Issue Sub Menu");
    }

    public void productMenu() {
        System.out.println("Product Sub Menu");
    }

    public void reportsMenu() {
        System.out.println("Reports Sub Menu");
    }
}
