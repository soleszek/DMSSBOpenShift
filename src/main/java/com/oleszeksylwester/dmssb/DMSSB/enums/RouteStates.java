package com.oleszeksylwester.dmssb.DMSSB.enums;

public enum RouteStates {
    NOT_STARTED("not started"),
    CHECKING("checking"),
    APPROVING("approving"),
    COMPLETED("completed");

    String state;

    RouteStates(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}


