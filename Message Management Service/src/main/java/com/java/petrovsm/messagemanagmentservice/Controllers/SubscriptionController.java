package com.java.petrovsm.messagemanagmentservice.Controllers;

import com.java.petrovsm.messagemanagmentservice.DAL.SubscriptionRepository;
import com.java.petrovsm.messagemanagmentservice.DTOS.Constants;
import com.java.petrovsm.messagemanagmentservice.DTOS.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class SubscriptionController {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    Map<String, Object> response = new HashMap<>();

    @RequestMapping(method = RequestMethod.GET, path = "/subscription/{id}")
    public Mono<ResponseEntity<Map<String, Object>>> getSubscriptionById(@PathVariable("id") String subscriberId) {
        try {
            Subscription subscription = subscriptionRepository.getSubscription(UUID.fromString(subscriberId));
            if (subscription == null || subscription.getSubscriber() == null) {
                response.put(Constants.CODE, "404");
                response.put(Constants.MESSAGE, "Subscription not found");
                response.put(Constants.DATA, new HashMap<>());
            } else {
                response.put(Constants.CODE, "200");
                response.put(Constants.MESSAGE, "Subscription found successfully");
                response.put(Constants.DATA, subscription);
            }
        } catch (Exception e) {
            response.put(Constants.CODE, "500");
            response.put(Constants.MESSAGE, "Error finding subscription: " + e.getMessage());
            response.put(Constants.DATA, new HashMap<>());
        }
        return Mono.just(ResponseEntity.ok()
                .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .header(Constants.ACCEPT, Constants.APPLICATION_JSON)
                .body(response));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/subscription")
    public Mono<ResponseEntity<Map<String, Object>>> createSubscription(@RequestBody Subscription subscription) {
        try {
            boolean result = subscriptionRepository.createSubscription(subscription);
            if (!result) {
                response.put(Constants.CODE, "500");
                response.put(Constants.MESSAGE, "Error creating subscription");
                response.put(Constants.DATA, new HashMap<>());
            } else {
                Subscription createdSubscription = subscriptionRepository.getSubscription(subscription.getSubscriber());
                response.put(Constants.CODE, "201");
                response.put(Constants.MESSAGE, "Subscription created successfully");
                response.put(Constants.DATA, createdSubscription);
            }
        } catch (Exception e) {
            response.put(Constants.CODE, "500");
            response.put(Constants.MESSAGE, "Error creating subscription: " + e.getMessage());
            response.put(Constants.DATA, new HashMap<>());
        }
        return Mono.just(ResponseEntity.ok()
                .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .header(Constants.ACCEPT, Constants.APPLICATION_JSON)
                .body(response));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/subscription/{id}")
    public Mono<ResponseEntity<Map<String, Object>>> updateSubscription(@PathVariable("id") String subscriberId,
                                                                        @RequestBody Subscription updatedSubscription) {
        try {
            // Проверка существования подписки
            Subscription existingSubscription = subscriptionRepository.getSubscription(UUID.fromString(subscriberId));
            if (existingSubscription == null || existingSubscription.getSubscriber() == null) {
                response.put(Constants.CODE, "404");
                response.put(Constants.MESSAGE, "Subscription not found");
                response.put(Constants.DATA, new HashMap<>());
                return Mono.just(ResponseEntity.ok()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                        .header(Constants.ACCEPT, Constants.APPLICATION_JSON)
                        .body(response));
            }

            updatedSubscription.setSubscriber(UUID.fromString(subscriberId));

            boolean result = subscriptionRepository.updateSubscription(updatedSubscription);

            if (!result) {
                response.put(Constants.CODE, "500");
                response.put(Constants.MESSAGE, "Error updating subscription");
                response.put(Constants.DATA, new HashMap<>());
            } else {
                Subscription newSubscription = subscriptionRepository.getSubscription(UUID.fromString(subscriberId));
                response.put(Constants.CODE, "200");
                response.put(Constants.MESSAGE, "Subscription updated successfully");
                response.put(Constants.DATA, newSubscription);
            }
        } catch (Exception e) {
            response.put(Constants.CODE, "500");
            response.put(Constants.MESSAGE, "Error updating subscription: " + e.getMessage());
            response.put(Constants.DATA, new HashMap<>());
        }
        return Mono.just(ResponseEntity.ok()
                .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .header(Constants.ACCEPT, Constants.APPLICATION_JSON)
                .body(response));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/subscription/{id}")
    public Mono<ResponseEntity<Map<String, Object>>> deleteSubscription(@PathVariable("id") String subscriberId) {
        try {
            boolean result = subscriptionRepository.deleteSubscription(UUID.fromString(subscriberId));
            if (!result) {
                response.put(Constants.CODE, "404");
                response.put(Constants.MESSAGE, "Subscription not found or cannot be deleted");
                response.put(Constants.DATA, new HashMap<>());
            } else {
                response.put(Constants.CODE, "200");
                response.put(Constants.MESSAGE, "Subscription deleted successfully");
                response.put(Constants.DATA, new HashMap<>());
            }
        } catch (Exception e) {
            response.put(Constants.CODE, "500");
            response.put(Constants.MESSAGE, "Error deleting subscription: " + e.getMessage());
            response.put(Constants.DATA, new HashMap<>());
        }
        return Mono.just(ResponseEntity.ok()
                .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .header(Constants.ACCEPT, Constants.APPLICATION_JSON)
                .body(response));
    }
}
