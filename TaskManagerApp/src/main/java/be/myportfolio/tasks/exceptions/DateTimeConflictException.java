package be.myportfolio.tasks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DateTimeConflictException extends RuntimeException{
    public DateTimeConflictException(){
        super("Task with the same date and time already exists.");
    }
}
