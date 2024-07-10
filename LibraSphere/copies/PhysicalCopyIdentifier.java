package be.hasan.libraSphere.copies;

public record PhysicalCopyIdentifier(String barcode) implements CopyTypeIdentifier {
    public PhysicalCopyIdentifier(PhysicalCopy physicalCopy){
        this(physicalCopy.getBarcode());
    }
}
