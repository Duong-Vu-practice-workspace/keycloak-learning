package com.demo.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Service2Client {
    private final RestTemplate restTemplate;
    private final OAuth2AuthorizedClientManager manager;
    @Value("${service2.url}")
    String service2Url;

    public Service2Client(RestTemplate restTemplate, OAuth2AuthorizedClientManager manager) {
        this.restTemplate = restTemplate;
        this.manager = manager;
    }
    public String fetchData() {
//        var authRequest = OAuth2AuthorizeRequest.withClientRegistrationId("keycloak-client")
//                .principal("machine")
//                .build();
//        var client = manager.authorize(authRequest);
//        String token = client.getAccessToken().getTokenValue();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(token);
//
//        var response = restTemplate.exchange(
//                service2Url + "/data",
//                HttpMethod.GET,
//                new HttpEntity<>(headers),
//                String.class
//        );
//        return response.getBody();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String incomingToken = null;
        if (auth instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            incomingToken = jwtAuthenticationToken.getToken().getTokenValue();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(incomingToken);

        var response = restTemplate.exchange(
                service2Url + "/data",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        );
        return response.getBody();
    }
}
