/**
 * 
 */
package model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "user_roles")
public class UserRole implements GrantedAuthority {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;

	private String authority;

	public UserRole(String username, String authority) {
		this.username = username;
		this.authority = authority;
	}
	@Override
	public String getAuthority() {
		return this.authority;
	}
}
