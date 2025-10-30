package models;

import exceptions.InvalidDataException;
import utils.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String id;
    private String name;
    private int age;

    public Student(String id, String name, int age) throws InvalidDataException {
        List<String> errors = new ArrayList<>();
        if (id == null || id.isEmpty()) errors.add("id: cannot be empty");
        if (name == null || name.isEmpty()) errors.add("name: cannot be empty");
        if (age <= 0) errors.add("age: must be > 0");

        if (!errors.isEmpty()) {
            LoggerUtil.log("Validation failed: " + String.join("; ", errors));
            throw new InvalidDataException(errors);
        }

        this.id = id;
        this.name = name;
        this.age = age;
        LoggerUtil.log("Student created successfully: " + this);
    }

    public String getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) throws InvalidDataException {
        if (name == null || name.isEmpty()) {
            LoggerUtil.log("Invalid name update attempt");
            throw new InvalidDataException(List.of("name: cannot be empty"));
        }
        this.name = name;
        LoggerUtil.log("Name updated: " + name);
    }

    public int getAge() { return age; }
    public void setAge(int age) throws InvalidDataException {
        if (age <= 0) {
            LoggerUtil.log("Invalid age update attempt");
            throw new InvalidDataException(List.of("age: must be > 0"));
        }
        this.age = age;
        LoggerUtil.log("Age updated: " + age);
    }

    @Override
    public String toString() {
        return name + " (ID: " + id + ", age: " + age + ")";
    }
}
