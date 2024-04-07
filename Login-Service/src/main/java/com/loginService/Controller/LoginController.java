package com.loginService.Controller;

import com.loginService.Exception.InvalidValueProvidedException;

import com.loginService.JWTService.JwtService;
import com.loginService.POJO.AuthenticationRequest;
import com.loginService.SecurityConfig.UserInfoUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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

	@PostMapping("/v1/addDetail")
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

	@GetMapping("/v1/validate/user/{username}")
	public String validateToken(@RequestParam("token") String token, @PathVariable("username") String username){
		UserDetails user = userDetailsService.loadUserByUsername(username);

		if (jwtService.validateToken(token,user)){
			return "Token is valid";
		}
		return "token is invalid";
	}
}
