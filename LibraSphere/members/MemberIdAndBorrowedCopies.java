package be.hasan.libraSphere.members;

import be.hasan.libraSphere.borrowedCopies.BorrowedCopySummary;

import java.util.List;

public record MemberIdAndBorrowedCopies(long id, List<BorrowedCopySummary> borrowedCopiesSummary) {
    MemberIdAndBorrowedCopies(Member member){
        this(member.getId(),
                member.getBorrowedCopies()
                        .stream()
                        .map(borrowedCopy -> new BorrowedCopySummary(borrowedCopy))
                        .toList());
    }
}
