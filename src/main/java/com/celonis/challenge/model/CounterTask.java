package com.celonis.challenge.model;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterTask extends Task implements Callable<String> {
    private final int x;
    private final int y;
    private final AtomicInteger progress = new AtomicInteger(0);
    private final AtomicBoolean cancelled = new AtomicBoolean(false);

    public CounterTask(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String call() {
        setStatus(TaskStatus.IN_PROGRESS);
        for (int i = x; i <= y; i++) {
            progress.set((i - x) * 100 / (y - x)); // progress percentage
            try {
                Thread.sleep(1000); // 1 second gap
            } catch (InterruptedException e) {
                return TaskStatus.INTERRUPTED.getStatus();
            }
            if (this.isCancelled()) {
                return TaskStatus.CANCELLED.getStatus();
            }
        }
        return TaskStatus.COMPLETED.getStatus();
    }

    private boolean isCancelled() {
        return cancelled.get();
    }

    @Override
    public void cancel() {
        cancelled.set(true);
    }

    @Override
    public int getProgress() {
        return progress.get();
    }
}
