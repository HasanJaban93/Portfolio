package be.hasan.libraSphere.borrowedCopies;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BorrowedCopyNotFoundException extends RuntimeException{
    public BorrowedCopyNotFoundException(){
        super("Borrowed Copy not found.");
    }
}
