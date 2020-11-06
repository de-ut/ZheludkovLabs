package ru.bmstu.lab3;

import org.apache.hadoop.io.Text;

public final class Utilities {
    private static final String QUOTE_PATTERN = "\"";
    private static final String COMMA_SEPARATOR = ",";

    public static String[] separate(String value, int limit){
        return value.replace(QUOTE_PATTERN, "").split(COMMA_SEPARATOR, limit);
    }
}
