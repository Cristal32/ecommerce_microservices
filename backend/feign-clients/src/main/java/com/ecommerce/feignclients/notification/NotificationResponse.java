package com.ecommerce.feignclients.notification;

import java.time.LocalDateTime;

public class NotificationResponse {
    private Long clientId;
    private String clientEmail;
    private String sender;
    private String msg;
    private LocalDateTime sentAt;

    // Default constructor
    public NotificationResponse() {
    }

    // All-args constructor
    public NotificationResponse(Long clientId, String clientEmail, String sender, String msg, LocalDateTime sentAt) {
        this.clientId = clientId;
        this.clientEmail = clientEmail;
        this.sender = sender;
        this.msg = msg;
        this.sentAt = sentAt;
    }

    // Getters and setters
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    // toString method
    @Override
    public String toString() {
        return "NotificationResponse{" +
                "clientId=" + clientId +
                ", clientEmail='" + clientEmail + '\'' +
                ", sender='" + sender + '\'' +
                ", msg='" + msg + '\'' +
                ", sentAt=" + sentAt +
                '}';
    }
}
