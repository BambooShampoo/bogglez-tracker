package ca.boggleztracker.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChangeItem {
    private int changeID;
    private int priority;
    private String status;
    private LocalDate anticipatedReleaseDate;
    private List<ChangeRequest> requests = new ArrayList<>();

    public ChangeItem(int priority, String status, LocalDate anticipatedReleaseDate) {}

    public void updateChangeItem(String status, int priority, LocalDate anticipatedReleaseDate) {}

    private void generateRandomChangeID() {}

    public List<ChangeRequest> getRequests() {}

    public void addChangeRequest(LocalDate date, Requester requester) {}
}
