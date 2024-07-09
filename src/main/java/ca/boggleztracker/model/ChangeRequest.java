/**
 * File: ChangeRequest.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
 * - 2024-07-02: System redesign remove storing records into RAM
 * - 2024-07-06: writeChangeRequest implementation
 * Purpose:
 * ChangeRequest class represents a change request of a product, storing data such as
 * reported date and the requester.
 */
package ca.boggleztracker.model;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;

public class ChangeRequest {
    //=============================
    // Member fields
    //=============================
    private int changeID;
    private char[] productName;
    private char[] reportedRelease;
    private char[] requesterEmail;
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
                         String requesterEmail, LocalDate reportedDate) {
        this.changeID = changeID;
        this.productName = ScenarioManager.padCharArray(productName.toCharArray(), Product.MAX_PRODUCT_NAME);
        this.reportedRelease = ScenarioManager.padCharArray(reportedRelease.toCharArray(), Release.MAX_RELEASE_ID);
        this.requesterEmail = ScenarioManager.padCharArray(requesterEmail.toCharArray(), Requester.MAX_EMAIL);
        this.reportedDate = reportedDate;
    }

    //-----------------------------
    /**
     * Writes the contents of release object to the release file.
     *
     * @param file (in) RandomAccessFile - The file to read from.
     */
    //---
    public void writeChangeRequest(RandomAccessFile file) throws IOException {
        file.writeInt(changeID);
        file.writeChars(new String(productName));
        file.writeChars(new String(reportedRelease));
        file.writeChars(new String(requesterEmail));
        file.writeChars(reportedDate.toString()); // format to yyyy-mm-dd (20 bytes)
        System.out.println("Change Request: " + file.length());
    }

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
    public void readChangeRequest(RandomAccessFile file) throws IOException{
        changeID = file.readInt();
        productName = ScenarioManager.readCharsFromFile(file, Product.MAX_PRODUCT_NAME);
        reportedRelease = ScenarioManager.readCharsFromFile(file, Release.MAX_RELEASE_ID);
        requesterEmail = ScenarioManager.readCharsFromFile(file, Requester.MAX_EMAIL);
        reportedDate = ScenarioManager.readDateFromFile(file);
    }
}
