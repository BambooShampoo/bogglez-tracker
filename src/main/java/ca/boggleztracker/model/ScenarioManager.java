/**
 * File: ScenarioManager.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
 * Purpose:
 * ScenarioManager class is responsible for opening and closing the data file,
 * populating the array lists of products and requesters, and supports various interactions
 * with these lists.
 */

package ca.boggleztracker.model;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ScenarioManager {
    //=============================
    // Member fields
    //=============================
    private List<Product> products = new ArrayList<>();
    private List<Requester> requesters = new ArrayList<>();
    private RandomAccessFile file;

    //=============================
    // Constructors
    //=============================

    //-----------------------------

    /**
     * One argument constructor for ScenarioManager.
     *
     * @param filePath (in) String - Path of the file to read/write data.
     */
    //---
    public ScenarioManager(String filePath) {}

    //=============================
    // Methods
    //=============================

    //-----------------------------
    /**
     * Utility method to open the file and parse its data to populate products and requesters,
     * and any other relevant data.
     */
    //---
    public void populateFromFile() {}

    //-----------------------------
    /**
     * Getter method for product array list.
     */
    //---
    public List<Product> getProducts() {
        return new ArrayList<Product>();
    }

    //-----------------------------
    /**
     * Getter method to array for requester array list.
     */
    //---
    public List<Requester> getRequesters() {
        return new ArrayList<Requester>();
    }

    //-----------------------------
    /**
     * Checks if requester already exists in system when creating a new requester.
     *
     * @param email (in) String - email of specified requester to check
     * @return (out) boolean - true if requester with given email exists, false otherwise.
     */
    //---
    public boolean requesterExists(String email) {
        return true;
    }

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
    /**
     * Retrieves a specific requester based on the number displayed on UI.
     *
     * @param number (in) int - Position of the requester in displayed list.
     * @return (out) Requester - Requester object at the specified position.
     */
    //---
    public Requester getSpecifiedRequester(int number) {
        return new Requester("", "", 1, "");
    }

    //-----------------------------
    /**
     * Retrieves a specific product based on number on list displayed.
     *
     * @param number (in) int - Position of the product in displayed list.
     * @return (out) Product - Product object at the specified position.
     */
    //---
    public Product getSpecifiedProduct(int number) {
        return new Product("");
    }

    //-----------------------------
    /**
     * Closes the file, on system shut down
     */
    //---
    public void closeFile() {}
}
