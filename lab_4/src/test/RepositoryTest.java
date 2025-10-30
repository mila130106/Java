package test;

import model.Car;
import model.enums.CarStatus;
import org.junit.jupiter.api.*;
import repository.GenericRepository;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    private GenericRepository<Car> repo;

    @BeforeEach
    void setUp() {
        repo = new GenericRepository<>(Car::getLicensePlate);
    }

    @Test
    void testAddAndFind() {
        Car car = new Car("BB4321CC", "Honda Civic", 2021, 20000, CarStatus.AVAILABLE);
        repo.add(car);

        assertTrue(repo.findByIdentity("BB4321CC").isPresent(),
                "Car should be found by license plate");
    }

    @Test
    void testRemove() {
        Car car = new Car("BB4321CC", "Honda Civic", 2021, 20000, CarStatus.AVAILABLE);
        repo.add(car);
        repo.remove("BB4321CC");

        assertFalse(repo.findByIdentity("BB4321CC").isPresent(),
                "Car should be removed from repository");
    }

    @Test
    void testDuplicateWarning() {
        Car car1 = new Car("AA1111BB", "Mazda 3", 2022, 10000, CarStatus.AVAILABLE);
        Car car2 = new Car("AA1111BB", "Mazda CX-5", 2023, 5000, CarStatus.RENTED);

        repo.add(car1);
        repo.add(car2); // дубль — має згенерувати warning у логах

        assertEquals(1, repo.getAll().size(),
                "Repository should contain only one car with the same identity");
    }

    @Test
    void testGetAll() {
        repo.add(new Car("AA1111AA", "BMW X5", 2019, 70000, CarStatus.AVAILABLE));
        repo.add(new Car("BB2222BB", "Audi A6", 2020, 50000, CarStatus.MAINTENANCE));

        assertEquals(2, repo.getAll().size(),
                "Repository should return all added cars");
    }
}
