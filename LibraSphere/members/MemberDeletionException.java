package be.hasan.libraSphere.members;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MemberDeletionException extends RuntimeException{
    MemberDeletionException(long memberId){
        super("Cannot delete member with ID: " + memberId + " as it has related records.");
    }
}
