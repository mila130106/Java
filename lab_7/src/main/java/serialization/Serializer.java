package serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import utils.LoggerUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Serializer {

    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static final ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());

    public static <T> void toJson(List<T> items, String path) throws DataSerializationException {
        try {
            jsonMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), items);
            LoggerUtil.log("Saved JSON to " + path);
        } catch (IOException e) {
            throw new DataSerializationException("Error saving JSON", e);
        }
    }

    public static <T> List<T> fromJson(Class<T[]> clazz, String path) throws DataSerializationException {
        try {
            T[] array = jsonMapper.readValue(new File(path), clazz);
            LoggerUtil.log("Loaded JSON from " + path);
            return List.of(array);
        } catch (IOException e) {
            throw new DataSerializationException("Error loading JSON", e);
        }
    }

    public static <T> void toYaml(List<T> items, String path) throws DataSerializationException {
        try {
            yamlMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), items);
            LoggerUtil.log("Saved YAML to " + path);
        } catch (IOException e) {
            throw new DataSerializationException("Error saving YAML", e);
        }
    }

    public static <T> List<T> fromYaml(Class<T[]> clazz, String path) throws DataSerializationException {
        try {
            T[] array = yamlMapper.readValue(new File(path), clazz);
            LoggerUtil.log("Loaded YAML from " + path);
            return List.of(array);
        } catch (IOException e) {
            throw new DataSerializationException("Error loading YAML", e);
        }
    }
}
