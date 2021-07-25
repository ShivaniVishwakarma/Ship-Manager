package com.hpc.shipservice.controller;

import com.hpc.shipservice.models.JwtRequest;
import com.hpc.shipservice.models.Response;
import com.hpc.shipservice.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/ships")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	//Login authentication
	@PostMapping("/authenticate")
	public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {

		authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(jwtRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		Response response = new Response();
		response.setData(token);
		response.setStatus(true);
		//return ResponseEntity.ok(new JwtResponse(token));
		return ResponseEntity.ok(response);
	}


	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}

//eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzaGl2YW5pIiwiZXhwIjoxNjI3MTQ5Mjc1LCJpYXQiOjE2MjcxMzEyNzV9.Qgxvn33RJXYSE8q36V3-TFW4Iw3tyDSSdEbfvggGvANBG_vvX6qw9YADaVs1sUmUViAFv3Spi46bF-sVWz3P0Q
