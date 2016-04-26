package fr.iocean.application.repository;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.iocean.application.model.User;


@Repository
public class UserRepository extends AbstractJpaRepository<User> {
	
	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}
	
	@Transactional(readOnly = true)
    public User findOneByLogin(String login) {
		return (User)getSession().createCriteria(entityClass).add(Restrictions.eq("login", login)).uniqueResult();
    }
	
	
}
