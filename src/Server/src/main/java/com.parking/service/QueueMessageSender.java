package com.parking.service;

import com.microsoft.azure.servicebus.IQueueClient;
import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.QueueClient;
import com.microsoft.azure.servicebus.ReceiveMode;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import com.parking.contract.IQueueMessageSender;
import com.parking.model.ImageMessage;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class QueueMessageSender implements IQueueMessageSender {

    private IQueueClient queueClient;

    public QueueMessageSender(@Value("${azure.servicebus.connection-string}") String connectionString,
                              @Value("${azure.servicebus.queue-name}") String queueName) {
        try {
            queueClient = new QueueClient(new ConnectionStringBuilder(connectionString, queueName), ReceiveMode.PEEKLOCK);
        } catch (InterruptedException | ServiceBusException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean send(ImageMessage message) {
        try {
            byte[] data = SerializationUtils.serialize(message);
            final Message msg = new Message(data);
            queueClient.send(msg);
            return true;
        } catch (InterruptedException | ServiceBusException e) {
            e.printStackTrace();
        }
        return false;
    }
}
