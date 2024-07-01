/**
 * File: Product.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
 * Purpose:
 * Product class represents a product in the system and is responsible for
 * managing the releases of the product. The class stores data such as product name
 * and a list of releases.
 */
package ca.boggleztracker.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Product {
    //=============================
    // Member fields
    //=============================
    private String productName;
    private List<Release> releases = new ArrayList<>();

    //=============================
    // Constructors
    //=============================

    //-----------------------------
    /**
     * One argument constructor for Product.
     *
     * @param productName (in) String - Name of the product.
     */
    //---
    public Product(String productName) {}

    //=============================
    // Methods
    //=============================

    //-----------------------------
    /**
     * Getter method for accessing the list of releases
     *
     * @return (out) List - Reference to the releases array list.
     */
    //---
    public List<Release> getReleases() {
        return new ArrayList<Release>();
    }

    //-----------------------------
    /**
     * Retrieves a specific release based on the number displayed on UI.
     *
     * @param number (in) int - Position of the release in the displayed list.
     * @return (out) Release - Release object at the specified position.
     */
    //---
    public Release getSpecifedRelease(int number) {
        return new Release("", LocalDate.of(2020, 1, 8));
    }

    //-----------------------------
    /**
     * Adds a new release version of product to the array list.
     *
     * @param releaseID (in) String - ID of the new release.
     * @param date (in) LocalDate - Release date of the new release.
     */
    //---
    public void addRelease(String releaseID, LocalDate date) {}

    //-----------------------------
    /**
     * Retrieves the list of all pending changes of the product.
     *
     * @return (out) List - ArrayList of ChangeItem objects with pending changes.
     */
    //---
    public List<ChangeItem> getListPendingChanges() {
        return new ArrayList<ChangeItem>();
    }

    //-----------------------------
    /**
     * Retrieves the list of all completed change items of the product.
     *
     * @return (out) List - ArrayList of ChangeItem objects with completed changes.
     */
    //---
    public List<ChangeItem> getListCompletedChanges() {
        return new ArrayList<ChangeItem>();
    }
}
