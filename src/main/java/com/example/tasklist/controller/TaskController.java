package com.example.tasklist.controller;

import com.example.tasklist.entity.Task;
import com.example.tasklist.exception.UpdateTaskException;
import com.example.tasklist.exception.ValidateTaskExceprion;
import com.example.tasklist.service.TaskService;
import com.example.tasklist.util.MergeObject;
import com.example.tasklist.util.ValidateTask;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;


@RestController
@RequestMapping("/api/v1/task")
@Slf4j
@RequiredArgsConstructor
@Api(value = "Task", description = "Api de gerenciamento de Tarefas")
@CrossOrigin(origins = {"http://localhost:4200", "https://dashboard.heroku.com/apps/ws-tasklist-api"})
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private MessageSource messageSource;

    @PostMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> create(@RequestBody Task task) {
        try {
            if (!new ValidateTask(task).isValid())
                throw new ValidateTaskExceprion();

            return new ResponseEntity(taskService.save(task), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("msg: " + messageSource.getMessage("task.M001", null, Locale.getDefault()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> findAll() {
        return new ResponseEntity(taskService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> findId(@PathVariable Long id) {
        return new ResponseEntity(taskService.findById(id), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> update(@RequestBody Task task, @PathVariable Long id) {
        try {
            if (!new ValidateTask(task).isValid())
                throw new ValidateTaskExceprion();

            Task task_ = taskService.findById(id)
                    .map(task_dataBase -> {

                        taskService.save(new MergeObject<>(task, task_dataBase).merge());

                        return task_dataBase;
                    })
                    .orElseGet(() -> {
                        throw new UpdateTaskException();
                    });

            return new ResponseEntity(task_, HttpStatus.OK);
        } catch (ValidateTaskExceprion e) {
            return new ResponseEntity("msg: " + messageSource.getMessage("task.M001", null, Locale.getDefault()), HttpStatus.BAD_REQUEST);
        } catch (UpdateTaskException e) {
            return new ResponseEntity("msg: " + messageSource.getMessage("task.M005", null, Locale.getDefault()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        try {
            return new ResponseEntity(taskService.deleteById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
    }
}
