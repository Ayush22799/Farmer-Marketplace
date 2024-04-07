package com.marketplace.AdminService.Fiegn;

import com.marketplace.AdminService.DTO.AuthenticationRequest;
import com.marketplace.AdminService.DTO.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "user", url = "http://localhost:8081/auth")
public interface LoginServiceFeign {
    @PostMapping("/v1/authenticate")
    String token(@RequestBody AuthenticationRequest request);

    @PostMapping("/v1/addDetail")
    ResponseEntity<UserInfo> addUsers(@RequestBody UserInfo user);

    @GetMapping("/login/User/{username}")
    ResponseEntity<UserInfo> getUserByUsername(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @PathVariable("username") String username);

    @PutMapping("/login/updateDetail/{userId}")
    ResponseEntity<UserInfo> updateUserDetails(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @PathVariable("userId") int userId, @RequestBody UserInfo userInfo);

    @DeleteMapping("/login/delete/User/{userId}")
    ResponseEntity<String> deleteUser(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @PathVariable("userId") int id);

    @GetMapping("/v1/User/{userId}")
    ResponseEntity<UserInfo> getUserDetailsById(@PathVariable int userId);
}
