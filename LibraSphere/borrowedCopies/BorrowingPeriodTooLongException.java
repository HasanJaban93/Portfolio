package be.hasan.libraSphere.borrowedCopies;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BorrowingPeriodTooLongException extends RuntimeException{
    public BorrowingPeriodTooLongException(){
        super("The borrowing period can not exceed one month.");
    }
}
