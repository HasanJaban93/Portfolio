package be.hasan.libraSphere.borrowedCopies;

import be.hasan.libraSphere.copies.PhysicalCopySummary;

public record PhysicalCopySummaryWithBorrowKey(PhysicalCopySummary physicalCopySummary, long borrowKey)
        implements CopySummaryWithBorrowKey{
}
