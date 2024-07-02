/**
 * File: ChangeItem.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
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
    private String releaseID;
    private String changeDescription;
    private int priority;
    private String status;
    private LocalDate anticipatedReleaseDate;

    //=============================
    // Constructors
    //=============================

    //-----------------------------
    /**
     * Three argument constructor for ChangeItem. Change ID is randomly generated, using
     * utility function generateRandomChangeID().
     *
     * @param priority (in) int - Priority of the change item (1 - 5)
     * @param status (in) String - status of the change item
     *               (Open, Assessed, In Progress, Completed, Cancelled).
     * @param anticipatedReleaseDate - Anticipated release date of the change item.
     */
    //---
    public ChangeItem(int priority, String status, LocalDate anticipatedReleaseDate) {}

    //=============================
    // Methods
    //=============================

    //-----------------------------
    /**
     * Generates a random change ID upon instantiation of object.
     */
    //---
    private void generateRandomChangeID() {}

    public void readChangeItems(RandomAccessFile file) {}
}
