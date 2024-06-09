package be.myportfolio.tasks.controller;

import be.myportfolio.tasks.changes.DateChange;
import be.myportfolio.tasks.changes.DescriptionChange;
import be.myportfolio.tasks.changes.StateChange;
import be.myportfolio.tasks.changes.TimeChange;
import be.myportfolio.tasks.exceptions.DateTimeConflictException;
import be.myportfolio.tasks.exceptions.TaskNotFoundException;
import be.myportfolio.tasks.model.NewTask;
import be.myportfolio.tasks.model.State;
import be.myportfolio.tasks.model.Task;
import be.myportfolio.tasks.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping("all")
    List<Task> findAll(){
        return taskService.findAll();
    }
    @GetMapping("{id}")
    Task findById(@PathVariable long id){return taskService.findById(id).orElseThrow(TaskNotFoundException::new);}
    @GetMapping(params = "description")
    List<Task> findByDescriptionContaining(String description) {
        return taskService.findByDescriptionContaining(description);
    }
    @GetMapping(params = "date")
    List<Task> findByDate(LocalDate date){
        return taskService.findByDate(date);
    }
    @GetMapping(params = {"startDate", "endDate"})
    List<Task> findByStartAndEndDate(LocalDate startDate, LocalDate endDate){
        return taskService.findByStartAndEndDate(startDate, endDate);
    }
    @GetMapping(params = "state")
    List<Task> findByState(State state){
        return taskService.findByState(state);
    }
    @PatchMapping("{id}/description")
    ResponseEntity<Object> updateDescription(@PathVariable long id,
                                             @RequestBody @Valid DescriptionChange descriptionChange){
        try{
            taskService.updateDescription(id, descriptionChange.description());
            return ResponseEntity.ok().build();
        }catch (TaskNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

    }
    @PatchMapping("{id}/date")
    ResponseEntity<Object> updateDate(@PathVariable long id,
                                             @RequestBody @Valid DateChange dateChange) {
        try {
            taskService.updateDate(id, dateChange.date());
            return ResponseEntity.ok().build();
        } catch (DateTimeConflictException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }
    @PatchMapping("{id}/time")
    ResponseEntity<Object> updateTime(@PathVariable long id,
                                             @RequestBody @Valid TimeChange timeChange) {
        try {
            taskService.updateTime(id, timeChange.time());
            return ResponseEntity.ok().build();
        } catch (DateTimeConflictException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }
    @PatchMapping("{id}/state")
    ResponseEntity<Object> updateState(@PathVariable long id,
                                       @RequestBody @Valid StateChange stateChange){
        try{
            taskService.updateState(id, stateChange.state());
            return ResponseEntity.ok().build();
        }catch (TaskNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

    }
    @PostMapping()
    ResponseEntity<Object> createTask(@RequestBody @Valid NewTask newTask) {
        try {
            long taskId = taskService.create(newTask);
            return ResponseEntity.ok().body(taskId);
        } catch (DateTimeConflictException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ex.getMessage());
        }
    }
    @DeleteMapping("{id}")
    void delete(@PathVariable long id) {
        try {
            taskService.delete(id);
        } catch (EmptyResultDataAccessException ignored) {
        }
    }
}
