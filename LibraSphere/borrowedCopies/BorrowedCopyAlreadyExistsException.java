package be.hasan.libraSphere.borrowedCopies;

public class BorrowedCopyAlreadyExistsException extends RuntimeException{
    public BorrowedCopyAlreadyExistsException(){
        super("Cannot borrow the same book again during the current borrowing period.");
    }
}
