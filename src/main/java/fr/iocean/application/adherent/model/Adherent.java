package fr.iocean.application.adherent.model;

import java.util.ArrayList;
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


import fr.iocean.application.emprunt.model.Emprunt;
import fr.iocean.application.helper.DateHelper;


@Entity
@Table(name="adherent")
public class Adherent {
	
	@Id
	@GeneratedValue
	private int id;
	
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
	@OneToMany(mappedBy="adherent")
	private List<Emprunt> emprunts;
	
	public Adherent () {
		
	}
	
	public Adherent (String prenom, String nom, Date date_naissance, String email) {
		this.nom = nom;
		this.prenom = prenom;
		this.date_naissance = date_naissance;
		this.email = email;
	}
	
	
	//GETTERS
	public int getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public Date getDateNaissance() {
		return date_naissance;
	}
	
	public String getAdresse() {
		return adresse;
	}
	
	public String getCodePostal() {
		return code_postal;
	}
	
	public String getVille() {
		return ville;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Date getDatePaiement() {
		return date_paiement;
	}
	
	public int getMontantCotisation() {
		return montant_cotisation;
	}
	
	public List<Emprunt> getEmprunts() {
		if(this.emprunts== null){
			this.emprunts = new ArrayList<Emprunt>();
		}
		return this.emprunts;
	}
	
	//SETTERS
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public void setDateNaissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}
	
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public void setCodePostal(String code_postal) {
		this.code_postal = code_postal;
	}
	
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setDatePaiement(Date date_paiement) {
		this.date_paiement = date_paiement;
	}
	
	public void setMontantCotisation(int montant_cotisation) {
		this.montant_cotisation = montant_cotisation;
	}
	
	public void setEmprunts(List<Emprunt> em) {
		this.emprunts = em;
	}
	
	public void addEmprunt(Emprunt emprunt){
		if(!getEmprunts().contains(emprunt)){
			this.emprunts.add(emprunt);
		}
	}
	
	
	public String toString() {
		return prenom + " " + nom;
	}
	
	
	
	public boolean isAjourCotisation() {
		Date dateFinCotisation = DateHelper.plusYears(date_paiement, 1);
		
		if (dateFinCotisation.compareTo(DateHelper.now()) <= 0)
			return false;
		else
			return true;
	}
	
	
	public int getNbMediaPossede() {
		int res = 0;
		
		for (Emprunt emp : getEmprunts()) {
			if (emp.getDateRetour().compareTo(DateHelper.now()) > 0)
				res++;
		}
		
		return res;
	}
	
}







