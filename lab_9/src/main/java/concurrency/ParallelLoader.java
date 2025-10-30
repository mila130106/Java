package concurrency;

import repository.ThreadSafeRepository;
import serialization.Serializer;
import serialization.DataSerializationException;
import utils.LoggerUtil;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ParallelLoader {

    public static <T> CompletableFuture<ThreadSafeRepository<T>> loadFromJsonAsync(Class<T[]> clazz, String path) {
        return CompletableFuture.supplyAsync(() -> {
            ThreadSafeRepository<T> repo = new ThreadSafeRepository<>();
            try {
                List<T> items = Serializer.fromJson(clazz, path);
                items.forEach(repo::add);
                LoggerUtil.log("Loaded " + items.size() + " items from JSON in thread " + Thread.currentThread().getName());
            } catch (DataSerializationException e) {
                LoggerUtil.log("Error loading JSON: " + e.getMessage());
            }
            return repo;
        });
    }

    public static <T> CompletableFuture<ThreadSafeRepository<T>> loadFromYamlAsync(Class<T[]> clazz, String path) {
        return CompletableFuture.supplyAsync(() -> {
            ThreadSafeRepository<T> repo = new ThreadSafeRepository<>();
            try {
                List<T> items = Serializer.fromYaml(clazz, path);
                items.forEach(repo::add);
                LoggerUtil.log("Loaded " + items.size() + " items from YAML in thread " + Thread.currentThread().getName());
            } catch (DataSerializationException e) {
                LoggerUtil.log("Error loading YAML: " + e.getMessage());
            }
            return repo;
        });
    }
}
