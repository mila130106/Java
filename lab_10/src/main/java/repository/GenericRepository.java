package repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GenericRepository<T> {
    protected Map<Integer, T> storage = new ConcurrentHashMap<>();

    public void add(int id, T obj) {
        storage.put(id, obj);
    }

    public T getById(int id) {
        return storage.get(id);
    }

    public List<T> getAll() {
        return new ArrayList<>(storage.values());
    }

    public void update(int id, T obj) {
        storage.put(id, obj);
    }

    public void delete(int id) {
        storage.remove(id);
    }
}
