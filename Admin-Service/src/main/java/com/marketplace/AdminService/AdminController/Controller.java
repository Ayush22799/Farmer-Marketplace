package com.marketplace.AdminService.AdminController;

import com.marketplace.AdminService.AdminService.AdminService;
import com.marketplace.AdminService.DTO.AuthenticationRequest;
import com.marketplace.AdminService.DTO.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class Controller {

    @Autowired
    AdminService service;

    @PostMapping("/add")
    public ResponseEntity<UserInfo> addUsers(@RequestBody UserInfo user) {
        return ResponseEntity.ok(service.addUsers(user));
    }

    @GetMapping("/fetch/user/{username}")
    public ResponseEntity<UserInfo> getUserByUsername(@RequestHeader(value = "Authorization", required = true) String token,@PathVariable("username") String username) {
        return ResponseEntity.ok(service.getUserByUsername(token,username));
    }
    @PutMapping("/update/user/{userId}")
    public ResponseEntity<UserInfo> updateUserDetails(@RequestHeader(value = "Authorization", required = true) String token, @RequestBody UserInfo userInfo, @PathVariable("userId") int id) {
        return ResponseEntity.ok(service.updateUserDetails(token,id, userInfo));
    }

    @DeleteMapping("/delete/user/{userId}")
    public ResponseEntity<String> deleteUser(@RequestHeader(value = "Authorization", required = true) String token, @PathVariable("userId") int id) {
        return ResponseEntity.ok(service.deleteUser(token, id));
    }

    @PostMapping("/generate")
    public String token(@RequestBody AuthenticationRequest request) {
        return service.token(request);
    }
}
