package com.parking.database;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CarPlateService {
    private CarPlateRepository carPlateRepository;

    public CarPlateService(CarPlateRepository carPlateRepository){
        this.carPlateRepository = carPlateRepository;
    }

    public Long savePlaceholder(LocalDateTime entryTimestamp){
        CarPlate carPlate = new CarPlate(entryTimestamp);
        return carPlateRepository.save(carPlate).getId();
    }

    public void savePlate(Long id, String plate){
        CarPlate carPlate = carPlateRepository.getOne(id);
        carPlate.setPlate(plate);
        carPlateRepository.save(carPlate);
    }

    public void saveExitTime(Long id, LocalDateTime exitTimestamp){
        CarPlate carPlate = carPlateRepository.getOne(id);
        carPlate.setExitTimestamp(exitTimestamp);
        carPlateRepository.save(carPlate);
    }
    public Optional<CarPlate> getCarPlateById(Long id){
        return carPlateRepository.findById(id);
    }

    public List<CarPlate> getAll() {
        return carPlateRepository.findAll();
    }
}
