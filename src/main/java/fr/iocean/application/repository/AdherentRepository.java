package fr.iocean.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.iocean.application.model.Adherent;
import fr.iocean.application.model.Media;

public interface AdherentRepository extends JpaRepository<Adherent, Long>, AdherentRepositoryCustom {	
	
	@Query("SELECT a from Adherent as a WHERE concat(a.nom, ' ', a.prenom) LIKE %:nom% ")
	public List<Adherent> findByNom(@Param("nom") String nom);
	
}
