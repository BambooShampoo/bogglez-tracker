package ca.boggleztracker.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Release {
    private String releaseID;
    private LocalDate date;
    private List<ChangeItem> changes = new ArrayList<>();

    public Release(String releaseID, LocalDate date) {}

    public List<ChangeItem> getChanges() {
        return new ArrayList<ChangeItem>();
    }

    public ChangeItem getSpecifedChangeItem(int changeID) {
        return new ChangeItem(1, "", LocalDate.of(2000, 1, 2) );
    }

    public void addChangeItem(String status, int priority, LocalDate anticipatedReleaseDate) {}

    public void updateRelease(LocalDate date) {}

    public List<ChangeItem> filterAllPendingChanges() {
        return new ArrayList<ChangeItem>();
    }

    public List<ChangeItem> filterAllCompletedChanges() {
        return new ArrayList<ChangeItem>();
    }
}
