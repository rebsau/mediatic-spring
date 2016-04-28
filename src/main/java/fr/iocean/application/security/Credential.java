package fr.iocean.application.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import fr.iocean.application.model.IoEntity;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name="credential_")
@Getter
@Setter
public class Credential implements GrantedAuthority, IoEntity {
	private static final long serialVersionUID = -8169997129760396016L;
	
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String value;
	
	
	
	@Override
	public String getAuthority() {
		return value;
	}

	
	
	
}
