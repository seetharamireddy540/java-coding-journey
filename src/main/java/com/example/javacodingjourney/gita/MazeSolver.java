package com.example.javacodingjourney.gita;

import java.util.*;

public class MazeSolver {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static List<int[]> findPath(int[][] maze) {
        return findPathBFS(maze);
    }

    public static List<int[]> findPathBFS(int[][] maze) {
        int[] start = {0, 0};
        int[] cheese = findPosition(maze, 2);
        int[] exit = findPosition(maze, 3);

        if (cheese == null || exit == null) return new ArrayList<>();

        List<int[]> pathToCheese = bfs(maze, start, cheese);
        List<int[]> pathToExit = bfs(maze, cheese, exit);

        if (pathToCheese.isEmpty() || pathToExit.isEmpty()) return new ArrayList<>();

        List<int[]> fullPath = new ArrayList<>(pathToCheese);
        fullPath.addAll(pathToExit.subList(1, pathToExit.size()));

        return fullPath;
    }

    public static List<int[]> findPathDFS(int[][] maze) {
        int[] start = {0, 0};
        int[] cheese = findPosition(maze, 2);
        int[] exit = findPosition(maze, 3);

        if (cheese == null || exit == null) return new ArrayList<>();

        List<int[]> pathToCheese = dfs(maze, start, cheese);
        List<int[]> pathToExit = dfs(maze, cheese, exit);

        if (pathToCheese.isEmpty() || pathToExit.isEmpty()) return new ArrayList<>();

        List<int[]> fullPath = new ArrayList<>(pathToCheese);
        fullPath.addAll(pathToExit.subList(1, pathToExit.size()));

        return fullPath;
    }

    private static List<int[]> bfs(int[][] maze, int[] start, int[] target) {
        Queue<int[]> queue = new LinkedList<>();
        Map<String, int[]> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start[0] + "," + start[1]);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            if (current[0] == target[0] && current[1] == target[1]) {
                return reconstructPath(parent, start, target);
            }

            for (int[] dir : DIRECTIONS) {
                int newRow = current[0] + dir[0];
                int newCol = current[1] + dir[1];
                String key = newRow + "," + newCol;

                if (isValid(maze, newRow, newCol) && !visited.contains(key)) {
                    queue.offer(new int[]{newRow, newCol});
                    visited.add(key);
                    parent.put(key, current);
                }
            }
        }
        return new ArrayList<>();
    }

    private static List<int[]> dfs(int[][] maze, int[] start, int[] target) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        List<int[]> path = new ArrayList<>();

        if (dfsHelper(maze, start[0], start[1], target, visited, path)) {
            return path;
        }
        return new ArrayList<>();
    }

    private static boolean dfsHelper(int[][] maze, int row, int col, int[] target, boolean[][] visited, List<int[]> path) {
        if (!isValid(maze, row, col) || visited[row][col]) return false;

        path.add(new int[]{row, col});
        visited[row][col] = true;

        if (row == target[0] && col == target[1]) return true;

        for (int[] dir : DIRECTIONS) {
            if (dfsHelper(maze, row + dir[0], col + dir[1], target, visited, path)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    private static List<int[]> reconstructPath(Map<String, int[]> parent, int[] start, int[] target) {
        List<int[]> path = new ArrayList<>();
        int[] current = target;

        while (current != null) {
            path.add(0, current);
            current = parent.get(current[0] + "," + current[1]);
        }
        return path;
    }

    private static boolean isValid(int[][] maze, int row, int col) {
        return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length && maze[row][col] != 1;
    }

    private static int[] findPosition(int[][] maze, int value) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == value) return new int[]{i, j};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[][] maze = {
                {0, 1, 0, 2, 1},
                {0, 0, 0, 1, 3},
                {1, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}
        };

        System.out.println("BFS Solution:");
        List<int[]> bfsPath = findPathBFS(maze);
        printPath(bfsPath);

        System.out.println("\nDFS Solution:");
        List<int[]> dfsPath = findPathDFS(maze);
        printPath(dfsPath);
    }

    private static void printPath(List<int[]> path) {
        System.out.print("[");
        for (int i = 0; i < path.size(); i++) {
            System.out.print("(" + path.get(i)[0] + "," + path.get(i)[1] + ")");
            if (i < path.size() - 1) System.out.print(", ");
        }
        System.out.println("] - Length: " + path.size());
    }
}