package com.uj.springsecurityjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uj.springsecurityjwt.entity.Users;
import com.uj.springsecurityjwt.model.AuthenticationRequest;
import com.uj.springsecurityjwt.model.AuthenticationResponse;
import com.uj.springsecurityjwt.service.MyUserDetailService;
import com.uj.springsecurityjwt.utils.JwtUtil;

@RestController
public class UserController {

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private MyUserDetailService userDetailService;

	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("/user/{emailid}")
	public Users getUserDetails(@PathVariable("emailid") String emailId) throws Exception {
		return userDetailService.getUserDetails(emailId);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest request)
			throws Exception {
		try {
			manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailService.loadUserByUsername(request.getUserName());
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
