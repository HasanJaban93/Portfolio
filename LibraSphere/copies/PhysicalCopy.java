package be.hasan.libraSphere.copies;


import be.hasan.libraSphere.books.Book;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PhysicalCopy")
public class PhysicalCopy extends Copy {
    private String barcode;
    private String location;
    private int shelfNumber;
    protected PhysicalCopy(){}

    public PhysicalCopy(Book book, String barcode, String location, int shelfNumber) {
        super(book);
        this.barcode = barcode;
        this.location = location;
        this.shelfNumber = shelfNumber;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getLocation() {
        return location;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof PhysicalCopy physicalCopy &&
                barcode.equalsIgnoreCase(physicalCopy.barcode);
    }
    @Override
    public int hashCode() {
        return barcode.toLowerCase().hashCode();
    }
}
