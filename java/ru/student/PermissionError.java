package ru.student;

public class PermissionError extends RuntimeException {
    public PermissionError(String message) {
        super(message);
    }
}
