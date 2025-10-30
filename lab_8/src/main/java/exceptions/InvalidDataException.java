package exceptions;

import java.util.List;

public class InvalidDataException extends Exception {
    public InvalidDataException(List<String> errors) {
        super(String.join("; ", errors));
    }
}
