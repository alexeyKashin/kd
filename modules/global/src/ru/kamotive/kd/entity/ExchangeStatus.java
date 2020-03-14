package ru.kamotive.kd.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum ExchangeStatus implements EnumClass<String> {

    IN_PROCESS("IN_PROCESS"),
    DELIVERED("DELIVERED"),
    DONE("DONE");

    private String id;

    ExchangeStatus(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static ExchangeStatus fromId(String id) {
        for (ExchangeStatus at : ExchangeStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}