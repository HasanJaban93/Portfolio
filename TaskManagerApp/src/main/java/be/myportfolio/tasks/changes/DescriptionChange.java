package be.myportfolio.tasks.changes;

import jakarta.validation.constraints.NotBlank;

public record DescriptionChange(@NotBlank String description) {
}
