package demo.microservice.user.dtos;

import jakarta.validation.constraints.Email;

public record UserRequestDto(String username, @Email String email, String password) {
}
