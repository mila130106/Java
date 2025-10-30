package model;

import model.enums.CarStatus;

public class Car {
    private String licensePlate;
    private String model;
    private int year;
    private double mileage;
    private CarStatus status;

    public Car(String licensePlate, String model, int year, double mileage, CarStatus status) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.status = status;
    }

    public String getLicensePlate() { return licensePlate; }

    @Override
    public String toString() {
        return "Car{" +
                "licensePlate='" + licensePlate + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", mileage=" + mileage +
                ", status=" + status +
                '}';
    }
}
