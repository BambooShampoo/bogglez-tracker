package ca.boggleztracker.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductManager implements Iterable<Product> {
    private List<Product> products = new ArrayList<>();

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }
}
