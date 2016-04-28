package fr.iocean.application.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import fr.iocean.application.model.Adherent;
import fr.iocean.application.repository.AdherentRepository;

@Service
public class AdherentService {

	@Autowired
	AdherentRepository adherentRepository;

	public Adherent findById(Long id) {
		return adherentRepository.findOne(id);
	}

	public PageImpl<Adherent> searchAdherents(int page, boolean ascend, String triParam, Long id, String nom) {
		Pageable pageable;
		if (ascend) {
			pageable = new PageRequest(page, 10, Sort.Direction.ASC, triParam);
		} else {
			pageable = new PageRequest(page, 10, Sort.Direction.DESC, triParam);
		}
		return adherentRepository.search(pageable, id, nom);
	}

	public List<Adherent> searchAdherentsByName(String nom) {
		return adherentRepository.findByNom(nom);
	}

	public Adherent create(Adherent adherent) {
		return adherentRepository.save(adherent);
	}

	public List<Adherent> findAll() {
		return adherentRepository.findAll();
	}

	public void update(Adherent adherent) {
		adherentRepository.save(adherent);
	}

}
