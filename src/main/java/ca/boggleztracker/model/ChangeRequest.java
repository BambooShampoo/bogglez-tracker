/**
 * File: ChangeRequest.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
 * - 2024-07-02: System redesign remove storing records into RAM
 * Purpose:
 * ChangeRequest class represents a change request of a product, storing data such as
 * reported date and the requester.
 */
package ca.boggleztracker.model;

import java.io.RandomAccessFile;
import java.time.LocalDate;

public class ChangeRequest {
    //=============================
    // Member fields
    //=============================
    private int changeID;
    private String productName;
    private String reportedRelease;
    private String requesterEmail;
    private LocalDate reportedDate;

    //-----------------------------
    /**
     * Five argument constructor for ChangeRequest.
     *
     * @param changeID (in) int - Identifier for the ChangeItem this request is for.
     * @param productName (in) String - Name of product of change request.
     * @param reportedRelease (in) String - Release version of the product
     * @param requesterEmail (in) String - Email of the requester.
     * @param reportedDate (in) LocalDate - Date of when the request was made.
     */
    //---
    public ChangeRequest(int changeID, String productName, String reportedRelease,
                         String requesterEmail, LocalDate reportedDate) {}

    //-----------------------------
    /**
     * Writes the contents of release object to the release file.
     *
     * @param file (in) RandomAccessFile - The file to read from.
     */
    //---
    public void writeChangeRequest(RandomAccessFile file) {}

    //-----------------------------
    /**
     * Checks file to see if exact permutation of the 5 ChangeRequest parameters already exists.
     *
     * @param file (in) RandomAccessFile - The file to read from.
     * @param changeID (in) int - Identifier for the ChangeItem this request is for.
     * @param productName (in) String - Name of product of change request.
     * @param reportedRelease (in) String - Release version of the product
     * @param requesterEmail (in) String - Email of the requester.
     * @param reportedDate (in) LocalDate - Date of when the request was made.
     */
    //---
    public static boolean changeRequestExists(RandomAccessFile file, int changeID, String productName,
                                              String reportedRelease, String requesterEmail, LocalDate reportedDate) {
        return false;
    }

    /**
     * Reads individual change request record from file
     *
     * @param file (in) RandomAccessFile - The file to read from.
     */
    //---
    public void readChangeRequest(RandomAccessFile file) {}
}
