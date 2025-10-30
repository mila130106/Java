package main;

import functional.IdentityExtractor;
import models.*;
import repository.*;
import utils.LoggerUtil;

public class Main {
    public static void main(String[] args) {
        LoggerUtil.log("=== Demo start ===");

        IdentityExtractor<Student> studentId = Student::getId;
        StudentRepository studentRepo = new StudentRepository(studentId);

        studentRepo.add(new Student("S01", "Anna", 19, 91.5));
        studentRepo.add(new Student("S03", "John", 22, 76.0));
        studentRepo.add(new Student("S02", "Maria", 20, 85.4));
        studentRepo.add(new Student("S01", "Anna", 19, 91.5)); // дубль

        LoggerUtil.log("Before sort:");
        studentRepo.getAll().forEach(System.out::println);

        studentRepo.sortByIdentity("asc");
        studentRepo.sortByAverageGrade();
        studentRepo.sortByAgeDesc();

        IdentityExtractor<Group> groupId = Group::getCode;
        GroupRepository groupRepo = new GroupRepository(groupId);

        groupRepo.add(new Group("G01", "CS", 2));
        groupRepo.add(new Group("G02", "Math", 3));
        groupRepo.add(new Group("G03", "Physics", 1));

        groupRepo.sortByYearDesc();

        LoggerUtil.log("Find student by ID 'S02': " + studentRepo.findByIdentity("S02").orElse(null));
    }
}
