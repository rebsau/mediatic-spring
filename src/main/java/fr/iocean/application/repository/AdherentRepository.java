package fr.iocean.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.iocean.application.model.Adherent;
import fr.iocean.application.model.Media;

public interface AdherentRepository extends JpaRepository<Adherent, Long>, AdherentRepositoryCustom {
	
	
	public List<Adherent> findByNom(String nom);
	
	//public Adherent infoAdherent(int id);
		
	

}
