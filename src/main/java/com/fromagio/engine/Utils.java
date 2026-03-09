package com.fromagio.engine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {

    private Utils() {
    }

    /**
     * Reads entire file content into a {@link String}.
     *
     * <p> This method loads all bytes from the specific file path and converts
     * them into a String using the platform's default character encoding.
     * Intended for small text-based resources like shader files or
     * configuration files </p>
     *
     * @param filePath relative or absolute path to a file
     * @return complete content of file as String
     * @throws RuntimeException if I/O error occurs while reading file
     */
    public static String readFile(String filePath) {
        String str;
        try {
            str = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException("Error reading file " + filePath, e);
        }

        return str;
    }
}
