package com.marketplace.farmerservice.Feign;

import com.loginService.Entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "user", url = "http://localhost:8091/auth")
public interface UserServiceFeign {
    @RequestMapping(method = RequestMethod.POST, value = "/v1/authenticate", produces = "application/json")
    ResponseEntity<UserInfo> getUserById(@PathVariable("userId") int userId);
}
