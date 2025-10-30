package test;

import functional.IdentityExtractor;
import models.Student;
import repository.StudentRepository;
import utils.LoggerUtil;

import java.util.List;

public class RepositoryTest {
    public static void main(String[] args) {
        IdentityExtractor<Student> extractor = Student::getId;
        StudentRepository repo = new StudentRepository(extractor);

        repo.add(new Student("S1", "Alice", 20, 88.5));
        repo.add(new Student("S2", "Bob", 22, 91.0));
        repo.add(new Student("S3", "Charlie", 19, 76.0));

        List<Student> age20to22 = repo.findByAgeRange(20, 22);
        LoggerUtil.log("Test: Students aged 20-22");
        age20to22.forEach(System.out::println);

        List<Student> topStudents = repo.findByMinimumGrade(85);
        LoggerUtil.log("Test: Students with grade >= 85");
        topStudents.forEach(System.out::println);

        double total = repo.totalAverageGrades();
        LoggerUtil.log("Test: Total grades: " + total);
    }
}
