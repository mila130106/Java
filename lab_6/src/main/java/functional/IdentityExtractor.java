package functional;

@FunctionalInterface
public interface IdentityExtractor<T> {
    String extractId(T entity);
}
