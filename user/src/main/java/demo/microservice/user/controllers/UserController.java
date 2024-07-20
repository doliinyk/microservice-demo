package demo.microservice.user.controllers;

import demo.microservice.user.dtos.UserRequestDto;
import demo.microservice.user.dtos.UserResponseDto;
import demo.microservice.user.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class UserController {
	private final UserService userService;

	@GetMapping
	public Flux<UserResponseDto> findAll() {
		return userService.findAll();
	}

	@PostMapping
	public Mono<UUID> save(@Valid @RequestBody UserRequestDto userRequestDto) {
		return userService.save(userRequestDto);
	}

	@GetMapping("{uuid}")
	public Mono<UserResponseDto> findById(@PathVariable UUID uuid) {
		return userService.findById(uuid);
	}

	@PutMapping("{uuid}")
	public Mono<Void> update(@PathVariable UUID uuid, @Valid @RequestBody UserRequestDto userRequestDto) {
		return userService.update(uuid, userRequestDto);
	}

	@DeleteMapping("{uuid}")
	public Mono<Void> delete(@PathVariable UUID uuid) {
		return userService.delete(uuid);
	}
}
