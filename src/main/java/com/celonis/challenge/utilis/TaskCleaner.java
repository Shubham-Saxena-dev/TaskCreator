package com.celonis.challenge.utilis;

import com.celonis.challenge.services.TaskCleanupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class TaskCleaner {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private final TaskCleanupService cleanupService;

    public TaskCleaner(TaskCleanupService cleanupService) {
        this.cleanupService = cleanupService;
    }

    @Scheduled(fixedDelay = 86400000) // every day run remover
    public void cleanupTasks() {
        int removedTaskCount = this.cleanupService.removeOldTasks();
        LOG.info("Total redundant tasks cleaned : ", removedTaskCount);
    }
}
