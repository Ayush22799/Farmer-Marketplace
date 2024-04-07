package com.loginService.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loginService.Entity.UserInfo;
import com.loginService.service.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TestLoginServiceController {

    @Autowired
    LoginController loginController;
    @Autowired
    LoginService loginService;

    @Test
    public void testSaveDetail() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(loginService.saveDetail(any(UserInfo.class))).thenReturn(new UserInfo());
        UserInfo userInfo = new UserInfo("FARMER", "Supriya", "SupriyaDon", 9998887770L, "Gramin Bank", 123456789L);
        ResponseEntity<UserInfo> response = loginController.saveDetail(userInfo);

        assertThat(response.getStatusCode().is2xxSuccessful());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
