package ca.boggleztracker.model;

import java.time.LocalDate;

public class ChangeRequest {
    private LocalDate reportedDate;
    private Requester requester;

    public ChangeRequest(LocalDate reportedDate, Requester requester) {}
}
