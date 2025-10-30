package main;

import exceptions.InvalidDataException;
import models.Group;
import models.Student;
import repository.GenericRepository;
import utils.LoggerUtil;

public class Main {
    public static void main(String[] args) {
        GenericRepository<Student> studentRepo = new GenericRepository<>();
        GenericRepository<Group> groupRepo = new GenericRepository<>();

        try {
            Student s1 = new Student("S1", "Alice", 20);
            studentRepo.add(s1);

            Student s2 = new Student("", "Bob", -5); // викличе виняток
            studentRepo.add(s2);
        } catch (InvalidDataException e) {
            LoggerUtil.log("Error creating student: " + e.getMessage());
        }

        try {
            Group g1 = new Group("G1", "CS", 2);
            groupRepo.add(g1);

            Group g2 = new Group("", "", -1); // викличе виняток
            groupRepo.add(g2);
        } catch (InvalidDataException e) {
            LoggerUtil.log("Error creating group: " + e.getMessage());
        }

        LoggerUtil.log("Final students in repo:");
        studentRepo.getAll().forEach(System.out::println);

        LoggerUtil.log("Final groups in repo:");
        groupRepo.getAll().forEach(System.out::println);
    }
}
