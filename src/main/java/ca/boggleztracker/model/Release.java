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

import java.io.RandomAccessFile;
import java.time.LocalDate;

public class Release {
    //=============================
    // Member fields
    //=============================
    private String productName;
    private String releaseID;
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
    public Release(String productName, String releaseID, LocalDate date) {}

    //=============================
    // Methods
    //=============================

    public void readRelease(RandomAccessFile file) {}
}
