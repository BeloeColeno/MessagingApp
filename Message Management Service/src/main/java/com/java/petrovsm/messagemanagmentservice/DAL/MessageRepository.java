package com.java.petrovsm.messagemanagmentservice.DAL;

import java.util.List;
import java.util.UUID;
import com.java.petrovsm.messagemanagmentservice.DTOS.Message;

    public interface MessageRepository {
        public Message getMessageById(UUID messageId);
        public List<Message> getMessagesForProducerById(UUID producerId);
        public List<Message> getMessagesForSubscriberById(UUID subscriberId);
        public UUID createMessage(Message message);
        public int deleteMessageById(UUID messageId);
    }
