package com.example.javacodingjourney.timeandspace;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ConstantTime {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("key-1", "value");
        map.compute("key-2", (k, v) -> {
            if (v != null) {
                return v.concat("-new");
            } else {
                return "new-value";
            }
        });
        map.computeIfAbsent("key-2", key -> {
            return key.toUpperCase(Locale.ROOT);
        });
        map.computeIfPresent("key-1", (k, v) -> {
            return v.concat("-modified");
        });
        // Operation takes the same time regardless of input size.
        System.out.println(map.get("key-2"));
        System.out.println(map.get("key-1"));
    }
}
