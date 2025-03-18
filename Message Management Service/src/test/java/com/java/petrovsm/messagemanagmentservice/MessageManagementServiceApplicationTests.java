package com.java.petrovsm.messagemanagmentservice;

import com.java.petrovsm.messagemanagmentservice.DTOS.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class QueryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testGetMessageById() {
        UUID messageId = UUID.fromString("462894b2-b5a1-4088-b7af-701c71d6d304");

        List<Map<String, Object>> results = jdbcTemplate.queryForList(
                Constants.GET_MESSAGE_BY_ID,
                messageId.toString()
        );
        System.out.println(results);

        assertNotNull(results);
    }

    @Test
    void testGetMessagesForProducer() {
        UUID producerId = UUID.fromString("6e27ea06-a716-4c89-af88-813749a8bd48");

        List<Map<String, Object>> results = jdbcTemplate.queryForList(
                Constants.GET_MESSAGES_FOR_PRODUCER,
                producerId.toString()
        );
        System.out.println(results);

        assertNotNull(results);
    }

    @Test
    void testGetMessagesForSubscriber() {
        UUID subscriberId = UUID.fromString("70a64b54-43c3-4c18-bbec-64590ff7e0cc");

        List<Map<String, Object>> results = jdbcTemplate.queryForList(
                Constants.GET_MESSAGES_FOR_SUBSCRIBER,
                subscriberId.toString()
        );
        System.out.println(results);

        assertNotNull(results);
    }
}
