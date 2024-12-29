package com.example.javacodingjourney.ps;

import java.io.File;

public class FileSizeMatch implements FileMatch {
    private final long size;
    private final FileSizeOperator operator;

    public FileSizeMatch(FileSizeOperator operator, long size) {
        this.operator = operator;
        this.size = size;
    }

    @Override
    public boolean matches(File fileName) {
        return switch (this.operator) {
            case EQUALS -> fileName.length() == size;
            case NOT_EQUALS -> fileName.length() != size;
            case GREATER_THAN -> fileName.length() > size;
            case LESS_THAN -> fileName.length() < size;
            case GREATER_THAN_OR_EQUALS -> fileName.length() >= size;
            case LESS_THAN_OR_EQUALS -> fileName.length() <= size;
            default -> throw new IllegalArgumentException("Unknown operator: " + operator);
        };
    }
    public enum FileSizeOperator {
        EQUALS, NOT_EQUALS, GREATER_THAN, LESS_THAN, GREATER_THAN_OR_EQUALS, LESS_THAN_OR_EQUALS;
    }
}
