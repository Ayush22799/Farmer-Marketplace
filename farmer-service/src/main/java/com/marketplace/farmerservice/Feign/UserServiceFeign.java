package com.marketplace.farmerservice.Feign;


import com.marketplace.farmerservice.DTO.AuthenticationRequest;
import com.marketplace.farmerservice.DTO.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "user", url = "http://localhost:8081/auth")
public interface UserServiceFeign {
    @RequestMapping(method = RequestMethod.GET, value = "/v1/User/{userId}")
    ResponseEntity<UserInfo> getUserById(@PathVariable("userId") int userId);

    @PostMapping("/v1/authenticate")
    ResponseEntity<String> getToken(@RequestBody AuthenticationRequest request);
}
