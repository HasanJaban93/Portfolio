package be.hasan.libraSphere.books;


import be.hasan.libraSphere.authors.Author;
import be.hasan.libraSphere.authors.AuthorAlreadyExistsException;
import be.hasan.libraSphere.copies.Copy;
import be.hasan.libraSphere.copies.CopyDeletionException;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String isbn;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private LocalDate publicationDate;
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private Set<Copy> copies; //Each book can have many copies.
    @ManyToMany
    @JoinTable(
            name = "booksauthors",
            joinColumns = @JoinColumn(name = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "authorId"))
    private Set<Author> authors; // Each book can be written by many authors.
    protected Book(){};

    public Book(String title, String isbn, Genre genre, LocalDate publicationDate) {
        this.title = title;
        this.isbn = isbn;
        this.genre = genre;
        this.publicationDate = publicationDate;
        this.copies = new LinkedHashSet<Copy>();
        this.authors = new LinkedHashSet<Author>();
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public Genre getGenre() {
        return genre;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public Set<Copy> getCopies() {
        return Collections.unmodifiableSet(copies);
    }

    public Set<Author> getAuthors() {
        return Collections.unmodifiableSet(authors);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void addCopy(Copy copy) {
        if (!copies.add(copy)) {
            throw new CopyAlreadyExistsException();
        }
    }
    public void removeCopy(Copy copy){
        if (!copies.remove(copy)) {
            throw new CopyDeletionException(copy.getId());
        }
    }
    public void addAuthor(Author author) {
        if (!authors.add(author)) {
            throw new AuthorAlreadyExistsException();
        }
    }
    public void removeAuthor(Author author){
        authors.remove(author);
    }
    @Override
    public boolean equals(Object object) {
        return object instanceof Book book &&
                isbn.equalsIgnoreCase(book.isbn);
    }
    @Override
    public int hashCode() {
        return isbn.toLowerCase().hashCode();
    }
}
