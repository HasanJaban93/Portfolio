package be.hasan.libraSphere.authors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AuthorAlreadyExistsException extends RuntimeException{
        public AuthorAlreadyExistsException(){
            super("This author is already registered.");
        }
    }