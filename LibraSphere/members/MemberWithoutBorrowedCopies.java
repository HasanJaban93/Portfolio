package be.hasan.libraSphere.members;

import be.hasan.libraSphere.authors.Gender;

import java.time.LocalDate;

public record MemberWithoutBorrowedCopies(long id, String firstName, String lastName, LocalDate dateOfBirth,
                                          Gender gender, String email,
                                          String phoneNumber, LocalDate membershipDate, Address address) {
    MemberWithoutBorrowedCopies(Member member){
        this(member.getId(), member.getFirstName(), member.getLastName(), member.getDateOfBirth(),
                member.getGender(), member.getEmail(), member.getPhoneNumber(), member.getMembershipDate(),
                member.getAddress());
    }
}
