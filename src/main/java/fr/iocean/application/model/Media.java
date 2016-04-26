package fr.iocean.application.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
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
	@OneToMany(mappedBy="media")
	private List<Emprunt> emprunt; 
	
	@Transient
	Boolean emprunter;

	public enum Type {
		Livre, CD, DVD;
	}

	public Media() {
	}

	public Media(String titre, String auteur, Type type) {
		this.titre = titre;
		this.auteur = auteur;
		this.type = type;
	}



	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	

	// SETTER et GETTER du titre
	public void setTitre(String t) {
		this.titre = t;
	}
	public String getTitre() {
		return this.titre;
	}

	// SETTER et GETTER de l'auteur
	public void setAuteur(String a) {
		this.auteur = a;
	}
	public String getAuteur() {
		return this.auteur;
	}

	// SETTER et GETTER du type
	public void setType(Type ty) {
		this.type = ty;
	}
	public Type getType() {
		return this.type;
	}

	// SETTER et GETTER de l'emprunt
	public List<Emprunt> getEmprunt() {
		if(this.emprunt== null){
			this.emprunt = new ArrayList<Emprunt>();
		}
		return this.emprunt;
	}
	public void setEmprunts(List<Emprunt> em) {
		this.emprunt = em;
	}
	
	public void addEmprunt(Emprunt emprunt){
		if(!getEmprunt().contains(emprunt)){
			this.emprunt.add(emprunt);
		}
	}
	
	public void isEmprunte() {
		Date today = new Date();
		for (Emprunt emp : emprunt) {
			if(today.after(emp.getDateEmprunt()) && today.before(emp.getDateRetour())){
				emprunter = true;
				break;
			}else{
				emprunter = false;
			}
		}
	}
	
	public Boolean getEmp(){
		return emprunter;
	}

	
}
