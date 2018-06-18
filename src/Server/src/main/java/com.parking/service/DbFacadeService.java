package com.parking.service;

import com.parking.model.CarPlate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class DbFacadeService {
    @Value("${dbFacade.url}")
    private String dbFacedeUrl;

    public List<CarPlate> getPlates() {
        RestTemplate restTemplate = new RestTemplate();
        return Arrays.asList(restTemplate.getForObject(dbFacedeUrl.concat("/plates"), CarPlate[].class));
    }

    public CarPlate getPlate(String id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(dbFacedeUrl.concat("/plates/").concat(id), CarPlate.class);
    }

    public String exitPlate(String id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = dbFacedeUrl.concat("/plates/").concat(id).concat("/exit");
        ResponseEntity<String> response = restTemplate
                .exchange(url, HttpMethod.PUT, new HttpEntity<>(LocalDateTime.now().toString()), String.class);
        return response.getBody();
    }

    public String addPlate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(dbFacedeUrl.concat("/plates"), LocalDateTime.now().toString(), String.class);
    }
}
