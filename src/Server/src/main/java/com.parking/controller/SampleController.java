package com.parking.controller;

import com.parking.contract.IQueueMessageSender;
import com.parking.model.ApiResponseBody;
import com.parking.model.ImageMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/sample")
@RestController
public class SampleController {

    @Autowired
    private IQueueMessageSender sender;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ApiResponseBody> getSampleResponse() {
         ImageMessage msg = new ImageMessage("abc", "test bytes".getBytes());
         boolean succeeded = sender.send(msg);
        return new ResponseEntity<>(new ApiResponseBody("SampleResponse"), HttpStatus.OK);
    }
}
