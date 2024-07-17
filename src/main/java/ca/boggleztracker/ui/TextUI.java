/**
 * File: TextUI.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
 * - 2024-07-02: System redesign remove storing records into RAM
 * - 2024-07-09: implemented sub menu display methods
 * - 2024-07-10: implemented add requester user interaction
 * - 2024-07-13: implemented add all add user interactions
 * - 2024-07-16: implemented all selection & report interactions
 * Purpose:
 * TextUI class is responsible for managing the user interface (UI) of the bug tracker
 * application. The class creates TextMenu objects and handles the different
 * user interactions related to the specified menu item. These actions include managing requesters, issues, products,
 * and generating reports
 */
package ca.boggleztracker.ui;

import ca.boggleztracker.model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TextUI {
    //=============================
    // Static and Constant fields
    //=============================
    public static final int PAGE_SIZE = 6;

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
        System.out.println("Enter requester department (QA/M/PD/S/'')");
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
    public void doAddChangeRequest() {
        String requesterEmail = selectRequester();
        if (requesterEmail == null) {
            return;
        }
        String productName = selectProduct();
        if (productName == null) {
            return;
        }
        String releaseID = selectRelease(productName);
        if (releaseID == null) {
            return;
        }
        int changeID = selectChangeItem(productName, releaseID, "");
        if (changeID == -1) {
            return;
        }

        System.out.println("Enter change request date (yyyy-mm-dd)");
        LocalDate date = getValidLocalDateInput();

        // confirmation of creation
        System.out.println("Confirming entry of new change request?" + " (Y/N)");
        if (getYesOrNoUserInput()) {
            manager.addChangeRequest(changeID, productName, releaseID, requesterEmail, date);
        }
        System.out.println("Do you wish to add another change request? (Y/N)");
        if (getYesOrNoUserInput()) {
            doAddChangeRequest();
        }
    }

    //-----------------------------

    /**
     * Provides the user interactions to add a change item.
     */
    //---
    public void doAddChangeItem(String productName, String releaseID) {
        InputValidator maxLengthValidator = (input, length) -> input.length() <= length;

        System.out.println("Enter change description (length: 30 max)");
        String changeDescription = getStringUserInput(ChangeItem.MAX_DESCRIPTION, maxLengthValidator);
        System.out.println("Enter status (Open/Assessed/In-Progress/Completed/Cancelled)");
        String status = getValidStatusUserInput(maxLengthValidator);

        System.out.println("Enter priority (1 - 5 or '')");
        char priority = getValidPriorityUserInput(maxLengthValidator);
        System.out.println("Enter anticipated release date (yyyy-mm-dd or '')");
        LocalDate anticipatedReleaseDate = getValidLocalDateInputOrNull();

        // confirmation of creation
        System.out.println("Confirming entry of new change item?" + " (Y/N)");
        if (getYesOrNoUserInput()) {
            manager.addChangeItem(productName, releaseID, changeDescription, priority, status, anticipatedReleaseDate);
        }
        System.out.println("Do you wish to add another change item? (Y/N)");
        if (getYesOrNoUserInput()) {
            doAddChangeItem(productName, releaseID);
        }
    }

    //-----------------------------
    /**
     * Provides the user interactions to modify a change item.
     */
    //---
    public void doModifyIssue() {
        InputValidator maxLengthValidator = (input, length) -> input.length() <= length;

        String productName = selectProduct();
        if (productName == null) {
            return;
        }

        String releaseID = selectRelease(productName);
        if (releaseID == null) {
            return;
        }

        int changeID = selectChangeItem(productName, releaseID, "");

        System.out.println("Enter change description (length: 30 max)");
        String changeDescription = getStringUserInput(ChangeItem.MAX_DESCRIPTION, maxLengthValidator);
        System.out.println("Enter status (Open/Assessed/In-Progress/Completed/Cancelled)");
        String status = getValidStatusUserInput(maxLengthValidator);

        System.out.println("Enter priority (1 - 5 or '')");
        char priority = getValidPriorityUserInput(maxLengthValidator);
        System.out.println("Enter anticipated release date (yyyy-mm-dd or '')");
        LocalDate anticipatedReleaseDate = getValidLocalDateInputOrNull();

        // confirmation of modification
        System.out.println("Confirming entry of modified change item?" + " (Y/N)");
        if (getYesOrNoUserInput()) {
            ChangeItem changeItem = new ChangeItem(changeID, productName, releaseID, changeDescription,
                    priority, status, anticipatedReleaseDate);
            manager.modifyChangeItem(changeID, changeItem);
        }
        System.out.println("Do you wish to modify another change item? (Y/N)");
        if (getYesOrNoUserInput()) {
            doModifyIssue();
        }
    }

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
    public void doAddProduct() {
        InputValidator maxLengthValidator = (input, length) -> input.length() <= length;

        // product name user input
        System.out.println("Enter new product name (length: 10 max)");
        String name = getStringUserInput(Product.MAX_PRODUCT_NAME, maxLengthValidator);

        // confirmation of creation
        System.out.println("Confirming entry of new " + name + "?" + " (Y/N)");
        if (getYesOrNoUserInput()) {
            manager.addProduct(name);
        }
        System.out.println("Do you wish to add another product? (Y/N)");
        if (getYesOrNoUserInput()) {
            doAddProduct();
        }
    }

    //-----------------------------
    /**
     * Provides the user interaction to add a product release.
     */
    //---
    public void doAddRelease() {
        InputValidator maxLengthValidator = (input, length) -> input.length() <= length;
        String productName = selectProduct();
        if (productName == null) {
            return;
        }

        System.out.println("Enter new release ID for product " + productName.trim() + " (length: 8 max)");
        String releaseID = getStringUserInput(Release.MAX_RELEASE_ID, maxLengthValidator);
        System.out.println("Enter release date (yyyy-mm-dd)");
        LocalDate releaseDate = getValidLocalDateInput();

        // confirmation of creation
        System.out.println("Confirming entry of new release ID " + releaseID + "?" + " (Y/N)");
        if (getYesOrNoUserInput()) {
            manager.addRelease(productName, releaseID, releaseDate);
        }
        System.out.println("Do you wish to add another release? (Y/N)");
        if (getYesOrNoUserInput()) {
            doAddRelease();
        }
    }

    //-----------------------------
    /**
     * Provides the user interactions to modify a release.
     */
    //---
    public void doModifyRelease() {
        String productName = selectProduct();
        if (productName == null) {
            return;
        }

        String releaseID = selectRelease(productName);
        if (releaseID == null) {
            return;
        }

        System.out.println("Enter updated release date (yyyy-mm-dd)");
        LocalDate releaseDate = getValidLocalDateInput();

        // confirmation of modification
        System.out.println("Confirming entry of modified release ID " + releaseID.trim() + "?" + " (Y/N)");
        if (getYesOrNoUserInput()) {
            Release release = new Release(productName, releaseID, releaseDate);
            manager.modifyRelease(releaseID, release);
        }
        System.out.println("Do you wish to modify another product release? (Y/N)");
        if (getYesOrNoUserInput()) {
            doModifyRelease();
        }
    }

    //-----------------------------
    /**
     * Use the TextMenu class to create a reports menu and manage interactions.
     */
    //---
    public void reportsMenu() {
        TextMenu.MenuEntry[] menuEntries = new TextMenu.MenuEntry[] {
                new TextMenu.MenuEntry("Report for Pending Change Items of a Product", this::listPendingChanges),
                new TextMenu.MenuEntry("Report for Requester/Staff Notification", this::listRequesterNotification),
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
    public void listPendingChanges() {
        String productName = selectProduct();
        if (productName == null) {
            return;
        }
        selectChangeItem(productName, "", "pending");
    }

    //-----------------------------
    /**
     * Provides the user interaction to display report of all customers needing to be notified
     * of completed change items.
     */
    //---
    public void listRequesterNotification() {
        String productName = selectProduct();
        if (productName == null) {
            return;
        }

        String releaseID = selectRelease(productName);
        if (releaseID == null) {
            return;
        }

        int changeID = selectChangeItem(productName, releaseID, "completed");
        if (changeID == -1) {
            return;
        }

        Scanner keyboard = new Scanner(System.in);
        String input;
        String lastEmail = null;


        while (true) {
            System.out.println("Requester Emails to Notify:");
            System.out.println("==========================================================================");
            System.out.printf("   %-30s  %-24s  %-14s\n", "Name", "Emails", "Phone Number");
            System.out.println("   ------------------------------  ------------------------  --------------");

            Requester[] requesters = manager.generateEmailsPage(changeID, lastEmail, PAGE_SIZE);


            for (int i = 0; i < requesters.length; i++) {
                if (requesters[i] != null) {
                    System.out.print(i + 1 + ")") ;
                    System.out.printf(" %-30s  %-24s  %-14s\n", new String(requesters[i].getName()), new String(requesters[i].getEmail()),
                            requesters[i].formatPhoneNumber());
                }
            }
            System.out.println("0) Return to report menu");
            System.out.println("N) List next emails");
            System.out.println("ENTER:");

            input = keyboard.nextLine().toLowerCase();

            switch (input) {
                case "0":
                    return;
                case "n":
                    if (requesters[PAGE_SIZE -1] == null) {
                        lastEmail = null;
                    } else {
                        lastEmail = new String(requesters[PAGE_SIZE - 1].getEmail());
                    }
                    break;
                default:
                    break;
            }
        }
    }

    //-----------------------------
    /**
     * Displays a list of requesters and retrieves requester email
     *
     * @return (out) String - the selected requester email.
     */
    //---
    public String selectRequester() {
        Scanner keyboard = new Scanner(System.in);
        String requesterEmail;
        String input;
        int page = 0;

        while (true) {

            // list out requester emails
            System.out.println("==Requester Emails==");
            String[] emails = manager.generateRequesterPage(page, PAGE_SIZE);
            for (int i = 0; i < emails.length; i++) {
                if (emails[i] != null) {
                    System.out.println(i + 1 + ") " + emails[i]);
                }
            }
            System.out.println("0) Return to issue menu");
            System.out.println("N) List next emails");
            System.out.println("C) Create new requester");
            System.out.println("ENTER:");

            input = keyboard.nextLine().toLowerCase();

            switch (input) {
                case "0":
                    return null;
                case "n":
                    // reset to first page if it's the last
                    try {
                        page += 1;
                        boolean exceedsFileSize = page * PAGE_SIZE * Requester.BYTES_SIZE_REQUESTER
                                > manager.getRequesterFileSize();
                        if (exceedsFileSize) {
                            page = 0;
                        }
                    } catch (IOException e) {
                        System.out.println("Error opening file");
                    }
                    break;
                case "c":
                    doAddRequester();
                    break;
                default:
                    try {
                        int selection = Integer.parseInt(input) - 1;
                        boolean inputOk = selection >= 0 && selection <= emails.length;
                        if (!inputOk) {
                            System.out.println("Error: Please enter a valid selection");
                        } else {
                            requesterEmail = emails[selection];
                            return requesterEmail;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter a valid selection");
                    }
                    break;
            }
        }
    }

    //-----------------------------
    /**
     * Displays a list of products and returns selected product name.
     *
     * @return (out) String - the selected product name.
     */
    //---
    public String selectProduct() {
        Scanner keyboard = new Scanner(System.in);
        String productName;
        String input;
        int page = 0;

        while (true) {
            // list out product names
            System.out.println("==Product==");
            String[] products = manager.generateProductPage(page, PAGE_SIZE);
            for (int i = 0; i < products.length; i++) {
                if (products[i] != null) {
                    System.out.println(i + 1 + ") " + products[i]);
                }
            }
            System.out.println("0) Return to issue menu");
            System.out.println("N) List next products");
            System.out.println("ENTER:");

            input = keyboard.nextLine().toLowerCase();

            switch (input) {
                case "0":
                    return null;
                case "n":
                    // reset to first page if it's the last
                    try {
                        page += 1;
                        boolean exceedsFileSize = page * PAGE_SIZE * Requester.BYTES_SIZE_REQUESTER
                                >= manager.getProductFileSize();
                        if (exceedsFileSize) {
                            page = 0;
                        }
                    } catch (IOException e) {
                        System.out.println("Error opening file");
                    }
                    break;
                default:
                    try {
                        int selection = Integer.parseInt(input) - 1;
                        boolean inputOk = selection >= 0 && selection <= products.length;
                        if (!inputOk) {
                            System.out.println("Error: Please enter a valid selection");
                        } else {
                            productName = products[selection];
                            return productName;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter a valid selection");
                    }
                    break;
            }
        }
    }

    //-----------------------------
    /**
     * Displays a list of product releases based on provided product and returns selected release.
     *
     * @return (out) String - the selected release ID.
     */
    //---
    public String selectRelease(String productName) {
        Scanner keyboard = new Scanner(System.in);
        String releaseName;
        String lastReleaseName = null;
        String input;

        while (true) {
            // list out product names
            System.out.println("==Release==");
            String[] releases = manager.generateReleasePage(productName, lastReleaseName, PAGE_SIZE);
            for (int i = 0; i < releases.length; i++) {
                if (releases[i] != null) {
                    System.out.println(i + 1 + ") " + releases[i]);
                }
            }
            System.out.println("0) Return to issue menu");
            System.out.println("N) List next releases");
            System.out.println("ENTER:");

            input = keyboard.nextLine().toLowerCase();

            switch (input) {
                case "0":
                    return null;
                case "n":
                    // reset to first page if it's the last
                    lastReleaseName = releases[PAGE_SIZE - 1];
                    break;
                default:
                    try {
                        int selection = Integer.parseInt(input) - 1;
                        boolean inputOk = selection >= 0 && selection <= releases.length;
                        if (!inputOk) {
                            System.out.println("Error: Please enter a valid selection");
                        } else {
                            releaseName = releases[selection];
                            return releaseName;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter a valid selection");
                    }
                    break;
            }
        }
    }


    //-----------------------------
    /**
     * Displays a list of change items based on provided release and returns changeID.
     *
     * @return (out) int - The selected change ID.
     */
    //---
    public int selectChangeItem(String productName, String releaseID, String mode) {
        Scanner keyboard = new Scanner(System.in);
        ChangeItem[] changeItems;
        int lastChangeID = -1;
        int changeID;
        String input;

        while (true) {
            // list out changes
            System.out.println("Changes for " + productName.trim() + " " + releaseID.trim() + ":");
            System.out.println("=======================================================================================");
            System.out.println("                                                                            Anticipated");
            System.out.printf("   %10s  %-30s  %12s  %10s  %14s\n", "ChangeID", "Description", "Status", "Priority", " Release Date");
            System.out.println("   ----------  ------------------------------  ------------  ----------  --------------");

            if (mode.equals("pending")) {
                changeItems = manager.generateFilteredChangesPage(productName, lastChangeID, PAGE_SIZE, mode);
            } else if (mode.equals("completed")) {
                changeItems = manager.generateFilteredChangesPage(productName, lastChangeID, PAGE_SIZE, mode);
            } else {
                changeItems = manager.generateChangeItemPage(productName, releaseID, lastChangeID, PAGE_SIZE);
            }

            for (int i = 0; i < changeItems.length; i++) {
                if (changeItems[i] != null) {
                    ChangeItem item = changeItems[i];
                    System.out.print(i + 1 + ") ") ;
                    System.out.printf(" %9d  %-30s  %12s  %10s  %14s\n", item.getChangeID(), new String(item.getChangeDescription()),
                            new String(item.getStatus()).trim(), item.getPriority(), item.getAnticipatedReleaseDate());
                }
            }
            System.out.println("0) Return to issue menu");
            System.out.println("N) List next change items");
            System.out.println("C) Create new change item");
            System.out.println("ENTER:");

            input = keyboard.nextLine().toLowerCase();

            // handling user input
            switch (input) {
                case "0":
                    return -1;
                case "n":
                    // reset to first page if it's the last
                    if (changeItems[PAGE_SIZE - 1] == null) {
                        lastChangeID = -1;
                    } else {
                        lastChangeID = changeItems[PAGE_SIZE - 1].getChangeID();
                    }
                    break;
                case "c":
                    doAddChangeItem(productName, releaseID);
                    break;
                default:
                    try {
                        int selection = Integer.parseInt(input) - 1;
                        boolean inputOk = selection >= 0 && selection <= changeItems.length;
                        if (!inputOk) {
                            System.out.println("Error: Please enter a valid selection");
                        } else {
                            changeID = changeItems[selection].getChangeID();
                            return changeID;
                        }
                    } catch (NullPointerException e) {
                        System.out.println("Error: Please enter a valid selection");
                    }
                    break;
            }
        }
    }

    //-----------------------------
    /**
     * Helper method that validates the department of user input, and returns it.
     *
     * @param validator (in) InputValidator - The input validator for length checking.
     * @return (out) String - A valid department.
     */
    //---
    public String getValidDepartmentUserInput(InputValidator validator) {
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

    //-----------------------------
    /**
     * Helper method that validates the priority of user input, and returns a character.
     *
     * @param validator (in) InputValidator - The input validator for length checking.
     * @return (out) char - A valid priority
     */
    //---
    public char getValidPriorityUserInput(InputValidator validator) {
        String priority = "";
        boolean priorityValid = false;
        while (!priorityValid) {
            priority = getStringUserInput(1, validator);
            priorityValid = priority.equals("1") || priority.equals("2")
                    || priority.equals("3") || priority.equals("4")
                    || priority.equals("5") || priority.isEmpty();
            if (!priorityValid) {
                System.out.println("Error: Please enter a valid department");
            }
        }
        if (priority.isEmpty()) {
            return ' ';
        }

        return priority.charAt(0);
    }

    //-----------------------------
    /**
     * Helper method that validates the department of user input, and returns it.
     *
     * @param validator (in) InputValidator - The input validator for length checking.
     * @return (out) String - A valid department.
     */
    //---
    public String getValidStatusUserInput(InputValidator validator) {
        String status = "";
        boolean statusValid = false;
        while (!statusValid) {
            status = getStringUserInput(ChangeItem.MAX_STATUS, validator);
            statusValid = status.equals("Open") || status.equals("Assessed")
                    || status.equals("In-Progress") || status.equals("Completed")
                    || status.equals("Cancelled");
            if (!statusValid) {
                System.out.println("Error: Please enter a valid status");
            }
        }
        return status;
    }

    //-----------------------------
    /**
     * Helper method that validates the date of user input, and returns it.
     *
     * @return (out) LocalDate - A valid date.
     */
    //---
    public LocalDate getValidLocalDateInput() {
        String input;
        boolean dateValid = false;
        LocalDate localDate = null;
        while (!dateValid) {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("> ");
            input = keyboard.nextLine();
            try {
                localDate = LocalDate.parse(input);
                dateValid = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: Please enter a valid date");
            }
        }
        return localDate;
    }

    //-----------------------------
    /**
     * Helper method that validates the date of user input, and returns it.
     *
     * @return (out) LocalDate - A valid date or null.
     */
    //---
    public LocalDate getValidLocalDateInputOrNull() {
        String input;
        boolean dateValid = false;
        LocalDate localDate = null;
        while (!dateValid) {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("> ");
            input = keyboard.nextLine();
            if (!input.isEmpty()) {
                try {
                    localDate = LocalDate.parse(input);
                    dateValid = true;
                } catch (DateTimeParseException e) {
                    System.out.println("Error: Please enter a valid date");
                }
            } else {
                break;
            }
        }
        return localDate;
    }

    //-----------------------------
    /**
     * Helper method that gets the user input, it supports length checking.
     *
     * @param length (in) int - length of string for validation.
     * @param validator (in) InputValidator - The input validator for length checking.
     * @return (out) String - A valid user input.
     */
    //---
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

    //-----------------------------
    /**
     * Helper method that determines if user selects yes or no.
     * @return (out) boolean - yes or no flag.
     */
    //---
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
     * Closes the data file. and exits the application.
     */
    //---
    public void exitSystem() {
        manager.closeFiles();
        TextMenu.setShouldMainMenuRepeat(false);
    }
}
