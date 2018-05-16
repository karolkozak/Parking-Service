package com.parking.database;

import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class CarPlate {

    @Id @GeneratedValue
    private Long id;
    private LocalDateTime localDateTime;
    private String plate;

    protected CarPlate() {}

    public CarPlate(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Long getId() {
        return id;
    }

    public String getPlate() {
        return plate;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
