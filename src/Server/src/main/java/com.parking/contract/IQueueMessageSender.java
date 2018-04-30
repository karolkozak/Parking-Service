package com.parking.contract;

import com.parking.model.ImageMessage;

public interface IQueueMessageSender {
    boolean send(ImageMessage message);
}
