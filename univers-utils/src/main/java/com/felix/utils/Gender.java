package com.felix.utils;

public enum Gender {

    MALE("male"),
    FEMALE("female");

    private String string;

    Gender(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
