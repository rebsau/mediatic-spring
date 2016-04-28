package fr.iocean.application.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.iocean.application.helper.DateHelper;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adherent")
@Getter
@Setter
public class Adherent implements IoEntity {
	private static final long serialVersionUID = 3488747282845051698L;

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty
	private String nom;

	@NotEmpty
	private String prenom;
	
	@NotNull
	private Date date_naissance;
	
	private String adresse;
	
	private String code_postal;

	private String ville;

	@NotEmpty
	private String email;

	private Date date_paiement;

	private Integer montant_cotisation;

	private Integer nbMedia;


	public Adherent() {

	}
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public Adherent(String prenom, String nom, Date date_naissance, String email) {
		this.nom = nom;
		this.prenom = prenom;
		this.date_naissance = date_naissance;
		this.email = email;
	}
	
	public String toString() {
		return prenom + " " + nom;
	}
	
	public boolean aJourCotisation() {
		Date dateFinCotisation = DateHelper.plusYears(date_paiement, 1);
		
		if (dateFinCotisation.compareTo(DateHelper.now()) <= 0)
			return false;
		else
			return true;
	}

}
