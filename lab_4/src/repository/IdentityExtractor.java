package repository;

@FunctionalInterface
public interface IdentityExtractor<T> {
    String extractIdentity(T obj);
}
