package ru.kamotive.kd.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "KD_TASK")
@Entity(name = "kd_Task")
public class Task extends StandardEntity {
    private static final long serialVersionUID = 5026046743277595070L;
}