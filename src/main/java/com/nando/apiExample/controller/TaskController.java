package com.nando.apiExample.controller;

import com.nando.apiExample.model.entity.Task;
import com.nando.apiExample.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController extends Controller {
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> hi() {
        return this.taskService.findAll();
    }

    @PostMapping("/task")
    public Task saveTask(@RequestBody Task task) {
        return this.taskService.save(task);
    }

    @GetMapping("task/{id}")
    public Task getById(@PathVariable() Integer id) {
        System.out.println(id);

        return this.taskService.findById(id);
    }
}
