package fr.iocean.application.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fr.iocean.application.model.Media;
import fr.iocean.application.repository.MediaRepository;

@Service
@Transactional
public class MediaService {

	@Autowired
	private MediaRepository mediaRepository;
	
	public void save(Media media){
		mediaRepository.save(media);
	}
		
	public PageImpl<Media> searchMedias(int page, boolean ascend, String triParam, String title, String author, String type){
		
		Pageable pageable;
		
		if(ascend){
			pageable = new PageRequest(page, 10, Sort.Direction.ASC, triParam);
		}else{
			pageable = new PageRequest(page, 10, Sort.Direction.DESC, triParam);
		}
		
		return mediaRepository.search(pageable, title, author, type);
	}
	
	public Media findMediaById(Long id){
		return mediaRepository.findOne(id);
	}
	
	
	
}
