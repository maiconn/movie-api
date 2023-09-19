package com.example.movieapi.helper;

import org.springframework.web.multipart.MultipartFile;

public class CSVHelper {
    public static String TYPE = "text/csv";
    public static String TRUE = "YES";
    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static boolean retrieveBoolean(String[] array, int position) {
        if(array.length > position) {
            return TRUE.equalsIgnoreCase(array[position]);
        }
        return false;
    }
}
