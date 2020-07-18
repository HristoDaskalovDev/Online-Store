package com.hristodaskalov.online.store.utils;

import com.hristodaskalov.online.store.exception.InvalidInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static void fieldNotEmptyOrNull(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(
                    String.format("Field %s cannot be empty", field)
            );
        }
    }

    public static void fieldHasValidLength(String value, int maxLength, String field) {
        if (value.length() > maxLength) {
            throw new InvalidInputException(
                    String.format("Field %s cannot be longer than %d characters.", field, maxLength)
            );
        }
    }

    public static void isValidPositiveNumber(String value){
        Pattern pattern = Pattern.compile(Constants.NUMERIC_FIELD_REGEX);
        Matcher matcher = pattern.matcher(value);

        if (!matcher.matches()) {
            throw new InvalidInputException(
                    String.format("%s is not a valid number.", value)
            );
        }

        if (value.startsWith("-")) {
            throw new InvalidInputException("Value must be larger than 0.");
        }
    }

    public static boolean isSameAsExisting(String oldValue, String newValue) {
        return oldValue.trim().equals(newValue.trim());
    }

    public static void validateEmail(String email) {

        Pattern pattern = Pattern.compile(Constants.EMAIL_FIELD_REGEX);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new InvalidInputException(String.format("[%s] is not a valid email address.", email));
        }
    }

    public static void validatePassword(String password) {
        if (password.length() < Constants.PASSWORD_FIELD_MIN_LENGTH || password.length() > Constants.EMAIL_FIELD_MAX_LENGTH) {
            throw new InvalidInputException(
                    String.format("Password must be between %d and %d characters long.",
                            Constants.PASSWORD_FIELD_MIN_LENGTH,
                            Constants.PASSWORD_FIELD_MAX_LENGTH
                    )
            );
        }
    }
}
