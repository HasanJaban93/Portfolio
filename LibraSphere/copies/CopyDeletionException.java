package be.hasan.libraSphere.copies;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CopyDeletionException extends RuntimeException{
    public CopyDeletionException(long copyId){
        super("Cannot delete author with ID: " + copyId + " as it has related records.");
    }
}
