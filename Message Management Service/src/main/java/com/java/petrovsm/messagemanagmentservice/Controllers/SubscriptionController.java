package com.java.petrovsm.messagemanagmentservice.Controllers;

import com.java.petrovsm.messagemanagmentservice.DTOS.Constants;
import com.java.petrovsm.messagemanagmentservice.DTOS.Subscription;
import com.java.petrovsm.messagemanagmentservice.Services.SubscriptionsService;
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
    private SubscriptionsService subscriptionsService;

    Map<String, Object> response = new HashMap<>();

    @RequestMapping(method = RequestMethod.GET, path = Constants.URI_SUBSCRIPTION + "/{subscriber-id}")
    public Mono<ResponseEntity<Map<String, Object>>> getSubscriptionBySubscriberId(
            @PathVariable(value = "subscriber-id", required = true) UUID subscriberId) {
        return subscriptionsService.getSubscriptionsForSubscriberById(subscriberId);
    }

    @RequestMapping(method = RequestMethod.PUT, path = Constants.URI_SUBSCRIPTIONS, consumes = Constants.APPLICATION_JSON)
    public Mono<ResponseEntity<Map<String, Object>>> up(@RequestBody Subscription subscription) {
        return subscriptionsService.updateSubscriptionForSubscriberById(subscription);
    }

    @RequestMapping(method = RequestMethod.POST, path = Constants.URI_SUBSCRIPTIONS, consumes = Constants.APPLICATION_JSON)
    public Mono<ResponseEntity<Map<String, Object>>> createSubscription(@RequestBody Subscription subscription) {
        return subscriptionsService.createSubscription(subscription);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = Constants.URI_SUBSCRIPTION + "/{subscriber-id}")
    public Mono<ResponseEntity<Map<String, Object>>> createSubscription(
            @PathVariable(value = "subscriber-id", required = true) UUID subscriberId) {
        return subscriptionsService.deleteSubscriptionForSubscriberById(subscriberId);
    }
}
