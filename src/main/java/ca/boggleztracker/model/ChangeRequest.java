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
    private LocalDate reportedDate;
    private Requester requester;

    //-----------------------------
    /**
     * Two argument constructor for ChangeRequest
     *
     * @param reportedDate (in) LocalDate - Date of reported issue.
     * @param requester (in) Requester - The requester who reported the change request.
     */
    //---
    public ChangeRequest(LocalDate reportedDate, Requester requester) {}
}
