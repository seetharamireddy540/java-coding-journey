package com.example.javacodingjourney.ps;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FileSizeMatchTest {

    @TempDir
    Path tempDir;

    @TempDir
    File tempDir_2;

    /**
     * Test constructor and matches method for EQUALS operator
     */
    @Test
    void testFileSizeMatchEqualsOperator() {
        // Arrange
        FileSizeMatch.FileSizeOperator operator = FileSizeMatch.FileSizeOperator.EQUALS;
        long size = 1024L;
        FileSizeMatch fileSizeMatch = new FileSizeMatch(operator, size);

        File mockFile = mock(File.class);
        when(mockFile.length()).thenReturn(size);

        // Act
        boolean result = fileSizeMatch.matches(mockFile);

        // Assert
        assertTrue(result);
        verify(mockFile).length();
    }

    @Test
    void testFileSizeMatchWithIllegalOperator() {
        /**
         * Test matches method with an illegal operator.
         * This tests exception handling in FileSizeMatch.
         */
        FileSizeMatch fileSizeMatch = new FileSizeMatch(null, 100L);
        File file = Mockito.mock(File.class);

        assertThrows(IllegalArgumentException.class, () -> fileSizeMatch.matches(file),
                "matches method should throw IllegalArgumentException for null operator");
    }

    @Test
    void testFileSizeMatchWithMaxLongValue() {
        /**
         * Test FileSizeMatch with the maximum possible long value.
         * This tests an edge case for FileSizeMatch.
         */
        FileSizeMatch fileSizeMatch = new FileSizeMatch(FileSizeMatch.FileSizeOperator.EQUALS, Long.MAX_VALUE);
        File largeFile = Mockito.mock(File.class);
        Mockito.when(largeFile.length()).thenReturn(Long.MAX_VALUE);

        assertTrue(fileSizeMatch.matches(largeFile),
                "matches method should return true for file with maximum long size");
    }

    @Test
    void testFileSizeMatchWithNegativeSize() {
        /**
         * Test FileSizeMatch constructor with a negative size value.
         * This tests the scenario where the input is outside accepted bounds.
         */
        assertDoesNotThrow(() -> new FileSizeMatch(FileSizeMatch.FileSizeOperator.EQUALS, -1L),
                "FileSizeMatch should not throw an exception for negative size");
    }

    @Test
    void testFileSizeMatchWithNonExistentFile() {
        /**
         * Test matches method with a non-existent file.
         * This tests an edge case for FileSizeMatch.
         */
        FileSizeMatch fileSizeMatch = new FileSizeMatch(FileSizeMatch.FileSizeOperator.EQUALS, 100L);
        File nonExistentFile = Mockito.mock(File.class);
        Mockito.when(nonExistentFile.length()).thenReturn(0L);

        assertFalse(fileSizeMatch.matches(nonExistentFile),
                "matches method should return false for non-existent file");
    }

    @ParameterizedTest
    @EnumSource(FileSizeMatch.FileSizeOperator.class)
    void testFileSizeMatchWithNullFile(FileSizeMatch.FileSizeOperator operator) {
        /**
         * Test matches method with a null file for all operators.
         * This tests the scenario where the input is invalid.
         */
        FileSizeMatch fileSizeMatch = new FileSizeMatch(operator, 100L);
        assertThrows(NullPointerException.class, () -> fileSizeMatch.matches(null),
                "matches method should throw NullPointerException for null file");
    }

    /**
     * Test the matches method for all FileSizeOperator cases
     */

    @Test
    void testMatchesWithDirectory() {
        /**
         * Test that matches method handles directories correctly
         */
        FileSizeMatch matcher = new FileSizeMatch(FileSizeMatch.FileSizeOperator.EQUALS, 0);
        assertTrue(matcher.matches(tempDir_2)); // Directories typically have a size of 0
    }

    @Test
    void testMatchesWithMaxLongValue() throws IOException {
        /**
         * Test that matches method handles extreme file sizes correctly
         */
        FileSizeMatch matcher = new FileSizeMatch(FileSizeMatch.FileSizeOperator.LESS_THAN, Long.MAX_VALUE);
        File file = File.createTempFile("test", ".txt", tempDir_2);
        assertTrue(matcher.matches(file)); // Any real file should be less than Long.MAX_VALUE
    }

    @Test
    void testMatchesWithNegativeSize() {
        /**
         * Test that matches method handles negative size correctly
         */
        FileSizeMatch matcher = new FileSizeMatch(FileSizeMatch.FileSizeOperator.LESS_THAN, -1);
        File file = new File(tempDir_2, "test.txt");
        assertFalse(matcher.matches(file)); // File size can't be negative
    }

    @Test
    void testMatchesWithNonExistentFile() {
        /**
         * Test that matches method returns false when given a non-existent file
         */
        FileSizeMatch matcher = new FileSizeMatch(FileSizeMatch.FileSizeOperator.EQUALS, 100);
        File nonExistentFile = new File(tempDir_2, "nonexistent.txt");
        assertFalse(matcher.matches(nonExistentFile));
    }

    @Test
    void testMatchesWithNullFile() {
        /**
         * Test that matches method throws NullPointerException when given a null file
         */
        FileSizeMatch matcher = new FileSizeMatch(FileSizeMatch.FileSizeOperator.EQUALS, 100);
        assertThrows(NullPointerException.class, () -> matcher.matches(null));
    }

    @Test
    void testMatchesWithUnknownOperator() {
        /**
         * Test that matches method throws IllegalArgumentException for unknown operator
         */
        FileSizeMatch matcher = new FileSizeMatch(null, 100);
        File file = new File(tempDir_2, "test.txt");
        assertThrows(IllegalArgumentException.class, () -> matcher.matches(file));
    }

}