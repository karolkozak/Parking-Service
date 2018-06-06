package com.parking.contract;

public interface IBlobService {
    boolean uploadImage(byte[] image, String name);
}
