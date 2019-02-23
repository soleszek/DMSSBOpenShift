package com.oleszeksylwester.dmssb.DMSSB.enums;

public enum TaskStates {
    ACTIVE("active"),
    COMPLETED("completed");

    String state;

    TaskStates(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
