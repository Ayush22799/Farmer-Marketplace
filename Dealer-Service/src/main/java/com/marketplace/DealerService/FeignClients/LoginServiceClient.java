package com.marketplace.DealerService.FeignClients;

import com.marketplace.DealerService.DTO.AuthenticationRequest;
import com.marketplace.DealerService.DTO.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "LoginService",url = "http://localhost:8081/auth")
public interface LoginServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/v1/User/{userId}")
    ResponseEntity<UserInfo> getUserById(@PathVariable("userId") int userId);

    @PostMapping("/v1/authenticate")
    ResponseEntity<String> getToken(@RequestBody AuthenticationRequest request);
}
