package ca.boggleztracker.model;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String productName;
    private List<Release> releases = new ArrayList<>();

    public Product(String productName) {
        this.productName = productName;
    }
}
