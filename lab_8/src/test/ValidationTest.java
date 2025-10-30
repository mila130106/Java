package test;

import exceptions.InvalidDataException;
import models.Student;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValidationTest {

    @Test
    public void validStudentCreation() throws InvalidDataException {
        Student s = new Student("S1", "Alice", 20);
        assertEquals("Alice", s.getName());
        assertEquals(20, s.getAge());
    }

    @Test(expected = InvalidDataException.class)
    public void invalidStudentCreation() throws InvalidDataException {
        new Student("", "", -5);
    }
}
