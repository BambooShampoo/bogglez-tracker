package ca.boggleztracker.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ScenarioManager implements Iterable<Product> {
    private List<Product> products = new ArrayList<>();
    private List<Requester> requesters = new ArrayList<>();

    public List<Product> getProducts() {}

    public List<Requester> getRequesters() {}

    public boolean requesterExists(String email) {}

    public void addRequester(String email, String name, int phoneNumber, String department) {}

    public void addProduct(String productName) {}

    public Requester getSpecifiedRequester(Requester requester) {}

    public Product getSpecifiedProduct(Product product) {}

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }
}
