/**
 * File: TextUI.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
 * - 2024-07-02: System redesign remove storing records into RAM
 * Purpose:
 * TextUI class is responsible for managing the user interface (UI) of the bug tracker
 * application. The class creates TextMenu objects and handles the different
 * user interactions related to the specified menu item. These actions include managing requesters, issues, products,
 * and generating reports
 */
package ca.boggleztracker.ui;

import ca.boggleztracker.model.ScenarioManager;

public class TextUI {
    //=============================
    // Member fields
    //=============================
    private final ScenarioManager manager;

    //=============================
    // Constructors
    //=============================

    //-----------------------------
    /**
     * One argument constructor for TextUI.
     *
     * @param manager (in) ScenarioManager - reference to the manager is passed to work with
     *                its data during menu interactions.
     */
    //---
    public TextUI(ScenarioManager manager) {
        this.manager = manager;
    }

    //=============================
    // Methods
    //=============================

    //-----------------------------
    /**
     * Starts the user interface by displaying the main menu.
     * Uses the TextMenu class to create menus and manage interactions.
     * MenuEntry objects are instantiated with method references to define
     * its action
     */
    //---
    public void start() {
        TextMenu.MenuEntry[] menuEntries = new TextMenu.MenuEntry[] {
                new TextMenu.MenuEntry("Requester", this::requesterMenu),
                new TextMenu.MenuEntry("Issue", this::issueMenu),
                new TextMenu.MenuEntry("Product", this::productMenu),
                new TextMenu.MenuEntry("Reports", this::reportsMenu),
                new TextMenu.MenuEntry("Exit", this::exitSystem)
        };

        boolean menuToRepeat = true;
        TextMenu mainMenu = new TextMenu("==Main Menu==", menuToRepeat, menuEntries);
        mainMenu.doMenu();
    }

    //-----------------------------
    /**
     * Use the TextMenu class to create a requester menu and manage interactions.
     */
    //---
    public void requesterMenu() {
        TextMenu.MenuEntry[] menuEntries = new TextMenu.MenuEntry[] {
                new TextMenu.MenuEntry("Create New Requester", this::doAddRequester),
                new TextMenu.MenuEntry("Return to Main Menu", null)
        };

        boolean menuToRepeat = true;
        TextMenu issueMenu = new TextMenu("==Requester==", menuToRepeat, menuEntries);
        issueMenu.doMenu();
    }

    //-----------------------------
    /**
     * Provides the user interactions to add a requester. When an email is provided
     * from the customer, the system will check if it already exists.
     */
    //---
    public void doAddRequester() {}

    //-----------------------------
    /**
     * Use the TextMenu class to create an issue menu and manage interactions.
     */
    //---
    public void issueMenu() {
        TextMenu.MenuEntry[] menuEntries = new TextMenu.MenuEntry[] {
                new TextMenu.MenuEntry("Report an Issue", this::doAddChangeRequest),
                new TextMenu.MenuEntry("Modify Existing Issue", this::doModifyIssue),
                new TextMenu.MenuEntry("Return to Main Menu", null)
        };

        boolean menuToRepeat = true;
        TextMenu issueMenu = new TextMenu("==Issue==", menuToRepeat, menuEntries);
        issueMenu.doMenu();
    }

    //-----------------------------
    /**
     * Provides the user interactions to add a change request.
     */
    //---
    public void doAddChangeRequest() {}

    //-----------------------------

    /**
     * Provides the user interactions to add a change item.
     */
    //---
    public void doAddChangeItem() {}

    //-----------------------------
    /**
     * Provides the user interactions to modify a change item.
     */
    //---
    public void doModifyIssue() {}

    //-----------------------------
    /**
     * Use the TextMenu class to create a product menu and manage interactions.
     */
    //---
    public void productMenu() {
        TextMenu.MenuEntry[] menuEntries = new TextMenu.MenuEntry[] {
                new TextMenu.MenuEntry("Create New Product", this::doAddProduct),
                new TextMenu.MenuEntry("Create New Product Release", this::doAddRelease),
                new TextMenu.MenuEntry("Update Product Release", this::doModifyRelease),
                new TextMenu.MenuEntry("Return to Main Menu", null)
        };

        boolean menuToRepeat = true;
        TextMenu issueMenu = new TextMenu("==Product==", menuToRepeat, menuEntries);
        issueMenu.doMenu();
    }

    //-----------------------------
    /**
     * Provides the user interaction to add a product.
     */
    //---
    public void doAddProduct() { }

    //-----------------------------
    /**
     * Provides the user interaction to add a product release.
     */
    //---
    public void doAddRelease() { }


    //-----------------------------
    /**
     * Provides the user interactions to modify a release.
     */
    //---
    public void doModifyRelease() {}

    //-----------------------------
    /**
     * Use the TextMenu class to create a reports menu and manage interactions.
     */
    //---
    public void reportsMenu() {
        TextMenu.MenuEntry[] menuEntries = new TextMenu.MenuEntry[] {
                new TextMenu.MenuEntry("Report for Pending Change Items of a Product", this::listPendingChanges),
                new TextMenu.MenuEntry("Report for Requester/Staff Notification", this::listPendingChanges),
                new TextMenu.MenuEntry("Return to Main Menu", null)
        };

        boolean menuToRepeat = true;
        TextMenu issueMenu = new TextMenu("==Reports==", menuToRepeat, menuEntries);
        issueMenu.doMenu();
    }

    //-----------------------------
    /**
     * Provides the user interaction to display report of all pending change items for a product.
     */
    //---
    public void listPendingChanges() {}

    //-----------------------------
    /**
     * Provides the user interaction to display report of all customers needing to be notified
     * of completed change items.
     */
    //---
    public void listRequesterNotification() {}

    //-----------------------------
    /**
     * Displays a list of requesters and retrieves requester email
     */
    //---
    public String selectRequester() { return ""; }

    //-----------------------------
    /**
     * Displays a list of products and returns selected product name.
     */
    //---
    public String selectProduct() {
        return "";
    }

    //-----------------------------
    /**
     * Displays a list of product releases based on provided product and returns selected release.
     */
    //---
    public String selectRelease(String productName) {
        return "";
    }

    //-----------------------------
    /**
     * Displays a list of change items based on provided release and returns changeID.
     */
    //---
    public int selectChangeItem(String releaseID) {
        return 0;
    }

    //-----------------------------
    /**
     * Closes the data file. and exits the application.
     */
    //---
    public void exitSystem() {
        manager.closeFiles();
        TextMenu.setShouldMainMenuRepeat(false);
    }
}
