package com.java.petrovsm.usermanagmetservice.controllers;

import com.java.petrovsm.usermanagmetservice.dal.UmsRepository;
import com.java.petrovsm.usermanagmetservice.dtos.Constants;
import com.java.petrovsm.usermanagmetservice.dtos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
}

