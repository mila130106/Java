package repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadSafeRepository<T> {
    private List<T> items = new CopyOnWriteArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public List<T> getAll() {
        return List.copyOf(items);
    }

    public void clear() {
        items.clear();
    }

    public int size() {
        return items.size();
    }
}
