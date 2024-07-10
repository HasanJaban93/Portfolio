package be.hasan.libraSphere.copies;

public record PhysicalCopySummary(long id, boolean available, String barcode,
                                  String location, int shelfNumber) implements CopySummary {
    public PhysicalCopySummary(PhysicalCopy physicalCopy){
        this(physicalCopy.getId(), physicalCopy.isAvailable(), physicalCopy.getBarcode(),
                physicalCopy.getLocation(), physicalCopy.getShelfNumber());
    }
}
