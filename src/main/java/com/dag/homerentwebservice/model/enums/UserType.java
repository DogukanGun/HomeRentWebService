package com.dag.homerentwebservice.model.enums;

public enum UserType {
    USER("user"),
    TENANT("tenant"),
    LANDLORD("landlord"),
    ADMIN("admin");

    public final String label;

    public String getLabel(){ return label; }

    private UserType(String label) {
        this.label = label;
    }
}
