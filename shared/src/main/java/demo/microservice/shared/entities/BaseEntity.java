package demo.microservice.shared.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Getter
@Setter
public abstract class BaseEntity {
	@Id
	protected UUID uuid;
}
