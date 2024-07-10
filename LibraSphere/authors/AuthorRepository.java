package be.hasan.libraSphere.authors;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByFirstNameContainingOrderByFirstNameAscLastNameAsc(String word);
    List<Author> findByLastNameContainingOrderByLastNameAscFirstNameAsc(String word);
    List<Author> findByYearOfBirthOrderByFirstNameAscLastNameAsc(int yearOfBirth);
    List<Author> findByGenderOrderByFirstNameAscLastNameAsc(Gender gender);
    List<Author> findByNationalityContainingOrderByFirstNameAscLastNameAsc(String word);

    boolean existsByFirstNameAndLastName(String firstName, String lastName);
    @EntityGraph(attributePaths = {"awards", "books"})
    Optional<Author> findById(long id);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Author a WHERE a.id = :id")
    Optional<Author> findAndLockById(long id);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @EntityGraph(attributePaths = {"awards"})
    Optional<Author> findWithAwardsAndLockById(long id);
}
