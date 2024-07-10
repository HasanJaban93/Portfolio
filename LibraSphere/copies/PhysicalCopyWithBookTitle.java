package be.hasan.libraSphere.copies;

public record PhysicalCopyWithBookTitle(String title, long id, boolean available,
                                        String barcode, String location, int shelfNumber) {
    PhysicalCopyWithBookTitle(PhysicalCopy physicalCopy){
        this(physicalCopy.getBook().getTitle(), physicalCopy.getId(), physicalCopy.isAvailable(),
                physicalCopy.getBarcode(), physicalCopy.getLocation(), physicalCopy.getShelfNumber());
    }
}
