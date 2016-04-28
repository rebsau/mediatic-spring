package fr.iocean.application.repository;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import fr.iocean.application.model.Adherent;

public interface AdherentRepositoryCustom {
	
	public PageImpl<Adherent> search(Pageable pageable, Long id, String nom);

}
