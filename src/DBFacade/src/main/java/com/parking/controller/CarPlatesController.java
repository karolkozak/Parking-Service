package com.parking.controller;

import com.parking.database.CarPlate;
import com.parking.database.CarPlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class CarPlatesController {

    private final CarPlateService carPlateService;

    @Autowired
    public CarPlatesController(CarPlateService carPlateService) {
        this.carPlateService = carPlateService;
    }

    @GetMapping(value = "/all")
    public List<CarPlate> findAll() {
        return carPlateService.getAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getCarPlate(@PathVariable final Long id) {
        return carPlateService.getCarPlateById(id).<ResponseEntity>map(ResponseEntity::ok).orElseGet(()
                -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no such ID in database"));
    }


    @PostMapping(value = "/savePlaceholder")
    public ResponseEntity savePlaceholder(@RequestBody final String localDateTimeString) {
        try{
            LocalDateTime localDateTime = LocalDateTime.parse(localDateTimeString);
            long id = carPlateService.savePlaceholder(localDateTime);
            return ResponseEntity.ok(id);
        }catch (DateTimeParseException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Send LocalDateTime as string");
        }
    }

    @PostMapping(value = "/savePlate/{id}")
    public ResponseEntity savePlate(@PathVariable final Long id, @RequestBody final String plate){
        try {
            carPlateService.savePlate(Long.valueOf(id), plate);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no " + id + " id in database");
        }
        return ResponseEntity.ok("Car plate " + plate + " saved for id " + id);
    }
}