package fr.iocean.application.repository;

import fr.iocean.application.model.Emprunt;

public class EmpruntRepositoryImpl extends AbstractJpaRepository<Emprunt> implements EmpruntRepositoryCustom{

	@Override
	protected Class<Emprunt> getEntityClass() {
		return Emprunt.class;
	}

}
