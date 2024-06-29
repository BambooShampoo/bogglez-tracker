package ca.boggleztracker.model;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ScenarioManager implements Iterable<Product> {
    private List<Product> products = new ArrayList<>();
    private List<Requester> requesters = new ArrayList<>();
    private RandomAccessFile file;

    public ScenarioManager(String filePath) {}

    public void populateFromFile() {}

    public List<Product> getProducts() {
        return new ArrayList<Product>();
    }

    public List<Requester> getRequesters() {
        return new ArrayList<Requester>();
    }

    public boolean requesterExists(String email) {
        return true;
    }

    public void addRequester(String email, String name, int phoneNumber, String department) {}

    public void addProduct(String productName) {}

    public Requester getSpecifiedRequester(Requester requester) {
        return new Requester("", "", 1, "");
    }

    public Product getSpecifiedProduct(Product product) {
        return new Product("");
    }

    public void closeFile() {}

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }
}
