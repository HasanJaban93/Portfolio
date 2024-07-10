package be.hasan.libraSphere.copies;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CopyService {
    private final CopyRepository copyRepository;

    public CopyService(CopyRepository copyRepository) {
        this.copyRepository = copyRepository;
    }
    List<CopySummary> findAll(){
        return copyRepository.findAll().stream()
                .map(this::mapToCopySummary)
                .collect(Collectors.toList());
    }
    List<DigitalCopySummary> findDigitalCopies(){
        return copyRepository.findDigitalCopies()
                .stream()
                .map(copy -> new DigitalCopySummary(copy))
                .toList();
    }
    List<PhysicalCopySummary> findPhysicalCopies(){
        return copyRepository.findPhysicalCopies()
                .stream()
                .map(copy -> new PhysicalCopySummary(copy))
                .toList();
    }
    List<CopySummary> findByBookId(long bookId){
        return copyRepository.findByBookId(bookId).stream()
                .map(this::mapToCopySummary)
                .collect(Collectors.toList());
    }
    List<CopySummary> findByAvailabilityAndBookId(boolean available, long bookId){
        return copyRepository.findByAvailabilityAndBookId(available, bookId).stream()
                .map(this::mapToCopySummary)
                .collect(Collectors.toList());
    }
    DigitalCopyWithBookTitle findByFileHash(String fileHash){
        var digitalCopy = copyRepository.findWithBookByFileHash(fileHash).orElseThrow(CopyNotFoundException::new);
        return new DigitalCopyWithBookTitle(digitalCopy);
    }
    PhysicalCopyWithBookTitle findByBarcode(String barcode){
        var physicalCopy = copyRepository.findWithBookByBarcode(barcode).orElseThrow(CopyNotFoundException::new);
        return new PhysicalCopyWithBookTitle(physicalCopy);
    }
    CopyIdAndBorrowers findBorrowersByCopyId(long id){
        var copy = copyRepository.findBorrowersById(id).orElseThrow(CopyNotFoundException::new);
        var copyIdAndBorrowers = new CopyIdAndBorrowers(copy);
        return copyIdAndBorrowers;
    }
    @Transactional
    void updateFileHash(long id, String fileHash){
        var existedFileHash = copyRepository.findByFileHash(fileHash);
        if(existedFileHash.isPresent()){
            throw new FileHashAlreadyExistsException();
        }
        var digitalCopy = copyRepository.findDigitalCopyAndLockById(id)
                .orElseThrow(CopyNotFoundException::new);
        digitalCopy.setFileHash(fileHash);
    }
    @Transactional
    void updateFileFormat(long id, String fileFormat){
        var digitalCopy = copyRepository.findDigitalCopyAndLockById(id)
                .orElseThrow(CopyNotFoundException::new);
        digitalCopy.setFileFormat(fileFormat);
    }
    @Transactional
    void updateVisitLink(long id, String visitLink){
        var digitalCopy = copyRepository.findDigitalCopyAndLockById(id)
                .orElseThrow(CopyNotFoundException::new);
        digitalCopy.setVisitLink(visitLink);

    }
    @Transactional
    void updateBarcode(long id, String barcode){
        var existedBarcode = copyRepository.findByBarcode(barcode);
        if (existedBarcode.isPresent()){
            throw new BarcodeAlreadyExistsException();
        }
        var physicalCopy = copyRepository.findPhysicalCopyAndLockById(id)
                .orElseThrow(CopyNotFoundException::new);
        physicalCopy.setBarcode(barcode);
    }
    @Transactional
    void updateLocation(long id, String location){
        var physicalCopy = copyRepository.findPhysicalCopyAndLockById(id)
                .orElseThrow(CopyNotFoundException::new);
        physicalCopy.setLocation(location);
    }
    @Transactional
    void updateShelfNumber(long id, int shelfNumber){
        var physicalCopy = copyRepository.findPhysicalCopyAndLockById(id)
                .orElseThrow(CopyNotFoundException::new);
        physicalCopy.setShelfNumber(shelfNumber);
    }



    private CopySummary mapToCopySummary(Copy copy) {
        if (copy instanceof DigitalCopy) {
            return new DigitalCopySummary((DigitalCopy) copy);
        } else {
            return new PhysicalCopySummary((PhysicalCopy) copy);
        }
    }
}
