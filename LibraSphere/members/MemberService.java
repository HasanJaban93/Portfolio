package be.hasan.libraSphere.members;

import be.hasan.libraSphere.authors.Gender;
import be.hasan.libraSphere.books.Book;
import be.hasan.libraSphere.books.BookNotFoundException;
import be.hasan.libraSphere.books.BookRepository;
import be.hasan.libraSphere.borrowedCopies.*;
import be.hasan.libraSphere.copies.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final BorrowedCopyRepository borrowedCopyRepository;
    public MemberService(MemberRepository memberRepository, BookRepository bookRepository, BorrowedCopyRepository borrowedCopyRepository) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.borrowedCopyRepository = borrowedCopyRepository;
    }
    List<MemberSummary> findAll(){
        return memberRepository.findAll(Sort.by("firstName", "lastName"))
                .stream()
                .map(member -> new MemberSummary(member))
                .toList();
    }
    List<MemberSummary> findByFirstName(String firstName){
        return memberRepository.findByFirstNameContainingOrderByFirstNameAscLastNameAsc(firstName)
                .stream()
                .map(member -> new MemberSummary(member))
                .toList();
    }
    List<MemberSummary> findByLastName(String lastName){
        return memberRepository.findByLastNameContainingOrderByLastNameAscFirstNameAsc(lastName)
                .stream()
                .map(member -> new MemberSummary(member))
                .toList();
    }
    List<MemberSummary> findByGender(Gender gender){
        return memberRepository.findByGenderOrderByFirstNameAscLastNameAsc(gender)
                .stream()
                .map(member -> new MemberSummary(member))
                .toList();
    }
    List<MemberSummary> findByMunicipality(String municipality){
        return memberRepository.findByMunicipality(municipality)
                .stream()
                .map(member -> new MemberSummary(member))
                .toList();
    }
    MemberWithoutBorrowedCopies findByIdWithoutBorrowedCopies(long id){
        var member = memberRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new);
        var memberWithoutBorrowedCopies = new MemberWithoutBorrowedCopies(member);
        return memberWithoutBorrowedCopies;
    }
    MemberIdAndBorrowedCopies findBorrowedCopiesByMemberId(long id){
        var member = memberRepository.findBorrowedCopiesById(id)
                .orElseThrow(MemberNotFoundException::new);
        var memberIdAndBorrowedCopies = new MemberIdAndBorrowedCopies(member);
        return memberIdAndBorrowedCopies;
    }
    @Transactional
    long create(NewMember newMember) {
        boolean existedMember = memberRepository
                .existsByEmail(newMember.email());
        if (existedMember){
            throw new MemberAlreadyExistsException();
        }
        var address = new Address(newMember.street(), newMember.houseNumber(),
                newMember.municipality(), newMember.postcode());
        var member = new Member(newMember.firstName(), newMember.lastName(),
                newMember.dateOfBirth(), newMember.gender(), newMember.email(),newMember.phoneNumber(),
                address);
        memberRepository.save(member);
        return member.getId();
    }
    @Transactional
    void delete(long id) {
        var member = memberRepository.findAndLockById(id).orElseThrow(MemberNotFoundException::new);
        var relatedBorrowedCopies = member.getBorrowedCopies().size();
        if(relatedBorrowedCopies == 0){
            memberRepository.deleteById(id);
        }else{
            throw new MemberDeletionException(id);
        }
    }
    @Transactional
    CopySummaryWithBorrowKey borrowCopy(long id, NewBorrowedCopy newBorrowedCopy) {
        validateBorrowPeriod(newBorrowedCopy.borrowDate(), newBorrowedCopy.returnDate());

        var member = memberRepository.findAndLockById(id)
                .orElseThrow(MemberNotFoundException::new);

        var book = bookRepository.findWithCopiesAndLockById(newBorrowedCopy.bookId())
                .orElseThrow(BookNotFoundException::new);

        var requiredCopyType = newBorrowedCopy.requiredCopyType();
        Copy toBorrowCopy = findAvailableCopy(book, requiredCopyType);

        var borrowedCopy = new BorrowedCopy(newBorrowedCopy.borrowDate(), newBorrowedCopy.returnDate(), member, toBorrowCopy);
        borrowedCopyRepository.save(borrowedCopy);
        toBorrowCopy.setAvailable(false);

        var borrowKey = borrowedCopy.getId();
        return createCopySummaryWithBorrowKey(toBorrowCopy, borrowKey);
    }
    @Transactional
    void returnBorrowedCopy(long borrowKey){
        var borrowedCopy = borrowedCopyRepository
                .findById(borrowKey)
                .orElseThrow(BorrowedCopyNotFoundException::new);
        var copy = borrowedCopy.getCopy();
        copy.setAvailable(true);
        borrowedCopy.setReturnDate(LocalDate.now());
    }
    @Transactional
    void updateFirstName(long id, String firstName){
        var member = memberRepository.findAndLockById(id)
                .orElseThrow(MemberNotFoundException::new);
                member.setFirstName(firstName);
    }
    @Transactional
    void updateLastName(long id, String lastName){
        var member = memberRepository.findAndLockById(id)
                .orElseThrow(MemberNotFoundException::new);
        member.setLastName(lastName);
    }
    @Transactional
    void updateDateOfBirth(long id, LocalDate dateOfBirth){
        var member = memberRepository.findAndLockById(id)
                .orElseThrow(MemberNotFoundException::new);
        member.setDateOfBirth(dateOfBirth);
    }
    @Transactional
    void updateGender(long id, Gender gender){
        var member = memberRepository.findAndLockById(id)
                .orElseThrow(MemberNotFoundException::new);
        member.setGender(gender);
    }
    @Transactional
    void updateEmail(long id, String email){
        var existedEmail = memberRepository.existsByEmail(email);
        if(existedEmail){
            throw new EmailAlreadyExistsException();
        }
        var member = memberRepository.findAndLockById(id)
                .orElseThrow(MemberNotFoundException::new);
        member.setEmail(email);
    }
    @Transactional
    void updatePhoneNumber(long id, String phoneNumber){
        var member = memberRepository.findAndLockById(id)
                .orElseThrow(MemberNotFoundException::new);
        member.setPhoneNumber(phoneNumber);
    }
    @Transactional
    void updateMembershipDate(long id, LocalDate membershipDate){
        var member = memberRepository.findAndLockById(id)
                .orElseThrow(MemberNotFoundException::new);
        member.setMembershipDate(membershipDate);
    }
    @Transactional
    void updateAddress(long id, NewAddress newAddress){
        var member = memberRepository.findAndLockById(id)
                .orElseThrow(MemberNotFoundException::new);
        var address = new Address(newAddress.street(), newAddress.houseNumber(),
                newAddress.municipality(), newAddress.postcode());
        member.setAddress(address);
    }
    @Transactional
    void updateStreet(long id, String street){
        var member = memberRepository.findAndLockById(id)
                .orElseThrow(MemberNotFoundException::new);
        member.getAddress().setStreet(street);
    }
    @Transactional
    void updateHouseNumber(long id, String houseNumber){
        var member = memberRepository.findAndLockById(id)
                .orElseThrow(MemberNotFoundException::new);
        member.getAddress().setHouseNumber(houseNumber);
    }
    @Transactional
    void updateMunicipality(long id, String municipality){
        var member = memberRepository.findAndLockById(id)
                .orElseThrow(MemberNotFoundException::new);
        member.getAddress().setMunicipality(municipality);
    }
    @Transactional
    void updatePostcode(long id, int postcode){
        var member = memberRepository.findAndLockById(id)
                .orElseThrow(MemberNotFoundException::new);
        member.getAddress().setPostcode(postcode);
    }

    private void validateBorrowPeriod(LocalDate borrowDate, LocalDate returnDate) {
        if (returnDate.isAfter(borrowDate.plusMonths(1))) {
            throw new BorrowingPeriodTooLongException();
        }
    }

    private Copy findAvailableCopy(Book book, RequiredCopyType requiredCopyType) {
        var copies = book.getCopies().stream()
                .filter(Copy::isAvailable)
                .collect(Collectors.partitioningBy(copy -> copy instanceof DigitalCopy));

        List<Copy> availableDigitalCopies = copies.get(true);
        List<Copy> availablePhysicalCopies = copies.get(false);

        if (RequiredCopyType.DIGITAL.equals(requiredCopyType)) {
            if (availableDigitalCopies.isEmpty()) {
                throw new NoAvailableCopiesException("No available digital copies found");
            }
            return availableDigitalCopies.get(0);
        } else {
            if (availablePhysicalCopies.isEmpty()) {
                throw new NoAvailableCopiesException("No available physical copies found");
            }
            return availablePhysicalCopies.get(0);
        }
    }

    private CopySummaryWithBorrowKey createCopySummaryWithBorrowKey(Copy copy, long borrowKey) {
        if (copy instanceof DigitalCopy) {
            var digitalCopySummary = new DigitalCopySummary((DigitalCopy) copy);
            return new DigitalCopySummaryWithBorrowKey(digitalCopySummary, borrowKey);
        } else {
            var physicalCopySummary = new PhysicalCopySummary((PhysicalCopy) copy);
            return new PhysicalCopySummaryWithBorrowKey(physicalCopySummary, borrowKey);
        }
    }
}
