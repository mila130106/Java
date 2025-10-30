package serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Serializer {

    public static <T> void toJson(List<T> data, String path) throws DataSerializationException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(path), data);
        } catch (IOException e) {
            throw new DataSerializationException("Error writing JSON", e);
        }
    }

    public static <T> List<T> fromJson(Class<T[]> clazz, String path) throws DataSerializationException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            T[] array = mapper.readValue(new File(path), clazz);
            return Arrays.asList(array);
        } catch (IOException e) {
            throw new DataSerializationException("Error reading JSON", e);
        }
    }

    public static <T> void toYaml(List<T> data, String path) throws DataSerializationException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            mapper.writeValue(new File(path), data);
        } catch (IOException e) {
            throw new DataSerializationException("Error writing YAML", e);
        }
    }

    public static <T> List<T> fromYaml(Class<T[]> clazz, String path) throws DataSerializationException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            T[] array = mapper.readValue(new File(path), clazz);
            return Arrays.asList(array);
        } catch (IOException e) {
            throw new DataSerializationException("Error reading YAML", e);
        }
    }
}
