package be.hasan.libraSphere.copies;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CopyRepository extends JpaRepository<Copy, Long> {
    @Query("SELECT d FROM DigitalCopy d")
    List<DigitalCopy> findDigitalCopies();
    @Query("SELECT p FROM PhysicalCopy p")
    List<PhysicalCopy> findPhysicalCopies();
    @Query("SELECT c FROM Copy c WHERE c.book.id = :bookId")
    List<Copy> findByBookId(long bookId);
    @Query("SELECT c FROM Copy c WHERE c.available = :available AND c.book.id = :bookId")
    List<Copy> findByAvailabilityAndBookId(boolean available, long bookId);
    @EntityGraph(attributePaths = {"borrowedCopies", "borrowedCopies.member"})
    Optional<Copy> findBorrowersById(long id);
    Optional<DigitalCopy> findByFileHash(String fileHash);
    @EntityGraph(attributePaths = {"book"})
    Optional<DigitalCopy> findWithBookByFileHash(String fileHash);
    Optional<PhysicalCopy> findByBarcode(String barcode);
    @EntityGraph(attributePaths = {"book"})
    Optional<PhysicalCopy> findWithBookByBarcode(String barcode);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM Copy c WHERE c.id = :id")
    Optional<Copy> findCopyAndLockById(long id);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT d FROM DigitalCopy d WHERE d.id = :id")
    Optional<DigitalCopy> findDigitalCopyAndLockById(long id);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM PhysicalCopy p WHERE p.id = :id")
    Optional<PhysicalCopy> findPhysicalCopyAndLockById(long id);
}
