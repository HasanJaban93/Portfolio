package be.hasan.libraSphere.books;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CopyAlreadyExistsException extends RuntimeException{
    CopyAlreadyExistsException(){
        super("This copy of the book has already been added.");
    }
}
