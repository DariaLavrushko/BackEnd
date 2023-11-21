package org.group28projectjpa.core.services;

import lombok.AllArgsConstructor;
import org.group28projectjpa.core.utils.TaskConverter;
import org.group28projectjpa.core.validation.NotFoundException;
import org.group28projectjpa.dto.task.TaskCreateOrUpdateResponseDTO;
import org.group28projectjpa.dto.task.TaskCreateRequestDTO;
import org.group28projectjpa.dto.task.TaskResponseDTO;
import org.group28projectjpa.dto.task.TaskUpdateRequestDTO;
import org.group28projectjpa.entity.Task;
import org.group28projectjpa.repository.ManagerRepository;
import org.group28projectjpa.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TasksServices {
    private final TaskRepository repository;
    private final TaskConverter converter;

    //1. Получение списка всех задач
    public List<TaskResponseDTO> findAllTasks(){
        return repository.findAll().stream()
                .map(converter::toDto)
                .toList();
    }
    //2. Получение задачи по ID
    public TaskResponseDTO findById(Integer id){
        Task task = repository.findById(id).orElseThrow(()-> new NotFoundException("Task not found whith id: " + id));
        return converter.toDto(task);
    }
    //1. Создать новую задачу
    public TaskCreateOrUpdateResponseDTO createTask(TaskCreateRequestDTO createRequestDTO){
        Task task = converter.fromCreateRequest(createRequestDTO);
        task = repository.save(task);
        return converter.toCreateDto(task);
    }
    //2. Редактировать задачу
    public TaskCreateOrUpdateResponseDTO updateTask(TaskUpdateRequestDTO updateTask){
        Task task = repository.findById(updateTask.getId())
                .orElseThrow(() -> new NotFoundException("Task whith ID: " + updateTask.getId() + " is not found"));
        task.setTaskName(updateTask.getTaskName());
        task.setDescription(updateTask.getDescription());
        task.setDeadline(updateTask.getDeadline());
        task.setStatus(updateTask.getStatus());
        return converter.toCreateDto(task);
    }
    //3. Удалить задачу
    public void deleteTask(Integer id){
        Task task = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task whith ID: " + id + " is not found"));
        repository.delete(task);
    }
}
