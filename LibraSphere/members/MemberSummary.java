package be.hasan.libraSphere.members;

public record MemberSummary(long id, String firstName, String lastName, String email) {
    public MemberSummary(Member member){
        this(member.getId(), member.getFirstName(), member.getLastName(), member.getEmail());
    }
}
