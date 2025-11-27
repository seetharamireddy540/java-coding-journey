package com.example.javacodingjourney.gita;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.List;

public class ThreadApp {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");

        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getId());

        getMemoryInfo();
        Thread.currentThread().join();
    }

    public static void getMemoryInfo() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

        // Heap Memory
        MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
        System.out.println("HEAP MEMORY:");
        System.out.println("  Initial: " + formatBytes(heapUsage.getInit()));
        System.out.println("  Used: " + formatBytes(heapUsage.getUsed()));
        System.out.println("  Committed: " + formatBytes(heapUsage.getCommitted()));
        System.out.println("  Max: " + formatBytes(heapUsage.getMax()));
        System.out.println("  Usage: " +
                String.format("%.2f%%", (heapUsage.getUsed() * 100.0 / heapUsage.getMax())));

        // Non-Heap Memory (Metaspace, etc.)
        MemoryUsage nonHeapUsage = memoryBean.getNonHeapMemoryUsage();
        System.out.println("\nNON-HEAP MEMORY:");
        System.out.println("  Initial: " + formatBytes(nonHeapUsage.getInit()));
        System.out.println("  Used: " + formatBytes(nonHeapUsage.getUsed()));
        System.out.println("  Committed: " + formatBytes(nonHeapUsage.getCommitted()));
        System.out.println("  Max: " + formatBytes(nonHeapUsage.getMax()));

        // Memory Pools (Eden, Survivor, Old Gen, etc.)
        System.out.println("\nMEMORY POOLS:");
        List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean pool : pools) {
            MemoryUsage usage = pool.getUsage();
            System.out.println("  " + pool.getName() + ":");
            System.out.println("    Type: " + pool.getType());
            System.out.println("    Used: " + formatBytes(usage.getUsed()));
            System.out.println("    Max: " + formatBytes(usage.getMax()));
        }

        // Runtime memory info
        Runtime runtime = Runtime.getRuntime();
        System.out.println("\nRUNTIME MEMORY:");
        System.out.println("  Free Memory: " + formatBytes(runtime.freeMemory()));
        System.out.println("  Total Memory: " + formatBytes(runtime.totalMemory()));
        System.out.println("  Max Memory: " + formatBytes(runtime.maxMemory()));
    }

    // Helper method to format bytes
    private static String formatBytes(long bytes) {
        if (bytes < 0) return "N/A";
        String[] units = {"B", "KB", "MB", "GB", "TB"};
        int unit = 0;
        double value = bytes;
        while (value >= 1024 && unit < units.length - 1) {
            value /= 1024;
            unit++;
        }
        return String.format("%.2f %s", value, units[unit]);
    }
}
