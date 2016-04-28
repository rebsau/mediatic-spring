package fr.iocean.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.iocean.application.repository.UserRepository;


@Service
public class UserService {
	
	
	@Autowired
	@Qualifier("userRepository")
	UserRepository userRepo;
	

	/*
    public User save(User user) {
        return userRepo.save(user);
    }
    
    
    public User findOne(Long id) {
        return userRepo.findOne(id);
    }
    
    
    public List<User> findAll() {
        return userRepo.findAll();
    }
    
    
    public void delete(Long id) {
    	User user = findOne(id);
    	
    	if (user != null)
    		userRepo.delete(user);
    }
>>>>>>> branch 'master' of https://github.com/rebsau/mediatic-spring.git
    
    
    public User findOneByLogin(String login) {
		return userRepo.findOneByLogin(login);
    }
    */
}





