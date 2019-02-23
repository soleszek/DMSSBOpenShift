package com.oleszeksylwester.dmssb.DMSSB.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long message_id;
    private String name;
    private String title;
    private String content;
    @ManyToOne(optional = false)
    @JoinColumn(name="sender_id")
    private User sender;
    @ManyToOne(optional = false)
    @JoinColumn(name="receiver_id")
    private User receiver;
    private LocalDate sendingDate;
    private LocalDate receivingDate;
    private boolean isRead;
    private boolean isDeleted;

    public Message() {
    }

    public Message(String name, String title, String content, User sender, User receiver, LocalDate sendingDate, LocalDate receivingDate, boolean isRead, boolean isDeleted) {
        this.name = name;
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.sendingDate = sendingDate;
        this.receivingDate = receivingDate;
        this.isRead = isRead;
        this.isDeleted = isDeleted;
    }

    public Long getMessage_id() {
        return message_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public LocalDate getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(LocalDate sendingDate) {
        this.sendingDate = sendingDate;
    }

    public LocalDate getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(LocalDate receivingDate) {
        this.receivingDate = receivingDate;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean read) {
        isRead = read;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
