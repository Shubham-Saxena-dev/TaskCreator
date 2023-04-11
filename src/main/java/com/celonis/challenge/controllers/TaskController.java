package com.celonis.challenge.controllers;

import com.celonis.challenge.model.ProjectGenerationTask;
import com.celonis.challenge.services.FileService;
import com.celonis.challenge.services.TaskService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    private final FileService fileService;

    public TaskController(TaskService taskService,
                          FileService fileService) {
        this.taskService = taskService;
        this.fileService = fileService;
    }

    @GetMapping("/")
    public List<ProjectGenerationTask> listTasks() {
        return taskService.listTasks();
    }

    @PostMapping("/")
    public ProjectGenerationTask createTask(@RequestBody @Valid ProjectGenerationTask projectGenerationTask) {
        return taskService.createTask(projectGenerationTask);
    }

    @GetMapping("/{taskId}")
    public ProjectGenerationTask getTask(@PathVariable String taskId) {
        return taskService.getTask(taskId);
    }

    @PutMapping("/{taskId}")
    public ProjectGenerationTask updateTask(@PathVariable String taskId,
                                            @RequestBody @Valid ProjectGenerationTask projectGenerationTask) {
        return taskService.update(taskId, projectGenerationTask);
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable String taskId) {
        taskService.delete(taskId);
    }

    @PostMapping("/{taskId}/execute")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void executeTask(@PathVariable String taskId) {
        taskService.executeTask(taskId);
    }

    @GetMapping("/{taskId}/result")
    public ResponseEntity<FileSystemResource> getResult(@PathVariable String taskId) {
        return fileService.getTaskResult(taskId);
    }

    @PostMapping(value = "/counter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> startCounter(@RequestParam(name = "x", defaultValue = "1") int x,
                                               @RequestParam(name = "y", defaultValue = "100") int y) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(this.taskService.startCounter(x, y));
    }

    @GetMapping(value = "/counter/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getCounterTaskProgress(@PathVariable int taskId) {
        return ResponseEntity.ok(this.taskService.getTaskProgress(taskId));
    }

    @DeleteMapping("/counter/{taskId}")
    public ResponseEntity<Object> cancelCounter(@PathVariable int taskId) {
        this.taskService.cancelTask(taskId);
        return ResponseEntity.ok().build();
    }
}
