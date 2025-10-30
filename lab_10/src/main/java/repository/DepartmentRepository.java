package org.example.repositories;

import org.example.models.Department;

import java.util.*;
import java.util.stream.Collectors;

public class DepartmentRepository extends BaseRepository<Department> {

    public Optional<Department> findByName(String name) {
        return storage.stream()
                .filter(d -> d.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public List<Department> findByLocation(String location) {
        return storage.stream()
                .filter(d -> d.getLocation().equalsIgnoreCase(location))
                .collect(Collectors.toList());
    }

    public List<String> getAllNames() {
        return storage.stream()
                .map(Department::getName)
                .collect(Collectors.toList());
    }

    public int totalNameLength() {
        return storage.stream()
                .map(Department::getName)
                .map(String::length)
                .reduce(0, Integer::sum);
    }

    public long countDepartmentsWithLongNames() {
        return storage.parallelStream()
                .filter(d -> d.getName().length() > 8)
                .count();
    }
}
