package serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Serializer {
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static <T> void writeToFile(String path, List<T> data) throws IOException {
        mapper.writeValue(new File(path), data);
    }

    public static <T> List<T> readFromFile(String path, Class<T> clazz) throws IOException {
        return mapper.readValue(new File(path),
                mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }
}
