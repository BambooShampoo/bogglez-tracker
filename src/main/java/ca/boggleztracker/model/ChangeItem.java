/**
 * File: ChangeItem.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
 * - 2024-07-02: System redesign remove storing records into RAM
 * Purpose:
 * ChangeItem class represents a change item of a particular product release and is responsible for
 * managing the change requests of the change item. The class stores data such as changeID, priority
 * status, anticipated release date, and a list of change requests.
 */
package ca.boggleztracker.model;

import java.io.RandomAccessFile;
import java.time.LocalDate;

public class ChangeItem {
    //=============================
    // Member fields
    //=============================
    private int changeID;
    private String productName;
    private String changeDescription;
    private int priority;
    private String releaseID;
    private String status;
    private LocalDate anticipatedReleaseDate;

    //=============================
    // Constructors
    //=============================

    //-----------------------------
    /**
     * Six argument constructor for ChangeItem. Change ID is randomly generated, using
     * utility function generateRandomChangeID().
     *
     * @param releaseID (in) String - Release ID change is referencing to
     * @param changeDescription (in) String - Description of change item.
     * @param priority (in) int - Priority of the change item (1 - 5)
     * @param status (in) String - status of the change item
     *               (Open, Assessed, In Progress, Completed, Cancelled).
     * @param anticipatedReleaseDate - Anticipated release date of the change item.
     */
    //---
    public ChangeItem(String releaseID, String productName, String changeDescription, int priority,
                      String status, LocalDate anticipatedReleaseDate) {}

    //-----------------------------
    /**
     * Six argument constructor for creating a "modifed" ChangeItem.
     *
     * @param changeID (in) int - change ID of the change item.
     * @param releaseID (in) String - Release ID change is referencing to
     * @param changeDescription (in) String - Description of change item.
     * @param priority (in) int - Priority of the change item (1 - 5)
     * @param status (in) String - status of the change item
     *               (Open, Assessed, In Progress, Completed, Cancelled).
     * @param anticipatedReleaseDate - Anticipated release date of the change item.
     */
    //---
    public ChangeItem(int changeID, String productName, String releaseID, String changeDescription, int priority, String status, LocalDate anticipatedReleaseDate) {}

    //=============================
    // Methods
    //=============================

    //-----------------------------
    /**
     * Generates a random change ID upon instantiation of object.
     */
    //---
    private void generateRandomChangeID() {}

    //-----------------------------
    /**
     * Writes the contents of release object to the release file.
     *
     * @param file (in) RandomAccessFile - The file to read from.
     */
    //---
    public void writeChangeItem(RandomAccessFile file) {}

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
    public void readChangeItems(RandomAccessFile file) {}
}
