package com.java.petrovsm.messagemanagmentservice.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    private UUID subscriber;
    private List<UUID> producers = new ArrayList<>();

    public void addProducer(UUID producerId) {
        this.producers.add(producerId);
    }
}
