package com.celonis.challenge.services;

import com.celonis.challenge.model.ProjectGenerationTask;
import com.celonis.challenge.repository.ProjectGenerationTaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TaskCleanupService {

    private static final long WEEK = 1000 * 60 * 60 * 24 * 7;

    private final ProjectGenerationTaskRepository repository;

    public TaskCleanupService(ProjectGenerationTaskRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public int removeOldTasks() {
        Date weekAgo = new Date(System.currentTimeMillis() - WEEK);
        List<ProjectGenerationTask> tasks = repository.findByCreationDateBefore(weekAgo);
        repository.deleteAll(tasks);
        return tasks.size();
    }
}
