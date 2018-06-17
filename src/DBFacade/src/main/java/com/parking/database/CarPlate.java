package com.parking.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class CarPlate {

    @Id @GeneratedValue
    private Long id;
    private LocalDateTime entryTimestamp;
    private String plate;
    private LocalDateTime exitTimestamp;

    protected CarPlate() {}

    public CarPlate(LocalDateTime entryTimestamp) {
        this.entryTimestamp = entryTimestamp;
    }

    public Long getId() {
        return id;
    }

    public String getPlate() {
        return plate;
    }

    public LocalDateTime getEntryTimestamp() {
        return entryTimestamp;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public LocalDateTime getExitTimestamp() {
        return exitTimestamp;
    }

    public void setExitTimestamp(LocalDateTime exitTimestamp) {
        this.exitTimestamp = exitTimestamp;
    }
}
