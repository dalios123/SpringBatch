package com.ooredoo.projetfinetude.Security.services;

 
import com.ooredoo.projetfinetude.Entities.User;
import com.ooredoo.projetfinetude.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;





@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(login).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> login : " + login));

		return UserPrinciple.build(user);
	}
}