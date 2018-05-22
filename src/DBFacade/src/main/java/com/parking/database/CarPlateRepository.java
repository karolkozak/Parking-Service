package com.parking.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CarPlateRepository extends JpaRepository<CarPlate, Long> {

    CarPlate findCarPlateByPlate (String plate);
}
