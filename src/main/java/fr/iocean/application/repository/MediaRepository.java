package fr.iocean.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.iocean.application.model.Media;

public interface MediaRepository extends JpaRepository<Media, Long>, MediaRepositoryCustom{

	@Query("SELECT m FROM Media as m left join fetch m.emprunt as e left join fetch e.adherent  where m.id=?1 ")
	public Media findOne(Long id);
}
