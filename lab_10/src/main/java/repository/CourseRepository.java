package org.example.repositories;

import org.example.models.Course;
import java.util.*;
import java.util.stream.Collectors;

public class CourseRepository extends BaseRepository<Course> {

    public Optional<Course> findByName(String name) {
        return storage.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public List<Course> findByDepartmentId(int departmentId) {
        return storage.stream()
                .filter(c -> c.getDepartmentId() == departmentId)
                .collect(Collectors.toList());
    }

    public double averageNameLength() {
        return storage.stream()
                .map(Course::getName)
                .mapToInt(String::length)
                .average()
                .orElse(0.0);
    }

    public long countLongNamedCourses() {
        return storage.parallelStream()
                .filter(c -> c.getName().length() > 10)
                .count();
    }
}
