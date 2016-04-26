package fr.iocean.application.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import fr.iocean.application.model.IoEntity;




public abstract class AbstractJpaRepository<T extends IoEntity> {
	
	protected Class<T> entityClass;
	
	protected abstract Class<T> getEntityClass();
	
	
	@PersistenceContext
	EntityManager em;
	
	
	
	@PostConstruct
    public void init() {
        entityClass = getEntityClass();
    }
	
	
	protected Session getSession() {
		return em.unwrap(Session.class);
	}
	
	
	@Transactional
    public T save(T entity) {
        if (isNew(entity)) {
            em.persist(entity);
            return entity;
        } else if (!em.contains(entity)) {
            return em.merge(entity);
        }

        return entity;
    }
	

    @Transactional
    public T findOne(Long id) {
        return em.find(entityClass, id);
    }
    
	
    @SuppressWarnings("unchecked")
    @Transactional
    public List<T> findAll() {
        return getSession().createCriteria(entityClass).list();
    }
    
	
    @Transactional
    public void delete(T entity) {
        if (!getSession().contains(entity)) {
            em.remove(getSession().merge(entity));
        } else {
            em.remove(entity);
        }
    }
    

	public boolean isNew(T entity) {
        return entity.getId() == null;
    }
	
}
