package com.oleszeksylwester.dmssb.DMSSB.enums;

public enum ObjectTypes {
    USER("user"),
    DRAWING("drawing"),
    IMAGE("image"),
    DOCUMENT("document"),
    ROUTE("route"),
    TASK("task"),
    MESSAGE("message");

    String objectType;

    ObjectTypes(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectType() {
        return objectType;
    }
}
