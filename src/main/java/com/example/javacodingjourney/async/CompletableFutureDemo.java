package com.example.javacodingjourney.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo {
    public static void main(String[] args) {

        // Basic Async Operation
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // Simulating a time-consuming task
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Hello, Async World!";
        });
        // Chaining Operations
        CompletableFuture<String> processedFuture = future
                .thenApply(result -> result.toUpperCase())  // Transform result
                .thenApply(upperResult -> "Processed: " + upperResult)  // Further processing
                .exceptionally(ex -> "Error occurred: " + ex.getMessage());  // Error handling

        // Wait and print result
        processedFuture.thenAccept(System.out::println).join();
    }
}
