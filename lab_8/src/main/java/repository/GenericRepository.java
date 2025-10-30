package repository;

import java.util.ArrayList;
import java.util.List;

public class GenericRepository<T> {
    protected List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    public void clear() {
        items.clear();
    }
}
