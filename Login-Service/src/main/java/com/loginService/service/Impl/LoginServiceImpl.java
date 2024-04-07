package com.loginService.service.Impl;

import java.util.Optional;

import com.loginService.Exception.DetailsNotFoundException;
import com.loginService.Exception.InvalidValueProvidedException;

import com.loginService.Entity.*;
import com.loginService.service.LoginService;
import com.loginService.utility.utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.loginService.repo.LoginRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
	@Autowired
	private final LoginRepository repo;

	@Autowired
	private final PasswordEncoder passwordEncoder;

	public UserInfo saveDetail(UserInfo userInfo) {
		Optional<UserInfo> user = repo.findByuserName(userInfo.getUserName());
		if(user.isPresent()){
			throw new InvalidValueProvidedException("User Already Exists");
		}

		if(utility.validateUserType(userInfo.getUserType())){
			throw new InvalidValueProvidedException("Provided userType is invalid");
		}
		if(utility.validatePassword(userInfo.getPassword())){
			throw new InvalidValueProvidedException("Provided password Doesn't meet criteria");
		}
		if(utility.validatePhoneNumber(userInfo.getPhoneNo())){
			throw new InvalidValueProvidedException("Phone Number is invalid");
		}
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		repo.save(userInfo);
		return userInfo;
	}

	public UserInfo getUserDetailsById(int userId) {
		Optional<UserInfo> user = repo.findById(userId);
		if(user.isEmpty()){
			throw new DetailsNotFoundException("No details available for UserId: " + userId);
		}
		return user.get();
	}

	public UserInfo updateDetail(int id, UserInfo userInfo) {
		Optional<UserInfo> user = repo.findById(id);
		if(user.isEmpty()){
			throw new DetailsNotFoundException("No details exists for provided user");
		}
		if(utility.validateUserType(userInfo.getUserType())){
			throw new InvalidValueProvidedException("Provided userType is invalid");
		}
		if(utility.validatePassword(userInfo.getPassword())){
			throw new InvalidValueProvidedException("Provided password Doesn't meet criteria");
		}
		if(utility.validatePhoneNumber(userInfo.getPhoneNo())){
			throw new InvalidValueProvidedException("Phone Number is invalid");
		}
		userInfo.setUserId(id);
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		repo.save(userInfo);
		return userInfo;
	}
}
