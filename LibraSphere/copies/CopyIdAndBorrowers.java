package be.hasan.libraSphere.copies;

import be.hasan.libraSphere.borrowedCopies.BorrowerSummary;

import java.util.List;
import java.util.stream.Collectors;

public record CopyIdAndBorrowers(long copyId, List<BorrowerSummary> borrowerSummaries) {
    CopyIdAndBorrowers(Copy copy){
        this(copy.getId(),
                copy.getBorrowedCopies()
                        .stream()
                        .map(borrowedCopy -> new BorrowerSummary(borrowedCopy))
                        .collect(Collectors.toList())
        );
    }
}
