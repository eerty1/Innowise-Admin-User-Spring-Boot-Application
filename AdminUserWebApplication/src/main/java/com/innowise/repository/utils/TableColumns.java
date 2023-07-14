package com.innowise.repository.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TableColumns {
    ID("id"),
    USERNAME("username"),
    PASSWORD("password"),
    ROLE("role"),
    FULL_NAME("full_name"),
    POSITION("position"),
    DEPARTMENT("department"),
    ADDRESS("address"),
    PHONE_NUMBER("phone_number");

    private final String tableColumnValue;
}
