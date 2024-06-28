package ca.boggleztracker.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RequesterManager implements Iterable<Requester> {
    private List<Requester> requesters = new ArrayList<>();

    @Override
    public Iterator<Requester> iterator() {
        return requesters.iterator();
    }
}
