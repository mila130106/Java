package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {
    @JsonProperty
    private String id;
    @JsonProperty
    private String name;
    @JsonProperty
    private int age;

    public Student() {} // потрібен для Jackson

    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return name + " (ID: " + id + ", age: " + age + ")";
    }
}
