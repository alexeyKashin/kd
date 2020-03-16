package ru.kamotive.kd.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum TaskStatusEnum implements EnumClass<Integer> {

    NEW(10),
    INPROGRESS(20),
    DONE(30);

    private Integer id;

    TaskStatusEnum(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static TaskStatusEnum fromId(Integer id) {
        for (TaskStatusEnum at : TaskStatusEnum.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}