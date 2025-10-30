package org.example;

import org.example.repositories.*;
import org.example.models.*;
import org.example.utils.LoggerUtil;

public class Main {
    public static void main(String[] args) {
        var logger = LoggerUtil.getLogger();

        logger.info("Application started");

        StudentRepository students = new StudentRepository();
        students.save(new Student(1, "Anna", 20));
        students.save(new Student(2, "Oleh", 22));

        DepartmentRepository departments = new DepartmentRepository();
        departments.save(new Department(1, "IT"));
        departments.save(new Department(2, "Math"));

        CourseRepository courses = new CourseRepository();
        courses.save(new Course(1, "Java Programming", 1));
        courses.save(new Course(2, "Databases", 2));

        logger.info("Repositories initialized successfully");
    }
}
