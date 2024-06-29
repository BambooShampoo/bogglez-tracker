package ca.boggleztracker.ui;

import ca.boggleztracker.model.ChangeItem;
import ca.boggleztracker.model.Release;
import ca.boggleztracker.model.Product;
import ca.boggleztracker.model.ScenarioManager;

public class TextUI {
    private final ScenarioManager manager;

    public TextUI(ScenarioManager manager) {
        this.manager = manager;
    }

    public void start() {
        TextMenu.MenuEntry[] menuEntries = new TextMenu.MenuEntry[]{
                new TextMenu.MenuEntry("Requester.", this::RequesterMenu),
                new TextMenu.MenuEntry("Issue.", this::issueMenu),
                new TextMenu.MenuEntry("Product.", this::productMenu),
                new TextMenu.MenuEntry("Reports.", this::reportsMenu),
                new TextMenu.MenuEntry("Exit.", null)
        };

        boolean menuToRepeat = true;
        TextMenu mainMenu = new TextMenu("Main Menu", menuToRepeat, menuEntries);
        mainMenu.doMenu();
    }

    public void RequesterMenu() { System.out.println("Requester Sub Menu"); }

    public void doAddRequester() { }

    public void issueMenu() {
        System.out.println("Issue Sub Menu");
    }

    public void doAddChangeRequest() {}

    public void doAddChangeItem(Release release) {}

    public void doModifyIssue() {}

    public void productMenu() {
        System.out.println("Product Sub Menu");
    }

    public void doAddProduct() { }

    public void doAddRelease() { }

    public void reportsMenu() {
        System.out.println("Reports Sub Menu");
    }

    public void listPendingChanges() {}

    public void listRequesterNotification() {}

    public void listRequesters() {}

    public void listProducts() {}

    public void listReleases(Product product) {}

    public void listChangeItems(Release release) {}

    public void listChangeRequests(ChangeItem change) {}
}
