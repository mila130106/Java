package utils;

import java.time.LocalTime;

public class LoggerUtil {
    public static void log(String msg) {
        System.out.println("[" + LocalTime.now() + "] " + msg);
    }
}
