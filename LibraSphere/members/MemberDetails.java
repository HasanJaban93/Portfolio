package be.hasan.libraSphere.members;

import be.hasan.libraSphere.authors.Gender;
import be.hasan.libraSphere.borrowedCopies.BorrowedCopySummary;

import java.time.LocalDate;
import java.util.List;

public record MemberDetails(long id, String firstName, String lastName,
                            LocalDate dateOfBirth, Gender gender, String email,
                            String phoneNumber, Address address,
                            LocalDate membershipDate,
                            List<BorrowedCopySummary> borrowedCopiesSummary) {
    MemberDetails(Member member){
        this(member.getId(), member.getFirstName(), member.getLastName(),
                member.getDateOfBirth(), member.getGender(),member.getEmail(), member.getPhoneNumber(),
                member.getAddress(), member.getMembershipDate(),
                member.getBorrowedCopies()
                        .stream()
                        .map(borrowedCopy -> new BorrowedCopySummary(borrowedCopy))
                        .toList());
    }
}
