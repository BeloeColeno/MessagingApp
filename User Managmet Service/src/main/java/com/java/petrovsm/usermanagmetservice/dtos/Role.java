package com.java.petrovsm.usermanagmetservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    UUID roleId;
    String role;
    String description;
}
