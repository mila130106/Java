package repository;

import functional.IdentityExtractor;
import utils.LoggerUtil;
import java.util.*;
import java.util.stream.Collectors;

public class GenericRepository<T extends Comparable<T>> {
    protected List<T> items = new ArrayList<>();
    protected IdentityExtractor<T> extractor;

    public GenericRepository(IdentityExtractor<T> extractor) {
        this.extractor = extractor;
    }

    public void add(T item) {
        String id = extractor.extractId(item);
        if (findByIdentity(id).isPresent()) {
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

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    public Optional<T> findByIdentity(String id) {
        return items.stream().filter(i -> extractor.extractId(i).equals(id)).findFirst();
    }

    public void sortByIdentity(String order) {
        Comparator<T> comparator = Comparator.comparing(extractor::extractId);
        if ("desc".equalsIgnoreCase(order)) comparator = comparator.reversed();
        items = items.stream().sorted(comparator).collect(Collectors.toList());
        LoggerUtil.log("Sorted by identity (" + order + ")");
    }
}
