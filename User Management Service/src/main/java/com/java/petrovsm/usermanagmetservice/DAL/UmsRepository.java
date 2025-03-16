package com.java.petrovsm.usermanagmetservice.DAL;

import com.java.petrovsm.usermanagmetservice.DTOS.Role;
import com.java.petrovsm.usermanagmetservice.DTOS.User;
import java.util.Map;
import java.util.UUID;

public interface UmsRepository {
    Map<UUID, User> findAllUsers();
    Map<String, Role> findAllRoles();
    User findUserById(UUID userId);
    UUID createUser(User user);
    int deleteUser(UUID userId);
}
