package fr.iocean.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name="user_")
public class User implements IoEntity {
	private static final long serialVersionUID = -6021728712862181659L;
	
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank
	private String login;
	@NotBlank
	private String password;
	private String name;
	
	//@ManyToMany(fetch = FetchType.EAGER)
	//private List<Credential> credentials;
	
	
	
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
//	public List<Credential> getCredentials() {
//		return credentials;
//	}
//	public void setCredentials(List<Credential> credentials) {
//		this.credentials = credentials;
//	}
	

}
