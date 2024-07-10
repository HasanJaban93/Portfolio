package be.hasan.libraSphere.borrowedCopies;

import be.hasan.libraSphere.copies.DigitalCopySummary;

public record DigitalCopySummaryWithBorrowKey(DigitalCopySummary digitalCopySummary, long borrowKey)
        implements CopySummaryWithBorrowKey{
}
