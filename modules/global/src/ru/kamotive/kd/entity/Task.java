package ru.kamotive.kd.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;

@PublishEntityChangedEvents
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

    @Column(name = "SUMMARY")
    protected String summary;

    @Column(name = "BP_CODE")
    protected String bpCode;

    @Column(name = "TASK_CODE")
    protected String taskCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INITIATOR_ID")
    protected User initiator;

    public User getInitiator() {
        return initiator;
    }

    public void setInitiator(User initiator) {
        this.initiator = initiator;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getBpCode() {
        return bpCode;
    }

    public void setBpCode(String bpCode) {
        this.bpCode = bpCode;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

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