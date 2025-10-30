package models;

import utils.LoggerUtil;

public class Student {
    private String id;
    private String name;
    private int age;

    public Student(String id, String name, int age) {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("id cannot be empty");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("name cannot be empty");
        if (age <= 0) throw new IllegalArgumentException("age must be > 0");

        this.id = id;
        this.name = name;
        this.age = age;
        LoggerUtil.log("Student created: " + this);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return name + " (ID: " + id + ", age: " + age + ")";
    }
}
