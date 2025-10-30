package repository;

import functional.IdentityExtractor;
import models.Student;
import java.util.Comparator;

public class StudentRepository extends GenericRepository<Student> {
    public StudentRepository(IdentityExtractor<Student> extractor) {
        super(extractor);
    }

    public void sortByName() {
        items.sort(Comparator.comparing(Student::getName));
        LoggerUtil.log("Sorted students by name");
    }

    public void sortByAgeDesc() {
        items.sort(Comparator.comparingInt(Student::getAge).reversed());
        LoggerUtil.log("Sorted students by age descending");
    }

    public void sortByAverageGrade() {
        items.sort(Comparator.comparingDouble(Student::getAverageGrade));
        LoggerUtil.log("Sorted students by average grade ascending");
    }
}
