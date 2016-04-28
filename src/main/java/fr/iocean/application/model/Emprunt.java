package fr.iocean.application.model;


import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Emprunt implements IoEntity {
	private static final long serialVersionUID = 9055544949827654081L;
	
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JsonIgnoreProperties("emprunt")
	private Media media;
	
	@ManyToOne
	@JsonIgnoreProperties("emprunt")
	private Adherent adherent;
	
	@NotNull
	private Date dateEmprunt;
	
	private Date dateRetour;
	
	public Emprunt(){}
	
	public Emprunt(Media media,Adherent adherent,Date date){
		this.media = media;
		this.adherent = adherent;
		this.dateEmprunt = date;
		calculDateRetour();
		
		//this.media.addEmprunt(this);
	}
	
	private void calculDateRetour(){
		if(media.getType()==Media.Type.Livre){
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateEmprunt);
			cal.add(Calendar.DATE, 30); // add 30 days 
			dateRetour = cal.getTime(); 
		}else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateEmprunt);
			cal.add(Calendar.DATE, 15); // add 15 days 
			dateRetour = cal.getTime(); 
		}
	}
	
}