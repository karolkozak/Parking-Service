package com.parking.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.servicebus.IQueueClient;
import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.QueueClient;
import com.microsoft.azure.servicebus.ReceiveMode;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import com.parking.contract.IQueueMessageSender;
import com.parking.model.ImageMessage;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class QueueMessageSender implements IQueueMessageSender {

    private IQueueClient queueClient;
    private static final Logger LOGGER = Logger.getLogger(QueueMessageSender.class);

    public QueueMessageSender(@Value("${azure.servicebus.connection-string}") String connectionString,
                              @Value("${azure.servicebus.queue-name}") String queueName) {
        try {
            queueClient = new QueueClient(new ConnectionStringBuilder(connectionString, queueName), ReceiveMode.PEEKLOCK);
        } catch (InterruptedException | ServiceBusException e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(e.getMessage());
            }
        }
    }

    @Override
    public boolean send(ImageMessage message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(message);
            //byte[] data = SerializationUtils.serialize(message);
            final Message msg = new Message(jsonInString);
            queueClient.send(msg);
            return true;
        } catch (InterruptedException | ServiceBusException e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(e.getMessage());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
