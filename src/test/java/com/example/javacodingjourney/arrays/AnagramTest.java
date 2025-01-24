package com.example.javacodingjourney.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnagramTest {

    @Test
    public void testAnagram() {

        Assertions.assertTrue(Anagram.isAnagrams("danger", "garden"));
        Assertions.assertFalse(Anagram.isAnagrams("danger", "angers"));
    }

}