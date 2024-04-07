package com.marketplace.GatewayService.Filter;

import com.marketplace.GatewayService.JWTService.JwtService;
import com.marketplace.GatewayService.Router.RouteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory {

    @Autowired
    private RouteValidator validator;

    @Autowired
    JwtService jwtService;
    public AuthenticationFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange, chain) -> {
           if(validator.isSecured.test(exchange.getRequest())){
                 if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                     throw new RuntimeException("Missing Authorization header");
                 }

                 String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                 if(authHeader!= null && authHeader.startsWith("Bearer ")){
                     authHeader=authHeader.substring(7);
                 }
                 try {
                 jwtService.validateToken(authHeader);
                 }
                 catch (Exception e){
                     throw new RuntimeException("Failed to retrieved details from Login service");
                 }
           }
            return chain.filter(exchange);
        });
    }


    public static class Config{

    }
}
