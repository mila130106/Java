import model.*;
import model.enums.*;
import repository.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        GenericRepository<Car> carRepo = new GenericRepository<>(Car::getLicensePlate);
        GenericRepository<Customer> customerRepo = new GenericRepository<>(Customer::driverLicense);
        GenericRepository<Branch> branchRepo = new GenericRepository<>(Branch::name);

        Car car1 = new Car("AA1234BC", "Toyota Camry", 2020, 45000, CarStatus.AVAILABLE);
        Car car2 = new Car("AA1234BC", "Toyota Corolla", 2019, 60000, CarStatus.RENTED); // дубль

        carRepo.add(car1);
        carRepo.add(car2); // покаже попередження

        Customer customer1 = new Customer("Ivan", "Petrenko", "DL12345", LocalDate.of(1995, 5, 10));
        customerRepo.add(customer1);

        Branch branch = new Branch("Central Branch", "Kyiv");
        branchRepo.add(branch);

        System.out.println("\n--- Усі машини ---");
        carRepo.getAll().forEach(System.out::println);

        System.out.println("\n--- Пошук машини за номером ---");
        carRepo.findByIdentity("AA1234BC").ifPresent(System.out::println);
    }
}
