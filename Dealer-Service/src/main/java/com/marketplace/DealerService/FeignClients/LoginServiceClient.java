package com.marketplace.DealerService.FeignClients;

import com.marketplace.DealerService.Entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "LoginService",url = "http://localhost:8092/login")
public interface LoginServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/UserList/{userId}", produces = "application/json")
    ResponseEntity<UserInfo> getUserById(@PathVariable("userId") int userId);
}
