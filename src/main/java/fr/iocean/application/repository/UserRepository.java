package fr.iocean.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iocean.application.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByLogin(String login);
	
}
