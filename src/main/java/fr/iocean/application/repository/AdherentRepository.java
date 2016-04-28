package fr.iocean.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iocean.application.model.Adherent;

public interface AdherentRepository extends JpaRepository<Adherent, Long>, AdherentRepositoryCustom {	
	
	public List<Adherent> findByNom(String nom);
	
}
