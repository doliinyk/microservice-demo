package demo.microservice.user.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDto(UUID uuid,
                              String username,
                              String email,
                              LocalDateTime createdAt,
                              LocalDateTime updatedAt) {
}
