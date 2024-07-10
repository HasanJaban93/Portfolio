package be.hasan.libraSphere.borrowedCopies;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record NewBorrowedCopy(@NotNull @FutureOrPresent LocalDate borrowDate,
                              @NotNull @Future LocalDate returnDate,
                              @NotNull @Positive long bookId,
                              @NotNull RequiredCopyType requiredCopyType) {
}
