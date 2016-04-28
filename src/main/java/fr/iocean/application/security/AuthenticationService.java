package fr.iocean.application.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import fr.iocean.application.model.User;
import fr.iocean.application.repository.UserRepository;


@Component
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(final String username) {
		Optional<User> option = Optional.of(userRepository.findByLogin(username));
		
		if (option.isPresent()) {
			User user = option.get();
			List<GrantedAuthority> rules = this.getUserCredentials(user);
			return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), rules);
		}
		
		throw new UsernameNotFoundException("username.not.found");
	}
	
	
	public List<GrantedAuthority> getUserCredentials(User user) {
		List<GrantedAuthority> res = new ArrayList<GrantedAuthority>();
		
		
		for (Credential c : user.getCredentials()) {
			res.add(new SimpleGrantedAuthority(c.getAuthority()));
		}
		
		return res;
	}
}


