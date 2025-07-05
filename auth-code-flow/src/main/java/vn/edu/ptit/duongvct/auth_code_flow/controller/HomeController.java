package vn.edu.ptit.duongvct.auth_code_flow.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home(OAuth2AuthenticationToken token){
        String email = token.getPrincipal().getAttribute("email");
        String name = token.getPrincipal().getAttribute("name");
        return email + " Welcome, Hello Keycloak! Your token: " + token.getName() + ", " + token.getPrincipal();
    }
}
