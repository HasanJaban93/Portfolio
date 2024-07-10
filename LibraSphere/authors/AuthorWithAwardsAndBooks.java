package be.hasan.libraSphere.authors;

import be.hasan.libraSphere.books.BookSummary;

import java.util.List;
import java.util.Set;

public record AuthorWithAwardsAndBooks(long id, String firstName, String lastName, String biography,
                                       int yearOfBirth, Gender gender, String nationality, Set<Award> awards,
                                       List<BookSummary> bookSummaryList) {
    AuthorWithAwardsAndBooks(Author author){
        this(author.getId(), author.getFirstName(), author.getLastName(), author.getBiography(), author.getYearOfBirth(),
                author.getGender(),author.getNationality(), author.getAwards(),
                author.getBooks().stream().map(book -> new BookSummary(book)).toList());
    }
}
