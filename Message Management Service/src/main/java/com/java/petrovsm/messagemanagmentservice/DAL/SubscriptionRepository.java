package com.java.petrovsm.messagemanagmentservice.DAL;

import com.java.petrovsm.messagemanagmentservice.DTOS.Subscription;

import java.util.UUID;

public interface SubscriptionRepository {
    public Subscription getSubscription(UUID subscriberId);
    public boolean createSubscription(Subscription subscription);
    public boolean updateSubscription(Subscription subscription);
    public boolean deleteSubscription(UUID subscriberId);
}
