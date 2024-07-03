/**
 * File: ScenarioManager.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
 * - 2024-07-02: System redesign remove storing records into RAM
 * Purpose:
 * ScenarioManager class is responsible for opening and closing the data file,
 * populating the array lists of products and requesters, and supports various interactions
 * with these lists.
 */

package ca.boggleztracker.model;

import java.io.RandomAccessFile;
import java.time.LocalDate;

public class ScenarioManager {
    //=============================
    // Constants and static fields
    //=============================
    private static final String REQUESTER_FILE = "requester.dat";
    private static final String PRODUCT_FILE = "product.dat";
    private static final String RELEASE_FILE = "release.dat";
    private static final String CHANGE_ITEM_FILE = "change_item.dat";
    private static final String CHANGE_REQUEST_FILE = "change-request.dat";

    //=============================
    // Member fields
    //=============================
    private RandomAccessFile file;

    //=============================
    // Methods
    //=============================

    //-----------------------------
    /**
     * Opens the specified file for reading and writing.
     *
     * @param fileName (in) String - Name of the file to open.
     * @param mode (in) String - Access mode.
     */
    //---
    public void openFile(String fileName, String mode) {}

    //-----------------------------
    /**
     * Adds a new requester to file.
     *
     * @param email (in) String - Email of new requester.
     * @param name (in) String - Name of new requester.
     * @param phoneNumber (in) int - Phone number of new requester.
     * @param department (in) String - Department of new requester. This can be left as empty.
     */
    //---
    public void addRequester(String email, String name, int phoneNumber, String department) {}

    //-----------------------------
    /**
     * Adds a new product to file.
     *
     * @param productName (in) String - Name of the new product.
     */
    //---
    public void addProduct(String productName) {}

    //-----------------------------
    /**
     * Adds a new request to the file.
     *
     * @param changeID (in) int - Identifier of the ChangeItem this request is for.
     * @param productName (in) String - Name of product of change request.
     * @param reportedRelease (in) String - Release version of the product
     * @param requesterEmail (in) String - Email of the requester.
     * @param reportedDate (in) LocalDate - Date of when the request was made.
     */
    //---
    public void addChangeRequest(int changeID, String productName, String reportedRelease,
                                 String requesterEmail, LocalDate reportedDate) {}

    /**
     * Adds a new change item to the file.
     *
     * @param releaseID (in) int - Identifier of the release this change item is for.
     * @param productName (in) String - Product name of reported change item.
     * @param changeDescription (in) String - Change description of change.
     * @param priority (in) int - Priority of the change.
     * @param status (in) String - Status of the change.
     * @param anticipatedReleaseDate (in) LocalDate - Date of anticipated release date.
     */
    public void addChangeItem(String releaseID, String productName, String changeDescription, int priority,
                              String status, LocalDate anticipatedReleaseDate) {}

    /**
     * Modifies a specific change item in the file.
     *
     * @param changeID (in) int - Change ID reference to be searched in file.
     * @param modifiedChangeItem (in) ChangeItem - The new modified change item to be written
     *                          into file.
     */
    public void modifyChangeItem(int changeID, ChangeItem modifiedChangeItem) {}

    /**
     * Modifies a specific release in the file.
     *
     * @param releaseID (in) String - Release ID reference to be searched in file.
     * @param modifiedRelease (in) Release - The new modified release to be written into file.
     */
    public void modifyRelease(String releaseID, Release modifiedRelease) {}

    /**
     * Adds a new release to the file.
     *
     * @param productName (in) String - Identifier of the product this release is for.
     * @param releaseID (in) String - Identifier for the release
     * @param date (in) LocalDate - Date of release
     */
    public void addRelease(String productName, String releaseID, LocalDate date) {}


    //-----------------------------
    /**
     * Gets a list of Requesters from the file to display for user.
     *
     * @param page (in) int - Counter to track what page of Requester are displayed for user.
     * @param pageSize (in) int - How many items of data each page can hold.
     */
    //---
    public String generateRequesterPage(int page, int pageSize) {
        return "";
    }

    //-----------------------------
    /**
     * Gets a list of Products from the file to display for user.
     *
     * @param page (in) int - Counter to track what page of Product are displayed for user.
     * @param pageSize (in) int - How many items of data each page can hold.
     */
    //---
    public String generateProductPage(int page, int pageSize) {
        return "";
    }

    //-----------------------------
    /**
     * Gets a list of Valid Releases from the file to display for user.
     *
     * @param page (in) int - Counter to track what page of Release are displayed for user.
     * @param pageSize (in) int - How many items of data each page can hold.
     */
    //---
    public String generateReleasePage(int page, int pageSize) {
        return "";
    }

    //-----------------------------
    /**
     * Gets a list of Valid ChangeItem from the file to display for user.
     *
     * @param page (in) int - Counter to track what page of ChangeItem are displayed for user.
     * @param pageSize (in) int - How many items of data each page can hold.
     */
    //---
    public String generateChangeItemPage(int page, int pageSize) {
        return "";
    }

    //-----------------------------
    /**
     * Gets a list of all pending change items of a specific product.
     *
     * @param productName (in) String - Product name reference to find all pending changes.
     */
    //---
    public String generatePendingChangesPage(String productName) {
        return "";
    }

    //-----------------------------
    /**
     * Gets a list of all completed changes for customer notification.
     */
    //---
    public String generateCompletedChangesPage() {
        return "";
    }

    //-----------------------------
    /**
     * Closes the file, on system shut down
     */
    //---
    public void closeFile() {}
}
