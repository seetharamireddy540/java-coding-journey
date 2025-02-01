package com.example.javacodingjourney.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RiversCountApp {

    public static void main(String[] args) {
        int count = 0;
        int[][] riverData = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0},
                {0, 1, 0}
        };

        count = countConnectedRivers(riverData);
        System.out.println("Number of rivers connected: " + count);
        System.out.println("Number of rivers connected: " + count);

    }

    private static int countConnectedRivers(int[][] riverData) {
        if (riverData == null || riverData.length == 0) {
            return 0;
        }
        int count = 0;
        List<Integer> reversSize = new ArrayList<>();
        for (int i = 0; i < riverData.length; i++) {
            for (int j = 0; j < riverData[0].length; j++) {
                if (riverData[i][j] == 1) {
                    reversSize.add(dfs(riverData, i, j));
                    count++;
                }
            }
        }
        System.out.println(Arrays.toString(reversSize.toArray()));
        return count;
    }

    private static int dfs(int[][] riverData, int i, int j) {

        if (i < 0 || i >= riverData.length || j < 0 || j >= riverData[0].length
                || riverData[i][j] == 0) {
            return 0;
        }
        riverData[i][j] = 0;
        int size = 1;

        size = size + dfs(riverData, i + 1, j);
        size = size + dfs(riverData, i - 1, j);
        size = size + dfs(riverData, i, j + 1);
        size = size + dfs(riverData, i, j - 1);
        return size;
    }
}
