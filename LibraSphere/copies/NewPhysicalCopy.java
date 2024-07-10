package be.hasan.libraSphere.copies;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NewPhysicalCopy(@NotBlank String barcode, @NotBlank String location,
                              @NotNull @Positive int shelfNumber) {
}
