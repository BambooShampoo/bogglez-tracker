package ca.boggleztracker.ui;

import ca.boggleztracker.model.ChangeItem;
import ca.boggleztracker.model.ChangeRequest;
import ca.boggleztracker.model.ScenarioManager;
import org.w3c.dom.Text;

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

    public void doCreateChangeRequest() {
//        // list requesters
//        listRequesters();
//        Requester requester = manager.getSpecifiedRequester("email");
//
//        listProducts();
//        Product product = manager.getSpecifiedProduct("product");
//
//        listReleases(product);
//        // get user input
//        Release release = product.getSpecifiedRelease("releaseID");
//
//        listChangeItems(release);
//        // get user input for Changei
//        ChangeItem change = release.getSpecifiedChange(1234231);
//
//        // inputs for change request, date
//        change.addChangeRequest("date, requester");
    }

    public void listRequesters() {
//        manager.getRequesters();
    }

    public void listProducts() {

    }

    public void listReleases(Product product) {
//        product.getReleases();

        // for loop to list
    }

    public void listChangeItems(Release release) {
//        release.getChanges();

        //for loop to list
    }

    public void listChangeRequests(ChangeItem change) {
//        change.getChangeRequest();

        // for loop to lsit
    }

    public void doModifyIssue() { }

    public void productMenu() {
        System.out.println("Product Sub Menu");
    }

    public void reportsMenu() {
        System.out.println("Reports Sub Menu");
    }


}
