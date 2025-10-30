package main;

import models.Student;
import repository.GenericRepository;
import serialization.DataSerializationException;
import serialization.Serializer;
import utils.ConfigReader;
import utils.LoggerUtil;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        try {
            ConfigReader.load("src/main/resources/config.properties");
            String jsonPath = ConfigReader.get("jsonPath");
            String yamlPath = ConfigReader.get("yamlPath");
            int numberOfStudents = ConfigReader.getInt("numberOfStudents");

            // Створюємо репозиторій та заповнюємо тестовими даними
            GenericRepository<Student> studentRepo = new GenericRepository<>();
            IntStream.range(0, numberOfStudents).forEach(i ->
                    studentRepo.add(new Student("S" + i, "Student" + i, 18 + i))
            );

            LoggerUtil.log("Initial repository:");
            studentRepo.getAll().forEach(System.out::println);

            Serializer.toJson(studentRepo.getAll(), jsonPath);
            Serializer.toYaml(studentRepo.getAll(), yamlPath);

            List<Student> fromJson = Serializer.fromJson(Student[].class, jsonPath);
            List<Student> fromYaml = Serializer.fromYaml(Student[].class, yamlPath);

            LoggerUtil.log("Repository loaded from JSON:");
            fromJson.forEach(System.out::println);

            LoggerUtil.log("Repository loaded from YAML:");
            fromYaml.forEach(System.out::println);

        } catch (IOException e) {
            LoggerUtil.log("Config file error: " + e.getMessage());
        } catch (DataSerializationException e) {
            LoggerUtil.log("Serialization error: " + e.getMessage());
        }
    }
}
