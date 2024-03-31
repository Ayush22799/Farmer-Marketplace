package com.loginService.service;

import com.loginService.Entity.UserInfo;

public interface LoginService {

	 UserInfo saveDetail(UserInfo login);

	 UserInfo getUserDetailsById(int userId);

	 UserInfo updateDetail(int id, UserInfo userInfo);

}