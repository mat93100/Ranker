package org.mat93100.util;

public class various {
    public static String emptyOrNA(String value) {
        return (value == null || value.trim().isEmpty()) ? "N/A" : value;
    }

    public static String listOrNA(java.util.List<String> list) {
        return (list == null || list.isEmpty()) ? "N/A" : String.join(", ", list);
    }
}
