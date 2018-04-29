package com.parking.controller;

import com.parking.model.ApiResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/sample")
@RestController
public class SampleController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ApiResponseBody> getSampleResponse() {
        return new ResponseEntity<>(new ApiResponseBody("SampleResponse"), HttpStatus.OK);
    }
}
