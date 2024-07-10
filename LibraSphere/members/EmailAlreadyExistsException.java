package be.hasan.libraSphere.members;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailAlreadyExistsException extends RuntimeException{
    EmailAlreadyExistsException(){
        super("Email already in use. Please provide a unique email address.");
    }
}
