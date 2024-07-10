package be.hasan.libraSphere.borrowedCopies;

import be.hasan.libraSphere.members.MemberSummary;

import java.time.LocalDate;

public record BorrowerSummary(LocalDate borrowDate, LocalDate returnDate, MemberSummary memberSummary) {
    public BorrowerSummary(BorrowedCopy borrowedCopy){
        this(borrowedCopy.getBorrowDate(), borrowedCopy.getReturnDate(),
                new MemberSummary(borrowedCopy.getMember())
        );
    }
}
