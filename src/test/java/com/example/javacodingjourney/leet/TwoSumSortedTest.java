package com.example.javacodingjourney.leet;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TwoSumSortedTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void twoSum() {
        int[] data = {4, 5, 7, 8, 9, 10};
        int target = 15;
        System.out.println("result ->" + Arrays.toString(TwoSumSorted.twoSum(data, target)));

    }
}