package demo.microservice.user.entities;

import demo.microservice.shared.entities.AuditableBaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("users")
public class User extends AuditableBaseEntity {
	private String username;

	private String email;

	private String password;
}
