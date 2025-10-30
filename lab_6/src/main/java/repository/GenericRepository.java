package repository;

import functional.IdentityExtractor;
import utils.LoggerUtil;
import java.util.*;
import java.util.stream.Collectors;

public class GenericRepository<T> {
    protected List<T> items = new ArrayList<>();
    protected IdentityExtractor<T> extractor;

    public GenericRepository(IdentityExtractor<T> extractor) {
        this.extractor = extractor;
    }

    public void add(T item) {
        String id = extractor.extractId(item);
        if (findById(id).isPresent()) {
            LoggerUtil.log("Duplicate ignored: " + id);
            return;
        }
        items.add(item);
        LoggerUtil.log("Added: " + id);
    }

    public void remove(String id) {
        items.removeIf(i -> extractor.extractId(i).equals(id));
        LoggerUtil.log("Removed: " + id);
    }

    public Optional<T> findById(String id) {
        return items.stream().filter(i -> extractor.extractId(i).equals(id)).findFirst();
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    public List<T> filterBy(Predicate<T> predicate) {
        return items.stream().filter(predicate).collect(Collectors.toList());
    }

    public List<T> parallelFilterBy(Predicate<T> predicate) {
        return items.parallelStream().filter(predicate).collect(Collectors.toList());
    }
}
