/**
 * File: Release.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
 * Purpose:
 * Release class represents a release of a product in the system and is responsible for
 * managing the change items of the release. The class stores data such as release ID,
 * release date, and a list of change items.
 */
package ca.boggleztracker.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Release {
    //=============================
    // Member fields
    //=============================
    private String releaseID;
    private LocalDate date;
    private List<ChangeItem> changes = new ArrayList<>();

    //=============================
    // Constructors
    //=============================

    //-----------------------------
    /**
     * Two argument constructor for Release.
     *
     * @param releaseID (in) String - ID of the release version.
     * @param date (in) LocalDate - Date of the release.
     */
    //---
    public Release(String releaseID, LocalDate date) {}

    //=============================
    // Methods
    //=============================

    //-----------------------------
    /**
     * Getter method for accessing the list of change items.
     *
     * @return (out) List - Reference to the change items array list.
     */
    //---
    public List<ChangeItem> getChanges() {
        return new ArrayList<ChangeItem>();
    }

    //-----------------------------

    /**
     * Retrieves a specific change item based on the number displayed on UI.
     *
     * @param number (in) int - Position of the change item in the displayed list.
     * @return (out) ChangeItem - ChangeItem object at the specified position.
     */
    //---
    public ChangeItem getSpecifedChangeItem(int number) {
        return new ChangeItem(1, "", LocalDate.of(2000, 1, 2) );
    }

    //-----------------------------
    /**
     * Adds a new change item to the array list.
     *
     * @param status (in) String - Status of the change item.
     * @param priority (in) int - priority of the change item.
     * @param anticipatedReleaseDate (in) LocalDate - Anticipated release date of the change item.
     */
    //---
    public void addChangeItem(String status, int priority, LocalDate anticipatedReleaseDate) {}

    //-----------------------------
    /**
     * Updates the release date of the change item
     *
     * @param date (in) LocalDate - New date of release.
     */
    //---
    public void updateRelease(LocalDate date) {}

    //-----------------------------
    /**
     * Filters through the list of change items and retrieves all pending changes.
     *
     * @return (out) List - ArrayList of ChangeItem objects with pending changes.
     */
    //---
    public List<ChangeItem> filterAllPendingChanges() {
        return new ArrayList<ChangeItem>();
    }

    //-----------------------------
    /**
     * Filters through the list of change items and retrieves all completed changes.
     *
     * @return (out) List - ArrayList of ChangeItem objects with completed changes.
     */
    //---
    public List<ChangeItem> filterAllCompletedChanges() {
        return new ArrayList<ChangeItem>();
    }
}
