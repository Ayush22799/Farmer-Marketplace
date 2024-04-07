package com.loginService.service;

import com.loginService.Entity.UserInfo;

public interface LoginService {

	 UserInfo saveDetail(UserInfo login);

	 UserInfo getUserDetailsById(int userId);

	 UserInfo getUserByUsername(String username);

	 UserInfo updateDetail(int id, UserInfo userInfo);

}