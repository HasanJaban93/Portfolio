package be.myportfolio.tasks.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private LocalDate date;
    private LocalTime time;
    @Enumerated(EnumType.STRING)
    private State state;
    protected Task(){}
    public Task(String description, LocalDate date, LocalTime time){
        this.description = description;
        this.date = date;
        this.time = time;
        state = State.INCOMPLETED;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public State getState() {
        return state;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setState(State state) {
        this.state = state;
    }
}
