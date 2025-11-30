package com.example.javacodingjourney.concurrency;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DateFormatterExample {
    // SimpleDateFormat is not thread-safe, so we use ThreadLocal
    private static ThreadLocal<SimpleDateFormat> dateFormatter =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static String formatDate(Date date) {
        return dateFormatter.get().format(date);
    }

    public static Date parseDate(String dateStr) throws ParseException {
        return dateFormatter.get().parse(dateStr);
    }

    public static void main(String[] args) {
        try (ExecutorService executor = Executors.newFixedThreadPool(5)) {

            for (int i = 0; i < 10; i++) {
                executor.submit(() -> {
                    String threadName = Thread.currentThread().getName();
                    Date now = new Date();

                    // Each thread safely uses its own formatter
                    String formatted = formatDate(now);
                    System.out.println(threadName + " formatted: " + formatted);

                    try {
                        Date parsed = parseDate(formatted);
                        System.out.println(threadName + " parsed back: " + parsed);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });
            }

            executor.shutdown();
        }
    }
}