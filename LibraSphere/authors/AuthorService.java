package be.hasan.libraSphere.authors;


import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AuthorService {
    private final AuthorRepository authorRepository;
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public List<AuthorSummary> getAllAuthors(){
        return authorRepository.findAll(Sort.by("firstName","lastName"))
                .stream()
                .map(author -> new AuthorSummary(author))
                .toList();
    }
    public List<AuthorSummary> findByFirstName(String word){
        return authorRepository.findByFirstNameContainingOrderByFirstNameAscLastNameAsc(word)
                .stream()
                .map(author -> new AuthorSummary(author))
                .toList();
    }
    public List<AuthorSummary> findByLastName(String word){
        return authorRepository.findByLastNameContainingOrderByLastNameAscFirstNameAsc(word)
                .stream()
                .map(author -> new AuthorSummary(author))
                .toList();
    }
    public List<AuthorSummary> findByYearOfBirth(int yearOfBirth){
        return authorRepository.findByYearOfBirthOrderByFirstNameAscLastNameAsc(yearOfBirth)
                .stream()
                .map(author -> new AuthorSummary(author))
                .toList();
    }
    public List<AuthorSummary> findByGender(Gender gender){
        return authorRepository.findByGenderOrderByFirstNameAscLastNameAsc(gender)
                .stream()
                .map(author -> new AuthorSummary(author))
                .toList();
    }
    public List<AuthorSummary> findByNationality(String word){
        return authorRepository.findByNationalityContainingOrderByFirstNameAscLastNameAsc(word)
                .stream()
                .map(author -> new AuthorSummary(author))
                .toList();
    }
    public AuthorWithAwardsAndBooks findById(long id){
        var author = authorRepository.findById(id).orElseThrow(AuthorNotFoundException::new);
        return new AuthorWithAwardsAndBooks(author);
    }
    @Transactional
    long create(NewAuthor newAuthor) {
        boolean existedAuthor = authorRepository
                .existsByFirstNameAndLastName(newAuthor.firstName(),newAuthor.lastName());
        if (existedAuthor){
            throw new AuthorAlreadyExistsException();
        }
        var author = new Author(newAuthor.firstName(),newAuthor.lastName(),
                newAuthor.yearOfBirth(),newAuthor.gender(), newAuthor.nationality());
        authorRepository.save(author);
        return author.getId();
    }
    @Transactional
    void delete(long id) {
        var author = authorRepository.findAndLockById(id).orElseThrow(AuthorNotFoundException::new);
        var relatedBooks = author.getBooks().size();
        if(relatedBooks == 0){
            authorRepository.deleteById(id);
        }else{
            throw new AuthorDeletionException(id);
        }
    }
    @Transactional
    void updateFirstName(long id, String newFirstName) {
        authorRepository.findAndLockById(id)
                .orElseThrow(AuthorNotFoundException::new)
                .setFirstName(newFirstName);
    }
    @Transactional
    void updateLastName(long id, String newLastName) {
        authorRepository.findAndLockById(id)
                .orElseThrow(AuthorNotFoundException::new)
                .setLastName(newLastName);
    }
    @Transactional
    void updateBiography(long id, String newBiography) {
        authorRepository.findAndLockById(id)
                .orElseThrow(AuthorNotFoundException::new)
                .setBiography(newBiography);
    }
    @Transactional
    void updateNationality(long id, String newNationality) {
        authorRepository.findAndLockById(id)
                .orElseThrow(AuthorNotFoundException::new)
                .setNationality(newNationality);
    }
    @Transactional
    void updateYearOfBirth(long id, int newYearOfBirth) {
        authorRepository.findAndLockById(id)
                .orElseThrow(AuthorNotFoundException::new)
                .setYearOfBirth(newYearOfBirth);
    }
    @Transactional
    void updateGender(long id, Gender gender) {
        authorRepository.findAndLockById(id)
                .orElseThrow(AuthorNotFoundException::new)
                .setGender(gender);
    }
    @Transactional
    void addAward(long id, NewAward newAward){
        var award = new Award(newAward.name(), newAward.year());
        authorRepository.findWithAwardsAndLockById(id)
                .orElseThrow(AuthorNotFoundException::new)
                .addAward(award);
    }
    @Transactional
    void removeAward(long id, NewAward newAward){
        var award = new Award(newAward.name(), newAward.year());
        authorRepository.findWithAwardsAndLockById(id)
                .orElseThrow(AuthorNotFoundException::new)
                .removeAward(award);
    }
}
