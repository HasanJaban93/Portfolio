package be.hasan.libraSphere.authors;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NewAuthor(@NotBlank String firstName, @NotBlank String lastName,
                        @NotNull @Positive int yearOfBirth, @NotNull Gender gender, @NotBlank String nationality) {
}
