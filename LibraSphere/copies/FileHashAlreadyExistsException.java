package be.hasan.libraSphere.copies;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FileHashAlreadyExistsException extends RuntimeException{
    public FileHashAlreadyExistsException(){
        super("File hash already in use. Please provide a unique file hash.");
    }
}
