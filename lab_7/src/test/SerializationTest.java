package test;

import models.Student;
import org.junit.Test;
import repository.GenericRepository;
import serialization.DataSerializationException;
import serialization.Serializer;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SerializationTest {

    @Test
    public void testJsonSerialization() throws DataSerializationException {
        GenericRepository<Student> repo = new GenericRepository<>();
        repo.add(new Student("S1", "Alice", 20));
        repo.add(new Student("S2", "Bob", 22));

        String path = "test_students.json";
        Serializer.toJson(repo.getAll(), path);
        List<Student> loaded = Serializer.fromJson(Student[].class, path);
        assertEquals(repo.getAll().size(), loaded.size());
    }

    @Test
    public void testYamlSerialization() throws DataSerializationException {
        GenericRepository<Student> repo = new GenericRepository<>();
        repo.add(new Student("S1", "Alice", 20));
        repo.add(new Student("S2", "Bob", 22));

        String path = "test_students.yaml";
        Serializer.toYaml(repo.getAll(), path);
        List<Student> loaded = Serializer.fromYaml(Student[].class, path);
        assertEquals(repo.getAll().size(), loaded.size());
    }
}
