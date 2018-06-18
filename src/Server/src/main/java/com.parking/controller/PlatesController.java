package com.parking.controller;

import com.parking.contract.IBlobService;
import com.parking.contract.IQueueMessageSender;
import com.parking.exceptions.EmptyFIleException;
import com.parking.model.ApiResponseBody;
import com.parking.model.CarPlate;
import com.parking.model.ImageMessage;
import com.parking.service.DbFacadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/plates")
public class PlatesController {

    @Autowired
    private IQueueMessageSender sender;

    @Autowired
    private IBlobService blobService;

    @Autowired
    private DbFacadeService dbFacadeService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponseBody> uploadFile(@RequestBody MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new EmptyFIleException("File is empty");
        }
        String carPlateId = dbFacadeService.addPlate();
        byte[] bytes = file.getBytes();

        boolean succeeded = blobService.uploadImage(bytes, carPlateId);

        if (succeeded) {
            ImageMessage img = new ImageMessage(carPlateId);
            sender.send(img);
            return new ResponseEntity<>(new ApiResponseBody(carPlateId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseBody(carPlateId), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<List<CarPlate>> getPlatesEntities() {
        List<CarPlate> carPlateList = dbFacadeService.getPlates();
        return new ResponseEntity<>(carPlateList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarPlate> getPlateEntity(@PathVariable String id) {
        CarPlate carPlate = dbFacadeService.getPlate(id);
        HttpStatus httpStatus = carPlate.getPlate().equals("") ? HttpStatus.PROCESSING : HttpStatus.OK;
        return new ResponseEntity<>(carPlate, httpStatus);
    }

    @PutMapping("/{id}/exit")
    public ResponseEntity<ApiResponseBody> exitPlate(@PathVariable String id) {
        dbFacadeService.exitPlate(id);
        return new ResponseEntity<>(new ApiResponseBody("Exited successfully"), HttpStatus.OK);
    }
}
