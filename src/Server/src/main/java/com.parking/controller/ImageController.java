package com.parking.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/image")
public class ImageController {

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }
        String imageId = UUID.randomUUID().toString();
        byte[] bytes = file.getBytes();

        // TODO: Do something with bytes

        return imageId;
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