package com.parking.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ImageMessage implements Serializable {
    private static final long serialVersionUID = 4943003061558382236L;
    private String filename;
    //private byte[] image;
}
