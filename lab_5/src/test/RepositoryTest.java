package test;

import functional.IdentityExtractor;
import models.Student;
import repository.StudentRepository;
import utils.LoggerUtil;

public class RepositoryTest {
    public static void main(String[] args) {
        IdentityExtractor<Student> extractor = Student::getId;
        StudentRepository repo = new StudentRepository(extractor);

        repo.add(new Student("S1", "Alice", 20, 88.5));
        repo.add(new Student("S3", "David", 21, 70.3));
        repo.add(new Student("S2", "Bob", 19, 92.1));

        LoggerUtil.log("Before sorting:");
        repo.getAll().forEach(System.out::println);

        repo.sortByIdentity("asc");
        LoggerUtil.log("After sorting ASC:");
        repo.getAll().forEach(System.out::println);

        repo.sortByAverageGrade();
        LoggerUtil.log("After sorting by average grade:");
        repo.getAll().forEach(System.out::println);

        LoggerUtil.log("Find S2: " + repo.findByIdentity("S2").orElse(null));
    }
}
