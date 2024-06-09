package be.myportfolio.tasks.repository;

import be.myportfolio.tasks.model.State;
import be.myportfolio.tasks.model.Task;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
   List<Task> findByDescriptionContainingOrderByDateAscTimeAsc(String word);
   List<Task> findByDateOrderByTime(LocalDate date);
   @Query("""
          select t
          from Task t
          where t.date between :startDate and :endDate
          order by t.date, t.time
          """)
   List<Task> findByStartAndEndDate(LocalDate startDate, LocalDate endDate);
   List<Task> findByStateOrderByDateAscTimeAsc(State state);
   boolean existsByDateAndTime(LocalDate date, LocalTime time);
   @Lock(LockModeType.PESSIMISTIC_WRITE)
   @Query("select t from Task t where t.id = :id")
   Optional<Task> findAndLockById(long id);


}
