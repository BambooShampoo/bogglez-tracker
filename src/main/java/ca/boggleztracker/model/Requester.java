/**
 * File: Requester.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
 * - 2024-07-02: System redesign remove storing records into RAM
 * Purpose:
 * Requester class represents a requester in the system, storing data such as email,
 * name, phone number, and department.
 */
package ca.boggleztracker.model;

import java.io.RandomAccessFile;

public class Requester {
    //=============================
    // Member fields
    //=============================
    private String email;
    private String name;
    private int phoneNumber;
    private String department;

    //=============================
    // Constructors
    //=============================

    //-----------------------------
    /**
     * Four argument constructor for Requester.
     *
     * @param email (in) String - Email address of the requester.
     * @param name (in) String - Name of the requester.
     * @param phoneNumber (in) String - Phone number of the requester.
     * @param department (in) String - Department of the requester. This can be left as an empty string.
     */
    //---
    public Requester(String email, String name, int phoneNumber, String department) {}

    //=============================
    // Methods
    //=============================

    //-----------------------------
    /**
     * Writes the contents of Request object to the Request file.
     *
     * @param file (in) RandomAccessFile - The file to read from.
     */
    //---
    public void writeRequester(RandomAccessFile file) {}

    //-----------------------------
    /**
     * Checks file to see if email already exists.
     *
     * @param file (in) RandomAccessFile - The file to read from.
     * @param email (in) String - The email to be checked.
     */
    //---
    public static boolean requesterExists(RandomAccessFile file, String email) { return false; }

    //-----------------------------
    /**
     * Reads individual requester record from file
     *
     * @param file (in) RandomAccessFile - The file to read from.
     */
    //---
    public void readRequester(RandomAccessFile file) {}
}
