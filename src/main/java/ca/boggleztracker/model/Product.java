/**
 * File: Product.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
 * - 2024-07-02: System redesign remove storing records into RAM
 * - 2024-07-04: writeProduct implementation
 * - 2024-07-08: readProduct implementation
 * Purpose:
 * Product class represents a product in the system and is responsible for
 * managing the releases of the product. The class stores data such as product name
 * and a list of releases.
 */
package ca.boggleztracker.model;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Product {
    //=============================
    // Constants and static fields
    //=============================
    public static final int MAX_PRODUCT_NAME = 10;
    public static final int SIZE = MAX_PRODUCT_NAME * 2;
    public static final int BYTES_SIZE_PRODUCT = 20;
    //=============================
    // Member fields
    //=============================
    private char[] productName;

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
    public Product(String productName) {
        this.productName = ScenarioManager.padCharArray(productName.toCharArray(), MAX_PRODUCT_NAME);
    }

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
    public void writeProduct(RandomAccessFile file) throws IOException {
        file.writeChars(new String(productName));
        System.out.println("Product: " + file.length());
    }

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
    public void readProduct(RandomAccessFile file) throws IOException{
        productName = ScenarioManager.readCharsFromFile(file, Product.MAX_PRODUCT_NAME);
    }
}
