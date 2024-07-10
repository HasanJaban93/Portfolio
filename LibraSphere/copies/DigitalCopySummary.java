package be.hasan.libraSphere.copies;

public record DigitalCopySummary(long id, boolean available, String fileHash,
                                 String fileFormat, String visitLink) implements CopySummary {
    public DigitalCopySummary(DigitalCopy digitalCopy){
        this(digitalCopy.getId(), digitalCopy.isAvailable(), digitalCopy.getFileHash(),
                digitalCopy.getFileFormat(), digitalCopy.getVisitLink());
    }
}
