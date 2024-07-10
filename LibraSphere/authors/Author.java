package be.hasan.libraSphere.authors;

import be.hasan.libraSphere.books.Book;
import be.hasan.libraSphere.books.BookAlreadyExistsException;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name="authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private int yearOfBirth;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String biography;
    @ElementCollection
    @CollectionTable(name = "awards",
            joinColumns = @JoinColumn(name = "authorId"))
    @OrderBy("year")
    private Set<Award> awards;     //awards is a collection of custom value object.
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;  // Each author can write many books.
    protected Author(){}
    public Author(String firstName, String lastName, int yearOfBirth, Gender gender, String nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.gender = gender;
        this.nationality = nationality;
        this.awards = new LinkedHashSet<Award>();
        this.books = new LinkedHashSet<Book>();
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }
    public Gender getGender() {
        return gender;
    }
    public String getNationality() {
        return nationality;
    }

    public String getBiography() {
        return biography;
    }

    public Set<Award> getAwards() {
        return Collections.unmodifiableSet(awards);
    }
    public Set<Book> getBooks() {
        return Collections.unmodifiableSet(books);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void addAward(Award award) {
        if (!awards.add(award)) {
            throw new AwardAlreadyGrantedException();
        }
    }
    public void removeAward(Award award){
        awards.remove(award);
    }
    public void addBook(Book book){
        if (!books.add(book)) {
            throw new BookAlreadyExistsException("The book is already added to this author's collection");
        }

    }
    public void removeBook(Book book){
        books.remove(book);
    }
    @Override
    public boolean equals(Object object) {
        return object instanceof Author author &&
                firstName.equalsIgnoreCase(author.firstName) &&
                lastName.equalsIgnoreCase(author.lastName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstName.toLowerCase(), lastName.toLowerCase());
    }
}
