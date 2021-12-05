package com.uj.springsecurityjwt.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uj.springsecurityjwt.entity.Users;
import com.uj.springsecurityjwt.repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		return new User("root", "root", Arrays.asList());
	}

	public Users getUserDetails(String emailId) throws Exception {
		Optional<Users> user = userRepo.findUserByEmailId(emailId);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new Exception("User not present");
		}

	}

}
