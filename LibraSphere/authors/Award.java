package be.hasan.libraSphere.authors;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable  //Award is a custom value object class.
public class Award {
    private String name;
    private int year;
    protected Award(){}

    public Award(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }
    @Override
    public boolean equals(Object object) {
        return object instanceof Award award &&
                name.equalsIgnoreCase(award.name) &&
                year == award.year;
    }
    @Override
    public int hashCode() {

        return Objects.hash(name.toLowerCase(), year);
    }
}
