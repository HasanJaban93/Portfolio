package be.hasan.libraSphere.borrowedCopies;

import be.hasan.libraSphere.copies.*;

import java.time.LocalDate;


public record BorrowedCopySummary(LocalDate borrowDate, LocalDate returnDate,
                                  CopyTypeIdentifier copyTypeIdentifier) {
    public BorrowedCopySummary(BorrowedCopy borrowedCopy){
        this(borrowedCopy.getBorrowDate(), borrowedCopy.getReturnDate(),
                getCopyTypeIdentifier(borrowedCopy.getCopy())
        );
    }
    private static CopyTypeIdentifier getCopyTypeIdentifier(Copy copy){
        if (copy instanceof DigitalCopy){
            return new DigitalCopyIdentifier((DigitalCopy) copy);
        }else {
            return new PhysicalCopyIdentifier((PhysicalCopy) copy);
        }
    }
}

