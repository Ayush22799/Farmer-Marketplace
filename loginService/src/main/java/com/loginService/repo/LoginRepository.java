package com.loginService.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loginService.Entity.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<UserInfo,Integer>{
 Optional<UserInfo> findByuserName(String username);
}
