package com.java.petrovsm.messagemanagmentservice.Controllers;

import com.java.petrovsm.messagemanagmentservice.DTOS.Constants;
import com.java.petrovsm.messagemanagmentservice.DTOS.Message;

import com.java.petrovsm.messagemanagmentservice.Services.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


import java.util.Map;
import java.util.UUID;

@RestController
public class MessageController {

    @Autowired
    private MessagesService messages;

    @RequestMapping(method = RequestMethod.GET, path = Constants.URI_MESSAGE + "/{message-id}")
    public Mono<ResponseEntity<Map<String, Object>>> getMessageById(
            @PathVariable(value = "message-id", required = true) String messageId) {
        return messages.getMessageById(UUID.fromString(messageId));
    }

    @RequestMapping(method = RequestMethod.GET, path = Constants.URI_PRODUCER + "/{producer-id}")
    public Mono<ResponseEntity<Map<String, Object>>> getMessagesForProducerById(
            @PathVariable(value = "producer-id", required = true) String producerId) {
        return messages.getMessagesForProducerById(UUID.fromString(producerId));
    }

    @RequestMapping(method = RequestMethod.GET, path = Constants.URI_SUBSCRIBER + "/{subscriber-id}")
    public Mono<ResponseEntity<Map<String, Object>>> getMessagesForSubscriberById(
            @PathVariable(value = "subscriber-id", required = true) String subscriberId) {
        return messages.getMessagesForSubscriberById(UUID.fromString(subscriberId));
    }

    @RequestMapping(method = RequestMethod.POST, path = Constants.URI_MESSAGE, consumes = Constants.APPLICATION_JSON)
    public Mono<ResponseEntity<Map<String, Object>>> createMessage(@RequestBody Message message) {
        return messages.createMessage(message);
    }

    @RequestMapping(method = RequestMethod.PUT, path = Constants.URI_MESSAGE + "/{message-id}", consumes = Constants.APPLICATION_JSON)
    public Mono<ResponseEntity<Map<String, Object>>> updateMessageById(
            @PathVariable(value = "message-id", required = true) String messageId,
            @RequestBody Message message) {
        message.setId(UUID.fromString(messageId));
        return messages.updateMessage(message);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = Constants.URI_MESSAGE + "/{message-id}")
    public Mono<ResponseEntity<Map<String, Object>>> deleteMessageById(
            @PathVariable(value = "message-id", required = true) String messageId) {
        return messages.deleteMessageById(UUID.fromString(messageId));
    }
}