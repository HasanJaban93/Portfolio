package be.hasan.libraSphere.borrowedCopies;

import be.hasan.libraSphere.copies.Copy;
import be.hasan.libraSphere.members.Member;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="borrowedcopies")
public class BorrowedCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "copyId")
    private Copy copy;
    protected BorrowedCopy() {
    }

    public BorrowedCopy(LocalDate borrowDate, LocalDate returnDate, Member member, Copy copy) {
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.member = member;
        this.copy = copy;
    }

    public long getId() {
        return id;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public Member getMember() {
        return member;
    }

    public Copy getCopy() {
        return copy;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof BorrowedCopy borrowedCopy &&
                member.equals(borrowedCopy.member) &&
                copy.equals(borrowedCopy.copy) &&
                borrowDate.equals(borrowedCopy.borrowDate);
    }
    @Override
    public int hashCode() {
        return Objects.hash(member, copy, borrowDate);
    }
}
