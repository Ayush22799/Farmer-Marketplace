package com.marketplace.AdminService.AdminService;

import com.marketplace.AdminService.DTO.AuthenticationRequest;
import com.marketplace.AdminService.DTO.UserInfo;

public interface AdminService {


    String token(AuthenticationRequest request);

    UserInfo addUsers(UserInfo user);

    UserInfo getUserByUsername(String token, String username);

    UserInfo updateUserDetails(String token, int id, UserInfo userInfo);

    String deleteUser(String token, int id);

}
