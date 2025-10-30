package main;

import functional.IdentityExtractor;
import models.*;
import repository.*;
import utils.LoggerUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        LoggerUtil.log("=== Stream API Demo ===");

        IdentityExtractor<Student> studentId = Student::getId;
        StudentRepository studentRepo = new StudentRepository(studentId);

        studentRepo.add(new Student("S1", "Alice", 20, 88.5));
        studentRepo.add(new Student("S2", "Bob", 22, 91.0));
        studentRepo.add(new Student("S3", "Charlie", 19, 76.0));
        studentRepo.add(new Student("S4", "Diana", 21, 85.0));

        List<Student> age20to22 = studentRepo.findByAgeRange(20, 22);
        LoggerUtil.log("Students aged 20-22:");
        age20to22.forEach(System.out::println);

        List<Student> topStudents = studentRepo.findByMinimumGrade(85);
        LoggerUtil.log("Students with grade >= 85:");
        topStudents.forEach(System.out::println);

        double totalGrades = studentRepo.totalAverageGrades();
        LoggerUtil.log("Total of all average grades: " + totalGrades);

        IdentityExtractor<Group> groupId = Group::getCode;
        GroupRepository groupRepo = new GroupRepository(groupId);

        groupRepo.add(new Group("G1", "CS", 2));
        groupRepo.add(new Group("G2", "Math", 3));
        groupRepo.add(new Group("G3", "CS", 1));

        List<Group> csGroups = groupRepo.findByFaculty("CS");
        LoggerUtil.log("CS groups:");
        csGroups.forEach(System.out::println);

        long start = System.nanoTime();
        studentRepo.getAll().stream().forEach(s -> s.getAverageGrade());
        long end = System.nanoTime();
        LoggerUtil.log("Stream time: " + (end - start) + " ns");

        long startP = System.nanoTime();
        studentRepo.getAll().parallelStream().forEach(s -> s.getAverageGrade());
        long endP = System.nanoTime();
        LoggerUtil.log("ParallelStream time: " + (endP - startP) + " ns");
    }
}
