package test;

import models.Student;
import org.junit.Test;
import repository.ThreadSafeRepository;

import java.util.concurrent.*;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class ConcurrencyTest {

    @Test
    public void testParallelAdd() throws InterruptedException {
        ThreadSafeRepository<Student> repo = new ThreadSafeRepository<>();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        IntStream.range(0, 100).forEach(i ->
                executor.submit(() -> repo.add(new Student("S" + i, "Student" + i, 18 + i % 5)))
        );

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        assertEquals(100, repo.size());
    }

    @Test
    public void testParallelProcessing() throws InterruptedException, ExecutionException {
        ThreadSafeRepository<Student> repo = new ThreadSafeRepository<>();
        IntStream.range(0, 10).forEach(i -> repo.add(new Student("S" + i, "Student" + i, 18 + i)));

        Callable<Long> adultCount = () -> repo.getAll().parallelStream().filter(s -> s.getAge() >= 18).count();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Long> future = executor.submit(adultCount);
        Long count = future.get();

        assertEquals(Long.valueOf(10), count);
        executor.shutdown();
    }
}
