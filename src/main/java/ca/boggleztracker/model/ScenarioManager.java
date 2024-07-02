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
     * Adds a new requester to the array list.
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
     * Adds a new product to the array list.
     *
     * @param productName (in) String - Name of the new product.
     */
    //---
    public void addProduct(String productName) {}

    //-----------------------------

    //---
    public void addRequest(int changeID, String productName, String reportedRelease, String requesterEmail, LocalDate reportedDate) {}

    // return string of entire page

    //return position on page
    public String getFileRequesters(int page, int pageSize) {
        return "";
    }

    public String getFileProducts(int page, int pageSize) {
        return "";
    }

    public String getFileReleases(int page, int pageSize) {
        return "";
    }

    public int getFileChangeItems(int page, int pageSize) {
        return 0;
    }



    //-----------------------------
    /**
     * Closes the file, on system shut down
     */
    //---
    public void closeFile() {}
}
