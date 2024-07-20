package demo.microservice.user.services.impl;

import demo.microservice.user.dtos.UserRequestDto;
import demo.microservice.user.dtos.UserResponseDto;
import demo.microservice.user.entities.User;
import demo.microservice.user.mappers.UserMapper;
import demo.microservice.user.repositories.UserRepository;
import demo.microservice.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public Flux<UserResponseDto> findAll() {
		return userRepository.findAll().map(userMapper::toDto);
	}

	@Override
	@Transactional
	public Mono<UUID> save(UserRequestDto userRequestDto) {
		User user = userMapper.toEntity(userRequestDto);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user).map(User::getUuid);
	}

	@Override
	@Transactional(readOnly = true)
	public Mono<UserResponseDto> findById(UUID uuid) {
		return userRepository.findById(uuid).map(userMapper::toDto);
	}

	@Override
	@Transactional
	public Mono<Void> update(UUID uuid, UserRequestDto userRequestDto) {
		return findById(uuid).flatMap(existingUser -> {
			User user = userMapper.toEntity(userRequestDto);
			user.setUuid(existingUser.uuid());
			return userRepository.save(user);
		}).then();
	}

	@Override
	@Transactional
	public Mono<Void> delete(UUID uuid) {
		return userRepository.deleteById(uuid);
	}
}
