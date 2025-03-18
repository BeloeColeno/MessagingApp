package com.java.petrovsm.messagemanagmentservice.DTOS;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Constants only class");
    }

    // HTTP Section
    public static final String CODE = "code";
    public static final String MESSAGE = "message";
    public static final String DATA = "data";

    // HEADERS Section
    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String ACCEPT = "Accept";

    // Database Section
    public static final String DB = "`mms`";
    public static final String TABLES_SUBSCRIBERS = "`subscribers`";
    public static final String TABLES_PRODUCERS = "`producers`";
    public static final String TABLES_MESSAGES = "`messages`";
    public static final String TABLES_SUBSCRIPTIONS = "`subscriptions`";

    public static final String GET_MESSAGE_BY_ID = "SELECT * FROM " + TABLES_MESSAGES +
            "LEFT JOIN " + TABLES_PRODUCERS + " ON " + TABLES_MESSAGES + ".`producer_id` = " + TABLES_PRODUCERS + ".`producer_id` " +
            "LEFT JOIN " + TABLES_SUBSCRIPTIONS + " ON " + TABLES_PRODUCERS + ".`producer_id` = " + TABLES_SUBSCRIPTIONS + ".`producer_id` " +
            "LEFT JOIN " + TABLES_SUBSCRIBERS + " ON " + TABLES_SUBSCRIPTIONS + ".`subscriber_id` = " + TABLES_SUBSCRIBERS + ".`subscriber_id` " +
            "WHERE " + TABLES_MESSAGES + ".`id` = UUID_TO_BIN(?)";

    public static final String GET_MESSAGES_FOR_PRODUCER = "SELECT * FROM " + TABLES_MESSAGES +
            "LEFT JOIN " + TABLES_PRODUCERS + " ON " + TABLES_MESSAGES + ".`producer_id` = " + TABLES_PRODUCERS + ".`producer_id` " +
            "LEFT JOIN " + TABLES_SUBSCRIPTIONS + " ON " + TABLES_PRODUCERS + ".`producer_id` = " + TABLES_SUBSCRIPTIONS + ".`producer_id` " +
            "LEFT JOIN " + TABLES_SUBSCRIBERS + " ON " + TABLES_SUBSCRIPTIONS + ".`subscriber_id` = " + TABLES_SUBSCRIBERS + ".`subscriber_id` " +
            "WHERE " + TABLES_PRODUCERS + ".`producer_id` = UUID_TO_BIN(?)";

    public static final String GET_MESSAGES_FOR_SUBSCRIBER = "SELECT * FROM " + TABLES_MESSAGES +
            "LEFT JOIN " + TABLES_PRODUCERS + " ON " + TABLES_MESSAGES + ".`producer_id` = " + TABLES_PRODUCERS + ".`producer_id` " +
            "LEFT JOIN " + TABLES_SUBSCRIPTIONS + " ON " + TABLES_PRODUCERS + ".`producer_id` = " + TABLES_SUBSCRIPTIONS + ".`producer_id` " +
            "LEFT JOIN " + TABLES_SUBSCRIBERS + " ON " + TABLES_SUBSCRIPTIONS + ".`subscriber_id` = " + TABLES_SUBSCRIBERS + ".`subscriber_id` " +
            "WHERE " + TABLES_SUBSCRIBERS + ".`subscriber_id` = UUID_TO_BIN(?)";

    public static final String CREATE_MESSAGE = "INSERT INTO " + TABLES_MESSAGES +
            " (`id`, `producer_id`, `content`, `created`) VALUES " +
            "(UUID_TO_BIN(?), UUID_TO_BIN(?), ?, ?)";

    public static final String CREATE_PRODUCER = "INSERT INTO " + TABLES_PRODUCERS +
            " (`producer_id`) VALUES " +
            "(UUID_TO_BIN(?))";

    public static final String DELETE_MESSAGE = "DELETE FROM " + TABLES_MESSAGES + " WHERE `id` = UUID_TO_BIN(?)";
}
