package be.hasan.libraSphere.books;

import be.hasan.libraSphere.validation.ISBN;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record NewBook(@NotBlank String title, @ISBN String isbn, @NotNull Genre genre,
                      @NotNull @PastOrPresent LocalDate publicationDate) {
}
