package fr.iocean.application.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fr.iocean.application.model.Emprunt;
import fr.iocean.application.model.Media;
import fr.iocean.application.repository.EmpruntRepository;

@Service
@Transactional
public class EmpruntService {

	@Autowired
	EmpruntRepository empruntRepository;
	
	public void save(Emprunt emprunt){
		empruntRepository.save(emprunt);
	}
	
	
	public PageImpl<Emprunt> searchListEmpruntByMedia(int page, boolean ascend, String triParam,Long id){
		
		Pageable pageable;
		if(ascend){
			pageable = new PageRequest(page, 10, Sort.Direction.ASC, triParam);
		}else{
			pageable = new PageRequest(page, 10, Sort.Direction.DESC, triParam);
		}	
		return empruntRepository.findListEmpruntByMediaId(pageable, id);
	}
	
	public PageImpl<Emprunt> searchListEmpruntByAdherent(int page, boolean ascend, String triParam,Long id){
		
		Pageable pageable;
		if(ascend){
			pageable = new PageRequest(page, 10, Sort.Direction.ASC, triParam);
		}else{
			pageable = new PageRequest(page, 10, Sort.Direction.DESC, triParam);
		}	
		return empruntRepository.findListEmpruntByAdherentId(pageable, id);
	}
	
}
