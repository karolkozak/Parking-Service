package com.parking.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CarPlate {
    private Long id;
    private LocalDateTime entryTimestamp;
    private String plate;
    private LocalDateTime exitTimestamp;
}
