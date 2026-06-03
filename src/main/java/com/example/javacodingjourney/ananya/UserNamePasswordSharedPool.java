package com.example.javacodingjourney.ananya;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Option B — shared pool + per-user task queue.
 *
 * One bounded thread pool of N workers shared across all users. For each user
 * we keep a FIFO queue of pending tasks and an "in-flight" flag. Only ONE task
 * per user ever runs on the pool at a time — when it finishes, it kicks off
 * the next task from that user's queue.
 *
 * Why this is better than a single per-user lock:
 * - When 100 ram tasks arrive, only ONE ram task is sitting on a worker thread.
 * The other 99 are in ram's per-user queue, NOT blocking pool threads.
 * - gita's task therefore finds free workers immediately. No head-of-line
 * blocking even if ram floods the system.
 * - Thread count stays bounded regardless of user count.
 */
public class UserNamePasswordSharedPool {

    private static final int POOL_SIZE = 4;

    private final ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE, r -> {
        Thread t = new Thread(r, "shared-worker");
        t.setDaemon(true);
        return t;
    });

    // Per-user state. Guard the Deque + inFlight flag together via the UserQueue's
    // monitor.
    private final Map<String, UserQueue> userQueues = new ConcurrentHashMap<>();
    private final AtomicInteger ramDone = new AtomicInteger();
    private final AtomicInteger gitaDone = new AtomicInteger();

    private static final class UserQueue {
        final Deque<Runnable> tasks = new ArrayDeque<>();
        final ReentrantLock lock = new ReentrantLock();
        boolean inFlight = false;
    }

    public static void main(String[] args) throws InterruptedException {
        UserNamePasswordSharedPool app = new UserNamePasswordSharedPool();
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            app.submit("ram", "dummy=1", start);
        }
        app.submit("gita", "dummy=1", start);

        Thread.sleep(25_000);
        app.shutdown();
        System.out.println("ram done=" + app.ramDone.get() + ", gita done=" + app.gitaDone.get());
    }

    public void submit(String userName, String password, long startMillis) {
        UserQueue uq = userQueues.computeIfAbsent(userName, k -> new UserQueue());
        Runnable task = () -> validate(userName, password, startMillis);

        Runnable toRun = null;
        ReentrantLock lock = uq.lock;
        lock.lock();
        try {
            if (uq.inFlight) {
                uq.tasks.addLast(task);
            } else {
                uq.inFlight = true;
                toRun = task;
            }
            if (toRun != null) {
                scheduleOnPool(uq, toRun);
            }
        } finally {
            lock.unlock();
        }

    }

    private void scheduleOnPool(UserQueue uq, Runnable task) {
        pool.execute(() -> {
            try {
                task.run();
            } finally {
                Runnable next;
                uq.lock.lock();
                try {
                    next = uq.tasks.pollFirst();
                    if (next == null) {
                        uq.inFlight = false;
                    }
                } finally {
                    uq.lock.unlock();
                }
                if (next != null) {
                    scheduleOnPool(uq, next);
                }

            }
        });
    }

    private void validate(String userName, String password, long startMillis) {
        long elapsed = System.currentTimeMillis() - startMillis;
        System.out.printf("[t+%5dms] START  user=%s thread=%s%n",
                elapsed, userName, Thread.currentThread().getName());
        try {
            Thread.sleep(2000L); // mimic compute time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        long doneAt = System.currentTimeMillis() - startMillis;
        System.out.printf("[t+%5dms] DONE   user=%s thread=%s%n",
                doneAt, userName, Thread.currentThread().getName());
        if ("ram".equals(userName))
            ramDone.incrementAndGet();
        else if ("gita".equals(userName))
            gitaDone.incrementAndGet();
    }

    public void shutdown() {
        pool.shutdownNow();
    }
}
