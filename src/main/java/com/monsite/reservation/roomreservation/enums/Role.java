package com.monsite.reservation.roomreservation.enums;

public enum Role {
    ADMIN("admin"),
    CLIENT("client");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
