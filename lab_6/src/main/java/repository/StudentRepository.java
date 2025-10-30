package repository;

import functional.IdentityExtractor;
import models.Student;
import utils.LoggerUtil;

import java.util.List;
import java.util.stream.Collectors;

public class StudentRepository extends GenericRepository<Student> {
    public StudentRepository(IdentityExtractor<Student> extractor) {
        super(extractor);
    }

    public List<Student> findByAgeRange(int min, int max) {
        LoggerUtil.log("Finding students with age between " + min + " and " + max);
        return items.stream()
                .filter(s -> s.getAge() >= min && s.getAge() <= max)
                .collect(Collectors.toList());
    }

    public List<Student> findByMinimumGrade(double grade) {
        LoggerUtil.log("Finding students with averageGrade >= " + grade);
        return items.stream()
                .filter(s -> s.getAverageGrade() >= grade)
                .collect(Collectors.toList());
    }

    public double totalAverageGrades() {
        LoggerUtil.log("Calculating total of average grades");
        return items.stream()
                .map(Student::getAverageGrade)
                .reduce(0.0, Double::sum);
    }
}
