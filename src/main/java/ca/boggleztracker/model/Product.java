package ca.boggleztracker.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private String productName;
    private List<Release> releases = new ArrayList<>();

    public Product(String productName) {}

    public List<Release> getReleases() {}

    public Release getSpecifedRelease(String releaseID) {}

    public void addRelease(String releaseID, LocalDate date) {}

    public List<ChangeItem> getListPendingChanges() {}

    public List<ChangeItem> getListCompletedChanges() {}
}
