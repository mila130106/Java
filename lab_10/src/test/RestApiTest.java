package org.example;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.net.*;
import static org.junit.jupiter.api.Assertions.*;

public class RestApiTest {

    @Test
    void testStudentGetRequest() throws IOException {
        URL url = new URL("http://localhost:8080/students");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int status = con.getResponseCode();
        assertTrue(status == 200 || status == 404);
        con.disconnect();
    }
}
