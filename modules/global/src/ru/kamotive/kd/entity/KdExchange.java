package ru.kamotive.kd.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "KD_KD_EXCHANGE")
@Entity(name = "kd_KdExchange")
public class KdExchange extends StandardEntity {
    private static final long serialVersionUID = 4839530974200601778L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHOR_ID")
    protected User author;

    @Column(name = "STATE")
    protected String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECEIVER_ID")
    protected User receiver;

    @Column(name = "START_DATE")
    protected LocalDateTime startDate;

    @Column(name = "RECEIVE_DATE")
    protected LocalDateTime receiveDate;

    @Column(name = "DOCUMENT_LINKS", length = 4000)
    protected String documentLinks;

    @Column(name = "PROCESS_ID")
    protected String processId;

    @Column(name = "EXTERNAL_ID")
    protected String externalId;

    public ExchangeStatus getState() {
        return state == null ? null : ExchangeStatus.fromId(state);
    }

    public void setState(ExchangeStatus state) {
        this.state = state == null ? null : state.getId();
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getDocumentLinks() {
        return documentLinks;
    }

    public void setDocumentLinks(String documentLinks) {
        this.documentLinks = documentLinks;
    }

    public LocalDateTime getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDateTime receiveDate) {
        this.receiveDate = receiveDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}