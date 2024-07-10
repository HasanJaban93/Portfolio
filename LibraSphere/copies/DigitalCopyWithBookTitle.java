package be.hasan.libraSphere.copies;

public record DigitalCopyWithBookTitle(String title, long id, boolean available,
                                       String fileHash, String fileFormat, String visitLink) {
    DigitalCopyWithBookTitle(DigitalCopy digitalCopy){
        this(digitalCopy.getBook().getTitle(), digitalCopy.getId(), digitalCopy.isAvailable(),
                digitalCopy.getFileHash(), digitalCopy.getFileFormat(), digitalCopy.getVisitLink());
    }
}
