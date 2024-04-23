package zingmp3.dto;

public record UserRegistrationRecord(
        String username,
        String password,
        String email,
        String firstName,
        String lastName
) {
}
