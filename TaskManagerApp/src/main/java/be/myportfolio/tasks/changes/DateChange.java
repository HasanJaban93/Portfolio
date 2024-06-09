package be.myportfolio.tasks.changes;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DateChange(@NotNull @FutureOrPresent LocalDate date) {
}
