package be.hasan.libraSphere.books;

import be.hasan.libraSphere.authors.AuthorSummary;

import java.time.LocalDate;
import java.util.List;

public record BookWithoutCopies(long id, String title, String isbn, Genre genre,
                                LocalDate publicationDate, List<AuthorSummary> authors) {
    BookWithoutCopies(Book book){
        this(book.getId(), book.getTitle(), book.getIsbn(),
                book.getGenre(), book.getPublicationDate(),
                book.getAuthors().stream().map(author -> new AuthorSummary(author)).toList());
    }
}
