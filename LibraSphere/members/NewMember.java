package be.hasan.libraSphere.members;

import be.hasan.libraSphere.authors.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record NewMember(@NotBlank String firstName, @NotBlank String lastName,
                        @NotNull @Past LocalDate dateOfBirth, @NotNull Gender gender,
                        @NotBlank @Email String email,
                        @NotBlank String phoneNumber, @NotBlank String street, @NotBlank String houseNumber,
                        @NotBlank String municipality, @NotNull @Positive int postcode) {
}
