package main;

import concurrency.DataProcessor;
import concurrency.ParallelLoader;
import models.Student;
import repository.ThreadSafeRepository;
import utils.ConfigReader;
import utils.LoggerUtil;

import java.io.IOException;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        ConfigReader.load("src/main/resources/config.properties");
        String jsonPath = ConfigReader.get("jsonPath");
        String yamlPath = ConfigReader.get("yamlPath");

        CompletableFuture<ThreadSafeRepository<Student>> repoJsonFuture =
                ParallelLoader.loadFromJsonAsync(Student[].class, jsonPath);
        CompletableFuture<ThreadSafeRepository<Student>> repoYamlFuture =
                ParallelLoader.loadFromYamlAsync(Student[].class, yamlPath);

        CompletableFuture<Void> allLoaded = CompletableFuture.allOf(repoJsonFuture, repoYamlFuture);
        allLoaded.join();

        ThreadSafeRepository<Student> repoJson = repoJsonFuture.get();
        ThreadSafeRepository<Student> repoYaml = repoYamlFuture.get();

        LoggerUtil.log("JSON repo size: " + repoJson.size());
        LoggerUtil.log("YAML repo size: " + repoYaml.size());

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Integer> jsonAdultCount = executor.submit(DataProcessor.countAdultStudents(repoJson.getAll()));
        Future<Integer> yamlAdultCount = executor.submit(DataProcessor.countAdultStudents(repoYaml.getAll()));

        LoggerUtil.log("Adult students in JSON: " + jsonAdultCount.get());
        LoggerUtil.log("Adult students in YAML: " + yamlAdultCount.get());

        executor.shutdown();

        long start = System.currentTimeMillis();
        long count = repoJson.getAll().parallelStream().filter(s -> s.getAge() >= 18).count();
        long end = System.currentTimeMillis();
        LoggerUtil.log("ParallelStream adult count: " + count + " (took " + (end - start) + "ms)");
    }
}
