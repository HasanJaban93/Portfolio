package be.hasan.libraSphere.copies;

import be.hasan.libraSphere.books.Book;
import be.hasan.libraSphere.borrowedCopies.BorrowedCopy;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="copies")
@DiscriminatorColumn(name = "type")
public abstract class Copy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean available;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId")
    private Book book; //Reference to the Book object this copy belongs to(Every copy belongs to one book only).
    @OneToMany(mappedBy = "copy")
    private Set<BorrowedCopy> borrowedCopies;
    protected Copy(){}

    public Copy(Book book) {
        this.available = true;
        this.book = book;
        this.borrowedCopies =  new LinkedHashSet<BorrowedCopy>();
    }

    public long getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Book getBook() {
        return book;
    }
    public Set<BorrowedCopy> getBorrowedCopies() {
        return Collections.unmodifiableSet(borrowedCopies);
    }

}
