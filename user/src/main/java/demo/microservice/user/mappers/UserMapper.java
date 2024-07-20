package demo.microservice.user.mappers;

import demo.microservice.user.dtos.UserRequestDto;
import demo.microservice.user.dtos.UserResponseDto;
import demo.microservice.user.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserMapper {
	UserResponseDto toDto(User user);

	@Mapping(target = "uuid", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	User toEntity(UserRequestDto userRequestDto);
}
