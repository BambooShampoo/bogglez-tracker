package ca.boggleztracker.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ScenarioManager implements Iterable<Product> {
    private List<Product> products = new ArrayList<>();
    private List<Requester> requesters = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public List<Requester> getRequesters() {
        return requesters;
    }

    public boolean requesterExists(String email) {
        return true;
    }

    public void addRequester(String email, String name, int phoneNumber, String department) {
    }

    public Requester getSpecifiedRequester(Requester requester) { }
        // select the requester
        // or create new requester addRequester()

    public Product getSpecifiedProduct(Product product) {
        //
        //user input
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }
}
