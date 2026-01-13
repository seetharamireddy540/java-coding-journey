package com.example.javacodingjourney.interview;

/**
 * You are given a 2D-array representing a maze. The array can contain the following values:
 * 0 = An open space
 * 1 = A wall
 * 2 = A piece of cheese
 * 3 = An exit to the maze
 *
 * Write a function that takes the array as input and returns a list that represents the path
 * from (0,0) to the cheese and then the exit. You can only move horizontally and vertically,
 * not diagonally.
 *
 * Input:
 * {
 *     {0, 1, 0, 2, 1},
 *     {0, 0, 0, 1, 3},
 *     {1, 0, 1, 0, 0},
 *     {0, 0, 0, 0, 0}
 * }
 *
 * Rat starts at (0,0)
 * Cheese at (0,3)
 * Exit at (1, 4)
 *
 * Output: [(0,0), (1,0), (1,1), (1,2), (0,2), (0,3), (0,2), (1,2),
 *          (1,1), (2,1), (3,1), (3,2), (3,3), (3,4), (2,4), (1,4)]
 */
import java.util.*;

public class MazeSolver {

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

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    public static List<Point> solveMaze(int[][] maze) {
        // Find cheese and exit positions
        Point cheese = null;
        Point exit = null;

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 2) {
                    cheese = new Point(i, j);
                } else if (maze[i][j] == 3) {
                    exit = new Point(i, j);
                }
            }
        }

        // Find path from start to cheese
        List<Point> pathToCheese = findPath(maze, new Point(0, 0), cheese);

        // Find path from cheese to exit
        List<Point> pathToExit = findPath(maze, cheese, exit);

        // Combine paths (remove duplicate cheese position)
        List<Point> fullPath = new ArrayList<>(pathToCheese);
        for (int i = 1; i < pathToExit.size(); i++) {
            fullPath.add(pathToExit.get(i));
        }

        return fullPath;
    }

    private static List<Point> findPath(int[][] maze, Point start, Point end) {
        int rows = maze.length;
        int cols = maze[0].length;

        // Directions: up, right, down, left
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<Point> queue = new LinkedList<>();
        Map<Point, Point> parent = new HashMap<>();
        Set<Point> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);
        parent.put(start, null);

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.equals(end)) {
                // Reconstruct path
                List<Point> path = new ArrayList<>();
                Point node = current;
                while (node != null) {
                    path.add(0, node);
                    node = parent.get(node);
                }
                return path;
            }

            // Explore neighbors
            for (int i = 0; i < 4; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];

                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols) {
                    Point neighbor = new Point(newX, newY);

                    // Can move to this cell if it's not a wall and not visited
                    if (maze[newX][newY] != 1 && !visited.contains(neighbor)) {
                        visited.add(neighbor);
                        parent.put(neighbor, current);
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return new ArrayList<>(); // No path found
    }

    public static void main(String[] args) {
        int[][] maze = {
                {0, 1, 0, 2, 1},
                {0, 0, 0, 1, 3},
                {1, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}
        };

        List<Point> path = solveMaze(maze);

        System.out.println("Path from start to cheese to exit:");
        System.out.println(path);

        // Verify the path
        System.out.println("\nPath verification:");
        for (int i = 0; i < path.size(); i++) {
            Point p = path.get(i);
            int value = maze[p.x][p.y];
            String type = value == 0 ? "open space" :
                    value == 2 ? "CHEESE" :
                            value == 3 ? "EXIT" : "unknown";
            System.out.println(i + ": " + p + " - " + type);
        }
    }
}