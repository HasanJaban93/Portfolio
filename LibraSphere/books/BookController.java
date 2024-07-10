package be.hasan.libraSphere.books;


import be.hasan.libraSphere.authors.NewAuthor;
import be.hasan.libraSphere.copies.NewDigitalCopy;
import be.hasan.libraSphere.copies.NewPhysicalCopy;
import be.hasan.libraSphere.validation.ISBN;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    List<BookWithoutCopies> getAllBooksWithoutCopies() {
        return bookService.getAllBooksWithoutCopies();
    }
    @GetMapping(params = "title")
    List<BookWithoutCopies> findByTitleContainingOrderByTitle(String title){
        return bookService.findByTitleContainingOrderByTitle(title);
    }
    @GetMapping(params = "genre")
    List<BookWithoutCopies>findByGenreContainingOrderByTitle(Genre genre){
        return bookService.findByGenreOrderByTitle(genre);
    }
    @GetMapping(params = "publicationYear")
    List<BookWithoutCopies> findByYear(int publicationYear){
        return bookService.findByYear(publicationYear);
    }
    @GetMapping("{id}")
    BookWithCopies findById(@PathVariable long id) {
        var book = bookService.findById(id).orElseThrow(BookNotFoundException::new);
        return new BookWithCopies(book);
    }
    @PostMapping
    long create(@RequestBody @Valid NewBook newBook) {
        return bookService.create(newBook);
    }
    @DeleteMapping("{id}")
    void delete(@PathVariable long id) {
            bookService.delete(id);
    }
    @PatchMapping("{id}/title")
    void updateTitle(@PathVariable long id,
                     @RequestBody @NotBlank String newTitle){
        bookService.updateTitle(id, newTitle);
    }
    @PatchMapping("{id}/isbn")
    void updateIsbn(@PathVariable long id,
                     @RequestBody @ISBN String newIsbn){
        bookService.updateIsbn(id, newIsbn);
    }
    @PatchMapping("{id}/genre")
    void updateGenre(@PathVariable long id,
                    @RequestBody @NotNull Genre newGenre){
        bookService.updateGenre(id, newGenre);
    }
    @PatchMapping("{id}/publicationDate")
    void updatePublicationDate(@PathVariable long id,
                     @RequestBody @NotNull @PastOrPresent LocalDate newPublicationDate){
        bookService.updatePublicationDate(id, newPublicationDate);
    }
    @PatchMapping(value = "{id}/addAuthor")
    void addAuthor(@PathVariable long id, @RequestBody @NotNull @Positive long authorId){
        bookService.addAuthor(id, authorId);
    }
    @PatchMapping(value = "{id}/removeAuthor")
    void removeAuthor(@PathVariable long id, @RequestBody @NotNull @Positive long authorId){
        bookService.removeAuthor(id, authorId);
    }
    @PatchMapping("{id}/addDigitalCopy")
    void addDigitalCopy(@PathVariable long id, @RequestBody @Valid NewDigitalCopy newDigitalCopy){
        bookService.addDigitalCopy(id,newDigitalCopy);
    }
    @PatchMapping("{id}/addPhysicalCopy")
    void addPhysicalCopy(@PathVariable long id, @RequestBody @Valid NewPhysicalCopy newPhysicalCopy){
        bookService.addPhysicalCopy(id,newPhysicalCopy);
    }
    @PatchMapping(value = "{id}/removeCopy")
    void removeCopy(@PathVariable long id, @RequestBody @NotNull @Positive long copyId){
        bookService.removeCopy(id, copyId);
    }
}
