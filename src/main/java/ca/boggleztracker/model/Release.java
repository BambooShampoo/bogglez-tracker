/**
 * File: Release.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
 * - 2024-07-02: System redesign remove storing records into RAM
 * - 2024-07-06: writeRelease implementation
 * - 2024-07-08: readRelease implementation
 * Purpose:
 * Release class represents a release of a product in the system and is responsible for
 * managing the change items of the release. The class stores data such as release ID,
 * release date, and a list of change items.
 */
package ca.boggleztracker.model;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;

public class Release {
    //=============================
    // Constants and static fields
    //=============================
    public static final int MAX_RELEASE_ID = 8;
    public static final int BYTES_SIZE_RELEASE = 56;
    //=============================
    // Member fields
    //=============================
    private char[] productName;
    private char[] releaseID;
    private LocalDate date;

    //=============================
    // Constructors
    //=============================

    //-----------------------------
    /**
     * Two argument constructor for Release.
     *
     * @param productName (in) String - Product name of release version.
     * @param releaseID (in) String - ID of the release version.
     * @param date (in) LocalDate - Date of the release.
     */
    //---
    public Release(String productName, String releaseID, LocalDate date) {
        this.productName = ScenarioManager.padCharArray(productName.toCharArray(), Product.MAX_PRODUCT_NAME);
        this.releaseID = ScenarioManager.padCharArray(releaseID.toCharArray(), MAX_RELEASE_ID);
        this.date = date;
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
    public void writeRelease(RandomAccessFile file) throws IOException {
        file.writeChars(new String(productName));
        file.writeChars(new String(releaseID));
        file.writeChars(date.toString()); // format to yyyy-mm-dd (20 bytes)
        System.out.println("Release: " + file.length());
    }

    //-----------------------------
    /**
     * returns the ReleaseID of the object that it calls from.
     * @return (out) String - Release ID of the object.
     */
    //---
    public String getReleaseID(){
        return String.valueOf(releaseID);
    }

    //-----------------------------
    /**
     * Checks file to see if an exact permutation of the three ProductRelease parameters already exists.
     *
     * @param file (in) RandomAccessFile - The file to read from.
     * @param productName (in) String - Product name of release version.
     * @param releaseID (in) String - ID of the release version.
     * @param date (in) LocalDate - Date of the release.
     */
    //---
    public static boolean releaseExists(RandomAccessFile file, String productName, String releaseID, LocalDate date) { return false; }

    //-----------------------------
    /**
     * Reads individual release record from file
     *
     * @param file (in) RandomAccessFile - The file to read from.
     */
    //---
    public void readRelease(RandomAccessFile file) throws IOException{
        productName = ScenarioManager.readCharsFromFile(file, Product.MAX_PRODUCT_NAME);
        releaseID = ScenarioManager.readCharsFromFile(file, Release.MAX_RELEASE_ID);
        date = ScenarioManager.readDateFromFile(file);
    }
}
