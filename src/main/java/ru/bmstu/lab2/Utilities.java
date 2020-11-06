package ru.bmstu.lab2;

public class Utilities {
    private static final String QUOTE_PATTERN = "\"";
    private static final String COMMA_SEPARATOR = ",";

    public String[] separate(String value, int limit){
        return value.toString().replace(QUOTE_PATTERN, "").split(COMMA_SEPARATOR, limit);
    }
}
