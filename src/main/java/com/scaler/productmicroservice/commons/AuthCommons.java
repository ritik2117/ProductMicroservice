package com.scaler.productmicroservice.commons;

import com.scaler.productmicroservice.dtos.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class AuthCommons {
    private RestTemplate restTemplate;

    public UserDto validateToken(String token) {
        ResponseEntity<UserDto> responseEntity = restTemplate.getForEntity("http://localhost:8081/users/validate/" + token, UserDto.class);
        if (responseEntity.getBody() == null) {
//            Token is invalid
//            TODO: Throw Exception
            return null;
        }
        return responseEntity.getBody();
    }
}
