/**
 * File: ChangeItem.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
 * - 2024-07-02: System redesign remove storing records into RAM
 * - 2024-07-03: Added toString() method for printing
 * - 2024-07-06: writeChangeItem implementation
 * - 2024-07-08: readChangeItem implementation
 * Purpose:
 * ChangeItem class represents a change item of a particular product release and is responsible for
 * managing the change requests of the change item. The class stores data such as changeID, priority
 * status, anticipated release date, and a list of change requests.
 */
package ca.boggleztracker.model;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ChangeItem {
    //=============================
    // Constants and static fields
    //=============================
    public static final int MAX_DESCRIPTION = 30;
    public static final int MAX_STATUS = 12;


    //=============================
    // Member fields
    //=============================
    private int changeID;
    private char[] productName;
    private char[] releaseID;
    private char[] changeDescription;
    private int priority;
    private char[] status;
    private LocalDate anticipatedReleaseDate;

    //=============================
    // Constructors
    //=============================

    //-----------------------------
    /**
     * Six argument constructor for ChangeItem. Change ID is randomly generated, using
     * utility function generateRandomChangeID().
     *
     * @param productName (in) String - Product of change  is referencing to.
     * @param releaseID (in) String - Release ID change is referencing to.
     * @param changeDescription (in) String - Description of change item.
     * @param priority (in) int - Priority of the change item (1 - 5).
     * @param status (in) String - status of the change item.
     *               (Open, Assessed, In Progress, Completed, Cancelled).
     * @param anticipatedReleaseDate - Anticipated release date of the change item.
     */
    //---
    public ChangeItem(String productName, String releaseID, String changeDescription, int priority,
                      String status, LocalDate anticipatedReleaseDate) {
        this.changeID = generateRandomChangeID();
        this.productName = ScenarioManager.padCharArray(productName.toCharArray(), Product.MAX_PRODUCT_NAME);
        this.releaseID = ScenarioManager.padCharArray(releaseID.toCharArray(), Release.MAX_RELEASE_ID);
        this.changeDescription = ScenarioManager.padCharArray(changeDescription.toCharArray(), MAX_DESCRIPTION);
        this.priority = priority;
        this.status = ScenarioManager.padCharArray(status.toCharArray(), MAX_STATUS);
        this.anticipatedReleaseDate = anticipatedReleaseDate;
    }

    //-----------------------------
    /**
     * Six argument constructor for creating a "modifed" ChangeItem.
     *
     * @param changeID (in) int - change ID of the change item.
     * @param productName (in) String - Product of change  is referencing to.
     * @param releaseID (in) String - Release ID change is referencing to
     * @param changeDescription (in) String - Description of change item.
     * @param priority (in) int - Priority of the change item (1 - 5)
     * @param status (in) String - status of the change item
     *               (Open, Assessed, In Progress, Completed, Cancelled).
     * @param anticipatedReleaseDate - Anticipated release date of the change item.
     */
    //---
    public ChangeItem(int changeID, String productName, String releaseID, String changeDescription,
                      int priority, String status, LocalDate anticipatedReleaseDate) {
        this.changeID = changeID;
        this.productName = ScenarioManager.padCharArray(productName.toCharArray(), Product.MAX_PRODUCT_NAME);
        this.releaseID = ScenarioManager.padCharArray(releaseID.toCharArray(), Release.MAX_RELEASE_ID);
        this.changeDescription = ScenarioManager.padCharArray(changeDescription.toCharArray(), MAX_DESCRIPTION);
        this.priority = priority;
        this.status = ScenarioManager.padCharArray(status.toCharArray(), MAX_STATUS);
        this.anticipatedReleaseDate = anticipatedReleaseDate;
    }

    //=============================
    // Methods
    //=============================

    //-----------------------------
    /**
     * Generates a random change ID upon instantiation of object.
     * @return (out) int - random generated change ID.
     */
    //---
    private int generateRandomChangeID() {
        return 1; //temporary
    }

    //-----------------------------
    /**
     * Writes the contents of release object to the release file.
     *
     * @param file (in) RandomAccessFile - The file to read from.
     */
    //---
    public void writeChangeItem(RandomAccessFile file) throws IOException {
        file.writeInt(changeID);
        file.writeChars(new String(productName));
        file.writeChars(new String(releaseID));
        file.writeChars(new String(changeDescription));
        file.writeInt(priority);
        file.writeChars(new String(status));
        file.writeChars(anticipatedReleaseDate.toString()); // format to yyyy-mm-dd (20 bytes)
        System.out.println("Change Item: " + file.length());
    }

    //-----------------------------
    /**
     * Checks file to see if exact permutation of the 3 ChangeItem parameters already exists.
     *
     * @param file (in) RandomAccessFile - The file to read from.
     * @param priority (in) int - Priority of the change item (1 - 5)
     * @param status (in) String - status of the change item
     *               (Open, Assessed, In Progress, Completed, Cancelled).
     * @param anticipatedReleaseDate - Anticipated release date of the change item.
     */
    //---
    public static boolean changeItemExists(RandomAccessFile file, int priority, String status, LocalDate anticipatedReleaseDate) { return false; }

    //-----------------------------
    /**
     * Reads individual change item record from file
     *
     * @param file (in) RandomAccessFile - The file to read from.
     */
    //---
    public void readChangeItems(RandomAccessFile file) throws IOException{
        changeID = file.readInt();
        productName = ScenarioManager.readCharsFromFile(file, Product.MAX_PRODUCT_NAME);
        releaseID = ScenarioManager.readCharsFromFile(file, Release.MAX_RELEASE_ID);
        changeDescription = ScenarioManager.readCharsFromFile(file, MAX_DESCRIPTION);
        priority = file.readInt();
        status = ScenarioManager.readCharsFromFile(file, MAX_STATUS);
        anticipatedReleaseDate = ScenarioManager.readDateFromFile(file);
    }
    //---

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "Change Details:\n" +
                "Change ID: " + changeID + "\n" +
                "Product Name: " + new String(productName) + "\n" +
                "Release ID: " + new String(releaseID) + "\n" +
                "Change Description: " + new String(changeDescription) + "\n" +
                "Priority: " + priority + "\n" +
                "Status: " + new String(status) + "\n" +
                "Anticipated Release Date: " + anticipatedReleaseDate.format(formatter);
    }
}
