package be.hasan.libraSphere.copies;

public record DigitalCopyIdentifier(String fileHash) implements CopyTypeIdentifier{
    public DigitalCopyIdentifier(DigitalCopy digitalCopy){
        this(digitalCopy.getFileHash());
    }
}
