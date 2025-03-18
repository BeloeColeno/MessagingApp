package com.java.petrovsm.messagemanagmentservice.Controllers;

import com.java.petrovsm.messagemanagmentservice.DAL.MessageRepository;
import com.java.petrovsm.messagemanagmentservice.DTOS.Constants;
import com.java.petrovsm.messagemanagmentservice.DTOS.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    Map<String, Object> response = new HashMap<>();

    @RequestMapping(method = RequestMethod.GET, path = "/message/{id}")
    public Mono<ResponseEntity<Map<String, Object>>> getMessageById(@PathVariable("id") String messageId) {
        try {
            Message message = messageRepository.getMessageById(UUID.fromString(messageId));
            if (message == null) {
                response.put(Constants.CODE, "404");
                response.put(Constants.MESSAGE, "Message not found");
                response.put(Constants.DATA, new HashMap<>());
            } else {
                response.put(Constants.CODE, "200");
                response.put(Constants.MESSAGE, "Message found successfully");
                response.put(Constants.DATA, message);
            }
        } catch (Exception e) {
            response.put(Constants.CODE, "500");
            response.put(Constants.MESSAGE, "Error finding message: " + e.getMessage());
            response.put(Constants.DATA, new HashMap<>());
        }
        return Mono.just(ResponseEntity.ok()
                .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .header(Constants.ACCEPT, Constants.APPLICATION_JSON)
                .body(response));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/producer/{id}/messages")
    public Mono<ResponseEntity<Map<String, Object>>> getMessagesForProducer(@PathVariable("id") String producerId) {
        try {
            List<Message> messages = messageRepository.getMessagesForProducerById(UUID.fromString(producerId));
            if (messages == null || messages.isEmpty()) {
                response.put(Constants.CODE, "404");
                response.put(Constants.MESSAGE, "No messages found for this producer");
                response.put(Constants.DATA, new HashMap<>());
            } else {
                response.put(Constants.CODE, "200");
                response.put(Constants.MESSAGE, "Messages found successfully");
                response.put(Constants.DATA, messages);
            }
        } catch (Exception e) {
            response.put(Constants.CODE, "500");
            response.put(Constants.MESSAGE, "Error finding messages: " + e.getMessage());
            response.put(Constants.DATA, new HashMap<>());
        }
        return Mono.just(ResponseEntity.ok()
                .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .header(Constants.ACCEPT, Constants.APPLICATION_JSON)
                .body(response));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/subscriber/{id}/messages")
    public Mono<ResponseEntity<Map<String, Object>>> getMessagesForSubscriber(@PathVariable("id") String subscriberId) {
        try {
            List<Message> messages = messageRepository.getMessagesForSubscriberById(UUID.fromString(subscriberId));
            if (messages == null || messages.isEmpty()) {
                response.put(Constants.CODE, "404");
                response.put(Constants.MESSAGE, "No messages found for this subscriber");
                response.put(Constants.DATA, new HashMap<>());
            } else {
                response.put(Constants.CODE, "200");
                response.put(Constants.MESSAGE, "Messages found successfully");
                response.put(Constants.DATA, messages);
            }
        } catch (Exception e) {
            response.put(Constants.CODE, "500");
            response.put(Constants.MESSAGE, "Error finding messages: " + e.getMessage());
            response.put(Constants.DATA, new HashMap<>());
        }
        return Mono.just(ResponseEntity.ok()
                .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .header(Constants.ACCEPT, Constants.APPLICATION_JSON)
                .body(response));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/message")
    public Mono<ResponseEntity<Map<String, Object>>> createMessage(@RequestBody Message message) {
        try {
            UUID messageId = messageRepository.createMessage(message);
            if (messageId == null) {
                response.put(Constants.CODE, "500");
                response.put(Constants.MESSAGE, "Error creating message");
                response.put(Constants.DATA, new HashMap<>());
            } else {
                Message createdMessage = messageRepository.getMessageById(messageId);
                response.put(Constants.CODE, "201");
                response.put(Constants.MESSAGE, "Message created successfully");
                response.put(Constants.DATA, createdMessage);
            }
        } catch (Exception e) {
            response.put(Constants.CODE, "500");
            response.put(Constants.MESSAGE, "Error creating message: " + e.getMessage());
            response.put(Constants.DATA, new HashMap<>());
        }
        return Mono.just(ResponseEntity.ok()
                .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .header(Constants.ACCEPT, Constants.APPLICATION_JSON)
                .body(response));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/message/{id}")
    public Mono<ResponseEntity<Map<String, Object>>> deleteMessage(@PathVariable("id") String messageId) {
        try {
            int result = messageRepository.deleteMessageById(UUID.fromString(messageId));
            if (result <= 0) {
                response.put(Constants.CODE, "404");
                response.put(Constants.MESSAGE, "Message not found or cannot be deleted");
                response.put(Constants.DATA, new HashMap<>());
            } else {
                response.put(Constants.CODE, "200");
                response.put(Constants.MESSAGE, "Message deleted successfully");
                response.put(Constants.DATA, new HashMap<>());
            }
        } catch (Exception e) {
            response.put(Constants.CODE, "500");
            response.put(Constants.MESSAGE, "Error deleting message: " + e.getMessage());
            response.put(Constants.DATA, new HashMap<>());
        }
        return Mono.just(ResponseEntity.ok()
                .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .header(Constants.ACCEPT, Constants.APPLICATION_JSON)
                .body(response));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/message/{id}")
    public Mono<ResponseEntity<Map<String, Object>>> updateMessage(@PathVariable("id") String messageId, @RequestBody Message updatedMessage) {
        try {
            Message existingMessage = messageRepository.getMessageById(UUID.fromString(messageId));
            if (existingMessage == null) {
                response.put(Constants.CODE, "404");
                response.put(Constants.MESSAGE, "Message not found");
                response.put(Constants.DATA, new HashMap<>());
                return Mono.just(ResponseEntity.ok()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                        .header(Constants.ACCEPT, Constants.APPLICATION_JSON)
                        .body(response));
            }

            messageRepository.deleteMessageById(UUID.fromString(messageId));

            UUID newMessageId = messageRepository.createMessage(updatedMessage);

            if (newMessageId == null) {
                response.put(Constants.CODE, "500");
                response.put(Constants.MESSAGE, "Error updating message");
                response.put(Constants.DATA, new HashMap<>());
            } else {
                Message newMessage = messageRepository.getMessageById(newMessageId);
                response.put(Constants.CODE, "200");
                response.put(Constants.MESSAGE, "Message updated successfully");
                response.put(Constants.DATA, newMessage);
            }
        } catch (Exception e) {
            response.put(Constants.CODE, "500");
            response.put(Constants.MESSAGE, "Error updating message: " + e.getMessage());
            response.put(Constants.DATA, new HashMap<>());
        }
        return Mono.just(ResponseEntity.ok()
                .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .header(Constants.ACCEPT, Constants.APPLICATION_JSON)
                .body(response));
    }
}