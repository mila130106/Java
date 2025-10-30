package model;

import java.time.LocalDate;

public record Customer(String firstName, String lastName, String driverLicense, LocalDate birthDate) {}
