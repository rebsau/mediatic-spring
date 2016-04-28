package fr.iocean.application.model;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	@Column
	@NotEmpty
	private String nom;
	@Column
	@NotEmpty
	private String prenom;
	@Column
	@NotNull
	private Date date_naissance;
	@Column
	private String adresse;
	@Column
	private String code_postal;
	@Column
	private String ville;
	@Column
	@NotEmpty
	private String email;
	@Column
	private Date date_paiement;
	@Column
	private int montant_cotisation;
	@OneToMany(mappedBy = "adherent")
	private List<Emprunt> emprunts;
	
	@Column
	private int nbrMedia;

	public Adherent() {

	}
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public Adherent(String prenom, String nom, Date date_naissance, String email) {
		this.nom = nom;
		this.prenom = prenom;
		this.date_naissance = date_naissance;
		this.email = email;
	}

	public void addEmprunt(Emprunt emprunt) {
		if (!getEmprunts().contains(emprunt)) {
			this.emprunts.add(emprunt);
		}
	}

	public String toString() {
		return prenom + " " + nom;
	}

//	public boolean isAjourCotisation() {
//		Date dateFinCotisation = DateHelper.plusYears(date_paiement, 1);
//
//		if (dateFinCotisation.compareTo(DateHelper.now()) <= 0)
//			return false;
//		else
//			return true;
//	}

//	public int getNbMediaPossede() {
//		int res = 0;
//
//		for (Emprunt emp : getEmprunts()) {
//			if (emp.getDateRetour().compareTo(DateHelper.now()) > 0)
//				res++;
//		}
//
//		return res;
//	}

}
