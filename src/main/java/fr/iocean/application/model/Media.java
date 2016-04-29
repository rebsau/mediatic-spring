package fr.iocean.application.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Media implements IoEntity {
	
	private static final long serialVersionUID = 2819081289697510606L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String titre;
	
	@NotEmpty
	private String auteur;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Type type;
	
	@OneToOne
	@JsonIgnoreProperties("media")
	private Emprunt emprunt;
		
	public enum Type {
		Livre, CD, DVD;
	}

	public Media() {
		System.out.println("default");
	}

	//@JsonIgnoreProperties({"hibernateLazyInitialiser","handler"})
	public Media(String titre, String auteur, Type type) {
		System.out.println("construt");
		
		this.titre = titre;
		this.auteur = auteur;
		this.type = type;
	}
		
}


