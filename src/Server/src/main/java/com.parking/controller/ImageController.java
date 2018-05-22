package com.parking.controller;

import com.parking.contract.IQueueMessageSender;
import com.parking.exceptions.EmptyFIleException;
import com.parking.model.ApiResponseBody;
import com.parking.model.ImageMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private IQueueMessageSender sender;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponseBody> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new EmptyFIleException("File is empty");
        }
        String imageId = UUID.randomUUID().toString();
        byte[] bytes = file.getBytes();
        ImageMessage img = new ImageMessage(imageId, bytes);
        sender.send(img);

        // TODO: Do something with bytes
//        boolean succeeded = sender.send(msg);

        return new ResponseEntity<>(new ApiResponseBody(imageId), HttpStatus.OK);
    }

    @GetMapping("/{imageId}/ready")
    public boolean isReady(@PathVariable String imageId) {
        // TODO: Check if image with a given ID has already been processed
        return true;
    }

    @GetMapping("/{imageId}")
    public String getResult(@PathVariable String imageId) {
        // TODO: Return the result of license plate recognition for a given image
        return "KR 552VY";
    }
}