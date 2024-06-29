package ca.boggleztracker.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Release {
    private String releaseID;
    private LocalDate date;
    private List<ChangeItem> changes = new ArrayList<>();

    public Release(String releaseID, LocalDate date) {}

    public List<ChangeItem> getChanges() {}

    public ChangeItem getSpecifedChangeItem(int changeID) {}

    public void addChangeItem(String status, int priority, LocalDate anticipatedReleaseDate) {}

    public void updateRelease(LocalDate date) {}

    public List<ChangeItem> filterAllPendingChanges() {}

    public List<ChangeItem> filterAllCompletedChanges() {}
}
