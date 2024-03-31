package com.loginService.controller;

import com.loginService.Exception.InvalidValueProvidedException;

import com.loginService.JWTService.JwtService;
import com.loginService.POJO.AuthenticationRequest;
import com.loginService.SecurityConfig.UserInfoUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


import com.loginService.Entity.UserInfo;
import com.loginService.service.LoginService;
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class LoginController {
	
	@Autowired
	private final LoginService service;

	@Autowired
	private final JwtService jwtService;

	@Autowired
	private final AuthenticationManager authenticationManager;

	@Autowired
	private final UserInfoUserDetailsService userDetailsService;

	@PostMapping("/login/addDetail")
	public ResponseEntity<UserInfo> saveDetail(@RequestBody UserInfo userInfo) {
		return ResponseEntity.ok(service.saveDetail(userInfo));
	}

	  @GetMapping("/login/UserList/{userId}")
      public ResponseEntity<UserInfo> getUserDetailsById(@PathVariable int userId){
    	  return ResponseEntity.ok(service.getUserDetailsById(userId));
    	
		 }
	  
	  @PutMapping("/login/updateDetail/{userId}")
		public ResponseEntity<UserInfo> updateDetail(@PathVariable int userId, @RequestBody UserInfo userInfo) {
		  return ResponseEntity.ok(service.updateDetail(userId, userInfo));
	  }

	@PostMapping("/v1/authenticate")
	public String authenticate(@RequestBody AuthenticationRequest request) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
		if (authentication.isAuthenticated()){
			return jwtService.generateToken(request.getUserName());
		} else {
			throw new InvalidValueProvidedException("Invalid User Request !");
		}
	}

	@GetMapping("/v1/validate")
	public ResponseEntity<Boolean> validateAdmin(@RequestHeader(name = "Authorization") String token) {
		log.info("START - validateAdmin()");
		UserDetails user = userDetailsService.loadUserByUsername(jwtService.extractUsername(token));

		// throws custom exception and response if token is invalid
		jwtService.validateToken(token,user);

		// else the user is loaded and role is checked, if role is valid, access is granted

		if (user.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
			log.info("END - validateAdmin()");
			return new ResponseEntity<>(true, HttpStatus.OK);
		}

		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);

	}
}
