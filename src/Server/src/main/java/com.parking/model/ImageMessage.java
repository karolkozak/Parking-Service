package com.parking.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ImageMessage implements Serializable {
    private String message;
    private byte[] image;
}
