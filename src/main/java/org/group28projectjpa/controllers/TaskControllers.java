package org.group28projectjpa.controllers;

import lombok.AllArgsConstructor;
import org.group28projectjpa.core.services.TasksServices;
import org.group28projectjpa.dto.task.TaskCreateOrUpdateResponseDTO;
import org.group28projectjpa.dto.task.TaskCreateRequestDTO;
import org.group28projectjpa.dto.task.TaskResponseDTO;
import org.group28projectjpa.dto.task.TaskUpdateRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TaskControllers {
    private final TasksServices services;

    //1. Получение списка всех задач
    @GetMapping("/tasks")
    public List<TaskResponseDTO> findAllTasks(){
        return services.findAllTasks();
    }
    //2. Получение задачи по ID
    @GetMapping("/tasks/{id}")
    public TaskResponseDTO findTaskById(@PathVariable("id") Integer id){
        return services.findById(id);
    }
    //1. Создать новую задачу
    @PostMapping("/task")
    public TaskCreateOrUpdateResponseDTO createTask(@RequestBody TaskCreateRequestDTO request){
        return services.createTask(request);
    }
    //2. Редактировать задачу
    @PutMapping("/tasks")
    public TaskCreateOrUpdateResponseDTO updateTask(@RequestBody TaskUpdateRequestDTO taskUpdateRequestDTO){
        return services.updateTask(taskUpdateRequestDTO);
    }
    //3. Удалить задачу
    @DeleteMapping("/tasks/{id}")
    public void deleteTTask(@RequestParam Integer id){
        services.deleteTask(id);
    }

}
