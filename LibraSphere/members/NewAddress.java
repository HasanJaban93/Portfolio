package be.hasan.libraSphere.members;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NewAddress(@NotBlank String street, @NotBlank String houseNumber,
                         @NotBlank String municipality, @NotNull @Positive int postcode) {
}
