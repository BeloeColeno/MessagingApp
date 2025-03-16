package com.java.petrovsm.usermanagmetservice.controllers;

import com.java.petrovsm.usermanagmetservice.dal.UmsRepository;
import com.java.petrovsm.usermanagmetservice.dtos.Constants;
import com.java.petrovsm.usermanagmetservice.dtos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UmsRepository umsRepository;

    Map<String, Object> response = new HashMap<>();

    @RequestMapping(method = RequestMethod.GET, path = "/users")
    public Mono<ResponseEntity<Map<String, Object>>> getAllUsers() {
        Map<UUID, User> users = umsRepository.findAllUsers();
        if (users == null) {
            response.put(Constants.CODE, "500");
            response.put(Constants.MESSAGE, "Users have not been retrieved");
            response.put(Constants.DATA, new HashMap<>());
        } else {
            response.put(Constants.CODE, "200");
            response.put(Constants.MESSAGE, "List of Users has been requested successfully");
            response.put(Constants.DATA, new ArrayList<>(users.values()));
        }
        return Mono.just(ResponseEntity.ok().header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .header(Constants.ACCEPT, Constants.APPLICATION_JSON).body(response));

    }

    @RequestMapping(method = RequestMethod.GET, path = "/user/{id}")
    public Mono<ResponseEntity<Map<String, Object>>> getUserById(@PathVariable("id") String userId) {
        try {
            User user = umsRepository.findUserById(UUID.fromString(userId));
            if (user.getId() == null) {
                response.put(Constants.CODE, "404");
                response.put(Constants.MESSAGE, "Пользователь не найден");
                response.put(Constants.DATA, new HashMap<>());
            } else {
                response.put(Constants.CODE, "200");
                response.put(Constants.MESSAGE, "Пользователь успешно найден");
                response.put(Constants.DATA, user);
            }
        } catch (Exception e) {
            response.put(Constants.CODE, "500");
            response.put(Constants.MESSAGE, "Ошибка при поиске пользователя: " + e.getMessage());
            response.put(Constants.DATA, new HashMap<>());
        }
        return Mono.just(ResponseEntity.ok()
                .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .header(Constants.ACCEPT, Constants.APPLICATION_JSON)
                .body(response));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/user")
    public Mono<ResponseEntity<Map<String, Object>>> createUser(@RequestBody User user) {
        try {
            UUID newUserId = umsRepository.createUser(user);
            if (newUserId == null) {
                response.put(Constants.CODE, "500");
                response.put(Constants.MESSAGE, "Ошибка при создании пользователя");
                response.put(Constants.DATA, new HashMap<>());
            } else {
                User createdUser = umsRepository.findUserById(newUserId);
                response.put(Constants.CODE, "201");
                response.put(Constants.MESSAGE, "Пользователь успешно создан");
                response.put(Constants.DATA, createdUser);
            }
        } catch (Exception e) {
            response.put(Constants.CODE, "500");
            response.put(Constants.MESSAGE, "Ошибка при создании пользователя: " + e.getMessage());
            response.put(Constants.DATA, new HashMap<>());
        }
        return Mono.just(ResponseEntity.ok()
                .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .header(Constants.ACCEPT, Constants.APPLICATION_JSON)
                .body(response));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/user/{id}")
    public Mono<ResponseEntity<Map<String, Object>>> deleteUser(@PathVariable("id") String userId) {
        try {
            int result = umsRepository.deleteUser(UUID.fromString(userId));
            if (result <= 0) {
                response.put(Constants.CODE, "404");
                response.put(Constants.MESSAGE, "Пользователь не найден или не может быть удален");
                response.put(Constants.DATA, new HashMap<>());
            } else {
                response.put(Constants.CODE, "200");
                response.put(Constants.MESSAGE, "Пользователь успешно удален");
                response.put(Constants.DATA, new HashMap<>());
            }
        } catch (Exception e) {
            response.put(Constants.CODE, "500");
            response.put(Constants.MESSAGE, "Ошибка при удалении пользователя: " + e.getMessage());
            response.put(Constants.DATA, new HashMap<>());
        }
        return Mono.just(ResponseEntity.ok()
                .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .header(Constants.ACCEPT, Constants.APPLICATION_JSON)
                .body(response));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/user/{id}")
    public Mono<ResponseEntity<Map<String, Object>>> updateUser(@PathVariable("id") String userId, @RequestBody User updatedUser) {
        try {

            User existingUser = umsRepository.findUserById(UUID.fromString(userId));
            if (existingUser.getId() == null) {
                response.put(Constants.CODE, "404");
                response.put(Constants.MESSAGE, "Пользователь не найден");
                response.put(Constants.DATA, new HashMap<>());
                return Mono.just(ResponseEntity.ok()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                        .header(Constants.ACCEPT, Constants.APPLICATION_JSON)
                        .body(response));
            }

            umsRepository.deleteUser(UUID.fromString(userId));

            UUID newUserId = umsRepository.createUser(updatedUser);

            if (newUserId == null) {
                response.put(Constants.CODE, "500");
                response.put(Constants.MESSAGE, "Ошибка при обновлении пользователя");
                response.put(Constants.DATA, new HashMap<>());
            } else {
                User newUser = umsRepository.findUserById(newUserId);
                response.put(Constants.CODE, "200");
                response.put(Constants.MESSAGE, "Пользователь успешно обновлен");
                response.put(Constants.DATA, newUser);
            }
        } catch (Exception e) {
            response.put(Constants.CODE, "500");
            response.put(Constants.MESSAGE, "Ошибка при обновлении пользователя: " + e.getMessage());
            response.put(Constants.DATA, new HashMap<>());
        }
        return Mono.just(ResponseEntity.ok()
                .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .header(Constants.ACCEPT, Constants.APPLICATION_JSON)
                .body(response));
    }
}

