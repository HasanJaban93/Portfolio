package be.hasan.libraSphere.authors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AwardAlreadyGrantedException extends RuntimeException {
    AwardAlreadyGrantedException(){
        super("The author has already received this award.");
    }
}
