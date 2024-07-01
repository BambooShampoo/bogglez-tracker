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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChangeItem {
    //=============================
    // Member fields
    //=============================
    private int changeID;
    private int priority;
    private String status;
    private LocalDate anticipatedReleaseDate;
    private List<ChangeRequest> requests = new ArrayList<>();

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

    //-----------------------------
    /**
     * Updates the status, priority, and anticipated release date of change item.
     *
     * @param status (in) String - New status of change item.
     * @param priority (in) int - New priority of change item.
     * @param anticipatedReleaseDate (in) LocalDate - New anticipated or actual release date of change item.
     */
    //---
    public void updateChangeItem(String status, int priority, LocalDate anticipatedReleaseDate) {}

    //-----------------------------
    /**
     * Getter method for accessing the list of change requests.
     *
     * @return (out) List - Reference to the change requests array list.
     */
    //---
    public List<ChangeRequest> getRequests() {
        return new ArrayList<ChangeRequest>();
    }

    //-----------------------------
    /**
     * Adds a new change request to the array list.
     *
     * @param date (in) LocalDate - Current date of the reported change request.
     * @param requester (in) Requester - Reference to the requester of reported request.
     */
    //---
    public void addChangeRequest(LocalDate date, Requester requester) {}
}
