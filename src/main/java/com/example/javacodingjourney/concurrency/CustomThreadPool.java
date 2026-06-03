package com.example.javacodingjourney.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Custom ThreadPool built from scratch using ThreadPoolExecutor.
 * Demonstrates: custom thread factory, rejection handler, bounded queue, and monitoring.
 */
public class CustomThreadPool {

    // Custom ThreadFactory — gives threads meaningful names + daemon flag
    static class NamedThreadFactory implements ThreadFactory {
        private final AtomicInteger counter = new AtomicInteger(1);
        private final String prefix;

        NamedThreadFactory(String prefix) {
            this.prefix = prefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, prefix + "-" + counter.getAndIncrement());
            t.setDaemon(false);
            return t;
        }
    }

    // Custom RejectedExecutionHandler — logs and optionally retries
    static class LoggingRejectionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.err.println("[REJECTED] Task " + r + " rejected. " +
                    "Pool size: " + executor.getPoolSize() +
                    ", Active: " + executor.getActiveCount() +
                    ", Queue size: " + executor.getQueue().size());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Build a custom pool:
        //   - 2 core threads (always alive)
        //   - 4 max threads (scales up under load)
        //   - 30s keep-alive for idle non-core threads
        //   - Bounded queue of 4 (backpressure!)
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2,                                  // corePoolSize
                4,                                  // maximumPoolSize
                30, TimeUnit.SECONDS,               // keepAliveTime
                new ArrayBlockingQueue<>(4),        // bounded work queue
                new NamedThreadFactory("worker"),   // custom thread factory
                new LoggingRejectionHandler()       // custom rejection handler
        );

        // Allow core threads to time out too (optional)
        pool.allowCoreThreadTimeOut(true);

        // Submit 10 tasks — with pool max 4 + queue 4 = can hold 8, last 2 get rejected
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            pool.execute(() -> {
                System.out.println("[START] Task-" + taskId + " on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("[DONE]  Task-" + taskId + " on " + Thread.currentThread().getName());
            });
        }

        // Monitor the pool
        System.out.println("\n--- Pool Stats ---");
        System.out.println("Pool size: " + pool.getPoolSize());
        System.out.println("Active threads: " + pool.getActiveCount());
        System.out.println("Queued tasks: " + pool.getQueue().size());
        System.out.println("Completed tasks: " + pool.getCompletedTaskCount());

        // Graceful shutdown
        pool.shutdown();
        if (!pool.awaitTermination(10, TimeUnit.SECONDS)) {
            pool.shutdownNow();
        }

        System.out.println("\n--- Final Stats ---");
        System.out.println("Total completed: " + pool.getCompletedTaskCount());
    }
}
