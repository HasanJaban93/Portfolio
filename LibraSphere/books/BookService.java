package be.hasan.libraSphere.books;

import be.hasan.libraSphere.authors.AuthorAlreadyExistsException;
import be.hasan.libraSphere.authors.AuthorNotFoundException;
import be.hasan.libraSphere.authors.AuthorRepository;
import be.hasan.libraSphere.copies.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CopyRepository copyRepository;

    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       CopyRepository copyRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.copyRepository = copyRepository;
    }

    public List<BookWithoutCopies> getAllBooksWithoutCopies(){
        return bookRepository
                .findAll(Sort.by("title"))
                .stream()
                .map(book -> new BookWithoutCopies(book)).toList();
    }

    public List<BookWithoutCopies> findByTitleContainingOrderByTitle(String woord){
        return bookRepository
                .findByTitleContainingOrderByTitle(woord)
                .stream()
                .map(book -> new BookWithoutCopies(book)).toList();
    }
    public List<BookWithoutCopies>findByGenreOrderByTitle(Genre genre){
        return bookRepository
                .findByGenreOrderByTitle(genre)
                .stream()
                .map(book -> new BookWithoutCopies(book)).toList();
    }
    List<BookWithoutCopies>findByYear(int year){
        return bookRepository
                .findByYear(year)
                .stream()
                .map(book -> new BookWithoutCopies(book)).toList();
    }
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    @Transactional
    long create(NewBook newBook) {
        boolean existedIsbn = bookRepository.existsByIsbn(newBook.isbn());
        if (existedIsbn){
            throw new BookAlreadyExistsException("This book has already been added.");
        }
        var book = new Book(newBook.title(), newBook.isbn(), newBook.genre(), newBook.publicationDate());
        bookRepository.save(book);
        return book.getId();
    }
    @Transactional
    void delete(long id) {
        var book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        var relatedAuthors = book.getAuthors().size();
        var relatedCopies = book.getCopies().size();
        if(relatedAuthors == 0 && relatedCopies == 0){
            bookRepository.deleteById(id);
        }else{
            throw new BookDeletionException(id);
        }
    }
    @Transactional
    void updateTitle(long id, String newTitle) {
        bookRepository.findAndLockById(id)
                      .orElseThrow(BookNotFoundException::new)
                      .setTitle(newTitle);
    }
    @Transactional
    void updateIsbn(long id, String newIsbn) {
        boolean existedIsbn = bookRepository.existsByIsbn(newIsbn);
        if (existedIsbn){
            throw new BookAlreadyExistsException("a book with the same ISBN already exists. ISBN must be unique");
        }
        bookRepository.findAndLockById(id)
                .orElseThrow(BookNotFoundException::new)
                .setIsbn(newIsbn);
    }
    @Transactional
    void updateGenre(long id, Genre newGenre) {
        bookRepository.findAndLockById(id)
                .orElseThrow(BookNotFoundException::new)
                .setGenre(newGenre);
    }
    @Transactional
    void updatePublicationDate(long id, LocalDate newPublicationDate) {
        bookRepository.findAndLockById(id)
                .orElseThrow(BookNotFoundException::new)
                .setPublicationDate(newPublicationDate);
    }
    @Transactional
    void addAuthor(long bookId, long authorId){
        var book = bookRepository.findAndLockById(bookId).orElseThrow(BookNotFoundException::new);
        var author = authorRepository.findAndLockById(authorId).orElseThrow(AuthorNotFoundException::new);
        book.addAuthor(author);
    }
    @Transactional
    void removeAuthor(long bookId, long authorId){
        var book = bookRepository.findAndLockById(bookId).orElseThrow(BookNotFoundException::new);
        var author = authorRepository.findById(authorId).orElseThrow(AuthorAlreadyExistsException::new);
        book.removeAuthor(author);
    }
    @Transactional
    void addDigitalCopy(long id, NewDigitalCopy newDigitalCopy){
        var book = bookRepository.findWithCopiesAndLockById(id).orElseThrow(BookNotFoundException::new);
        var existedDigitalCopy = copyRepository
                .findByFileHash(newDigitalCopy.fileHash());
        if (existedDigitalCopy.isEmpty()){
            var digitalCopy = new DigitalCopy(book, newDigitalCopy.fileHash(),
                    newDigitalCopy.fileFormat(), newDigitalCopy.visitLink());
            copyRepository.save(digitalCopy);
            book.addCopy(digitalCopy);
        }else{
            throw new FileHashAlreadyExistsException();
        }

    }
    @Transactional
    void addPhysicalCopy(long id, NewPhysicalCopy newPhysicalCopy){
        var book = bookRepository.findWithCopiesAndLockById(id).orElseThrow(BookNotFoundException::new);
        var existedPhysicalCopy = copyRepository
                .findByBarcode(newPhysicalCopy.barcode());
        if (existedPhysicalCopy.isEmpty()){
            var physicalCopy = new PhysicalCopy(book, newPhysicalCopy.barcode(),
                    newPhysicalCopy.location(), newPhysicalCopy.shelfNumber());
            copyRepository.save(physicalCopy);
            book.addCopy(physicalCopy);
        }else{
            throw new BarcodeAlreadyExistsException();
        }


    }
    @Transactional
    void removeCopy(long bookId, long copyId){
        var book = bookRepository.findWithCopiesAndLockById(bookId).orElseThrow(BookNotFoundException::new);
        var copy = copyRepository.findCopyAndLockById(copyId).orElseThrow(CopyNotFoundException::new);
        book.removeCopy(copy);  // Remove the copy from the book's set of copies
        copyRepository.deleteById(copyId);
    }
}
