package models;

public class Student implements Comparable<Student> {
    private String id;
    private String name;
    private int age;
    private double averageGrade;

    public Student(String id, String name, int age, double averageGrade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.averageGrade = averageGrade;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getAverageGrade() { return averageGrade; }

    @Override
    public int compareTo(Student other) {
        // Сортування за ідентифікатором — основне поле
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return name + " (ID: " + id + ", age: " + age + ", avg: " + averageGrade + ")";
    }
}
