package be.hasan.libraSphere.authors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(){
        super("The requested author could not be found.");
    }
}
