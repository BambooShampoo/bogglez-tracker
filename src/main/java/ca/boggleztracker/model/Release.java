package ca.boggleztracker.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Release {
    private String releaseID;
    private LocalDate date;
    private List<ChangeItem> changes = new ArrayList<>();

    public Release(String releaseID, LocalDate date) {
        this.releaseID = releaseID;
        this.date = date;
    }

    public List<ChangeItem> getChanges() {
        return changes;
    }

    public ChangeItem getSpecifedChangeItem(int changeID) {

    }
}
