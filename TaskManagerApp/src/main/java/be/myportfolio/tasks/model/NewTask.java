package be.myportfolio.tasks.model;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record NewTask( @NotBlank String description,
                       @NotNull @FutureOrPresent LocalDate date,
                      @NotNull LocalTime time) {
}
