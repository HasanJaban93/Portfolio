package be.hasan.libraSphere.members;

import be.hasan.libraSphere.authors.Gender;
import be.hasan.libraSphere.borrowedCopies.CopySummaryWithBorrowKey;
import be.hasan.libraSphere.borrowedCopies.NewBorrowedCopy;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping
    List<MemberSummary> findAll(){
        return memberService.findAll();
    }
    @GetMapping(params = "firstName")
    List<MemberSummary> findByFirstName(String firstName){
        return memberService.findByFirstName(firstName);
    }
    @GetMapping(params = "lastName")
    List<MemberSummary> findByLastName(String lastName){
        return memberService.findByLastName(lastName);
    }
    @GetMapping(params = "gender")
    List<MemberSummary> findByGender(Gender gender){
        return memberService.findByGender(gender);
    }
    @GetMapping(params = "municipality")
    List<MemberSummary> findByMunicipality(String  municipality){
        return memberService.findByMunicipality(municipality);
    }
    @GetMapping("{id}")
    MemberWithoutBorrowedCopies findById(@PathVariable long id){
        return memberService.findByIdWithoutBorrowedCopies(id);
    }
    @GetMapping("withBorrowedCopies/{id}")
    MemberIdAndBorrowedCopies findBorrowedCopiesByMemberId(@PathVariable long id){
        return memberService.findBorrowedCopiesByMemberId(id);
    }
    @PostMapping
    long create(@RequestBody @Valid NewMember newMember) {
        return memberService.create(newMember);
    }
    @DeleteMapping("{id}")
    void delete(@PathVariable long id) {
        memberService.delete(id);
    }
    @PatchMapping("{id}/borrowCopy")
    CopySummaryWithBorrowKey borrowCopy(@PathVariable long id, @RequestBody @Valid NewBorrowedCopy newBorrowedCopy){
        return memberService.borrowCopy(id, newBorrowedCopy);
    }
    @PatchMapping("{borrowKey}/returnBorrowedCopy")
    void returnBorrowedCopy(@PathVariable long borrowKey){
        memberService.returnBorrowedCopy(borrowKey);
    }
    @PatchMapping("{id}/firstName")
    void updateFirstName(@PathVariable long id, @RequestBody @NotBlank String firstName){
        memberService.updateFirstName(id,firstName);
    }
    @PatchMapping("{id}/lastName")
    void updateLastName(@PathVariable long id, @RequestBody @NotBlank String lastName){
        memberService.updateLastName(id,lastName);
    }
    @PatchMapping("{id}/dateOfBirth")
    void updateDateOfBirth(@PathVariable long id, @RequestBody @NotNull @Past LocalDate dateOfBirth){
        memberService.updateDateOfBirth(id, dateOfBirth);
    }
    @PatchMapping("{id}/gender")
    void updateGender(@PathVariable long id, @RequestBody @NotNull Gender gender){
        memberService.updateGender(id, gender);
    }
    @PatchMapping("{id}/email")
    void updateEmail(@PathVariable long id, @RequestBody @NotBlank @Email String email){
        memberService.updateEmail(id, email);
    }
    @PatchMapping("{id}/phoneNumber")
    void updatePhoneNumber(@PathVariable long id, @RequestBody @NotBlank String phoneNumber){
        memberService.updatePhoneNumber(id, phoneNumber);
    }
    @PatchMapping("{id}/membershipDate")
    void updateMembershipDate(@PathVariable long id, @RequestBody @PastOrPresent LocalDate membershipDate){
        memberService.updateMembershipDate(id, membershipDate);
    }
    @PatchMapping("{id}/address")
    void updateAddress(@PathVariable long id, @RequestBody @Valid NewAddress newAddress){
        memberService.updateAddress(id, newAddress);
    }
    @PatchMapping("{id}/street")
    void updateStreet(@PathVariable long id, @RequestBody @NotBlank String street){
        memberService.updateStreet(id, street);
    }
    @PatchMapping("{id}/houseNumber")
    void updateHouseNumber(@PathVariable long id, @RequestBody @NotBlank String houseNumber){
        memberService.updateHouseNumber(id, houseNumber);
    }
    @PatchMapping("{id}/municipality")
    void updateMunicipality(@PathVariable long id, @RequestBody @NotBlank String municipality) {
        memberService.updateMunicipality(id, municipality);
    }
    @PatchMapping("{id}/postcode")
    void updatePostcode(@PathVariable long id, @RequestBody @NotNull @Positive int postcode){
        memberService.updatePostcode(id, postcode);
    }
}
