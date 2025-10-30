package model;

import java.time.LocalDate;

public class Rental {
    private Car car;
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;

    public Rental(Car car, Customer customer, LocalDate startDate, LocalDate endDate) {
        this.car = car;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Car getCar() { return car; }
    public Customer getCustomer() { return customer; }

    @Override
    public String toString() {
        return "Rental{" +
                "car=" + car.getLicensePlate() +
                ", customer=" + customer.driverLicense() +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
