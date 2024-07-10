package be.hasan.libraSphere.copies;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("copies")
public class CopyController {
    private final CopyService copyService;

    public CopyController(CopyService copyService) {
        this.copyService = copyService;
    }
    @GetMapping()
    List<CopySummary> findAll(){
        return copyService.findAll();
    }
    @GetMapping("digital")
    List<DigitalCopySummary> findDigitalCopies(){
        return copyService.findDigitalCopies();
    }
    @GetMapping("physical")
    List<PhysicalCopySummary> findPhysicalCopies(){
        return copyService.findPhysicalCopies();
    }
    @GetMapping(params = "bookId")
    List<CopySummary> findByBookId(long bookId){
        return copyService.findByBookId(bookId);
    }
    @GetMapping(params = {"available","bookId"})
    List<CopySummary> findByAvailabilityAndBookId(boolean available, long bookId){
        return copyService.findByAvailabilityAndBookId(available, bookId);
    }
    @GetMapping(params = "fileHash")
    DigitalCopyWithBookTitle findByFileHash(String fileHash){
        return copyService.findByFileHash(fileHash);
    }
    @GetMapping(params = "barcode")
    PhysicalCopyWithBookTitle findByBarcode(String barcode){
        return copyService.findByBarcode(barcode);
    }
    @GetMapping("borrowersOf/{id}")
    CopyIdAndBorrowers findBorrowersByCopyId(@PathVariable long id){
        return copyService.findBorrowersByCopyId(id);
    }
    @PatchMapping("{id}/fileHash")
    void updateFileHash(@PathVariable long id, @RequestBody @NotBlank String fileHash){
        copyService.updateFileHash(id, fileHash);
    }
    @PatchMapping("{id}/fileFormat")
    void updateFileFormat(@PathVariable long id, @RequestBody @NotBlank String fileFormat){
        copyService.updateFileFormat(id, fileFormat);
    }
    @PatchMapping("{id}/visitLink")
    void updateVisitLink(@PathVariable long id, @RequestBody @NotBlank String visitLink){
        copyService.updateVisitLink(id, visitLink);
    }
    @PatchMapping("{id}/barcode")
    void updateBarcode(@PathVariable long id, @RequestBody @NotBlank String barcode){
        copyService.updateBarcode(id, barcode);
    }
    @PatchMapping("{id}/location")
    void updateLocation(@PathVariable long id, @RequestBody @NotBlank String location){
        copyService.updateLocation(id, location);
    }
    @PatchMapping("{id}/shelfNumber")
    void updateShelfNumber(@PathVariable long id, @RequestBody @NotNull @Positive int shelfNumber){
        copyService.updateShelfNumber(id, shelfNumber);
    }
}
