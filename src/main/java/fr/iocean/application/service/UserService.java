package fr.iocean.application.service;

import java.util.List;

import fr.iocean.application.model.User;
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





