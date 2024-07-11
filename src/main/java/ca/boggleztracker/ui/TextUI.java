/**
 * File: TextUI.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
 * - 2024-07-02: System redesign remove storing records into RAM
 * - 2024-07-09: implemented sub menu display methods
 * - 2024-07-10: implemented add requester user interaction
 * Purpose:
 * TextUI class is responsible for managing the user interface (UI) of the bug tracker
 * application. The class creates TextMenu objects and handles the different
 * user interactions related to the specified menu item. These actions include managing requesters, issues, products,
 * and generating reports
 */
package ca.boggleztracker.ui;

import ca.boggleztracker.model.InputValidator;
import ca.boggleztracker.model.Requester;
import ca.boggleztracker.model.ScenarioManager;

import java.util.Scanner;

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
    public void doAddRequester() {
        // anonymous classes that implement the InputValidator interface
        // these are passed to the getUserInputString to determine length checking
        // (Strategy Pattern)
        InputValidator maxLengthValidator = (input, length) -> input.length() <= length;
        InputValidator exactLengthValidator = (input, length) -> input.length() == length;

        // requester name user input
        System.out.println("Enter requester name (length: 30 max)");
        String name = getStringUserInput(Requester.MAX_NAME, maxLengthValidator);

        // requester phone user input
        System.out.println("Enter requester phone number (length: 11)");
        long phoneNumber = Long.parseLong(getStringUserInput(Requester.PHONE_NUMBER_LENGTH, exactLengthValidator));

        // requester email user input
        System.out.println("Enter requester email (length: 24 max)");
        String email = getStringUserInput(Requester.MAX_EMAIL, maxLengthValidator);

        // requester department user input
        String department = getValidDepartmentUserInput(maxLengthValidator);

        // confirmation of creation
        System.out.println("Confirming entry of " + name + "?" + " (Y/N)");
        if (getYesOrNoUserInput()) {
            manager.addRequester(email, name, phoneNumber, department);
        }
        System.out.println("Do you wish to add another requester? (Y/N)");
        if (getYesOrNoUserInput()) {
            doAddRequester();
        }
    }

    /**
     * Helper method that validates the department of user input.
     *
     * @param validator (in) InputValidator - The input validator for length checking.
     * @return (out) String - A valid department.
     */
    public String getValidDepartmentUserInput(InputValidator validator) {
        System.out.println("Enter requester department (QA/M/PD/S/'')");
        String department = "";
        boolean departmentValid = false;
        while (!departmentValid) {
            department = getStringUserInput(Requester.MAX_DEPARTMENT, validator);
            departmentValid = department.equals("QA") || department.equals("M")
                    || department.equals("PD") || department.equals("S")
                    || department.isEmpty();
            if (!departmentValid) {
                System.out.println("Error: Please enter a valid department");
            }
        }
        return department;
    }

    /**
     * Helper method that gets the user input, it supports length checking.
     *
     * @param length (in) int - length of string for validation.
     * @param validator (in) InputValidator - The input validator for length checking.
     * @return (out) String - A valid user input.
     */
    public String getStringUserInput(int length, InputValidator validator) {
        Scanner keyboard = new Scanner(System.in);
        String input;
        System.out.print("> ");
        input = keyboard.nextLine();
        while (!validator.isValid(input, length)) {
            System.out.println("Error: Please enter valid input length");
            System.out.print("> ");
            input = keyboard.nextLine();
        }
        return input;
    }

    /**
     * Helper method that determines if user selects yes or no.
     * @return (out) boolean - yes or no flag.
     */
    public boolean getYesOrNoUserInput() {
        Scanner keyboard = new Scanner(System.in);
        boolean isValid;
        boolean inputYesOrNo;
        while (true) {
            String input = keyboard.nextLine().toLowerCase();
            isValid = input.equals("y") || input.equals("n");
            if (!isValid) {
                System.out.println("Error: Please enter Y or N");
                System.out.print("> ");
            } else {
                inputYesOrNo = input.equals("y");
                break;
            }
        }
        return inputYesOrNo;
    }

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
