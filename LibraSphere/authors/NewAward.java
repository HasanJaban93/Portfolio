package be.hasan.libraSphere.authors;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NewAward(@NotBlank String name, @NotNull @Positive int year) {
}
