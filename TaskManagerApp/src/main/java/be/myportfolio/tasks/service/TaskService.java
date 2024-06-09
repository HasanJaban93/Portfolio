package be.myportfolio.tasks.service;

import be.myportfolio.tasks.exceptions.DateTimeConflictException;
import be.myportfolio.tasks.exceptions.TaskNotFoundException;
import be.myportfolio.tasks.model.NewTask;
import be.myportfolio.tasks.model.State;
import be.myportfolio.tasks.model.Task;
import be.myportfolio.tasks.repository.TaskRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TaskService {
    private final TaskRepository taskRepository;

    // Commented out EntityManager
    // @PersistenceContext
    // private EntityManager entityManager;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll(){
        return taskRepository.findAll(Sort.by("date", "time"));
    }
    public Optional<Task> findById(long id){ return taskRepository.findById(id);}
    public List<Task> findByDescriptionContaining(String word){
        return taskRepository.findByDescriptionContainingOrderByDateAscTimeAsc(word);
    }
    public List<Task> findByDate(LocalDate date){
        return taskRepository.findByDateOrderByTime(date);
    }
    public List<Task> findByStartAndEndDate(LocalDate startDate, LocalDate endDate){
        return taskRepository.findByStartAndEndDate(startDate, endDate);
    }
    public List<Task> findByState(State state){
        return taskRepository.findByStateOrderByDateAscTimeAsc(state);
    }
    @Transactional
    public void updateDescription(long id, String description) {
        taskRepository.findAndLockById(id)
                .orElseThrow(TaskNotFoundException::new).setDescription(description);

        // Commented out explicit flush
        // entityManager.flush(); // Ensure changes are flushed to the database
        /*
        Before adding EntityManager.flush(),
        the PATCH and DELETE requests used to give a status of 200, but the assertions would fail.
        Hibernate might have delayed flushing the changes to the database,
         causing the test to read stale data.
        By explicitly saving the entity and forcing a flush,
         you ensured the changes were written to the database immediately,
         leading to the expected behavior in your test.
         */

    }
    @Transactional
    public void updateDate(long id, LocalDate date) {
            var task = taskRepository.findAndLockById(id)
                    .orElseThrow(TaskNotFoundException::new);
            if (taskRepository.existsByDateAndTime(date,task.getTime())){
                throw new DateTimeConflictException();
            }else{
                task.setDate(date);

                // Commented out explicit flush
                // entityManager.flush();
            }
    }
    @Transactional
    public void updateTime(long id, LocalTime time) {
           var task = taskRepository.findAndLockById(id)
                    .orElseThrow(TaskNotFoundException::new);
           if (taskRepository.existsByDateAndTime(task.getDate(), time)){
               throw new DateTimeConflictException();
           }else {
               task.setTime(time);

               // Commented out explicit flush
               // entityManager.flush();
           }

    }
    @Transactional
    public void updateState(long id, State state) {
        taskRepository.findAndLockById(id)
                .orElseThrow(TaskNotFoundException::new).setState(state);

        // Commented out explicit flush
        // entityManager.flush();
    }
    @Transactional
    public long create(NewTask newTask) {
            var task = new Task(newTask.description(), newTask.date(), newTask.time());
            if (taskRepository.existsByDateAndTime(task.getDate(), task.getTime())){
                throw new DateTimeConflictException();
            }else {
                taskRepository.save(task);
                return task.getId();
            }
    }
    @Transactional
    public void delete(long id) {
        taskRepository.deleteById(id);

        // Commented out explicit flush
        // entityManager.flush();
    }
}
