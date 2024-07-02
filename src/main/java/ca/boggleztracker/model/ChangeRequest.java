/**
 * File: ChangeRequest.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
 * Purpose:
 * ChangeRequest class represents a change request of a product, storing data such as
 * reported date and the requester.
 */
package ca.boggleztracker.model;

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
     *
     */
    //---
    public ChangeRequest(int changeID, String productName, String reportedRelease, String requesterEmail, LocalDate reportedDate) {}
}
