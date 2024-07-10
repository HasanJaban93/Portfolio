package be.hasan.libraSphere.books;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    /* When utilizing JPQL to fetch the authors attribute in a single query using join fetch
         to resolve the N+1 problem, I forego the ability to employ derived queries.
       By employing the @EntityGraph, I regain the ability to leverage derived queries.
       Additionally, I have incorporated the Sort parameter to facilitate sorting by specific attributes,
         such as title, in the service layer.*/

    @EntityGraph(attributePaths = "authors")
    List<Book> findAll(Sort sort);
    @EntityGraph(attributePaths = "authors")
    List<Book>findByTitleContainingOrderByTitle(String woord);
    @EntityGraph(attributePaths = "authors")
    List<Book>findByGenreOrderByTitle(Genre genre);
    /*
    JPQL query to select Book entities, eagerly fetching authors,
    filtered by the year part of the publicationDate attribute,
    and ordered by title.
    The method findByYear(int year) requires a custom JPQL query
    because it involves extracting a year from a date attribute (FUNCTION('YEAR', b.publicationDate)).
    This functionality is beyond the capabilities of standard derived queries provided by Spring Data JPA.
     */
    @Query("SELECT b FROM Book b JOIN FETCH b.authors WHERE FUNCTION('YEAR', b.publicationDate) = :year ORDER BY b.title")
    List<Book> findByYear(int year);
    @EntityGraph(attributePaths = {"authors", "copies"})
    Optional<Book> findById(long id);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT b FROM Book b WHERE b.id = :id")
    Optional<Book> findAndLockById(long id);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @EntityGraph(attributePaths = {"copies"})
    Optional<Book> findWithCopiesAndLockById(long id);
    boolean existsByIsbn(String isbn);

}
