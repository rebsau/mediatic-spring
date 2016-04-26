package fr.iocean.application.repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import fr.iocean.application.model.IoEntity;

public abstract class AbstractJpaRepository<T extends IoEntity> {

	protected Class<T> entityClass;

    @PersistenceContext
    protected EntityManager em;

    @PostConstruct
    public void init() {
        entityClass = getEntityClass();
    }

    protected abstract Class<T> getEntityClass();

    protected Session getSession() {
        return em.unwrap(Session.class);
    }

    public Criteria createSearchCriteria(Pageable pageable) {
        return getSession().createCriteria(entityClass)
                .setFirstResult(pageable.getOffset())
                .setMaxResults(pageable.getPageSize());
    }

    @SuppressWarnings("unchecked")
    public PageImpl<T> createSearchResult(Pageable pageable, Criteria query, long count) {
        if (pageable.getSort() == null) {
            return new PageImpl<>(query.list(), pageable, count);
        } else {
            addOrder(query, pageable);
            return new PageImpl<>(query.list(), pageable, count);
        }
    }

    public void addOrder(Criteria query, Pageable pageable) {
        for (Sort.Order order : pageable.getSort()) {
            if (order.isAscending()) {
                query.addOrder(Order.asc(order.getProperty()));
            } else {
                query.addOrder(Order.desc(order.getProperty()));
            }
        }
    }
	
}
