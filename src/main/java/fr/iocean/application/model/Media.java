package fr.iocean.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
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
	@GeneratedValue
	private Long id;
	@Column
	@NotEmpty
	private String titre;
	@Column
	@NotEmpty
	private String auteur;
	@Enumerated(EnumType.STRING)
	@NotNull
	private Type type;
	
	@OneToOne
	@JsonIgnoreProperties("media")
	private Emprunt emprunteur;
		
	public enum Type {
		Livre, CD, DVD;
	}

	public Media() {
	}

	//@JsonIgnoreProperties({"hibernateLazyInitialiser","handler"})
	public Media(String titre, String auteur, Type type) {
		this.titre = titre;
		this.auteur = auteur;
		this.type = type;
	}
		
}


