package com.example.tasklist.service;

import com.example.tasklist.entity.Task;
import com.example.tasklist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Optional<Task> findById(Long id) {
        return repository.findById(id);
    }

    public Task save(Task task) {
        return repository.save(task);
    }

    public boolean deleteById(Long id) {
        repository.deleteById(id);

        return !repository.existsById(id);
    }

}
