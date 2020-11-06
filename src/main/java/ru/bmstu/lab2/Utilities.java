package ru.bmstu.lab2;

import org.apache.hadoop.io.Text;

public final class Utilities {
    private static final String QUOTE_PATTERN = "\"";
    private static final String COMMA_SEPARATOR = ",";

    public static String[] separate(Text value, int limit){
        return value.toString().replace(QUOTE_PATTERN, "").split(COMMA_SEPARATOR, limit);
    }
}
