package com.hristodaskalov.online.store.utils;

public class Constants {

    // field length constants
    public static final int NAME_FIELD_MAX_LENGTH = 128;
    public static final int EMAIL_FIELD_MAX_LENGTH = 128;
    public static final int PASSWORD_FIELD_MAX_LENGTH = 128;
    public static final int PASSWORD_FIELD_MIN_LENGTH = 6;
    public static final int PHONE_FIELD_MAX_LENGTH = 16;
    public static final int CONTENT_FIELD_MAX_LENGTH = 1024;
    public static final int DELIVERY_ADDRESS_FIELD_MAX_LENGTH = 128;

    // field value constraints
    public static final float RATING_MIN_VALUE = 0.00f;
    public static final float RATING_MAX_VALUE = 5.00f;
    public static final int DISCOUNT_MIN_VALUE = 0;
    public static final int DISCOUNT_MAX_VALUE = 100;

    // regex expression constants
    public static final String EMAIL_FIELD_REGEX = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    public static final String NUMERIC_FIELD_REGEX = "-?\\d+(\\.\\d+)?";

    public static final String STATUS_ACCEPTED = "Accepted";
    public static final String STATUS_SHIPPED = "Shipped";
    public static final String STATUS_READY_FOR_PICK_UP = "Ready for pick up";
    public static final String STATUS_DELIVERED = "Delivered";
    public static final String STATUS_CANCELED = "Canceled";
}
