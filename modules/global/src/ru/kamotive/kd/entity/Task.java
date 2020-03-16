package ru.kamotive.kd.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;

@Table(name = "KD_TASK")
@Entity(name = "kd_Task")
public class Task extends StandardEntity {
    private static final long serialVersionUID = 5026046743277595070L;

    @Column(name = "STATE")
    protected Integer state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    protected User user;

    @Column(name = "DESCRIPTION", length = 2000)
    protected String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TaskStatusEnum getState() {
        return state == null ? null : TaskStatusEnum.fromId(state);
    }

    public void setState(TaskStatusEnum state) {
        this.state = state == null ? null : state.getId();
    }
}