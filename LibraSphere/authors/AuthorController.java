package be.hasan.libraSphere.authors;

import be.hasan.libraSphere.books.NewBook;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping
    public List<AuthorSummary> getAllAuthors(){
        return authorService.getAllAuthors();
    }
    @GetMapping(params = "firstName")
    List<AuthorSummary> findByFirstName(String firstName){
        return authorService.findByFirstName(firstName);
    }
    @GetMapping(params = "lastName")
    List<AuthorSummary> findByLastName(String lastName){
        return authorService.findByLastName(lastName);
    }
    @GetMapping(params = "yearOfBirth")
    List<AuthorSummary> findByYearOfBirth(int yearOfBirth){
        return authorService.findByYearOfBirth(yearOfBirth);
    }
    @GetMapping(params = "gender")
    List<AuthorSummary> findByGender(Gender gender){return authorService.findByGender(gender);}
    @GetMapping(params = "nationality")
    List<AuthorSummary> findByNationality(String nationality){
        return authorService.findByNationality(nationality);
    }
    @GetMapping("{id}")
    AuthorWithAwardsAndBooks findById(@PathVariable long id){
        return authorService.findById(id);
    }
    @PostMapping
    long create(@RequestBody @Valid NewAuthor newAuthor) {
        return authorService.create(newAuthor);
    }
    @DeleteMapping("{id}")
    void delete(@PathVariable long id) {
        authorService.delete(id);
    }
    @PatchMapping("{id}/firstName")
    void updateFirstName(@PathVariable long id,
                     @RequestBody @NotBlank String newFristName){
        authorService.updateFirstName(id, newFristName);
    }
    @PatchMapping("{id}/lastName")
    void updateLastName(@PathVariable long id,
                         @RequestBody @NotBlank String newLastName){
        authorService.updateLastName(id, newLastName);
    }
    @PatchMapping("{id}/biography")
    void updateBiography(@PathVariable long id,
                        @RequestBody @NotBlank String newBiography){
        authorService.updateBiography(id, newBiography);
    }
    @PatchMapping("{id}/nationality")
    void updateNationality(@PathVariable long id,
                         @RequestBody @NotBlank String newNationality){
        authorService.updateNationality(id, newNationality);
    }
    @PatchMapping("{id}/yearOfBirth")
    void updateYearOfBirth(@PathVariable long id,
                           @RequestBody @NotNull @Positive int newYearOfBirth){
        authorService.updateYearOfBirth(id, newYearOfBirth);
    }
    @PatchMapping("{id}/gender")
    void updateGender(@PathVariable long id, @RequestBody @NotNull Gender gender){
        authorService.updateGender(id, gender);
    }
    @PatchMapping("{id}/addAward")
    void addAward(@PathVariable long id,
                           @RequestBody @Valid NewAward newAward){
        authorService.addAward(id, newAward);
    }
    @PatchMapping("{id}/removeAward")
    void removeAward(@PathVariable long id,
                  @RequestBody @Valid NewAward newAward){
        authorService.removeAward(id, newAward);
    }
}
