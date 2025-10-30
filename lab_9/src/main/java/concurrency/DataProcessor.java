package concurrency;

import models.Student;
import utils.LoggerUtil;

import java.util.List;
import java.util.concurrent.Callable;

public class DataProcessor {

    public static Callable<Integer> countAdultStudents(List<Student> students) {
        return () -> {
            int count = (int) students.parallelStream().filter(s -> s.getAge() >= 18).count();
            LoggerUtil.log("Counted " + count + " adult students in thread " + Thread.currentThread().getName());
            return count;
        };
    }
}
