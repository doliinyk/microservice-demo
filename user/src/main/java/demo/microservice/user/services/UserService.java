package demo.microservice.user.services;

import demo.microservice.user.dtos.UserRequestDto;
import demo.microservice.user.dtos.UserResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService {
	Flux<UserResponseDto> findAll();

	Mono<UUID> save(UserRequestDto userRequestDto);

	Mono<UserResponseDto> findById(UUID uuid);

	Mono<Void> update(UUID uuid, UserRequestDto userRequestDto);

	Mono<Void> delete(UUID uuid);
}
