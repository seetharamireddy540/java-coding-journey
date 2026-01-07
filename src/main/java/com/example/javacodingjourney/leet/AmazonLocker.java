package com.example.javacodingjourney.leet;

import java.util.*;

public class AmazonLocker {
    private int citySize;
    private Set<Point> lockers;
    private int[][] distances;

    // Helper class to represent coordinates
    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    // Constructor
    public AmazonLocker(int citySize, List<int[]> lockerLocations) {
        this.citySize = citySize;
        this.lockers = new HashSet<>();
        this.distances = new int[citySize][citySize];

        // Convert locker locations to Point objects
        for (int[] location : lockerLocations) {
            lockers.add(new Point(location[0], location[1]));
        }

        // Calculate initial distances
        calculateDistances();
    }

    // Main method: Calculate distances using multi-source BFS
    private void calculateDistances() {
        // Initialize distances array with MAX_VALUE
        for (int i = 0; i < citySize; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }

        // If no lockers, all distances remain MAX_VALUE
        if (lockers.isEmpty()) {
            return;
        }

        // Multi-source BFS
        Queue<Point> queue = new LinkedList<>();

        // Add all locker locations to queue with distance 0
        for (Point locker : lockers) {
            queue.offer(locker);
            distances[locker.x][locker.y] = 0;
        }

        // Directions for adjacent cells (up, down, left, right)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // BFS to calculate minimum distances
        while (!queue.isEmpty()) {
            Point current = queue.poll();

            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];

                // Check bounds
                if (newX >= 0 && newX < citySize && newY >= 0 && newY < citySize) {
                    int newDistance = distances[current.x][current.y] + 1;

                    // Update if we found a shorter path
                    if (newDistance < distances[newX][newY]) {
                        distances[newX][newY] = newDistance;
                        queue.offer(new Point(newX, newY));
                    }
                }
            }
        }
    }

    // Follow-up 1: Add a locker at given location
    public void addLocker(int x, int y) {
        Point newLocker = new Point(x, y);

        // If locker already exists, no changes needed
        if (lockers.contains(newLocker)) {
            return;
        }

        lockers.add(newLocker);

        // Optimize: Only update cells that would be closer to new locker
        updateDistancesAfterAdd(newLocker);
    }

    // Optimized update after adding a locker
    private void updateDistancesAfterAdd(Point newLocker) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[citySize][citySize];

        queue.offer(newLocker);
        distances[newLocker.x][newLocker.y] = 0;
        visited[newLocker.x][newLocker.y] = true;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];

                if (newX >= 0 && newX < citySize && newY >= 0 && newY < citySize && !visited[newX][newY]) {
                    int newDistance = distances[current.x][current.y] + 1;

                    // Only continue BFS if we improve the distance
                    if (newDistance < distances[newX][newY]) {
                        distances[newX][newY] = newDistance;
                        queue.offer(new Point(newX, newY));
                    }
                    visited[newX][newY] = true;
                }
            }
        }
    }

    // Follow-up 2: Remove a locker at given location
    public void removeLocker(int x, int y) {
        Point lockerToRemove = new Point(x, y);

        // If locker doesn't exist, no changes needed
        if (!lockers.contains(lockerToRemove)) {
            return;
        }

        lockers.remove(lockerToRemove);

        // After removal, we need to recalculate all distances
        // as some cells might have been dependent on the removed locker
        calculateDistances();
    }

    // Get the distances array
    public int[][] getDistances() {
        return distances;
    }

    // Print the distance matrix for visualization
    public void printDistances() {
        System.out.println("Distance Matrix:");
        for (int i = 0; i < citySize; i++) {
            for (int j = 0; j < citySize; j++) {
                if (distances[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF ");
                } else {
                    System.out.printf("%3d ", distances[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example usage
        int citySize = 5;
        List<int[]> lockerLocations = Arrays.asList(
                new int[]{0, 0},
                new int[]{2, 3},
                new int[]{4, 4}
        );

        AmazonLocker solution = new AmazonLocker(citySize, lockerLocations);

        System.out.println("Initial distances:");
        solution.printDistances();

        // Test adding a locker
        System.out.println("After adding locker at (1, 1):");
        solution.addLocker(1, 1);
        solution.printDistances();

        // Test removing a locker
        System.out.println("After removing locker at (0, 0):");
        solution.removeLocker(0, 0);
        solution.printDistances();
    }
}