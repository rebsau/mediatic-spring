package fr.iocean.application.repository;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.iocean.application.model.Emprunt;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long>{

	@Query(value = "select e from Emprunt as e join e.media as m join fetch e.adherent where m.id = :id", 
			countQuery = "select count(e) from Emprunt as e join e.media as m where m.id = :id")
	public PageImpl<Emprunt> findListEmpruntByMediaId(Pageable pageable, @Param("id") Long id);
	
	@Query(value = "select e from Emprunt as e join e.adherent as a join fetch e.media where a.id = :id", 
			countQuery = "select count(e) from Emprunt as e join e.adherent as a where a.id = :id")
	public PageImpl<Emprunt> findListEmpruntByAdherentId(Pageable pageable, @Param("id") Long id);
	
	@Query("select count(*) from Emprunt as e join e.adherent as a where a.id = :id")
	public Integer countEmpruntForAdherent(@Param("id") Long id);
}
