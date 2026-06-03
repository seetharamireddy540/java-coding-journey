package com.example.javacodingjourney.ananya;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Option A — per-user single-thread executor.
 *
 * Each username gets its OWN single-thread executor (created lazily).
 * - Same user's requests are naturally serialized (one thread, FIFO queue).
 * - Different users run on different threads → no head-of-line blocking.
 * - No explicit lock needed.
 *
 * Trade-off: one OS thread per active user. Fine when active users are bounded
 * and small; bad if you can have tens of thousands of users.
 */
public class UserNamePasswordPerUserExecutor {

    private final Map<String, ExecutorService> userExecutors = new ConcurrentHashMap<>();
    private final AtomicInteger ramDone = new AtomicInteger();
    private final AtomicInteger gitaDone = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        UserNamePasswordPerUserExecutor app = new UserNamePasswordPerUserExecutor();
        long start = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {
            app.submit("ram", "dummy=1", start);
        }
        app.submit("gita", "dummy=1", start);

        Thread.sleep(12_000);
        app.shutdown();
        System.out.println("ram done=" + app.ramDone.get() + ", gita done=" + app.gitaDone.get());
    }

    public void submit(String userName, String password, long startMillis) {
        ExecutorService exec = userExecutors.computeIfAbsent(
                userName,
                k -> Executors.newSingleThreadExecutor(r -> {
                    Thread t = new Thread(r, "user-" + k);
                    t.setDaemon(true);
                    return t;
                }));
        exec.execute(() -> validate(userName, password, startMillis));
    }

    private void validate(String userName, String password, long startMillis) {
        long elapsed = System.currentTimeMillis() - startMillis;
        System.out.printf("[t+%4dms] START  user=%s thread=%s%n",
                elapsed, userName, Thread.currentThread().getName());
        try {
            Thread.sleep(2000L); // mimic compute time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        long doneAt = System.currentTimeMillis() - startMillis;
        System.out.printf("[t+%4dms] DONE   user=%s thread=%s%n",
                doneAt, userName, Thread.currentThread().getName());
        if ("ram".equals(userName)) ramDone.incrementAndGet();
        else if ("gita".equals(userName)) gitaDone.incrementAndGet();
    }

    public void shutdown() {
        userExecutors.values().forEach(ExecutorService::shutdownNow);
    }
}
