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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	//Login authentication
	@PostMapping("/authenticate")
	public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		System.out.println("Encrypted pwd : " + bCryptPasswordEncoder.encode(jwtRequest.getPassword()));

		authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(jwtRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		System.out.println("Token: " + token);

		Response response = new Response();
		response.setData(token);
		response.setStatus(true);
		response.setMessage("Token Generated");
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

//eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzaGl2YW5pIiwiZXhwIjoxNjI3NDA5NjQ5LCJpYXQiOjE2MjczOTE2NDl9.vlN7dgaLxkPcbNJxmpY9QYvWRX2QEg_Bgpci-0pUtlaFXrB09EMtBlIxEarFXWHGs4YaPIR8-8L_3PrxOSPnzw
