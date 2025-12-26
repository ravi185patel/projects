package com.ravidpatel.mybookingapp.constant;

public enum BookingStatus {
    CONFIRMED("CONFIRMED"),
    UNCONFIRMED("UNCONFIRMED"),
    CANCELLED("CANCELLED");

    private final String name;

    BookingStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

