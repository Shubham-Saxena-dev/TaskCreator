package com.celonis.challenge.services;

import com.celonis.challenge.model.CounterTask;
import com.celonis.challenge.model.Task;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.*;

@Component
public class TaskHandler {
    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private final Map<Integer, Task> tasks = new ConcurrentHashMap<>();


    public String submitTask(CounterTask task) throws ExecutionException, InterruptedException {
        int taskId = tasks.size() + 1; //UUID
        tasks.put(taskId, task);
        Future<String> future = executorService.submit(task);
        return future.get();
    }

    public int getTaskProgress(int taskId) {
        Task task = tasks.get(taskId);
        if (task != null) {
            return task.getProgress();
        }
        return -1;
    }

    public void cancelTask(int taskId) {
        Task task = tasks.get(taskId);
        if (task != null) {
            task.cancel();
            tasks.remove(taskId);
        }
    }
}
