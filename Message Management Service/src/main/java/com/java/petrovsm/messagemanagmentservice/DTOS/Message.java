package com.java.petrovsm.messagemanagmentservice.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private UUID id;
    private UUID producer_id;
    private String content;
    private long created;
}
