/**
 * File: Product.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
 * - 2024-07-02: System redesign remove storing records into RAM
 * - 2024-07-04: writeProduct implementation
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
    // Static fields
    //=============================
    public static final int MAX_PRODUCT_NAME = 10;
    public static final int SIZE = MAX_PRODUCT_NAME * 2;

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
        this.productName = padProductName(productName.toCharArray(), MAX_PRODUCT_NAME);
    }

    //=============================
    // Methods
    //=============================

    /**
     * Helper function to pad product name with spaces to ensure
     * it's of desired length.
     *
     * @param productName (in) char[] - character array to pad.
     * @param padLength (in) int - length of new character array.
     * @return (out) char[] - product name with padded spaces.
     */
    public char[] padProductName(char[] productName, int padLength) {
        char[] temp = new char[padLength];

        if (productName.length > padLength) {
            for (int i = 0; i < padLength; i++) {
                temp[i] = productName[i];
            }
        } else {
            // Copy all characters from productName
            for (int i = 0; i < productName.length; i++) {
                temp[i] = productName[i];
            }

            // Pad with spaces to max length
            for (int i = productName.length; i < padLength; i++) {
                temp[i] = ' ';
            }
        }
        return temp;
    }

    //-----------------------------
    /**
     * Writes the contents of release object to the release file.
     *
     * @param file (in) RandomAccessFile - The file to read from.
     */
    //---
    public void writeProduct(RandomAccessFile file) throws IOException {
        file.writeChars(new String(productName));
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
    public void readProduct(RandomAccessFile file) {}
}
