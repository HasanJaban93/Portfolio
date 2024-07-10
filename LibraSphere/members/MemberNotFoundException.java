package be.hasan.libraSphere.members;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MemberNotFoundException extends RuntimeException{
    MemberNotFoundException(){
        super("The requested member could not be found.");
    }
}
