package be.hasan.libraSphere.books;

import java.time.LocalDate;

public record BookSummary(long id, String title, String isbn,
                          Genre genre, LocalDate publicationDate) {
    public BookSummary(Book book){
        this(book.getId(), book.getTitle(), book.getIsbn(), book.getGenre(), book.getPublicationDate());
    }
}
