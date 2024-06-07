package com.marketplace.AdminService.AdminService.Impl;

import com.marketplace.AdminService.AdminService.AdminService;
import com.marketplace.AdminService.DTO.AuthenticationRequest;
import com.marketplace.AdminService.DTO.UserInfo;
import com.marketplace.AdminService.Fiegn.LoginServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements AdminService {


    @Autowired
    LoginServiceFeign feign;

    @Override
    public UserInfo addUsers(UserInfo user) {
        return feign.addUsers(user).getBody();
    }

    @Override
    public UserInfo getUserByUsername(String token, String username) {
        return feign.getUserByUsername(token,username).getBody();
    }
    @Override
    public UserInfo updateUserDetails(String token,int id, UserInfo userInfo) {
        return feign.updateUserDetails(token, id, userInfo).getBody();
    }

    @Override
    public String deleteUser(String token, int id) {
        return feign.deleteUser(token, id).getBody();
    }

    @Override
    public String token(AuthenticationRequest request) {
        return feign.token(request);
    }
}
