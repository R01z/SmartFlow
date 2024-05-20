package com.smartflow.smartflow.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class FileNameSanitizer {
    public static String sanitizeFileName(String fileName) {
        // Normalize the file name
        String normalized = Normalizer.normalize(fileName, Normalizer.Form.NFD);
        // Remove special characters
        Pattern pattern = Pattern.compile("[^\\p{ASCII}]");
        return pattern.matcher(normalized).replaceAll("");
    }
}