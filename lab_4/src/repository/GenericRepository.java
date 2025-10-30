package repository;

import java.util.*;
import java.util.logging.Logger;

public class GenericRepository<T> {
    private final Map<String, T> storage = new HashMap<>();
    private final IdentityExtractor<T> extractor;
    private static final Logger logger = Logger.getLogger(GenericRepository.class.getName());

    public GenericRepository(IdentityExtractor<T> extractor) {
        this.extractor = extractor;
    }

    public void add(T obj) {
        String id = extractor.extractIdentity(obj);
        if (storage.containsKey(id)) {
            logger.warning("Duplicate identity: " + id);
        } else {
            storage.put(id, obj);
            logger.info("Added: " + obj);
        }
    }

    public void remove(String id) {
        storage.remove(id);
        logger.info("Removed object with ID: " + id);
    }

    public List<T> getAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<T> findByIdentity(String id) {
        return Optional.ofNullable(storage.get(id));
    }
}
