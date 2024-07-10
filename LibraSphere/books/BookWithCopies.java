package be.hasan.libraSphere.books;

import be.hasan.libraSphere.authors.AuthorSummary;
import be.hasan.libraSphere.copies.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record BookWithCopies(long id, String title, String isbn, Genre genre,
                             LocalDate publicationDate,
                             List<CopySummary> copySummaries,
                             List<AuthorSummary> authors) {
    BookWithCopies(Book book){
        this(book.getId(), book.getTitle(), book.getIsbn(), book.getGenre(), book.getPublicationDate(),
                book.getCopies()
                        .stream()
                        .map(copy -> {
                            if (copy instanceof DigitalCopy){
                                return new DigitalCopySummary((DigitalCopy) copy);
                            }else {
                                return new PhysicalCopySummary((PhysicalCopy) copy);
                            }
                        })
                        .collect(Collectors.toList()),
                book.getAuthors().stream().map(author -> new AuthorSummary(author)).toList());
    }
}
