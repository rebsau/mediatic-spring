package fr.iocean.application.repository;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import fr.iocean.application.model.Media;

//Not Used

public class MediaRepositoryImpl extends AbstractJpaRepository<Media> implements MediaRepositoryCustom {
	
	@Override
    protected Class<Media> getEntityClass() {
        return Media.class;
    }

    @Override
    public PageImpl<Media> search(Pageable pageable, String title, String authorName, String type) {
        Criteria query = createSearchCriteria(pageable);
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        constructQuerySearch(query, title, authorName, type);
        
        Long count = count(title, authorName, type);        
        return createSearchResult(pageable, query, count);
    }
	
    private Long count(String title, String authorName, String type) {
        Criteria query = getSession().createCriteria(entityClass).setProjection(Projections.countDistinct("id"));
        constructQuerySearch(query, title, authorName, type);
        return (Long) query.uniqueResult();
    }

    private void constructQuerySearch(Criteria query, String title, String authorName, String type) {
    	
    	query.createAlias("emprunteur", "e", JoinType.LEFT_OUTER_JOIN);
    	query.createAlias("e.adherent", "a");
    	
		if (!StringUtils.isEmpty(title)) {
			query.add(Restrictions.like("titre", "%" + title + "%"));
		}
		if (!StringUtils.isEmpty(authorName)) {
			query.add(Restrictions.like("auteur", "%" + authorName + "%"));
		}
		if (!StringUtils.isEmpty(type)) {
			query.add(Restrictions.like("type", "%" + type + "%"));
		}
    }

}
