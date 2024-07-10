package be.hasan.libraSphere.copies;

import be.hasan.libraSphere.books.Book;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DigitalCopy")
public class DigitalCopy extends Copy {
    private String fileHash;
    private String fileFormat;
    private String visitLink;
    protected DigitalCopy(){}

    public DigitalCopy(Book book, String fileHash, String fileFormat, String visitLink) {
        super(book);
        this.fileHash = fileHash;
        this.fileFormat = fileFormat;
        this.visitLink = visitLink;
    }

    public String getFileHash() {
        return fileHash;
    }
    public String getFileFormat() {
        return fileFormat;
    }
    public String getVisitLink() {
        return visitLink;
    }
    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }
    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }
    public void setVisitLink(String visitLink) {
        this.visitLink = visitLink;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof DigitalCopy digitalCopy &&
                fileHash.equalsIgnoreCase(digitalCopy.fileHash);
    }
    @Override
    public int hashCode() {
        return fileHash.toLowerCase().hashCode();
    }
}
