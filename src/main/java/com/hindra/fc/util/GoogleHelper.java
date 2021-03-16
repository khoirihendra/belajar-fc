package com.hindra.fc.util;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GoogleHelper {

    public static String getUserInfo(String googleAccessToken) {
        
        final String uri = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token="
            + googleAccessToken;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
    
        return result;
    }
    
}
