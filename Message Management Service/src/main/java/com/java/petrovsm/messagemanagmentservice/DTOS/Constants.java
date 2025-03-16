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
    public static final String SUBSCRIBERS = "`subscribers`";
    public static final String PRODUCERS = "`producers`";
    public static final String MESSAGES = "`messages`";
    public static final String SUBSCRIPTIONS = "`subscriptions`";
    public static final String GET_MESSAGE_BY_ID = "";
    public static final String GET_MESSAGES_FOR_PRODUCER = "";
    public static final String GET_MESSAGES_FOR_SUBSCRIBER = "";
    public static final String CREATE_MESSAGE = "";
    public static final String CREATE_PRODUCER = "";
    public static final String DELETE_MESSAGE = "";
}
