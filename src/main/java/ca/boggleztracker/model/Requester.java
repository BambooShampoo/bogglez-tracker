/**
 * File: Requester.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
 * Purpose:
 * Requester class represents a requester in the system, storing data such as email,
 * name, phone number, and department.
 */
package ca.boggleztracker.model;

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
}
