package fr.iocean.application.repository;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.util.StringUtils;

import fr.iocean.application.model.Adherent;

public class AdherentRepositoryImpl extends AbstractJpaRepository<Adherent> implements AdherentRepositoryCustom {

	@Override
	protected Class<Adherent> getEntityClass() {
		return Adherent.class;
	}

	@Override
	public PageImpl<Adherent> search(Pageable pageable, Long id, String nom) {
		Criteria query = createSearchCriteria(pageable);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		constructQuerySearch(query, id, nom);
		Long count = count(id, nom);
		return createSearchResult(pageable, query, count);
	}

	private Long count(long id, String nom) {
		Criteria query = getSession().createCriteria(entityClass).setProjection(Projections.countDistinct("id"));
		constructQuerySearch(query, id, nom);
		return (Long) query.uniqueResult();
	}

	private void constructQuerySearch(Criteria query, long id, String nom) {
		
		Disjunction or = Restrictions.disjunction();
		query.add(or);
		
		if (!StringUtils.isEmpty(id)) {
			query.add(Restrictions.like("id", "%" + id + "%"));
		}
		if (!StringUtils.isEmpty(nom)) {
			or.add(Restrictions.like("nom", nom));
			or.add(Restrictions.like("prenom", nom));
		}
	}

}
