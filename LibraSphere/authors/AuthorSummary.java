package be.hasan.libraSphere.authors;

public record AuthorSummary(long id, String firstName, String lastName, String nationality) {
   public AuthorSummary(Author author){
        this(author.getId(), author.getFirstName(), author.getLastName(), author.getNationality());
    }
}
