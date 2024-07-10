package be.hasan.libraSphere.members;

import be.hasan.libraSphere.authors.Gender;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByFirstNameContainingOrderByFirstNameAscLastNameAsc(String word);
    List<Member> findByLastNameContainingOrderByLastNameAscFirstNameAsc(String word);
    List<Member> findByGenderOrderByFirstNameAscLastNameAsc(Gender gender);
    @Query("SELECT m FROM Member m Where m.address.municipality LIKE %:municipality% ORDER BY m.firstName ASC, m.lastName ASC")
    List<Member> findByMunicipality(String municipality);
   @EntityGraph(attributePaths = {"borrowedCopies", "borrowedCopies.copy"})
    Optional<Member> findBorrowedCopiesById(long id);
    boolean existsByEmail(String email);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT m FROM Member m WHERE m.id = :id")
    Optional<Member> findAndLockById(long id);

}
