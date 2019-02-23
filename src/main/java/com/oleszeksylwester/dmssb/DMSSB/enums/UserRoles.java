package com.oleszeksylwester.dmssb.DMSSB.enums;

public enum UserRoles {
    VIEWER("viewer"),
    CONTRIBUTOR("contributor"),
    MANAGER("manager"),
    ADMIN("admin");

    String role;

    UserRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
