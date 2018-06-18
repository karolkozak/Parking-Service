package com.parking.service;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.*;
import com.parking.contract.IBlobService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

@Service
public class BlobService implements IBlobService {

    private CloudBlobContainer container;
    private static final Logger LOGGER = Logger.getLogger(BlobService.class);

    public BlobService(@Value("${azure.storage.container-name}") String containerName, @Value("${azure.storage.connection-string}") String connectionString) {
        try {
        CloudStorageAccount storageAccount = CloudStorageAccount.parse(connectionString);
        CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

            container = blobClient.getContainerReference(containerName);
        } catch (URISyntaxException | StorageException | InvalidKeyException e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(e.getMessage());
            }
        }
    }

    public boolean uploadImage(byte[] image, String name) {
        try {
            CloudBlockBlob blob = container.getBlockBlobReference(name);

            BlobProperties props = blob.getProperties();
            props.setContentType("image/png"); // TODO: maybe image/jpeg

            blob.uploadFromByteArray(image, 0, image.length);

            return true;


        } catch (URISyntaxException | StorageException | IOException e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(e.getMessage());
            }
            return false;
        }


    }
}