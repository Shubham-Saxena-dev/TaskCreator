package com.celonis.challenge.model;

public abstract class Task {

    public abstract void cancel();

    public abstract int getProgress();

    private TaskStatus status;

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
