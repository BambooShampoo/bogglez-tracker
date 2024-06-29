package ca.boggleztracker.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private String productName;
    private List<Release> releases = new ArrayList<>();

    public Product(String productName) {}

    public List<Release> getReleases() {
        return new ArrayList<Release>();
    }

    public Release getSpecifedRelease(String releaseID) {
        return new Release("", LocalDate.of(2020, 1, 8));
    }

    public void addRelease(String releaseID, LocalDate date) {}

    public List<ChangeItem> getListPendingChanges() {
        return new ArrayList<ChangeItem>();
    }

    public List<ChangeItem> getListCompletedChanges() {
        return new ArrayList<ChangeItem>();
    }
}
