package be.hasan.libraSphere.copies;

import jakarta.validation.constraints.NotBlank;

public record NewDigitalCopy(@NotBlank String fileHash, @NotBlank String fileFormat,
                             @NotBlank String visitLink) {
}
