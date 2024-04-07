package com.loginService.service;

import com.loginService.Entity.UserInfo;
import com.loginService.POJO.AuthenticationRequest;

public interface LoginService {

	 UserInfo saveDetail(UserInfo login);

	 UserInfo getUserDetailsById(int userId);

	 UserInfo getUserByUsername(String username);

	 UserInfo updateDetail(int id, UserInfo userInfo);

	 String deleteUser(int id);

	 String authenticate(AuthenticationRequest request);

}