package be.hasan.libraSphere.members;

import be.hasan.libraSphere.authors.Gender;
import be.hasan.libraSphere.borrowedCopies.BorrowedCopy;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String email;
    private String phoneNumber;
    private LocalDate membershipDate;
    @Embedded
    private Address address;   //Address is a value object class.
    @OneToMany(mappedBy = "member")
    private Set<BorrowedCopy> borrowedCopies;
    protected Member(){}
    public Member(String firstName, String lastName, LocalDate dateOfBirth, Gender gender, String email,
                  String phoneNumber, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.membershipDate = LocalDate.now();
        this.borrowedCopies = new LinkedHashSet<BorrowedCopy>();
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public Address getAddress() {
        return address;
    }
    public Set<BorrowedCopy> getBorrowedCopies() {
        return Collections.unmodifiableSet(borrowedCopies);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMembershipDate(LocalDate membershipDate) {
        this.membershipDate = membershipDate;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    @Override
    public boolean equals(Object object) {
        return object instanceof Member member &&
                email.equalsIgnoreCase(member.email);
    }
    @Override
    public int hashCode() {
        return email.toLowerCase().hashCode();
    }
}
