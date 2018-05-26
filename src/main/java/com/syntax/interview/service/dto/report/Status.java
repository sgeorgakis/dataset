package com.syntax.interview.service.dto.report;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {

    CREATED("created"), DELETED("deleted"), UNMODIFIED("unmodified"), MODIFIED("modified");

    private String name;

    Status(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Status{"
                + "name='" + name + '\''
                + '}';
    }
}
