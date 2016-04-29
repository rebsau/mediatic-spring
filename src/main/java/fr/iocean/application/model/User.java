package fr.iocean.application.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import fr.iocean.application.security.Credential;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="user_")
@Getter
@Setter
public class User implements IoEntity {
	private static final long serialVersionUID = -6021728712862181659L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(unique = true)
	private String login;
	@NotBlank
	private String password;
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Credential> credentials;
	
	
	

}
