package com.example.javacodingjourney.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Given a log of website requests, where each request entry contains (time, customerId, page visited),
 * find the top 3-page sequence visited.
 * <p>
 * For example, if we have two customers, and we log CustomerA visiting page A->B->C->D->E and CustomerB visiting E->B->C->D->A,
 * then the top 3-page sequence is B->C->D.
 */
public class TopPageSequenceFinder {

    // Class to represent a log entry
    static class LogEntry {
        String timestamp;
        String customerId;
        String page;

        public LogEntry(String timestamp, String customerId, String page) {
            this.timestamp = timestamp;
            this.customerId = customerId;
            this.page = page;
        }
    }

    // Class to represent a 3-page sequence
    static class PageSequence {
        String page1;
        String page2;
        String page3;

        public PageSequence(String page1, String page2, String page3) {
            this.page1 = page1;
            this.page2 = page2;
            this.page3 = page3;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PageSequence that = (PageSequence) o;
            return Objects.equals(page1, that.page1) &&
                    Objects.equals(page2, that.page2) &&
                    Objects.equals(page3, that.page3);
        }

        @Override
        public int hashCode() {
            return Objects.hash(page1, page2, page3);
        }

        @Override
        public String toString() {
            return page1 + "->" + page2 + "->" + page3;
        }
    }

    public static String findTop3PageSequence(List<LogEntry> logs) {
        // Sort logs by timestamp
        logs.sort(Comparator.comparing(log -> log.timestamp));

        // Group visits by customer
        Map<String, List<String>> customerVisits = new HashMap<>();

        for (LogEntry log : logs) {
            customerVisits.computeIfAbsent(log.customerId, k -> new ArrayList<>())
                    .add(log.page);
        }

        // Extract all 3-page sequences
        Map<PageSequence, Integer> sequenceCount = new HashMap<>();

        for (Map.Entry<String, List<String>> entry : customerVisits.entrySet()) {
            List<String> pages = entry.getValue();

            // Generate all consecutive 3-page sequences for this customer
            for (int i = 0; i <= pages.size() - 3; i++) {
                PageSequence sequence = new PageSequence(
                        pages.get(i),
                        pages.get(i + 1),
                        pages.get(i + 2)
                );
                sequenceCount.put(sequence, sequenceCount.getOrDefault(sequence, 0) + 1);
            }
        }

        // Find the most common sequence
        if (sequenceCount.isEmpty()) {
            return "No 3-page sequences found";
        }

        PageSequence topSequence = sequenceCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        return topSequence != null ? topSequence.toString() : "No sequences found";
    }

    public static void main(String[] args) {
        // Example usage
        List<LogEntry> logs = Arrays.asList(
                new LogEntry("2024-01-01 10:00:00", "CustomerA", "A"),
                new LogEntry("2024-01-01 10:01:00", "CustomerA", "B"),
                new LogEntry("2024-01-01 10:02:00", "CustomerA", "C"),
                new LogEntry("2024-01-01 10:03:00", "CustomerA", "D"),
                new LogEntry("2024-01-01 10:04:00", "CustomerA", "E"),
                new LogEntry("2024-01-01 11:00:00", "CustomerB", "E"),
                new LogEntry("2024-01-01 11:01:00", "CustomerB", "B"),
                new LogEntry("2024-01-01 11:02:00", "CustomerB", "C"),
                new LogEntry("2024-01-01 11:03:00", "CustomerB", "D"),
                new LogEntry("2024-01-01 11:04:00", "CustomerB", "A")
        );

        String result = findTop3PageSequence(logs);
        System.out.println("Top 3-page sequence: " + result);

        // Print customer paths for verification
        System.out.println("\nCustomer paths:");
        logs.sort(Comparator.comparing(log -> log.timestamp));
        Map<String, List<String>> customerPaths = new HashMap<>();
        for (LogEntry log : logs) {
            customerPaths.computeIfAbsent(log.customerId, k -> new ArrayList<>())
                    .add(log.page);
        }
        for (Map.Entry<String, List<String>> entry : customerPaths.entrySet()) {
            System.out.println(entry.getKey() + ": " +
                    String.join("->", entry.getValue()));
        }

        // Print all sequences and their counts
        System.out.println("\nAll 3-page sequences found:");
        Map<PageSequence, Integer> allSequences = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : customerPaths.entrySet()) {
            String customerId = entry.getKey();
            List<String> pages = entry.getValue();
            for (int i = 0; i <= pages.size() - 3; i++) {
                PageSequence seq = new PageSequence(
                        pages.get(i), pages.get(i + 1), pages.get(i + 2)
                );
                System.out.println("  " + customerId + ": " + seq);
                allSequences.put(seq, allSequences.getOrDefault(seq, 0) + 1);
            }
        }

        System.out.println("\nSequence counts:");
        allSequences.entrySet().stream()
                .sorted(Map.Entry.<PageSequence, Integer>comparingByValue().reversed())
                .forEach(e -> System.out.println("  " + e.getKey() + ": " +
                        e.getValue() + " times"));
    }
}