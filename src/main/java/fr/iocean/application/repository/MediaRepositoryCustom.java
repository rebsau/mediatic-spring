package fr.iocean.application.repository;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import fr.iocean.application.model.Media;

public interface MediaRepositoryCustom {
	
	public PageImpl<Media> search(Pageable pageable, String title, String authorName, Media.Type type);
}
