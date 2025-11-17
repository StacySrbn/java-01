package example;

import java.io.FileWriter;
import java.io.IOException;

public class ErrorLogger {
    private static final String FILE_PATH = "errors.log";

    public static void log(String message) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            System.out.println("Помилка при записі в файл логів.");
        }
    }
}

