package fr.iocean.application.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Utilisateur {

		@Id
		@GeneratedValue
		private int id;
		
		@Column
		@NotEmpty
		private String pseudo;
		
		@Column
		@NotEmpty
		private String motDePasse;
		
		@Column
		private boolean droits;

		
		public Utilisateur(){
			
		}
		
		public Utilisateur(String pseudo, String motDePasse, boolean droits) {

			this.pseudo = pseudo;
			this.motDePasse = motDePasse;
			this.droits = droits;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getPseudo() {
			return pseudo;
		}

		public void setPseudo(String pseudo) {
			this.pseudo = pseudo;
		} 

		public String getMotDePasse() {
			return motDePasse;
		}

		public void setMotDePasse(String motDePasse) {
			this.motDePasse = motDePasse;
		}

		public boolean getDroits() {
			return droits;
		}

		public void setDroits(boolean droits) {
			this.droits = droits;
		}
		
	

	}


