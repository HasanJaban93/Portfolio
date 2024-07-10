package be.hasan.libraSphere.books;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BookDeletionException extends RuntimeException{
    BookDeletionException(long bookId){
        super("Cannot delete book with ID: " + bookId + " as it has related records.");
    }
}
