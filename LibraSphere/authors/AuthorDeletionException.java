package be.hasan.libraSphere.authors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AuthorDeletionException extends RuntimeException{
    AuthorDeletionException(long authorId){
        super("Cannot delete author with ID: " + authorId + " as it has related records.");
    }
}
