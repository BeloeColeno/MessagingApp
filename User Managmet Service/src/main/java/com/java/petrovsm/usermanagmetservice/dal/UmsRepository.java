package com.java.petrovsm.usermanagmetservice.dal;

import com.java.petrovsm.usermanagmetservice.dtos.Role;
import com.java.petrovsm.usermanagmetservice.dtos.User;
import java.util.Map;
import java.util.UUID;

public interface UmsRepository {
    Map<UUID, User> findAllUsers();
    Map<String, Role> findAllRoles();
    User findUserById(UUID userId);
    UUID createUser(User user);
    int deleteUser(UUID userId);
}
