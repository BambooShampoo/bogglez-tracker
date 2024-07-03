/**
 * File: Product.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
 * - 2024-07-02: System redesign remove storing records into RAM
 * Purpose:
 * Product class represents a product in the system and is responsible for
 * managing the releases of the product. The class stores data such as product name
 * and a list of releases.
 */
package ca.boggleztracker.model;

import java.io.RandomAccessFile;

public class Product {
    //=============================
    // Member fields
    //=============================
    private String productName;

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
     * Writes the contents of release object to the release file.
     *
     * @param file (in) RandomAccessFile - The file to read from.
     */
    //---
    public void writeProduct(RandomAccessFile file) {}

    //-----------------------------
    /**
     * Checks file to see if email already exists.
     *
     * @param file (in) RandomAccessFile - The file to read from.
     * @param productName (in) String - The product name is checked.
     */
    //---
    public static boolean productExists(RandomAccessFile file, String productName) { return false; }

    //-----------------------------
    /**
     * Reads individual product record from file
     *
     * @param file (in) RandomAccessFile - The file to read from.
     */
    //---
    public void readProduct(RandomAccessFile file) {}
}
