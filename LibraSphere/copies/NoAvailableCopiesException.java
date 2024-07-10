package be.hasan.libraSphere.copies;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoAvailableCopiesException extends RuntimeException{
    public NoAvailableCopiesException(String message){
        super(message);
    }
}
