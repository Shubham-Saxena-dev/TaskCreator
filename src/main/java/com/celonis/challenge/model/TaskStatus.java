package com.celonis.challenge.model;

public enum TaskStatus {
    IN_PROGRESS("In progress"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed"),
    INTERRUPTED("Interrupted");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
