package be.hasan.libraSphere.copies;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CopyNotFoundException extends RuntimeException{
    public CopyNotFoundException(){
        super("The requested copy could not be found.");
    }
}
